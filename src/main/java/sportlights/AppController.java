package sportlights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Hashtable;
import java.util.TreeSet;
import java.time.LocalDateTime;

import java.util.List;

@CrossOrigin(origins = "*")
// {"http://localhost:4200", "http://localhost:8080", "http://localhost:8100"})
@RestController
public class AppController {
  @Autowired private PlannedActivityRepository plannedActivityRepository;
  @Autowired private FieldRepository fieldRepository;
  @Autowired private UserRepository userRepository;
  @Autowired private RatingRepository ratingRepository;
  @Autowired private FavoriteRepository favoriteRepository;

  // 2 hashtabeller för att hålla koll på när bokade aktiviteter senast hämtades
  private Hashtable<String, LocalDateTime> lastUpdated;
  private Hashtable<String, TreeSet<BookedActivity>> fieldBookedActivities;

  // Field API
  @RequestMapping("/field")
  public @ResponseBody Iterable<Field> goodField() {
    System.out.println("/field");
    for (Field field : fieldRepository.findAllByOrderByNameAsc()) {
      field.setVisitors();
    }
    return fieldRepository.findAllByOrderByNameAsc();
  }

  @RequestMapping("/field/{id}")
  public @ResponseBody Field aField(@PathVariable("id") Long id) {
    System.out.println("/field/" + id);
    return fieldRepository.findById(id).get();
  }

  @RequestMapping(value = "/field/{id}/lightson", method = RequestMethod.PUT)
  public void setLight(@PathVariable("id") Long id) {
    System.out.println("/field/" + id + "/lightson");
    fieldRepository.findById(id).get().lightsOn();
  }

  @RequestMapping(value = "/field/{id}/activity", method = RequestMethod.GET)
  public @ResponseBody Iterable<PlannedActivity> getAllPlannedActivities(
      @PathVariable("id") Long id) {
    System.out.println("/field/" + id + "/activity");
    return plannedActivityRepository.getByFieldId(id);
  }

  // Hämta bokade aktiviteter
  @RequestMapping(value = "/field/{id}/bookedactivity", method = RequestMethod.GET)
  public @ResponseBody Iterable<BookedActivity> getAllBookedActivities(
      @PathVariable("id") Long id) {
    TreeSet<BookedActivity> bookedActivities;

    // Hämta ut planens namn utifrån id
    String fieldName = fieldRepository.findById(id).get().getName();
    // Skapa kopia utav namnet (som stämmer med API)
    String realName = fieldName;

    // Om inte bokade aktiviteter för planen har förfrågats förut
    if (lastUpdated == null) {
      lastUpdated = new Hashtable<>();
      fieldBookedActivities = new Hashtable<>();
    }

    // Olika namn i API och från Interbook, därför byts 'bollplan' ut mot BP
    // (finns med både stort och litet B, därför kollar den efter 'ollplan'...
    if (fieldName.contains("ollplan")) {
      // Byt ut till namn som passar Interbook
      System.out.println("tjosan 1. " + fieldName);
      fieldName = fieldName.replace("s bollplan", " BP");
      System.out.println("2. " + fieldName);
      fieldName = fieldName.replace("bollplan", "BP");
      System.out.println("3. " + fieldName);
      fieldName = fieldName.replace("Bollplan", "BP");
      System.out.println("4. " + fieldName);
    }

    // Om planens bokade aktiviteter inte hämtats förut eller för längre tid sedan än
    // en timma så scrapea Interbook på den planens aktiviteter
    if (lastUpdated.get(realName) == null
        || lastUpdated.get(realName).isBefore(LocalDateTime.now().minusHours(1))) {
      bookedActivities = WebScraperInterbook.scrapeInterbook(fieldName);

      // Lagra aktiviteterna på servern samt datum+tid för hämtning av dem
      fieldBookedActivities.put(realName, bookedActivities);
      lastUpdated.put(realName, LocalDateTime.now());
    } else {
      // Om de scrapeats nyligen hämtas den listan som finns på servern
      bookedActivities = fieldBookedActivities.get(realName);
    }

    return bookedActivities;
  }

  @RequestMapping(value = "/field/{id}/activity/remove/{aid}", method = RequestMethod.DELETE)
  public @ResponseBody void removePlannedActivity(
      @PathVariable("id") Long id, @PathVariable("aid") Integer aid) {
    System.out.println("/field/" + id + "/activity/" + aid + "/remove");
    plannedActivityRepository.deleteById(aid);
  }

  @RequestMapping(
    value = "/field/{id}/activity/add",
    method = RequestMethod.POST,
    consumes = "application/json"
  )
  public @ResponseBody PlannedActivity addPlannedActivity(
      @PathVariable("id") Long id, @RequestBody PlannedActivity a) {
    System.out.println("/field/" + id + "/activity/add");
    a.setFieldId(id);
    return plannedActivityRepository.save(a);
  }

  @RequestMapping(
    value = "/user/{userId}/field/{id}/favorite/add",
    method = RequestMethod.POST,
    consumes = "application/json"
  )
  public @ResponseBody Favorite addFavorite(
      @PathVariable("userId") String userId, @PathVariable("id") Long id) {
    System.out.println("/user/" + userId + "/field/" + id + "/favorite/add");

    List<Favorite> favorites = favoriteRepository.getByFieldIdAndUserId(id, userId);
    if (favorites.isEmpty()) {
      Favorite favorite = new Favorite(id, userId);
      return favoriteRepository.save(favorite);
    }
    return favorites.get(0);
  }

 /* @RequestMapping(value = "/user/{userId}/field/{id}/favorite/remove", method = RequestMethod.DELETE)
  public @ResponseBody void removeFavorite(
      @PathVariable("userId") String userId, @PathVariable("id") Long id) {
    System.out.println("/user/" + userId + "/field/" + id + "/favorite/remove");
    List<Favorite> favorites = favoriteRepository.getByFieldIdAndUserId(id, userId);
    Favorite favoriteToBeDeleted = favorites.get(0);
    favoriteRepository.deleteById(favoriteToBeDeleted.getDummyId());
    favorites.clear();
  }*/

  @RequestMapping(
    value = "/user/{userId}/field/{id}/rating/{value}",
    method = RequestMethod.POST,
    consumes = "application/json"
  )
  public @ResponseBody Rating addRating(
      @PathVariable("userId") Long userId,
      @PathVariable("id") Long id,
      @PathVariable("value") Integer value) {
    System.out.println("/user/" + userId + "/field/" + id + "/rating/" + value);

    List<Rating> ratings = ratingRepository.getByUserIdAndFieldId(userId, id);
    if (ratings.isEmpty()) {
      Rating rating = new Rating(value, userId, id);
      System.out.println(rating);
      return ratingRepository.save(rating);
    }
    System.out.println(ratings.get(0));
    return ratings.get(0);
  }

  @RequestMapping(value = "/field/{id}/rating", method = RequestMethod.GET)
  public @ResponseBody Integer getRateAverage(@PathVariable("id") Long id) {
    List<Rating> ratings = ratingRepository.getAllByFieldId(id);
    if (ratings.isEmpty()) {
      return 0;
    }
    int average = 0;
    for (Rating rating: ratings) {
      average = average + rating.getValue();
    }
    return average/ratings.size();
  }
}
