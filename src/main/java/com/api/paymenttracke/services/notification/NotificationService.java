package com.api.paymenttracke.services.notification;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.api.paymenttracke.dto.notification.NotificationRequestDTO;
import com.api.paymenttracke.enums.NotificationStatus;
import com.api.paymenttracke.enums.NotificationType;
import com.api.paymenttracke.models.Notification;
import com.api.paymenttracke.repositories.NotificationRepository;
import com.api.paymenttracke.exception.ResourceNotFoundException;

import io.micrometer.common.util.StringUtils;

@Service
public class NotificationService implements NotificationServiceInterface {

    final private NotificationRepository notificationRepository;
    final private ModelMapper modelMapper;

    public NotificationService(final NotificationRepository notificationRepository,
            ModelMapper modelMapper) {
        this.notificationRepository = notificationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Notification getNotificationById(final Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Notification.class, id));
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification createNotification(final NotificationRequestDTO request) {
        final Notification notification = this.mapDtoToNotification(request);
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(final Long id, final NotificationRequestDTO updatedPayment) {
        Notification notificationExisting = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Notification.class, id));

        notificationExisting.setMessageContent(updatedPayment.getMessageContent());
        notificationExisting.setSentDateTime(updatedPayment.getSentDateTime());

        if (StringUtils.isNotBlank(updatedPayment.getStatus())) {
            notificationExisting.setStatus(NotificationStatus.valueOf(updatedPayment.getStatus()));
        }

        return notificationRepository.save(notificationExisting);
    }

    @Override
    public void deleteNotification(final Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new ResourceNotFoundException(Notification.class, id);
        }
        notificationRepository.deleteById(id);
    }

    @Override
    public boolean updateStatusNotification(final Long id, final NotificationStatus notificationStatus) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Notification.class, id));

        notification.setStatus(notificationStatus);
        return true;
    }

    @Override
    public List<Notification> getPendingNotifications(NotificationType notificationType) {
        LocalDateTime currentTime = LocalDateTime.now();
        return notificationRepository.findPendingNotificationsByTypeAndDueDate(notificationType, currentTime);
    }

    private Notification mapDtoToNotification(final NotificationRequestDTO request) {
        return this.modelMapper.map(request, Notification.class);
    }
}
