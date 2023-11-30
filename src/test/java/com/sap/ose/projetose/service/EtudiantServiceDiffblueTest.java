package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.StudentAppliedOffersDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.EtudiantNotFoundException;
import com.sap.ose.projetose.exception.FileNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import com.sap.ose.projetose.modeles.OfferReviewRequest;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.modeles.Role;
import com.sap.ose.projetose.modeles.State;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.FileEntityRepository;
import com.sap.ose.projetose.repository.InternshipCandidatesRepository;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class EtudiantServiceDiffblueTest {
    @MockBean
    private EtudiantRepository etudiantRepository;

    @MockBean
    private FileEntityRepository fileEntityRepository;

    @MockBean
    private InternshipCandidatesRepository internshipCandidatesRepository;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private ProgrammeService programmeService;

    @Autowired
    private EtudiantService etudiantService;

    /**
     * Method under test: {@link EtudiantService#getEtudiants()}
     */
    @Test
    void testGetEtudiants() {
        // Arrange
        when(etudiantRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<EtudiantDto> actualEtudiants = etudiantService.getEtudiants();

        // Assert
        verify(etudiantRepository).findAll();
        assertTrue(actualEtudiants.isEmpty());
    }

    /**
     * Method under test: {@link EtudiantService#getEtudiants()}
     */
    @Test
    void testGetEtudiants2() throws UnsupportedEncodingException {
        // Arrange
        File activeCv = new File();
        activeCv.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv.setEtudiant(new Etudiant());
        activeCv.setFileName("foo.txt");
        activeCv.setId(1L);
        activeCv.setInternshipCandidates(new InternshipCandidates());
        activeCv.setIsAccepted(State.ACCEPTED);

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

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
        etudiant.setProgramme(programme);
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

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

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
        etudiant3.setProgramme(programme2);
        etudiant3.setRole(Role.employer);

        ArrayList<Etudiant> etudiantList = new ArrayList<>();
        etudiantList.add(etudiant3);
        when(etudiantRepository.findAll()).thenReturn(etudiantList);

        // Act
        List<EtudiantDto> actualEtudiants = etudiantService.getEtudiants();

        // Assert
        verify(etudiantRepository).findAll();
        assertEquals(1, actualEtudiants.size());
    }

    /**
     * Method under test: {@link EtudiantService#getEtudiants()}
     */
    @Test
    void testGetEtudiants3() {
        // Arrange
        when(etudiantRepository.findAll()).thenThrow(new EmptyResultDataAccessException(3));

        // Act and Assert
        assertThrows(EmptyResultDataAccessException.class, () -> etudiantService.getEtudiants());
        verify(etudiantRepository).findAll();
    }

    /**
     * Method under test: {@link EtudiantService#getEtudiants()}
     */
    @Test
    void testGetEtudiants4() throws UnsupportedEncodingException {
        // Arrange
        File activeCv = new File();
        activeCv.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv.setEtudiant(new Etudiant());
        activeCv.setFileName("foo.txt");
        activeCv.setId(1L);
        activeCv.setInternshipCandidates(new InternshipCandidates());
        activeCv.setIsAccepted(State.ACCEPTED);

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

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
        etudiant.setProgramme(programme);
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

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");
        Etudiant etudiant3 = mock(Etudiant.class);
        when(etudiant3.getCv()).thenReturn(new ArrayList<>());
        when(etudiant3.getInternshipsCandidate()).thenReturn(new ArrayList<>());
        when(etudiant3.getProgramme()).thenReturn(programme3);
        when(etudiant3.getMatricule()).thenReturn("Matricule");
        when(etudiant3.getEmail()).thenReturn("jane.doe@example.org");
        when(etudiant3.getNom()).thenReturn("Nom");
        when(etudiant3.getPhone()).thenReturn("6625550144");
        when(etudiant3.getPrenom()).thenReturn("Prenom");
        doNothing().when(etudiant3).setActiveCv(Mockito.<File>any());
        doNothing().when(etudiant3).setCv(Mockito.<List<File>>any());
        doNothing().when(etudiant3).setInternshipsCandidate(Mockito.<List<InternshipCandidates>>any());
        doNothing().when(etudiant3).setMatricule(Mockito.<String>any());
        doNothing().when(etudiant3).setProgramme(Mockito.<Programme>any());
        doNothing().when(etudiant3).setEmail(Mockito.<String>any());
        doNothing().when(etudiant3).setId(anyLong());
        doNothing().when(etudiant3).setNom(Mockito.<String>any());
        doNothing().when(etudiant3).setPassword(Mockito.<String>any());
        doNothing().when(etudiant3).setPhone(Mockito.<String>any());
        doNothing().when(etudiant3).setPrenom(Mockito.<String>any());
        doNothing().when(etudiant3).setRole(Mockito.<Role>any());
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
        etudiant3.setProgramme(programme2);
        etudiant3.setRole(Role.employer);

        ArrayList<Etudiant> etudiantList = new ArrayList<>();
        etudiantList.add(etudiant3);
        when(etudiantRepository.findAll()).thenReturn(etudiantList);

        // Act
        List<EtudiantDto> actualEtudiants = etudiantService.getEtudiants();

        // Assert
        verify(etudiant3).getCv();
        verify(etudiant3).getInternshipsCandidate();
        verify(etudiant3).getMatricule();
        verify(etudiant3).getProgramme();
        verify(etudiant3).setActiveCv(Mockito.<File>any());
        verify(etudiant3).setCv(Mockito.<List<File>>any());
        verify(etudiant3).setInternshipsCandidate(Mockito.<List<InternshipCandidates>>any());
        verify(etudiant3).setMatricule(Mockito.<String>any());
        verify(etudiant3).setProgramme(Mockito.<Programme>any());
        verify(etudiant3).getEmail();
        verify(etudiant3).getNom();
        verify(etudiant3).getPhone();
        verify(etudiant3).getPrenom();
        verify(etudiant3).setEmail(Mockito.<String>any());
        verify(etudiant3).setId(anyLong());
        verify(etudiant3).setNom(Mockito.<String>any());
        verify(etudiant3).setPassword(Mockito.<String>any());
        verify(etudiant3).setPhone(Mockito.<String>any());
        verify(etudiant3).setPrenom(Mockito.<String>any());
        verify(etudiant3).setRole(Mockito.<Role>any());
        verify(etudiantRepository).findAll();
        assertEquals(1, actualEtudiants.size());
    }

    /**
     * Method under test: {@link EtudiantService#getEtudiants()}
     */
    @Test
    void testGetEtudiants5() throws UnsupportedEncodingException {
        // Arrange
        File activeCv = new File();
        activeCv.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv.setEtudiant(new Etudiant());
        activeCv.setFileName("foo.txt");
        activeCv.setId(1L);
        activeCv.setInternshipCandidates(new InternshipCandidates());
        activeCv.setIsAccepted(State.ACCEPTED);

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

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
        etudiant.setProgramme(programme);
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

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");
        Etudiant etudiant3 = mock(Etudiant.class);
        when(etudiant3.getCv()).thenThrow(new FileNotFoundException("An error occurred"));
        when(etudiant3.getProgramme()).thenReturn(programme3);
        when(etudiant3.getMatricule()).thenReturn("Matricule");
        when(etudiant3.getEmail()).thenReturn("jane.doe@example.org");
        when(etudiant3.getNom()).thenReturn("Nom");
        when(etudiant3.getPhone()).thenReturn("6625550144");
        when(etudiant3.getPrenom()).thenReturn("Prenom");
        doNothing().when(etudiant3).setActiveCv(Mockito.<File>any());
        doNothing().when(etudiant3).setCv(Mockito.<List<File>>any());
        doNothing().when(etudiant3).setInternshipsCandidate(Mockito.<List<InternshipCandidates>>any());
        doNothing().when(etudiant3).setMatricule(Mockito.<String>any());
        doNothing().when(etudiant3).setProgramme(Mockito.<Programme>any());
        doNothing().when(etudiant3).setEmail(Mockito.<String>any());
        doNothing().when(etudiant3).setId(anyLong());
        doNothing().when(etudiant3).setNom(Mockito.<String>any());
        doNothing().when(etudiant3).setPassword(Mockito.<String>any());
        doNothing().when(etudiant3).setPhone(Mockito.<String>any());
        doNothing().when(etudiant3).setPrenom(Mockito.<String>any());
        doNothing().when(etudiant3).setRole(Mockito.<Role>any());
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
        etudiant3.setProgramme(programme2);
        etudiant3.setRole(Role.employer);

        ArrayList<Etudiant> etudiantList = new ArrayList<>();
        etudiantList.add(etudiant3);
        when(etudiantRepository.findAll()).thenReturn(etudiantList);

        // Act and Assert
        assertThrows(FileNotFoundException.class, () -> etudiantService.getEtudiants());
        verify(etudiant3).getCv();
        verify(etudiant3).getMatricule();
        verify(etudiant3).getProgramme();
        verify(etudiant3).setActiveCv(Mockito.<File>any());
        verify(etudiant3).setCv(Mockito.<List<File>>any());
        verify(etudiant3).setInternshipsCandidate(Mockito.<List<InternshipCandidates>>any());
        verify(etudiant3).setMatricule(Mockito.<String>any());
        verify(etudiant3).setProgramme(Mockito.<Programme>any());
        verify(etudiant3).getEmail();
        verify(etudiant3).getNom();
        verify(etudiant3).getPhone();
        verify(etudiant3).getPrenom();
        verify(etudiant3).setEmail(Mockito.<String>any());
        verify(etudiant3).setId(anyLong());
        verify(etudiant3).setNom(Mockito.<String>any());
        verify(etudiant3).setPassword(Mockito.<String>any());
        verify(etudiant3).setPhone(Mockito.<String>any());
        verify(etudiant3).setPrenom(Mockito.<String>any());
        verify(etudiant3).setRole(Mockito.<Role>any());
        verify(etudiantRepository).findAll();
    }

    /**
     * Method under test: {@link EtudiantService#getEtudiantById(Long)}
     */
    @Test
    void testGetEtudiantById() {
        // Arrange, Act and Assert
        assertNull(etudiantService.getEtudiantById(3L));
    }

    /**
     * Method under test: {@link EtudiantService#findEtudiantById(Long)}
     */
    @Test
    void testFindEtudiantById() {
        // Arrange, Act and Assert
        assertNull(etudiantService.findEtudiantById(3L));
    }

    /**
     * Method under test: {@link EtudiantService#findAllEtudiant()}
     */
    @Test
    void testFindAllEtudiant() {
        // Arrange, Act and Assert
        assertEquals(2, etudiantService.findAllEtudiant().size());
    }

    /**
     * Method under test: {@link EtudiantService#findByMatricule(String)}
     */
    @Test
    void testFindByMatricule() {
        // Arrange, Act and Assert
        assertNull(etudiantService.findByMatricule("Matricule"));
    }

    /**
     * Method under test: {@link EtudiantService#findByEmail(String)}
     */
    @Test
    void testFindByEmail() {
        // Arrange, Act and Assert
        assertNull(etudiantService.findByEmail("Courriel"));
    }

    /**
     * Method under test: {@link EtudiantService#getPageOffersAppliedByEtudiant(long, int, int, String, String, String)}
     */
    @Test
    void testGetPageOffersAppliedByEtudiant() {
        // Arrange
        when(internshipCandidatesRepository.findAllByStudentId(Mockito.<Long>any(), Mockito.<String>any(),
                Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act
        Page<StudentAppliedOffersDto> actualPageOffersAppliedByEtudiant = etudiantService.getPageOffersAppliedByEtudiant(1L,
                1, 3, "Sort Field", "Sort Direction", "Session");

        // Assert
        verify(internshipCandidatesRepository).findAllByStudentId(Mockito.<Long>any(), Mockito.<String>any(),
                Mockito.<Pageable>any());
        assertTrue(actualPageOffersAppliedByEtudiant.toList().isEmpty());
    }

    /**
     * Method under test: {@link EtudiantService#getPageOffersAppliedByEtudiant(long, int, int, String, String, String)}
     */
    @Test
    void testGetPageOffersAppliedByEtudiant2() {
        // Arrange
        when(internshipCandidatesRepository.findAllByStudentId(Mockito.<Long>any(), Mockito.<String>any(),
                Mockito.<Pageable>any())).thenReturn(null);

        // Act and Assert
        assertThrows(ServiceException.class,
                () -> etudiantService.getPageOffersAppliedByEtudiant(1L, 1, 3, "Sort Field", "Sort Direction", "Session"));
        verify(internshipCandidatesRepository).findAllByStudentId(Mockito.<Long>any(), Mockito.<String>any(),
                Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link EtudiantService#getPageOffersAppliedByEtudiant(long, int, int, String, String, String)}
     */
    @Test
    void testGetPageOffersAppliedByEtudiant3() {
        // Arrange, Act and Assert
        assertThrows(ServiceException.class,
                () -> etudiantService.getPageOffersAppliedByEtudiant(1L, -1, 3, "Sort Field", "Sort Direction", "Session"));
    }

    /**
     * Method under test: {@link EtudiantService#getPageOffersAppliedByEtudiant(long, int, int, String, String, String)}
     */
    @Test
    void testGetPageOffersAppliedByEtudiant4() {
        // Arrange, Act and Assert
        assertThrows(ServiceException.class,
                () -> etudiantService.getPageOffersAppliedByEtudiant(1L, 1, 3, "", "Sort Direction", "Session"));
    }

    /**
     * Method under test: {@link EtudiantService#getPageOffersAppliedByEtudiant(long, int, int, String, String, String)}
     */
    @Test
    void testGetPageOffersAppliedByEtudiant5() {
        // Arrange
        when(internshipCandidatesRepository.findAllByStudentId(Mockito.<Long>any(), Mockito.<String>any(),
                Mockito.<Pageable>any())).thenThrow(new EmptyResultDataAccessException(3));

        // Act and Assert
        assertThrows(DatabaseException.class,
                () -> etudiantService.getPageOffersAppliedByEtudiant(1L, 1, 3, "Sort Field", "Sort Direction", "Session"));
        verify(internshipCandidatesRepository).findAllByStudentId(Mockito.<Long>any(), Mockito.<String>any(),
                Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link EtudiantService#getPageOffersAppliedByEtudiant(long, int, int, String, String, String)}
     */
    @Test
    void testGetPageOffersAppliedByEtudiant6() {
        // Arrange
        when(internshipCandidatesRepository.findAllByStudentId(Mockito.<Long>any(), Mockito.<String>any(),
                Mockito.<Pageable>any())).thenThrow(new EtudiantNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(EtudiantNotFoundException.class,
                () -> etudiantService.getPageOffersAppliedByEtudiant(1L, 1, 3, "Sort Field", "Sort Direction", "Session"));
        verify(internshipCandidatesRepository).findAllByStudentId(Mockito.<Long>any(), Mockito.<String>any(),
                Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link EtudiantService#getPageOffersAppliedByEtudiant(long, int, int, String, String, String)}
     */
    @Test
    void testGetPageOffersAppliedByEtudiant7() {
        // Arrange
        when(internshipCandidatesRepository.findAllByStudentId(Mockito.<Long>any(), Mockito.<String>any(),
                Mockito.<Pageable>any()))
                .thenThrow(new DatabaseException("Erreur lors de la récupération des offres appliquées par l'étudiant"));

        // Act and Assert
        assertThrows(ServiceException.class,
                () -> etudiantService.getPageOffersAppliedByEtudiant(1L, 1, 3, "Sort Field", "Sort Direction", "Session"));
        verify(internshipCandidatesRepository).findAllByStudentId(Mockito.<Long>any(), Mockito.<String>any(),
                Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link EtudiantService#getOffersAppliedByEtudiant(long)}
     */
    @Test
    void testGetOffersAppliedByEtudiant() throws UnsupportedEncodingException {
        // Arrange
        File activeCv = new File();
        activeCv.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv.setEtudiant(new Etudiant());
        activeCv.setFileName("foo.txt");
        activeCv.setId(1L);
        activeCv.setInternshipCandidates(new InternshipCandidates());
        activeCv.setIsAccepted(State.ACCEPTED);

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

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
        etudiant.setProgramme(programme);
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

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

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
        etudiant3.setProgramme(programme2);
        etudiant3.setRole(Role.employer);
        Optional<Etudiant> ofResult = Optional.of(etudiant3);
        when(etudiantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        List<StudentAppliedOffersDto> actualOffersAppliedByEtudiant = etudiantService.getOffersAppliedByEtudiant(1L);

        // Assert
        verify(etudiantRepository).findById(Mockito.<Long>any());
        assertTrue(actualOffersAppliedByEtudiant.isEmpty());
    }

    /**
     * Method under test: {@link EtudiantService#getOffersAppliedByEtudiant(long)}
     */
    @Test
    void testGetOffersAppliedByEtudiant2() throws UnsupportedEncodingException {
        // Arrange
        File activeCv = new File();
        activeCv.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv.setEtudiant(new Etudiant());
        activeCv.setFileName("foo.txt");
        activeCv.setId(1L);
        activeCv.setInternshipCandidates(new InternshipCandidates());
        activeCv.setIsAccepted(State.ACCEPTED);

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

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
        etudiant.setProgramme(programme);
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

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");
        Etudiant etudiant3 = mock(Etudiant.class);
        when(etudiant3.getInternshipsCandidate()).thenReturn(new ArrayList<>());
        doNothing().when(etudiant3).setActiveCv(Mockito.<File>any());
        doNothing().when(etudiant3).setCv(Mockito.<List<File>>any());
        doNothing().when(etudiant3).setInternshipsCandidate(Mockito.<List<InternshipCandidates>>any());
        doNothing().when(etudiant3).setMatricule(Mockito.<String>any());
        doNothing().when(etudiant3).setProgramme(Mockito.<Programme>any());
        doNothing().when(etudiant3).setEmail(Mockito.<String>any());
        doNothing().when(etudiant3).setId(anyLong());
        doNothing().when(etudiant3).setNom(Mockito.<String>any());
        doNothing().when(etudiant3).setPassword(Mockito.<String>any());
        doNothing().when(etudiant3).setPhone(Mockito.<String>any());
        doNothing().when(etudiant3).setPrenom(Mockito.<String>any());
        doNothing().when(etudiant3).setRole(Mockito.<Role>any());
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
        etudiant3.setProgramme(programme2);
        etudiant3.setRole(Role.employer);
        Optional<Etudiant> ofResult = Optional.of(etudiant3);
        when(etudiantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        List<StudentAppliedOffersDto> actualOffersAppliedByEtudiant = etudiantService.getOffersAppliedByEtudiant(1L);

        // Assert
        verify(etudiant3).getInternshipsCandidate();
        verify(etudiant3).setActiveCv(Mockito.<File>any());
        verify(etudiant3).setCv(Mockito.<List<File>>any());
        verify(etudiant3).setInternshipsCandidate(Mockito.<List<InternshipCandidates>>any());
        verify(etudiant3).setMatricule(Mockito.<String>any());
        verify(etudiant3).setProgramme(Mockito.<Programme>any());
        verify(etudiant3).setEmail(Mockito.<String>any());
        verify(etudiant3).setId(anyLong());
        verify(etudiant3).setNom(Mockito.<String>any());
        verify(etudiant3).setPassword(Mockito.<String>any());
        verify(etudiant3).setPhone(Mockito.<String>any());
        verify(etudiant3).setPrenom(Mockito.<String>any());
        verify(etudiant3).setRole(Mockito.<Role>any());
        verify(etudiantRepository).findById(Mockito.<Long>any());
        assertTrue(actualOffersAppliedByEtudiant.isEmpty());
    }

    /**
     * Method under test: {@link EtudiantService#getAllCvfromStudentById(long)}
     */
    @Test
    void testGetAllCvfromStudentById() {
        // Arrange, Act and Assert
        assertTrue(etudiantService.getAllCvfromStudentById(1L).isEmpty());
        assertTrue(etudiantService.getAllCvfromStudentById(2L).isEmpty());
    }

    /**
     * Method under test: {@link EtudiantService#getDefaultCv(long)}
     */
    @Test
    void testGetDefaultCv() {
        // Arrange, Act and Assert
        assertNull(etudiantService.getDefaultCv(1L));
        assertThrows(ServiceException.class, () -> etudiantService.getDefaultCv(3L));
    }
}
