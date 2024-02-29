package com.unipi.smartalert.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

// Global error handling class
@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = LanguageNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleLanguageNotSupportedException(LanguageNotSupportedException ex) {

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ActionNotAllowedException.class)
    protected ResponseEntity<ErrorResponse> handleActionNotAllowedException(ActionNotAllowedException ex) {

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

}
