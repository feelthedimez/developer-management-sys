package za.co.wethinkcode.dms.absenteeismFeature.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class CheckOut implements Checks {
    private final LocalDate date;
    private final LocalTime time;

    public CheckOut(LocalTime time, LocalDate date) {
        this.time = time;
        this.date = date;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public LocalTime getTime() {
        return time;
    }
}
