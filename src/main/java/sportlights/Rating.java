package sportlights;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Rating {
    @Id
    @GeneratedValue
    private long userId;

    private int value;   

	public long getUserId() {
		return userId;
	}

	public void setUid(long uid) {
		this.userId = userId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
    @Override

    public String toString() {
        return "Rating{" + "userId=" + userId + ", value='" + value + '\'' + '}';
  }
}