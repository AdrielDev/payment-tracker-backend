package com.api.paymenttracke.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.paymenttracke.enums.NotificationType;
import com.api.paymenttracke.models.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT n FROM Notification n WHERE n.notificationType = :notificationType AND n.sentDateTime = :sentDateTime")
    List<Notification> findPendingNotificationsByTypeAndDueDate(
            @Param("notificationType") NotificationType notificationType,
            @Param("sentDateTime") LocalDateTime sentDateTime);

}
