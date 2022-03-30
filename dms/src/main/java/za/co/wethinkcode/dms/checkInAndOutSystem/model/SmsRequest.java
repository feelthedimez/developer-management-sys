package za.co.wethinkcode.dms.checkInAndOutSystem.model;

public class SmsRequest {
    private final String phoneNumber;
    private final String message;


    public SmsRequest(String phoneNuber, String message) {
        this.phoneNumber = phoneNuber;
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SmsRequest{" +
                "phoneNuber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
