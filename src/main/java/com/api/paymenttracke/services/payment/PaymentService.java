package com.api.paymenttracke.services.payment;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.api.paymenttracke.dto.payment.PaymentRequestDTO;
import com.api.paymenttracke.enums.PaymentStatus;
import com.api.paymenttracke.exception.ResourceNotFoundException;
import com.api.paymenttracke.models.Payment;
import com.api.paymenttracke.repositories.PaymentRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class PaymentService implements PaymentServiceInterface {
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    public PaymentService(final PaymentRepository paymentRepository,
            ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
    }

    public Payment getPaymentById(final Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Payment.class, id));
    }

    public Payment createPayment(final PaymentRequestDTO requestDTO) {
        final Payment payment = mapDtoToPayment(requestDTO);
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(final Long id, final PaymentRequestDTO updatedPayment) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Payment.class, id));

        existingPayment.setAmount(updatedPayment.getAmount());
        existingPayment.setDueDate(updatedPayment.getDueDate());

        if (StringUtils.isNotBlank(updatedPayment.getStatus())) {
            existingPayment.setStatus(PaymentStatus.valueOf(updatedPayment.getStatus()));
        }

        return paymentRepository.save(existingPayment);
    }

    public Boolean updateStatusPayment(final Long id, final PaymentStatus status) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Payment.class, id));

        existingPayment.setStatus(status);
        paymentRepository.save(existingPayment);
        return true;
    }

    public void deletePayment(final Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new ResourceNotFoundException(Payment.class, id);
        }
        paymentRepository.deleteById(id);
    }

    private Payment mapDtoToPayment(final PaymentRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Payment.class);
    }
}
