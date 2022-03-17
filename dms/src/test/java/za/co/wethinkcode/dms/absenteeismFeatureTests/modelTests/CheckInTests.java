package za.co.wethinkcode.dms.absenteeismFeatureTests.modelTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.dms.absenteeismFeature.model.CheckIn;
import za.co.wethinkcode.dms.absenteeismFeature.model.CheckOut;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckInTests {

    @Test
    @DisplayName("A normal checking in")
    void TestNormalCheckIn() {
        CheckIn checkIn = CheckIn.createCheckIn("tetema", LocalTime.parse("08:30"), LocalDate.now());
        assertThat(checkIn).isNotNull();
        assertThat(checkIn.isCheckedIn()).isTrue();
        assertThat(checkIn.isLate()).isFalse();
        assertThat(checkIn).isNotNull();
    }

    @Test
    @DisplayName("Check when you check in late")
    void TestCheckInLateTime() {
        CheckIn checkIn = CheckIn.createCheckIn("tetema", LocalTime.parse("14:30"), LocalDate.parse("2022-03-13"));
        assertThat(checkIn.isLate()).isTrue();
        assertThat(checkIn).isNotNull();
        assertThat(checkIn.getDate()).isEqualTo(LocalDate.parse("2022-03-13"));
    }

    @Test
    @DisplayName("Check if you check in early or on time")
    void TestCheckInEarlyTime() {
        CheckIn checkIn = CheckIn.createCheckIn("tetema", LocalTime.parse("08:30"), LocalDate.parse("2022-03-13"));
        assertThat(checkIn).isNotNull();
        assertThat(checkIn.isLate()).isFalse();
        assertThat(checkIn.getDate()).isEqualTo(LocalDate.parse("2022-03-13"));
    }

    @Test
    @DisplayName("Checking what happens when you don't checkIn, and try to access the values")
    void TestCheckIfCheckedIn() {
        CheckIn checkIn = new CheckIn();
        assertThat(checkIn).isNotNull();
        assertThat(checkIn.getDate()).isNull();
        assertThat(checkIn.isCheckedIn()).isFalse();
        assertThat(checkIn.getUsername()).isNull();
        assertThat(checkIn.getTime()).isNull();
    }


}
