package models;

import java.util.*;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User extends Model {

	@Id
	public String email;
	
	public String name;
	
	@JsonIgnore
	public String password;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "users")
	public Set<Instance> instances = new HashSet<Instance>();

	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public static Finder<String, User> find = new Finder<String, User>(
			String.class, User.class);

	public static User authenticate(String email, String password) {
		return find.where().eq("email", email).eq("password", password)
				.findUnique();
	}

//	@Override
//	public int compareTo(Object that) {
//		if(that == null)
//			return -1;
//		else if(that instanceof User == false)
//			return that.hashCode() - this.hashCode();
//		else
//			return ((User)that).email.compareTo(this.email);
//	}

}
