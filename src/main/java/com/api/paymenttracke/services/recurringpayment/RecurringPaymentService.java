package com.api.paymenttracke.services.recurringpayment;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.api.paymenttracke.dto.recurringpayment.RecurringPaymentRequestDTO;
import com.api.paymenttracke.dto.recurringpayment.RecurringPaymentResponseDTO;
import com.api.paymenttracke.enums.PaymentStatus;
import com.api.paymenttracke.exception.ResourceNotFoundException;
import com.api.paymenttracke.models.RecurringPayment;
import com.api.paymenttracke.repositories.RecurringPaymentRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class RecurringPaymentService implements RecurringPaymentServiceInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecurringPaymentService.class);

    private final RecurringPaymentRepository recurringPaymentRepository;

    private final ModelMapper modelMapper;

    public RecurringPaymentService(RecurringPaymentRepository recurringPaymentRepository,
            ModelMapper modelMapper) {
        this.recurringPaymentRepository = recurringPaymentRepository;
        this.modelMapper = modelMapper;
    }

    public RecurringPaymentResponseDTO getRecurringPaymentById(final Long id) {
        return recurringPaymentRepository.findById(id)
                .map(this::mapRecurringPaymentToDto)
                .orElseThrow(() -> new ResourceNotFoundException(RecurringPayment.class, id));
    }

    public List<RecurringPaymentResponseDTO> getAllRecurringPayments() {
        return recurringPaymentRepository.findAll().stream()
                .map(this::mapRecurringPaymentToDto)
                .collect(Collectors.toList());
    }

    public RecurringPaymentResponseDTO createRecurringPayment(final RecurringPaymentRequestDTO requestDTO) {
        final RecurringPayment recurringPayment = mapDtoToRecurringPayment(requestDTO);
        return mapRecurringPaymentToDto(recurringPaymentRepository.save(recurringPayment));
    }

    public RecurringPaymentResponseDTO updateRecurringPayment(final Long id, final RecurringPaymentRequestDTO updatedRecurringPayment) {
        final RecurringPayment existingRecurringPayment = recurringPaymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RecurringPayment.class, id));

        final RecurringPayment updatedRecurringPaymentRequest = mapDtoToRecurringPayment(updatedRecurringPayment);

        existingRecurringPayment.setDescription(updatedRecurringPaymentRequest.getDescription());
        existingRecurringPayment.setInstallmentAmount(updatedRecurringPaymentRequest.getInstallmentAmount());
        existingRecurringPayment.setInstallmentValue(updatedRecurringPaymentRequest.getInstallmentValue());
        existingRecurringPayment.setTotalInstallments(updatedRecurringPaymentRequest.getTotalInstallments());
        existingRecurringPayment.setStatus(updatedRecurringPaymentRequest.getStatus());

        final RecurringPayment savedRecurringPayment = recurringPaymentRepository.save(existingRecurringPayment);

        return mapRecurringPaymentToDto(savedRecurringPayment);
    }

    public RecurringPaymentResponseDTO partialUpdateRecurringPayment(final Long id,
            final RecurringPaymentRequestDTO partialRecurringPayment) {
        RecurringPayment existingRecurringPayment = recurringPaymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RecurringPayment.class, id));

        LOGGER.debug("Updating recurring payment with ID {}: {}", id, partialRecurringPayment);

        if (StringUtils.isNotBlank(partialRecurringPayment.getDescription())) {
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
            existingRecurringPayment.setStatus(PaymentStatus.valueOf(partialRecurringPayment.getStatus()));
        }

        final RecurringPayment savedRecurringPayment = recurringPaymentRepository.save(existingRecurringPayment);

        LOGGER.debug("Recurring payment updated: {}", savedRecurringPayment);

        return mapRecurringPaymentToDto(savedRecurringPayment);
    }

    public void deleteRecurringPayment(final Long id) {
        if (!recurringPaymentRepository.existsById(id)) {
            throw new ResourceNotFoundException(RecurringPayment.class, id);
        }
        recurringPaymentRepository.deleteById(id);
    }

    private RecurringPaymentResponseDTO mapRecurringPaymentToDto(final RecurringPayment recurringPayment) {
        return modelMapper.map(recurringPayment, RecurringPaymentResponseDTO.class);
    }

    private RecurringPayment mapDtoToRecurringPayment(final RecurringPaymentRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, RecurringPayment.class);
    }
}
