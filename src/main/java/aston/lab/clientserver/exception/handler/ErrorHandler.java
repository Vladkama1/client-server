package aston.lab.clientserver.exception.handler;

import aston.lab.clientserver.exception.ClientOkvedNotFoundException;
import aston.lab.clientserver.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ClientOkvedNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ClientOkvedNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("404", e.getMessage()));
    }

}