package sportlights;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean fieldRepository
// CRUD refers Create, Read, Update, Delete

public interface FieldRepository extends CrudRepository<Field, Long> {
    public List<Field> findByName(String name);
    public List<Field> findAllByOrderByNameAsc();
}
