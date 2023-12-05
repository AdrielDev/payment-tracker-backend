package com.api.paymenttracke.exception.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.paymenttracke.exception.ResourceNotFoundException;
import com.api.paymenttracke.exception.model.ResponseMessageError;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseMessageError> handleResourceNotFoundException(ResourceNotFoundException ex) {
        LOGGER.error("Resource not found: {}", ex.getMessage());
        
        final ResponseMessageError responseMessageError = new ResponseMessageError(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessageError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessageError> handleValidationException(MethodArgumentNotValidException ex) {
        LOGGER.error("Validation failed: {}", ex.getMessage());

        final ResponseMessageError responseMessageError = new ResponseMessageError(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            System.currentTimeMillis());
        return ResponseEntity.badRequest().body(responseMessageError);
    }
}
