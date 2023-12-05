package com.api.paymenttracke.dto.notification;

import java.time.LocalDateTime;

public class NotificationRequestDTO {
    private Long id;
    private String messageContent;
    private LocalDateTime sentDateTime;
    private String status;
    private String notificationType;
    private Long associatedRecurringPaymentId;

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
    public LocalDateTime getSentDateTime() {
        return sentDateTime;
    }
    public void setSentDateTime(LocalDateTime sentDateTime) {
        this.sentDateTime = sentDateTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getNotificationType() {
        return notificationType;
    }
    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }
    public Long getAssociatedRecurringPaymentId() {
        return associatedRecurringPaymentId;
    }
    public void setAssociatedRecurringPaymentId(Long associatedRecurringPaymentId) {
        this.associatedRecurringPaymentId = associatedRecurringPaymentId;
    }
}
