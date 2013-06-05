package models;

import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonManagedReference;

import game.Board;
import play.db.ebean.Model;

@Entity
public class Table extends Model {

	public static Model.Finder<Integer, Table> finder = new Model.Finder<Integer, Table>(
			Integer.class, Table.class);

	@Id
	public Long id;

	public String name;

	public Integer seed;
	
	public String game;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JsonManagedReference
	public List<Command> commands;

	@Transient
	public Random random;
	
	@Transient
	public Board board;

	@PostConstruct
	public void init() {
		random = new Random(seed);
	}
}
