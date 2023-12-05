package com.api.paymenttracke.exception.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.paymenttracke.exception.InvalidPaymentDataException;
import com.api.paymenttracke.exception.model.ResponseMessageError;

@ControllerAdvice
public class PaymentControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidPaymentDataException.class);
    
    @ExceptionHandler(InvalidPaymentDataException.class)
    public ResponseEntity<ResponseMessageError> handleInvalidPaymentDataException(InvalidPaymentDataException ex) {
        LOGGER.error("Invalid Payment data: {}", ex.getMessage());

        final ResponseMessageError error = new ResponseMessageError(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
