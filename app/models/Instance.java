package models;

import game.Board;
import game.GameError;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import play.data.format.*;
import play.data.validation.*;

import play.db.ebean.*;

@Entity
public class Instance extends Model {
	
	@Id
	public Integer id;

	public String name;

	@JsonIgnore
	public Long seed;
	
	public String game;
	
	@ManyToMany
	public Set<User> users = new HashSet<User>();
	
	@OneToMany
	public List<Command> commands;
	
	public static Finder<Integer,Instance> find = new Finder<Integer,Instance>(Integer.class, Instance.class);
	
	@JsonIgnore
	public List<String> getCommandList() {
		List<String> commandStrings = new ArrayList<String>(commands.size());
		for(Command command : commands) {
			commandStrings.add(command.command);
		}
		return commandStrings;
	}
	
	@Transient
	public Board getBoard() {
		
		Board board = null;
		
		if(game != null) {
			try {
				board = (Board)Class.forName("game."+game+".Board").newInstance();
			}
			catch(Exception e) {
				throw new RuntimeException(e);
			}
			
			try {
				//by your command
				for(String command : getCommandList()) {
					board.runCommand(board.getCommand(command));
				}
			}
			catch(GameError e) {
			}
		}
		
		return board;
	}
	
	
	
}
