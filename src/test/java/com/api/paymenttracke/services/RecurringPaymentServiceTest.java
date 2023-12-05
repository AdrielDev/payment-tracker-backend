package com.api.paymenttracke.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.api.paymenttracke.dto.recurringpayment.RecurringPaymentRequestDTO;
import com.api.paymenttracke.dto.recurringpayment.RecurringPaymentResponseDTO;
import com.api.paymenttracke.exception.ResourceNotFoundException;
import com.api.paymenttracke.models.RecurringPayment;
import com.api.paymenttracke.repositories.RecurringPaymentRepository;
import com.api.paymenttracke.services.recurringpayment.RecurringPaymentService;

import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class RecurringPaymentServiceTest {

    @InjectMocks
    private RecurringPaymentService recurringPaymentService;

    @Mock
    private RecurringPaymentRepository recurringPaymentRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void getRecurringPaymentById_WithValidId_Success() {
        Long validRecurringPaymentId = 1L;

        RecurringPayment expectedRecurringPayment = new RecurringPayment();
        expectedRecurringPayment.setId(validRecurringPaymentId);

        when(recurringPaymentRepository.findById(validRecurringPaymentId))
                .thenReturn(Optional.of(expectedRecurringPayment));

        RecurringPaymentResponseDTO result = recurringPaymentService.getRecurringPaymentById(validRecurringPaymentId);

        assertNotNull(result);
        assertEquals(validRecurringPaymentId, result.getId());
    }

    @Test
    public void getRecurringPaymentById_WithInvalidId_ThrowsResourceNotFoundException() {
        Long recurringPaymentId = 1L;

        when(recurringPaymentRepository.findById(recurringPaymentId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> recurringPaymentService.getRecurringPaymentById(recurringPaymentId));
    }

    @Test
    public void getAllRecurringPayments_Success() {
        RecurringPayment recurringPayment = new RecurringPayment();
        recurringPayment.setId(1L);

        when(recurringPaymentRepository.findAll()).thenReturn(Collections.singletonList(recurringPayment));
        when(modelMapper.map(recurringPayment, RecurringPaymentResponseDTO.class))
                .thenReturn(new RecurringPaymentResponseDTO());

        List<RecurringPaymentResponseDTO> result = recurringPaymentService.getAllRecurringPayments();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void createRecurringPayment_WithValidRecurringPayment_Success() {
        RecurringPaymentRequestDTO inputRecurringPayment = new RecurringPaymentRequestDTO();
        inputRecurringPayment.setDescription("Test Payment");

        RecurringPayment savedRecurringPayment = new RecurringPayment();
        savedRecurringPayment.setId(1L);
        savedRecurringPayment.setDescription("Test Payment");

        when(modelMapper.map(inputRecurringPayment, RecurringPayment.class)).thenReturn(savedRecurringPayment);
        when(recurringPaymentRepository.save(savedRecurringPayment)).thenReturn(savedRecurringPayment);

        RecurringPaymentResponseDTO expectedResponse = new RecurringPaymentResponseDTO();
        expectedResponse.setId(1L);
        expectedResponse.setDescription("Test Payment");

        when(modelMapper.map(savedRecurringPayment, RecurringPaymentResponseDTO.class)).thenReturn(expectedResponse);

        RecurringPaymentResponseDTO result = recurringPaymentService.createRecurringPayment(inputRecurringPayment);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Payment", result.getDescription());
    }

    @Test
    public void createRecurringPayment_WithInvalidRecurringPayment_Null() {
        RecurringPaymentRequestDTO inputRecurringPayment = new RecurringPaymentRequestDTO();

        when(modelMapper.map(inputRecurringPayment, RecurringPayment.class)).thenReturn(new RecurringPayment());
        when(recurringPaymentRepository.save(any(RecurringPayment.class))).thenReturn(null);

        RecurringPaymentResponseDTO result = recurringPaymentService.createRecurringPayment(inputRecurringPayment);

        assertNull(result);
    }

    @Test
    public void updateRecurringPayment_WithExistingRecurringPayment_Success() {
        Long recurringPaymentId = 1L;

        RecurringPayment existingRecurringPayment = new RecurringPayment();
        existingRecurringPayment.setId(recurringPaymentId);
        existingRecurringPayment.setDescription("Existing Payment");

        RecurringPaymentRequestDTO updatedRecurringPayment = new RecurringPaymentRequestDTO();
        updatedRecurringPayment.setDescription("Updated Payment");

        RecurringPayment savedRecurringPayment = new RecurringPayment();
        savedRecurringPayment.setId(recurringPaymentId);
        savedRecurringPayment.setDescription("Updated Payment");

        when(recurringPaymentRepository.findById(recurringPaymentId)).thenReturn(Optional.of(existingRecurringPayment));
        when(recurringPaymentRepository.save(existingRecurringPayment)).thenReturn(savedRecurringPayment);

        RecurringPaymentResponseDTO result = recurringPaymentService.updateRecurringPayment(recurringPaymentId,
                updatedRecurringPayment);

        assertEquals(recurringPaymentId, result.getId());
        assertEquals("Updated Payment", result.getDescription());
    }

    @Test
    public void updateRecurringPayment_WithNonExistingRecurringPayment_ThrowsResourceNotFoundException() {
        Long recurringPaymentId = 1L;
        RecurringPaymentRequestDTO updatedRecurringPayment = new RecurringPaymentRequestDTO();

        when(recurringPaymentRepository.findById(recurringPaymentId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> recurringPaymentService.updateRecurringPayment(recurringPaymentId, updatedRecurringPayment));
    }

    @Test
    public void partialUpdateRecurringPayment_WithExistingRecurringPayment_Success() {
        Long recurringPaymentId = 1L;

        RecurringPayment existingRecurringPayment = new RecurringPayment();
        existingRecurringPayment.setId(recurringPaymentId);
        existingRecurringPayment.setDescription("Existing Payment");

        RecurringPaymentRequestDTO partialRecurringPayment = new RecurringPaymentRequestDTO();
        partialRecurringPayment.setDescription("Updated Payment");

        RecurringPayment savedRecurringPayment = new RecurringPayment();
        savedRecurringPayment.setId(recurringPaymentId);
        savedRecurringPayment.setDescription("Updated Payment");

        when(recurringPaymentRepository.findById(recurringPaymentId)).thenReturn(Optional.of(existingRecurringPayment));
        when(recurringPaymentRepository.save(existingRecurringPayment)).thenReturn(savedRecurringPayment);
        when(modelMapper.map(savedRecurringPayment, RecurringPaymentResponseDTO.class))
                .thenReturn(new RecurringPaymentResponseDTO());

        RecurringPaymentResponseDTO result = recurringPaymentService.partialUpdateRecurringPayment(recurringPaymentId,
                partialRecurringPayment);

        assertEquals(recurringPaymentId, result.getId());
        assertEquals("Updated Payment", result.getDescription());
    }

    @Test
    public void partialUpdateRecurringPayment_WithNonExistingRecurringPayment_ThrowsResourceNotFoundException() {
        Long recurringPaymentId = 1L;
        RecurringPaymentRequestDTO partialRecurringPayment = new RecurringPaymentRequestDTO();

        when(recurringPaymentRepository.findById(recurringPaymentId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> recurringPaymentService
                .partialUpdateRecurringPayment(recurringPaymentId, partialRecurringPayment));
    }

    @Test
    public void deleteRecurringPayment_WithExistingRecurringPayment_Success() {
        Long recurringPaymentId = 1L;

        when(recurringPaymentRepository.existsById(recurringPaymentId)).thenReturn(true);

        recurringPaymentService.deleteRecurringPayment(recurringPaymentId);

        verify(recurringPaymentRepository).deleteById(recurringPaymentId);
    }

    @Test
    public void deleteRecurringPayment_WithNonExistingRecurringPayment_ThrowsResourceNotFoundException() {
        Long recurringPaymentId = 1L;

        when(recurringPaymentRepository.existsById(recurringPaymentId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> recurringPaymentService.deleteRecurringPayment(recurringPaymentId));
    }

    private void assertThrows(Class<? extends Throwable> expectedType, Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable e) {
            if (!expectedType.isInstance(e)) {
                throw new AssertionError("Expected exception of type: " + expectedType.getName() +
                        ", but got an exception of type: " + e.getClass().getName(), e);
            }
            return;
        }
        throw new AssertionError("Expected exception of type: " + expectedType.getName() +
                ", but no exception was thrown.");
    }
}
