package za.co.wethinkcode.dms.checkInAndOutSystem.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
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
}
