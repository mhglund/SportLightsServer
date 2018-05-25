package sportlights;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Favorite {
    @Id
    @GeneratedValue
	private Field field;  
	private User user; 

    @Override

    public String toString() {
        return "Rating{" + "userId=" + user + ", field='" + field + '\'' + '}';
  }
}