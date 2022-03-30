package za.co.wethinkcode.dms.checkInAndCheckOutSystemTests.modelTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.dms.checkInAndOutSystem.model.CheckOut;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckOutTests {

    @Test
    @DisplayName("A normal checkout")
    void TestNormalCheckOut() {
        CheckOut checkOut = CheckOut.createCheckOut("tetema", "",LocalTime.parse("16:30"), LocalDate.now());
        assertThat(checkOut).isNotNull();
        assertThat(checkOut.isUserCheckedOut()).isTrue();
        assertThat(checkOut.isDidUserCheckOutLate()).isFalse();
    }

    @Test
    @DisplayName("Check when you checkout late")
    void TestCheckOutLate() {
        CheckOut checkOut = CheckOut.createCheckOut("tetema", "",LocalTime.parse("18:20"), LocalDate.now());
        assertThat(checkOut).isNotNull();
        assertThat(checkOut.isUserCheckedOut()).isTrue();
        assertThat(checkOut.getDate()).isEqualTo(LocalDate.now());
    }

    @Test
    @DisplayName("Checking what happens when you don't checkIn, and try to access the values")
    void TestCheckIfCheckedOut() {
        CheckOut checkOut = new CheckOut();
        assertThat(checkOut).isNotNull();
        assertThat(checkOut.getDate()).isNull();
        assertThat(checkOut.isUserCheckedOut()).isFalse();
        assertThat(checkOut.getUsername()).isNull();
        assertThat(checkOut.getTime()).isNull();
    }

}
