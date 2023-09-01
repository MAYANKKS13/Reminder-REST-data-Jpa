package in.stackroute.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        var fieldErrors = ex.getFieldErrors();
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", System.currentTimeMillis());
        response.put("status", status.value());
        for (var fieldError : fieldErrors) {
           response.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(response, headers, status);
    }

    @ExceptionHandler(ReminderNotFoundException.class)
    public ResponseEntity<Object> handleReminderNotFoundException(ReminderNotFoundException ex,
                                                                  WebRequest request, HttpStatusCode status) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", System.currentTimeMillis());
        response.put("status", status);
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(status.value()));
    }
}
