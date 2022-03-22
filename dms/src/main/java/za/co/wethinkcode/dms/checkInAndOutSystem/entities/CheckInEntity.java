package za.co.wethinkcode.dms.checkInAndOutSystem.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CheckInEntity {
    @Id
    @Column(nullable = false)
    private String username;
    @Column(nullable = false, unique = true)
    private LocalTime time;
    @Column(nullable = false, unique = true)
    private LocalDate date;

    public CheckInEntity(String username, LocalTime time, LocalDate date) {
        this.username = username;
        this.time = time;
        this.date = date;
    }
}
