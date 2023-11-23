package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.NotificationDto;
import com.sap.ose.projetose.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsService {
    private final NotificationRepository notificationRepository;

    public NotificationsService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public NotificationDto saveNotification(){

        return null;
    }

    @Transactional
    public List<NotificationDto> getNotificationByUserrId(long id){
        return null;
    }

    @Transactional
    public List<NotificationDto> saveNotificationForAllStudent(){
        return null;
    }

    @Transactional
    public List<NotificationDto> saveNotificationForAllEmployeur(){
        return null;
    }

    @Transactional
    public List<NotificationDto> saveNotificationForAllEmployeurAndStudent(){
        return null;
    }
}
