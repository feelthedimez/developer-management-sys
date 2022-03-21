package za.co.wethinkcode.dms.checkInAndOutSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class CheckOut implements Checks {
    @Id
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private LocalTime time;
    @Column(nullable = false)
    private boolean checkedOutLate;
    @Column(nullable = false)
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
