package com.example.radioadsapp.repository;


import com.example.radioadsapp.model.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity,Long> {
    @Transactional
    @Modifying
    @Query("UPDATE NotificationEntity n SET n.viewed = true WHERE n.viewed = false ")
    void updateAllNotifications();


    long countNotificationsByViewedIsFalse();
}
