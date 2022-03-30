package za.co.wethinkcode.dms.checkInAndOutSystem.model;

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
    private String phoneNumber;
    private LocalDate date;
    private LocalTime time;
    private boolean isUserLate;
    private boolean isUserCheckedIn;

    // TODO: Create an entity to store student profiles; the profile has all the details

    public static CheckIn createCheckIn(String username, String phoneNumber, LocalTime time, LocalDate date){
        return new CheckIn(username, phoneNumber, time, date, time.isAfter(LocalTime.parse("10:30")), true);
    }

    public static CheckIn createCheckIn(CheckInEntity checkInEntity) {
        return new CheckIn(checkInEntity);
    }

    private CheckIn( String username, String phoneNumber,LocalTime time, LocalDate date, boolean isLate, boolean isCheckedIn) {
        this.username = username;
        this.time = time;
        this.date = date;
        this.isUserLate = isLate;
        this.isUserCheckedIn = isCheckedIn;
        this.phoneNumber = phoneNumber;
    }

    private CheckIn(CheckInEntity checkIn) {
        this.username = checkIn.getUsername();
        this.date = checkIn.getDate();
        this.time = checkIn.getTime();
        this.isUserCheckedIn = true;
        this.isUserLate = time.isAfter(LocalTime.parse("10:30"));
        this.phoneNumber = checkIn.getPhoneNumber();
    }

}
