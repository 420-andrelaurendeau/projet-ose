package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sap.ose.projetose.dto.EtudiantDtoWithId;
import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.InternshipCandidatesDto;
import com.sap.ose.projetose.exception.DatabaseException;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {InternshipCandidatesService.class})
@ExtendWith(SpringExtension.class)
class InternshipCandidatesServiceTest {
    @MockBean
    private EtudiantService etudiantService;

    @MockBean
    private FileService fileService;

    @MockBean
    private InternOfferService internOfferService;

    @MockBean
    private InternshipCandidatesRepository internshipCandidatesRepository;

    @Autowired
    private InternshipCandidatesService internshipCandidatesService;

    /**
     * Method under test: {@link InternshipCandidatesService#saveCandidates(InternshipCandidates)}
     */
    @Test
    void testSaveCandidates() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        etudiant.setCv(new File());
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

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
        etudiant2.setEmail("jane.doe@example.org");
        etudiant2.setId(1L);
        etudiant2.setInternshipsCandidate(new ArrayList<>());
        etudiant2.setMatricule("Matricule");
        etudiant2.setNom("Nom");
        etudiant2.setPassword("iloveyou");
        etudiant2.setPhone("6625550144");
        etudiant2.setPrenom("Prenom");
        etudiant2.setProgramme(programme3);
        etudiant2.setRole(Role.employer);

        Etudiant etudiant3 = new Etudiant();
        etudiant3.setCv(new File());
        etudiant3.setEmail("jane.doe@example.org");
        etudiant3.setId(1L);
        etudiant3.setInternshipsCandidate(new ArrayList<>());
        etudiant3.setMatricule("Matricule");
        etudiant3.setNom("Nom");
        etudiant3.setPassword("iloveyou");
        etudiant3.setPhone("6625550144");
        etudiant3.setPrenom("Prenom");
        etudiant3.setProgramme(new Programme());
        etudiant3.setRole(Role.employer);

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
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(etudiant3);
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(internOffer);
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
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

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur2);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file2);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme4);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(programme5);
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(internOffer2);
        offerReviewRequest2.setInternshipmanager(internshipmanager);

        Programme programme6 = new Programme();
        programme6.setDescription("The characteristics of someone or something");
        programme6.setId(1L);
        programme6.setNom("Nom");

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest2);
        internOffer3.setProgramme(programme6);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer3);
        internshipCandidates2.setState(State.ACCEPTED);
        when(internshipCandidatesRepository.save(Mockito.<InternshipCandidates>any())).thenReturn(internshipCandidates2);

        Programme programme7 = new Programme();
        programme7.setDescription("The characteristics of someone or something");
        programme7.setId(1L);
        programme7.setNom("Nom");

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setCv(new File());
        etudiant4.setEmail("jane.doe@example.org");
        etudiant4.setId(1L);
        etudiant4.setInternshipsCandidate(new ArrayList<>());
        etudiant4.setMatricule("Matricule");
        etudiant4.setNom("Nom");
        etudiant4.setPassword("iloveyou");
        etudiant4.setPhone("6625550144");
        etudiant4.setPrenom("Prenom");
        etudiant4.setProgramme(programme7);
        etudiant4.setRole(Role.employer);

        Programme programme8 = new Programme();
        programme8.setDescription("The characteristics of someone or something");
        programme8.setId(1L);
        programme8.setNom("Nom");

        Employeur employeur3 = new Employeur();
        employeur3.setEmail("jane.doe@example.org");
        employeur3.setEntreprise("Entreprise");
        employeur3.setId(1L);
        employeur3.setInternOffers(new ArrayList<>());
        employeur3.setNom("Nom");
        employeur3.setPassword("iloveyou");
        employeur3.setPhone("6625550144");
        employeur3.setPrenom("Prenom");
        employeur3.setProgramme(programme8);
        employeur3.setRole(Role.employer);

        Etudiant etudiant5 = new Etudiant();
        etudiant5.setCv(new File());
        etudiant5.setEmail("jane.doe@example.org");
        etudiant5.setId(1L);
        etudiant5.setInternshipsCandidate(new ArrayList<>());
        etudiant5.setMatricule("Matricule");
        etudiant5.setNom("Nom");
        etudiant5.setPassword("iloveyou");
        etudiant5.setPhone("6625550144");
        etudiant5.setPrenom("Prenom");
        etudiant5.setProgramme(new Programme());
        etudiant5.setRole(Role.employer);

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(new Etudiant());
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(1L);
        internshipCandidates3.setInternOffer(new InternOffer());
        internshipCandidates3.setState(State.ACCEPTED);

        File file3 = new File();
        file3.setContent("AXAXAXAX".getBytes("UTF-8"));
        file3.setEtudiant(etudiant5);
        file3.setFileName("foo.txt");
        file3.setId(1L);
        file3.setInternshipCandidates(internshipCandidates3);
        file3.setIsAccepted(State.ACCEPTED);

        InternOffer internOffer4 = new InternOffer();
        internOffer4.setDescription("The characteristics of someone or something");
        internOffer4.setEmployeur(new Employeur());
        internOffer4.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer4.setFile(new File());
        internOffer4.setId(1L);
        internOffer4.setInternshipCandidates(new ArrayList<>());
        internOffer4.setLocation("Location");
        internOffer4.setOfferReviewRequest(new OfferReviewRequest());
        internOffer4.setProgramme(new Programme());
        internOffer4.setSalaryByHour(10.0d);
        internOffer4.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer4.setState(State.ACCEPTED);
        internOffer4.setStatus("Status");
        internOffer4.setTitle("Dr");

        Internshipmanager internshipmanager2 = new Internshipmanager();
        internshipmanager2.setEmail("jane.doe@example.org");
        internshipmanager2.setId(1L);
        internshipmanager2.setNom("Nom");
        internshipmanager2.setPassword("iloveyou");
        internshipmanager2.setPhone("6625550144");
        internshipmanager2.setPrenom("Prenom");
        internshipmanager2.setProgramme(new Programme());
        internshipmanager2.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest3 = new OfferReviewRequest();
        offerReviewRequest3.setComment("Comment");
        offerReviewRequest3.setId(1L);
        offerReviewRequest3.setInternOffer(internOffer4);
        offerReviewRequest3.setInternshipmanager(internshipmanager2);

        Programme programme9 = new Programme();
        programme9.setDescription("The characteristics of someone or something");
        programme9.setId(1L);
        programme9.setNom("Nom");

        InternOffer internOffer5 = new InternOffer();
        internOffer5.setDescription("The characteristics of someone or something");
        internOffer5.setEmployeur(employeur3);
        internOffer5.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer5.setFile(file3);
        internOffer5.setId(1L);
        internOffer5.setInternshipCandidates(new ArrayList<>());
        internOffer5.setLocation("Location");
        internOffer5.setOfferReviewRequest(offerReviewRequest3);
        internOffer5.setProgramme(programme9);
        internOffer5.setSalaryByHour(10.0d);
        internOffer5.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer5.setState(State.ACCEPTED);
        internOffer5.setStatus("Status");
        internOffer5.setTitle("Dr");

        InternshipCandidates internshipCandidates4 = new InternshipCandidates();
        internshipCandidates4.setEtudiant(etudiant4);
        internshipCandidates4.setFiles(new ArrayList<>());
        internshipCandidates4.setId(1L);
        internshipCandidates4.setInternOffer(internOffer5);
        internshipCandidates4.setState(State.ACCEPTED);
        InternshipCandidatesDto actualSaveCandidatesResult = internshipCandidatesService
                .saveCandidates(internshipCandidates4);
        verify(internshipCandidatesRepository).save(Mockito.<InternshipCandidates>any());
        InternOfferDto internOfferJob = actualSaveCandidatesResult.getInternOfferJob();
        assertEquals("1970-01-01", internOfferJob.getEndDate());
        assertEquals("1970-01-01", internOfferJob.getStartDate());
        EtudiantDtoWithId etudiant6 = actualSaveCandidatesResult.getEtudiant();
        assertEquals("6625550144", etudiant6.getPhone());
        assertEquals("Dr", internOfferJob.getTitle());
        assertEquals("Entreprise", internOfferJob.getEmployeurEntreprise());
        assertEquals("Location", internOfferJob.getLocation());
        assertEquals("Matricule", etudiant6.getMatricule());
        assertEquals("Nom", internOfferJob.getEmployeurNom());
        assertEquals("Nom", internOfferJob.getProgrammeNom());
        assertEquals("Nom", etudiant6.getNom());
        assertEquals("Prenom", internOfferJob.getEmployeurPrenom());
        assertEquals("Prenom", etudiant6.getPrenom());
        assertEquals("The characteristics of someone or something", internOfferJob.getDescription());
        FileDto file4 = internOfferJob.getFile();
        assertEquals("foo.txt", file4.getFileName());
        assertEquals("jane.doe@example.org", etudiant6.getEmail());
        assertEquals(1L, etudiant6.getProgramme_id());
        assertEquals(1L, file4.getId());
        assertEquals(1L, internOfferJob.getEmployeurId());
        assertEquals(1L, internOfferJob.getId());
        assertEquals(1L, internOfferJob.getOfferReviewRequestId());
        assertEquals(1L, internOfferJob.getProgrammeId());
        assertEquals(1L, actualSaveCandidatesResult.getId());
        assertEquals(1L, etudiant6.getId());
        assertEquals(State.ACCEPTED, file4.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferJob.getState());
        assertEquals(State.ACCEPTED, actualSaveCandidatesResult.getState());
        List<FileDto> files = actualSaveCandidatesResult.getFiles();
        assertTrue(files.isEmpty());
        assertEquals(new File(), internOfferJob.getInternshipCandidates());
        assertSame(files, etudiant6.getCv());
        assertSame(files, etudiant6.getInternships_id());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file4.getContent());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getInternshipCandidatesByOfferId(Long)}
     */
    @Test
    void testGetInternshipCandidatesByOfferId() {
        when(internshipCandidatesRepository.findAllByInternOfferId(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        List<InternshipCandidatesDto> actualInternshipCandidatesByOfferId = internshipCandidatesService
                .getInternshipCandidatesByOfferId(1L);
        verify(internshipCandidatesRepository).findAllByInternOfferId(Mockito.<Long>any());
        assertTrue(actualInternshipCandidatesByOfferId.isEmpty());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getInternshipCandidatesByOfferId(Long)}
     */
    @Test
    void testGetInternshipCandidatesByOfferId2() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        ArrayList<File> cv = new ArrayList<>();
        etudiant.setCv(new File());
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

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
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

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

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
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer2);
        internshipCandidates2.setState(State.ACCEPTED);

        ArrayList<InternshipCandidates> internshipCandidatesList = new ArrayList<>();
        internshipCandidatesList.add(internshipCandidates2);
        when(internshipCandidatesRepository.findAllByInternOfferId(Mockito.<Long>any()))
                .thenReturn(internshipCandidatesList);
        List<InternshipCandidatesDto> actualInternshipCandidatesByOfferId = internshipCandidatesService
                .getInternshipCandidatesByOfferId(1L);
        verify(internshipCandidatesRepository).findAllByInternOfferId(Mockito.<Long>any());
        InternshipCandidatesDto getResult = actualInternshipCandidatesByOfferId.get(0);
        InternOfferDto internOfferJob = getResult.getInternOfferJob();
        assertEquals("1970-01-01", internOfferJob.getEndDate());
        assertEquals("1970-01-01", internOfferJob.getStartDate());
        EtudiantDtoWithId etudiant3 = getResult.getEtudiant();
        assertEquals("6625550144", etudiant3.getPhone());
        assertEquals("Dr", internOfferJob.getTitle());
        assertEquals("Entreprise", internOfferJob.getEmployeurEntreprise());
        assertEquals("Location", internOfferJob.getLocation());
        assertEquals("Matricule", etudiant3.getMatricule());
        assertEquals("Nom", internOfferJob.getEmployeurNom());
        assertEquals("Nom", internOfferJob.getProgrammeNom());
        assertEquals("Nom", etudiant3.getNom());
        assertEquals("Prenom", internOfferJob.getEmployeurPrenom());
        assertEquals("Prenom", etudiant3.getPrenom());
        assertEquals("The characteristics of someone or something", internOfferJob.getDescription());
        FileDto file2 = internOfferJob.getFile();
        assertEquals("foo.txt", file2.getFileName());
        assertEquals("jane.doe@example.org", etudiant3.getEmail());
        assertEquals(1, actualInternshipCandidatesByOfferId.size());
        assertEquals(1L, etudiant3.getProgramme_id());
        assertEquals(1L, file2.getId());
        assertEquals(1L, internOfferJob.getEmployeurId());
        assertEquals(1L, internOfferJob.getId());
        assertEquals(1L, internOfferJob.getOfferReviewRequestId());
        assertEquals(1L, internOfferJob.getProgrammeId());
        assertEquals(1L, getResult.getId());
        assertEquals(1L, etudiant3.getId());
        assertEquals(State.ACCEPTED, file2.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferJob.getState());
        assertEquals(State.ACCEPTED, getResult.getState());
        List<FileDto> files = getResult.getFiles();
        assertTrue(files.isEmpty());
        assertEquals(cv, internOfferJob.getInternshipCandidates());
        assertSame(files, etudiant3.getCv());
        assertSame(files, etudiant3.getInternships_id());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file2.getContent());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getInternshipCandidatesByOfferId(Long)}
     */
    @Test
    void testGetInternshipCandidatesByOfferId3() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        etudiant.setCv(new File());
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

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
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

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

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
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer2);
        internshipCandidates2.setState(State.ACCEPTED);

        Programme programme4 = new Programme();
        programme4.setDescription("Description");
        programme4.setId(2L);
        programme4.setNom("com.sap.ose.projetose.modeles.Programme");

        Etudiant etudiant3 = new Etudiant();
        ArrayList<File> cv = new ArrayList<>();
        etudiant3.setCv(new File());
        etudiant3.setEmail("john.smith@example.org");
        etudiant3.setId(2L);
        etudiant3.setInternshipsCandidate(new ArrayList<>());
        etudiant3.setMatricule("com.sap.ose.projetose.modeles.Etudiant");
        etudiant3.setNom("com.sap.ose.projetose.modeles.Etudiant");
        etudiant3.setPassword("Password");
        etudiant3.setPhone("8605550118");
        etudiant3.setPrenom("com.sap.ose.projetose.modeles.Etudiant");
        etudiant3.setProgramme(programme4);
        etudiant3.setRole(Role.student);

        Programme programme5 = new Programme();
        programme5.setDescription("Description");
        programme5.setId(2L);
        programme5.setNom("com.sap.ose.projetose.modeles.Programme");

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("john.smith@example.org");
        employeur2.setEntreprise("com.sap.ose.projetose.modeles.Employeur");
        employeur2.setId(2L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("com.sap.ose.projetose.modeles.Employeur");
        employeur2.setPassword("Password");
        employeur2.setPhone("8605550118");
        employeur2.setPrenom("com.sap.ose.projetose.modeles.Employeur");
        employeur2.setProgramme(programme5);
        employeur2.setRole(Role.student);

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setCv(new File());
        etudiant4.setEmail("john.smith@example.org");
        etudiant4.setId(2L);
        etudiant4.setInternshipsCandidate(new ArrayList<>());
        etudiant4.setMatricule("com.sap.ose.projetose.modeles.Etudiant");
        etudiant4.setNom("com.sap.ose.projetose.modeles.Etudiant");
        etudiant4.setPassword("Password");
        etudiant4.setPhone("8605550118");
        etudiant4.setPrenom("com.sap.ose.projetose.modeles.Etudiant");
        etudiant4.setProgramme(new Programme());
        etudiant4.setRole(Role.student);

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(new Etudiant());
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(2L);
        internshipCandidates3.setInternOffer(new InternOffer());
        internshipCandidates3.setState(State.PENDING);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(etudiant4);
        file2.setFileName("File Name");
        file2.setId(2L);
        file2.setInternshipCandidates(internshipCandidates3);
        file2.setIsAccepted(State.PENDING);

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("Description");
        internOffer3.setEmployeur(new Employeur());
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(new File());
        internOffer3.setId(2L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("com.sap.ose.projetose.modeles.InternOffer");
        internOffer3.setOfferReviewRequest(new OfferReviewRequest());
        internOffer3.setProgramme(new Programme());
        internOffer3.setSalaryByHour(0.5d);
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.PENDING);
        internOffer3.setStatus("com.sap.ose.projetose.modeles.InternOffer");
        internOffer3.setTitle("Mr");

        Internshipmanager internshipmanager2 = new Internshipmanager();
        internshipmanager2.setEmail("john.smith@example.org");
        internshipmanager2.setId(2L);
        internshipmanager2.setNom("com.sap.ose.projetose.modeles.Internshipmanager");
        internshipmanager2.setPassword("Password");
        internshipmanager2.setPhone("8605550118");
        internshipmanager2.setPrenom("com.sap.ose.projetose.modeles.Internshipmanager");
        internshipmanager2.setProgramme(new Programme());
        internshipmanager2.setRole(Role.student);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("com.sap.ose.projetose.modeles.OfferReviewRequest");
        offerReviewRequest2.setId(2L);
        offerReviewRequest2.setInternOffer(internOffer3);
        offerReviewRequest2.setInternshipmanager(internshipmanager2);

        Programme programme6 = new Programme();
        programme6.setDescription("Description");
        programme6.setId(2L);
        programme6.setNom("com.sap.ose.projetose.modeles.Programme");

        InternOffer internOffer4 = new InternOffer();
        internOffer4.setDescription("Description");
        internOffer4.setEmployeur(employeur2);
        internOffer4.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer4.setFile(file2);
        internOffer4.setId(2L);
        internOffer4.setInternshipCandidates(new ArrayList<>());
        internOffer4.setLocation("com.sap.ose.projetose.modeles.InternOffer");
        internOffer4.setOfferReviewRequest(offerReviewRequest2);
        internOffer4.setProgramme(programme6);
        internOffer4.setSalaryByHour(0.5d);
        internOffer4.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer4.setState(State.PENDING);
        internOffer4.setStatus("com.sap.ose.projetose.modeles.InternOffer");
        internOffer4.setTitle("Mr");

        InternshipCandidates internshipCandidates4 = new InternshipCandidates();
        internshipCandidates4.setEtudiant(etudiant3);
        internshipCandidates4.setFiles(new ArrayList<>());
        internshipCandidates4.setId(2L);
        internshipCandidates4.setInternOffer(internOffer4);
        internshipCandidates4.setState(State.PENDING);

        ArrayList<InternshipCandidates> internshipCandidatesList = new ArrayList<>();
        internshipCandidatesList.add(internshipCandidates4);
        internshipCandidatesList.add(internshipCandidates2);
        when(internshipCandidatesRepository.findAllByInternOfferId(Mockito.<Long>any()))
                .thenReturn(internshipCandidatesList);
        List<InternshipCandidatesDto> actualInternshipCandidatesByOfferId = internshipCandidatesService
                .getInternshipCandidatesByOfferId(1L);
        verify(internshipCandidatesRepository).findAllByInternOfferId(Mockito.<Long>any());
        InternshipCandidatesDto getResult = actualInternshipCandidatesByOfferId.get(0);
        InternOfferDto internOfferJob = getResult.getInternOfferJob();
        assertEquals("1970-01-01", internOfferJob.getEndDate());
        InternshipCandidatesDto getResult2 = actualInternshipCandidatesByOfferId.get(1);
        InternOfferDto internOfferJob2 = getResult2.getInternOfferJob();
        assertEquals("1970-01-01", internOfferJob2.getEndDate());
        assertEquals("1970-01-01", internOfferJob.getStartDate());
        assertEquals("1970-01-01", internOfferJob2.getStartDate());
        EtudiantDtoWithId etudiant5 = getResult2.getEtudiant();
        assertEquals("6625550144", etudiant5.getPhone());
        EtudiantDtoWithId etudiant6 = getResult.getEtudiant();
        assertEquals("8605550118", etudiant6.getPhone());
        assertEquals("Description", internOfferJob.getDescription());
        assertEquals("Dr", internOfferJob2.getTitle());
        assertEquals("Entreprise", internOfferJob2.getEmployeurEntreprise());
        FileDto file3 = internOfferJob.getFile();
        assertEquals("File Name", file3.getFileName());
        assertEquals("Location", internOfferJob2.getLocation());
        assertEquals("Matricule", etudiant5.getMatricule());
        assertEquals("Mr", internOfferJob.getTitle());
        assertEquals("Nom", internOfferJob2.getEmployeurNom());
        assertEquals("Nom", internOfferJob2.getProgrammeNom());
        assertEquals("Nom", etudiant5.getNom());
        assertEquals("Prenom", internOfferJob2.getEmployeurPrenom());
        assertEquals("Prenom", etudiant5.getPrenom());
        assertEquals("The characteristics of someone or something", internOfferJob2.getDescription());
        assertEquals("com.sap.ose.projetose.modeles.Employeur", internOfferJob.getEmployeurEntreprise());
        assertEquals("com.sap.ose.projetose.modeles.Employeur", internOfferJob.getEmployeurNom());
        assertEquals("com.sap.ose.projetose.modeles.Employeur", internOfferJob.getEmployeurPrenom());
        assertEquals("com.sap.ose.projetose.modeles.Etudiant", etudiant6.getMatricule());
        assertEquals("com.sap.ose.projetose.modeles.Etudiant", etudiant6.getNom());
        assertEquals("com.sap.ose.projetose.modeles.Etudiant", etudiant6.getPrenom());
        assertEquals("com.sap.ose.projetose.modeles.InternOffer", internOfferJob.getLocation());
        assertEquals("com.sap.ose.projetose.modeles.Programme", internOfferJob.getProgrammeNom());
        FileDto file4 = internOfferJob2.getFile();
        assertEquals("foo.txt", file4.getFileName());
        assertEquals("jane.doe@example.org", etudiant5.getEmail());
        assertEquals("john.smith@example.org", etudiant6.getEmail());
        assertEquals(1L, etudiant5.getProgramme_id());
        assertEquals(1L, file4.getId());
        assertEquals(1L, internOfferJob2.getEmployeurId());
        assertEquals(1L, internOfferJob2.getId());
        assertEquals(1L, internOfferJob2.getOfferReviewRequestId());
        assertEquals(1L, internOfferJob2.getProgrammeId());
        assertEquals(1L, getResult2.getId());
        assertEquals(1L, etudiant5.getId());
        assertEquals(2, actualInternshipCandidatesByOfferId.size());
        assertEquals(2L, etudiant6.getProgramme_id());
        assertEquals(2L, file3.getId());
        assertEquals(2L, internOfferJob.getEmployeurId());
        assertEquals(2L, internOfferJob.getId());
        assertEquals(2L, internOfferJob.getOfferReviewRequestId());
        assertEquals(2L, internOfferJob.getProgrammeId());
        assertEquals(2L, getResult.getId());
        assertEquals(2L, etudiant6.getId());
        assertEquals(State.ACCEPTED, file4.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferJob2.getState());
        assertEquals(State.ACCEPTED, getResult2.getState());
        assertEquals(State.PENDING, file3.getIsAccepted());
        assertEquals(State.PENDING, internOfferJob.getState());
        assertEquals(State.PENDING, getResult.getState());
        assertTrue(getResult.getFiles().isEmpty());
        List<FileDto> files = getResult2.getFiles();
        assertTrue(files.isEmpty());
        assertEquals(cv, internOfferJob.getInternshipCandidates());
        assertEquals(cv, internOfferJob2.getInternshipCandidates());
        assertSame(files, etudiant6.getCv());
        assertSame(files, etudiant5.getCv());
        assertSame(files, etudiant6.getInternships_id());
        assertSame(files, etudiant5.getInternships_id());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file3.getContent());
        byte[] expectedContent2 = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent2, file4.getContent());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getInternshipCandidatesByOfferId(Long)}
     */
    @Test
    void testGetInternshipCandidatesByOfferId4() {
        when(internshipCandidatesRepository.findAllByInternOfferId(Mockito.<Long>any()))
                .thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> internshipCandidatesService.getInternshipCandidatesByOfferId(1L));
        verify(internshipCandidatesRepository).findAllByInternOfferId(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getInternshipCandidatesByOfferId(Long)}
     */
    @Test
    void testGetInternshipCandidatesByOfferId5() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        etudiant.setCv(new File());
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

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
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

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

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
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Etudiant etudiant3 = new Etudiant();
        ArrayList<File> cv = new ArrayList<>();
        etudiant3.setCv(new File());
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

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(programme5);
        employeur2.setRole(Role.employer);

        Programme programme6 = new Programme();
        programme6.setDescription("The characteristics of someone or something");
        programme6.setId(1L);
        programme6.setNom("Nom");

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setCv(new File());
        etudiant4.setEmail("jane.doe@example.org");
        etudiant4.setId(1L);
        etudiant4.setInternshipsCandidate(new ArrayList<>());
        etudiant4.setMatricule("Matricule");
        etudiant4.setNom("Nom");
        etudiant4.setPassword("iloveyou");
        etudiant4.setPhone("6625550144");
        etudiant4.setPrenom("Prenom");
        etudiant4.setProgramme(programme6);
        etudiant4.setRole(Role.employer);

        Programme programme7 = new Programme();
        programme7.setDescription("The characteristics of someone or something");
        programme7.setId(1L);
        programme7.setNom("Nom");

        Etudiant etudiant5 = new Etudiant();
        etudiant5.setCv(new File());
        etudiant5.setEmail("jane.doe@example.org");
        etudiant5.setId(1L);
        etudiant5.setInternshipsCandidate(new ArrayList<>());
        etudiant5.setMatricule("Matricule");
        etudiant5.setNom("Nom");
        etudiant5.setPassword("iloveyou");
        etudiant5.setPhone("6625550144");
        etudiant5.setPrenom("Prenom");
        etudiant5.setProgramme(programme7);
        etudiant5.setRole(Role.employer);

        Employeur employeur3 = new Employeur();
        employeur3.setEmail("jane.doe@example.org");
        employeur3.setEntreprise("Entreprise");
        employeur3.setId(1L);
        employeur3.setInternOffers(new ArrayList<>());
        employeur3.setNom("Nom");
        employeur3.setPassword("iloveyou");
        employeur3.setPhone("6625550144");
        employeur3.setPrenom("Prenom");
        employeur3.setProgramme(new Programme());
        employeur3.setRole(Role.employer);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(new Etudiant());
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(new InternshipCandidates());
        file2.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(new InternOffer());
        offerReviewRequest2.setInternshipmanager(new Internshipmanager());

        Programme programme8 = new Programme();
        programme8.setDescription("The characteristics of someone or something");
        programme8.setId(1L);
        programme8.setNom("Nom");

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur3);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file2);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest2);
        internOffer3.setProgramme(programme8);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant5);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer3);
        internshipCandidates2.setState(State.ACCEPTED);

        File file3 = new File();
        file3.setContent("AXAXAXAX".getBytes("UTF-8"));
        file3.setEtudiant(etudiant4);
        file3.setFileName("foo.txt");
        file3.setId(1L);
        file3.setInternshipCandidates(internshipCandidates2);
        file3.setIsAccepted(State.ACCEPTED);

        Programme programme9 = new Programme();
        programme9.setDescription("The characteristics of someone or something");
        programme9.setId(1L);
        programme9.setNom("Nom");

        Employeur employeur4 = new Employeur();
        employeur4.setEmail("jane.doe@example.org");
        employeur4.setEntreprise("Entreprise");
        employeur4.setId(1L);
        employeur4.setInternOffers(new ArrayList<>());
        employeur4.setNom("Nom");
        employeur4.setPassword("iloveyou");
        employeur4.setPhone("6625550144");
        employeur4.setPrenom("Prenom");
        employeur4.setProgramme(programme9);
        employeur4.setRole(Role.employer);

        Etudiant etudiant6 = new Etudiant();
        etudiant6.setCv(new File());
        etudiant6.setEmail("jane.doe@example.org");
        etudiant6.setId(1L);
        etudiant6.setInternshipsCandidate(new ArrayList<>());
        etudiant6.setMatricule("Matricule");
        etudiant6.setNom("Nom");
        etudiant6.setPassword("iloveyou");
        etudiant6.setPhone("6625550144");
        etudiant6.setPrenom("Prenom");
        etudiant6.setProgramme(new Programme());
        etudiant6.setRole(Role.employer);

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(new Etudiant());
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(1L);
        internshipCandidates3.setInternOffer(new InternOffer());
        internshipCandidates3.setState(State.ACCEPTED);

        File file4 = new File();
        file4.setContent("AXAXAXAX".getBytes("UTF-8"));
        file4.setEtudiant(etudiant6);
        file4.setFileName("foo.txt");
        file4.setId(1L);
        file4.setInternshipCandidates(internshipCandidates3);
        file4.setIsAccepted(State.ACCEPTED);

        InternOffer internOffer4 = new InternOffer();
        internOffer4.setDescription("The characteristics of someone or something");
        internOffer4.setEmployeur(new Employeur());
        internOffer4.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer4.setFile(new File());
        internOffer4.setId(1L);
        internOffer4.setInternshipCandidates(new ArrayList<>());
        internOffer4.setLocation("Location");
        internOffer4.setOfferReviewRequest(new OfferReviewRequest());
        internOffer4.setProgramme(new Programme());
        internOffer4.setSalaryByHour(10.0d);
        internOffer4.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer4.setState(State.ACCEPTED);
        internOffer4.setStatus("Status");
        internOffer4.setTitle("Dr");

        Internshipmanager internshipmanager2 = new Internshipmanager();
        internshipmanager2.setEmail("jane.doe@example.org");
        internshipmanager2.setId(1L);
        internshipmanager2.setNom("Nom");
        internshipmanager2.setPassword("iloveyou");
        internshipmanager2.setPhone("6625550144");
        internshipmanager2.setPrenom("Prenom");
        internshipmanager2.setProgramme(new Programme());
        internshipmanager2.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest3 = new OfferReviewRequest();
        offerReviewRequest3.setComment("Comment");
        offerReviewRequest3.setId(1L);
        offerReviewRequest3.setInternOffer(internOffer4);
        offerReviewRequest3.setInternshipmanager(internshipmanager2);

        Programme programme10 = new Programme();
        programme10.setDescription("The characteristics of someone or something");
        programme10.setId(1L);
        programme10.setNom("Nom");

        InternOffer internOffer5 = new InternOffer();
        internOffer5.setDescription("The characteristics of someone or something");
        internOffer5.setEmployeur(employeur4);
        internOffer5.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer5.setFile(file4);
        internOffer5.setId(1L);
        internOffer5.setInternshipCandidates(new ArrayList<>());
        internOffer5.setLocation("Location");
        internOffer5.setOfferReviewRequest(offerReviewRequest3);
        internOffer5.setProgramme(programme10);
        internOffer5.setSalaryByHour(10.0d);
        internOffer5.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer5.setState(State.ACCEPTED);
        internOffer5.setStatus("Status");
        internOffer5.setTitle("Dr");

        Programme programme11 = new Programme();
        programme11.setDescription("The characteristics of someone or something");
        programme11.setId(1L);
        programme11.setNom("Nom");

        Internshipmanager internshipmanager3 = new Internshipmanager();
        internshipmanager3.setEmail("jane.doe@example.org");
        internshipmanager3.setId(1L);
        internshipmanager3.setNom("Nom");
        internshipmanager3.setPassword("iloveyou");
        internshipmanager3.setPhone("6625550144");
        internshipmanager3.setPrenom("Prenom");
        internshipmanager3.setProgramme(programme11);
        internshipmanager3.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest4 = new OfferReviewRequest();
        offerReviewRequest4.setComment("Comment");
        offerReviewRequest4.setId(1L);
        offerReviewRequest4.setInternOffer(internOffer5);
        offerReviewRequest4.setInternshipmanager(internshipmanager3);

        Programme programme12 = new Programme();
        programme12.setDescription("The characteristics of someone or something");
        programme12.setId(1L);
        programme12.setNom("Nom");

        InternOffer internOffer6 = new InternOffer();
        internOffer6.setDescription("The characteristics of someone or something");
        internOffer6.setEmployeur(employeur2);
        internOffer6.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer6.setFile(file3);
        internOffer6.setId(1L);
        internOffer6.setInternshipCandidates(new ArrayList<>());
        internOffer6.setLocation("Location");
        internOffer6.setOfferReviewRequest(offerReviewRequest4);
        internOffer6.setProgramme(programme12);
        internOffer6.setSalaryByHour(10.0d);
        internOffer6.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer6.setState(State.ACCEPTED);
        internOffer6.setStatus("Status");
        internOffer6.setTitle("Dr");
        InternshipCandidates internshipCandidates4 = mock(InternshipCandidates.class);
        when(internshipCandidates4.getEtudiant()).thenReturn(etudiant3);
        when(internshipCandidates4.getInternOffer()).thenReturn(internOffer6);
        when(internshipCandidates4.getState()).thenReturn(State.ACCEPTED);
        when(internshipCandidates4.getFiles()).thenReturn(new ArrayList<>());
        when(internshipCandidates4.getId()).thenReturn(1L);
        doNothing().when(internshipCandidates4).setEtudiant(Mockito.<Etudiant>any());
        doNothing().when(internshipCandidates4).setFiles(Mockito.<List<File>>any());
        doNothing().when(internshipCandidates4).setId(anyLong());
        doNothing().when(internshipCandidates4).setInternOffer(Mockito.<InternOffer>any());
        doNothing().when(internshipCandidates4).setState(Mockito.<State>any());
        internshipCandidates4.setEtudiant(etudiant);
        internshipCandidates4.setFiles(new ArrayList<>());
        internshipCandidates4.setId(1L);
        internshipCandidates4.setInternOffer(internOffer2);
        internshipCandidates4.setState(State.ACCEPTED);

        ArrayList<InternshipCandidates> internshipCandidatesList = new ArrayList<>();
        internshipCandidatesList.add(internshipCandidates4);
        when(internshipCandidatesRepository.findAllByInternOfferId(Mockito.<Long>any()))
                .thenReturn(internshipCandidatesList);
        List<InternshipCandidatesDto> actualInternshipCandidatesByOfferId = internshipCandidatesService
                .getInternshipCandidatesByOfferId(1L);
        verify(internshipCandidates4, atLeast(1)).getEtudiant();
        verify(internshipCandidates4, atLeast(1)).getFiles();
        verify(internshipCandidates4).getId();
        verify(internshipCandidates4, atLeast(1)).getInternOffer();
        verify(internshipCandidates4).getState();
        verify(internshipCandidates4).setEtudiant(Mockito.<Etudiant>any());
        verify(internshipCandidates4).setFiles(Mockito.<List<File>>any());
        verify(internshipCandidates4).setId(anyLong());
        verify(internshipCandidates4).setInternOffer(Mockito.<InternOffer>any());
        verify(internshipCandidates4).setState(Mockito.<State>any());
        verify(internshipCandidatesRepository).findAllByInternOfferId(Mockito.<Long>any());
        InternshipCandidatesDto getResult = actualInternshipCandidatesByOfferId.get(0);
        InternOfferDto internOfferJob = getResult.getInternOfferJob();
        assertEquals("1970-01-01", internOfferJob.getEndDate());
        assertEquals("1970-01-01", internOfferJob.getStartDate());
        EtudiantDtoWithId etudiant7 = getResult.getEtudiant();
        assertEquals("6625550144", etudiant7.getPhone());
        assertEquals("Dr", internOfferJob.getTitle());
        assertEquals("Entreprise", internOfferJob.getEmployeurEntreprise());
        assertEquals("Location", internOfferJob.getLocation());
        assertEquals("Matricule", etudiant7.getMatricule());
        assertEquals("Nom", internOfferJob.getEmployeurNom());
        assertEquals("Nom", internOfferJob.getProgrammeNom());
        assertEquals("Nom", etudiant7.getNom());
        assertEquals("Prenom", internOfferJob.getEmployeurPrenom());
        assertEquals("Prenom", etudiant7.getPrenom());
        assertEquals("The characteristics of someone or something", internOfferJob.getDescription());
        FileDto file5 = internOfferJob.getFile();
        assertEquals("foo.txt", file5.getFileName());
        assertEquals("jane.doe@example.org", etudiant7.getEmail());
        assertEquals(1, actualInternshipCandidatesByOfferId.size());
        assertEquals(1L, etudiant7.getProgramme_id());
        assertEquals(1L, file5.getId());
        assertEquals(1L, internOfferJob.getEmployeurId());
        assertEquals(1L, internOfferJob.getId());
        assertEquals(1L, internOfferJob.getOfferReviewRequestId());
        assertEquals(1L, internOfferJob.getProgrammeId());
        assertEquals(1L, getResult.getId());
        assertEquals(1L, etudiant7.getId());
        assertEquals(State.ACCEPTED, file5.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferJob.getState());
        assertEquals(State.ACCEPTED, getResult.getState());
        List<FileDto> files = getResult.getFiles();
        assertTrue(files.isEmpty());
        assertEquals(cv, internOfferJob.getInternshipCandidates());
        assertSame(files, etudiant7.getCv());
        assertSame(files, etudiant7.getInternships_id());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file5.getContent());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getInternshipCandidatesByIds(String)}
     */
    @Test
    void testGetInternshipCandidatesByIds() {
        assertThrows(RuntimeException.class, () -> internshipCandidatesService.getInternshipCandidatesByIds("Ids"));
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getInternshipCandidatesByIds(String)}
     */
    @Test
    void testGetInternshipCandidatesByIds2() {
        when(internshipCandidatesRepository.findAllById(Mockito.<Iterable<Long>>any())).thenReturn(new ArrayList<>());
        List<InternshipCandidatesDto> actualInternshipCandidatesByIds = internshipCandidatesService
                .getInternshipCandidatesByIds(",");
        verify(internshipCandidatesRepository).findAllById(Mockito.<Iterable<Long>>any());
        assertTrue(actualInternshipCandidatesByIds.isEmpty());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getInternshipCandidatesByIds(String)}
     */
    @Test
    void testGetInternshipCandidatesByIds3() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom(",");

        Etudiant etudiant = new Etudiant();
        ArrayList<File> cv = new ArrayList<>();
        etudiant.setCv(new File());
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1L);
        etudiant.setInternshipsCandidate(new ArrayList<>());
        etudiant.setMatricule(",");
        etudiant.setNom(",");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom(",");
        etudiant.setProgramme(programme);
        etudiant.setRole(Role.employer);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom(",");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise(",");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom(",");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom(",");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
        etudiant2.setEmail("jane.doe@example.org");
        etudiant2.setId(1L);
        etudiant2.setInternshipsCandidate(new ArrayList<>());
        etudiant2.setMatricule(",");
        etudiant2.setNom(",");
        etudiant2.setPassword("iloveyou");
        etudiant2.setPhone("6625550144");
        etudiant2.setPrenom(",");
        etudiant2.setProgramme(new Programme());
        etudiant2.setRole(Role.employer);

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

        InternOffer internOffer = new InternOffer();
        internOffer.setDescription("The characteristics of someone or something");
        internOffer.setEmployeur(new Employeur());
        internOffer.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer.setFile(new File());
        internOffer.setId(1L);
        internOffer.setInternshipCandidates(new ArrayList<>());
        internOffer.setLocation(",");
        internOffer.setOfferReviewRequest(new OfferReviewRequest());
        internOffer.setProgramme(new Programme());
        internOffer.setSalaryByHour(10.0d);
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus(",");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom(",");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom(",");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment(",");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom(",");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation(",");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus(",");
        internOffer2.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer2);
        internshipCandidates2.setState(State.ACCEPTED);

        ArrayList<InternshipCandidates> internshipCandidatesList = new ArrayList<>();
        internshipCandidatesList.add(internshipCandidates2);
        when(internshipCandidatesRepository.findAllById(Mockito.<Iterable<Long>>any()))
                .thenReturn(internshipCandidatesList);
        List<InternshipCandidatesDto> actualInternshipCandidatesByIds = internshipCandidatesService
                .getInternshipCandidatesByIds(",");
        verify(internshipCandidatesRepository).findAllById(Mockito.<Iterable<Long>>any());
        InternshipCandidatesDto getResult = actualInternshipCandidatesByIds.get(0);
        EtudiantDtoWithId etudiant3 = getResult.getEtudiant();
        assertEquals(",", etudiant3.getMatricule());
        InternOfferDto internOfferJob = getResult.getInternOfferJob();
        assertEquals(",", internOfferJob.getEmployeurEntreprise());
        assertEquals(",", internOfferJob.getEmployeurNom());
        assertEquals(",", internOfferJob.getEmployeurPrenom());
        assertEquals(",", internOfferJob.getLocation());
        assertEquals(",", internOfferJob.getProgrammeNom());
        assertEquals(",", etudiant3.getNom());
        assertEquals(",", etudiant3.getPrenom());
        assertEquals("1970-01-01", internOfferJob.getEndDate());
        assertEquals("1970-01-01", internOfferJob.getStartDate());
        assertEquals("6625550144", etudiant3.getPhone());
        assertEquals("Dr", internOfferJob.getTitle());
        assertEquals("The characteristics of someone or something", internOfferJob.getDescription());
        FileDto file2 = internOfferJob.getFile();
        assertEquals("foo.txt", file2.getFileName());
        assertEquals("jane.doe@example.org", etudiant3.getEmail());
        assertEquals(1, actualInternshipCandidatesByIds.size());
        assertEquals(1L, etudiant3.getProgramme_id());
        assertEquals(1L, file2.getId());
        assertEquals(1L, internOfferJob.getEmployeurId());
        assertEquals(1L, internOfferJob.getId());
        assertEquals(1L, internOfferJob.getOfferReviewRequestId());
        assertEquals(1L, internOfferJob.getProgrammeId());
        assertEquals(1L, getResult.getId());
        assertEquals(1L, etudiant3.getId());
        assertEquals(State.ACCEPTED, file2.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferJob.getState());
        assertEquals(State.ACCEPTED, getResult.getState());
        List<FileDto> files = getResult.getFiles();
        assertTrue(files.isEmpty());
        assertEquals(cv, internOfferJob.getInternshipCandidates());
        assertSame(files, etudiant3.getCv());
        assertSame(files, etudiant3.getInternships_id());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file2.getContent());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getInternshipCandidatesByIds(String)}
     */
    @Test
    void testGetInternshipCandidatesByIds4() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom(",");

        Etudiant etudiant = new Etudiant();
        etudiant.setCv(new File());
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1L);
        etudiant.setInternshipsCandidate(new ArrayList<>());
        etudiant.setMatricule(",");
        etudiant.setNom(",");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom(",");
        etudiant.setProgramme(programme);
        etudiant.setRole(Role.employer);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom(",");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise(",");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom(",");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom(",");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
        etudiant2.setEmail("jane.doe@example.org");
        etudiant2.setId(1L);
        etudiant2.setInternshipsCandidate(new ArrayList<>());
        etudiant2.setMatricule(",");
        etudiant2.setNom(",");
        etudiant2.setPassword("iloveyou");
        etudiant2.setPhone("6625550144");
        etudiant2.setPrenom(",");
        etudiant2.setProgramme(new Programme());
        etudiant2.setRole(Role.employer);

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

        InternOffer internOffer = new InternOffer();
        internOffer.setDescription("The characteristics of someone or something");
        internOffer.setEmployeur(new Employeur());
        internOffer.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer.setFile(new File());
        internOffer.setId(1L);
        internOffer.setInternshipCandidates(new ArrayList<>());
        internOffer.setLocation(",");
        internOffer.setOfferReviewRequest(new OfferReviewRequest());
        internOffer.setProgramme(new Programme());
        internOffer.setSalaryByHour(10.0d);
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus(",");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom(",");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom(",");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment(",");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom(",");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation(",");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus(",");
        internOffer2.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer2);
        internshipCandidates2.setState(State.ACCEPTED);

        Programme programme4 = new Programme();
        programme4.setDescription(",");
        programme4.setId(2L);
        programme4.setNom("Nom");

        Etudiant etudiant3 = new Etudiant();
        ArrayList<File> cv = new ArrayList<>();
        etudiant3.setCv(new File());
        etudiant3.setEmail("john.smith@example.org");
        etudiant3.setId(2L);
        etudiant3.setInternshipsCandidate(new ArrayList<>());
        etudiant3.setMatricule("Matricule");
        etudiant3.setNom("Nom");
        etudiant3.setPassword(",");
        etudiant3.setPhone("8605550118");
        etudiant3.setPrenom("Prenom");
        etudiant3.setProgramme(programme4);
        etudiant3.setRole(Role.student);

        Programme programme5 = new Programme();
        programme5.setDescription(",");
        programme5.setId(2L);
        programme5.setNom("Nom");

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("john.smith@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(2L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword(",");
        employeur2.setPhone("8605550118");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(programme5);
        employeur2.setRole(Role.student);

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setCv(new File());
        etudiant4.setEmail("john.smith@example.org");
        etudiant4.setId(2L);
        etudiant4.setInternshipsCandidate(new ArrayList<>());
        etudiant4.setMatricule("Matricule");
        etudiant4.setNom("Nom");
        etudiant4.setPassword(",");
        etudiant4.setPhone("8605550118");
        etudiant4.setPrenom("Prenom");
        etudiant4.setProgramme(new Programme());
        etudiant4.setRole(Role.student);

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(new Etudiant());
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(2L);
        internshipCandidates3.setInternOffer(new InternOffer());
        internshipCandidates3.setState(State.PENDING);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(etudiant4);
        file2.setFileName(",");
        file2.setId(2L);
        file2.setInternshipCandidates(internshipCandidates3);
        file2.setIsAccepted(State.PENDING);

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription(",");
        internOffer3.setEmployeur(new Employeur());
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(new File());
        internOffer3.setId(2L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(new OfferReviewRequest());
        internOffer3.setProgramme(new Programme());
        internOffer3.setSalaryByHour(0.5d);
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.PENDING);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Mr");

        Internshipmanager internshipmanager2 = new Internshipmanager();
        internshipmanager2.setEmail("john.smith@example.org");
        internshipmanager2.setId(2L);
        internshipmanager2.setNom("Nom");
        internshipmanager2.setPassword(",");
        internshipmanager2.setPhone("8605550118");
        internshipmanager2.setPrenom("Prenom");
        internshipmanager2.setProgramme(new Programme());
        internshipmanager2.setRole(Role.student);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(2L);
        offerReviewRequest2.setInternOffer(internOffer3);
        offerReviewRequest2.setInternshipmanager(internshipmanager2);

        Programme programme6 = new Programme();
        programme6.setDescription(",");
        programme6.setId(2L);
        programme6.setNom("Nom");

        InternOffer internOffer4 = new InternOffer();
        internOffer4.setDescription(",");
        internOffer4.setEmployeur(employeur2);
        internOffer4.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer4.setFile(file2);
        internOffer4.setId(2L);
        internOffer4.setInternshipCandidates(new ArrayList<>());
        internOffer4.setLocation("Location");
        internOffer4.setOfferReviewRequest(offerReviewRequest2);
        internOffer4.setProgramme(programme6);
        internOffer4.setSalaryByHour(0.5d);
        internOffer4.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer4.setState(State.PENDING);
        internOffer4.setStatus("Status");
        internOffer4.setTitle("Mr");

        InternshipCandidates internshipCandidates4 = new InternshipCandidates();
        internshipCandidates4.setEtudiant(etudiant3);
        internshipCandidates4.setFiles(new ArrayList<>());
        internshipCandidates4.setId(2L);
        internshipCandidates4.setInternOffer(internOffer4);
        internshipCandidates4.setState(State.PENDING);

        ArrayList<InternshipCandidates> internshipCandidatesList = new ArrayList<>();
        internshipCandidatesList.add(internshipCandidates4);
        internshipCandidatesList.add(internshipCandidates2);
        when(internshipCandidatesRepository.findAllById(Mockito.<Iterable<Long>>any()))
                .thenReturn(internshipCandidatesList);
        List<InternshipCandidatesDto> actualInternshipCandidatesByIds = internshipCandidatesService
                .getInternshipCandidatesByIds(",");
        verify(internshipCandidatesRepository).findAllById(Mockito.<Iterable<Long>>any());
        InternshipCandidatesDto getResult = actualInternshipCandidatesByIds.get(1);
        EtudiantDtoWithId etudiant5 = getResult.getEtudiant();
        assertEquals(",", etudiant5.getMatricule());
        InternshipCandidatesDto getResult2 = actualInternshipCandidatesByIds.get(0);
        InternOfferDto internOfferJob = getResult2.getInternOfferJob();
        FileDto file3 = internOfferJob.getFile();
        assertEquals(",", file3.getFileName());
        assertEquals(",", internOfferJob.getDescription());
        InternOfferDto internOfferJob2 = getResult.getInternOfferJob();
        assertEquals(",", internOfferJob2.getEmployeurEntreprise());
        assertEquals(",", internOfferJob2.getEmployeurNom());
        assertEquals(",", internOfferJob2.getEmployeurPrenom());
        assertEquals(",", internOfferJob2.getLocation());
        assertEquals(",", internOfferJob2.getProgrammeNom());
        assertEquals(",", etudiant5.getNom());
        assertEquals(",", etudiant5.getPrenom());
        assertEquals("1970-01-01", internOfferJob.getEndDate());
        assertEquals("1970-01-01", internOfferJob2.getEndDate());
        assertEquals("1970-01-01", internOfferJob.getStartDate());
        assertEquals("1970-01-01", internOfferJob2.getStartDate());
        assertEquals("6625550144", etudiant5.getPhone());
        EtudiantDtoWithId etudiant6 = getResult2.getEtudiant();
        assertEquals("8605550118", etudiant6.getPhone());
        assertEquals("Dr", internOfferJob2.getTitle());
        assertEquals("Entreprise", internOfferJob.getEmployeurEntreprise());
        assertEquals("Location", internOfferJob.getLocation());
        assertEquals("Matricule", etudiant6.getMatricule());
        assertEquals("Mr", internOfferJob.getTitle());
        assertEquals("Nom", internOfferJob.getEmployeurNom());
        assertEquals("Nom", internOfferJob.getProgrammeNom());
        assertEquals("Nom", etudiant6.getNom());
        assertEquals("Prenom", internOfferJob.getEmployeurPrenom());
        assertEquals("Prenom", etudiant6.getPrenom());
        assertEquals("The characteristics of someone or something", internOfferJob2.getDescription());
        FileDto file4 = internOfferJob2.getFile();
        assertEquals("foo.txt", file4.getFileName());
        assertEquals("jane.doe@example.org", etudiant5.getEmail());
        assertEquals("john.smith@example.org", etudiant6.getEmail());
        assertEquals(1L, etudiant5.getProgramme_id());
        assertEquals(1L, file4.getId());
        assertEquals(1L, internOfferJob2.getEmployeurId());
        assertEquals(1L, internOfferJob2.getId());
        assertEquals(1L, internOfferJob2.getOfferReviewRequestId());
        assertEquals(1L, internOfferJob2.getProgrammeId());
        assertEquals(1L, getResult.getId());
        assertEquals(1L, etudiant5.getId());
        assertEquals(2, actualInternshipCandidatesByIds.size());
        assertEquals(2L, etudiant6.getProgramme_id());
        assertEquals(2L, file3.getId());
        assertEquals(2L, internOfferJob.getEmployeurId());
        assertEquals(2L, internOfferJob.getId());
        assertEquals(2L, internOfferJob.getOfferReviewRequestId());
        assertEquals(2L, internOfferJob.getProgrammeId());
        assertEquals(2L, getResult2.getId());
        assertEquals(2L, etudiant6.getId());
        assertEquals(State.ACCEPTED, file4.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferJob2.getState());
        assertEquals(State.ACCEPTED, getResult.getState());
        assertEquals(State.PENDING, file3.getIsAccepted());
        assertEquals(State.PENDING, internOfferJob.getState());
        assertEquals(State.PENDING, getResult2.getState());
        assertTrue(getResult2.getFiles().isEmpty());
        List<FileDto> files = getResult.getFiles();
        assertTrue(files.isEmpty());
        assertEquals(cv, internOfferJob.getInternshipCandidates());
        assertEquals(cv, internOfferJob2.getInternshipCandidates());
        assertSame(files, etudiant6.getCv());
        assertSame(files, etudiant5.getCv());
        assertSame(files, etudiant6.getInternships_id());
        assertSame(files, etudiant5.getInternships_id());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file3.getContent());
        byte[] expectedContent2 = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent2, file4.getContent());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getInternshipCandidatesByIds(String)}
     */
    @Test
    void testGetInternshipCandidatesByIds5() {
        when(internshipCandidatesRepository.findAllById(Mockito.<Iterable<Long>>any())).thenReturn(new ArrayList<>());
        List<InternshipCandidatesDto> actualInternshipCandidatesByIds = internshipCandidatesService
                .getInternshipCandidatesByIds("42");
        verify(internshipCandidatesRepository).findAllById(Mockito.<Iterable<Long>>any());
        assertTrue(actualInternshipCandidatesByIds.isEmpty());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getInternshipCandidatesByIds(String)}
     */
    @Test
    void testGetInternshipCandidatesByIds6() {
        when(internshipCandidatesRepository.findAllById(Mockito.<Iterable<Long>>any()))
                .thenThrow(new RuntimeException(","));
        assertThrows(RuntimeException.class, () -> internshipCandidatesService.getInternshipCandidatesByIds(","));
        verify(internshipCandidatesRepository).findAllById(Mockito.<Iterable<Long>>any());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getInternshipCandidatesByIds(String)}
     */
    @Test
    void testGetInternshipCandidatesByIds7() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom(",");

        Etudiant etudiant = new Etudiant();
        etudiant.setCv(new File());
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1L);
        etudiant.setInternshipsCandidate(new ArrayList<>());
        etudiant.setMatricule(",");
        etudiant.setNom(",");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom(",");
        etudiant.setProgramme(programme);
        etudiant.setRole(Role.employer);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom(",");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise(",");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom(",");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom(",");
        employeur.setProgramme(programme2);
        employeur.setRole(Role.employer);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
        etudiant2.setEmail("jane.doe@example.org");
        etudiant2.setId(1L);
        etudiant2.setInternshipsCandidate(new ArrayList<>());
        etudiant2.setMatricule(",");
        etudiant2.setNom(",");
        etudiant2.setPassword("iloveyou");
        etudiant2.setPhone("6625550144");
        etudiant2.setPrenom(",");
        etudiant2.setProgramme(new Programme());
        etudiant2.setRole(Role.employer);

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

        InternOffer internOffer = new InternOffer();
        internOffer.setDescription("The characteristics of someone or something");
        internOffer.setEmployeur(new Employeur());
        internOffer.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer.setFile(new File());
        internOffer.setId(1L);
        internOffer.setInternshipCandidates(new ArrayList<>());
        internOffer.setLocation(",");
        internOffer.setOfferReviewRequest(new OfferReviewRequest());
        internOffer.setProgramme(new Programme());
        internOffer.setSalaryByHour(10.0d);
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus(",");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom(",");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom(",");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment(",");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom(",");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation(",");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus(",");
        internOffer2.setTitle("Dr");

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Etudiant etudiant3 = new Etudiant();
        ArrayList<File> cv = new ArrayList<>();
        etudiant3.setCv(new File());
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

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(programme5);
        employeur2.setRole(Role.employer);

        Programme programme6 = new Programme();
        programme6.setDescription("The characteristics of someone or something");
        programme6.setId(1L);
        programme6.setNom("Nom");

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setCv(new File());
        etudiant4.setEmail("jane.doe@example.org");
        etudiant4.setId(1L);
        etudiant4.setInternshipsCandidate(new ArrayList<>());
        etudiant4.setMatricule("Matricule");
        etudiant4.setNom("Nom");
        etudiant4.setPassword("iloveyou");
        etudiant4.setPhone("6625550144");
        etudiant4.setPrenom("Prenom");
        etudiant4.setProgramme(programme6);
        etudiant4.setRole(Role.employer);

        Programme programme7 = new Programme();
        programme7.setDescription("The characteristics of someone or something");
        programme7.setId(1L);
        programme7.setNom("Nom");

        Etudiant etudiant5 = new Etudiant();
        etudiant5.setCv(new File());
        etudiant5.setEmail("jane.doe@example.org");
        etudiant5.setId(1L);
        etudiant5.setInternshipsCandidate(new ArrayList<>());
        etudiant5.setMatricule("Matricule");
        etudiant5.setNom("Nom");
        etudiant5.setPassword("iloveyou");
        etudiant5.setPhone("6625550144");
        etudiant5.setPrenom("Prenom");
        etudiant5.setProgramme(programme7);
        etudiant5.setRole(Role.employer);

        Employeur employeur3 = new Employeur();
        employeur3.setEmail("jane.doe@example.org");
        employeur3.setEntreprise("Entreprise");
        employeur3.setId(1L);
        employeur3.setInternOffers(new ArrayList<>());
        employeur3.setNom("Nom");
        employeur3.setPassword("iloveyou");
        employeur3.setPhone("6625550144");
        employeur3.setPrenom("Prenom");
        employeur3.setProgramme(new Programme());
        employeur3.setRole(Role.employer);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(new Etudiant());
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(new InternshipCandidates());
        file2.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(new InternOffer());
        offerReviewRequest2.setInternshipmanager(new Internshipmanager());

        Programme programme8 = new Programme();
        programme8.setDescription("The characteristics of someone or something");
        programme8.setId(1L);
        programme8.setNom("Nom");

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur3);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file2);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest2);
        internOffer3.setProgramme(programme8);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant5);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer3);
        internshipCandidates2.setState(State.ACCEPTED);

        File file3 = new File();
        file3.setContent("AXAXAXAX".getBytes("UTF-8"));
        file3.setEtudiant(etudiant4);
        file3.setFileName("foo.txt");
        file3.setId(1L);
        file3.setInternshipCandidates(internshipCandidates2);
        file3.setIsAccepted(State.ACCEPTED);

        Programme programme9 = new Programme();
        programme9.setDescription("The characteristics of someone or something");
        programme9.setId(1L);
        programme9.setNom("Nom");

        Employeur employeur4 = new Employeur();
        employeur4.setEmail("jane.doe@example.org");
        employeur4.setEntreprise("Entreprise");
        employeur4.setId(1L);
        employeur4.setInternOffers(new ArrayList<>());
        employeur4.setNom("Nom");
        employeur4.setPassword("iloveyou");
        employeur4.setPhone("6625550144");
        employeur4.setPrenom("Prenom");
        employeur4.setProgramme(programme9);
        employeur4.setRole(Role.employer);

        Etudiant etudiant6 = new Etudiant();
        etudiant6.setCv(new File());
        etudiant6.setEmail("jane.doe@example.org");
        etudiant6.setId(1L);
        etudiant6.setInternshipsCandidate(new ArrayList<>());
        etudiant6.setMatricule("Matricule");
        etudiant6.setNom("Nom");
        etudiant6.setPassword("iloveyou");
        etudiant6.setPhone("6625550144");
        etudiant6.setPrenom("Prenom");
        etudiant6.setProgramme(new Programme());
        etudiant6.setRole(Role.employer);

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(new Etudiant());
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(1L);
        internshipCandidates3.setInternOffer(new InternOffer());
        internshipCandidates3.setState(State.ACCEPTED);

        File file4 = new File();
        file4.setContent("AXAXAXAX".getBytes("UTF-8"));
        file4.setEtudiant(etudiant6);
        file4.setFileName("foo.txt");
        file4.setId(1L);
        file4.setInternshipCandidates(internshipCandidates3);
        file4.setIsAccepted(State.ACCEPTED);

        InternOffer internOffer4 = new InternOffer();
        internOffer4.setDescription("The characteristics of someone or something");
        internOffer4.setEmployeur(new Employeur());
        internOffer4.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer4.setFile(new File());
        internOffer4.setId(1L);
        internOffer4.setInternshipCandidates(new ArrayList<>());
        internOffer4.setLocation("Location");
        internOffer4.setOfferReviewRequest(new OfferReviewRequest());
        internOffer4.setProgramme(new Programme());
        internOffer4.setSalaryByHour(10.0d);
        internOffer4.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer4.setState(State.ACCEPTED);
        internOffer4.setStatus("Status");
        internOffer4.setTitle("Dr");

        Internshipmanager internshipmanager2 = new Internshipmanager();
        internshipmanager2.setEmail("jane.doe@example.org");
        internshipmanager2.setId(1L);
        internshipmanager2.setNom("Nom");
        internshipmanager2.setPassword("iloveyou");
        internshipmanager2.setPhone("6625550144");
        internshipmanager2.setPrenom("Prenom");
        internshipmanager2.setProgramme(new Programme());
        internshipmanager2.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest3 = new OfferReviewRequest();
        offerReviewRequest3.setComment("Comment");
        offerReviewRequest3.setId(1L);
        offerReviewRequest3.setInternOffer(internOffer4);
        offerReviewRequest3.setInternshipmanager(internshipmanager2);

        Programme programme10 = new Programme();
        programme10.setDescription("The characteristics of someone or something");
        programme10.setId(1L);
        programme10.setNom("Nom");

        InternOffer internOffer5 = new InternOffer();
        internOffer5.setDescription("The characteristics of someone or something");
        internOffer5.setEmployeur(employeur4);
        internOffer5.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer5.setFile(file4);
        internOffer5.setId(1L);
        internOffer5.setInternshipCandidates(new ArrayList<>());
        internOffer5.setLocation("Location");
        internOffer5.setOfferReviewRequest(offerReviewRequest3);
        internOffer5.setProgramme(programme10);
        internOffer5.setSalaryByHour(10.0d);
        internOffer5.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer5.setState(State.ACCEPTED);
        internOffer5.setStatus("Status");
        internOffer5.setTitle("Dr");

        Programme programme11 = new Programme();
        programme11.setDescription("The characteristics of someone or something");
        programme11.setId(1L);
        programme11.setNom("Nom");

        Internshipmanager internshipmanager3 = new Internshipmanager();
        internshipmanager3.setEmail("jane.doe@example.org");
        internshipmanager3.setId(1L);
        internshipmanager3.setNom("Nom");
        internshipmanager3.setPassword("iloveyou");
        internshipmanager3.setPhone("6625550144");
        internshipmanager3.setPrenom("Prenom");
        internshipmanager3.setProgramme(programme11);
        internshipmanager3.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest4 = new OfferReviewRequest();
        offerReviewRequest4.setComment("Comment");
        offerReviewRequest4.setId(1L);
        offerReviewRequest4.setInternOffer(internOffer5);
        offerReviewRequest4.setInternshipmanager(internshipmanager3);

        Programme programme12 = new Programme();
        programme12.setDescription("The characteristics of someone or something");
        programme12.setId(1L);
        programme12.setNom("Nom");

        InternOffer internOffer6 = new InternOffer();
        internOffer6.setDescription("The characteristics of someone or something");
        internOffer6.setEmployeur(employeur2);
        internOffer6.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer6.setFile(file3);
        internOffer6.setId(1L);
        internOffer6.setInternshipCandidates(new ArrayList<>());
        internOffer6.setLocation("Location");
        internOffer6.setOfferReviewRequest(offerReviewRequest4);
        internOffer6.setProgramme(programme12);
        internOffer6.setSalaryByHour(10.0d);
        internOffer6.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer6.setState(State.ACCEPTED);
        internOffer6.setStatus("Status");
        internOffer6.setTitle("Dr");
        InternshipCandidates internshipCandidates4 = mock(InternshipCandidates.class);
        when(internshipCandidates4.getEtudiant()).thenReturn(etudiant3);
        when(internshipCandidates4.getInternOffer()).thenReturn(internOffer6);
        when(internshipCandidates4.getState()).thenReturn(State.ACCEPTED);
        when(internshipCandidates4.getFiles()).thenReturn(new ArrayList<>());
        when(internshipCandidates4.getId()).thenReturn(1L);
        doNothing().when(internshipCandidates4).setEtudiant(Mockito.<Etudiant>any());
        doNothing().when(internshipCandidates4).setFiles(Mockito.<List<File>>any());
        doNothing().when(internshipCandidates4).setId(anyLong());
        doNothing().when(internshipCandidates4).setInternOffer(Mockito.<InternOffer>any());
        doNothing().when(internshipCandidates4).setState(Mockito.<State>any());
        internshipCandidates4.setEtudiant(etudiant);
        internshipCandidates4.setFiles(new ArrayList<>());
        internshipCandidates4.setId(1L);
        internshipCandidates4.setInternOffer(internOffer2);
        internshipCandidates4.setState(State.ACCEPTED);

        ArrayList<InternshipCandidates> internshipCandidatesList = new ArrayList<>();
        internshipCandidatesList.add(internshipCandidates4);
        when(internshipCandidatesRepository.findAllById(Mockito.<Iterable<Long>>any()))
                .thenReturn(internshipCandidatesList);
        List<InternshipCandidatesDto> actualInternshipCandidatesByIds = internshipCandidatesService
                .getInternshipCandidatesByIds(",");
        verify(internshipCandidates4, atLeast(1)).getEtudiant();
        verify(internshipCandidates4, atLeast(1)).getFiles();
        verify(internshipCandidates4).getId();
        verify(internshipCandidates4, atLeast(1)).getInternOffer();
        verify(internshipCandidates4).getState();
        verify(internshipCandidates4).setEtudiant(Mockito.<Etudiant>any());
        verify(internshipCandidates4).setFiles(Mockito.<List<File>>any());
        verify(internshipCandidates4).setId(anyLong());
        verify(internshipCandidates4).setInternOffer(Mockito.<InternOffer>any());
        verify(internshipCandidates4).setState(Mockito.<State>any());
        verify(internshipCandidatesRepository).findAllById(Mockito.<Iterable<Long>>any());
        InternshipCandidatesDto getResult = actualInternshipCandidatesByIds.get(0);
        InternOfferDto internOfferJob = getResult.getInternOfferJob();
        assertEquals("1970-01-01", internOfferJob.getEndDate());
        assertEquals("1970-01-01", internOfferJob.getStartDate());
        EtudiantDtoWithId etudiant7 = getResult.getEtudiant();
        assertEquals("6625550144", etudiant7.getPhone());
        assertEquals("Dr", internOfferJob.getTitle());
        assertEquals("Entreprise", internOfferJob.getEmployeurEntreprise());
        assertEquals("Location", internOfferJob.getLocation());
        assertEquals("Matricule", etudiant7.getMatricule());
        assertEquals("Nom", internOfferJob.getEmployeurNom());
        assertEquals("Nom", internOfferJob.getProgrammeNom());
        assertEquals("Nom", etudiant7.getNom());
        assertEquals("Prenom", internOfferJob.getEmployeurPrenom());
        assertEquals("Prenom", etudiant7.getPrenom());
        assertEquals("The characteristics of someone or something", internOfferJob.getDescription());
        FileDto file5 = internOfferJob.getFile();
        assertEquals("foo.txt", file5.getFileName());
        assertEquals("jane.doe@example.org", etudiant7.getEmail());
        assertEquals(1, actualInternshipCandidatesByIds.size());
        assertEquals(1L, etudiant7.getProgramme_id());
        assertEquals(1L, file5.getId());
        assertEquals(1L, internOfferJob.getEmployeurId());
        assertEquals(1L, internOfferJob.getId());
        assertEquals(1L, internOfferJob.getOfferReviewRequestId());
        assertEquals(1L, internOfferJob.getProgrammeId());
        assertEquals(1L, getResult.getId());
        assertEquals(1L, etudiant7.getId());
        assertEquals(State.ACCEPTED, file5.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferJob.getState());
        assertEquals(State.ACCEPTED, getResult.getState());
        List<FileDto> files = getResult.getFiles();
        assertTrue(files.isEmpty());
        assertEquals(cv, internOfferJob.getInternshipCandidates());
        assertSame(files, etudiant7.getCv());
        assertSame(files, etudiant7.getInternships_id());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file5.getContent());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#acceptCandidates(Long)}
     */
    @Test
    void testAcceptCandidates() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        etudiant.setCv(new File());
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

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
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

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

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
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer2);
        internshipCandidates2.setState(State.ACCEPTED);
        Optional<InternshipCandidates> ofResult = Optional.of(internshipCandidates2);

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Etudiant etudiant3 = new Etudiant();
        ArrayList<File> cv = new ArrayList<>();
        etudiant3.setCv(new File());
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

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(programme5);
        employeur2.setRole(Role.employer);

        Programme programme6 = new Programme();
        programme6.setDescription("The characteristics of someone or something");
        programme6.setId(1L);
        programme6.setNom("Nom");

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setCv(new File());
        etudiant4.setEmail("jane.doe@example.org");
        etudiant4.setId(1L);
        etudiant4.setInternshipsCandidate(new ArrayList<>());
        etudiant4.setMatricule("Matricule");
        etudiant4.setNom("Nom");
        etudiant4.setPassword("iloveyou");
        etudiant4.setPhone("6625550144");
        etudiant4.setPrenom("Prenom");
        etudiant4.setProgramme(programme6);
        etudiant4.setRole(Role.employer);

        Etudiant etudiant5 = new Etudiant();
        etudiant5.setCv(new File());
        etudiant5.setEmail("jane.doe@example.org");
        etudiant5.setId(1L);
        etudiant5.setInternshipsCandidate(new ArrayList<>());
        etudiant5.setMatricule("Matricule");
        etudiant5.setNom("Nom");
        etudiant5.setPassword("iloveyou");
        etudiant5.setPhone("6625550144");
        etudiant5.setPrenom("Prenom");
        etudiant5.setProgramme(new Programme());
        etudiant5.setRole(Role.employer);

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(new Employeur());
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(new File());
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(new OfferReviewRequest());
        internOffer3.setProgramme(new Programme());
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(etudiant5);
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(1L);
        internshipCandidates3.setInternOffer(internOffer3);
        internshipCandidates3.setState(State.ACCEPTED);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(etudiant4);
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(internshipCandidates3);
        file2.setIsAccepted(State.ACCEPTED);

        Employeur employeur3 = new Employeur();
        employeur3.setEmail("jane.doe@example.org");
        employeur3.setEntreprise("Entreprise");
        employeur3.setId(1L);
        employeur3.setInternOffers(new ArrayList<>());
        employeur3.setNom("Nom");
        employeur3.setPassword("iloveyou");
        employeur3.setPhone("6625550144");
        employeur3.setPrenom("Prenom");
        employeur3.setProgramme(new Programme());
        employeur3.setRole(Role.employer);

        File file3 = new File();
        file3.setContent("AXAXAXAX".getBytes("UTF-8"));
        file3.setEtudiant(new Etudiant());
        file3.setFileName("foo.txt");
        file3.setId(1L);
        file3.setInternshipCandidates(new InternshipCandidates());
        file3.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(new InternOffer());
        offerReviewRequest2.setInternshipmanager(new Internshipmanager());

        Programme programme7 = new Programme();
        programme7.setDescription("The characteristics of someone or something");
        programme7.setId(1L);
        programme7.setNom("Nom");

        InternOffer internOffer4 = new InternOffer();
        internOffer4.setDescription("The characteristics of someone or something");
        internOffer4.setEmployeur(employeur3);
        internOffer4.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer4.setFile(file3);
        internOffer4.setId(1L);
        internOffer4.setInternshipCandidates(new ArrayList<>());
        internOffer4.setLocation("Location");
        internOffer4.setOfferReviewRequest(offerReviewRequest2);
        internOffer4.setProgramme(programme7);
        internOffer4.setSalaryByHour(10.0d);
        internOffer4.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer4.setState(State.ACCEPTED);
        internOffer4.setStatus("Status");
        internOffer4.setTitle("Dr");

        Programme programme8 = new Programme();
        programme8.setDescription("The characteristics of someone or something");
        programme8.setId(1L);
        programme8.setNom("Nom");

        Internshipmanager internshipmanager2 = new Internshipmanager();
        internshipmanager2.setEmail("jane.doe@example.org");
        internshipmanager2.setId(1L);
        internshipmanager2.setNom("Nom");
        internshipmanager2.setPassword("iloveyou");
        internshipmanager2.setPhone("6625550144");
        internshipmanager2.setPrenom("Prenom");
        internshipmanager2.setProgramme(programme8);
        internshipmanager2.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest3 = new OfferReviewRequest();
        offerReviewRequest3.setComment("Comment");
        offerReviewRequest3.setId(1L);
        offerReviewRequest3.setInternOffer(internOffer4);
        offerReviewRequest3.setInternshipmanager(internshipmanager2);

        Programme programme9 = new Programme();
        programme9.setDescription("The characteristics of someone or something");
        programme9.setId(1L);
        programme9.setNom("Nom");

        InternOffer internOffer5 = new InternOffer();
        internOffer5.setDescription("The characteristics of someone or something");
        internOffer5.setEmployeur(employeur2);
        internOffer5.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer5.setFile(file2);
        internOffer5.setId(1L);
        internOffer5.setInternshipCandidates(new ArrayList<>());
        internOffer5.setLocation("Location");
        internOffer5.setOfferReviewRequest(offerReviewRequest3);
        internOffer5.setProgramme(programme9);
        internOffer5.setSalaryByHour(10.0d);
        internOffer5.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer5.setState(State.ACCEPTED);
        internOffer5.setStatus("Status");
        internOffer5.setTitle("Dr");

        InternshipCandidates internshipCandidates4 = new InternshipCandidates();
        internshipCandidates4.setEtudiant(etudiant3);
        internshipCandidates4.setFiles(new ArrayList<>());
        internshipCandidates4.setId(1L);
        internshipCandidates4.setInternOffer(internOffer5);
        internshipCandidates4.setState(State.ACCEPTED);
        when(internshipCandidatesRepository.save(Mockito.<InternshipCandidates>any())).thenReturn(internshipCandidates4);
        when(internshipCandidatesRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        InternshipCandidatesDto actualAcceptCandidatesResult = internshipCandidatesService.acceptCandidates(1L);
        verify(internshipCandidatesRepository).findById(Mockito.<Long>any());
        verify(internshipCandidatesRepository).save(Mockito.<InternshipCandidates>any());
        InternOfferDto internOfferJob = actualAcceptCandidatesResult.getInternOfferJob();
        assertEquals("1970-01-01", internOfferJob.getEndDate());
        assertEquals("1970-01-01", internOfferJob.getStartDate());
        EtudiantDtoWithId etudiant6 = actualAcceptCandidatesResult.getEtudiant();
        assertEquals("6625550144", etudiant6.getPhone());
        assertEquals("Dr", internOfferJob.getTitle());
        assertEquals("Entreprise", internOfferJob.getEmployeurEntreprise());
        assertEquals("Location", internOfferJob.getLocation());
        assertEquals("Matricule", etudiant6.getMatricule());
        assertEquals("Nom", internOfferJob.getEmployeurNom());
        assertEquals("Nom", internOfferJob.getProgrammeNom());
        assertEquals("Nom", etudiant6.getNom());
        assertEquals("Prenom", internOfferJob.getEmployeurPrenom());
        assertEquals("Prenom", etudiant6.getPrenom());
        assertEquals("The characteristics of someone or something", internOfferJob.getDescription());
        FileDto file4 = internOfferJob.getFile();
        assertEquals("foo.txt", file4.getFileName());
        assertEquals("jane.doe@example.org", etudiant6.getEmail());
        assertEquals(1L, etudiant6.getProgramme_id());
        assertEquals(1L, file4.getId());
        assertEquals(1L, internOfferJob.getEmployeurId());
        assertEquals(1L, internOfferJob.getId());
        assertEquals(1L, internOfferJob.getOfferReviewRequestId());
        assertEquals(1L, internOfferJob.getProgrammeId());
        assertEquals(1L, actualAcceptCandidatesResult.getId());
        assertEquals(1L, etudiant6.getId());
        assertEquals(State.ACCEPTED, file4.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferJob.getState());
        assertEquals(State.ACCEPTED, actualAcceptCandidatesResult.getState());
        List<FileDto> files = actualAcceptCandidatesResult.getFiles();
        assertTrue(files.isEmpty());
        assertEquals(cv, internOfferJob.getInternshipCandidates());
        assertSame(files, etudiant6.getCv());
        assertSame(files, etudiant6.getInternships_id());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file4.getContent());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#acceptCandidates(Long)}
     */
    @Test
    void testAcceptCandidates2() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        etudiant.setCv(new File());
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

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
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

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

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
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer2);
        internshipCandidates2.setState(State.ACCEPTED);
        Optional<InternshipCandidates> ofResult = Optional.of(internshipCandidates2);
        when(internshipCandidatesRepository.save(Mockito.<InternshipCandidates>any()))
                .thenThrow(new RuntimeException("foo"));
        when(internshipCandidatesRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> internshipCandidatesService.acceptCandidates(1L));
        verify(internshipCandidatesRepository).findById(Mockito.<Long>any());
        verify(internshipCandidatesRepository).save(Mockito.<InternshipCandidates>any());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#declineCandidates(Long)}
     */
    @Test
    void testDeclineCandidates() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        etudiant.setCv(new File());
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

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
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

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

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
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer2);
        internshipCandidates2.setState(State.ACCEPTED);
        Optional<InternshipCandidates> ofResult = Optional.of(internshipCandidates2);

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Etudiant etudiant3 = new Etudiant();
        ArrayList<File> cv = new ArrayList<>();
        etudiant3.setCv(new File());
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

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(programme5);
        employeur2.setRole(Role.employer);

        Programme programme6 = new Programme();
        programme6.setDescription("The characteristics of someone or something");
        programme6.setId(1L);
        programme6.setNom("Nom");

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setCv(new File());
        etudiant4.setEmail("jane.doe@example.org");
        etudiant4.setId(1L);
        etudiant4.setInternshipsCandidate(new ArrayList<>());
        etudiant4.setMatricule("Matricule");
        etudiant4.setNom("Nom");
        etudiant4.setPassword("iloveyou");
        etudiant4.setPhone("6625550144");
        etudiant4.setPrenom("Prenom");
        etudiant4.setProgramme(programme6);
        etudiant4.setRole(Role.employer);

        Etudiant etudiant5 = new Etudiant();
        etudiant5.setCv(new File());
        etudiant5.setEmail("jane.doe@example.org");
        etudiant5.setId(1L);
        etudiant5.setInternshipsCandidate(new ArrayList<>());
        etudiant5.setMatricule("Matricule");
        etudiant5.setNom("Nom");
        etudiant5.setPassword("iloveyou");
        etudiant5.setPhone("6625550144");
        etudiant5.setPrenom("Prenom");
        etudiant5.setProgramme(new Programme());
        etudiant5.setRole(Role.employer);

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(new Employeur());
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(new File());
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(new OfferReviewRequest());
        internOffer3.setProgramme(new Programme());
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(etudiant5);
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(1L);
        internshipCandidates3.setInternOffer(internOffer3);
        internshipCandidates3.setState(State.ACCEPTED);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(etudiant4);
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(internshipCandidates3);
        file2.setIsAccepted(State.ACCEPTED);

        Employeur employeur3 = new Employeur();
        employeur3.setEmail("jane.doe@example.org");
        employeur3.setEntreprise("Entreprise");
        employeur3.setId(1L);
        employeur3.setInternOffers(new ArrayList<>());
        employeur3.setNom("Nom");
        employeur3.setPassword("iloveyou");
        employeur3.setPhone("6625550144");
        employeur3.setPrenom("Prenom");
        employeur3.setProgramme(new Programme());
        employeur3.setRole(Role.employer);

        File file3 = new File();
        file3.setContent("AXAXAXAX".getBytes("UTF-8"));
        file3.setEtudiant(new Etudiant());
        file3.setFileName("foo.txt");
        file3.setId(1L);
        file3.setInternshipCandidates(new InternshipCandidates());
        file3.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(new InternOffer());
        offerReviewRequest2.setInternshipmanager(new Internshipmanager());

        Programme programme7 = new Programme();
        programme7.setDescription("The characteristics of someone or something");
        programme7.setId(1L);
        programme7.setNom("Nom");

        InternOffer internOffer4 = new InternOffer();
        internOffer4.setDescription("The characteristics of someone or something");
        internOffer4.setEmployeur(employeur3);
        internOffer4.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer4.setFile(file3);
        internOffer4.setId(1L);
        internOffer4.setInternshipCandidates(new ArrayList<>());
        internOffer4.setLocation("Location");
        internOffer4.setOfferReviewRequest(offerReviewRequest2);
        internOffer4.setProgramme(programme7);
        internOffer4.setSalaryByHour(10.0d);
        internOffer4.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer4.setState(State.ACCEPTED);
        internOffer4.setStatus("Status");
        internOffer4.setTitle("Dr");

        Programme programme8 = new Programme();
        programme8.setDescription("The characteristics of someone or something");
        programme8.setId(1L);
        programme8.setNom("Nom");

        Internshipmanager internshipmanager2 = new Internshipmanager();
        internshipmanager2.setEmail("jane.doe@example.org");
        internshipmanager2.setId(1L);
        internshipmanager2.setNom("Nom");
        internshipmanager2.setPassword("iloveyou");
        internshipmanager2.setPhone("6625550144");
        internshipmanager2.setPrenom("Prenom");
        internshipmanager2.setProgramme(programme8);
        internshipmanager2.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest3 = new OfferReviewRequest();
        offerReviewRequest3.setComment("Comment");
        offerReviewRequest3.setId(1L);
        offerReviewRequest3.setInternOffer(internOffer4);
        offerReviewRequest3.setInternshipmanager(internshipmanager2);

        Programme programme9 = new Programme();
        programme9.setDescription("The characteristics of someone or something");
        programme9.setId(1L);
        programme9.setNom("Nom");

        InternOffer internOffer5 = new InternOffer();
        internOffer5.setDescription("The characteristics of someone or something");
        internOffer5.setEmployeur(employeur2);
        internOffer5.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer5.setFile(file2);
        internOffer5.setId(1L);
        internOffer5.setInternshipCandidates(new ArrayList<>());
        internOffer5.setLocation("Location");
        internOffer5.setOfferReviewRequest(offerReviewRequest3);
        internOffer5.setProgramme(programme9);
        internOffer5.setSalaryByHour(10.0d);
        internOffer5.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer5.setState(State.ACCEPTED);
        internOffer5.setStatus("Status");
        internOffer5.setTitle("Dr");

        InternshipCandidates internshipCandidates4 = new InternshipCandidates();
        internshipCandidates4.setEtudiant(etudiant3);
        internshipCandidates4.setFiles(new ArrayList<>());
        internshipCandidates4.setId(1L);
        internshipCandidates4.setInternOffer(internOffer5);
        internshipCandidates4.setState(State.ACCEPTED);
        when(internshipCandidatesRepository.save(Mockito.<InternshipCandidates>any())).thenReturn(internshipCandidates4);
        when(internshipCandidatesRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        InternshipCandidatesDto actualDeclineCandidatesResult = internshipCandidatesService.declineCandidates(1L);
        verify(internshipCandidatesRepository).findById(Mockito.<Long>any());
        verify(internshipCandidatesRepository).save(Mockito.<InternshipCandidates>any());
        InternOfferDto internOfferJob = actualDeclineCandidatesResult.getInternOfferJob();
        assertEquals("1970-01-01", internOfferJob.getEndDate());
        assertEquals("1970-01-01", internOfferJob.getStartDate());
        EtudiantDtoWithId etudiant6 = actualDeclineCandidatesResult.getEtudiant();
        assertEquals("6625550144", etudiant6.getPhone());
        assertEquals("Dr", internOfferJob.getTitle());
        assertEquals("Entreprise", internOfferJob.getEmployeurEntreprise());
        assertEquals("Location", internOfferJob.getLocation());
        assertEquals("Matricule", etudiant6.getMatricule());
        assertEquals("Nom", internOfferJob.getEmployeurNom());
        assertEquals("Nom", internOfferJob.getProgrammeNom());
        assertEquals("Nom", etudiant6.getNom());
        assertEquals("Prenom", internOfferJob.getEmployeurPrenom());
        assertEquals("Prenom", etudiant6.getPrenom());
        assertEquals("The characteristics of someone or something", internOfferJob.getDescription());
        FileDto file4 = internOfferJob.getFile();
        assertEquals("foo.txt", file4.getFileName());
        assertEquals("jane.doe@example.org", etudiant6.getEmail());
        assertEquals(1L, etudiant6.getProgramme_id());
        assertEquals(1L, file4.getId());
        assertEquals(1L, internOfferJob.getEmployeurId());
        assertEquals(1L, internOfferJob.getId());
        assertEquals(1L, internOfferJob.getOfferReviewRequestId());
        assertEquals(1L, internOfferJob.getProgrammeId());
        assertEquals(1L, actualDeclineCandidatesResult.getId());
        assertEquals(1L, etudiant6.getId());
        assertEquals(State.ACCEPTED, file4.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferJob.getState());
        assertEquals(State.DECLINED, actualDeclineCandidatesResult.getState());
        List<FileDto> files = actualDeclineCandidatesResult.getFiles();
        assertTrue(files.isEmpty());
        assertEquals(cv, internOfferJob.getInternshipCandidates());
        assertSame(files, etudiant6.getCv());
        assertSame(files, etudiant6.getInternships_id());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file4.getContent());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#declineCandidates(Long)}
     */
    @Test
    void testDeclineCandidates2() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        etudiant.setCv(new File());
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

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
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

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

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
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer2);
        internshipCandidates2.setState(State.ACCEPTED);
        Optional<InternshipCandidates> ofResult = Optional.of(internshipCandidates2);
        when(internshipCandidatesRepository.save(Mockito.<InternshipCandidates>any()))
                .thenThrow(new RuntimeException("foo"));
        when(internshipCandidatesRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> internshipCandidatesService.declineCandidates(1L));
        verify(internshipCandidatesRepository).findById(Mockito.<Long>any());
        verify(internshipCandidatesRepository).save(Mockito.<InternshipCandidates>any());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getDeclinedCandidates()}
     */
    @Test
    void testGetDeclinedCandidates() {
        when(internshipCandidatesRepository.findAllDeclined()).thenReturn(new ArrayList<>());
        List<InternshipCandidatesDto> actualDeclinedCandidates = internshipCandidatesService.getDeclinedCandidates();
        verify(internshipCandidatesRepository).findAllDeclined();
        assertTrue(actualDeclinedCandidates.isEmpty());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getDeclinedCandidates()}
     */
    @Test
    void testGetDeclinedCandidates2() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        ArrayList<File> cv = new ArrayList<>();
        etudiant.setCv(new File());
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

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
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

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

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
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer2);
        internshipCandidates2.setState(State.ACCEPTED);

        ArrayList<InternshipCandidates> internshipCandidatesList = new ArrayList<>();
        internshipCandidatesList.add(internshipCandidates2);
        when(internshipCandidatesRepository.findAllDeclined()).thenReturn(internshipCandidatesList);
        List<InternshipCandidatesDto> actualDeclinedCandidates = internshipCandidatesService.getDeclinedCandidates();
        verify(internshipCandidatesRepository).findAllDeclined();
        InternshipCandidatesDto getResult = actualDeclinedCandidates.get(0);
        InternOfferDto internOfferJob = getResult.getInternOfferJob();
        assertEquals("1970-01-01", internOfferJob.getEndDate());
        assertEquals("1970-01-01", internOfferJob.getStartDate());
        EtudiantDtoWithId etudiant3 = getResult.getEtudiant();
        assertEquals("6625550144", etudiant3.getPhone());
        assertEquals("Dr", internOfferJob.getTitle());
        assertEquals("Entreprise", internOfferJob.getEmployeurEntreprise());
        assertEquals("Location", internOfferJob.getLocation());
        assertEquals("Matricule", etudiant3.getMatricule());
        assertEquals("Nom", internOfferJob.getEmployeurNom());
        assertEquals("Nom", internOfferJob.getProgrammeNom());
        assertEquals("Nom", etudiant3.getNom());
        assertEquals("Prenom", internOfferJob.getEmployeurPrenom());
        assertEquals("Prenom", etudiant3.getPrenom());
        assertEquals("The characteristics of someone or something", internOfferJob.getDescription());
        FileDto file2 = internOfferJob.getFile();
        assertEquals("foo.txt", file2.getFileName());
        assertEquals("jane.doe@example.org", etudiant3.getEmail());
        assertEquals(1, actualDeclinedCandidates.size());
        assertEquals(1L, etudiant3.getProgramme_id());
        assertEquals(1L, file2.getId());
        assertEquals(1L, internOfferJob.getEmployeurId());
        assertEquals(1L, internOfferJob.getId());
        assertEquals(1L, internOfferJob.getOfferReviewRequestId());
        assertEquals(1L, internOfferJob.getProgrammeId());
        assertEquals(1L, getResult.getId());
        assertEquals(1L, etudiant3.getId());
        assertEquals(State.ACCEPTED, file2.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferJob.getState());
        assertEquals(State.ACCEPTED, getResult.getState());
        List<FileDto> files = getResult.getFiles();
        assertTrue(files.isEmpty());
        assertEquals(cv, internOfferJob.getInternshipCandidates());
        assertSame(files, etudiant3.getCv());
        assertSame(files, etudiant3.getInternships_id());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file2.getContent());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getDeclinedCandidates()}
     */
    @Test
    void testGetDeclinedCandidates3() {
        when(internshipCandidatesRepository.findAllDeclined()).thenThrow(new EmptyResultDataAccessException(3));
        assertThrows(DatabaseException.class, () -> internshipCandidatesService.getDeclinedCandidates());
        verify(internshipCandidatesRepository).findAllDeclined();
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getDeclinedCandidates()}
     */
    @Test
    void testGetDeclinedCandidates4() {
        when(internshipCandidatesRepository.findAllDeclined()).thenThrow(new NullPointerException("foo"));
        assertThrows(ServiceException.class, () -> internshipCandidatesService.getDeclinedCandidates());
        verify(internshipCandidatesRepository).findAllDeclined();
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getDeclinedCandidates()}
     */
    @Test
    void testGetDeclinedCandidates5() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        etudiant.setCv(new File());
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

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
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

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

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
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Etudiant etudiant3 = new Etudiant();
        ArrayList<File> cv = new ArrayList<>();
        etudiant3.setCv(new File());
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

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(programme5);
        employeur2.setRole(Role.employer);

        Programme programme6 = new Programme();
        programme6.setDescription("The characteristics of someone or something");
        programme6.setId(1L);
        programme6.setNom("Nom");

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setCv(new File());
        etudiant4.setEmail("jane.doe@example.org");
        etudiant4.setId(1L);
        etudiant4.setInternshipsCandidate(new ArrayList<>());
        etudiant4.setMatricule("Matricule");
        etudiant4.setNom("Nom");
        etudiant4.setPassword("iloveyou");
        etudiant4.setPhone("6625550144");
        etudiant4.setPrenom("Prenom");
        etudiant4.setProgramme(programme6);
        etudiant4.setRole(Role.employer);

        Programme programme7 = new Programme();
        programme7.setDescription("The characteristics of someone or something");
        programme7.setId(1L);
        programme7.setNom("Nom");

        Etudiant etudiant5 = new Etudiant();
        etudiant5.setCv(new File());
        etudiant5.setEmail("jane.doe@example.org");
        etudiant5.setId(1L);
        etudiant5.setInternshipsCandidate(new ArrayList<>());
        etudiant5.setMatricule("Matricule");
        etudiant5.setNom("Nom");
        etudiant5.setPassword("iloveyou");
        etudiant5.setPhone("6625550144");
        etudiant5.setPrenom("Prenom");
        etudiant5.setProgramme(programme7);
        etudiant5.setRole(Role.employer);

        Employeur employeur3 = new Employeur();
        employeur3.setEmail("jane.doe@example.org");
        employeur3.setEntreprise("Entreprise");
        employeur3.setId(1L);
        employeur3.setInternOffers(new ArrayList<>());
        employeur3.setNom("Nom");
        employeur3.setPassword("iloveyou");
        employeur3.setPhone("6625550144");
        employeur3.setPrenom("Prenom");
        employeur3.setProgramme(new Programme());
        employeur3.setRole(Role.employer);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(new Etudiant());
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(new InternshipCandidates());
        file2.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(new InternOffer());
        offerReviewRequest2.setInternshipmanager(new Internshipmanager());

        Programme programme8 = new Programme();
        programme8.setDescription("The characteristics of someone or something");
        programme8.setId(1L);
        programme8.setNom("Nom");

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur3);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file2);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest2);
        internOffer3.setProgramme(programme8);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant5);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer3);
        internshipCandidates2.setState(State.ACCEPTED);

        File file3 = new File();
        file3.setContent("AXAXAXAX".getBytes("UTF-8"));
        file3.setEtudiant(etudiant4);
        file3.setFileName("foo.txt");
        file3.setId(1L);
        file3.setInternshipCandidates(internshipCandidates2);
        file3.setIsAccepted(State.ACCEPTED);

        Programme programme9 = new Programme();
        programme9.setDescription("The characteristics of someone or something");
        programme9.setId(1L);
        programme9.setNom("Nom");

        Employeur employeur4 = new Employeur();
        employeur4.setEmail("jane.doe@example.org");
        employeur4.setEntreprise("Entreprise");
        employeur4.setId(1L);
        employeur4.setInternOffers(new ArrayList<>());
        employeur4.setNom("Nom");
        employeur4.setPassword("iloveyou");
        employeur4.setPhone("6625550144");
        employeur4.setPrenom("Prenom");
        employeur4.setProgramme(programme9);
        employeur4.setRole(Role.employer);

        Etudiant etudiant6 = new Etudiant();
        etudiant6.setCv(new File());
        etudiant6.setEmail("jane.doe@example.org");
        etudiant6.setId(1L);
        etudiant6.setInternshipsCandidate(new ArrayList<>());
        etudiant6.setMatricule("Matricule");
        etudiant6.setNom("Nom");
        etudiant6.setPassword("iloveyou");
        etudiant6.setPhone("6625550144");
        etudiant6.setPrenom("Prenom");
        etudiant6.setProgramme(new Programme());
        etudiant6.setRole(Role.employer);

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(new Etudiant());
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(1L);
        internshipCandidates3.setInternOffer(new InternOffer());
        internshipCandidates3.setState(State.ACCEPTED);

        File file4 = new File();
        file4.setContent("AXAXAXAX".getBytes("UTF-8"));
        file4.setEtudiant(etudiant6);
        file4.setFileName("foo.txt");
        file4.setId(1L);
        file4.setInternshipCandidates(internshipCandidates3);
        file4.setIsAccepted(State.ACCEPTED);

        InternOffer internOffer4 = new InternOffer();
        internOffer4.setDescription("The characteristics of someone or something");
        internOffer4.setEmployeur(new Employeur());
        internOffer4.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer4.setFile(new File());
        internOffer4.setId(1L);
        internOffer4.setInternshipCandidates(new ArrayList<>());
        internOffer4.setLocation("Location");
        internOffer4.setOfferReviewRequest(new OfferReviewRequest());
        internOffer4.setProgramme(new Programme());
        internOffer4.setSalaryByHour(10.0d);
        internOffer4.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer4.setState(State.ACCEPTED);
        internOffer4.setStatus("Status");
        internOffer4.setTitle("Dr");

        Internshipmanager internshipmanager2 = new Internshipmanager();
        internshipmanager2.setEmail("jane.doe@example.org");
        internshipmanager2.setId(1L);
        internshipmanager2.setNom("Nom");
        internshipmanager2.setPassword("iloveyou");
        internshipmanager2.setPhone("6625550144");
        internshipmanager2.setPrenom("Prenom");
        internshipmanager2.setProgramme(new Programme());
        internshipmanager2.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest3 = new OfferReviewRequest();
        offerReviewRequest3.setComment("Comment");
        offerReviewRequest3.setId(1L);
        offerReviewRequest3.setInternOffer(internOffer4);
        offerReviewRequest3.setInternshipmanager(internshipmanager2);

        Programme programme10 = new Programme();
        programme10.setDescription("The characteristics of someone or something");
        programme10.setId(1L);
        programme10.setNom("Nom");

        InternOffer internOffer5 = new InternOffer();
        internOffer5.setDescription("The characteristics of someone or something");
        internOffer5.setEmployeur(employeur4);
        internOffer5.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer5.setFile(file4);
        internOffer5.setId(1L);
        internOffer5.setInternshipCandidates(new ArrayList<>());
        internOffer5.setLocation("Location");
        internOffer5.setOfferReviewRequest(offerReviewRequest3);
        internOffer5.setProgramme(programme10);
        internOffer5.setSalaryByHour(10.0d);
        internOffer5.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer5.setState(State.ACCEPTED);
        internOffer5.setStatus("Status");
        internOffer5.setTitle("Dr");

        Programme programme11 = new Programme();
        programme11.setDescription("The characteristics of someone or something");
        programme11.setId(1L);
        programme11.setNom("Nom");

        Internshipmanager internshipmanager3 = new Internshipmanager();
        internshipmanager3.setEmail("jane.doe@example.org");
        internshipmanager3.setId(1L);
        internshipmanager3.setNom("Nom");
        internshipmanager3.setPassword("iloveyou");
        internshipmanager3.setPhone("6625550144");
        internshipmanager3.setPrenom("Prenom");
        internshipmanager3.setProgramme(programme11);
        internshipmanager3.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest4 = new OfferReviewRequest();
        offerReviewRequest4.setComment("Comment");
        offerReviewRequest4.setId(1L);
        offerReviewRequest4.setInternOffer(internOffer5);
        offerReviewRequest4.setInternshipmanager(internshipmanager3);

        Programme programme12 = new Programme();
        programme12.setDescription("The characteristics of someone or something");
        programme12.setId(1L);
        programme12.setNom("Nom");

        InternOffer internOffer6 = new InternOffer();
        internOffer6.setDescription("The characteristics of someone or something");
        internOffer6.setEmployeur(employeur2);
        internOffer6.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer6.setFile(file3);
        internOffer6.setId(1L);
        internOffer6.setInternshipCandidates(new ArrayList<>());
        internOffer6.setLocation("Location");
        internOffer6.setOfferReviewRequest(offerReviewRequest4);
        internOffer6.setProgramme(programme12);
        internOffer6.setSalaryByHour(10.0d);
        internOffer6.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer6.setState(State.ACCEPTED);
        internOffer6.setStatus("Status");
        internOffer6.setTitle("Dr");
        InternshipCandidates internshipCandidates4 = mock(InternshipCandidates.class);
        when(internshipCandidates4.getEtudiant()).thenReturn(etudiant3);
        when(internshipCandidates4.getInternOffer()).thenReturn(internOffer6);
        when(internshipCandidates4.getState()).thenReturn(State.ACCEPTED);
        when(internshipCandidates4.getFiles()).thenReturn(new ArrayList<>());
        when(internshipCandidates4.getId()).thenReturn(1L);
        doNothing().when(internshipCandidates4).setEtudiant(Mockito.<Etudiant>any());
        doNothing().when(internshipCandidates4).setFiles(Mockito.<List<File>>any());
        doNothing().when(internshipCandidates4).setId(anyLong());
        doNothing().when(internshipCandidates4).setInternOffer(Mockito.<InternOffer>any());
        doNothing().when(internshipCandidates4).setState(Mockito.<State>any());
        internshipCandidates4.setEtudiant(etudiant);
        internshipCandidates4.setFiles(new ArrayList<>());
        internshipCandidates4.setId(1L);
        internshipCandidates4.setInternOffer(internOffer2);
        internshipCandidates4.setState(State.ACCEPTED);

        ArrayList<InternshipCandidates> internshipCandidatesList = new ArrayList<>();
        internshipCandidatesList.add(internshipCandidates4);
        when(internshipCandidatesRepository.findAllDeclined()).thenReturn(internshipCandidatesList);
        List<InternshipCandidatesDto> actualDeclinedCandidates = internshipCandidatesService.getDeclinedCandidates();
        verify(internshipCandidates4, atLeast(1)).getEtudiant();
        verify(internshipCandidates4, atLeast(1)).getFiles();
        verify(internshipCandidates4).getId();
        verify(internshipCandidates4, atLeast(1)).getInternOffer();
        verify(internshipCandidates4).getState();
        verify(internshipCandidates4).setEtudiant(Mockito.<Etudiant>any());
        verify(internshipCandidates4).setFiles(Mockito.<List<File>>any());
        verify(internshipCandidates4).setId(anyLong());
        verify(internshipCandidates4).setInternOffer(Mockito.<InternOffer>any());
        verify(internshipCandidates4).setState(Mockito.<State>any());
        verify(internshipCandidatesRepository).findAllDeclined();
        InternshipCandidatesDto getResult = actualDeclinedCandidates.get(0);
        InternOfferDto internOfferJob = getResult.getInternOfferJob();
        assertEquals("1970-01-01", internOfferJob.getEndDate());
        assertEquals("1970-01-01", internOfferJob.getStartDate());
        EtudiantDtoWithId etudiant7 = getResult.getEtudiant();
        assertEquals("6625550144", etudiant7.getPhone());
        assertEquals("Dr", internOfferJob.getTitle());
        assertEquals("Entreprise", internOfferJob.getEmployeurEntreprise());
        assertEquals("Location", internOfferJob.getLocation());
        assertEquals("Matricule", etudiant7.getMatricule());
        assertEquals("Nom", internOfferJob.getEmployeurNom());
        assertEquals("Nom", internOfferJob.getProgrammeNom());
        assertEquals("Nom", etudiant7.getNom());
        assertEquals("Prenom", internOfferJob.getEmployeurPrenom());
        assertEquals("Prenom", etudiant7.getPrenom());
        assertEquals("The characteristics of someone or something", internOfferJob.getDescription());
        FileDto file5 = internOfferJob.getFile();
        assertEquals("foo.txt", file5.getFileName());
        assertEquals("jane.doe@example.org", etudiant7.getEmail());
        assertEquals(1, actualDeclinedCandidates.size());
        assertEquals(1L, etudiant7.getProgramme_id());
        assertEquals(1L, file5.getId());
        assertEquals(1L, internOfferJob.getEmployeurId());
        assertEquals(1L, internOfferJob.getId());
        assertEquals(1L, internOfferJob.getOfferReviewRequestId());
        assertEquals(1L, internOfferJob.getProgrammeId());
        assertEquals(1L, getResult.getId());
        assertEquals(1L, etudiant7.getId());
        assertEquals(State.ACCEPTED, file5.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferJob.getState());
        assertEquals(State.ACCEPTED, getResult.getState());
        List<FileDto> files = getResult.getFiles();
        assertTrue(files.isEmpty());
        assertEquals(cv, internOfferJob.getInternshipCandidates());
        assertSame(files, etudiant7.getCv());
        assertSame(files, etudiant7.getInternships_id());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file5.getContent());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getPendingCandidates()}
     */
    @Test
    void testGetPendingCandidates() {
        when(internshipCandidatesRepository.findAllPending()).thenReturn(new ArrayList<>());
        List<InternshipCandidatesDto> actualPendingCandidates = internshipCandidatesService.getPendingCandidates();
        verify(internshipCandidatesRepository).findAllPending();
        assertTrue(actualPendingCandidates.isEmpty());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getPendingCandidates()}
     */
    @Test
    void testGetPendingCandidates2() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        ArrayList<File> cv = new ArrayList<>();
        etudiant.setCv(new File());
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

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
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

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

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
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer2);
        internshipCandidates2.setState(State.ACCEPTED);

        ArrayList<InternshipCandidates> internshipCandidatesList = new ArrayList<>();
        internshipCandidatesList.add(internshipCandidates2);
        when(internshipCandidatesRepository.findAllPending()).thenReturn(internshipCandidatesList);
        List<InternshipCandidatesDto> actualPendingCandidates = internshipCandidatesService.getPendingCandidates();
        verify(internshipCandidatesRepository).findAllPending();
        InternshipCandidatesDto getResult = actualPendingCandidates.get(0);
        InternOfferDto internOfferJob = getResult.getInternOfferJob();
        assertEquals("1970-01-01", internOfferJob.getEndDate());
        assertEquals("1970-01-01", internOfferJob.getStartDate());
        EtudiantDtoWithId etudiant3 = getResult.getEtudiant();
        assertEquals("6625550144", etudiant3.getPhone());
        assertEquals("Dr", internOfferJob.getTitle());
        assertEquals("Entreprise", internOfferJob.getEmployeurEntreprise());
        assertEquals("Location", internOfferJob.getLocation());
        assertEquals("Matricule", etudiant3.getMatricule());
        assertEquals("Nom", internOfferJob.getEmployeurNom());
        assertEquals("Nom", internOfferJob.getProgrammeNom());
        assertEquals("Nom", etudiant3.getNom());
        assertEquals("Prenom", internOfferJob.getEmployeurPrenom());
        assertEquals("Prenom", etudiant3.getPrenom());
        assertEquals("The characteristics of someone or something", internOfferJob.getDescription());
        FileDto file2 = internOfferJob.getFile();
        assertEquals("foo.txt", file2.getFileName());
        assertEquals("jane.doe@example.org", etudiant3.getEmail());
        assertEquals(1, actualPendingCandidates.size());
        assertEquals(1L, etudiant3.getProgramme_id());
        assertEquals(1L, file2.getId());
        assertEquals(1L, internOfferJob.getEmployeurId());
        assertEquals(1L, internOfferJob.getId());
        assertEquals(1L, internOfferJob.getOfferReviewRequestId());
        assertEquals(1L, internOfferJob.getProgrammeId());
        assertEquals(1L, getResult.getId());
        assertEquals(1L, etudiant3.getId());
        assertEquals(State.ACCEPTED, file2.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferJob.getState());
        assertEquals(State.ACCEPTED, getResult.getState());
        List<FileDto> files = getResult.getFiles();
        assertTrue(files.isEmpty());
        assertEquals(cv, internOfferJob.getInternshipCandidates());
        assertSame(files, etudiant3.getCv());
        assertSame(files, etudiant3.getInternships_id());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file2.getContent());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getPendingCandidates()}
     */
    @Test
    void testGetPendingCandidates3() {
        when(internshipCandidatesRepository.findAllPending()).thenThrow(new EmptyResultDataAccessException(3));
        assertThrows(DatabaseException.class, () -> internshipCandidatesService.getPendingCandidates());
        verify(internshipCandidatesRepository).findAllPending();
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getPendingCandidates()}
     */
    @Test
    void testGetPendingCandidates4() {
        when(internshipCandidatesRepository.findAllPending()).thenThrow(new NullPointerException("foo"));
        assertThrows(ServiceException.class, () -> internshipCandidatesService.getPendingCandidates());
        verify(internshipCandidatesRepository).findAllPending();
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getPendingCandidates()}
     */
    @Test
    void testGetPendingCandidates5() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        etudiant.setCv(new File());
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

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
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

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

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
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Etudiant etudiant3 = new Etudiant();
        ArrayList<File> cv = new ArrayList<>();
        etudiant3.setCv(new File());
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

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(programme5);
        employeur2.setRole(Role.employer);

        Programme programme6 = new Programme();
        programme6.setDescription("The characteristics of someone or something");
        programme6.setId(1L);
        programme6.setNom("Nom");

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setCv(new File());
        etudiant4.setEmail("jane.doe@example.org");
        etudiant4.setId(1L);
        etudiant4.setInternshipsCandidate(new ArrayList<>());
        etudiant4.setMatricule("Matricule");
        etudiant4.setNom("Nom");
        etudiant4.setPassword("iloveyou");
        etudiant4.setPhone("6625550144");
        etudiant4.setPrenom("Prenom");
        etudiant4.setProgramme(programme6);
        etudiant4.setRole(Role.employer);

        Programme programme7 = new Programme();
        programme7.setDescription("The characteristics of someone or something");
        programme7.setId(1L);
        programme7.setNom("Nom");

        Etudiant etudiant5 = new Etudiant();
        etudiant5.setCv(new File());
        etudiant5.setEmail("jane.doe@example.org");
        etudiant5.setId(1L);
        etudiant5.setInternshipsCandidate(new ArrayList<>());
        etudiant5.setMatricule("Matricule");
        etudiant5.setNom("Nom");
        etudiant5.setPassword("iloveyou");
        etudiant5.setPhone("6625550144");
        etudiant5.setPrenom("Prenom");
        etudiant5.setProgramme(programme7);
        etudiant5.setRole(Role.employer);

        Employeur employeur3 = new Employeur();
        employeur3.setEmail("jane.doe@example.org");
        employeur3.setEntreprise("Entreprise");
        employeur3.setId(1L);
        employeur3.setInternOffers(new ArrayList<>());
        employeur3.setNom("Nom");
        employeur3.setPassword("iloveyou");
        employeur3.setPhone("6625550144");
        employeur3.setPrenom("Prenom");
        employeur3.setProgramme(new Programme());
        employeur3.setRole(Role.employer);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(new Etudiant());
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(new InternshipCandidates());
        file2.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(new InternOffer());
        offerReviewRequest2.setInternshipmanager(new Internshipmanager());

        Programme programme8 = new Programme();
        programme8.setDescription("The characteristics of someone or something");
        programme8.setId(1L);
        programme8.setNom("Nom");

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur3);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file2);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest2);
        internOffer3.setProgramme(programme8);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant5);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer3);
        internshipCandidates2.setState(State.ACCEPTED);

        File file3 = new File();
        file3.setContent("AXAXAXAX".getBytes("UTF-8"));
        file3.setEtudiant(etudiant4);
        file3.setFileName("foo.txt");
        file3.setId(1L);
        file3.setInternshipCandidates(internshipCandidates2);
        file3.setIsAccepted(State.ACCEPTED);

        Programme programme9 = new Programme();
        programme9.setDescription("The characteristics of someone or something");
        programme9.setId(1L);
        programme9.setNom("Nom");

        Employeur employeur4 = new Employeur();
        employeur4.setEmail("jane.doe@example.org");
        employeur4.setEntreprise("Entreprise");
        employeur4.setId(1L);
        employeur4.setInternOffers(new ArrayList<>());
        employeur4.setNom("Nom");
        employeur4.setPassword("iloveyou");
        employeur4.setPhone("6625550144");
        employeur4.setPrenom("Prenom");
        employeur4.setProgramme(programme9);
        employeur4.setRole(Role.employer);

        Etudiant etudiant6 = new Etudiant();
        etudiant6.setCv(new File());
        etudiant6.setEmail("jane.doe@example.org");
        etudiant6.setId(1L);
        etudiant6.setInternshipsCandidate(new ArrayList<>());
        etudiant6.setMatricule("Matricule");
        etudiant6.setNom("Nom");
        etudiant6.setPassword("iloveyou");
        etudiant6.setPhone("6625550144");
        etudiant6.setPrenom("Prenom");
        etudiant6.setProgramme(new Programme());
        etudiant6.setRole(Role.employer);

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(new Etudiant());
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(1L);
        internshipCandidates3.setInternOffer(new InternOffer());
        internshipCandidates3.setState(State.ACCEPTED);

        File file4 = new File();
        file4.setContent("AXAXAXAX".getBytes("UTF-8"));
        file4.setEtudiant(etudiant6);
        file4.setFileName("foo.txt");
        file4.setId(1L);
        file4.setInternshipCandidates(internshipCandidates3);
        file4.setIsAccepted(State.ACCEPTED);

        InternOffer internOffer4 = new InternOffer();
        internOffer4.setDescription("The characteristics of someone or something");
        internOffer4.setEmployeur(new Employeur());
        internOffer4.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer4.setFile(new File());
        internOffer4.setId(1L);
        internOffer4.setInternshipCandidates(new ArrayList<>());
        internOffer4.setLocation("Location");
        internOffer4.setOfferReviewRequest(new OfferReviewRequest());
        internOffer4.setProgramme(new Programme());
        internOffer4.setSalaryByHour(10.0d);
        internOffer4.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer4.setState(State.ACCEPTED);
        internOffer4.setStatus("Status");
        internOffer4.setTitle("Dr");

        Internshipmanager internshipmanager2 = new Internshipmanager();
        internshipmanager2.setEmail("jane.doe@example.org");
        internshipmanager2.setId(1L);
        internshipmanager2.setNom("Nom");
        internshipmanager2.setPassword("iloveyou");
        internshipmanager2.setPhone("6625550144");
        internshipmanager2.setPrenom("Prenom");
        internshipmanager2.setProgramme(new Programme());
        internshipmanager2.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest3 = new OfferReviewRequest();
        offerReviewRequest3.setComment("Comment");
        offerReviewRequest3.setId(1L);
        offerReviewRequest3.setInternOffer(internOffer4);
        offerReviewRequest3.setInternshipmanager(internshipmanager2);

        Programme programme10 = new Programme();
        programme10.setDescription("The characteristics of someone or something");
        programme10.setId(1L);
        programme10.setNom("Nom");

        InternOffer internOffer5 = new InternOffer();
        internOffer5.setDescription("The characteristics of someone or something");
        internOffer5.setEmployeur(employeur4);
        internOffer5.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer5.setFile(file4);
        internOffer5.setId(1L);
        internOffer5.setInternshipCandidates(new ArrayList<>());
        internOffer5.setLocation("Location");
        internOffer5.setOfferReviewRequest(offerReviewRequest3);
        internOffer5.setProgramme(programme10);
        internOffer5.setSalaryByHour(10.0d);
        internOffer5.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer5.setState(State.ACCEPTED);
        internOffer5.setStatus("Status");
        internOffer5.setTitle("Dr");

        Programme programme11 = new Programme();
        programme11.setDescription("The characteristics of someone or something");
        programme11.setId(1L);
        programme11.setNom("Nom");

        Internshipmanager internshipmanager3 = new Internshipmanager();
        internshipmanager3.setEmail("jane.doe@example.org");
        internshipmanager3.setId(1L);
        internshipmanager3.setNom("Nom");
        internshipmanager3.setPassword("iloveyou");
        internshipmanager3.setPhone("6625550144");
        internshipmanager3.setPrenom("Prenom");
        internshipmanager3.setProgramme(programme11);
        internshipmanager3.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest4 = new OfferReviewRequest();
        offerReviewRequest4.setComment("Comment");
        offerReviewRequest4.setId(1L);
        offerReviewRequest4.setInternOffer(internOffer5);
        offerReviewRequest4.setInternshipmanager(internshipmanager3);

        Programme programme12 = new Programme();
        programme12.setDescription("The characteristics of someone or something");
        programme12.setId(1L);
        programme12.setNom("Nom");

        InternOffer internOffer6 = new InternOffer();
        internOffer6.setDescription("The characteristics of someone or something");
        internOffer6.setEmployeur(employeur2);
        internOffer6.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer6.setFile(file3);
        internOffer6.setId(1L);
        internOffer6.setInternshipCandidates(new ArrayList<>());
        internOffer6.setLocation("Location");
        internOffer6.setOfferReviewRequest(offerReviewRequest4);
        internOffer6.setProgramme(programme12);
        internOffer6.setSalaryByHour(10.0d);
        internOffer6.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer6.setState(State.ACCEPTED);
        internOffer6.setStatus("Status");
        internOffer6.setTitle("Dr");
        InternshipCandidates internshipCandidates4 = mock(InternshipCandidates.class);
        when(internshipCandidates4.getEtudiant()).thenReturn(etudiant3);
        when(internshipCandidates4.getInternOffer()).thenReturn(internOffer6);
        when(internshipCandidates4.getState()).thenReturn(State.ACCEPTED);
        when(internshipCandidates4.getFiles()).thenReturn(new ArrayList<>());
        when(internshipCandidates4.getId()).thenReturn(1L);
        doNothing().when(internshipCandidates4).setEtudiant(Mockito.<Etudiant>any());
        doNothing().when(internshipCandidates4).setFiles(Mockito.<List<File>>any());
        doNothing().when(internshipCandidates4).setId(anyLong());
        doNothing().when(internshipCandidates4).setInternOffer(Mockito.<InternOffer>any());
        doNothing().when(internshipCandidates4).setState(Mockito.<State>any());
        internshipCandidates4.setEtudiant(etudiant);
        internshipCandidates4.setFiles(new ArrayList<>());
        internshipCandidates4.setId(1L);
        internshipCandidates4.setInternOffer(internOffer2);
        internshipCandidates4.setState(State.ACCEPTED);

        ArrayList<InternshipCandidates> internshipCandidatesList = new ArrayList<>();
        internshipCandidatesList.add(internshipCandidates4);
        when(internshipCandidatesRepository.findAllPending()).thenReturn(internshipCandidatesList);
        List<InternshipCandidatesDto> actualPendingCandidates = internshipCandidatesService.getPendingCandidates();
        verify(internshipCandidates4, atLeast(1)).getEtudiant();
        verify(internshipCandidates4, atLeast(1)).getFiles();
        verify(internshipCandidates4).getId();
        verify(internshipCandidates4, atLeast(1)).getInternOffer();
        verify(internshipCandidates4).getState();
        verify(internshipCandidates4).setEtudiant(Mockito.<Etudiant>any());
        verify(internshipCandidates4).setFiles(Mockito.<List<File>>any());
        verify(internshipCandidates4).setId(anyLong());
        verify(internshipCandidates4).setInternOffer(Mockito.<InternOffer>any());
        verify(internshipCandidates4).setState(Mockito.<State>any());
        verify(internshipCandidatesRepository).findAllPending();
        InternshipCandidatesDto getResult = actualPendingCandidates.get(0);
        InternOfferDto internOfferJob = getResult.getInternOfferJob();
        assertEquals("1970-01-01", internOfferJob.getEndDate());
        assertEquals("1970-01-01", internOfferJob.getStartDate());
        EtudiantDtoWithId etudiant7 = getResult.getEtudiant();
        assertEquals("6625550144", etudiant7.getPhone());
        assertEquals("Dr", internOfferJob.getTitle());
        assertEquals("Entreprise", internOfferJob.getEmployeurEntreprise());
        assertEquals("Location", internOfferJob.getLocation());
        assertEquals("Matricule", etudiant7.getMatricule());
        assertEquals("Nom", internOfferJob.getEmployeurNom());
        assertEquals("Nom", internOfferJob.getProgrammeNom());
        assertEquals("Nom", etudiant7.getNom());
        assertEquals("Prenom", internOfferJob.getEmployeurPrenom());
        assertEquals("Prenom", etudiant7.getPrenom());
        assertEquals("The characteristics of someone or something", internOfferJob.getDescription());
        FileDto file5 = internOfferJob.getFile();
        assertEquals("foo.txt", file5.getFileName());
        assertEquals("jane.doe@example.org", etudiant7.getEmail());
        assertEquals(1, actualPendingCandidates.size());
        assertEquals(1L, etudiant7.getProgramme_id());
        assertEquals(1L, file5.getId());
        assertEquals(1L, internOfferJob.getEmployeurId());
        assertEquals(1L, internOfferJob.getId());
        assertEquals(1L, internOfferJob.getOfferReviewRequestId());
        assertEquals(1L, internOfferJob.getProgrammeId());
        assertEquals(1L, getResult.getId());
        assertEquals(1L, etudiant7.getId());
        assertEquals(State.ACCEPTED, file5.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferJob.getState());
        assertEquals(State.ACCEPTED, getResult.getState());
        List<FileDto> files = getResult.getFiles();
        assertTrue(files.isEmpty());
        assertEquals(cv, internOfferJob.getInternshipCandidates());
        assertSame(files, etudiant7.getCv());
        assertSame(files, etudiant7.getInternships_id());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file5.getContent());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getAcceptedCandidates()}
     */
    @Test
    void testGetAcceptedCandidates() {
        when(internshipCandidatesRepository.findAllAccepted()).thenReturn(new ArrayList<>());
        List<InternshipCandidatesDto> actualAcceptedCandidates = internshipCandidatesService.getAcceptedCandidates();
        verify(internshipCandidatesRepository).findAllAccepted();
        assertTrue(actualAcceptedCandidates.isEmpty());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getAcceptedCandidates()}
     */
    @Test
    void testGetAcceptedCandidates2() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        ArrayList<File> cv = new ArrayList<>();
        etudiant.setCv(new File());
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

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
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

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

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
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer2);
        internshipCandidates2.setState(State.ACCEPTED);

        ArrayList<InternshipCandidates> internshipCandidatesList = new ArrayList<>();
        internshipCandidatesList.add(internshipCandidates2);
        when(internshipCandidatesRepository.findAllAccepted()).thenReturn(internshipCandidatesList);
        List<InternshipCandidatesDto> actualAcceptedCandidates = internshipCandidatesService.getAcceptedCandidates();
        verify(internshipCandidatesRepository).findAllAccepted();
        InternshipCandidatesDto getResult = actualAcceptedCandidates.get(0);
        InternOfferDto internOfferJob = getResult.getInternOfferJob();
        assertEquals("1970-01-01", internOfferJob.getEndDate());
        assertEquals("1970-01-01", internOfferJob.getStartDate());
        EtudiantDtoWithId etudiant3 = getResult.getEtudiant();
        assertEquals("6625550144", etudiant3.getPhone());
        assertEquals("Dr", internOfferJob.getTitle());
        assertEquals("Entreprise", internOfferJob.getEmployeurEntreprise());
        assertEquals("Location", internOfferJob.getLocation());
        assertEquals("Matricule", etudiant3.getMatricule());
        assertEquals("Nom", internOfferJob.getEmployeurNom());
        assertEquals("Nom", internOfferJob.getProgrammeNom());
        assertEquals("Nom", etudiant3.getNom());
        assertEquals("Prenom", internOfferJob.getEmployeurPrenom());
        assertEquals("Prenom", etudiant3.getPrenom());
        assertEquals("The characteristics of someone or something", internOfferJob.getDescription());
        FileDto file2 = internOfferJob.getFile();
        assertEquals("foo.txt", file2.getFileName());
        assertEquals("jane.doe@example.org", etudiant3.getEmail());
        assertEquals(1, actualAcceptedCandidates.size());
        assertEquals(1L, etudiant3.getProgramme_id());
        assertEquals(1L, file2.getId());
        assertEquals(1L, internOfferJob.getEmployeurId());
        assertEquals(1L, internOfferJob.getId());
        assertEquals(1L, internOfferJob.getOfferReviewRequestId());
        assertEquals(1L, internOfferJob.getProgrammeId());
        assertEquals(1L, getResult.getId());
        assertEquals(1L, etudiant3.getId());
        assertEquals(State.ACCEPTED, file2.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferJob.getState());
        assertEquals(State.ACCEPTED, getResult.getState());
        List<FileDto> files = getResult.getFiles();
        assertTrue(files.isEmpty());
        assertEquals(cv, internOfferJob.getInternshipCandidates());
        assertSame(files, etudiant3.getCv());
        assertSame(files, etudiant3.getInternships_id());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file2.getContent());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getAcceptedCandidates()}
     */
    @Test
    void testGetAcceptedCandidates3() {
        when(internshipCandidatesRepository.findAllAccepted()).thenThrow(new EmptyResultDataAccessException(3));
        assertThrows(DatabaseException.class, () -> internshipCandidatesService.getAcceptedCandidates());
        verify(internshipCandidatesRepository).findAllAccepted();
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getAcceptedCandidates()}
     */
    @Test
    void testGetAcceptedCandidates4() {
        when(internshipCandidatesRepository.findAllAccepted()).thenThrow(new NullPointerException("foo"));
        assertThrows(ServiceException.class, () -> internshipCandidatesService.getAcceptedCandidates());
        verify(internshipCandidatesRepository).findAllAccepted();
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getAcceptedCandidates()}
     */
    @Test
    void testGetAcceptedCandidates5() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        etudiant.setCv(new File());
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

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
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

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

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
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Etudiant etudiant3 = new Etudiant();
        ArrayList<File> cv = new ArrayList<>();
        etudiant3.setCv(new File());
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

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(programme5);
        employeur2.setRole(Role.employer);

        Programme programme6 = new Programme();
        programme6.setDescription("The characteristics of someone or something");
        programme6.setId(1L);
        programme6.setNom("Nom");

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setCv(new File());
        etudiant4.setEmail("jane.doe@example.org");
        etudiant4.setId(1L);
        etudiant4.setInternshipsCandidate(new ArrayList<>());
        etudiant4.setMatricule("Matricule");
        etudiant4.setNom("Nom");
        etudiant4.setPassword("iloveyou");
        etudiant4.setPhone("6625550144");
        etudiant4.setPrenom("Prenom");
        etudiant4.setProgramme(programme6);
        etudiant4.setRole(Role.employer);

        Programme programme7 = new Programme();
        programme7.setDescription("The characteristics of someone or something");
        programme7.setId(1L);
        programme7.setNom("Nom");

        Etudiant etudiant5 = new Etudiant();
        etudiant5.setCv(new File());
        etudiant5.setEmail("jane.doe@example.org");
        etudiant5.setId(1L);
        etudiant5.setInternshipsCandidate(new ArrayList<>());
        etudiant5.setMatricule("Matricule");
        etudiant5.setNom("Nom");
        etudiant5.setPassword("iloveyou");
        etudiant5.setPhone("6625550144");
        etudiant5.setPrenom("Prenom");
        etudiant5.setProgramme(programme7);
        etudiant5.setRole(Role.employer);

        Employeur employeur3 = new Employeur();
        employeur3.setEmail("jane.doe@example.org");
        employeur3.setEntreprise("Entreprise");
        employeur3.setId(1L);
        employeur3.setInternOffers(new ArrayList<>());
        employeur3.setNom("Nom");
        employeur3.setPassword("iloveyou");
        employeur3.setPhone("6625550144");
        employeur3.setPrenom("Prenom");
        employeur3.setProgramme(new Programme());
        employeur3.setRole(Role.employer);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(new Etudiant());
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(new InternshipCandidates());
        file2.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(new InternOffer());
        offerReviewRequest2.setInternshipmanager(new Internshipmanager());

        Programme programme8 = new Programme();
        programme8.setDescription("The characteristics of someone or something");
        programme8.setId(1L);
        programme8.setNom("Nom");

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur3);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file2);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest2);
        internOffer3.setProgramme(programme8);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant5);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer3);
        internshipCandidates2.setState(State.ACCEPTED);

        File file3 = new File();
        file3.setContent("AXAXAXAX".getBytes("UTF-8"));
        file3.setEtudiant(etudiant4);
        file3.setFileName("foo.txt");
        file3.setId(1L);
        file3.setInternshipCandidates(internshipCandidates2);
        file3.setIsAccepted(State.ACCEPTED);

        Programme programme9 = new Programme();
        programme9.setDescription("The characteristics of someone or something");
        programme9.setId(1L);
        programme9.setNom("Nom");

        Employeur employeur4 = new Employeur();
        employeur4.setEmail("jane.doe@example.org");
        employeur4.setEntreprise("Entreprise");
        employeur4.setId(1L);
        employeur4.setInternOffers(new ArrayList<>());
        employeur4.setNom("Nom");
        employeur4.setPassword("iloveyou");
        employeur4.setPhone("6625550144");
        employeur4.setPrenom("Prenom");
        employeur4.setProgramme(programme9);
        employeur4.setRole(Role.employer);

        Etudiant etudiant6 = new Etudiant();
        etudiant6.setCv(new File());
        etudiant6.setEmail("jane.doe@example.org");
        etudiant6.setId(1L);
        etudiant6.setInternshipsCandidate(new ArrayList<>());
        etudiant6.setMatricule("Matricule");
        etudiant6.setNom("Nom");
        etudiant6.setPassword("iloveyou");
        etudiant6.setPhone("6625550144");
        etudiant6.setPrenom("Prenom");
        etudiant6.setProgramme(new Programme());
        etudiant6.setRole(Role.employer);

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(new Etudiant());
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(1L);
        internshipCandidates3.setInternOffer(new InternOffer());
        internshipCandidates3.setState(State.ACCEPTED);

        File file4 = new File();
        file4.setContent("AXAXAXAX".getBytes("UTF-8"));
        file4.setEtudiant(etudiant6);
        file4.setFileName("foo.txt");
        file4.setId(1L);
        file4.setInternshipCandidates(internshipCandidates3);
        file4.setIsAccepted(State.ACCEPTED);

        InternOffer internOffer4 = new InternOffer();
        internOffer4.setDescription("The characteristics of someone or something");
        internOffer4.setEmployeur(new Employeur());
        internOffer4.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer4.setFile(new File());
        internOffer4.setId(1L);
        internOffer4.setInternshipCandidates(new ArrayList<>());
        internOffer4.setLocation("Location");
        internOffer4.setOfferReviewRequest(new OfferReviewRequest());
        internOffer4.setProgramme(new Programme());
        internOffer4.setSalaryByHour(10.0d);
        internOffer4.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer4.setState(State.ACCEPTED);
        internOffer4.setStatus("Status");
        internOffer4.setTitle("Dr");

        Internshipmanager internshipmanager2 = new Internshipmanager();
        internshipmanager2.setEmail("jane.doe@example.org");
        internshipmanager2.setId(1L);
        internshipmanager2.setNom("Nom");
        internshipmanager2.setPassword("iloveyou");
        internshipmanager2.setPhone("6625550144");
        internshipmanager2.setPrenom("Prenom");
        internshipmanager2.setProgramme(new Programme());
        internshipmanager2.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest3 = new OfferReviewRequest();
        offerReviewRequest3.setComment("Comment");
        offerReviewRequest3.setId(1L);
        offerReviewRequest3.setInternOffer(internOffer4);
        offerReviewRequest3.setInternshipmanager(internshipmanager2);

        Programme programme10 = new Programme();
        programme10.setDescription("The characteristics of someone or something");
        programme10.setId(1L);
        programme10.setNom("Nom");

        InternOffer internOffer5 = new InternOffer();
        internOffer5.setDescription("The characteristics of someone or something");
        internOffer5.setEmployeur(employeur4);
        internOffer5.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer5.setFile(file4);
        internOffer5.setId(1L);
        internOffer5.setInternshipCandidates(new ArrayList<>());
        internOffer5.setLocation("Location");
        internOffer5.setOfferReviewRequest(offerReviewRequest3);
        internOffer5.setProgramme(programme10);
        internOffer5.setSalaryByHour(10.0d);
        internOffer5.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer5.setState(State.ACCEPTED);
        internOffer5.setStatus("Status");
        internOffer5.setTitle("Dr");

        Programme programme11 = new Programme();
        programme11.setDescription("The characteristics of someone or something");
        programme11.setId(1L);
        programme11.setNom("Nom");

        Internshipmanager internshipmanager3 = new Internshipmanager();
        internshipmanager3.setEmail("jane.doe@example.org");
        internshipmanager3.setId(1L);
        internshipmanager3.setNom("Nom");
        internshipmanager3.setPassword("iloveyou");
        internshipmanager3.setPhone("6625550144");
        internshipmanager3.setPrenom("Prenom");
        internshipmanager3.setProgramme(programme11);
        internshipmanager3.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest4 = new OfferReviewRequest();
        offerReviewRequest4.setComment("Comment");
        offerReviewRequest4.setId(1L);
        offerReviewRequest4.setInternOffer(internOffer5);
        offerReviewRequest4.setInternshipmanager(internshipmanager3);

        Programme programme12 = new Programme();
        programme12.setDescription("The characteristics of someone or something");
        programme12.setId(1L);
        programme12.setNom("Nom");

        InternOffer internOffer6 = new InternOffer();
        internOffer6.setDescription("The characteristics of someone or something");
        internOffer6.setEmployeur(employeur2);
        internOffer6.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer6.setFile(file3);
        internOffer6.setId(1L);
        internOffer6.setInternshipCandidates(new ArrayList<>());
        internOffer6.setLocation("Location");
        internOffer6.setOfferReviewRequest(offerReviewRequest4);
        internOffer6.setProgramme(programme12);
        internOffer6.setSalaryByHour(10.0d);
        internOffer6.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer6.setState(State.ACCEPTED);
        internOffer6.setStatus("Status");
        internOffer6.setTitle("Dr");
        InternshipCandidates internshipCandidates4 = mock(InternshipCandidates.class);
        when(internshipCandidates4.getEtudiant()).thenReturn(etudiant3);
        when(internshipCandidates4.getInternOffer()).thenReturn(internOffer6);
        when(internshipCandidates4.getState()).thenReturn(State.ACCEPTED);
        when(internshipCandidates4.getFiles()).thenReturn(new ArrayList<>());
        when(internshipCandidates4.getId()).thenReturn(1L);
        doNothing().when(internshipCandidates4).setEtudiant(Mockito.<Etudiant>any());
        doNothing().when(internshipCandidates4).setFiles(Mockito.<List<File>>any());
        doNothing().when(internshipCandidates4).setId(anyLong());
        doNothing().when(internshipCandidates4).setInternOffer(Mockito.<InternOffer>any());
        doNothing().when(internshipCandidates4).setState(Mockito.<State>any());
        internshipCandidates4.setEtudiant(etudiant);
        internshipCandidates4.setFiles(new ArrayList<>());
        internshipCandidates4.setId(1L);
        internshipCandidates4.setInternOffer(internOffer2);
        internshipCandidates4.setState(State.ACCEPTED);

        ArrayList<InternshipCandidates> internshipCandidatesList = new ArrayList<>();
        internshipCandidatesList.add(internshipCandidates4);
        when(internshipCandidatesRepository.findAllAccepted()).thenReturn(internshipCandidatesList);
        List<InternshipCandidatesDto> actualAcceptedCandidates = internshipCandidatesService.getAcceptedCandidates();
        verify(internshipCandidates4, atLeast(1)).getEtudiant();
        verify(internshipCandidates4, atLeast(1)).getFiles();
        verify(internshipCandidates4).getId();
        verify(internshipCandidates4, atLeast(1)).getInternOffer();
        verify(internshipCandidates4).getState();
        verify(internshipCandidates4).setEtudiant(Mockito.<Etudiant>any());
        verify(internshipCandidates4).setFiles(Mockito.<List<File>>any());
        verify(internshipCandidates4).setId(anyLong());
        verify(internshipCandidates4).setInternOffer(Mockito.<InternOffer>any());
        verify(internshipCandidates4).setState(Mockito.<State>any());
        verify(internshipCandidatesRepository).findAllAccepted();
        InternshipCandidatesDto getResult = actualAcceptedCandidates.get(0);
        InternOfferDto internOfferJob = getResult.getInternOfferJob();
        assertEquals("1970-01-01", internOfferJob.getEndDate());
        assertEquals("1970-01-01", internOfferJob.getStartDate());
        EtudiantDtoWithId etudiant7 = getResult.getEtudiant();
        assertEquals("6625550144", etudiant7.getPhone());
        assertEquals("Dr", internOfferJob.getTitle());
        assertEquals("Entreprise", internOfferJob.getEmployeurEntreprise());
        assertEquals("Location", internOfferJob.getLocation());
        assertEquals("Matricule", etudiant7.getMatricule());
        assertEquals("Nom", internOfferJob.getEmployeurNom());
        assertEquals("Nom", internOfferJob.getProgrammeNom());
        assertEquals("Nom", etudiant7.getNom());
        assertEquals("Prenom", internOfferJob.getEmployeurPrenom());
        assertEquals("Prenom", etudiant7.getPrenom());
        assertEquals("The characteristics of someone or something", internOfferJob.getDescription());
        FileDto file5 = internOfferJob.getFile();
        assertEquals("foo.txt", file5.getFileName());
        assertEquals("jane.doe@example.org", etudiant7.getEmail());
        assertEquals(1, actualAcceptedCandidates.size());
        assertEquals(1L, etudiant7.getProgramme_id());
        assertEquals(1L, file5.getId());
        assertEquals(1L, internOfferJob.getEmployeurId());
        assertEquals(1L, internOfferJob.getId());
        assertEquals(1L, internOfferJob.getOfferReviewRequestId());
        assertEquals(1L, internOfferJob.getProgrammeId());
        assertEquals(1L, getResult.getId());
        assertEquals(1L, etudiant7.getId());
        assertEquals(State.ACCEPTED, file5.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferJob.getState());
        assertEquals(State.ACCEPTED, getResult.getState());
        List<FileDto> files = getResult.getFiles();
        assertTrue(files.isEmpty());
        assertEquals(cv, internOfferJob.getInternshipCandidates());
        assertSame(files, etudiant7.getCv());
        assertSame(files, etudiant7.getInternships_id());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file5.getContent());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getInternshipCandidateById(long)}
     */
    @Test
    void testGetInternshipCandidateById() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        etudiant.setCv(new File());
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

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
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

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

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
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer2);
        internshipCandidates2.setState(State.ACCEPTED);
        Optional<InternshipCandidates> ofResult = Optional.of(internshipCandidates2);
        when(internshipCandidatesRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        internshipCandidatesService.getInternshipCandidateById(1L);
        verify(internshipCandidatesRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getCandidates()}
     */
    @Test
    void testGetCandidates() {
        when(internshipCandidatesRepository.findAll()).thenReturn(new ArrayList<>());
        List<InternshipCandidatesDto> actualCandidates = internshipCandidatesService.getCandidates();
        verify(internshipCandidatesRepository).findAll();
        assertTrue(actualCandidates.isEmpty());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getCandidates()}
     */
    @Test
    void testGetCandidates2() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        ArrayList<File> cv = new ArrayList<>();
        etudiant.setCv(new File());
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

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
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

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

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
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer2);
        internshipCandidates2.setState(State.ACCEPTED);

        ArrayList<InternshipCandidates> internshipCandidatesList = new ArrayList<>();
        internshipCandidatesList.add(internshipCandidates2);
        when(internshipCandidatesRepository.findAll()).thenReturn(internshipCandidatesList);
        List<InternshipCandidatesDto> actualCandidates = internshipCandidatesService.getCandidates();
        verify(internshipCandidatesRepository).findAll();
        InternshipCandidatesDto getResult = actualCandidates.get(0);
        InternOfferDto internOfferJob = getResult.getInternOfferJob();
        assertEquals("1970-01-01", internOfferJob.getEndDate());
        assertEquals("1970-01-01", internOfferJob.getStartDate());
        EtudiantDtoWithId etudiant3 = getResult.getEtudiant();
        assertEquals("6625550144", etudiant3.getPhone());
        assertEquals("Dr", internOfferJob.getTitle());
        assertEquals("Entreprise", internOfferJob.getEmployeurEntreprise());
        assertEquals("Location", internOfferJob.getLocation());
        assertEquals("Matricule", etudiant3.getMatricule());
        assertEquals("Nom", internOfferJob.getEmployeurNom());
        assertEquals("Nom", internOfferJob.getProgrammeNom());
        assertEquals("Nom", etudiant3.getNom());
        assertEquals("Prenom", internOfferJob.getEmployeurPrenom());
        assertEquals("Prenom", etudiant3.getPrenom());
        assertEquals("The characteristics of someone or something", internOfferJob.getDescription());
        FileDto file2 = internOfferJob.getFile();
        assertEquals("foo.txt", file2.getFileName());
        assertEquals("jane.doe@example.org", etudiant3.getEmail());
        assertEquals(1, actualCandidates.size());
        assertEquals(1L, etudiant3.getProgramme_id());
        assertEquals(1L, file2.getId());
        assertEquals(1L, internOfferJob.getEmployeurId());
        assertEquals(1L, internOfferJob.getId());
        assertEquals(1L, internOfferJob.getOfferReviewRequestId());
        assertEquals(1L, internOfferJob.getProgrammeId());
        assertEquals(1L, getResult.getId());
        assertEquals(1L, etudiant3.getId());
        assertEquals(State.ACCEPTED, file2.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferJob.getState());
        assertEquals(State.ACCEPTED, getResult.getState());
        List<FileDto> files = getResult.getFiles();
        assertTrue(files.isEmpty());
        assertEquals(cv, internOfferJob.getInternshipCandidates());
        assertSame(files, etudiant3.getCv());
        assertSame(files, etudiant3.getInternships_id());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file2.getContent());
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getCandidates()}
     */
    @Test
    void testGetCandidates3() {
        when(internshipCandidatesRepository.findAll()).thenThrow(new EmptyResultDataAccessException(3));
        assertThrows(DatabaseException.class, () -> internshipCandidatesService.getCandidates());
        verify(internshipCandidatesRepository).findAll();
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getCandidates()}
     */
    @Test
    void testGetCandidates4() {
        when(internshipCandidatesRepository.findAll()).thenThrow(new NullPointerException("foo"));
        assertThrows(ServiceException.class, () -> internshipCandidatesService.getCandidates());
        verify(internshipCandidatesRepository).findAll();
    }

    /**
     * Method under test: {@link InternshipCandidatesService#getCandidates()}
     */
    @Test
    void testGetCandidates5() throws UnsupportedEncodingException {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant = new Etudiant();
        etudiant.setCv(new File());
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

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv(new File());
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

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant2);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates);
        file.setIsAccepted(State.ACCEPTED);

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
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest);
        internOffer2.setProgramme(programme3);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Etudiant etudiant3 = new Etudiant();
        ArrayList<File> cv = new ArrayList<>();
        etudiant3.setCv(new File());
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

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(programme5);
        employeur2.setRole(Role.employer);

        Programme programme6 = new Programme();
        programme6.setDescription("The characteristics of someone or something");
        programme6.setId(1L);
        programme6.setNom("Nom");

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setCv(new File());
        etudiant4.setEmail("jane.doe@example.org");
        etudiant4.setId(1L);
        etudiant4.setInternshipsCandidate(new ArrayList<>());
        etudiant4.setMatricule("Matricule");
        etudiant4.setNom("Nom");
        etudiant4.setPassword("iloveyou");
        etudiant4.setPhone("6625550144");
        etudiant4.setPrenom("Prenom");
        etudiant4.setProgramme(programme6);
        etudiant4.setRole(Role.employer);

        Programme programme7 = new Programme();
        programme7.setDescription("The characteristics of someone or something");
        programme7.setId(1L);
        programme7.setNom("Nom");

        Etudiant etudiant5 = new Etudiant();
        etudiant5.setCv(new File());
        etudiant5.setEmail("jane.doe@example.org");
        etudiant5.setId(1L);
        etudiant5.setInternshipsCandidate(new ArrayList<>());
        etudiant5.setMatricule("Matricule");
        etudiant5.setNom("Nom");
        etudiant5.setPassword("iloveyou");
        etudiant5.setPhone("6625550144");
        etudiant5.setPrenom("Prenom");
        etudiant5.setProgramme(programme7);
        etudiant5.setRole(Role.employer);

        Employeur employeur3 = new Employeur();
        employeur3.setEmail("jane.doe@example.org");
        employeur3.setEntreprise("Entreprise");
        employeur3.setId(1L);
        employeur3.setInternOffers(new ArrayList<>());
        employeur3.setNom("Nom");
        employeur3.setPassword("iloveyou");
        employeur3.setPhone("6625550144");
        employeur3.setPrenom("Prenom");
        employeur3.setProgramme(new Programme());
        employeur3.setRole(Role.employer);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(new Etudiant());
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(new InternshipCandidates());
        file2.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(new InternOffer());
        offerReviewRequest2.setInternshipmanager(new Internshipmanager());

        Programme programme8 = new Programme();
        programme8.setDescription("The characteristics of someone or something");
        programme8.setId(1L);
        programme8.setNom("Nom");

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur3);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file2);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest2);
        internOffer3.setProgramme(programme8);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant5);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(internOffer3);
        internshipCandidates2.setState(State.ACCEPTED);

        File file3 = new File();
        file3.setContent("AXAXAXAX".getBytes("UTF-8"));
        file3.setEtudiant(etudiant4);
        file3.setFileName("foo.txt");
        file3.setId(1L);
        file3.setInternshipCandidates(internshipCandidates2);
        file3.setIsAccepted(State.ACCEPTED);

        Programme programme9 = new Programme();
        programme9.setDescription("The characteristics of someone or something");
        programme9.setId(1L);
        programme9.setNom("Nom");

        Employeur employeur4 = new Employeur();
        employeur4.setEmail("jane.doe@example.org");
        employeur4.setEntreprise("Entreprise");
        employeur4.setId(1L);
        employeur4.setInternOffers(new ArrayList<>());
        employeur4.setNom("Nom");
        employeur4.setPassword("iloveyou");
        employeur4.setPhone("6625550144");
        employeur4.setPrenom("Prenom");
        employeur4.setProgramme(programme9);
        employeur4.setRole(Role.employer);

        Etudiant etudiant6 = new Etudiant();
        etudiant6.setCv(new File());
        etudiant6.setEmail("jane.doe@example.org");
        etudiant6.setId(1L);
        etudiant6.setInternshipsCandidate(new ArrayList<>());
        etudiant6.setMatricule("Matricule");
        etudiant6.setNom("Nom");
        etudiant6.setPassword("iloveyou");
        etudiant6.setPhone("6625550144");
        etudiant6.setPrenom("Prenom");
        etudiant6.setProgramme(new Programme());
        etudiant6.setRole(Role.employer);

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(new Etudiant());
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(1L);
        internshipCandidates3.setInternOffer(new InternOffer());
        internshipCandidates3.setState(State.ACCEPTED);

        File file4 = new File();
        file4.setContent("AXAXAXAX".getBytes("UTF-8"));
        file4.setEtudiant(etudiant6);
        file4.setFileName("foo.txt");
        file4.setId(1L);
        file4.setInternshipCandidates(internshipCandidates3);
        file4.setIsAccepted(State.ACCEPTED);

        InternOffer internOffer4 = new InternOffer();
        internOffer4.setDescription("The characteristics of someone or something");
        internOffer4.setEmployeur(new Employeur());
        internOffer4.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer4.setFile(new File());
        internOffer4.setId(1L);
        internOffer4.setInternshipCandidates(new ArrayList<>());
        internOffer4.setLocation("Location");
        internOffer4.setOfferReviewRequest(new OfferReviewRequest());
        internOffer4.setProgramme(new Programme());
        internOffer4.setSalaryByHour(10.0d);
        internOffer4.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer4.setState(State.ACCEPTED);
        internOffer4.setStatus("Status");
        internOffer4.setTitle("Dr");

        Internshipmanager internshipmanager2 = new Internshipmanager();
        internshipmanager2.setEmail("jane.doe@example.org");
        internshipmanager2.setId(1L);
        internshipmanager2.setNom("Nom");
        internshipmanager2.setPassword("iloveyou");
        internshipmanager2.setPhone("6625550144");
        internshipmanager2.setPrenom("Prenom");
        internshipmanager2.setProgramme(new Programme());
        internshipmanager2.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest3 = new OfferReviewRequest();
        offerReviewRequest3.setComment("Comment");
        offerReviewRequest3.setId(1L);
        offerReviewRequest3.setInternOffer(internOffer4);
        offerReviewRequest3.setInternshipmanager(internshipmanager2);

        Programme programme10 = new Programme();
        programme10.setDescription("The characteristics of someone or something");
        programme10.setId(1L);
        programme10.setNom("Nom");

        InternOffer internOffer5 = new InternOffer();
        internOffer5.setDescription("The characteristics of someone or something");
        internOffer5.setEmployeur(employeur4);
        internOffer5.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer5.setFile(file4);
        internOffer5.setId(1L);
        internOffer5.setInternshipCandidates(new ArrayList<>());
        internOffer5.setLocation("Location");
        internOffer5.setOfferReviewRequest(offerReviewRequest3);
        internOffer5.setProgramme(programme10);
        internOffer5.setSalaryByHour(10.0d);
        internOffer5.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer5.setState(State.ACCEPTED);
        internOffer5.setStatus("Status");
        internOffer5.setTitle("Dr");

        Programme programme11 = new Programme();
        programme11.setDescription("The characteristics of someone or something");
        programme11.setId(1L);
        programme11.setNom("Nom");

        Internshipmanager internshipmanager3 = new Internshipmanager();
        internshipmanager3.setEmail("jane.doe@example.org");
        internshipmanager3.setId(1L);
        internshipmanager3.setNom("Nom");
        internshipmanager3.setPassword("iloveyou");
        internshipmanager3.setPhone("6625550144");
        internshipmanager3.setPrenom("Prenom");
        internshipmanager3.setProgramme(programme11);
        internshipmanager3.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest4 = new OfferReviewRequest();
        offerReviewRequest4.setComment("Comment");
        offerReviewRequest4.setId(1L);
        offerReviewRequest4.setInternOffer(internOffer5);
        offerReviewRequest4.setInternshipmanager(internshipmanager3);

        Programme programme12 = new Programme();
        programme12.setDescription("The characteristics of someone or something");
        programme12.setId(1L);
        programme12.setNom("Nom");

        InternOffer internOffer6 = new InternOffer();
        internOffer6.setDescription("The characteristics of someone or something");
        internOffer6.setEmployeur(employeur2);
        internOffer6.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer6.setFile(file3);
        internOffer6.setId(1L);
        internOffer6.setInternshipCandidates(new ArrayList<>());
        internOffer6.setLocation("Location");
        internOffer6.setOfferReviewRequest(offerReviewRequest4);
        internOffer6.setProgramme(programme12);
        internOffer6.setSalaryByHour(10.0d);
        internOffer6.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer6.setState(State.ACCEPTED);
        internOffer6.setStatus("Status");
        internOffer6.setTitle("Dr");
        InternshipCandidates internshipCandidates4 = mock(InternshipCandidates.class);
        when(internshipCandidates4.getEtudiant()).thenReturn(etudiant3);
        when(internshipCandidates4.getInternOffer()).thenReturn(internOffer6);
        when(internshipCandidates4.getState()).thenReturn(State.ACCEPTED);
        when(internshipCandidates4.getFiles()).thenReturn(new ArrayList<>());
        when(internshipCandidates4.getId()).thenReturn(1L);
        doNothing().when(internshipCandidates4).setEtudiant(Mockito.<Etudiant>any());
        doNothing().when(internshipCandidates4).setFiles(Mockito.<List<File>>any());
        doNothing().when(internshipCandidates4).setId(anyLong());
        doNothing().when(internshipCandidates4).setInternOffer(Mockito.<InternOffer>any());
        doNothing().when(internshipCandidates4).setState(Mockito.<State>any());
        internshipCandidates4.setEtudiant(etudiant);
        internshipCandidates4.setFiles(new ArrayList<>());
        internshipCandidates4.setId(1L);
        internshipCandidates4.setInternOffer(internOffer2);
        internshipCandidates4.setState(State.ACCEPTED);

        ArrayList<InternshipCandidates> internshipCandidatesList = new ArrayList<>();
        internshipCandidatesList.add(internshipCandidates4);
        when(internshipCandidatesRepository.findAll()).thenReturn(internshipCandidatesList);
        List<InternshipCandidatesDto> actualCandidates = internshipCandidatesService.getCandidates();
        verify(internshipCandidates4, atLeast(1)).getEtudiant();
        verify(internshipCandidates4, atLeast(1)).getFiles();
        verify(internshipCandidates4).getId();
        verify(internshipCandidates4, atLeast(1)).getInternOffer();
        verify(internshipCandidates4).getState();
        verify(internshipCandidates4).setEtudiant(Mockito.<Etudiant>any());
        verify(internshipCandidates4).setFiles(Mockito.<List<File>>any());
        verify(internshipCandidates4).setId(anyLong());
        verify(internshipCandidates4).setInternOffer(Mockito.<InternOffer>any());
        verify(internshipCandidates4).setState(Mockito.<State>any());
        verify(internshipCandidatesRepository).findAll();
        InternshipCandidatesDto getResult = actualCandidates.get(0);
        InternOfferDto internOfferJob = getResult.getInternOfferJob();
        assertEquals("1970-01-01", internOfferJob.getEndDate());
        assertEquals("1970-01-01", internOfferJob.getStartDate());
        EtudiantDtoWithId etudiant7 = getResult.getEtudiant();
        assertEquals("6625550144", etudiant7.getPhone());
        assertEquals("Dr", internOfferJob.getTitle());
        assertEquals("Entreprise", internOfferJob.getEmployeurEntreprise());
        assertEquals("Location", internOfferJob.getLocation());
        assertEquals("Matricule", etudiant7.getMatricule());
        assertEquals("Nom", internOfferJob.getEmployeurNom());
        assertEquals("Nom", internOfferJob.getProgrammeNom());
        assertEquals("Nom", etudiant7.getNom());
        assertEquals("Prenom", internOfferJob.getEmployeurPrenom());
        assertEquals("Prenom", etudiant7.getPrenom());
        assertEquals("The characteristics of someone or something", internOfferJob.getDescription());
        FileDto file5 = internOfferJob.getFile();
        assertEquals("foo.txt", file5.getFileName());
        assertEquals("jane.doe@example.org", etudiant7.getEmail());
        assertEquals(1, actualCandidates.size());
        assertEquals(1L, etudiant7.getProgramme_id());
        assertEquals(1L, file5.getId());
        assertEquals(1L, internOfferJob.getEmployeurId());
        assertEquals(1L, internOfferJob.getId());
        assertEquals(1L, internOfferJob.getOfferReviewRequestId());
        assertEquals(1L, internOfferJob.getProgrammeId());
        assertEquals(1L, getResult.getId());
        assertEquals(1L, etudiant7.getId());
        assertEquals(State.ACCEPTED, file5.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferJob.getState());
        assertEquals(State.ACCEPTED, getResult.getState());
        List<FileDto> files = getResult.getFiles();
        assertTrue(files.isEmpty());
        assertEquals(cv, internOfferJob.getInternshipCandidates());
        assertSame(files, etudiant7.getCv());
        assertSame(files, etudiant7.getInternships_id());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file5.getContent());
    }
}

