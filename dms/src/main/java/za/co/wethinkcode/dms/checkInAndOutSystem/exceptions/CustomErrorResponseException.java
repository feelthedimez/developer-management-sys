package za.co.wethinkcode.dms.checkInAndOutSystem.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomErrorResponseException extends RuntimeException {
    private HttpStatus httpStatus;

    public CustomErrorResponseException() {
        super();
    }

    public CustomErrorResponseException(String message) {
        super(message);
    }

    public CustomErrorResponseException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
