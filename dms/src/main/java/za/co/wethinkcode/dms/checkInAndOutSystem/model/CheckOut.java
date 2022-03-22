package za.co.wethinkcode.dms.checkInAndOutSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

public class CheckOut implements Checks {

    private String username;
    private LocalDate date;
    private LocalTime time;
    @JsonIgnore
    private boolean checkedOutLate;
    @JsonIgnore
    private boolean isCheckedOut;

    public CheckOut() {}

    public static CheckOut createCheckOut(String username, LocalTime time, LocalDate date) {
        return new CheckOut(username, time, date, time.isAfter(LocalTime.parse("18:00")), true);
    }

    public CheckOut(String username, LocalTime time, LocalDate date, boolean checkedOutLate, boolean isCheckedOut) {
        this.username = username;
        this.time = time;
        this.date = date;
        this.isCheckedOut = isCheckedOut;
        this.checkedOutLate = checkedOutLate;
    }

    public String getUsername() {
        return username;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public boolean isCheckedOutLate() {
        return checkedOutLate;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "CheckOut{" +
                "username='" + username + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", checkedOutLate=" + checkedOutLate +
                ", isCheckedOut=" + isCheckedOut +
                '}';
    }
}
