package sportlights;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;


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

/*public String getFieldName() {
      return fieldRepository.getFieldById(fieldId).getName();
}*/


  @Override
  public String toString() {
    return "Rating{" + "userId=" + this.userId + ", field='" + this.fieldId + "' }";
  }
}
