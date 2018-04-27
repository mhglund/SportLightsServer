package sportlights;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080", "http://localhost:8100"})
@RestController
public class AppController {
  private ArrayList<Field> fields;
  private String apiUrl = "http://api.stockholm.se/ServiceGuideService/ServiceUnitTypes/" +
          "a05cd75b-c974-4890-9a7d-abc790997cf1/ServiceUnits/json?";
  private String apiKey = "&apikey=d2c76f717a554a9bb7496a06fc16b8fc";
  private RestTemplate restTemplate = new RestTemplate();

    public AppController() {
        fields = new ArrayList<>();
    }

    @RequestMapping("/field")
    public Collection<Field> goodField() {
        getApi();
        return fields;
    }

  @RequestMapping("/api")
  public void api() {
      getApi();
  }

    public void getApi() {
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
     for (String name: names) {
       fields.add(new Field(name));
     }
     fields.sort(new Comparator<Field>() {
         @Override
         public int compare(Field o1, Field o2) {
             return o1.getName().compareTo(o2.getName());
         }
     });
  }
}
