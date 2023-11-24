package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.NotificationDto;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final EtudiantService etudiantService;
    private final EmployeurService employeurService;

    private final UtilisateurService utilisateurService;

    @Transactional
    public NotificationDto saveNotification(Notifications notifications){
        notificationRepository.save(notifications);
        return new NotificationDto(notifications);
    }

    @Transactional
    public NotificationDto saveNotificationByUser(long user_id,Notificationsi18n notificationsi18n){
        Notifications notifications = new Notifications();
        notifications.setMessage(notificationsi18n);
        notifications.setRead(false);
        notifications.setReceveurs(utilisateurService.getUserById(user_id));
        notificationRepository.save(notifications);
        return new NotificationDto(notifications);
    }

    @Transactional
    public List<NotificationDto> getNotificationByUserId(long id){
        return notificationRepository.getNotificationByUserId(id).stream().map(NotificationDto::new).toList();
    }

    @Transactional
    public List<NotificationDto> saveNotificationForAllStudent(Notificationsi18n message){
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
    public List<NotificationDto> saveNotificationForAllEmployeur(Notificationsi18n message){
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
    public List<NotificationDto> saveNotificationForAllEmployeurAndStudent(Notificationsi18n message){
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        notificationDtoList.addAll(saveNotificationForAllStudent(message));
        notificationDtoList.addAll(saveNotificationForAllEmployeur(message));
        return notificationDtoList;
    }
}
