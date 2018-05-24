package sportlights;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

public class Sensor {
  private String visitors;
  private double value;
  private LocalDateTime lastUpdate;

  public Sensor(double first) {
    value = first;
  }

  private void updateVisitors() {
    LocalDateTime last = lastUpdate;
    LocalDateTime current = LocalDateTime.now();
    if (last == null || Duration.between(last, current).getSeconds() >= 1800) {
      lastUpdate = current;
      value = Math.floor(Math.random() * 30) + 1;
      if (current.getMonthValue() <= 4 || current.getMonthValue() >= 8) {
        value *= 1.1;
      } else {
        value *= 1.7;
      }
      if (current.getDayOfWeek().equals(DayOfWeek.SATURDAY)
          || current.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
        if (current.getHour() < 5 || current.getHour() > 22) {
          value *= 0.8;
        } else {
          value *= 1.5;
        }
      } else {
        if (current.getHour() < 5 || current.getHour() > 22) {
          value *= 0.6;
        } else if (current.getHour() > 17 && current.getHour() < 22) {
          value *= 1.5;
        } else {
          value *= 1.1;
        }
      }
    }
    if (value < 10) {
      visitors = "Låg belastning";
    } else if (value > 40) {
      visitors = "Hög belastning";
    } else {
      visitors = "Medel belastning";
    }
  }

  public String getVisitors() {
    updateVisitors();
    return visitors;
  }
}
