package za.co.wethinkcode.dms.checkInAndOutSystem.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity @Table(name = "check_out")
@Getter @Setter @NoArgsConstructor @ToString
public class CheckOutEntity {
    @Id
    @Column(name="checkout_id", updatable = false, nullable = false)
    private String id;
    @Column(name="username" ,nullable = false)
    private String username;
    @Column(name="check_out_time" , nullable = false)
    private LocalTime time;
    @Column(name="check_out_date", nullable = false)
    private LocalDate date;
    @Column(name="phone_number", nullable = false, updatable = false)
    private String phoneNumber;

    public CheckOutEntity(String username, String phoneNumber, LocalTime time, LocalDate date) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.time = time;
        this.date = date;
    }

    public CheckOutEntity(String id, String username, String phoneNumber, LocalTime time, LocalDate date) {
        this.id = id;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.time = time;
        this.date = date;
    }

    public CheckOutEntity(String username, LocalDate date, String phoneNumber) {
        this.id = username + date.toString().replace("-", "");
        this.username = username;
        this.date = date;
        this.phoneNumber = phoneNumber;
        this.time = LocalTime.parse("23:59:50");
    }

}
