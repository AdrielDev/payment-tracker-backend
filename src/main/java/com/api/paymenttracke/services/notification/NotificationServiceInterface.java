package com.api.paymenttracke.services.notification;

import java.util.List;

import com.api.paymenttracke.enums.NotificationStatus;
import com.api.paymenttracke.models.Notification;

public interface NotificationServiceInterface {
    public Notification getNotificationById(final Long id);

    public List<Notification> getAllNotifications();

    public Notification createNotification(final Notification payment);

    public Notification updateNotification(final Long id, final Notification updatedPayment);

    public boolean deleteNotification(final Long id);

    public boolean updateStatusNotification(final Long id, final NotificationStatus notificationStatus);
}
