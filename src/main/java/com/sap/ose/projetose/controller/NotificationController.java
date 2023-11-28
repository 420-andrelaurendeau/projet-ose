package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.NotificationDto;
import com.sap.ose.projetose.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;


    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('internshipmanager') OR (hasAuthority('student')) OR (hasAuthority('employer'))")
    public List<NotificationDto> getNotifications(@PathVariable long userId) {
        return notificationService.getNotificationByUserId(userId);
    }
    @PutMapping("/read/{id}")
    public NotificationDto updateNotifcationRead(@PathVariable long id) {
        return notificationService.updateNotificationRead(id);
    }


}
