package com.api.paymenttracke.controllers.recurringpayment;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.paymenttracke.models.RecurringPayment;
import com.api.paymenttracke.services.recurringpayment.RecurringPaymentService;

@RestController
@RequestMapping("/recurring-payments")
public class RecurringPaymentController implements RecurringPaymentControllerInterface {

    private final RecurringPaymentService RecurringPaymentService;

    public RecurringPaymentController(final RecurringPaymentService RecurringPaymentService) {
        this.RecurringPaymentService = RecurringPaymentService;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RecurringPayment> getRecurringPaymentById(@PathVariable final Long id) {
        RecurringPayment RecurringPayment = RecurringPaymentService.getRecurringPaymentById(id);
        if (RecurringPayment != null) {
            return ResponseEntity.ok(RecurringPayment);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<RecurringPayment>> getAllRecurringPayment() {
        final List<RecurringPayment> RecurringPaymentList = RecurringPaymentService.getAllRecurringPayments();
        return ResponseEntity.ok(RecurringPaymentList);
    }

    @Override
    @PostMapping
    public ResponseEntity<RecurringPayment> createRecurringPayment(@RequestBody final RecurringPayment RecurringPayment) {
        final RecurringPayment createdRecurringPayment = RecurringPaymentService.createRecurringPayment(RecurringPayment);
        return ResponseEntity.ok(createdRecurringPayment);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<RecurringPayment> updateRecurringPayment(@PathVariable final Long id, @RequestBody final RecurringPayment updatedRecurringPayment) {
        final RecurringPayment existingRecurringPayment = RecurringPaymentService.getRecurringPaymentById(id);
        if (existingRecurringPayment == null) {
            return ResponseEntity.notFound().build();
        }
        final RecurringPayment updated = RecurringPaymentService.updateRecurringPayment(id, updatedRecurringPayment);
        return ResponseEntity.ok(updated);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecurringPayment(@PathVariable final Long id) {
        if (RecurringPaymentService.deleteRecurringPayment(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<RecurringPayment> partialUpdateRecurringPayment(final Long id, final RecurringPayment recurringPayment) {
        final RecurringPayment existingRecurringPayment = RecurringPaymentService.getRecurringPaymentById(id);
        if (existingRecurringPayment == null) {
            return ResponseEntity.notFound().build();
        }
        final RecurringPayment updated = RecurringPaymentService.partialUpdateRecurringPayment(id, existingRecurringPayment);
        return ResponseEntity.ok(updated);
    }
}