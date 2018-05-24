package sportlights;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class PlannedActivity {
  @Id @GeneratedValue private Integer id;

  private Long fieldId; // To identify the field for this activity
  private String title;
  private Date startTime;
  private Date endTime;
  private boolean all_day; // never true

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

  public boolean getAllDay() {
    return this.all_day;
  }

  public void setAllDay(boolean all_day) {
    this.all_day = all_day;
  }

  @Override
  public String toString() {
    String string = "PlannedActivity{" + "id=" + id + '\'' + '}';
    return string;
    }
}
