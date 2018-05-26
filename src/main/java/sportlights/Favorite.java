package sportlights;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Favorite {
    // dumma JPA vill absolut ha en unik identitet.  Jaaajaaa.....
    @Id @GeneratedValue Long dummyId;

    private Long fieldId;
	private Long userId; 

    public Favorite() {}

    public Favorite(Long fieldId, Long userId) {
        this.fieldId = fieldId;
        this.userId = userId;
    }
  
    public Long getFieldId() {
        return fieldId;
    }
    
    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }
  
    public Long getUserId() {
        return userId;
    }
    
    public void setId(Long userId) {
        this.userId = userId;
    }
          
    @Override
    public String toString() {
        return "Rating{" + "userId=" + userId + ", field='" + fieldId + '\'' + '}';
  }
}