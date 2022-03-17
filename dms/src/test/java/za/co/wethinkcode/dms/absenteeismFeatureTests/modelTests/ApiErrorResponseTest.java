package za.co.wethinkcode.dms.absenteeismFeatureTests.modelTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.dms.absenteeismFeature.exceptionResponses.ApiErrorResponse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ApiErrorResponseTest {

    @Test
    @DisplayName("Get the response & the status code")
    void TestGetErrorValues() {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(500, "Error message");
        assertThat(apiErrorResponse).isNotNull();
        assertThat(apiErrorResponse.getErrorCode()).isEqualTo(500);
        assertThat(apiErrorResponse.getErrorMessage()).isEqualTo("Error message");
    }
}
