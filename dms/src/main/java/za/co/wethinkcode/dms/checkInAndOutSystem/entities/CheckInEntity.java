package za.co.wethinkcode.dms.checkInAndOutSystem.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity @Table(name = "check_in")
@Getter @Setter @NoArgsConstructor @ToString
public class CheckInEntity {
    @Id
    @Column(name="checkin_id", updatable = false, nullable = false)
    private String id;
    @Column(name="username" ,nullable = false)
    private String username;
    @Column(name="check_in_time" , nullable = false, updatable = false)
    private LocalTime time;
    @Column(name="check_in_date", nullable = false, updatable = false)
    private LocalDate date;
    @Column(name="phone_number", nullable = false, updatable = false)
    private String phoneNumber;

    public CheckInEntity(String username, String phoneNumber, LocalTime time, LocalDate date) {
        this.id = username + date.toString().replace("-", "");
        this.username = username;
        this.time = time;
        this.date = date;
        this.phoneNumber = phoneNumber;
    }
}
