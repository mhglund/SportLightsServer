package sportlights;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean fieldRepository
// CRUD refers Create, Read, Update, Delete

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {
    List<Long> findUserIdByFieldId(Long id);
    List<Long> findFieldIdByUserId(Long userId);
    List<Favorite> getByFieldIdAndUserId(Long id, Long userId);
    Iterable<Favorite> getByUserId(Long userId);
}