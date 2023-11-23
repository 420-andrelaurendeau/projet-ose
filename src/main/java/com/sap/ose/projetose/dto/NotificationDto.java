package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Notifications;

import java.util.List;

public class NotificationDto {
    private long id;
    private long users_id;
    private String message;

    private boolean read;

    public NotificationDto(Notifications notifications){
        id = notifications.getId();
        users_id = notifications.getReceveurs().getId();
        message = notifications.getMessage();
        read = notifications.isRead();
    }

}
