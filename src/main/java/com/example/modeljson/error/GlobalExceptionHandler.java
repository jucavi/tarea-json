package com.example.modeljson.error;

import com.example.modeljson.error.notfound.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<AppError> handleMethodArgumentException(MethodArgumentNotValidException ex) {

        log.error(ex.getMessage());

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String message = error.getDefaultMessage();

            try {
                String field = ((FieldError) error).getField();
                errors.put(field, message);
            } catch (Exception e) {
                String field = error.getObjectName();
                errors.put(field, message);
            }
        });

        return ResponseEntity.unprocessableEntity().body(new AppError(
                HttpStatus.UNPROCESSABLE_ENTITY,
                errors
        ));
    }


    @ExceptionHandler({
            ConfigNotFoundException.class,
            AttributeNotFoundException.class,
            AttributeTypeNotFoundException.class,
            AttributeTypeValueNotFoundException.class,
            EnumeratedValueNotFound.class
    })
    public ResponseEntity<AppError> handleNotFoundException(RuntimeException ex) {

        String message = ex.getMessage();
        log.error(message);

        Map<String, String> errors = new HashMap<>();
        errors.put("Missing", message);

        return ResponseEntity.unprocessableEntity().body(new AppError(
                HttpStatus.NOT_FOUND,
                errors
        ));
    }


    @ExceptionHandler({
            RuntimeException.class
    })
    public ResponseEntity<AppError> handleRuntimeException(RuntimeException ex) {

        String message = ex.getMessage();
        log.error(message);

        Map<String, String> errors = new HashMap<>();
        errors.put("System", message);

        return ResponseEntity.unprocessableEntity().body(new AppError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                errors
        ));
    }

    @ExceptionHandler({
            NotImplementedException.class
    })
    public ResponseEntity<AppError> handleNotImplementedException(NotImplementedException ex) {

        String message = ex.getMessage();
        log.error(message);

        Map<String, String> errors = new HashMap<>();
        errors.put("System", message);

        return ResponseEntity.unprocessableEntity().body(new AppError(
                HttpStatus.ACCEPTED,
                errors
        ));
    }
}
