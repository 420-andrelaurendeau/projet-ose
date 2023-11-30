package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.sap.ose.projetose.dto.NotificationDto;
import com.sap.ose.projetose.modeles.Notifications;
import com.sap.ose.projetose.modeles.Notificationsi18n;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.modeles.Role;
import com.sap.ose.projetose.modeles.Utilisateur;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import com.sap.ose.projetose.repository.NotificationRepository;
import java.util.List;
import java.util.Stack;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NotificationServiceTest {
  /**
    * Method under test: {@link NotificationService#saveNotification(Notifications)}
    */
  @Test
  void testSaveNotification() {
    //   Diffblue Cover was unable to write a Spring test,
    //   so wrote a non-Spring test instead.
    //   Reason: R005 Unable to load class.
    //   Class: org.springframework.mail.javamail.JavaMailSender
    //   Please check that the class is available on your test runtime classpath.
    //   See https://diff.blue/R005 to resolve this issue.

    Programme programme = new Programme();
    programme.setDescription("The characteristics of someone or something");
    programme.setId(1L);
    programme.setNom("Nom");

    Utilisateur receveurs = new Utilisateur();
    receveurs.setEmail("jane.doe@example.org");
    receveurs.setId(1L);
    receveurs.setNom("Nom");
    receveurs.setPassword("iloveyou");
    receveurs.setPhone("6625550144");
    receveurs.setPrenom("Prenom");
    receveurs.setRole(Role.employer);

    Notifications notifications = new Notifications();
    notifications.setId(1L);
    notifications.setMessage(Notificationsi18n.newOfferAvailable);
    notifications.setProgramme(programme);
    notifications.setRead(true);
    notifications.setReceveurs(receveurs);
    NotificationRepository notificationRepository = mock(NotificationRepository.class);
    when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);
    NotificationService notificationService = new NotificationService(notificationRepository,
        new UtilisateurService(mock(EmployeurRepository.class), mock(EtudiantRepository.class),
            mock(InternshipmanagerRepository.class)),
        null);

    Programme programme2 = new Programme();
    programme2.setDescription("The characteristics of someone or something");
    programme2.setId(1L);
    programme2.setNom("Nom");

    Utilisateur receveurs2 = new Utilisateur();
    receveurs2.setEmail("jane.doe@example.org");
    receveurs2.setId(1L);
    receveurs2.setNom("Nom");
    receveurs2.setPassword("iloveyou");
    receveurs2.setPhone("6625550144");
    receveurs2.setPrenom("Prenom");
    receveurs2.setRole(Role.employer);

    Notifications notifications2 = new Notifications();
    notifications2.setId(1L);
    notifications2.setMessage(Notificationsi18n.newOfferAvailable);
    notifications2.setProgramme(programme2);
    notifications2.setRead(true);
    notifications2.setReceveurs(receveurs2);
    NotificationDto actualSaveNotificationResult = notificationService.saveNotification(notifications2);
    verify(notificationRepository).save(Mockito.<Notifications>any());
    assertEquals(1L, actualSaveNotificationResult.getId());
    assertEquals(1L, actualSaveNotificationResult.getUser_id());
    assertEquals(Notificationsi18n.newOfferAvailable, actualSaveNotificationResult.getMessage());
    assertTrue(actualSaveNotificationResult.isRead());
  }

  /**
  * Method under test: {@link NotificationService#getNotificationByUserId(long)}
  */
  @Test
  void testGetNotificationByUserId() {
    //   Diffblue Cover was unable to write a Spring test,
    //   so wrote a non-Spring test instead.
    //   Reason: R005 Unable to load class.
    //   Class: org.springframework.mail.javamail.JavaMailSender
    //   Please check that the class is available on your test runtime classpath.
    //   See https://diff.blue/R005 to resolve this issue.

    NotificationRepository notificationRepository = mock(NotificationRepository.class);
    when(notificationRepository.findByReceveurs_idOrderByIdDesc(anyLong())).thenReturn(new Stack<>());
    List<NotificationDto> actualNotificationByUserId = (new NotificationService(notificationRepository,
        new UtilisateurService(mock(EmployeurRepository.class), mock(EtudiantRepository.class),
            mock(InternshipmanagerRepository.class)),
        null)).getNotificationByUserId(1L);
    verify(notificationRepository).findByReceveurs_idOrderByIdDesc(anyLong());
    assertTrue(actualNotificationByUserId.isEmpty());
  }

  /**
  * Method under test: {@link NotificationService#getNotificationByUserId(long)}
  */
  @Test
  void testGetNotificationByUserId2() {
    //   Diffblue Cover was unable to write a Spring test,
    //   so wrote a non-Spring test instead.
    //   Reason: R005 Unable to load class.
    //   Class: org.springframework.mail.javamail.JavaMailSender
    //   Please check that the class is available on your test runtime classpath.
    //   See https://diff.blue/R005 to resolve this issue.

    Programme programme = new Programme();
    programme.setDescription("The characteristics of someone or something");
    programme.setId(1L);
    programme.setNom("Nom");

    Utilisateur receveurs = new Utilisateur();
    receveurs.setEmail("jane.doe@example.org");
    receveurs.setId(1L);
    receveurs.setNom("Nom");
    receveurs.setPassword("iloveyou");
    receveurs.setPhone("6625550144");
    receveurs.setPrenom("Prenom");
    receveurs.setRole(Role.employer);

    Notifications notifications = new Notifications();
    notifications.setId(1L);
    notifications.setMessage(Notificationsi18n.newOfferAvailable);
    notifications.setProgramme(programme);
    notifications.setRead(true);
    notifications.setReceveurs(receveurs);

    Stack<Notifications> notificationsList = new Stack<>();
    notificationsList.add(notifications);
    NotificationRepository notificationRepository = mock(NotificationRepository.class);
    when(notificationRepository.findByReceveurs_idOrderByIdDesc(anyLong())).thenReturn(notificationsList);
    List<NotificationDto> actualNotificationByUserId = (new NotificationService(notificationRepository,
        new UtilisateurService(mock(EmployeurRepository.class), mock(EtudiantRepository.class),
            mock(InternshipmanagerRepository.class)),
        null)).getNotificationByUserId(1L);
    verify(notificationRepository).findByReceveurs_idOrderByIdDesc(anyLong());
    assertEquals(1, actualNotificationByUserId.size());
    NotificationDto getResult = actualNotificationByUserId.get(0);
    assertEquals(1L, getResult.getId());
    assertEquals(1L, getResult.getUser_id());
    assertEquals(Notificationsi18n.newOfferAvailable, getResult.getMessage());
    assertTrue(getResult.isRead());
  }
}
