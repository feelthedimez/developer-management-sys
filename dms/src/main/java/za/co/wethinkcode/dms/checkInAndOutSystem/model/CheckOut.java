package za.co.wethinkcode.dms.checkInAndOutSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckOutEntity;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class CheckOut implements Checks {

    private String username;
    private LocalDate date;
    private LocalTime time;
    private boolean didUserCheckOutLate;
    private boolean isUserCheckedOut;

    // TODO: Create an entity to store student profiles; the profile has all the details


    public CheckOut(CheckOutEntity checkOut) {
        this.username = checkOut.getUsername();
        this.date = checkOut.getDate();
        this.time = checkOut.getTime();
        this.didUserCheckOutLate = time.isAfter(LocalTime.parse("18:00"));
        this.isUserCheckedOut = !checkOut.getTime().equals(LocalTime.parse("23:59:50"));
    }

    public static CheckOut createCheckOut(String username, LocalTime time, LocalDate date) {
        return new CheckOut(username, time, date, time.isAfter(LocalTime.parse("18:00")), !time.equals(LocalTime.parse("23:59:50")));
    }

    public static CheckOut createCheckOut(CheckOutEntity checkOut) {
        return new CheckOut(checkOut);
    }

    private CheckOut(String username, LocalTime time, LocalDate date, boolean checkedOutLate, boolean isCheckedOut) {
        this.username = username;
        this.time = time;
        this.date = date;
        this.isUserCheckedOut = !time.equals(LocalTime.parse("23:59:50"));
        this.didUserCheckOutLate = checkedOutLate;
    }

}
