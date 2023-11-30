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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NotificationService.class})
@ExtendWith(SpringExtension.class)
class NotificationServiceDiffblueTest {
    @Autowired
    private NotificationService notificationService;

    /**
     * Method under test: {@link NotificationService#saveNotificationByUser(long, Notificationsi18n)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveNotificationByUser() {
        // TODO: Complete this test.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        long user_id = 0L;
        Notificationsi18n notificationsi18n = Notificationsi18n.newOfferAvailable;

        // Act
        NotificationDto actualSaveNotificationByUserResult = this.notificationService.saveNotificationByUser(user_id,
                notificationsi18n);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllStudent() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        EtudiantRepository etudiantRepository = mock(EtudiantRepository.class);
        when(etudiantRepository.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllStudentResult = (new NotificationService(
                mock(NotificationRepository.class),
                new UtilisateurService(mock(EmployeurRepository.class), etudiantRepository,
                        mock(InternshipmanagerRepository.class)),
                null)).saveNotificationForAllStudent(1L, Notificationsi18n.newOfferAvailable);
        verify(etudiantRepository).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllStudent2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllStudent(1L,
                Notificationsi18n.newOfferAvailable);
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllStudent3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllStudent(1L,
                Notificationsi18n.cvAccepter);
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllStudent4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllStudent(1L,
                Notificationsi18n.cvRefuser);
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllStudent5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllStudent(1L,
                Notificationsi18n.studentApplyOnOffer);
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllStudent6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllStudent(1L,
                Notificationsi18n.revueCv);
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllStudent7() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllStudent(1L,
                Notificationsi18n.youAreAcceptedForStage);
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllStudent8() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllStudent(1L,
                Notificationsi18n.newOfferSavedByEmployeur);
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllStudent9() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllStudent(1L,
                Notificationsi18n.contractSignedByGSForEmployer);
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllStudent10() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllStudent(1L,
                Notificationsi18n.contractSignedByGSForStudent);
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllStudent11() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllStudent(1L,
                Notificationsi18n.contractSignedByStudentForEmployer);
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllStudent12() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllStudent(1L,
                Notificationsi18n.contractSignedByStudentForGS);
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        EmployeurRepository employeurRepository = mock(EmployeurRepository.class);
        when(employeurRepository.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = (new NotificationService(
                mock(NotificationRepository.class),
                new UtilisateurService(employeurRepository, mock(EtudiantRepository.class),
                        mock(InternshipmanagerRepository.class)),
                null)).saveNotificationForAllEmployeur(1L, Notificationsi18n.newOfferAvailable);
        verify(employeurRepository).findAllEmployeurByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeur(1L,
                Notificationsi18n.newOfferAvailable);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeur(1L,
                Notificationsi18n.cvAccepter);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeur(1L,
                Notificationsi18n.cvRefuser);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeur(1L,
                Notificationsi18n.studentApplyOnOffer);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeur(1L,
                Notificationsi18n.revueCv);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur7() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeur(1L,
                Notificationsi18n.youAreAcceptedForStage);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur8() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeur(1L,
                Notificationsi18n.newOfferSavedByEmployeur);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur9() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeur(1L,
                Notificationsi18n.contractSignedByGSForEmployer);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur10() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeur(1L,
                Notificationsi18n.contractSignedByGSForStudent);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur11() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeur(1L,
                Notificationsi18n.contractSignedByStudentForEmployer);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeur(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeur12() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeur(1L,
                Notificationsi18n.contractSignedByStudentForGS);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        EmployeurRepository employeurRepository = mock(EmployeurRepository.class);
        when(employeurRepository.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        EtudiantRepository etudiantRepository = mock(EtudiantRepository.class);
        when(etudiantRepository.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = (new NotificationService(
                mock(NotificationRepository.class),
                new UtilisateurService(employeurRepository, etudiantRepository, mock(InternshipmanagerRepository.class)), null))
                .saveNotificationForAllEmployeurAndStudent(1L, Notificationsi18n.newOfferAvailable);
        verify(employeurRepository).findAllEmployeurByProgram(anyLong());
        verify(etudiantRepository).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurAndStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeurAndStudent(1L,
                Notificationsi18n.newOfferAvailable);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurAndStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeurAndStudent(1L,
                Notificationsi18n.cvAccepter);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurAndStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeurAndStudent(1L,
                Notificationsi18n.cvRefuser);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurAndStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeurAndStudent(1L,
                Notificationsi18n.studentApplyOnOffer);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurAndStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeurAndStudent(1L,
                Notificationsi18n.revueCv);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurAndStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent7() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeurAndStudent(1L,
                Notificationsi18n.youAreAcceptedForStage);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurAndStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent8() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeurAndStudent(1L,
                Notificationsi18n.newOfferSavedByEmployeur);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurAndStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent9() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeurAndStudent(1L,
                Notificationsi18n.contractSignedByGSForEmployer);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurAndStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent10() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeurAndStudent(1L,
                Notificationsi18n.contractSignedByGSForStudent);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurAndStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent11() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeurAndStudent(1L,
                Notificationsi18n.contractSignedByStudentForEmployer);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurAndStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllEmployeurAndStudent(long, Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllEmployeurAndStudent12() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllEmployeurByProgram(anyLong())).thenReturn(new ArrayList<>());
        when(utilisateurService.findAllEtudiantByProgram(anyLong())).thenReturn(new ArrayList<>());
        List<NotificationDto> actualSaveNotificationForAllEmployeurAndStudentResult = (new NotificationService(
                mock(NotificationRepository.class), utilisateurService, null)).saveNotificationForAllEmployeurAndStudent(1L,
                Notificationsi18n.contractSignedByStudentForGS);
        verify(utilisateurService).findAllEmployeurByProgram(anyLong());
        verify(utilisateurService).findAllEtudiantByProgram(anyLong());
        assertTrue(actualSaveNotificationForAllEmployeurAndStudentResult.isEmpty());
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        InternshipmanagerRepository internshipmanagerRepository = mock(InternshipmanagerRepository.class);
        when(internshipmanagerRepository.findAll()).thenReturn(new ArrayList<>());
        (new NotificationService(mock(NotificationRepository.class), new UtilisateurService(mock(EmployeurRepository.class),
                mock(EtudiantRepository.class), internshipmanagerRepository), null))
                .saveNotificationForAllManagers(Notificationsi18n.newOfferAvailable);
        verify(internshipmanagerRepository).findAll();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllManagers()).thenReturn(new ArrayList<>());
        (new NotificationService(mock(NotificationRepository.class), utilisateurService, null))
                .saveNotificationForAllManagers(Notificationsi18n.newOfferAvailable);
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllManagers()).thenReturn(new ArrayList<>());
        (new NotificationService(mock(NotificationRepository.class), utilisateurService, null))
                .saveNotificationForAllManagers(Notificationsi18n.cvAccepter);
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllManagers()).thenReturn(new ArrayList<>());
        (new NotificationService(mock(NotificationRepository.class), utilisateurService, null))
                .saveNotificationForAllManagers(Notificationsi18n.cvRefuser);
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllManagers()).thenReturn(new ArrayList<>());
        (new NotificationService(mock(NotificationRepository.class), utilisateurService, null))
                .saveNotificationForAllManagers(Notificationsi18n.studentApplyOnOffer);
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllManagers()).thenReturn(new ArrayList<>());
        (new NotificationService(mock(NotificationRepository.class), utilisateurService, null))
                .saveNotificationForAllManagers(Notificationsi18n.revueCv);
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers7() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllManagers()).thenReturn(new ArrayList<>());
        (new NotificationService(mock(NotificationRepository.class), utilisateurService, null))
                .saveNotificationForAllManagers(Notificationsi18n.youAreAcceptedForStage);
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers8() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllManagers()).thenReturn(new ArrayList<>());
        (new NotificationService(mock(NotificationRepository.class), utilisateurService, null))
                .saveNotificationForAllManagers(Notificationsi18n.newOfferSavedByEmployeur);
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers9() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllManagers()).thenReturn(new ArrayList<>());
        (new NotificationService(mock(NotificationRepository.class), utilisateurService, null))
                .saveNotificationForAllManagers(Notificationsi18n.contractSignedByGSForEmployer);
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers10() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllManagers()).thenReturn(new ArrayList<>());
        (new NotificationService(mock(NotificationRepository.class), utilisateurService, null))
                .saveNotificationForAllManagers(Notificationsi18n.contractSignedByGSForStudent);
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers11() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllManagers()).thenReturn(new ArrayList<>());
        (new NotificationService(mock(NotificationRepository.class), utilisateurService, null))
                .saveNotificationForAllManagers(Notificationsi18n.contractSignedByStudentForEmployer);
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#saveNotificationForAllManagers(Notificationsi18n)}
     */
    @Test
    void testSaveNotificationForAllManagers12() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: org.springframework.mail.javamail.JavaMailSender
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        UtilisateurService utilisateurService = mock(UtilisateurService.class);
        when(utilisateurService.findAllManagers()).thenReturn(new ArrayList<>());
        (new NotificationService(mock(NotificationRepository.class), utilisateurService, null))
                .saveNotificationForAllManagers(Notificationsi18n.contractSignedByStudentForGS);
        verify(utilisateurService).findAllManagers();
    }

    /**
     * Method under test: {@link NotificationService#updateNotificationRead(long)}
     */
    @Test
    void testUpdateNotificationRead() {
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
        Optional<Notifications> ofResult = Optional.of(notifications);

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
        NotificationRepository notificationRepository = mock(NotificationRepository.class);
        when(notificationRepository.save(Mockito.<Notifications>any())).thenReturn(notifications2);
        when(notificationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        NotificationDto actualUpdateNotificationReadResult = (new NotificationService(notificationRepository,
                new UtilisateurService(mock(EmployeurRepository.class), mock(EtudiantRepository.class),
                        mock(InternshipmanagerRepository.class)),
                null)).updateNotificationRead(1L);
        verify(notificationRepository).findById(Mockito.<Long>any());
        verify(notificationRepository).save(Mockito.<Notifications>any());
        assertEquals(1L, actualUpdateNotificationReadResult.getId());
        assertEquals(1L, actualUpdateNotificationReadResult.getUser_id());
        assertEquals(Notificationsi18n.newOfferAvailable, actualUpdateNotificationReadResult.getMessage());
        assertTrue(actualUpdateNotificationReadResult.isRead());
    }
}
