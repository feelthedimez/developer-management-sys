package za.co.wethinkcode.dms.absenteeismFeature.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class CheckIn implements Checks {
    @Id
    private String username;
    private LocalDate date;
    private LocalTime time;
    private boolean isLate;

    public CheckIn() {

    }

    public static CheckIn getCheckIn(String username, LocalTime time, LocalDate date){
        return new CheckIn(username, time, date, time.isAfter(LocalTime.parse("10:30")));
    }

    private CheckIn(String username, LocalTime time, LocalDate date, boolean isLate) {
        this.username = username;
        this.time = time;
        this.date = date;
        this.isLate = isLate;
    }

    @Override
    public LocalTime getTime() {
        return time;
    }

    public boolean isLate() {
        return isLate;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "CheckIn{" +
                "date=" + date +
                ", time=" + time +
                ", isLate=" + isLate +
                '}';
    }

}
