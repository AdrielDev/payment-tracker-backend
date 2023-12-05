package com.api.paymenttracke.controllers.recurringpayment;

import java.util.List;

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

import com.api.paymenttracke.dto.recurringpayment.RecurringPaymentRequestDTO;
import com.api.paymenttracke.dto.recurringpayment.RecurringPaymentResponseDTO;
import com.api.paymenttracke.services.recurringpayment.RecurringPaymentService;

@RestController
@RequestMapping("/recurring-payments")
public class RecurringPaymentController implements RecurringPaymentControllerInterface {

    private final RecurringPaymentService recurringPaymentService;

    public RecurringPaymentController(final RecurringPaymentService recurringPaymentService) {
        this.recurringPaymentService = recurringPaymentService;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RecurringPaymentResponseDTO> getRecurringPaymentById(@PathVariable final Long id) {
        return ResponseEntity.ok(recurringPaymentService.getRecurringPaymentById(id));
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<RecurringPaymentResponseDTO>> getAllRecurringPayment() {
        final List<RecurringPaymentResponseDTO> response = recurringPaymentService.getAllRecurringPayments();
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<RecurringPaymentResponseDTO> createRecurringPayment(
            @RequestBody final RecurringPaymentRequestDTO requestDTO) {
        final RecurringPaymentResponseDTO createdRecurringPayment = recurringPaymentService.createRecurringPayment(requestDTO);
        return ResponseEntity.ok(createdRecurringPayment);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<RecurringPaymentResponseDTO> updateRecurringPayment(@PathVariable final Long id,
            @RequestBody final RecurringPaymentRequestDTO updatedRecurringPayment) {
        final RecurringPaymentResponseDTO response = recurringPaymentService.updateRecurringPayment(id, updatedRecurringPayment);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecurringPayment(@PathVariable final Long id) {
        recurringPaymentService.deleteRecurringPayment(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<RecurringPaymentResponseDTO> partialUpdateRecurringPayment(final Long id,
            @RequestBody final RecurringPaymentRequestDTO updatedRecurringPayment) {
        final RecurringPaymentResponseDTO response = recurringPaymentService.partialUpdateRecurringPayment(id, updatedRecurringPayment);
        return ResponseEntity.ok(response);
    }
}