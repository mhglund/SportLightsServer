package sportlights;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean fieldRepository
// CRUD refers Create, Read, Update, Delete

public interface RatingRepository extends CrudRepository<Rating, Long> {
  List<Rating> getByUserIdAndFieldId(Long userId, Long FieldId);
}