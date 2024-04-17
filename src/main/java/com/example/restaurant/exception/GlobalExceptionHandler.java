package com.example.restaurant.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.restaurant.model.ResponseMessage;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<ResponseMessage> handleUserNotFoundException(NotFoundException ex) {
        final var response = new ResponseMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({ MessageException.class })
    public ResponseEntity<ResponseMessage> handleMessageException(MessageException ex) {
        final var response = new ResponseMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
