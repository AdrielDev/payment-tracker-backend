package com.api.paymenttracke.config.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationKafkaProducer {
    
    final private KafkaTemplate<String, String> kafkaTemplate;

    public NotificationKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotificationToKafka(String message, String topic) {
        kafkaTemplate.send(topic, message);
    }
}
