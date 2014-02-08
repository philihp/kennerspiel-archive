package models;

import game.Board;

import javax.persistence.*;
import javax.validation.Constraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import play.data.format.*;
import play.data.validation.*;

import play.db.ebean.*;

@Entity
public class Command extends Model {
	
	@Id
	public Integer id;
	
	@JsonIgnore
	@ManyToOne
	public Instance instance;

	public String command;
		
	public static Finder<Integer,Command> find = new Finder<Integer,Command>(Integer.class, Command.class);
	
	
}
