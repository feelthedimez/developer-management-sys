package za.co.wethinkcode.dms.checkInAndOutSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckInEntity;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter @ToString @NoArgsConstructor
public class CheckIn implements Checks {
    private String username;
    private LocalDate date;
    private LocalTime time;
    private boolean isUserLate;
    private boolean isUserCheckedIn;

    public static CheckIn createCheckIn(String username, LocalTime time, LocalDate date){
        return new CheckIn(username, time, date, time.isAfter(LocalTime.parse("10:30")), true);
    }

    public static CheckIn createCheckIn(CheckInEntity checkInEntity) {
        return new CheckIn(checkInEntity);
    }

    private CheckIn( String username, LocalTime time, LocalDate date, boolean isLate, boolean isCheckedIn) {
        this.username = username;
        this.time = time;
        this.date = date;
        this.isUserLate = isLate;
        this.isUserCheckedIn = isCheckedIn;
    }

    private CheckIn(CheckInEntity checkIn) {
        this.username = checkIn.getUsername();
        this.date = checkIn.getDate();
        this.time = checkIn.getTime();
        this.isUserCheckedIn = true;
        this.isUserLate = time.isAfter(LocalTime.parse("10:30"));
    }

}
