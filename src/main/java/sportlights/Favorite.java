package sportlights;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Favorite {
  // dumma JPA vill absolut ha en unik identitet.  Jaaajaaa.....
  @Id @GeneratedValue Long dummyId;
  private Long fieldId;
  private String userId;

  public Favorite() {}

  public Favorite(Long fieldId, String userId) {
    this.fieldId = fieldId;
    this.userId = userId;
  }

  public Long getFieldId() {
    return fieldId;
  }

  public void setFieldId(Long fieldId) {
    this.fieldId = fieldId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public void setDummyId() {
    this.dummyId = dummyId;
  }

  public long getDummyId() {
    return dummyId;
  }

  @Override
  public String toString() {
    return "Rating{" + "userId=" + this.userId + ", field='" + this.fieldId + '\'' + '}';
  }
}
