package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instance")
public class Instance {

  private static final long serialVersionUID = 1L;

  @Id
  public Long id;

  public Long seed;

  public String gameName;

  public String toString() {
    return "["+id+":"+gameName+"]";
  }

}
