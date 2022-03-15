package za.co.wethinkcode.dms.absenteeismFeature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class CheckIn implements Checks {
    @Id
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private LocalTime time;
    @Column(nullable = false)
    private boolean isLate;
    @Column(nullable = false)
    private boolean isCheckedIn;

    public CheckIn() {}

    public static CheckIn createCheckIn(String username, LocalTime time, LocalDate date){
        return new CheckIn(username, time, date, time.isAfter(LocalTime.parse("10:30")), true);
    }

    private CheckIn(String username, LocalTime time, LocalDate date, boolean isLate, boolean isCheckedIn) {
        this.username = username;
        this.time = time;
        this.date = date;
        this.isLate = isLate;
        this.isCheckedIn = isCheckedIn;
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
                "username='" + username + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", isLate=" + isLate +
                ", isCheckedIn=" + isCheckedIn +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public boolean isCheckedIn() {
        return isCheckedIn;
    }
}
