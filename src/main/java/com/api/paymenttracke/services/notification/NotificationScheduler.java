package com.api.paymenttracke.services.notification;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.api.paymenttracke.config.KafkaProducerConfig;
import com.api.paymenttracke.enums.NotificationType;
import com.api.paymenttracke.models.Notification;
import com.api.paymenttracke.utils.Utils;

@Service
public class NotificationScheduler {

    private static final Logger logger = LoggerFactory.getLogger(NotificationScheduler.class);

    final private NotificationService notificationService;

    final private KafkaProducerConfig kafkaProducer;

    public NotificationScheduler(final NotificationService notificationService,
            final KafkaProducerConfig kafkaProducer) {
        this.notificationService = notificationService;
        this.kafkaProducer = kafkaProducer;
    }

    @Scheduled(fixedRate = 60000) // (milliseconds)
    public void scheduleNotificationSending() {
        this.processAndSendNotifications();
    }

    public void processAndSendNotifications() {
        for (NotificationType type : NotificationType.values()) {
            final List<Notification> pendingNotifications = notificationService.getPendingNotifications(type);

            pendingNotifications.forEach(this::sendNotificationToKafka);
        }
    }

    private void sendNotificationToKafka(final Notification notification) {
        final NotificationType notificationType = notification.getNotificationType();
        final String message = notification.getMessageContent();
        final String topic = Utils.getTopicForNotificationType(notificationType);

        try {
            kafkaProducer.sendNotificationToKafka(message, topic);

            logger.info("Notification sent to Kafka - ID: {}, Type: {}, Topic: {}",
                    notification.getId(),
                    notificationType,
                    topic);
        } catch (Exception e) {
            logger.error("Error processing notification - ID: {}, Type: {}, Topic: {}",
                    notification.getId(),
                    notificationType,
                    topic, 
                    e);
        }
    }
}
