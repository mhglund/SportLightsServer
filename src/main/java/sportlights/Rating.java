package sportlights;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Rating {

  @Id @GeneratedValue private long rateId;
  private int value;
  private Long userId;
  private Long fieldId;

  public Rating(int value, Long userId, Long fieldId){
    this.value = value;
    this.userId = userId;
    this.fieldId = fieldId;
  }

  public long getRateId() {
    return rateId;
  }

  public void setRateId(long rateId) {
    this.rateId = rateId;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getFieldId() {
    return fieldId;
  }

  public void setFieldId(Long fieldId) {
    this.fieldId = fieldId;
  }

  @Override
  public String toString() {
	return "Rating{" + "rateId='" + rateId + ", value='" + value + ", userId='" + userId + ", " + 
	"fieldId='" + fieldId + "" + '\'' + '}';
  }
}