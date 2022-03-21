package za.co.wethinkcode.dms.checkInAndOutSystem.exceptions;


public class ApiSuccessResponse {
    private final int successCode;
    private final String successMessage;

    public ApiSuccessResponse(int successCode, String successMessage) {
        this.successCode = successCode;
        this.successMessage = successMessage;
    }

    public int getSuccessCode() {
        return successCode;
    }

    public String getMessage() {
        return successMessage;
    }
}
