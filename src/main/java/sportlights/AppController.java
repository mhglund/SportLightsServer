package sportlights;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;
import java.util.Comparator;

import sportlights.Activity;
import sportlights.ActivityRepository;

@CrossOrigin(origins = "*")
//{"http://localhost:4200", "http://localhost:8080", "http://localhost:8100"})
@RestController
public class AppController {
  private ArrayList<Field> fields;
  private String apiUrl = "http://api.stockholm.se/ServiceGuideService/ServiceUnitTypes/" +
          "a05cd75b-c974-4890-9a7d-abc790997cf1/ServiceUnits/json?";
  private String apiKey = "&apikey=d2c76f717a554a9bb7496a06fc16b8fc";
  private RestTemplate restTemplate = new RestTemplate();

    //Läser in fotbollsplanerna varje gång servern startar
    public AppController() {
        fields = new ArrayList<>();
        getApi();
    }

    // Field API

    private Field getFieldByID(Long id) {
        for(Field f : fields) {
            if(f.getId() == id) {
                return f;
            }
        } 

        throw new IllegalStateException("Id: " + id + " is not in the list");
    }

    @GetMapping("/field")
    public Collection<Field> goodField() {
        return fields;
    }

    @GetMapping("/field/{id}")
    public Field aField(@PathVariable("id") Long id) {
        goodField();
        return getFieldByID(id);
    }

    @PostMapping("/field/{id}/lightson")
    public void setLight(@PathVariable("id") Long id) {
        Field f = getFieldByID(id);
        f.lightsOn();
    }

    // Field Activity API

    @Autowired
    private ActivityRepository activityRepository;
  
    @GetMapping("/field/{id}/activity")
    public @ResponseBody Iterable<Activity> getAllActivities(@PathVariable("id") Long id) {
        return activityRepository.getByFieldName(getFieldByID(id).getName());
    }

    @GetMapping("/field/{id}/activity/{aid}")
    public @ResponseBody Activity getActivityByID(@PathVariable("id") Long id,
                                                            @PathVariable("aid") Long aid) {
        // ignore field id, as its name is already in the activity structure
        return activityRepository.findById(aid).get();
    }


    @PostMapping("/field/{id}/activity/add")
    public @ResponseBody String addActivity(@PathVariable("id") Long id, //maps the HttpRequest body onto a Java object
                                                        @RequestBody String title,
                                                        @RequestBody String description,
                                                        @RequestBody Date startTime,
                                                        @RequestBody Date endTime,
                                                        @RequestBody boolean allDay
                                                        ) {
        Activity a = new Activity(); //constructor for a new activity
        a.setFieldName(getFieldByID(id).getName());
        a.setTitle(title);
        a.setDescription(description);
        a.setStartTime(startTime);
        a.setEndTime(endTime);
        activityRepository.save(a);
        return "Saved";
    }

    private void getApi() {
        ResponseEntity<String> entity = restTemplate.getForEntity(apiUrl + apiKey, String.class);
        String body = entity.getBody();
        String[] subBody = body.split(",");
        ArrayList<String> names = new ArrayList<>();
        for (int i = 1; i < subBody.length; i++ ) {
            if (subBody[i].contains("Name")) {
                String[] temp = subBody[i].split(":");
                names.add(temp[1].substring(1, temp[1].lastIndexOf('"')));
            }
        }

        //Ger varje fotbollsplan ett id-nummer tillfälligt innan databasen tar plats
        Long id = (long)0; 
        for (String name: names) {
            fields.add(new Field(name, id)); 
            id++;
            
        }

        fields.get(3).lightsOn();
        
        fields.sort(Comparator.comparing(Field::getName));
    }
}

	