package za.co.wethinkcode.dms.checkInAndOutSystem.exceptions;

public class DateErrorException extends RuntimeException {

    public DateErrorException() {
        super();
    }

    public DateErrorException(String message) {
        super(message);
    }
}
