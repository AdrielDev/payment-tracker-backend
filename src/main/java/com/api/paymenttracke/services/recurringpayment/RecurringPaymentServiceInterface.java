package com.api.paymenttracke.services.recurringpayment;

import java.util.List;

import com.api.paymenttracke.models.RecurringPayment;

public interface RecurringPaymentServiceInterface {
    RecurringPayment getRecurringPaymentById(final Long id);

    List<RecurringPayment> getAllRecurringPayments();

    RecurringPayment createRecurringPayment(final RecurringPayment recurringPayment);

    RecurringPayment updateRecurringPayment(final Long id, final RecurringPayment updatedRecurringPayment);

    RecurringPayment partialUpdateRecurringPayment(final Long id, final RecurringPayment partialRecurringPayment);

    boolean deleteRecurringPayment(final Long id);
}
