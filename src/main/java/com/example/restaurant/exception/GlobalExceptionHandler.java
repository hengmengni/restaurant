package com.example.restaurant.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.restaurant.model.ResponseMessage;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ FileNotFoundException.class })
    public ResponseEntity<ResponseMessage> handleFileNotFoundException(FileNotFoundException ex) {
        final var response = new ResponseMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({ UserNotFoundException.class })
    public ResponseEntity<ResponseMessage> handleUserNotFoundException(UserNotFoundException ex) {
        final var response = new ResponseMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
