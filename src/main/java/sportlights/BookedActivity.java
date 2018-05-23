package sportlights;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;


public class BookedActivity implements Comparable<BookedActivity>{
    public final LocalDate date;
    public final LocalTime startTid, slutTid;
    public final LocalDateTime startTime, endTime;
    public final String owner, place, description, activity;
    //public final Field theField;

    public BookedActivity(LocalDate date, LocalTime startTid, LocalTime slutTid,
                          String owner, String place, String description,
                          String activity) {
        this.date = date;
        this.startTid = startTid;
        this.slutTid = slutTid;
        this.owner = owner;
        this.place = place; 
        this.activity = activity;
        this.description = description;

        // Gör om formatet för att det ska passa bättre med PlannedActivity och appen
        this.startTime = LocalDateTime.of(date, startTid);
        this.endTime = LocalDateTime.of(date, slutTid);
    }

            @Override
            public String toString() {
            String bookedActivityInfo = "\n---------------------------------------" +
            "\n Aktivitet: " + activity +
            "\n Datum: " + date +
            "\n Starttid: " + startTime + " | Sluttid: " + endTime +
            "\n Förening: " + owner +
            "\n Hemsida: " + description +
            "\n Plats: " + place;
            return bookedActivityInfo; 
        }

        @Override
            public int compareTo(BookedActivity ba) {
            int lika = this.date.compareTo(ba.date);
            if (lika != 0) {
                return lika;
            }
        
            lika = this.startTime.compareTo(ba.startTime);
            if (lika != 0) {
                return lika;
            }

            lika = this.endTime.compareTo(ba.endTime);
            if (lika != 0) {
                return lika;
            }

            lika = this.activity.compareTo(ba.activity);
            if (lika != 0) {
                return lika;
            }

            lika = this.owner.compareTo(ba.owner);
            if (lika != 0) {
                return lika;
            }
        
            return lika;
        }

}
