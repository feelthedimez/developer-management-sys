package za.co.wethinkcode.dms.checkInAndOutSystem.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime timestamp;

    private int code;
    private String status;
    private String message;
//    private String stackStrace;
//    private Object data;


    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(HttpStatus status, String message) {
        this();
        this.code = status.value();
        this.message = message;
        this.status = status.name();
    }

//    public ErrorResponse(HttpStatus status, String message, String stackStrace) {
//        this(status, message);
//        this.stackStrace = stackStrace;
//    }
//
//    public ErrorResponse(HttpStatus status, String message, String stackStrace, Object data) {
//        this(status, message, stackStrace);
//        this.data = data;
//    }
}
