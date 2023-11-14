package com.api.paymenttracke.models;

import java.time.LocalDateTime;

import com.api.paymenttracke.enums.NotificationStatus;
import com.api.paymenttracke.enums.NotificationType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String messageContent;
    private LocalDateTime sentDateTime;

    @Enumerated(EnumType.STRING)
    private NotificationStatus  status;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @ManyToOne
    private RecurringPayment associatedRecurringPayment;

    public Notification() {
    }

    public Notification(long id, String messageContent) {
        this.id = id;
        this.messageContent = messageContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public RecurringPayment getRecurringPayment() {
        return associatedRecurringPayment;
    }

    public void setRecurringPayment(RecurringPayment recurringPayment) {
        this.associatedRecurringPayment = recurringPayment;
    }

    public LocalDateTime getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(LocalDateTime sentDateTime) {
        this.sentDateTime = sentDateTime;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public RecurringPayment getAssociatedRecurringPayment() {
        return associatedRecurringPayment;
    }

    public void setAssociatedRecurringPayment(RecurringPayment associatedRecurringPayment) {
        this.associatedRecurringPayment = associatedRecurringPayment;
    }
}
