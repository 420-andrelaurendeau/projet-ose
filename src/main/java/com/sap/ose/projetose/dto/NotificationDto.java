package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Notifications;
import com.sap.ose.projetose.modeles.Notificationsi18n;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private long id;
    private long user_id;
    private Notificationsi18n message;
    private boolean isRead;

    public NotificationDto(Notifications notifications){
        this.id = notifications.getId();
        this.user_id = notifications.getReceveurs().getId();
        this.message = notifications.getMessage();
        this.isRead = notifications.isRead();
    }

}
