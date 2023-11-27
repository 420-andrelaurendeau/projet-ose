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
        List<NotificationDto> list = new ArrayList<>();

        for(Notifications notifications : notificationRepository.findByReceveurs_idOrderByIdDesc(id)){
            list.add(new NotificationDto(notifications));
        }
        System.out.println(list);
        return list;
    }

    @Transactional
    public List<NotificationDto> saveNotificationForAllStudent(Notificationsi18n message){
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        for(Etudiant etudiant : utilisateurService.findAllEtudiant()){
            Notifications notifications = new Notifications();

            notifications.setReceveurs(etudiant);
            notifications.setRead(false);
            notifications.setProgramme(etudiant.getProgramme());
            notifications.setMessage(message);
            notificationRepository.save(notifications);

            notificationDtoList.add(new NotificationDto(notifications));
        }
        return notificationDtoList;
    }

    @Transactional
    public List<NotificationDto> saveNotificationForAllEmployeur(Notificationsi18n message){
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        for(Employeur employeur : utilisateurService.findAllEmployeur()){
            Notifications notifications = new Notifications();

            notifications.setReceveurs(employeur);
            notifications.setRead(false);
            notifications.setProgramme(employeur.getProgramme());
            notifications.setMessage(message);
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

    @Transactional
    public List<NotificationDto> saveNotificationForAllManagers(Notificationsi18n notificationsi18n) {
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        for(Internshipmanager internshipmanager : utilisateurService.findAllManagers()){
            Notifications notifications = new Notifications();

            notifications.setReceveurs(internshipmanager);
            notifications.setRead(false);
            notifications.setProgramme(internshipmanager.getProgramme());
            notifications.setMessage(notificationsi18n);
            notificationRepository.save(notifications);

            notificationDtoList.add(new NotificationDto(notifications));
        }
        return notificationDtoList;
    }

    @Transactional
    public NotificationDto updateNotificationRead(long id) {
        Notifications notification = notificationRepository.findById(id).orElse(null);

        try {
            assert notification != null;
            notification.setRead(true);
            notificationRepository.save(notification);
            return new NotificationDto(notification);
        } catch (Exception e){
            System.out.println("Error mise a jour notification: " + e.getMessage());
            return null;
        }
    }
}
