package za.co.wethinkcode.dms.checkInAndCheckOutSystemTests.modelTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.dms.checkInAndOutSystem.exceptions.ApiSuccessResponse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApiSuccessResponseTest {
    @Test
    @DisplayName("Get the response & the status code")
    void TestGetSuccessValues() {
        ApiSuccessResponse apiSuccessResponse = new ApiSuccessResponse(200, "Successful message");
        assertThat(apiSuccessResponse).isNotNull();
        assertThat(apiSuccessResponse.getSuccessCode()).isEqualTo(200);
        assertThat(apiSuccessResponse.getMessage()).isEqualTo("Successful message");
    }
}
