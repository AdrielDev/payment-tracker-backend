package com.api.paymenttracke.services.recurringpayment;

import java.util.List;

import com.api.paymenttracke.dto.recurringpayment.RecurringPaymentRequestDTO;
import com.api.paymenttracke.dto.recurringpayment.RecurringPaymentResponseDTO;

public interface RecurringPaymentServiceInterface {
    RecurringPaymentResponseDTO getRecurringPaymentById(final Long id);

    List<RecurringPaymentResponseDTO> getAllRecurringPayments();

    RecurringPaymentResponseDTO createRecurringPayment(final RecurringPaymentRequestDTO recurringPayment);

    RecurringPaymentResponseDTO updateRecurringPayment(final Long id, final RecurringPaymentRequestDTO updatedRecurringPayment);

    RecurringPaymentResponseDTO partialUpdateRecurringPayment(final Long id, final RecurringPaymentRequestDTO partialRecurringPayment);

    void deleteRecurringPayment(final Long id);
}
