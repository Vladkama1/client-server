package aston.lab.clientserver.exception.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Ошибка валидации (если @NotNull или @NotEmpty не пройдены)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException() {
        return buildBadRequestResponse();
    }

    // Ошибка, если JSON содержит неправильный тип (например, строку "hello" вместо boolean)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleJsonParseException(HttpMessageNotReadableException ex) {
        if (!(ex.getCause() instanceof InvalidFormatException)) {
            ex.getMessage();
        }
        return buildBadRequestResponse();
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException() {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", "Bad request");
        errorResponse.put("message", "Некорректные параметры запроса");

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Ошибка для любых других исключений
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException() {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", "Internal Server Error");
        errorResponse.put("message", "Что-то пошло не так");

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Универсальный ответ для 400 Bad Request
    private ResponseEntity<Map<String, Object>> buildBadRequestResponse() {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", "Bad request");
        errorResponse.put("message", "Некорректные параметры запроса");

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
