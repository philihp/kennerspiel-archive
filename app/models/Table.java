package models;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import game.Board;
import play.db.ebean.Model;

@Entity
public class Table extends Model {

	@Id
	public Long id;

	public String name;

	public Integer seed;

	public static Model.Finder<Integer, Table> finder = new Model.Finder<Integer, Table>(
			Integer.class, Table.class);
	
	public String game;

	@Transient
	public Random random;
	
	@Transient
	public Board board;

	@PostConstruct
	public void init() {
		random = new Random(seed);
	}
}
