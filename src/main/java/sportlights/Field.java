package sportlights;

import javax.persistence.*;

@Entity
public class Field implements Comparable<Field> {

  @Id @GeneratedValue private Long id;
  private String name;
  private String visitors;
  private boolean lights;
  @Transient private Sensor sensor = new Sensor(0);
/*
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "favorite",
            joinColumns = @JoinColumn(name = "fieldId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private List<User> users = new ArrayList<>();
*/

  public Field() {}

  public Field(String name, Long id) {
    this.name = name;
    this.id = id;
    this.setVisitors();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void lightsOn() {
    this.lights = true;
  }

  public void lightsOff() {
    this.lights = false;
  }

  public boolean getLights() {
    return lights;
  }

  public String getVisitors() {
    return visitors = sensor.getVisitors();
  }

  // Metod för att sätta planens belastningsgrad.
  public void setVisitors() {
    visitors = sensor.getVisitors();
  }

  @Override
  public int compareTo(Field f) {
    return this.name.compareTo(f.getName());
  }

  @Override
  public String toString() {
    return "Field{" + "id=" + id + ", name='" + name + '\'' + '}';
  }
}
