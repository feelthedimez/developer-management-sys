package za.co.wethinkcode.dms.checkInAndOutSystem.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity @Table(name = "check_out")
@Getter @Setter @NoArgsConstructor
public class CheckOutEntity {
    @Id
    @Column(name="checkout_id", updatable = false, nullable = false)
    private UUID id;
    @Column(name="username" ,nullable = false)
    private String username;
    @Column(name="check_out_time" , nullable = false, unique = true)
    private LocalTime time;
    @Column(name="check_out_date", nullable = false, unique = true)
    private LocalDate date;

    public CheckOutEntity(String username, LocalTime time, LocalDate date) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.time = time;
        this.date = date;
    }

}
