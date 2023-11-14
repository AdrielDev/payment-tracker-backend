package com.api.paymenttracke.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.api.paymenttracke.models.Notification;
import com.api.paymenttracke.repositories.NotificationRepository;
import com.api.paymenttracke.services.notification.NotificationService;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    @Test
    public void getNotificationById_WithValidId_Success() {
        final Long validNotificationId = 1L;
        final Notification expectedNotification = new Notification();
        expectedNotification.setId(validNotificationId);

        when(notificationRepository.findById(validNotificationId)).thenReturn(Optional.of(expectedNotification));

        final Notification result = notificationService.getNotificationById(validNotificationId);

        assertNotNull(result);
        assertEquals(validNotificationId, result.getId());
    }

    @Test
    public void getNotificationById_WithInvalidId_Null() {
        final Long notificationId = 1L;

        when(notificationRepository.findById(notificationId)).thenReturn(Optional.empty());

        final Notification result = notificationService.getNotificationById(notificationId);

        assertNull(result);
    }

    @Test
    public void getAllNotifications_Success() {
        final List<Notification> notificationList = new ArrayList<>();
        notificationList.add(new Notification(1L, "Message 1"));
        notificationList.add(new Notification(2L, "Message 2"));
        notificationList.add(new Notification(3L, "Message 3"));

        when(notificationRepository.findAll()).thenReturn(notificationList);

        final List<Notification> result = notificationService.getAllNotifications();

        // Assert the results
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    public void createNotification_WithValidNotification_Success() {
        final Notification inputNotification = new Notification();
        inputNotification.setMessageContent("Sample Message");

        final Notification savedNotification = new Notification();
        savedNotification.setId(1L);
        savedNotification.setMessageContent("Sample Message");

        when(notificationRepository.save(inputNotification)).thenReturn(savedNotification);

        final Notification result = notificationService.createNotification(inputNotification);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Sample Message", result.getMessageContent());
    }

    @Test
    public void createNotification_WithInvalidNotification_Null() {
        final Notification inputNotification = new Notification();

        when(notificationRepository.save(inputNotification)).thenReturn(null);

        final Notification result = notificationService.createNotification(inputNotification);

        assertNull(result);
    }

    @Test
    public void updateNotification_WithExistingNotification_Success() {
        final Long notificationId = 1L;

        final Notification existingNotification = new Notification();
        existingNotification.setId(notificationId);
        existingNotification.setMessageContent("Initial Message");

        final Notification updatedNotification = new Notification();
        updatedNotification.setId(notificationId);
        updatedNotification.setMessageContent("Updated Message");

        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(existingNotification));
        when(notificationRepository.save(existingNotification)).thenReturn(updatedNotification);

        final Notification result = notificationService.updateNotification(notificationId, updatedNotification);

        assertEquals(notificationId, result.getId());
        assertEquals("Updated Message", result.getMessageContent());
    }

    @Test
    public void updateNotification_WithNonExistingNotification_Null() {
        final Long notificationId = 1L;
        final Notification updatedNotification = new Notification();

        when(notificationRepository.findById(notificationId)).thenReturn(Optional.empty());

        final Notification result = notificationService.updateNotification(notificationId, updatedNotification);

        assertNull(result);
    }

    @Test
    public void deleteNotification_WithExistingNotification_Success() {
        final Long notificationId = 1L;

        when(notificationRepository.existsById(notificationId)).thenReturn(true);

        boolean result = notificationService.deleteNotification(notificationId);

        assertTrue(result);
    }

    @Test
    public void deleteNotification_WithNonExistingNotification_Failure() {
        final Long notificationId = 1L;

        when(notificationRepository.existsById(notificationId)).thenReturn(false);

        boolean result = notificationService.deleteNotification(notificationId);

        assertFalse(result);
    }
}
