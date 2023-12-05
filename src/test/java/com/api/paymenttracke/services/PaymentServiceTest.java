package com.api.paymenttracke.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.api.paymenttracke.dto.payment.PaymentRequestDTO;
import com.api.paymenttracke.models.Payment;
import com.api.paymenttracke.repositories.PaymentRepository;
import com.api.paymenttracke.services.payment.PaymentService;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Test
    public void getPaymentById_WithValidId_Success() {
        Long validPaymentId = 1L;

        Payment expectedPayment = new Payment();
        expectedPayment.setId(validPaymentId);

        when(paymentRepository.findById(validPaymentId)).thenReturn(Optional.of(expectedPayment));

        Payment result = paymentService.getPaymentById(validPaymentId);

        assertNotNull(result);
        assertEquals(validPaymentId, result.getId());
    }

    @Test
    public void getPaymentById_WithInvalidId_Null() {
        Long paymentId = 1L;

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.empty());

        Payment result = paymentService.getPaymentById(paymentId);

        assertNull(result);
    }

    @Test
    public void createPayment_WithValidPayment_Success() {
        Payment inputPayment = new Payment();
        inputPayment.setAmount(100.0);
        PaymentRequestDTO inputPaymentDto = new PaymentRequestDTO();
        inputPaymentDto.setAmount(100.0);

        Payment savedPayment = new Payment();
        savedPayment.setId(1L);
        savedPayment.setAmount(100.0);

        when(paymentRepository.save(inputPayment)).thenReturn(savedPayment);

        Payment result = paymentService.createPayment(inputPaymentDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(100.0, result.getAmount(), 0.01);
    }

    @Test
    public void createPayment_WithInvalidPayment_Null() {
        Payment inputPayment = new Payment();
        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();

        when(paymentRepository.save(inputPayment)).thenReturn(null);

        Payment result = paymentService.createPayment(paymentRequestDTO);

        assertNull(result);
    }

    @Test
    public void updatePayment_WithExistingPayment_Success() {
        Long paymentId = 1L;

        Payment existingPayment = new Payment();
        existingPayment.setId(paymentId);
        existingPayment.setAmount(100.0);
        existingPayment.setDueDate(LocalDate.of(2023, 10, 10));

        Payment updatedPayment = new Payment();
        updatedPayment.setId(paymentId);
        updatedPayment.setAmount(150.0);
        updatedPayment.setDueDate(LocalDate.of(2023, 11, 11));
        PaymentRequestDTO updatedPaymentRequestDTO = new PaymentRequestDTO();
        updatedPayment.setId(paymentId);
        updatedPayment.setAmount(150.0);
        updatedPayment.setDueDate(LocalDate.of(2023, 11, 11));

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(existingPayment));
        when(paymentRepository.save(existingPayment)).thenReturn(updatedPayment);

        Payment result = paymentService.updatePayment(paymentId, updatedPaymentRequestDTO);

        assertEquals(paymentId, result.getId());
        assertEquals(150.0, result.getAmount(), 0.01);
        assertEquals(LocalDate.of(2023, 11, 11), result.getDueDate());
    }

    @Test
    public void updatePayment_WithNonExistingPayment_Null() {
        Long paymentId = 1L;
        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.empty());

        Payment result = paymentService.updatePayment(paymentId, paymentRequestDTO);

        assertNull(result);
    }

    @Test
    public void deletePayment_WithExistingPayment_True() {
        Long paymentId = 1L;

        when(paymentRepository.existsById(paymentId)).thenReturn(true);

        paymentService.deletePayment(paymentId);
    }

    @Test
    public void deletePayment_WithNonExistingPayment_False() {
        Long paymentId = 1L;

        when(paymentRepository.existsById(paymentId)).thenReturn(false);

        paymentService.deletePayment(paymentId);
    }
}
