package com.api.paymenttracke.services.payment;

import com.api.paymenttracke.dto.payment.PaymentRequestDTO;
import com.api.paymenttracke.models.Payment;

public interface PaymentServiceInterface {
    
    public Payment getPaymentById(final Long id);

    public Payment createPayment(final PaymentRequestDTO payment);

    public Payment updatePayment(final Long id, final PaymentRequestDTO updatedPayment);

    public void deletePayment(final Long id);
}
