package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "state")
public class State {

  private static final long serialVersionUID = 1L;

  @Id
  public Long id;

  public Float rank;

  public String token;

  public User user;

  @ManyToOne
  public Instance instance;

  public String toString() {
    return "["+id+":"+token+"]";
  }

}
