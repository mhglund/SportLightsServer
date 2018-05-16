package sportlights;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class Lamp {
  @Id @GeneratedValue private Integer id;

  private Long fieldId; // To identify the field for this activity
  private Boolean lights;
  

  public boolean LightsOn() {
    return lights;
  }

  public void setLights(boolean lights) {
    this.lights = lights;
  }

  public Long getFieldId() {
    return fieldId;
  }

  public void setFieldId(Long fieldId) {
    this.fieldId = fieldId;
  }

  @Override
  public String toString() {
    String string = "lights{" + "id=" + id + '\'' + '}';
    return string;
    }
}
