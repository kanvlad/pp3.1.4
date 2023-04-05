package tech.itmentors.crud.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.itmentors.crud.exception.NotAcceptableException;
import tech.itmentors.crud.exception.NotFoundException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundHandler(NotFoundException e) {
        String bodyMessage = String.format("{\"message\": \"%s\"}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyMessage);
    }

    @ExceptionHandler(NotAcceptableException.class)
    public ResponseEntity<String> notAcceptableHandler(NotAcceptableException e) {
        String bodyMessage = String.format("{\"message\": \"%s\"}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyMessage);
    }
}
