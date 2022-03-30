package za.co.wethinkcode.dms.checkInAndCheckOutSystemTests.modelTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.dms.checkInAndOutSystem.model.CheckIn;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckInTests {

    @Test
    @DisplayName("A normal checking in")
    void testNormalCheckIn() {
        CheckIn checkIn = CheckIn.createCheckIn("tetema", "",LocalTime.parse("08:30"), LocalDate.now());
        assertThat(checkIn).isNotNull();
        assertThat(checkIn.isUserCheckedIn()).isTrue();
        assertThat(checkIn.isUserLate()).isFalse();
        assertThat(checkIn).isNotNull();
    }

    @Test
    @DisplayName("Check when you check in late")
    void testCheckInLateTime() {
        CheckIn checkIn = CheckIn.createCheckIn("tetema", "",LocalTime.parse("14:30"), LocalDate.parse("2022-03-13"));
        assertThat(checkIn.isUserLate()).isTrue();
        assertThat(checkIn).isNotNull();
        assertThat(checkIn.getDate()).isEqualTo(LocalDate.parse("2022-03-13"));
    }

    @Test
    @DisplayName("Check if you check in early or on time")
    void testCheckInEarlyTime() {
        CheckIn checkIn = CheckIn.createCheckIn("tetema", "",LocalTime.parse("08:30"), LocalDate.parse("2022-03-13"));
        assertThat(checkIn).isNotNull();
        assertThat(checkIn.isUserLate()).isFalse();
        assertThat(checkIn.getDate()).isEqualTo(LocalDate.parse("2022-03-13"));
    }

    @Test
    @DisplayName("Checking what happens when you don't checkIn, and try to access the values")
    void testCheckIfCheckedIn() {
        CheckIn checkIn = new CheckIn();
        assertThat(checkIn).isNotNull();
        assertThat(checkIn.getDate()).isNull();
        assertThat(checkIn.isUserCheckedIn()).isFalse();
        assertThat(checkIn.getUsername()).isNull();
        assertThat(checkIn.getTime()).isNull();
    }

}
