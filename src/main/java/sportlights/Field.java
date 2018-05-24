package sportlights;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Field implements Comparable<Field> {

    @Id @GeneratedValue private Long id;
    private String name;
    private String visitors;
    private boolean lights;
    @Transient private Sensor sensor;

    public Field() {}

    public Field(String name, Long id) {
        this.name = name;
        this.id = id;
        this.setVisitorAmount();
        this.sensor = new Sensor(0);
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
        return visitors;
    }

    public void setVisitors(String visitors) {
        this.visitors = visitors;
    }

    //Metod för att sätta planens belastningsgrad.
    private void setVisitorAmount() {

        visitors = this.sensor.getVisitors();

    }

    @Override
    public int compareTo(Field f) {
        int lika = this.name.compareTo(f.getName()); 
        return lika;
    }

    @Override
    public String toString() {
        return "Field{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
