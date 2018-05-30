package sportlights;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean fieldRepository
// CRUD refers Create, Read, Update, Delete

public interface FieldRepository extends CrudRepository<Field, Long> {
    List<Field> findByName(String name);
    List<Field> findAllByOrderByNameAsc();
    Field getFieldById(Long fieldId);
}
