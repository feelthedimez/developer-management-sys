package za.co.wethinkcode.dms.checkInAndOutSystem.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomErrorResponse extends RuntimeException {
    private HttpStatus httpStatus;

    public CustomErrorResponse() {
        super();
    }

    public CustomErrorResponse(String message) {
        super(message);
    }

    public CustomErrorResponse(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
