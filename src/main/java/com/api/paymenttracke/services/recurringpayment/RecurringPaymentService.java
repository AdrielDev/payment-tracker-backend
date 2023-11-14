package com.api.paymenttracke.services.recurringpayment;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.paymenttracke.models.RecurringPayment;
import com.api.paymenttracke.repositories.RecurringPaymentRepository;

@Service
public class RecurringPaymentService implements RecurringPaymentServiceInterface {

    private final RecurringPaymentRepository recurringPaymentRepository;

    public RecurringPaymentService(RecurringPaymentRepository recurringPaymentRepository) {
        this.recurringPaymentRepository = recurringPaymentRepository;
    }

    public RecurringPayment getRecurringPaymentById(final Long id) {
        return recurringPaymentRepository.findById(id).orElse(null);
    }

    public List<RecurringPayment> getAllRecurringPayments() {
        return recurringPaymentRepository.findAll();
    }

    public RecurringPayment createRecurringPayment(final RecurringPayment recurringPayment) {
        return recurringPaymentRepository.save(recurringPayment);
    }

    public RecurringPayment updateRecurringPayment(final Long id, final RecurringPayment updatedRecurringPayment) {
        RecurringPayment existingRecurringPayment = recurringPaymentRepository.findById(id).orElse(null);
        if (existingRecurringPayment == null) {
            return null;
        }

        existingRecurringPayment.setDescription(updatedRecurringPayment.getDescription());
        existingRecurringPayment.setInstallmentAmount(updatedRecurringPayment.getInstallmentAmount());
        existingRecurringPayment.setInstallmentValue(updatedRecurringPayment.getInstallmentValue());
        existingRecurringPayment.setTotalInstallments(updatedRecurringPayment.getTotalInstallments());
        existingRecurringPayment.setStatus(updatedRecurringPayment.getStatus());

        return recurringPaymentRepository.save(existingRecurringPayment);
    }

    public RecurringPayment partialUpdateRecurringPayment(final Long id, final RecurringPayment partialRecurringPayment) {
        RecurringPayment existingRecurringPayment = recurringPaymentRepository.findById(id).orElse(null);
        if (existingRecurringPayment == null) {
            return null;
        }

        if (partialRecurringPayment.getDescription() != null) {
            existingRecurringPayment.setDescription(partialRecurringPayment.getDescription());
        }
        if (partialRecurringPayment.getInstallmentAmount() != null) {
            existingRecurringPayment.setInstallmentAmount(partialRecurringPayment.getInstallmentAmount());
        }
        if (partialRecurringPayment.getInstallmentValue() != null) {
            existingRecurringPayment.setInstallmentValue(partialRecurringPayment.getInstallmentValue());
        }
        if (partialRecurringPayment.getTotalInstallments() != null) {
            existingRecurringPayment.setTotalInstallments(partialRecurringPayment.getTotalInstallments());
        }
        if (partialRecurringPayment.getStatus() != null) {
            existingRecurringPayment.setStatus(partialRecurringPayment.getStatus());
        }

        return recurringPaymentRepository.save(existingRecurringPayment);
    }

    public boolean deleteRecurringPayment(final Long id) {
        if (recurringPaymentRepository.existsById(id)) {
            recurringPaymentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
