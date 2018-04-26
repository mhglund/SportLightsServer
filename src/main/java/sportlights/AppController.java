package sportlights;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;

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
        fields.add(new Field("Enskede IP"));
        fields.add(new Field("Zinkensdamm IP"));
        fields.add(new Field("Hjorthagens IP"));
        fields.add(new Field("Kristinebergs IP"));
        fields.add(new Field("Hammarbyhöjdens IP"));
        fields.add(new Field("Sätra IP"));
    }

    @RequestMapping("/field")
    public Collection<Field> goodField() {

        return fields;
    }

  @RequestMapping("/api")
  public String api() {
      return getApi();
  }

    public String getApi() {
        ResponseEntity<String> entity = restTemplate.getForEntity(apiUrl + apiKey, String
                                                                  .class);
     return entity.getBody();
  }
}
