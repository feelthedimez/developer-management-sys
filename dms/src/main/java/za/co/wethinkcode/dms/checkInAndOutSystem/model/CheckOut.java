package za.co.wethinkcode.dms.checkInAndOutSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class CheckOut implements Checks {

    private String username;
    private LocalDate date;
    private LocalTime time;
    @JsonIgnore
    private boolean didUserCheckOutLate;
    @JsonIgnore
    private boolean isUserCheckedOut;

    public static CheckOut createCheckOut(String username, LocalTime time, LocalDate date) {
        return new CheckOut(username, time, date, time.isAfter(LocalTime.parse("18:00")), true);
    }

    private CheckOut(String username, LocalTime time, LocalDate date, boolean checkedOutLate, boolean isCheckedOut) {
        this.username = username;
        this.time = time;
        this.date = date;
        this.isUserCheckedOut = isCheckedOut;
        this.didUserCheckOutLate = checkedOutLate;
    }

}
