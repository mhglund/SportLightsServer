package sportlights;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called lampRepository
// CRUD refers Create, Read, Update, Delete

public interface LampRepository extends CrudRepository<Lamp, Long> {

  public List<LampRepository> getByFieldId(Long id);
}