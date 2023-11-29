package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sap.ose.projetose.dto.NotificationDto;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import com.sap.ose.projetose.modeles.Internshipmanager;
import com.sap.ose.projetose.modeles.Notifications;
import com.sap.ose.projetose.modeles.Notificationsi18n;
import com.sap.ose.projetose.modeles.OfferReviewRequest;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.modeles.Role;
import com.sap.ose.projetose.modeles.State;
import com.sap.ose.projetose.modeles.Utilisateur;
import com.sap.ose.projetose.repository.NotificationRepository;

import java.io.UnsupportedEncodingException;

import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;

import java.util.Stack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NotificationService.class})
@ExtendWith(SpringExtension.class)
class NotificationServiceTest {
    @MockBean
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationService notificationService;

    @MockBean
    private UtilisateurService utilisateurService;

    /**
     * Method under test: {@link NotificationService#saveNotification(Notifications)}
     */
    @Test
    void testSaveNotification() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

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
        assertEquals(1L, actualSaveNotificationResult.getId());
        assertTrue(actualSaveNotificationResult.isRead());
        assertEquals(1L, actualSaveNotificationResult.getUser_id());
        assertEquals(Notificationsi18n.newOfferAvailable, actualSaveNotificationResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationByUser(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationByUser() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.employer);
        when(utilisateurService.getUserById(anyLong())).thenReturn(utilisateur);
        NotificationDto actualSaveNotificationByUserResult = notificationService.saveNotificationByUser(1L,
                Notificationsi18n.newOfferAvailable);
        assertEquals(0L, actualSaveNotificationByUserResult.getId());
        assertFalse(actualSaveNotificationByUserResult.isRead());
        assertEquals(1L, actualSaveNotificationByUserResult.getUser_id());
        assertEquals(Notificationsi18n.newOfferAvailable, actualSaveNotificationByUserResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).getUserById(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationByUser(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationByUser2() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.employer);
        when(utilisateurService.getUserById(anyLong())).thenReturn(utilisateur);
        NotificationDto actualSaveNotificationByUserResult = notificationService.saveNotificationByUser(1L,
                Notificationsi18n.contractText);
        assertEquals(0L, actualSaveNotificationByUserResult.getId());
        assertFalse(actualSaveNotificationByUserResult.isRead());
        assertEquals(1L, actualSaveNotificationByUserResult.getUser_id());
        assertEquals(Notificationsi18n.contractText, actualSaveNotificationByUserResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).getUserById(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationByUser(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationByUser3() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.employer);
        when(utilisateurService.getUserById(anyLong())).thenReturn(utilisateur);
        NotificationDto actualSaveNotificationByUserResult = notificationService.saveNotificationByUser(1L,
                Notificationsi18n.stageText);
        assertEquals(0L, actualSaveNotificationByUserResult.getId());
        assertFalse(actualSaveNotificationByUserResult.isRead());
        assertEquals(1L, actualSaveNotificationByUserResult.getUser_id());
        assertEquals(Notificationsi18n.stageText, actualSaveNotificationByUserResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).getUserById(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationByUser(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationByUser4() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.employer);
        when(utilisateurService.getUserById(anyLong())).thenReturn(utilisateur);
        NotificationDto actualSaveNotificationByUserResult = notificationService.saveNotificationByUser(1L,
                Notificationsi18n.cvAccepter);
        assertEquals(0L, actualSaveNotificationByUserResult.getId());
        assertFalse(actualSaveNotificationByUserResult.isRead());
        assertEquals(1L, actualSaveNotificationByUserResult.getUser_id());
        assertEquals(Notificationsi18n.cvAccepter, actualSaveNotificationByUserResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).getUserById(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationByUser(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationByUser5() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.employer);
        when(utilisateurService.getUserById(anyLong())).thenReturn(utilisateur);
        NotificationDto actualSaveNotificationByUserResult = notificationService.saveNotificationByUser(1L,
                Notificationsi18n.cvRefuser);
        assertEquals(0L, actualSaveNotificationByUserResult.getId());
        assertFalse(actualSaveNotificationByUserResult.isRead());
        assertEquals(1L, actualSaveNotificationByUserResult.getUser_id());
        assertEquals(Notificationsi18n.cvRefuser, actualSaveNotificationByUserResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).getUserById(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationByUser(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationByUser6() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.employer);
        when(utilisateurService.getUserById(anyLong())).thenReturn(utilisateur);
        NotificationDto actualSaveNotificationByUserResult = notificationService.saveNotificationByUser(1L,
                Notificationsi18n.studentApplyOnOffer);
        assertEquals(0L, actualSaveNotificationByUserResult.getId());
        assertFalse(actualSaveNotificationByUserResult.isRead());
        assertEquals(1L, actualSaveNotificationByUserResult.getUser_id());
        assertEquals(Notificationsi18n.studentApplyOnOffer, actualSaveNotificationByUserResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).getUserById(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationByUser(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationByUser7() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.employer);
        when(utilisateurService.getUserById(anyLong())).thenReturn(utilisateur);
        NotificationDto actualSaveNotificationByUserResult = notificationService.saveNotificationByUser(1L,
                Notificationsi18n.revueCv);
        assertEquals(0L, actualSaveNotificationByUserResult.getId());
        assertFalse(actualSaveNotificationByUserResult.isRead());
        assertEquals(1L, actualSaveNotificationByUserResult.getUser_id());
        assertEquals(Notificationsi18n.revueCv, actualSaveNotificationByUserResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).getUserById(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationByUser(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationByUser8() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.employer);
        when(utilisateurService.getUserById(anyLong())).thenReturn(utilisateur);
        NotificationDto actualSaveNotificationByUserResult = notificationService.saveNotificationByUser(1L,
                Notificationsi18n.youAreAcceptedForStage);
        assertEquals(0L, actualSaveNotificationByUserResult.getId());
        assertFalse(actualSaveNotificationByUserResult.isRead());
        assertEquals(1L, actualSaveNotificationByUserResult.getUser_id());
        assertEquals(Notificationsi18n.youAreAcceptedForStage, actualSaveNotificationByUserResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).getUserById(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationByUser(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationByUser9() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.employer);
        when(utilisateurService.getUserById(anyLong())).thenReturn(utilisateur);
        NotificationDto actualSaveNotificationByUserResult = notificationService.saveNotificationByUser(1L,
                Notificationsi18n.newOfferSavedByEmployeur);
        assertEquals(0L, actualSaveNotificationByUserResult.getId());
        assertFalse(actualSaveNotificationByUserResult.isRead());
        assertEquals(1L, actualSaveNotificationByUserResult.getUser_id());
        assertEquals(Notificationsi18n.newOfferSavedByEmployeur, actualSaveNotificationByUserResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).getUserById(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationByUser(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationByUser10() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.employer);
        when(utilisateurService.getUserById(anyLong())).thenReturn(utilisateur);
        NotificationDto actualSaveNotificationByUserResult = notificationService.saveNotificationByUser(1L,
                Notificationsi18n.contractSignedByGS);
        assertEquals(0L, actualSaveNotificationByUserResult.getId());
        assertFalse(actualSaveNotificationByUserResult.isRead());
        assertEquals(1L, actualSaveNotificationByUserResult.getUser_id());
        assertEquals(Notificationsi18n.contractSignedByGS, actualSaveNotificationByUserResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).getUserById(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationByUser(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationByUser11() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.employer);
        when(utilisateurService.getUserById(anyLong())).thenReturn(utilisateur);
        NotificationDto actualSaveNotificationByUserResult = notificationService.saveNotificationByUser(1L,
                Notificationsi18n.newContractAsBeenCreated);
        assertEquals(0L, actualSaveNotificationByUserResult.getId());
        assertFalse(actualSaveNotificationByUserResult.isRead());
        assertEquals(1L, actualSaveNotificationByUserResult.getUser_id());
        assertEquals(Notificationsi18n.newContractAsBeenCreated, actualSaveNotificationByUserResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).getUserById(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#getNotificationByUserId(long)}
     */
    @Test
    void testGetNotificationByUserId() {
        when(notificationRepository.findByReceveurs_idOrderByIdDesc(anyLong())).thenReturn(new Stack<>());
        assertTrue(notificationService.getNotificationByUserId(1L).isEmpty());
        verify(notificationRepository).findByReceveurs_idOrderByIdDesc(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#getNotificationByUserId(long)}
     */
    @Test
    void testGetNotificationByUserId2() {
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
        when(notificationRepository.findByReceveurs_idOrderByIdDesc(anyLong())).thenReturn(notificationsList);
        List<NotificationDto> actualNotificationByUserId = notificationService.getNotificationByUserId(1L);
        assertEquals(1, actualNotificationByUserId.size());
        NotificationDto getResult = actualNotificationByUserId.get(0);
        assertEquals(1L, getResult.getId());
        assertTrue(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.newOfferAvailable, getResult.getMessage());
        verify(notificationRepository).findByReceveurs_idOrderByIdDesc(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllStudent() {
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        assertTrue(notificationService.saveNotificationForAllStudent(1L, Notificationsi18n.newOfferAvailable).isEmpty());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllStudent2() throws UnsupportedEncodingException {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        File activeCv = new File();
        activeCv.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv.setEtudiant(new Etudiant());
        activeCv.setFileName("foo.txt");
        activeCv.setId(1L);
        activeCv.setInternshipCandidates(new InternshipCandidates());
        activeCv.setIsAccepted(State.ACCEPTED);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        etudiant.setActiveCv(activeCv);
        etudiant.setCv(new ArrayList<>());
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1L);
        etudiant.setInternshipsCandidate(new ArrayList<>());
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme(programme2);
        etudiant.setRole(Role.employer);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setActiveCv(new File());
        etudiant2.setCv(new ArrayList<>());
        etudiant2.setEmail("jane.doe@example.org");
        etudiant2.setId(1L);
        etudiant2.setInternshipsCandidate(new ArrayList<>());
        etudiant2.setMatricule("Matricule");
        etudiant2.setNom("Nom");
        etudiant2.setPassword("iloveyou");
        etudiant2.setPhone("6625550144");
        etudiant2.setPrenom("Prenom");
        etudiant2.setProgramme(new Programme());
        etudiant2.setRole(Role.employer);

        InternOffer internOffer = new InternOffer();
        internOffer.setDescription("The characteristics of someone or something");
        internOffer.setEmployeur(new Employeur());
        internOffer.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer.setFile(new File());
        internOffer.setId(1L);
        internOffer.setInternshipCandidates(new ArrayList<>());
        internOffer.setLocation("Location");
        internOffer.setOfferReviewRequest(new OfferReviewRequest());
        internOffer.setProgramme(new Programme());
        internOffer.setSalaryByHour(10.0d);
        internOffer.setSession("Session");
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        File studentCv = new File();
        studentCv.setContent("AXAXAXAX".getBytes("UTF-8"));
        studentCv.setEtudiant(new Etudiant());
        studentCv.setFileName("foo.txt");
        studentCv.setId(1L);
        studentCv.setInternshipCandidates(new InternshipCandidates());
        studentCv.setIsAccepted(State.ACCEPTED);

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(etudiant2);
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(internOffer);
        internshipCandidates.setState(State.ACCEPTED);
        internshipCandidates.setStudentCv(studentCv);

        File activeCv2 = new File();
        activeCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv2.setEtudiant(etudiant);
        activeCv2.setFileName("foo.txt");
        activeCv2.setId(1L);
        activeCv2.setInternshipCandidates(internshipCandidates);
        activeCv2.setIsAccepted(State.ACCEPTED);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        Etudiant etudiant3 = new Etudiant();
        etudiant3.setActiveCv(activeCv2);
        etudiant3.setCv(new ArrayList<>());
        etudiant3.setEmail("jane.doe@example.org");
        etudiant3.setId(1L);
        etudiant3.setInternshipsCandidate(new ArrayList<>());
        etudiant3.setMatricule("Matricule");
        etudiant3.setNom("Nom");
        etudiant3.setPassword("iloveyou");
        etudiant3.setPhone("6625550144");
        etudiant3.setPrenom("Prenom");
        etudiant3.setProgramme(programme3);
        etudiant3.setRole(Role.employer);

        ArrayList<Etudiant> etudiantList = new ArrayList<>();
        etudiantList.add(etudiant3);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(etudiantList);
        List<NotificationDto> actualSaveNotificationForAllStudentResult = notificationService
                .saveNotificationForAllStudent(1L, Notificationsi18n.newOfferAvailable);
        assertEquals(1, actualSaveNotificationForAllStudentResult.size());
        NotificationDto getResult = actualSaveNotificationForAllStudentResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.newOfferAvailable, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur() {
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        assertTrue(
                notificationService.saveNotificationForAllEmployeur(1L, Notificationsi18n.newOfferAvailable).isEmpty());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur2() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = notificationService
                .saveNotificationForAllEmployeur(1L, Notificationsi18n.newOfferAvailable);
        assertEquals(1, actualSaveNotificationForAllEmployeurResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.newOfferAvailable, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur3() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = notificationService
                .saveNotificationForAllEmployeur(1L, Notificationsi18n.contractText);
        assertEquals(1, actualSaveNotificationForAllEmployeurResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.contractText, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur4() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = notificationService
                .saveNotificationForAllEmployeur(1L, Notificationsi18n.stageText);
        assertEquals(1, actualSaveNotificationForAllEmployeurResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.stageText, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur5() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = notificationService
                .saveNotificationForAllEmployeur(1L, Notificationsi18n.cvAccepter);
        assertEquals(1, actualSaveNotificationForAllEmployeurResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.cvAccepter, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur6() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = notificationService
                .saveNotificationForAllEmployeur(1L, Notificationsi18n.cvRefuser);
        assertEquals(1, actualSaveNotificationForAllEmployeurResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.cvRefuser, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur7() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = notificationService
                .saveNotificationForAllEmployeur(1L, Notificationsi18n.studentApplyOnOffer);
        assertEquals(1, actualSaveNotificationForAllEmployeurResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.studentApplyOnOffer, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur8() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = notificationService
                .saveNotificationForAllEmployeur(1L, Notificationsi18n.revueCv);
        assertEquals(1, actualSaveNotificationForAllEmployeurResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.revueCv, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur9() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = notificationService
                .saveNotificationForAllEmployeur(1L, Notificationsi18n.youAreAcceptedForStage);
        assertEquals(1, actualSaveNotificationForAllEmployeurResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.youAreAcceptedForStage, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur10() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = notificationService
                .saveNotificationForAllEmployeur(1L, Notificationsi18n.newOfferSavedByEmployeur);
        assertEquals(1, actualSaveNotificationForAllEmployeurResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.newOfferSavedByEmployeur, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent() {
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        assertTrue(notificationService.saveNotificationForAllEmployeurAndStudent(1L, Notificationsi18n.newOfferAvailable)
                .isEmpty());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent2() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = notificationService
                .saveNotificationForAllEmployeurAndStudent(1L, Notificationsi18n.newOfferAvailable);
        assertEquals(1, actualSaveNotificationForAllEmployeurAndStudentResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurAndStudentResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.newOfferAvailable, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent3() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);

        File activeCv = new File();
        activeCv.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
        activeCv.setEtudiant(new Etudiant());
        activeCv.setFileName("foo.txt");
        activeCv.setId(1L);
        activeCv.setInternshipCandidates(new InternshipCandidates());
        activeCv.setIsAccepted(State.ACCEPTED);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        etudiant.setActiveCv(activeCv);
        etudiant.setCv(new ArrayList<>());
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1L);
        etudiant.setInternshipsCandidate(new ArrayList<>());
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme(programme3);
        etudiant.setRole(Role.employer);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setActiveCv(new File());
        etudiant2.setCv(new ArrayList<>());
        etudiant2.setEmail("jane.doe@example.org");
        etudiant2.setId(1L);
        etudiant2.setInternshipsCandidate(new ArrayList<>());
        etudiant2.setMatricule("Matricule");
        etudiant2.setNom("Nom");
        etudiant2.setPassword("iloveyou");
        etudiant2.setPhone("6625550144");
        etudiant2.setPrenom("Prenom");
        etudiant2.setProgramme(new Programme());
        etudiant2.setRole(Role.employer);

        InternOffer internOffer = new InternOffer();
        internOffer.setDescription("The characteristics of someone or something");
        internOffer.setEmployeur(new Employeur());
        internOffer.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer.setFile(new File());
        internOffer.setId(1L);
        internOffer.setInternshipCandidates(new ArrayList<>());
        internOffer.setLocation("Location");
        internOffer.setOfferReviewRequest(new OfferReviewRequest());
        internOffer.setProgramme(new Programme());
        internOffer.setSalaryByHour(10.0d);
        internOffer.setSession("Session");
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        File studentCv = new File();
        studentCv.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
        studentCv.setEtudiant(new Etudiant());
        studentCv.setFileName("foo.txt");
        studentCv.setId(1L);
        studentCv.setInternshipCandidates(new InternshipCandidates());
        studentCv.setIsAccepted(State.ACCEPTED);

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(etudiant2);
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(internOffer);
        internshipCandidates.setState(State.ACCEPTED);
        internshipCandidates.setStudentCv(studentCv);

        File activeCv2 = new File();
        activeCv2.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
        activeCv2.setEtudiant(etudiant);
        activeCv2.setFileName("foo.txt");
        activeCv2.setId(1L);
        activeCv2.setInternshipCandidates(internshipCandidates);
        activeCv2.setIsAccepted(State.ACCEPTED);

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Etudiant etudiant3 = new Etudiant();
        etudiant3.setActiveCv(activeCv2);
        etudiant3.setCv(new ArrayList<>());
        etudiant3.setEmail("jane.doe@example.org");
        etudiant3.setId(1L);
        etudiant3.setInternshipsCandidate(new ArrayList<>());
        etudiant3.setMatricule("Matricule");
        etudiant3.setNom("Nom");
        etudiant3.setPassword("iloveyou");
        etudiant3.setPhone("6625550144");
        etudiant3.setPrenom("Prenom");
        etudiant3.setProgramme(programme4);
        etudiant3.setRole(Role.employer);

        ArrayList<Etudiant> etudiantList = new ArrayList<>();
        etudiantList.add(etudiant3);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(etudiantList);
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = notificationService
                .saveNotificationForAllEmployeurAndStudent(1L, Notificationsi18n.newOfferAvailable);
        assertEquals(2, actualSaveNotificationForAllEmployeurAndStudentResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurAndStudentResult.get(0);
        assertFalse(getResult.isRead());
        NotificationDto getResult2 = actualSaveNotificationForAllEmployeurAndStudentResult.get(1);
        assertFalse(getResult2.isRead());
        assertEquals(1L, getResult2.getUser_id());
        assertEquals(Notificationsi18n.newOfferAvailable, getResult2.getMessage());
        assertEquals(0L, getResult2.getId());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.newOfferAvailable, getResult.getMessage());
        assertEquals(0L, getResult.getId());
        verify(notificationRepository, atLeast(1)).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent4() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = notificationService
                .saveNotificationForAllEmployeurAndStudent(1L, Notificationsi18n.contractText);
        assertEquals(1, actualSaveNotificationForAllEmployeurAndStudentResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurAndStudentResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.contractText, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent5() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = notificationService
                .saveNotificationForAllEmployeurAndStudent(1L, Notificationsi18n.stageText);
        assertEquals(1, actualSaveNotificationForAllEmployeurAndStudentResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurAndStudentResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.stageText, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent6() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = notificationService
                .saveNotificationForAllEmployeurAndStudent(1L, Notificationsi18n.cvAccepter);
        assertEquals(1, actualSaveNotificationForAllEmployeurAndStudentResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurAndStudentResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.cvAccepter, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent7() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = notificationService
                .saveNotificationForAllEmployeurAndStudent(1L, Notificationsi18n.cvRefuser);
        assertEquals(1, actualSaveNotificationForAllEmployeurAndStudentResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurAndStudentResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.cvRefuser, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent8() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = notificationService
                .saveNotificationForAllEmployeurAndStudent(1L, Notificationsi18n.studentApplyOnOffer);
        assertEquals(1, actualSaveNotificationForAllEmployeurAndStudentResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurAndStudentResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.studentApplyOnOffer, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent9() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = notificationService
                .saveNotificationForAllEmployeurAndStudent(1L, Notificationsi18n.revueCv);
        assertEquals(1, actualSaveNotificationForAllEmployeurAndStudentResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurAndStudentResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.revueCv, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent10() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = notificationService
                .saveNotificationForAllEmployeurAndStudent(1L, Notificationsi18n.youAreAcceptedForStage);
        assertEquals(1, actualSaveNotificationForAllEmployeurAndStudentResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurAndStudentResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.youAreAcceptedForStage, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent11() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = notificationService
                .saveNotificationForAllEmployeurAndStudent(1L, Notificationsi18n.newOfferSavedByEmployeur);
        assertEquals(1, actualSaveNotificationForAllEmployeurAndStudentResult.size());
        NotificationDto getResult = actualSaveNotificationForAllEmployeurAndStudentResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.newOfferSavedByEmployeur, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers() {
        when(utilisateurService.findAllManagers()).thenReturn(new ArrayList<>());
        assertTrue(notificationService.saveNotificationForAllManagers(Notificationsi18n.newOfferAvailable).isEmpty());
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers2() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme2);
        internshipmanager.setRole(Role.employer);

        ArrayList<Internshipmanager> internshipmanagerList = new ArrayList<>();
        internshipmanagerList.add(internshipmanager);
        when(utilisateurService.findAllManagers()).thenReturn(internshipmanagerList);
        List<NotificationDto> actualSaveNotificationForAllManagersResult = notificationService
                .saveNotificationForAllManagers(Notificationsi18n.newOfferAvailable);
        assertEquals(1, actualSaveNotificationForAllManagersResult.size());
        NotificationDto getResult = actualSaveNotificationForAllManagersResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.newOfferAvailable, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers3() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme2);
        internshipmanager.setRole(Role.employer);

        ArrayList<Internshipmanager> internshipmanagerList = new ArrayList<>();
        internshipmanagerList.add(internshipmanager);
        when(utilisateurService.findAllManagers()).thenReturn(internshipmanagerList);
        List<NotificationDto> actualSaveNotificationForAllManagersResult = notificationService
                .saveNotificationForAllManagers(Notificationsi18n.contractText);
        assertEquals(1, actualSaveNotificationForAllManagersResult.size());
        NotificationDto getResult = actualSaveNotificationForAllManagersResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.contractText, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers4() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme2);
        internshipmanager.setRole(Role.employer);

        ArrayList<Internshipmanager> internshipmanagerList = new ArrayList<>();
        internshipmanagerList.add(internshipmanager);
        when(utilisateurService.findAllManagers()).thenReturn(internshipmanagerList);
        List<NotificationDto> actualSaveNotificationForAllManagersResult = notificationService
                .saveNotificationForAllManagers(Notificationsi18n.stageText);
        assertEquals(1, actualSaveNotificationForAllManagersResult.size());
        NotificationDto getResult = actualSaveNotificationForAllManagersResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.stageText, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers5() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme2);
        internshipmanager.setRole(Role.employer);

        ArrayList<Internshipmanager> internshipmanagerList = new ArrayList<>();
        internshipmanagerList.add(internshipmanager);
        when(utilisateurService.findAllManagers()).thenReturn(internshipmanagerList);
        List<NotificationDto> actualSaveNotificationForAllManagersResult = notificationService
                .saveNotificationForAllManagers(Notificationsi18n.cvAccepter);
        assertEquals(1, actualSaveNotificationForAllManagersResult.size());
        NotificationDto getResult = actualSaveNotificationForAllManagersResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.cvAccepter, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers6() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme2);
        internshipmanager.setRole(Role.employer);

        ArrayList<Internshipmanager> internshipmanagerList = new ArrayList<>();
        internshipmanagerList.add(internshipmanager);
        when(utilisateurService.findAllManagers()).thenReturn(internshipmanagerList);
        List<NotificationDto> actualSaveNotificationForAllManagersResult = notificationService
                .saveNotificationForAllManagers(Notificationsi18n.cvRefuser);
        assertEquals(1, actualSaveNotificationForAllManagersResult.size());
        NotificationDto getResult = actualSaveNotificationForAllManagersResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.cvRefuser, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers7() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme2);
        internshipmanager.setRole(Role.employer);

        ArrayList<Internshipmanager> internshipmanagerList = new ArrayList<>();
        internshipmanagerList.add(internshipmanager);
        when(utilisateurService.findAllManagers()).thenReturn(internshipmanagerList);
        List<NotificationDto> actualSaveNotificationForAllManagersResult = notificationService
                .saveNotificationForAllManagers(Notificationsi18n.studentApplyOnOffer);
        assertEquals(1, actualSaveNotificationForAllManagersResult.size());
        NotificationDto getResult = actualSaveNotificationForAllManagersResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.studentApplyOnOffer, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers8() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme2);
        internshipmanager.setRole(Role.employer);

        ArrayList<Internshipmanager> internshipmanagerList = new ArrayList<>();
        internshipmanagerList.add(internshipmanager);
        when(utilisateurService.findAllManagers()).thenReturn(internshipmanagerList);
        List<NotificationDto> actualSaveNotificationForAllManagersResult = notificationService
                .saveNotificationForAllManagers(Notificationsi18n.revueCv);
        assertEquals(1, actualSaveNotificationForAllManagersResult.size());
        NotificationDto getResult = actualSaveNotificationForAllManagersResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.revueCv, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers9() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme2);
        internshipmanager.setRole(Role.employer);

        ArrayList<Internshipmanager> internshipmanagerList = new ArrayList<>();
        internshipmanagerList.add(internshipmanager);
        when(utilisateurService.findAllManagers()).thenReturn(internshipmanagerList);
        List<NotificationDto> actualSaveNotificationForAllManagersResult = notificationService
                .saveNotificationForAllManagers(Notificationsi18n.youAreAcceptedForStage);
        assertEquals(1, actualSaveNotificationForAllManagersResult.size());
        NotificationDto getResult = actualSaveNotificationForAllManagersResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.youAreAcceptedForStage, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers10() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme2);
        internshipmanager.setRole(Role.employer);

        ArrayList<Internshipmanager> internshipmanagerList = new ArrayList<>();
        internshipmanagerList.add(internshipmanager);
        when(utilisateurService.findAllManagers()).thenReturn(internshipmanagerList);
        List<NotificationDto> actualSaveNotificationForAllManagersResult = notificationService
                .saveNotificationForAllManagers(Notificationsi18n.newOfferSavedByEmployeur);
        assertEquals(1, actualSaveNotificationForAllManagersResult.size());
        NotificationDto getResult = actualSaveNotificationForAllManagersResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.newOfferSavedByEmployeur, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers11() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme2);
        internshipmanager.setRole(Role.employer);

        ArrayList<Internshipmanager> internshipmanagerList = new ArrayList<>();
        internshipmanagerList.add(internshipmanager);
        when(utilisateurService.findAllManagers()).thenReturn(internshipmanagerList);
        List<NotificationDto> actualSaveNotificationForAllManagersResult = notificationService
                .saveNotificationForAllManagers(Notificationsi18n.contractSignedByGS);
        assertEquals(1, actualSaveNotificationForAllManagersResult.size());
        NotificationDto getResult = actualSaveNotificationForAllManagersResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.contractSignedByGS, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers12() {
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
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme2);
        internshipmanager.setRole(Role.employer);

        ArrayList<Internshipmanager> internshipmanagerList = new ArrayList<>();
        internshipmanagerList.add(internshipmanager);
        when(utilisateurService.findAllManagers()).thenReturn(internshipmanagerList);
        List<NotificationDto> actualSaveNotificationForAllManagersResult = notificationService
                .saveNotificationForAllManagers(Notificationsi18n.newContractAsBeenCreated);
        assertEquals(1, actualSaveNotificationForAllManagersResult.size());
        NotificationDto getResult = actualSaveNotificationForAllManagersResult.get(0);
        assertEquals(0L, getResult.getId());
        assertFalse(getResult.isRead());
        assertEquals(1L, getResult.getUser_id());
        assertEquals(Notificationsi18n.newContractAsBeenCreated, getResult.getMessage());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        verify(utilisateurService).findAllManagers();
    }
}

