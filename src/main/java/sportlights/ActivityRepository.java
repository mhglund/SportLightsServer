package sportlights;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called activityRepository
// CRUD refers Create, Read, Update, Delete

public interface ActivityRepository extends CrudRepository<Activity, Long> {

  public List<Activity> getByFieldId(Long id);
}