package models;

import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

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
  @OrderBy("id")
  public Instance instance;

  @Version
  public Timestamp dateUpdated;

  @CreatedTimestamp
  public Timestamp dateCreated;

  public String toString() {
    return "[" + id + ":" + token + "]";
  }

}
