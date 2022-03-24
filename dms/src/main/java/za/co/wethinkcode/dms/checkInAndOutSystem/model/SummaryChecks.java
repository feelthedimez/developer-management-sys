package za.co.wethinkcode.dms.checkInAndOutSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.temporal.ChronoUnit;

@Getter @ToString @AllArgsConstructor @NoArgsConstructor
public class SummaryChecks {
    private double hoursSpent;
    private int availableDays;
    private int unavailableDays;
    private int daysLate;
}

//    double diffHours = ChronoUnit.MINUTES.between(checkInData.get().getTime(), checkOutData.get().getTime()) / 60.0;