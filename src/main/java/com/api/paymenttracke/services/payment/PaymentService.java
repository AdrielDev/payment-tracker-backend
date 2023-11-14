package com.api.paymenttracke.services.payment;

import org.springframework.stereotype.Service;

import com.api.paymenttracke.enums.PaymentStatus;
import com.api.paymenttracke.models.Payment;
import com.api.paymenttracke.repositories.PaymentRepository;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(final PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment getPaymentById(final Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment createPayment(final Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(final Long id, final Payment updatedPayment) {
        Payment existingPayment = paymentRepository.findById(id).orElse(null);
        if (existingPayment == null) {
            return null;
        }

        existingPayment.setAmount(updatedPayment.getAmount());
        existingPayment.setDueDate(updatedPayment.getDueDate());
        existingPayment.setRecurringPayment(updatedPayment.getRecurringPayment());
        existingPayment.setStatus(updatedPayment.getStatus());

        return paymentRepository.save(existingPayment);
    }

    public Boolean updateStatusPayment(final Long id, final PaymentStatus status) {
        Payment existingPayment = paymentRepository.findById(id).orElse(null);
        if (existingPayment == null) {
            return null;
        }

        existingPayment.setStatus(status);
        paymentRepository.save(existingPayment);
        return true;
    }

    public boolean deletePayment(final Long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
