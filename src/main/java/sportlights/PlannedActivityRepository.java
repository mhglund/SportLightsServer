package sportlights;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called plannedActivityRepository
// CRUD refers Create, Read, Update, Delete

public interface PlannedActivityRepository extends CrudRepository<PlannedActivity, Long> {

  public List<PlannedActivity> getByFieldId(Long id);
}