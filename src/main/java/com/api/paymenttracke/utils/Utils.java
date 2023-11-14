package com.api.paymenttracke.utils;

import com.api.paymenttracke.enums.NotificationType;

public class Utils {
    public static String getTopicForNotificationType(NotificationType type) {
        switch (type) {
            case SMS:
                return "notification-sms";
            case EMAIL:
                return "notification-email";
            case WHATSAPP:
                return "notification-whatsapp";
            default:
                throw new IllegalArgumentException("Invalid notification type");
        }
    }
}
