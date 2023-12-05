package com.api.paymenttracke.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.api.paymenttracke.controllers.payment.PaymentController;
import com.api.paymenttracke.dto.payment.PaymentRequestDTO;
import com.api.paymenttracke.exception.InvalidPaymentDataException;
import com.api.paymenttracke.models.Payment;
import com.api.paymenttracke.services.payment.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PaymentService paymentService;

    @Test
    public void testGetPaymentById_Success() throws Exception {
        final Long paymentId = 1L;

        Payment mockPayment = this.createPayment(paymentId);

        when(paymentService.getPaymentById(paymentId)).thenReturn(mockPayment);

        mockMvc.perform(get("/payments/" + paymentId)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockPayment)));
    }

    @Test
    public void testGetPaymentById_NotFound() throws Exception {
        final Long paymentId = 999L;

        when(paymentService.getPaymentById(paymentId)).thenReturn(null);

        mockMvc.perform(get("/payments/" + paymentId)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void testCreatePayment_WithValidData_Created() throws Exception {
        final Payment payment = createPayment(null);

        when(paymentService.createPayment(any(PaymentRequestDTO.class))).thenReturn(payment);

        mockMvc.perform(post("/payments")
                .content(objectMapper.writeValueAsString(payment))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    public void createPayment_WithInvalidData_BadRequest() throws Exception {
        Payment payment = new Payment();
        payment.setAmount(-50.00); // Invalid amount

        when(paymentService.createPayment(any(PaymentRequestDTO.class)))
                .thenThrow(InvalidPaymentDataException.class);

        mockMvc.perform(post("/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payment)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updatePayment_WithValidData_Success() throws Exception {
        final long paymentId = 10L;
        Payment payment = new Payment();
        payment.setAmount(100.0);
        payment.setId(paymentId);
        payment.setDueDate(LocalDate.now().plusDays(10));

        when(paymentService.updatePayment(anyLong(), any(PaymentRequestDTO.class))).thenReturn(payment);

        mockMvc.perform(put("/payments/{id}", 10L)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(payment)))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePayment_WithValidData_BadRequest() throws Exception {
        PaymentRequestDTO payment = new PaymentRequestDTO();
        payment.setAmount(-50.0);
        payment.setId(10L);
        payment.setDueDate(LocalDate.now().plusDays(10));

        when(paymentService.updatePayment(10L, payment)).thenThrow(InvalidPaymentDataException.class);

        mockMvc.perform(put("/payments/{id}", 10L)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(payment)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deletePayment_WithValidId_ShouldReturn204NoContent() throws Exception {
        final Long paymentId = -1L;

        mockMvc.perform(delete("/payments/{id}", paymentId))
                .andExpect(status().isNoContent());
    }

    private Payment createPayment(final Long id) {
        Payment payment = new Payment();
        payment.setId(id);
        payment.setAmount(100.0);
        payment.setDueDate(LocalDate.of(2023, 10, 5));
        return payment;
    }

}
