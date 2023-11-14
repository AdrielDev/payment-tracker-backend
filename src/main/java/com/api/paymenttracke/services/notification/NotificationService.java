package com.api.paymenttracke.services.notification;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api.paymenttracke.enums.NotificationStatus;
import com.api.paymenttracke.enums.NotificationType;
import com.api.paymenttracke.models.Notification;
import com.api.paymenttracke.repositories.NotificationRepository;

@Service
public class NotificationService implements NotificationServiceInterface {

    private NotificationRepository notificationRepository;

    public NotificationService(final NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification getNotificationById(final Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification createNotification(final Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(final Long id, final Notification updatedPayment) {
        Notification notificationExisting = notificationRepository.findById(id).orElse(null);
        if (notificationExisting == null) {
            return null;
        }

        notificationExisting.setMessageContent(updatedPayment.getMessageContent());
        notificationExisting.setRecurringPayment(updatedPayment.getRecurringPayment());
        notificationExisting.setSentDateTime(updatedPayment.getSentDateTime());
        notificationExisting.setStatus(updatedPayment.getStatus());

        return notificationRepository.save(notificationExisting);
    }

    @Override
    public boolean deleteNotification(final Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStatusNotification(final Long id, final NotificationStatus notificationStatus) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        if (notification == null) {
            return false;
        }

        notification.setStatus(notificationStatus);
        return true;
    }

    public List<Notification> getPendingNotifications(NotificationType notificationType) {
        LocalDateTime currentTime = LocalDateTime.now();
        return notificationRepository.findPendingNotificationsByTypeAndDueDate(notificationType, currentTime);
    }
}
