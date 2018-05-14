package sportlights;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class Activity {
  @Id @GeneratedValue private Integer id;

  private Long fieldId; // To identify the field for this activity
  private String title;
  private String description;
  private Date startTime;
  private Date endTime;
  private boolean allDay;

  public boolean isAllDay() {
    return allDay;
  }

  public void setAllDay(boolean allDay) {
    this.allDay = allDay;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Long getFieldId() {
    return fieldId;
  }

  public void setFieldId(Long fieldId) {
    this.fieldId = fieldId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  @Override
  public String toString() {
    String string = "Activity{" + "id=" + id + '\'' + '}';
    return string;
    }
}
