package com.sap.ose.projetose.dto;

import java.util.List;

public class NotificationDto {
    private long id;
    private List<Long> users_id;
    private String message;

    private boolean read;

}
