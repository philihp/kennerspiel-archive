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

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import game.Board;
import play.data.Form;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.Result;

@Entity
@JsonSerialize(using = Command.Serializer.class)
public class Command extends Model {
	
	static class Serializer extends JsonSerializer<Command>{

		/**
		 * Overrides normal seralize operation by adding the table.id field
		 */
		@Override
		public void serialize(Command command, JsonGenerator jgen,
				SerializerProvider provider) throws IOException,
				JsonProcessingException {
			jgen.writeStartObject();
			jgen.writeNumberField("id", command.id);
			jgen.writeStringField("command", command.command);
			jgen.writeNumberField("table.id", command.table.id);
			jgen.writeEndObject();
		}
	}

	public static Model.Finder<Integer, Command> finder = new Model.Finder<Integer, Command>(
			Integer.class, Command.class);

	@Id
	public Long id;

	public String command;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	public Table table;
	
	@PostConstruct
	public void init() {
	}
}
