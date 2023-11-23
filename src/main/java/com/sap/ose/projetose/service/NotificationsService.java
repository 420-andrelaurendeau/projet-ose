package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.NotificationDto;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.Internshipmanager;
import com.sap.ose.projetose.modeles.Notifications;
import com.sap.ose.projetose.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationsService {
    private final NotificationRepository notificationRepository;
    private final EtudiantService etudiantService;
    private final EmployeurService employeurService;

    public NotificationsService(NotificationRepository notificationRepository, EtudiantService etudiantService, EmployeurService employeurService, InternshipmanagerService internshipmanagerService) {
        this.notificationRepository = notificationRepository;
        this.etudiantService = etudiantService;
        this.employeurService = employeurService;
    }

    @Transactional
    public NotificationDto saveNotification(Notifications notifications){
        notificationRepository.save(notifications);
        return new NotificationDto(notifications);
    }

    @Transactional
    public List<NotificationDto> getNotificationByUserId(long id){
        return notificationRepository.getNotificationByUserId(id).stream().map(NotificationDto::new).toList();
    }

    @Transactional
    public List<NotificationDto> saveNotificationForAllStudent(String message){
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        for(Etudiant etudiant : etudiantService.findAllEtudiant()){
            Notifications notifications = new Notifications();

            notifications.setReceveurs(etudiant);
            notifications.setRead(false);
            notifications.setProgramme(etudiant.getProgramme());
            notifications.setMessage(message);
            notificationRepository.save(notifications);
            notificationRepository.save(notifications);

            notificationDtoList.add(new NotificationDto(notifications));
        }
        return notificationDtoList;
    }

    @Transactional
    public List<NotificationDto> saveNotificationForAllEmployeur(String message){
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        for(Employeur employeur : employeurService.findAllEmployeur()){
            Notifications notifications = new Notifications();

            notifications.setReceveurs(employeur);
            notifications.setRead(false);
            notifications.setProgramme(employeur.getProgramme());
            notifications.setMessage(message);
            notificationRepository.save(notifications);
            notificationRepository.save(notifications);

            notificationDtoList.add(new NotificationDto(notifications));
        }
        return notificationDtoList;
    }

    @Transactional
    public List<NotificationDto> saveNotificationForAllEmployeurAndStudent(String message){
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        notificationDtoList.addAll(saveNotificationForAllStudent(message));
        notificationDtoList.addAll(saveNotificationForAllEmployeur(message));
        return notificationDtoList;
    }
}
