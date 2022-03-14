package za.co.wethinkcode.dms.absenteeismFeatureTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.dms.absenteeismFeature.dtos.CheckIn;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ChecksTests {

    @Test
    @DisplayName("Check when you check in late")
    void TestCheckInLateTime() {
        CheckIn checkIn = CheckIn.getCheckIn(LocalTime.parse("14:30"), LocalDate.parse("2022-03-13"));
        assertThat(checkIn.isLate()).isTrue();
        assertThat(checkIn).isNotNull();
        assertThat(checkIn.getDate()).isEqualTo(LocalDate.parse("2022-03-13"));
    }

    @Test
    @DisplayName("Check if you check in early or on time")
    void TestCheckInEarlyTime() {
        CheckIn checkIn = CheckIn.getCheckIn(LocalTime.parse("08:30"), LocalDate.parse("2022-03-13"));
        assertThat(checkIn).isNotNull();
        assertThat(checkIn.isLate()).isFalse();
        assertThat(checkIn.getDate()).isEqualTo(LocalDate.parse("2022-03-13"));
    }

}
