package aston.lab.clientserver.controller.exception;

public class FeignException extends RuntimeException {

    public FeignException(String message) {
        super(message);
    }

}
