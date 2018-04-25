package sportlights;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.ArrayList;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080", "http://localhost:8100", "https://pvt.dsv.su.se/Group02"})
@RestController
public class AppController {
    private ArrayList<Field> fields;

    public AppController() {
        fields = new ArrayList<>();
        fields.add(new Field("Enskede IP"));
        fields.add(new Field("Zinkensdamm IP"));
    }

    @RequestMapping("/field")
    public Collection<Field> goodField() {

        return fields;
    }

}
