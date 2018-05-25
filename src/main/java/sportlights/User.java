package sportlights;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

  @Id @GeneratedValue private Long userId;
  private String name;

    public User() {}

    public User(String name, Long userId) {
        this.name = name;
        this.userId = userId;
    }

    public Long getUserid() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
  public String toString() {
    return "User{" + "userId=" + userId + ", name='" + name + '\'' + '}';
  }
}
