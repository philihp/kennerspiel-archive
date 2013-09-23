package models;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import game.Board;
import play.db.ebean.Model;

@Entity
@JsonSerialize(using = Table.Serializer.class)
public class Table extends Model {
	
	static class Serializer extends JsonSerializer<Table>{

		/**
		 * Overrides normal serialize operation by adding the table.id field
		 */
		@Override
		public void serialize(Table table, JsonGenerator jgen,
				SerializerProvider provider) throws IOException,
				JsonProcessingException {
			jgen.writeStartObject();
			jgen.writeNumberField("id", table.id);
			jgen.writeStringField("name", table.name);
			jgen.writeStringField("game", table.game);
			
			provider.defaultSerializeField("board", table.board, jgen);
			
			jgen.writeArrayFieldStart("commands");
			for(Command command : table.commands) {
				jgen.writeStartObject();
				jgen.writeNumberField("id", command.id);
				jgen.writeStringField("command", command.command);
				jgen.writeEndObject();
			}
			jgen.writeEndArray();
			
			jgen.writeEndObject();
		}
	}
	
	
	public static Model.Finder<Integer, Table> finder = new Model.Finder<Integer, Table>(
			Integer.class, Table.class);

	@Id
	public Integer id;

	public String name;

	public Integer seed;
	
	public String game;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JsonManagedReference
	public List<Command> commands;
	
	@Transient
	public Board board;

	@PostConstruct
	public void init() {
		//board = (Board)Class.forName("game."+game+".Board").newInstance();
		//board.seedRandom(seed);
		//doesn't seem to want to work here?
	}
}
