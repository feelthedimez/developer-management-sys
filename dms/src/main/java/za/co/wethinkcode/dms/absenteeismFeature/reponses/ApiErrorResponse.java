package za.co.wethinkcode.dms.absenteeismFeature.reponses;

public class ApiErrorResponse {
    private final String errorMessage;
    private final int errorCode;

    public ApiErrorResponse(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
