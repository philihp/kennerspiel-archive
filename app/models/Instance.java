package models;

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

  @OneToMany
  @OrderBy("rank")
  public List<State> states;

  @ManyToMany
  public List<User> players = new ArrayList<>();

  public String toString() {
    return "["+id+":"+gameName+"]";
  }

}
