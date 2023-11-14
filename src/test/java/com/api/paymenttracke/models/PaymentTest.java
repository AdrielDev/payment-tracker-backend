package com.api.paymenttracke.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.api.paymenttracke.enums.PaymentStatus;

public class PaymentTest {
    
    @Test
    public void testPaymentCreation() {
        Payment payment = new Payment();

        payment.setId(1L);
        payment.setAmount(100.0);
        payment.setDueDate(LocalDate.of(2023, 10, 31));
        payment.setStatus(PaymentStatus.PENDING);

        assertEquals(1L, payment.getId());
        assertEquals(100.0, payment.getAmount(), 0.01);
        assertEquals(LocalDate.of(2023, 10, 31), payment.getDueDate());
        assertEquals(PaymentStatus.PENDING, payment.getStatus());

        String paymeString = payment.toString();
        assertNotNull(paymeString);
        assertTrue(paymeString.contains("Payment"));
    }
}
