package models;

import com.avaje.ebean.annotation.EnumValue;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instance")
public class Instance {

  private static final long serialVersionUID = 1L;

  @Id
  public Long id;

  public Long seed;

  public String gameName;

  public enum Phase {
    @EnumValue("N") NEW,
    @EnumValue("A") ACTIVE,
    @EnumValue("F") FINISHED,
  }
  public Phase phase;

  @OneToMany
  @OrderBy("rank")
  public List<State> states;

  @ManyToMany
  public List<User> players = new ArrayList<>();

  public String toString() {
    return "[" + id + ":" + gameName + "]";
  }

}
