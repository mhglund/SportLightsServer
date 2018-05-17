package sportlights;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called plannedActivityRepository
// CRUD refers Create, Read, Update, Delete
//För varje klass som ska sparas i databasen som en egen tabell, 
//så behövs det en motsvarande repositoryklass som ser ut så här. 
//Den skapar en Java bean, som hanterar den automatiska uppdateringen av databastabeller. 

public interface PlannedActivityRepository extends CrudRepository<PlannedActivity, Long> {

  public List<PlannedActivity> getByFieldId(Long id);
}