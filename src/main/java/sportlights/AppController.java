package sportlights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "*")
// {"http://localhost:4200", "http://localhost:8080", "http://localhost:8100"})
@RestController
public class AppController {
  @Autowired private PlannedActivityRepository plannedActivityRepository;
  @Autowired private FieldRepository fieldRepository;

  // Field API

  @RequestMapping("/field")
  public @ResponseBody Iterable<Field> goodField() {
    System.out.println("/field");
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
  public @ResponseBody Iterable<PlannedActivity> getAllPlannedActivities(@PathVariable("id") Long id) {
    System.out.println("/field/" + id + "/activity");
    return plannedActivityRepository.getByFieldId(id);
  }

  @RequestMapping(value = "/field/{id}/activity/{aid}", method = RequestMethod.GET)
  public @ResponseBody PlannedActivity getPlannedActivityByID(
      @PathVariable("id") Long id, @PathVariable("aid") Long aid) {
    // ignore field id, as its name is already in the activity structure
    System.out.println("/field/" + id + "/activity" + aid);
    return plannedActivityRepository.findById(aid).get();
  }

  @RequestMapping(value = "/field/{id}/activity/add", method = RequestMethod.POST)
  public @ResponseBody String addPlannedActivity(
      @PathVariable("id") Long id,
      @RequestBody String title,
      @RequestBody String description,
      @RequestBody Date startTime,
      @RequestBody Date endTime,
      @RequestBody Boolean allDay) {

    System.out.println("/field/" + id + "/activity/add");
    PlannedActivity a = new PlannedActivity();
    a.setFieldId(id);
    a.setTitle(title);
    a.setDescription(description);
    a.setStartTime(startTime);
    a.setEndTime(endTime);
    a.setAllDay(allDay);
    plannedActivityRepository.save(a);
    return "Saved";
  }

}
