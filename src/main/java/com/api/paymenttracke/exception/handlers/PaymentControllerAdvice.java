package com.api.paymenttracke.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.paymenttracke.exception.InvalidPaymentDataException;

@ControllerAdvice
public class PaymentControllerAdvice {
    
    @ExceptionHandler(InvalidPaymentDataException.class)
    public ResponseEntity<String> handleInvalidPaymentDataException(InvalidPaymentDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
