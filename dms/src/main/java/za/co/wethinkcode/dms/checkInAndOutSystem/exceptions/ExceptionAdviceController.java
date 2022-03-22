package za.co.wethinkcode.dms.checkInAndOutSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdviceController {

    @ExceptionHandler(CustomErrorWithDataException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerExceptions(Exception e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ErrorResponse(status, e.getMessage()), status);
    }

    @ExceptionHandler(CustomErrorResponseException.class)
    public ResponseEntity<ErrorResponse> handleCustomErrorExceptions(Exception e) {
        CustomErrorResponseException customErrorException = (CustomErrorResponseException) e;
        HttpStatus httpStatus = customErrorException.getHttpStatus();

        return new ResponseEntity<>(new ErrorResponse(httpStatus, e.getMessage()), httpStatus);
    }
}
