package za.co.wethinkcode.dms.absenteeismFeature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class CheckInEntity {
    @Id
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private LocalTime time;
    @Column(nullable = false)
    private LocalDate date;

    public CheckInEntity() {}

    public CheckInEntity(String username, LocalTime time, LocalDate date) {
        this.username = username;
        this.time = time;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
