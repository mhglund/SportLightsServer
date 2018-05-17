package sportlights;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Rating {
    @Id
    @GeneratedValue
    private int id;

    private int value;   

	public Integer ID() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
    @Override

    public String toString() {
        return "Rating{" + "id=" + id + ", value='" + value + '\'' + '}';
  }
}