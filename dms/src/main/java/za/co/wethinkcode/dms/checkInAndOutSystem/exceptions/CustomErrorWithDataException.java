package za.co.wethinkcode.dms.checkInAndOutSystem.exceptions;

public class CustomErrorWithDataException extends RuntimeException {

    public CustomErrorWithDataException() {
        super();
    }

    public CustomErrorWithDataException(String message) {
        super(message);
    }
}
