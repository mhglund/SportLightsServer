package sportlights;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Field {

  @Id @GeneratedValue private Long id;
  private String name;
    private String visitors;
    private boolean lights;

    public Field() {}

    public Field(String name, Long id) {
        this.name = name;
        this.id = id;
        this.setVisitorAmount();
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

        double noiseLevel = Math.floor(Math.random() * 3) + 1;

        if (noiseLevel == 1) {
            this.visitors ="Låg belastning";
        }
        if (noiseLevel == 2) {
            this.visitors = "Medel belastning";
        }
        if (noiseLevel == 3) {
            this.visitors = "Hög belastning";
        }

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
