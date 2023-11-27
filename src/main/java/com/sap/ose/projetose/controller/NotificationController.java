package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.NotificationDto;
import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;


    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    @PreAuthorize("hasAuthority('internshipmanager') OR (hasAuthority('student')) OR (hasAuthority('employer'))")
    public List<NotificationDto> getNotifications(@PathVariable Long id) {
        return notificationService.getNotificationByUserId(id);
    }
}
