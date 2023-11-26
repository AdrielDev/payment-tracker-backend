package com.api.paymenttracke.config;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerConfig {
    
    final private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerConfig(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotificationToKafka(String message, String topic) {
        kafkaTemplate.send(topic, message);
    }
}
