package za.co.wethinkcode.dms.absenteeismFeature.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class CheckOut implements Checks {
    @Id
    private String username;
    private LocalDate date;
    private LocalTime time;

    public CheckOut() {}

    public CheckOut(LocalTime time, LocalDate date) {
        this.time = time;
        this.date = date;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public LocalTime getTime() {
        return time;
    }
}
