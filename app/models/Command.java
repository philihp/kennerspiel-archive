package models;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import com.fasterxml.jackson.annotation.*;

import game.Board;
import play.data.Form;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.Result;

@Entity
@JsonSerialize(using = Command.Serializer.class)
public class Command extends Model {
	
	static class Serializer extends JsonSerializer<Command>{

		/**
		 * Overrides normal serialize operation by adding the table.id field
		 */
		@Override
		public void serialize(Command command, JsonGenerator jgen,
				SerializerProvider provider) throws IOException,
				JsonProcessingException {
			jgen.writeStartObject();
			jgen.writeNumberField("id", command.getId());
			jgen.writeStringField("command", command.getCommand());
			jgen.writeNumberField("table.id", command.getTable().getId());
			jgen.writeEndObject();
		}
	}

	public static Model.Finder<Integer, Command> finder = new Model.Finder<Integer, Command>(
			Integer.class, Command.class);

	@Id
	private Integer id;

	@Required
	private String command;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	private Table table;
	
	@PostConstruct
	public void init() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}
}
