package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.FileDtoAll;
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
import com.sap.ose.projetose.repository.FileEntityRepository;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FileService.class})
@ExtendWith(SpringExtension.class)
class FileServiceTest {
    @MockBean
    private FileEntityRepository fileEntityRepository;

    @Autowired
    private FileService fileService;

    /**
     * Method under test: {@link FileService#findById(Long)}
     */
    @Test
    void testFindById() throws UnsupportedEncodingException {
        Etudiant etudiant = new Etudiant();
        etudiant.setActiveCv(new File());
        etudiant.setCv(new ArrayList<>());
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1L);
        etudiant.setInternshipsCandidate(new ArrayList<>());
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme(new Programme());
        etudiant.setRole(Role.employer);

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);
        internshipCandidates.setStudentCv(new File());

        File activeCv = new File();
        activeCv.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv.setEtudiant(etudiant);
        activeCv.setFileName("foo.txt");
        activeCv.setId(1L);
        activeCv.setInternshipCandidates(internshipCandidates);
        activeCv.setIsAccepted(State.ACCEPTED);

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setActiveCv(activeCv);
        etudiant2.setCv(new ArrayList<>());
        etudiant2.setEmail("jane.doe@example.org");
        etudiant2.setId(1L);
        etudiant2.setInternshipsCandidate(new ArrayList<>());
        etudiant2.setMatricule("Matricule");
        etudiant2.setNom("Nom");
        etudiant2.setPassword("iloveyou");
        etudiant2.setPhone("6625550144");
        etudiant2.setPrenom("Prenom");
        etudiant2.setProgramme(programme);
        etudiant2.setRole(Role.employer);

        File activeCv2 = new File();
        activeCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv2.setEtudiant(new Etudiant());
        activeCv2.setFileName("foo.txt");
        activeCv2.setId(1L);
        activeCv2.setInternshipCandidates(new InternshipCandidates());
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

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(new Programme());
        employeur.setRole(Role.employer);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(new Etudiant());
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(new InternshipCandidates());
        file.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(new InternOffer());
        offerReviewRequest.setInternshipmanager(new Internshipmanager());

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer = new InternOffer();
        internOffer.setDescription("The characteristics of someone or something");
        internOffer.setEmployeur(employeur);
        internOffer.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer.setFile(file);
        internOffer.setId(1L);
        internOffer.setInternshipCandidates(new ArrayList<>());
        internOffer.setLocation("Location");
        internOffer.setOfferReviewRequest(offerReviewRequest);
        internOffer.setProgramme(programme3);
        internOffer.setSalaryByHour(10.0d);
        internOffer.setSession("Session");
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setActiveCv(new File());
        etudiant4.setCv(new ArrayList<>());
        etudiant4.setEmail("jane.doe@example.org");
        etudiant4.setId(1L);
        etudiant4.setInternshipsCandidate(new ArrayList<>());
        etudiant4.setMatricule("Matricule");
        etudiant4.setNom("Nom");
        etudiant4.setPassword("iloveyou");
        etudiant4.setPhone("6625550144");
        etudiant4.setPrenom("Prenom");
        etudiant4.setProgramme(new Programme());
        etudiant4.setRole(Role.employer);

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(new Etudiant());
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(new InternOffer());
        internshipCandidates2.setState(State.ACCEPTED);
        internshipCandidates2.setStudentCv(new File());

        File studentCv = new File();
        studentCv.setContent("AXAXAXAX".getBytes("UTF-8"));
        studentCv.setEtudiant(etudiant4);
        studentCv.setFileName("foo.txt");
        studentCv.setId(1L);
        studentCv.setInternshipCandidates(internshipCandidates2);
        studentCv.setIsAccepted(State.ACCEPTED);

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(etudiant3);
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(1L);
        internshipCandidates3.setInternOffer(internOffer);
        internshipCandidates3.setState(State.ACCEPTED);
        internshipCandidates3.setStudentCv(studentCv);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(etudiant2);
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(internshipCandidates3);
        file2.setIsAccepted(State.ACCEPTED);
        Optional<File> ofResult = Optional.of(file2);
        when(fileEntityRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        File actualFindByIdResult = fileService.findById(1L);
        verify(fileEntityRepository).findById(Mockito.<Long>any());
        assertSame(file2, actualFindByIdResult);
    }

    /**
     * Method under test: {@link FileService#saveFile(File)}
     */
    @Test
    void testSaveFile() throws UnsupportedEncodingException {
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

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setActiveCv(new File());
        etudiant4.setCv(new ArrayList<>());
        etudiant4.setEmail("jane.doe@example.org");
        etudiant4.setId(1L);
        etudiant4.setInternshipsCandidate(new ArrayList<>());
        etudiant4.setMatricule("Matricule");
        etudiant4.setNom("Nom");
        etudiant4.setPassword("iloveyou");
        etudiant4.setPhone("6625550144");
        etudiant4.setPrenom("Prenom");
        etudiant4.setProgramme(new Programme());
        etudiant4.setRole(Role.employer);

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(new Etudiant());
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(new InternOffer());
        internshipCandidates2.setState(State.ACCEPTED);
        internshipCandidates2.setStudentCv(new File());

        File activeCv3 = new File();
        activeCv3.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv3.setEtudiant(etudiant4);
        activeCv3.setFileName("foo.txt");
        activeCv3.setId(1L);
        activeCv3.setInternshipCandidates(internshipCandidates2);
        activeCv3.setIsAccepted(State.ACCEPTED);

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        Etudiant etudiant5 = new Etudiant();
        etudiant5.setActiveCv(activeCv3);
        etudiant5.setCv(new ArrayList<>());
        etudiant5.setEmail("jane.doe@example.org");
        etudiant5.setId(1L);
        etudiant5.setInternshipsCandidate(new ArrayList<>());
        etudiant5.setMatricule("Matricule");
        etudiant5.setNom("Nom");
        etudiant5.setPassword("iloveyou");
        etudiant5.setPhone("6625550144");
        etudiant5.setPrenom("Prenom");
        etudiant5.setProgramme(programme3);
        etudiant5.setRole(Role.employer);

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme4);
        employeur.setRole(Role.employer);

        Etudiant etudiant6 = new Etudiant();
        etudiant6.setActiveCv(new File());
        etudiant6.setCv(new ArrayList<>());
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
        internshipCandidates3.setStudentCv(new File());

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(etudiant6);
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(internshipCandidates3);
        file.setIsAccepted(State.ACCEPTED);

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(new Employeur());
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(new File());
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(new OfferReviewRequest());
        internOffer2.setProgramme(new Programme());
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setSession("Session");
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

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
        offerReviewRequest.setInternOffer(internOffer2);
        offerReviewRequest.setInternshipmanager(internshipmanager);

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
        internOffer3.setOfferReviewRequest(offerReviewRequest);
        internOffer3.setProgramme(programme5);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setSession("Session");
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

        File activeCv4 = new File();
        activeCv4.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv4.setEtudiant(new Etudiant());
        activeCv4.setFileName("foo.txt");
        activeCv4.setId(1L);
        activeCv4.setInternshipCandidates(new InternshipCandidates());
        activeCv4.setIsAccepted(State.ACCEPTED);

        Programme programme6 = new Programme();
        programme6.setDescription("The characteristics of someone or something");
        programme6.setId(1L);
        programme6.setNom("Nom");

        Etudiant etudiant7 = new Etudiant();
        etudiant7.setActiveCv(activeCv4);
        etudiant7.setCv(new ArrayList<>());
        etudiant7.setEmail("jane.doe@example.org");
        etudiant7.setId(1L);
        etudiant7.setInternshipsCandidate(new ArrayList<>());
        etudiant7.setMatricule("Matricule");
        etudiant7.setNom("Nom");
        etudiant7.setPassword("iloveyou");
        etudiant7.setPhone("6625550144");
        etudiant7.setPrenom("Prenom");
        etudiant7.setProgramme(programme6);
        etudiant7.setRole(Role.employer);

        Etudiant etudiant8 = new Etudiant();
        etudiant8.setActiveCv(new File());
        etudiant8.setCv(new ArrayList<>());
        etudiant8.setEmail("jane.doe@example.org");
        etudiant8.setId(1L);
        etudiant8.setInternshipsCandidate(new ArrayList<>());
        etudiant8.setMatricule("Matricule");
        etudiant8.setNom("Nom");
        etudiant8.setPassword("iloveyou");
        etudiant8.setPhone("6625550144");
        etudiant8.setPrenom("Prenom");
        etudiant8.setProgramme(new Programme());
        etudiant8.setRole(Role.employer);

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
        internOffer4.setSession("Session");
        internOffer4.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer4.setState(State.ACCEPTED);
        internOffer4.setStatus("Status");
        internOffer4.setTitle("Dr");

        File studentCv2 = new File();
        studentCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
        studentCv2.setEtudiant(new Etudiant());
        studentCv2.setFileName("foo.txt");
        studentCv2.setId(1L);
        studentCv2.setInternshipCandidates(new InternshipCandidates());
        studentCv2.setIsAccepted(State.ACCEPTED);

        InternshipCandidates internshipCandidates4 = new InternshipCandidates();
        internshipCandidates4.setEtudiant(etudiant8);
        internshipCandidates4.setFiles(new ArrayList<>());
        internshipCandidates4.setId(1L);
        internshipCandidates4.setInternOffer(internOffer4);
        internshipCandidates4.setState(State.ACCEPTED);
        internshipCandidates4.setStudentCv(studentCv2);

        File studentCv3 = new File();
        studentCv3.setContent("AXAXAXAX".getBytes("UTF-8"));
        studentCv3.setEtudiant(etudiant7);
        studentCv3.setFileName("foo.txt");
        studentCv3.setId(1L);
        studentCv3.setInternshipCandidates(internshipCandidates4);
        studentCv3.setIsAccepted(State.ACCEPTED);

        InternshipCandidates internshipCandidates5 = new InternshipCandidates();
        internshipCandidates5.setEtudiant(etudiant5);
        internshipCandidates5.setFiles(new ArrayList<>());
        internshipCandidates5.setId(1L);
        internshipCandidates5.setInternOffer(internOffer3);
        internshipCandidates5.setState(State.ACCEPTED);
        internshipCandidates5.setStudentCv(studentCv3);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(etudiant3);
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(internshipCandidates5);
        file2.setIsAccepted(State.ACCEPTED);
        when(fileEntityRepository.save(Mockito.<File>any())).thenReturn(file2);

        Etudiant etudiant9 = new Etudiant();
        etudiant9.setActiveCv(new File());
        etudiant9.setCv(new ArrayList<>());
        etudiant9.setEmail("jane.doe@example.org");
        etudiant9.setId(1L);
        etudiant9.setInternshipsCandidate(new ArrayList<>());
        etudiant9.setMatricule("Matricule");
        etudiant9.setNom("Nom");
        etudiant9.setPassword("iloveyou");
        etudiant9.setPhone("6625550144");
        etudiant9.setPrenom("Prenom");
        etudiant9.setProgramme(new Programme());
        etudiant9.setRole(Role.employer);

        InternshipCandidates internshipCandidates6 = new InternshipCandidates();
        internshipCandidates6.setEtudiant(new Etudiant());
        internshipCandidates6.setFiles(new ArrayList<>());
        internshipCandidates6.setId(1L);
        internshipCandidates6.setInternOffer(new InternOffer());
        internshipCandidates6.setState(State.ACCEPTED);
        internshipCandidates6.setStudentCv(new File());

        File activeCv5 = new File();
        activeCv5.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv5.setEtudiant(etudiant9);
        activeCv5.setFileName("foo.txt");
        activeCv5.setId(1L);
        activeCv5.setInternshipCandidates(internshipCandidates6);
        activeCv5.setIsAccepted(State.ACCEPTED);

        Programme programme7 = new Programme();
        programme7.setDescription("The characteristics of someone or something");
        programme7.setId(1L);
        programme7.setNom("Nom");

        Etudiant etudiant10 = new Etudiant();
        etudiant10.setActiveCv(activeCv5);
        etudiant10.setCv(new ArrayList<>());
        etudiant10.setEmail("jane.doe@example.org");
        etudiant10.setId(1L);
        etudiant10.setInternshipsCandidate(new ArrayList<>());
        etudiant10.setMatricule("Matricule");
        etudiant10.setNom("Nom");
        etudiant10.setPassword("iloveyou");
        etudiant10.setPhone("6625550144");
        etudiant10.setPrenom("Prenom");
        etudiant10.setProgramme(programme7);
        etudiant10.setRole(Role.employer);

        File activeCv6 = new File();
        activeCv6.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv6.setEtudiant(new Etudiant());
        activeCv6.setFileName("foo.txt");
        activeCv6.setId(1L);
        activeCv6.setInternshipCandidates(new InternshipCandidates());
        activeCv6.setIsAccepted(State.ACCEPTED);

        Programme programme8 = new Programme();
        programme8.setDescription("The characteristics of someone or something");
        programme8.setId(1L);
        programme8.setNom("Nom");

        Etudiant etudiant11 = new Etudiant();
        etudiant11.setActiveCv(activeCv6);
        etudiant11.setCv(new ArrayList<>());
        etudiant11.setEmail("jane.doe@example.org");
        etudiant11.setId(1L);
        etudiant11.setInternshipsCandidate(new ArrayList<>());
        etudiant11.setMatricule("Matricule");
        etudiant11.setNom("Nom");
        etudiant11.setPassword("iloveyou");
        etudiant11.setPhone("6625550144");
        etudiant11.setPrenom("Prenom");
        etudiant11.setProgramme(programme8);
        etudiant11.setRole(Role.employer);

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

        Programme programme9 = new Programme();
        programme9.setDescription("The characteristics of someone or something");
        programme9.setId(1L);
        programme9.setNom("Nom");

        InternOffer internOffer5 = new InternOffer();
        internOffer5.setDescription("The characteristics of someone or something");
        internOffer5.setEmployeur(employeur2);
        internOffer5.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer5.setFile(file3);
        internOffer5.setId(1L);
        internOffer5.setInternshipCandidates(new ArrayList<>());
        internOffer5.setLocation("Location");
        internOffer5.setOfferReviewRequest(offerReviewRequest2);
        internOffer5.setProgramme(programme9);
        internOffer5.setSalaryByHour(10.0d);
        internOffer5.setSession("Session");
        internOffer5.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer5.setState(State.ACCEPTED);
        internOffer5.setStatus("Status");
        internOffer5.setTitle("Dr");

        Etudiant etudiant12 = new Etudiant();
        etudiant12.setActiveCv(new File());
        etudiant12.setCv(new ArrayList<>());
        etudiant12.setEmail("jane.doe@example.org");
        etudiant12.setId(1L);
        etudiant12.setInternshipsCandidate(new ArrayList<>());
        etudiant12.setMatricule("Matricule");
        etudiant12.setNom("Nom");
        etudiant12.setPassword("iloveyou");
        etudiant12.setPhone("6625550144");
        etudiant12.setPrenom("Prenom");
        etudiant12.setProgramme(new Programme());
        etudiant12.setRole(Role.employer);

        InternshipCandidates internshipCandidates7 = new InternshipCandidates();
        internshipCandidates7.setEtudiant(new Etudiant());
        internshipCandidates7.setFiles(new ArrayList<>());
        internshipCandidates7.setId(1L);
        internshipCandidates7.setInternOffer(new InternOffer());
        internshipCandidates7.setState(State.ACCEPTED);
        internshipCandidates7.setStudentCv(new File());

        File studentCv4 = new File();
        studentCv4.setContent("AXAXAXAX".getBytes("UTF-8"));
        studentCv4.setEtudiant(etudiant12);
        studentCv4.setFileName("foo.txt");
        studentCv4.setId(1L);
        studentCv4.setInternshipCandidates(internshipCandidates7);
        studentCv4.setIsAccepted(State.ACCEPTED);

        InternshipCandidates internshipCandidates8 = new InternshipCandidates();
        internshipCandidates8.setEtudiant(etudiant11);
        internshipCandidates8.setFiles(new ArrayList<>());
        internshipCandidates8.setId(1L);
        internshipCandidates8.setInternOffer(internOffer5);
        internshipCandidates8.setState(State.ACCEPTED);
        internshipCandidates8.setStudentCv(studentCv4);

        File file4 = new File();
        file4.setContent("AXAXAXAX".getBytes("UTF-8"));
        file4.setEtudiant(etudiant10);
        file4.setFileName("foo.txt");
        file4.setId(1L);
        file4.setInternshipCandidates(internshipCandidates8);
        file4.setIsAccepted(State.ACCEPTED);
        fileService.saveFile(file4);
        verify(fileEntityRepository).save(Mockito.<File>any());
        assertEquals("foo.txt", file4.getFileName());
        assertEquals(1L, file4.getId());
        assertEquals(State.ACCEPTED, file4.getIsAccepted());
        assertEquals(etudiant5, file4.getEtudiant());
        assertSame(internshipCandidates8, file4.getInternshipCandidates());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file4.getContent());
    }

    /**
     * Method under test: {@link FileService#getFileById(Long)}
     */
    @Test
    void testGetFileById() throws UnsupportedEncodingException {
        Etudiant etudiant = new Etudiant();
        etudiant.setActiveCv(new File());
        etudiant.setCv(new ArrayList<>());
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1L);
        etudiant.setInternshipsCandidate(new ArrayList<>());
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme(new Programme());
        etudiant.setRole(Role.employer);

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);
        internshipCandidates.setStudentCv(new File());

        File activeCv = new File();
        activeCv.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv.setEtudiant(etudiant);
        activeCv.setFileName("foo.txt");
        activeCv.setId(1L);
        activeCv.setInternshipCandidates(internshipCandidates);
        activeCv.setIsAccepted(State.ACCEPTED);

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setActiveCv(activeCv);
        etudiant2.setCv(new ArrayList<>());
        etudiant2.setEmail("jane.doe@example.org");
        etudiant2.setId(1L);
        etudiant2.setInternshipsCandidate(new ArrayList<>());
        etudiant2.setMatricule("Matricule");
        etudiant2.setNom("Nom");
        etudiant2.setPassword("iloveyou");
        etudiant2.setPhone("6625550144");
        etudiant2.setPrenom("Prenom");
        etudiant2.setProgramme(programme);
        etudiant2.setRole(Role.employer);

        File activeCv2 = new File();
        activeCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv2.setEtudiant(new Etudiant());
        activeCv2.setFileName("foo.txt");
        activeCv2.setId(1L);
        activeCv2.setInternshipCandidates(new InternshipCandidates());
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

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(new Programme());
        employeur.setRole(Role.employer);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(new Etudiant());
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(new InternshipCandidates());
        file.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(new InternOffer());
        offerReviewRequest.setInternshipmanager(new Internshipmanager());

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer = new InternOffer();
        internOffer.setDescription("The characteristics of someone or something");
        internOffer.setEmployeur(employeur);
        internOffer.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer.setFile(file);
        internOffer.setId(1L);
        internOffer.setInternshipCandidates(new ArrayList<>());
        internOffer.setLocation("Location");
        internOffer.setOfferReviewRequest(offerReviewRequest);
        internOffer.setProgramme(programme3);
        internOffer.setSalaryByHour(10.0d);
        internOffer.setSession("Session");
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setActiveCv(new File());
        etudiant4.setCv(new ArrayList<>());
        etudiant4.setEmail("jane.doe@example.org");
        etudiant4.setId(1L);
        etudiant4.setInternshipsCandidate(new ArrayList<>());
        etudiant4.setMatricule("Matricule");
        etudiant4.setNom("Nom");
        etudiant4.setPassword("iloveyou");
        etudiant4.setPhone("6625550144");
        etudiant4.setPrenom("Prenom");
        etudiant4.setProgramme(new Programme());
        etudiant4.setRole(Role.employer);

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(new Etudiant());
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(new InternOffer());
        internshipCandidates2.setState(State.ACCEPTED);
        internshipCandidates2.setStudentCv(new File());

        File studentCv = new File();
        studentCv.setContent("AXAXAXAX".getBytes("UTF-8"));
        studentCv.setEtudiant(etudiant4);
        studentCv.setFileName("foo.txt");
        studentCv.setId(1L);
        studentCv.setInternshipCandidates(internshipCandidates2);
        studentCv.setIsAccepted(State.ACCEPTED);

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(etudiant3);
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(1L);
        internshipCandidates3.setInternOffer(internOffer);
        internshipCandidates3.setState(State.ACCEPTED);
        internshipCandidates3.setStudentCv(studentCv);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(etudiant2);
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(internshipCandidates3);
        file2.setIsAccepted(State.ACCEPTED);
        Optional<File> ofResult = Optional.of(file2);
        when(fileEntityRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        FileDtoAll actualFileById = fileService.getFileById(1L);
        verify(fileEntityRepository).findById(Mockito.<Long>any());
        EtudiantDto etudiant5 = actualFileById.getEtudiant();
        assertEquals("6625550144", etudiant5.getPhone());
        assertEquals("Matricule", etudiant5.getMatricule());
        assertEquals("Nom", etudiant5.getNom());
        assertEquals("Prenom", etudiant5.getPrenom());
        assertEquals("foo.txt", actualFileById.getFileName());
        assertEquals("jane.doe@example.org", etudiant5.getEmail());
        assertEquals(1L, etudiant5.getProgramme_id());
        assertEquals(1L, actualFileById.getId());
        assertEquals(1L, etudiant5.getId());
        assertEquals(State.ACCEPTED, actualFileById.getIsAccepted());
        assertTrue(etudiant5.getCv().isEmpty());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, actualFileById.getContent());
    }

    /**
     * Method under test: {@link FileService#getAllStudentPendingCv()}
     */
    @Test
    void testGetAllStudentPendingCv() {
        Optional<List<File>> ofResult = Optional.of(new ArrayList<>());
        when(fileEntityRepository.findAllStudentCvPending()).thenReturn(ofResult);
        List<FileDtoAll> actualAllStudentPendingCv = fileService.getAllStudentPendingCv();
        verify(fileEntityRepository).findAllStudentCvPending();
        assertTrue(actualAllStudentPendingCv.isEmpty());
    }

    /**
     * Method under test: {@link FileService#getAllStudentPendingCv()}
     */
    @Test
    void testGetAllStudentPendingCv2() throws UnsupportedEncodingException {
        Etudiant etudiant = new Etudiant();
        etudiant.setActiveCv(new File());
        etudiant.setCv(new ArrayList<>());
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1L);
        etudiant.setInternshipsCandidate(new ArrayList<>());
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme(new Programme());
        etudiant.setRole(Role.employer);

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);
        internshipCandidates.setStudentCv(new File());

        File activeCv = new File();
        activeCv.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv.setEtudiant(etudiant);
        activeCv.setFileName("foo.txt");
        activeCv.setId(1L);
        activeCv.setInternshipCandidates(internshipCandidates);
        activeCv.setIsAccepted(State.ACCEPTED);

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setActiveCv(activeCv);
        etudiant2.setCv(new ArrayList<>());
        etudiant2.setEmail("jane.doe@example.org");
        etudiant2.setId(1L);
        etudiant2.setInternshipsCandidate(new ArrayList<>());
        etudiant2.setMatricule("Matricule");
        etudiant2.setNom("Nom");
        etudiant2.setPassword("iloveyou");
        etudiant2.setPhone("6625550144");
        etudiant2.setPrenom("Prenom");
        etudiant2.setProgramme(programme);
        etudiant2.setRole(Role.employer);

        File activeCv2 = new File();
        activeCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv2.setEtudiant(new Etudiant());
        activeCv2.setFileName("foo.txt");
        activeCv2.setId(1L);
        activeCv2.setInternshipCandidates(new InternshipCandidates());
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

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(new Programme());
        employeur.setRole(Role.employer);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(new Etudiant());
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(new InternshipCandidates());
        file.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(new InternOffer());
        offerReviewRequest.setInternshipmanager(new Internshipmanager());

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer = new InternOffer();
        internOffer.setDescription("The characteristics of someone or something");
        internOffer.setEmployeur(employeur);
        internOffer.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer.setFile(file);
        internOffer.setId(1L);
        internOffer.setInternshipCandidates(new ArrayList<>());
        internOffer.setLocation("Location");
        internOffer.setOfferReviewRequest(offerReviewRequest);
        internOffer.setProgramme(programme3);
        internOffer.setSalaryByHour(10.0d);
        internOffer.setSession("Session");
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setActiveCv(new File());
        etudiant4.setCv(new ArrayList<>());
        etudiant4.setEmail("jane.doe@example.org");
        etudiant4.setId(1L);
        etudiant4.setInternshipsCandidate(new ArrayList<>());
        etudiant4.setMatricule("Matricule");
        etudiant4.setNom("Nom");
        etudiant4.setPassword("iloveyou");
        etudiant4.setPhone("6625550144");
        etudiant4.setPrenom("Prenom");
        etudiant4.setProgramme(new Programme());
        etudiant4.setRole(Role.employer);

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(new Etudiant());
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(new InternOffer());
        internshipCandidates2.setState(State.ACCEPTED);
        internshipCandidates2.setStudentCv(new File());

        File studentCv = new File();
        studentCv.setContent("AXAXAXAX".getBytes("UTF-8"));
        studentCv.setEtudiant(etudiant4);
        studentCv.setFileName("foo.txt");
        studentCv.setId(1L);
        studentCv.setInternshipCandidates(internshipCandidates2);
        studentCv.setIsAccepted(State.ACCEPTED);

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(etudiant3);
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(1L);
        internshipCandidates3.setInternOffer(internOffer);
        internshipCandidates3.setState(State.ACCEPTED);
        internshipCandidates3.setStudentCv(studentCv);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(etudiant2);
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(internshipCandidates3);
        file2.setIsAccepted(State.ACCEPTED);

        ArrayList<File> fileList = new ArrayList<>();
        fileList.add(file2);
        Optional<List<File>> ofResult = Optional.of(fileList);
        when(fileEntityRepository.findAllStudentCvPending()).thenReturn(ofResult);
        List<FileDtoAll> actualAllStudentPendingCv = fileService.getAllStudentPendingCv();
        verify(fileEntityRepository).findAllStudentCvPending();
        FileDtoAll getResult = actualAllStudentPendingCv.get(0);
        EtudiantDto etudiant5 = getResult.getEtudiant();
        assertEquals("6625550144", etudiant5.getPhone());
        assertEquals("Matricule", etudiant5.getMatricule());
        assertEquals("Nom", etudiant5.getNom());
        assertEquals("Prenom", etudiant5.getPrenom());
        assertEquals("foo.txt", getResult.getFileName());
        assertEquals("jane.doe@example.org", etudiant5.getEmail());
        assertEquals(1, actualAllStudentPendingCv.size());
        assertEquals(1L, etudiant5.getProgramme_id());
        assertEquals(1L, getResult.getId());
        assertEquals(1L, etudiant5.getId());
        assertEquals(State.ACCEPTED, getResult.getIsAccepted());
        assertTrue(etudiant5.getCv().isEmpty());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, getResult.getContent());
    }

    /**
     * Method under test: {@link FileService#getAllStudentPendingCv()}
     */
    @Test
    void testGetAllStudentPendingCv3() throws UnsupportedEncodingException {
        Etudiant etudiant = new Etudiant();
        etudiant.setActiveCv(new File());
        etudiant.setCv(new ArrayList<>());
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1L);
        etudiant.setInternshipsCandidate(new ArrayList<>());
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme(new Programme());
        etudiant.setRole(Role.employer);

        InternshipCandidates internshipCandidates = new InternshipCandidates();
        internshipCandidates.setEtudiant(new Etudiant());
        internshipCandidates.setFiles(new ArrayList<>());
        internshipCandidates.setId(1L);
        internshipCandidates.setInternOffer(new InternOffer());
        internshipCandidates.setState(State.ACCEPTED);
        internshipCandidates.setStudentCv(new File());

        File activeCv = new File();
        activeCv.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv.setEtudiant(etudiant);
        activeCv.setFileName("foo.txt");
        activeCv.setId(1L);
        activeCv.setInternshipCandidates(internshipCandidates);
        activeCv.setIsAccepted(State.ACCEPTED);

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setActiveCv(activeCv);
        etudiant2.setCv(new ArrayList<>());
        etudiant2.setEmail("jane.doe@example.org");
        etudiant2.setId(1L);
        etudiant2.setInternshipsCandidate(new ArrayList<>());
        etudiant2.setMatricule("Matricule");
        etudiant2.setNom("Nom");
        etudiant2.setPassword("iloveyou");
        etudiant2.setPhone("6625550144");
        etudiant2.setPrenom("Prenom");
        etudiant2.setProgramme(programme);
        etudiant2.setRole(Role.employer);

        File activeCv2 = new File();
        activeCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv2.setEtudiant(new Etudiant());
        activeCv2.setFileName("foo.txt");
        activeCv2.setId(1L);
        activeCv2.setInternshipCandidates(new InternshipCandidates());
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

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(new Programme());
        employeur.setRole(Role.employer);

        File file = new File();
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setEtudiant(new Etudiant());
        file.setFileName("foo.txt");
        file.setId(1L);
        file.setInternshipCandidates(new InternshipCandidates());
        file.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
        offerReviewRequest.setComment("Comment");
        offerReviewRequest.setId(1L);
        offerReviewRequest.setInternOffer(new InternOffer());
        offerReviewRequest.setInternshipmanager(new Internshipmanager());

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        InternOffer internOffer = new InternOffer();
        internOffer.setDescription("The characteristics of someone or something");
        internOffer.setEmployeur(employeur);
        internOffer.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer.setFile(file);
        internOffer.setId(1L);
        internOffer.setInternshipCandidates(new ArrayList<>());
        internOffer.setLocation("Location");
        internOffer.setOfferReviewRequest(offerReviewRequest);
        internOffer.setProgramme(programme3);
        internOffer.setSalaryByHour(10.0d);
        internOffer.setSession("Session");
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setActiveCv(new File());
        etudiant4.setCv(new ArrayList<>());
        etudiant4.setEmail("jane.doe@example.org");
        etudiant4.setId(1L);
        etudiant4.setInternshipsCandidate(new ArrayList<>());
        etudiant4.setMatricule("Matricule");
        etudiant4.setNom("Nom");
        etudiant4.setPassword("iloveyou");
        etudiant4.setPhone("6625550144");
        etudiant4.setPrenom("Prenom");
        etudiant4.setProgramme(new Programme());
        etudiant4.setRole(Role.employer);

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(new Etudiant());
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(new InternOffer());
        internshipCandidates2.setState(State.ACCEPTED);
        internshipCandidates2.setStudentCv(new File());

        File studentCv = new File();
        studentCv.setContent("AXAXAXAX".getBytes("UTF-8"));
        studentCv.setEtudiant(etudiant4);
        studentCv.setFileName("foo.txt");
        studentCv.setId(1L);
        studentCv.setInternshipCandidates(internshipCandidates2);
        studentCv.setIsAccepted(State.ACCEPTED);

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(etudiant3);
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(1L);
        internshipCandidates3.setInternOffer(internOffer);
        internshipCandidates3.setState(State.ACCEPTED);
        internshipCandidates3.setStudentCv(studentCv);

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(etudiant2);
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(internshipCandidates3);
        file2.setIsAccepted(State.ACCEPTED);

        Etudiant etudiant5 = new Etudiant();
        etudiant5.setActiveCv(new File());
        etudiant5.setCv(new ArrayList<>());
        etudiant5.setEmail("john.smith@example.org");
        etudiant5.setId(2L);
        etudiant5.setInternshipsCandidate(new ArrayList<>());
        etudiant5.setMatricule("com.sap.ose.projetose.modeles.Etudiant");
        etudiant5.setNom("com.sap.ose.projetose.modeles.Etudiant");
        etudiant5.setPassword("Password");
        etudiant5.setPhone("8605550118");
        etudiant5.setPrenom("com.sap.ose.projetose.modeles.Etudiant");
        etudiant5.setProgramme(new Programme());
        etudiant5.setRole(Role.student);

        InternshipCandidates internshipCandidates4 = new InternshipCandidates();
        internshipCandidates4.setEtudiant(new Etudiant());
        internshipCandidates4.setFiles(new ArrayList<>());
        internshipCandidates4.setId(2L);
        internshipCandidates4.setInternOffer(new InternOffer());
        internshipCandidates4.setState(State.PENDING);
        internshipCandidates4.setStudentCv(new File());

        File activeCv3 = new File();
        activeCv3.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv3.setEtudiant(etudiant5);
        activeCv3.setFileName("File Name");
        activeCv3.setId(2L);
        activeCv3.setInternshipCandidates(internshipCandidates4);
        activeCv3.setIsAccepted(State.PENDING);

        Programme programme4 = new Programme();
        programme4.setDescription("Description");
        programme4.setId(2L);
        programme4.setNom("com.sap.ose.projetose.modeles.Programme");

        Etudiant etudiant6 = new Etudiant();
        etudiant6.setActiveCv(activeCv3);
        etudiant6.setCv(new ArrayList<>());
        etudiant6.setEmail("john.smith@example.org");
        etudiant6.setId(2L);
        etudiant6.setInternshipsCandidate(new ArrayList<>());
        etudiant6.setMatricule("com.sap.ose.projetose.modeles.Etudiant");
        etudiant6.setNom("com.sap.ose.projetose.modeles.Etudiant");
        etudiant6.setPassword("Password");
        etudiant6.setPhone("8605550118");
        etudiant6.setPrenom("com.sap.ose.projetose.modeles.Etudiant");
        etudiant6.setProgramme(programme4);
        etudiant6.setRole(Role.student);

        File activeCv4 = new File();
        activeCv4.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv4.setEtudiant(new Etudiant());
        activeCv4.setFileName("File Name");
        activeCv4.setId(2L);
        activeCv4.setInternshipCandidates(new InternshipCandidates());
        activeCv4.setIsAccepted(State.PENDING);

        Programme programme5 = new Programme();
        programme5.setDescription("Description");
        programme5.setId(2L);
        programme5.setNom("com.sap.ose.projetose.modeles.Programme");

        Etudiant etudiant7 = new Etudiant();
        etudiant7.setActiveCv(activeCv4);
        etudiant7.setCv(new ArrayList<>());
        etudiant7.setEmail("john.smith@example.org");
        etudiant7.setId(2L);
        etudiant7.setInternshipsCandidate(new ArrayList<>());
        etudiant7.setMatricule("com.sap.ose.projetose.modeles.Etudiant");
        etudiant7.setNom("com.sap.ose.projetose.modeles.Etudiant");
        etudiant7.setPassword("Password");
        etudiant7.setPhone("8605550118");
        etudiant7.setPrenom("com.sap.ose.projetose.modeles.Etudiant");
        etudiant7.setProgramme(programme5);
        etudiant7.setRole(Role.student);

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("john.smith@example.org");
        employeur2.setEntreprise("com.sap.ose.projetose.modeles.Employeur");
        employeur2.setId(2L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("com.sap.ose.projetose.modeles.Employeur");
        employeur2.setPassword("Password");
        employeur2.setPhone("8605550118");
        employeur2.setPrenom("com.sap.ose.projetose.modeles.Employeur");
        employeur2.setProgramme(new Programme());
        employeur2.setRole(Role.student);

        File file3 = new File();
        file3.setContent("AXAXAXAX".getBytes("UTF-8"));
        file3.setEtudiant(new Etudiant());
        file3.setFileName("File Name");
        file3.setId(2L);
        file3.setInternshipCandidates(new InternshipCandidates());
        file3.setIsAccepted(State.PENDING);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("com.sap.ose.projetose.modeles.OfferReviewRequest");
        offerReviewRequest2.setId(2L);
        offerReviewRequest2.setInternOffer(new InternOffer());
        offerReviewRequest2.setInternshipmanager(new Internshipmanager());

        Programme programme6 = new Programme();
        programme6.setDescription("Description");
        programme6.setId(2L);
        programme6.setNom("com.sap.ose.projetose.modeles.Programme");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("Description");
        internOffer2.setEmployeur(employeur2);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file3);
        internOffer2.setId(2L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("com.sap.ose.projetose.modeles.InternOffer");
        internOffer2.setOfferReviewRequest(offerReviewRequest2);
        internOffer2.setProgramme(programme6);
        internOffer2.setSalaryByHour(0.5d);
        internOffer2.setSession("com.sap.ose.projetose.modeles.InternOffer");
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.PENDING);
        internOffer2.setStatus("com.sap.ose.projetose.modeles.InternOffer");
        internOffer2.setTitle("Mr");

        Etudiant etudiant8 = new Etudiant();
        etudiant8.setActiveCv(new File());
        etudiant8.setCv(new ArrayList<>());
        etudiant8.setEmail("john.smith@example.org");
        etudiant8.setId(2L);
        etudiant8.setInternshipsCandidate(new ArrayList<>());
        etudiant8.setMatricule("com.sap.ose.projetose.modeles.Etudiant");
        etudiant8.setNom("com.sap.ose.projetose.modeles.Etudiant");
        etudiant8.setPassword("Password");
        etudiant8.setPhone("8605550118");
        etudiant8.setPrenom("com.sap.ose.projetose.modeles.Etudiant");
        etudiant8.setProgramme(new Programme());
        etudiant8.setRole(Role.student);

        InternshipCandidates internshipCandidates5 = new InternshipCandidates();
        internshipCandidates5.setEtudiant(new Etudiant());
        internshipCandidates5.setFiles(new ArrayList<>());
        internshipCandidates5.setId(2L);
        internshipCandidates5.setInternOffer(new InternOffer());
        internshipCandidates5.setState(State.PENDING);
        internshipCandidates5.setStudentCv(new File());

        File studentCv2 = new File();
        studentCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
        studentCv2.setEtudiant(etudiant8);
        studentCv2.setFileName("File Name");
        studentCv2.setId(2L);
        studentCv2.setInternshipCandidates(internshipCandidates5);
        studentCv2.setIsAccepted(State.PENDING);

        InternshipCandidates internshipCandidates6 = new InternshipCandidates();
        internshipCandidates6.setEtudiant(etudiant7);
        internshipCandidates6.setFiles(new ArrayList<>());
        internshipCandidates6.setId(2L);
        internshipCandidates6.setInternOffer(internOffer2);
        internshipCandidates6.setState(State.PENDING);
        internshipCandidates6.setStudentCv(studentCv2);

        File file4 = new File();
        file4.setContent("AXAXAXAX".getBytes("UTF-8"));
        file4.setEtudiant(etudiant6);
        file4.setFileName("File Name");
        file4.setId(2L);
        file4.setInternshipCandidates(internshipCandidates6);
        file4.setIsAccepted(State.PENDING);

        ArrayList<File> fileList = new ArrayList<>();
        fileList.add(file4);
        fileList.add(file2);
        Optional<List<File>> ofResult = Optional.of(fileList);
        when(fileEntityRepository.findAllStudentCvPending()).thenReturn(ofResult);
        List<FileDtoAll> actualAllStudentPendingCv = fileService.getAllStudentPendingCv();
        verify(fileEntityRepository).findAllStudentCvPending();
        FileDtoAll getResult = actualAllStudentPendingCv.get(1);
        EtudiantDto etudiant9 = getResult.getEtudiant();
        assertEquals("6625550144", etudiant9.getPhone());
        FileDtoAll getResult2 = actualAllStudentPendingCv.get(0);
        EtudiantDto etudiant10 = getResult2.getEtudiant();
        assertEquals("8605550118", etudiant10.getPhone());
        assertEquals("File Name", getResult2.getFileName());
        assertEquals("Matricule", etudiant9.getMatricule());
        assertEquals("Nom", etudiant9.getNom());
        assertEquals("Prenom", etudiant9.getPrenom());
        assertEquals("com.sap.ose.projetose.modeles.Etudiant", etudiant10.getMatricule());
        assertEquals("com.sap.ose.projetose.modeles.Etudiant", etudiant10.getNom());
        assertEquals("com.sap.ose.projetose.modeles.Etudiant", etudiant10.getPrenom());
        assertEquals("foo.txt", getResult.getFileName());
        assertEquals("jane.doe@example.org", etudiant9.getEmail());
        assertEquals("john.smith@example.org", etudiant10.getEmail());
        assertEquals(1L, etudiant9.getProgramme_id());
        assertEquals(1L, getResult.getId());
        assertEquals(1L, etudiant9.getId());
        assertEquals(2, actualAllStudentPendingCv.size());
        assertEquals(2L, etudiant10.getProgramme_id());
        assertEquals(2L, getResult2.getId());
        assertEquals(2L, etudiant10.getId());
        assertEquals(State.ACCEPTED, getResult.getIsAccepted());
        assertEquals(State.PENDING, getResult2.getIsAccepted());
        assertTrue(etudiant10.getCv().isEmpty());
        assertTrue(etudiant9.getCv().isEmpty());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, getResult2.getContent());
        byte[] expectedContent2 = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent2, getResult.getContent());
    }

    /**
     * Method under test: {@link FileService#getAllStudentPendingCv()}
     */
    @Test
    void testGetAllStudentPendingCv4() {
        Optional<List<File>> emptyResult = Optional.empty();
        when(fileEntityRepository.findAllStudentCvPending()).thenReturn(emptyResult);
        List<FileDtoAll> actualAllStudentPendingCv = fileService.getAllStudentPendingCv();
        verify(fileEntityRepository).findAllStudentCvPending();
        assertNull(actualAllStudentPendingCv);
    }
}
