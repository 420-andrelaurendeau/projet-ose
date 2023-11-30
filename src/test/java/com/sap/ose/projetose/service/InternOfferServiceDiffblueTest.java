package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.OfferAlreadyReviewException;
import com.sap.ose.projetose.exception.OfferNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import com.sap.ose.projetose.modeles.Internshipmanager;
import com.sap.ose.projetose.modeles.OfferReviewRequest;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.modeles.Role;
import com.sap.ose.projetose.modeles.State;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.InternOfferRepository;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {InternOfferService.class})
@ExtendWith(SpringExtension.class)
class InternOfferServiceDiffblueTest {
    @MockBean
    private EmployeurRepository employeurRepository;

    @MockBean
    private EmployeurService employeurService;

    @MockBean
    private InternOfferRepository internOfferRepository;

    @Autowired
    private InternOfferService internOfferService;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private ProgrammeService programmeService;

    /**
     * Method under test: {@link InternOfferService#getInterOfferById(Long)}
     */
    @Test
    void testGetInterOfferById() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        ArrayList<InternOffer> internOffers = new ArrayList<>();
        employeur.setInternOffers(internOffers);
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);

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

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(new Programme());
        employeur2.setRole(Role.employer);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(new Etudiant());
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(new InternshipCandidates());
        file2.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(new InternOffer());
        offerReviewRequest.setInternshipmanager(new Internshipmanager());

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur2);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file2);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setSession("Session");
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme4);
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(internOffer2);
        offerReviewRequest2.setInternshipmanager(internshipmanager);

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest2);
        internOffer3.setProgramme(programme5);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setSession("Session");
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");
        Optional<InternOffer> ofResult = Optional.of(internOffer3);
        when(internOfferRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        InternOfferDto actualInterOfferById = internOfferService.getInterOfferById(1L);
        verify(internOfferRepository).findById(Mockito.<Long>any());
        assertEquals("1970-01-01", actualInterOfferById.getEndDate());
        assertEquals("1970-01-01", actualInterOfferById.getStartDate());
        assertEquals("Dr", actualInterOfferById.getTitle());
        assertEquals("Entreprise", actualInterOfferById.getEmployeurEntreprise());
        assertEquals("Location", actualInterOfferById.getLocation());
        assertEquals("Nom", actualInterOfferById.getEmployeurNom());
        assertEquals("Nom", actualInterOfferById.getProgrammeNom());
        assertEquals("Prenom", actualInterOfferById.getEmployeurPrenom());
        assertEquals("Session", actualInterOfferById.getSession());
        assertEquals("The characteristics of someone or something", actualInterOfferById.getDescription());
        FileDto file3 = actualInterOfferById.getFile();
        assertEquals("foo.txt", file3.getFileName());
        assertEquals(10.0d, actualInterOfferById.getSalaryByHour());
        assertEquals(1L, file3.getId());
        assertEquals(1L, actualInterOfferById.getEmployeurId());
        assertEquals(1L, actualInterOfferById.getId());
        assertEquals(1L, actualInterOfferById.getOfferReviewRequestId());
        assertEquals(1L, actualInterOfferById.getProgrammeId());
        assertEquals(State.ACCEPTED, file3.getIsAccepted());
        assertEquals(State.ACCEPTED, actualInterOfferById.getState());
        assertEquals(internOffers, actualInterOfferById.getInternshipCandidates());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file3.getContent());
    }

    /**
     * Method under test: {@link InternOfferService#getInterOfferById(Long)}
     */
    @Test
    void testGetInterOfferById2() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);

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

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(new Programme());
        employeur2.setRole(Role.employer);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(new Etudiant());
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(new InternshipCandidates());
        file2.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(new InternOffer());
        offerReviewRequest.setInternshipmanager(new Internshipmanager());

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur2);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file2);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setSession("Session");
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme4);
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(internOffer2);
        offerReviewRequest2.setInternshipmanager(internshipmanager);

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");
        InternOffer internOffer3 = mock(InternOffer.class);
        when(internOffer3.getInternshipCandidates()).thenThrow(new OfferNotFoundException("foo"));
        when(internOffer3.getEndDate()).thenReturn(LocalDate.of(1970, 1, 1));
        when(internOffer3.getDescription()).thenReturn("The characteristics of someone or something");
        when(internOffer3.getLocation()).thenReturn("Location");
        when(internOffer3.getTitle()).thenReturn("Dr");
        when(internOffer3.getStartDate()).thenReturn(LocalDate.of(1970, 1, 1));
        when(internOffer3.getId()).thenReturn(1L);
        doNothing().when(internOffer3).setDescription(Mockito.<String>any());
        doNothing().when(internOffer3).setEmployeur(Mockito.<Employeur>any());
        doNothing().when(internOffer3).setEndDate(Mockito.<LocalDate>any());
        doNothing().when(internOffer3).setFile(Mockito.<File>any());
        doNothing().when(internOffer3).setId(anyLong());
        doNothing().when(internOffer3).setInternshipCandidates(Mockito.<List<InternshipCandidates>>any());
        doNothing().when(internOffer3).setLocation(Mockito.<String>any());
        doNothing().when(internOffer3).setOfferReviewRequest(Mockito.<OfferReviewRequest>any());
        doNothing().when(internOffer3).setProgramme(Mockito.<Programme>any());
        doNothing().when(internOffer3).setSalaryByHour(anyDouble());
        doNothing().when(internOffer3).setSession(Mockito.<String>any());
        doNothing().when(internOffer3).setStartDate(Mockito.<LocalDate>any());
        doNothing().when(internOffer3).setState(Mockito.<State>any());
        doNothing().when(internOffer3).setStatus(Mockito.<String>any());
        doNothing().when(internOffer3).setTitle(Mockito.<String>any());
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest2);
        internOffer3.setProgramme(programme5);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setSession("Session");
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");
        Optional<InternOffer> ofResult = Optional.of(internOffer3);
        when(internOfferRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(OfferNotFoundException.class, () -> internOfferService.getInterOfferById(1L));
        verify(internOffer3).getDescription();
        verify(internOffer3).getEndDate();
        verify(internOffer3).getId();
        verify(internOffer3).getInternshipCandidates();
        verify(internOffer3).getLocation();
        verify(internOffer3).getStartDate();
        verify(internOffer3).getTitle();
        verify(internOffer3).setDescription(Mockito.<String>any());
        verify(internOffer3).setEmployeur(Mockito.<Employeur>any());
        verify(internOffer3).setEndDate(Mockito.<LocalDate>any());
        verify(internOffer3).setFile(Mockito.<File>any());
        verify(internOffer3).setId(anyLong());
        verify(internOffer3).setInternshipCandidates(Mockito.<List<InternshipCandidates>>any());
        verify(internOffer3).setLocation(Mockito.<String>any());
        verify(internOffer3).setOfferReviewRequest(Mockito.<OfferReviewRequest>any());
        verify(internOffer3).setProgramme(Mockito.<Programme>any());
        verify(internOffer3).setSalaryByHour(anyDouble());
        verify(internOffer3).setSession(Mockito.<String>any());
        verify(internOffer3).setStartDate(Mockito.<LocalDate>any());
        verify(internOffer3).setState(Mockito.<State>any());
        verify(internOffer3).setStatus(Mockito.<String>any());
        verify(internOffer3).setTitle(Mockito.<String>any());
        verify(internOfferRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link InternOfferService#getCountByState(String)}
     */
    @Test
    void testGetCountByState() {
        when(internOfferRepository.getCountByState(Mockito.<String>any())).thenReturn(new ArrayList<>());
        Map<String, Long> actualCountByState = internOfferService.getCountByState("Session");
        verify(internOfferRepository).getCountByState(Mockito.<String>any());
        assertEquals(4, actualCountByState.size());
        Long expectedGetResult = new Long(0L);
        assertEquals(expectedGetResult, actualCountByState.get("ACCEPTED"));
        Long expectedGetResult2 = new Long(0L);
        assertEquals(expectedGetResult2, actualCountByState.get("DECLINED"));
        Long expectedGetResult3 = new Long(0L);
        assertEquals(expectedGetResult3, actualCountByState.get("PENDING"));
        Long expectedGetResult4 = new Long(0L);
        assertEquals(expectedGetResult4, actualCountByState.get("TOTAL"));
    }

    /**
     * Method under test: {@link InternOfferService#getCountByState(String)}
     */
    @Test
    void testGetCountByState2() {
        ArrayList<Object[]> objectArrayList = new ArrayList<>();
        objectArrayList.add(new Object[]{"42"});
        when(internOfferRepository.getCountByState(Mockito.<String>any())).thenReturn(objectArrayList);
        assertThrows(ServiceException.class, () -> internOfferService.getCountByState("Session"));
        verify(internOfferRepository).getCountByState(Mockito.<String>any());
    }

    /**
     * Method under test: {@link InternOfferService#getCountByState(String)}
     */
    @Test
    void testGetCountByState3() {
        when(internOfferRepository.getCountByState(Mockito.<String>any()))
                .thenThrow(new OfferAlreadyReviewException("An error occurred"));
        assertThrows(ServiceException.class, () -> internOfferService.getCountByState("Session"));
        verify(internOfferRepository).getCountByState(Mockito.<String>any());
    }

    /**
     * Method under test: {@link InternOfferService#getCountByState(String)}
     */
    @Test
    void testGetCountByState4() {
        when(internOfferRepository.getCountByState(Mockito.<String>any())).thenThrow(new EmptyResultDataAccessException(3));
        assertThrows(DatabaseException.class, () -> internOfferService.getCountByState("Session"));
        verify(internOfferRepository).getCountByState(Mockito.<String>any());
    }

    /**
     * Method under test: {@link InternOfferService#getInternOffer()}
     */
    @Test
    void testGetInternOffer() {
        when(internOfferRepository.findAll()).thenReturn(new ArrayList<>());
        List<InternOfferDto> actualInternOffer = internOfferService.getInternOffer();
        verify(internOfferRepository).findAll();
        assertTrue(actualInternOffer.isEmpty());
    }

    /**
     * Method under test: {@link InternOfferService#getInternOffer()}
     */
    @Test
    void testGetInternOffer2() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);

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

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(new Programme());
        employeur2.setRole(Role.employer);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(new Etudiant());
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(new InternshipCandidates());
        file2.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(new InternOffer());
        offerReviewRequest.setInternshipmanager(new Internshipmanager());

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur2);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file2);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setSession("Session");
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme4);
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(internOffer2);
        offerReviewRequest2.setInternshipmanager(internshipmanager);

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest2);
        internOffer3.setProgramme(programme5);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setSession("Session");
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

        ArrayList<InternOffer> internOfferList = new ArrayList<>();
        internOfferList.add(internOffer3);
        when(internOfferRepository.findAll()).thenReturn(internOfferList);
        List<InternOfferDto> actualInternOffer = internOfferService.getInternOffer();
        verify(internOfferRepository).findAll();
        assertEquals(1, actualInternOffer.size());
    }

    /**
     * Method under test: {@link InternOfferService#getInternOffer()}
     */
    @Test
    void testGetInternOffer3() {
        when(internOfferRepository.findAll()).thenThrow(new OfferAlreadyReviewException("An error occurred"));
        assertThrows(OfferAlreadyReviewException.class, () -> internOfferService.getInternOffer());
        verify(internOfferRepository).findAll();
    }

    /**
     * Method under test: {@link InternOfferService#getById(Long)}
     */
    @Test
    void testGetById() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        ArrayList<InternOffer> internOffers = new ArrayList<>();
        employeur.setInternOffers(internOffers);
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);

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

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(new Programme());
        employeur2.setRole(Role.employer);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(new Etudiant());
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(new InternshipCandidates());
        file2.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(new InternOffer());
        offerReviewRequest.setInternshipmanager(new Internshipmanager());

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur2);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file2);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setSession("Session");
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme4);
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(internOffer2);
        offerReviewRequest2.setInternshipmanager(internshipmanager);

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest2);
        internOffer3.setProgramme(programme5);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setSession("Session");
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");
        Optional<InternOffer> ofResult = Optional.of(internOffer3);
        when(internOfferRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        InternOfferDto actualById = internOfferService.getById(1L);
        verify(internOfferRepository).findById(Mockito.<Long>any());
        assertEquals("1970-01-01", actualById.getEndDate());
        assertEquals("1970-01-01", actualById.getStartDate());
        assertEquals("Dr", actualById.getTitle());
        assertEquals("Entreprise", actualById.getEmployeurEntreprise());
        assertEquals("Location", actualById.getLocation());
        assertEquals("Nom", actualById.getEmployeurNom());
        assertEquals("Nom", actualById.getProgrammeNom());
        assertEquals("Prenom", actualById.getEmployeurPrenom());
        assertEquals("Session", actualById.getSession());
        assertEquals("The characteristics of someone or something", actualById.getDescription());
        FileDto file3 = actualById.getFile();
        assertEquals("foo.txt", file3.getFileName());
        assertEquals(10.0d, actualById.getSalaryByHour());
        assertEquals(1L, file3.getId());
        assertEquals(1L, actualById.getEmployeurId());
        assertEquals(1L, actualById.getId());
        assertEquals(1L, actualById.getOfferReviewRequestId());
        assertEquals(1L, actualById.getProgrammeId());
        assertEquals(State.ACCEPTED, file3.getIsAccepted());
        assertEquals(State.ACCEPTED, actualById.getState());
        assertEquals(internOffers, actualById.getInternshipCandidates());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file3.getContent());
    }

    /**
     * Method under test: {@link InternOfferService#getById(Long)}
     */
    @Test
    void testGetById2() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);

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

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(new Programme());
        employeur2.setRole(Role.employer);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(new Etudiant());
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(new InternshipCandidates());
        file2.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(new InternOffer());
        offerReviewRequest.setInternshipmanager(new Internshipmanager());

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur2);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file2);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setSession("Session");
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme4);
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(internOffer2);
        offerReviewRequest2.setInternshipmanager(internshipmanager);

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");
        InternOffer internOffer3 = mock(InternOffer.class);
        when(internOffer3.getInternshipCandidates()).thenThrow(new OfferNotFoundException("foo"));
        when(internOffer3.getEndDate()).thenReturn(LocalDate.of(1970, 1, 1));
        when(internOffer3.getDescription()).thenReturn("The characteristics of someone or something");
        when(internOffer3.getLocation()).thenReturn("Location");
        when(internOffer3.getTitle()).thenReturn("Dr");
        when(internOffer3.getStartDate()).thenReturn(LocalDate.of(1970, 1, 1));
        when(internOffer3.getId()).thenReturn(1L);
        doNothing().when(internOffer3).setDescription(Mockito.<String>any());
        doNothing().when(internOffer3).setEmployeur(Mockito.<Employeur>any());
        doNothing().when(internOffer3).setEndDate(Mockito.<LocalDate>any());
        doNothing().when(internOffer3).setFile(Mockito.<File>any());
        doNothing().when(internOffer3).setId(anyLong());
        doNothing().when(internOffer3).setInternshipCandidates(Mockito.<List<InternshipCandidates>>any());
        doNothing().when(internOffer3).setLocation(Mockito.<String>any());
        doNothing().when(internOffer3).setOfferReviewRequest(Mockito.<OfferReviewRequest>any());
        doNothing().when(internOffer3).setProgramme(Mockito.<Programme>any());
        doNothing().when(internOffer3).setSalaryByHour(anyDouble());
        doNothing().when(internOffer3).setSession(Mockito.<String>any());
        doNothing().when(internOffer3).setStartDate(Mockito.<LocalDate>any());
        doNothing().when(internOffer3).setState(Mockito.<State>any());
        doNothing().when(internOffer3).setStatus(Mockito.<String>any());
        doNothing().when(internOffer3).setTitle(Mockito.<String>any());
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest2);
        internOffer3.setProgramme(programme5);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setSession("Session");
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");
        Optional<InternOffer> ofResult = Optional.of(internOffer3);
        when(internOfferRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(OfferNotFoundException.class, () -> internOfferService.getById(1L));
        verify(internOffer3).getDescription();
        verify(internOffer3).getEndDate();
        verify(internOffer3).getId();
        verify(internOffer3).getInternshipCandidates();
        verify(internOffer3).getLocation();
        verify(internOffer3).getStartDate();
        verify(internOffer3).getTitle();
        verify(internOffer3).setDescription(Mockito.<String>any());
        verify(internOffer3).setEmployeur(Mockito.<Employeur>any());
        verify(internOffer3).setEndDate(Mockito.<LocalDate>any());
        verify(internOffer3).setFile(Mockito.<File>any());
        verify(internOffer3).setId(anyLong());
        verify(internOffer3).setInternshipCandidates(Mockito.<List<InternshipCandidates>>any());
        verify(internOffer3).setLocation(Mockito.<String>any());
        verify(internOffer3).setOfferReviewRequest(Mockito.<OfferReviewRequest>any());
        verify(internOffer3).setProgramme(Mockito.<Programme>any());
        verify(internOffer3).setSalaryByHour(anyDouble());
        verify(internOffer3).setSession(Mockito.<String>any());
        verify(internOffer3).setStartDate(Mockito.<LocalDate>any());
        verify(internOffer3).setState(Mockito.<State>any());
        verify(internOffer3).setStatus(Mockito.<String>any());
        verify(internOffer3).setTitle(Mockito.<String>any());
        verify(internOfferRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link InternOfferService#getEmployeurOfferBySeason(String, String)}
     */
    @Test
    void testGetEmployeurOfferBySeason() {
        when(internOfferRepository.findInternOffersSeasonById(Mockito.<String>any(), Mockito.<Long>any()))
                .thenReturn(new ArrayList<>());

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);
        Optional<Employeur> ofResult = Optional.of(employeur);
        when(employeurRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        List<InternOfferDto> actualEmployeurOfferBySeason = internOfferService.getEmployeurOfferBySeason("Selected Option",
                "jane.doe@example.org");
        verify(employeurRepository).findByEmail(Mockito.<String>any());
        verify(internOfferRepository).findInternOffersSeasonById(Mockito.<String>any(), Mockito.<Long>any());
        assertTrue(actualEmployeurOfferBySeason.isEmpty());
    }

    /**
     * Method under test: {@link InternOfferService#getEmployeurOfferBySeason(String, String)}
     */
    @Test
    void testGetEmployeurOfferBySeason2() {
        when(internOfferRepository.findInternOffersSeasonById(Mockito.<String>any(), Mockito.<Long>any()))
                .thenThrow(new OfferAlreadyReviewException("An error occurred"));

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);
        Optional<Employeur> ofResult = Optional.of(employeur);
        when(employeurRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        assertThrows(OfferAlreadyReviewException.class,
                () -> internOfferService.getEmployeurOfferBySeason("Selected Option", "jane.doe@example.org"));
        verify(employeurRepository).findByEmail(Mockito.<String>any());
        verify(internOfferRepository).findInternOffersSeasonById(Mockito.<String>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link InternOfferService#getEmployeurOfferBySeason(String, String)}
     */
    @Test
    void testGetEmployeurOfferBySeason3() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);

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

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(new Programme());
        employeur2.setRole(Role.employer);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(new Etudiant());
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(new InternshipCandidates());
        file2.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(new InternOffer());
        offerReviewRequest.setInternshipmanager(new Internshipmanager());

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur2);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file2);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setSession("Session");
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme4);
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(internOffer2);
        offerReviewRequest2.setInternshipmanager(internshipmanager);

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest2);
        internOffer3.setProgramme(programme5);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setSession("Session");
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

        ArrayList<InternOffer> internOfferList = new ArrayList<>();
        internOfferList.add(internOffer3);
        when(internOfferRepository.findInternOffersSeasonById(Mockito.<String>any(), Mockito.<Long>any()))
                .thenReturn(internOfferList);

        Programme programme6 = new Programme();
        programme6.setDescription("The characteristics of someone or something");
        programme6.setId(1L);
        programme6.setNom("Nom");

        Employeur employeur3 = new Employeur();
        employeur3.setEmail("jane.doe@example.org");
        employeur3.setEntreprise("Entreprise");
        employeur3.setId(1L);
        employeur3.setInternOffers(new ArrayList<>());
        employeur3.setNom("Nom");
        employeur3.setPassword("iloveyou");
        employeur3.setPhone("6625550144");
        employeur3.setPrenom("Prenom");
        employeur3.setProgramme(programme6);
        employeur3.setRole(Role.employer);
        Optional<Employeur> ofResult = Optional.of(employeur3);
        when(employeurRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        List<InternOfferDto> actualEmployeurOfferBySeason = internOfferService.getEmployeurOfferBySeason("Selected Option",
                "jane.doe@example.org");
        verify(employeurRepository).findByEmail(Mockito.<String>any());
        verify(internOfferRepository).findInternOffersSeasonById(Mockito.<String>any(), Mockito.<Long>any());
        assertEquals(1, actualEmployeurOfferBySeason.size());
    }

    /**
     * Method under test: {@link InternOfferService#getEmployeurSeasonsOffers(String)}
     */
    @Test
    void testGetEmployeurSeasonsOffers() {
        ArrayList<String> stringList = new ArrayList<>();
        when(internOfferRepository.findEmployeurOffersSeasons(Mockito.<Long>any())).thenReturn(stringList);

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);
        Optional<Employeur> ofResult = Optional.of(employeur);
        when(employeurRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        List<String> actualEmployeurSeasonsOffers = internOfferService.getEmployeurSeasonsOffers("jane.doe@example.org");
        verify(employeurRepository).findByEmail(Mockito.<String>any());
        verify(internOfferRepository).findEmployeurOffersSeasons(Mockito.<Long>any());
        assertTrue(actualEmployeurSeasonsOffers.isEmpty());
        assertSame(stringList, actualEmployeurSeasonsOffers);
    }

    /**
     * Method under test: {@link InternOfferService#getEmployeurSeasonsOffers(String)}
     */
    @Test
    void testGetEmployeurSeasonsOffers2() {
        when(internOfferRepository.findEmployeurOffersSeasons(Mockito.<Long>any()))
                .thenThrow(new OfferAlreadyReviewException("An error occurred"));

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);
        Optional<Employeur> ofResult = Optional.of(employeur);
        when(employeurRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        assertThrows(OfferAlreadyReviewException.class,
                () -> internOfferService.getEmployeurSeasonsOffers("jane.doe@example.org"));
        verify(employeurRepository).findByEmail(Mockito.<String>any());
        verify(internOfferRepository).findEmployeurOffersSeasons(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link InternOfferService#getAllOffers()}
     */
    @Test
    void testGetAllOffers() {
        when(internOfferRepository.findAll()).thenReturn(new ArrayList<>());
        List<InternOfferDto> actualAllOffers = internOfferService.getAllOffers();
        verify(internOfferRepository).findAll();
        assertTrue(actualAllOffers.isEmpty());
    }

    /**
     * Method under test: {@link InternOfferService#getAllOffers()}
     */
    @Test
    void testGetAllOffers2() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);

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

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(new Programme());
        employeur2.setRole(Role.employer);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(new Etudiant());
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(new InternshipCandidates());
        file2.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(new InternOffer());
        offerReviewRequest.setInternshipmanager(new Internshipmanager());

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur2);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file2);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setSession("Session");
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme4);
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(internOffer2);
        offerReviewRequest2.setInternshipmanager(internshipmanager);

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest2);
        internOffer3.setProgramme(programme5);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setSession("Session");
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

        ArrayList<InternOffer> internOfferList = new ArrayList<>();
        internOfferList.add(internOffer3);
        when(internOfferRepository.findAll()).thenReturn(internOfferList);
        List<InternOfferDto> actualAllOffers = internOfferService.getAllOffers();
        verify(internOfferRepository).findAll();
        assertEquals(1, actualAllOffers.size());
    }

    /**
     * Method under test: {@link InternOfferService#getAllOffers()}
     */
    @Test
    void testGetAllOffers3() {
        when(internOfferRepository.findAll()).thenThrow(new OfferAlreadyReviewException("An error occurred"));
        assertThrows(OfferAlreadyReviewException.class, () -> internOfferService.getAllOffers());
        verify(internOfferRepository).findAll();
    }
}
