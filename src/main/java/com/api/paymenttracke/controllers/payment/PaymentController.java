package com.api.paymenttracke.controllers.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.paymenttracke.dto.payment.PaymentRequestDTO;
import com.api.paymenttracke.enums.PaymentStatus;
import com.api.paymenttracke.models.Payment;
import com.api.paymenttracke.services.payment.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController implements PaymentControllerInterface {

    private final PaymentService paymentService;

    public PaymentController(final PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        final Payment payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@Valid @RequestBody final PaymentRequestDTO payment) {
        final Payment createdPayment = paymentService.createPayment(payment);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable final Long id,
            @Valid @RequestBody final PaymentRequestDTO updatedPayment) {
        final Payment updated = paymentService.updatePayment(id, updatedPayment);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<Void> updateStatusPayment(@PathVariable final Long id,
            @PathVariable final PaymentStatus status) {
        paymentService.updateStatusPayment(id, status);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable final Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
