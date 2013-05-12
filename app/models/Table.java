package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Table extends Model {

	@Id
	public Long id;

	public String name;

	public Integer seed;
	
	public static Model.Finder<Integer, Table> finder = new Model.Finder<Integer, Table>(Integer.class, Table.class);
}
