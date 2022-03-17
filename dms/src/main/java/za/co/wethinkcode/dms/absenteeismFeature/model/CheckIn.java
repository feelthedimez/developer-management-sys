package za.co.wethinkcode.dms.absenteeismFeature.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalTime;

public class CheckIn implements Checks {
    private String username;
    private LocalDate date;
    private LocalTime time;
    @JsonIgnore
    private boolean isLate;
    @JsonIgnore
    private boolean isCheckedIn;

    public CheckIn() {}

    public static CheckIn createCheckIn(String username, LocalTime time, LocalDate date){
        return new CheckIn(username, time, date, time.isAfter(LocalTime.parse("10:30")), true);
    }

    public CheckIn( String username, LocalTime time, LocalDate date, boolean isLate, boolean isCheckedIn) {
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

    public void setLate(boolean late) {
        isLate = late;
    }

    public void setCheckedIn(boolean checkedIn) {
        isCheckedIn = checkedIn;
    }

    public String getUsername() {
        return username;
    }

    public boolean isCheckedIn() {
        return isCheckedIn;
    }
}
