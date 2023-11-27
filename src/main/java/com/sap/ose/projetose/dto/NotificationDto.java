package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Notifications;
import com.sap.ose.projetose.modeles.Notificationsi18n;

import java.util.List;

public class NotificationDto {
    private long id;
    private long user_id;
    private Notificationsi18n message;

    private boolean isRead;

    public NotificationDto(Notifications notifications){
        id = notifications.getId();
        user_id = notifications.getReceveurs().getId();
        message = notifications.getMessage();
        isRead = notifications.isRead();
    }

}
