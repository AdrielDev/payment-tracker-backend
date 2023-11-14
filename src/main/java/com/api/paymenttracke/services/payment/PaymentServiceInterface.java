package com.api.paymenttracke.services.payment;

import com.api.paymenttracke.models.Payment;

public interface PaymentServiceInterface {
    
    public Payment getPaymentById(final Long id);

    public Payment createPayment(final Payment payment);

    public Payment updatePayment(final Long id, final Payment updatedPayment);

    public void deletePayment(final Long id);
}
