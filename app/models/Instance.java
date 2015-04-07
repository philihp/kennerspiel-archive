package models;

import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.EnumValue;
import com.avaje.ebean.annotation.UpdatedTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "instance")
public class Instance {

  private static final long serialVersionUID = 1L;

  @Id
  public Long id;

  public Long seed;

  public String gameName;

  public String gameType;

  public enum Phase {
    @EnumValue("N") NEW,
    @EnumValue("A") ACTIVE,
    @EnumValue("F") FINISHED,
  }
  public Phase phase;

  @OneToMany(mappedBy="instance")
  public List<State> states;

  @ManyToMany
  public List<User> players = new ArrayList<>();

  @Version
  public Timestamp dateUpdated;

  @CreatedTimestamp
  public Timestamp dateCreated;

  /**
   * Necessary because Ebean is broken
   */
  public void sortStates() {
    states.sort(
    new Comparator<State>() {
      @Override public int compare(State s1, State s2) {
        return (int)(s1.id - s2.id);
      }
    });
  }

  public String toString() {
    return "{" + id + ":" + gameName + "}";
  }

}
