package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.NotificationDto;
import com.sap.ose.projetose.exception.NotificationNotFoundException;
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
    private final EmailService emailService;

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
        Utilisateur userById = utilisateurService.getUserById(user_id);
        notifications.setReceveurs(userById);
        notificationRepository.save(notifications);

        sendEmailNotification(userById.getEmail(), notifications.getMessage().getBilingualEmail());

        return new NotificationDto(notifications);
    }

    private void sendEmailNotification(String email, String messageBody) {
        emailService.sendEmail(email, "Vous avez un nouvelle notification sur Projet OSE \\ You have a new notification on Projet OSE.", messageBody);
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
    public List<NotificationDto> saveNotificationForAllStudent(long program_id,Notificationsi18n message){
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        List<Etudiant> allEtudiantByProgram = utilisateurService.findAllEtudiantByProgram(program_id);
        for(Etudiant etudiant : allEtudiantByProgram){
            Notifications notifications = new Notifications();

            notifications.setReceveurs(etudiant);
            notifications.setRead(false);
            notifications.setProgramme(etudiant.getProgramme());
            notifications.setMessage(message);
            notificationRepository.save(notifications);

            notificationDtoList.add(new NotificationDto(notifications));
        }

        for(Etudiant etudiant : allEtudiantByProgram) {
            sendEmailNotification(etudiant.getEmail(), message.getBilingualEmail());
        }
        return notificationDtoList;
    }

    @Transactional
    public List<NotificationDto> saveNotificationForAllEmployeur(long program_id,Notificationsi18n message){
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        List<Employeur> allEmployeurByProgram = utilisateurService.findAllEmployeurByProgram(program_id);
        for(Employeur employeur : allEmployeurByProgram){
            Notifications notifications = new Notifications();

            notifications.setReceveurs(employeur);
            notifications.setRead(false);
            notifications.setProgramme(employeur.getProgramme());
            notifications.setMessage(message);
            notificationRepository.save(notifications);

            notificationDtoList.add(new NotificationDto(notifications));
        }

        for(Employeur employeur : allEmployeurByProgram) {
            sendEmailNotification(employeur.getEmail(), message.getBilingualEmail());
        }

        return notificationDtoList;
    }

    @Transactional
    public List<NotificationDto> saveNotificationForAllEmployeurAndStudent(long program_id ,Notificationsi18n message){
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        notificationDtoList.addAll(saveNotificationForAllStudent(program_id,message));
        notificationDtoList.addAll(saveNotificationForAllEmployeur(program_id,message));
        return notificationDtoList;
    }

    @Transactional
    public void saveNotificationForAllManagers(Notificationsi18n notificationsi18n) {
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        List<Internshipmanager> allManagers = utilisateurService.findAllManagers();
        for(Internshipmanager internshipmanager : allManagers){
            Notifications notifications = new Notifications();

            notifications.setReceveurs(internshipmanager);
            notifications.setRead(false);
            notifications.setProgramme(internshipmanager.getProgramme());
            notifications.setMessage(notificationsi18n);
            notificationRepository.save(notifications);

            notificationDtoList.add(new NotificationDto(notifications));
        }

        for (Internshipmanager manager : allManagers) {
            sendEmailNotification(manager.getEmail(), notificationsi18n.getBilingualEmail());
        }

    }

    @Transactional
    public NotificationDto updateNotificationRead(long id) {
        Notifications notification = notificationRepository.findById(id).orElse(null);

        try {
            if (notification != null) {
                notification.setRead(true);
                notificationRepository.save(notification);
                return new NotificationDto(notification);
            }

            throw new NotificationNotFoundException();
        } catch (Exception e){
            System.out.println("Error mise a jour notification: " + e.getMessage());
            return null;
        }
    }
}
