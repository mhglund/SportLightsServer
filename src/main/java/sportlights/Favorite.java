package sportlights;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Favorite {
    @Id
    @GeneratedValue
    private long userId;
    private boolean isFavorite = false;   

	public long getUserId() {
		return userId;
	}

	public void setUserId(long uid) {
		this.userId = userId;
	}

	public boolean getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}
    @Override

    public String toString() {
        return "Rating{" + "userId=" + userId + ", isFavorite='" + isFavorite + '\'' + '}';
  }
}