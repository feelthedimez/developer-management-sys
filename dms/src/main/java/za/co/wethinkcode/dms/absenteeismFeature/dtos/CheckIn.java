package za.co.wethinkcode.dms.absenteeismFeature.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CheckIn implements Checks {
    private final LocalDate date;
    private final LocalTime time;
    private final boolean isLate;

    public static CheckIn getCheckIn(LocalTime time, LocalDate date){
        return new CheckIn(time, date, time.isAfter(LocalTime.parse("10:30")));
    }

    private CheckIn(LocalTime time, LocalDate date, boolean isLate) {
        this.time = time;
        this.date = date;
        this.isLate = isLate;
    }

    @Override
    public LocalTime getTime() {
        return time;
    }

    public boolean isLate() {
        return isLate;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "CheckIn{" +
                "date=" + date +
                ", time=" + time +
                ", isLate=" + isLate +
                '}';
    }

}
