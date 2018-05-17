package sportlights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Comparator;

//Den här klassen startar upp appen och laddar in fotbollsplanslistan. 

//Efter att den här har körts så är appen redo att ta emot http-förfrågningar.
@Component
public class AppStartup implements ApplicationListener<ApplicationReadyEvent> { 

    @Autowired //Något som Spring behöver för att veta vad som ska skapas automatiskt vid uppstart
    private FieldRepository fieldRepository;

    private String apiUrl = "http://api.stockholm.se/ServiceGuideService/ServiceUnitTypes/"
        + "a05cd75b-c974-4890-9a7d-abc790997cf1/ServiceUnits/json?";
    private String apiKey = "&apikey=d2c76f717a554a9bb7496a06fc16b8fc";
    private RestTemplate restTemplate = new RestTemplate();

    //Ser till att getStockholmApi körs vid uppstart
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        getStockholmApi();
    }

    //Hämtar fotbollsplans-API:t och kollar om namnet redan finns sparat, annars lägger till det. 
    private void getStockholmApi() {
        ResponseEntity<String> entity = restTemplate.getForEntity(apiUrl + apiKey, String.class);
        String body = entity.getBody();
        String[] subBody = body.split(",");
        for (int i = 1; i < subBody.length; i++) {
          if (subBody[i].contains("Name")) {
            String[] temp = subBody[i].split(":");
            String name = temp[1].substring(1, temp[1].lastIndexOf('"'));
            List<Field> fields = fieldRepository.findByName(name);
            if (fields.isEmpty()) {
              Field field = new Field();
              field.setName(name);
              System.out.println("Saving new field: " + field);              
              fieldRepository.save(field);
            }
          }
        }
      }
    
}