package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sap.ose.projetose.dto.TemplateContractDto;
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
import com.sap.ose.projetose.modeles.TemplateContract;
import com.sap.ose.projetose.repository.TemplateContractRepository;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TemplateContractService.class})
@ExtendWith(SpringExtension.class)
class TemplateContractServiceTest {
    @MockBean
    private TemplateContractRepository templateContractRepository;

    @Autowired
    private TemplateContractService templateContractService;

    /**
     * Method under test: {@link TemplateContractService#save(TemplateContractDto)}
     */
    @Test
    void testSave() throws UnsupportedEncodingException {
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

        TemplateContract templateContract = new TemplateContract();
        templateContract.setCreatedDate(LocalDate.of(1970, 1, 1));
        templateContract.setFile(file2);
        templateContract.setId(1L);
        templateContract.setIsActive(true);

        Etudiant etudiant5 = new Etudiant();
        etudiant5.setActiveCv(new File());
        etudiant5.setCv(new ArrayList<>());
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

        InternshipCandidates internshipCandidates4 = new InternshipCandidates();
        internshipCandidates4.setEtudiant(new Etudiant());
        internshipCandidates4.setFiles(new ArrayList<>());
        internshipCandidates4.setId(1L);
        internshipCandidates4.setInternOffer(new InternOffer());
        internshipCandidates4.setState(State.ACCEPTED);
        internshipCandidates4.setStudentCv(new File());

        File activeCv3 = new File();
        activeCv3.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv3.setEtudiant(etudiant5);
        activeCv3.setFileName("foo.txt");
        activeCv3.setId(1L);
        activeCv3.setInternshipCandidates(internshipCandidates4);
        activeCv3.setIsAccepted(State.ACCEPTED);

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Etudiant etudiant6 = new Etudiant();
        etudiant6.setActiveCv(activeCv3);
        etudiant6.setCv(new ArrayList<>());
        etudiant6.setEmail("jane.doe@example.org");
        etudiant6.setId(1L);
        etudiant6.setInternshipsCandidate(new ArrayList<>());
        etudiant6.setMatricule("Matricule");
        etudiant6.setNom("Nom");
        etudiant6.setPassword("iloveyou");
        etudiant6.setPhone("6625550144");
        etudiant6.setPrenom("Prenom");
        etudiant6.setProgramme(programme4);
        etudiant6.setRole(Role.employer);

        File activeCv4 = new File();
        activeCv4.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv4.setEtudiant(new Etudiant());
        activeCv4.setFileName("foo.txt");
        activeCv4.setId(1L);
        activeCv4.setInternshipCandidates(new InternshipCandidates());
        activeCv4.setIsAccepted(State.ACCEPTED);

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

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
        etudiant7.setProgramme(programme5);
        etudiant7.setRole(Role.employer);

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

        Programme programme6 = new Programme();
        programme6.setDescription("The characteristics of someone or something");
        programme6.setId(1L);
        programme6.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur2);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file3);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest2);
        internOffer2.setProgramme(programme6);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setSession("Session");
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

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

        InternshipCandidates internshipCandidates5 = new InternshipCandidates();
        internshipCandidates5.setEtudiant(new Etudiant());
        internshipCandidates5.setFiles(new ArrayList<>());
        internshipCandidates5.setId(1L);
        internshipCandidates5.setInternOffer(new InternOffer());
        internshipCandidates5.setState(State.ACCEPTED);
        internshipCandidates5.setStudentCv(new File());

        File studentCv2 = new File();
        studentCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
        studentCv2.setEtudiant(etudiant8);
        studentCv2.setFileName("foo.txt");
        studentCv2.setId(1L);
        studentCv2.setInternshipCandidates(internshipCandidates5);
        studentCv2.setIsAccepted(State.ACCEPTED);

        InternshipCandidates internshipCandidates6 = new InternshipCandidates();
        internshipCandidates6.setEtudiant(etudiant7);
        internshipCandidates6.setFiles(new ArrayList<>());
        internshipCandidates6.setId(1L);
        internshipCandidates6.setInternOffer(internOffer2);
        internshipCandidates6.setState(State.ACCEPTED);
        internshipCandidates6.setStudentCv(studentCv2);

        File file4 = new File();
        file4.setContent("AXAXAXAX".getBytes("UTF-8"));
        file4.setEtudiant(etudiant6);
        file4.setFileName("foo.txt");
        file4.setId(1L);
        file4.setInternshipCandidates(internshipCandidates6);
        file4.setIsAccepted(State.ACCEPTED);

        TemplateContract templateContract2 = new TemplateContract();
        templateContract2.setCreatedDate(LocalDate.of(1970, 1, 1));
        templateContract2.setFile(file4);
        templateContract2.setId(1L);
        templateContract2.setIsActive(true);
        when(templateContractRepository.save(Mockito.<TemplateContract>any())).thenReturn(templateContract2);
        when(templateContractRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        assertThrows(ServiceException.class, () -> templateContractService.save(new TemplateContractDto()));
        verify(templateContractRepository).findTemplateContractByIsActive();
        verify(templateContractRepository).save(Mockito.<TemplateContract>any());
    }

    /**
     * Method under test: {@link TemplateContractService#save(TemplateContractDto)}
     */
    @Test
    void testSave2() throws UnsupportedEncodingException {
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

        TemplateContract templateContract = new TemplateContract();
        templateContract.setCreatedDate(LocalDate.of(1970, 1, 1));
        templateContract.setFile(file2);
        templateContract.setId(1L);
        templateContract.setIsActive(true);
        when(templateContractRepository.save(Mockito.<TemplateContract>any()))
                .thenThrow(new EmptyResultDataAccessException(3));
        when(templateContractRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        assertThrows(DatabaseException.class, () -> templateContractService.save(new TemplateContractDto()));
        verify(templateContractRepository).findTemplateContractByIsActive();
        verify(templateContractRepository).save(Mockito.<TemplateContract>any());
    }

    /**
     * Method under test: {@link TemplateContractService#save(TemplateContractDto)}
     */
    @Test
    void testSave3() throws UnsupportedEncodingException {
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

        TemplateContract templateContract = new TemplateContract();
        templateContract.setCreatedDate(LocalDate.of(1970, 1, 1));
        templateContract.setFile(file2);
        templateContract.setId(1L);
        templateContract.setIsActive(true);
        when(templateContractRepository.save(Mockito.<TemplateContract>any()))
                .thenThrow(new DatabaseException("Erreur lors de la sauvegarde du PDF"));
        when(templateContractRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        assertThrows(ServiceException.class, () -> templateContractService.save(new TemplateContractDto()));
        verify(templateContractRepository).findTemplateContractByIsActive();
        verify(templateContractRepository).save(Mockito.<TemplateContract>any());
    }

    /**
     * Method under test: {@link TemplateContractService#unsetDefaultPDF()}
     */
    @Test
    void testUnsetDefaultPDF() throws UnsupportedEncodingException {
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

        TemplateContract templateContract = new TemplateContract();
        templateContract.setCreatedDate(LocalDate.of(1970, 1, 1));
        templateContract.setFile(file2);
        templateContract.setId(1L);
        templateContract.setIsActive(true);

        Etudiant etudiant5 = new Etudiant();
        etudiant5.setActiveCv(new File());
        etudiant5.setCv(new ArrayList<>());
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

        InternshipCandidates internshipCandidates4 = new InternshipCandidates();
        internshipCandidates4.setEtudiant(new Etudiant());
        internshipCandidates4.setFiles(new ArrayList<>());
        internshipCandidates4.setId(1L);
        internshipCandidates4.setInternOffer(new InternOffer());
        internshipCandidates4.setState(State.ACCEPTED);
        internshipCandidates4.setStudentCv(new File());

        File activeCv3 = new File();
        activeCv3.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv3.setEtudiant(etudiant5);
        activeCv3.setFileName("foo.txt");
        activeCv3.setId(1L);
        activeCv3.setInternshipCandidates(internshipCandidates4);
        activeCv3.setIsAccepted(State.ACCEPTED);

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Etudiant etudiant6 = new Etudiant();
        etudiant6.setActiveCv(activeCv3);
        etudiant6.setCv(new ArrayList<>());
        etudiant6.setEmail("jane.doe@example.org");
        etudiant6.setId(1L);
        etudiant6.setInternshipsCandidate(new ArrayList<>());
        etudiant6.setMatricule("Matricule");
        etudiant6.setNom("Nom");
        etudiant6.setPassword("iloveyou");
        etudiant6.setPhone("6625550144");
        etudiant6.setPrenom("Prenom");
        etudiant6.setProgramme(programme4);
        etudiant6.setRole(Role.employer);

        File activeCv4 = new File();
        activeCv4.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv4.setEtudiant(new Etudiant());
        activeCv4.setFileName("foo.txt");
        activeCv4.setId(1L);
        activeCv4.setInternshipCandidates(new InternshipCandidates());
        activeCv4.setIsAccepted(State.ACCEPTED);

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

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
        etudiant7.setProgramme(programme5);
        etudiant7.setRole(Role.employer);

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

        Programme programme6 = new Programme();
        programme6.setDescription("The characteristics of someone or something");
        programme6.setId(1L);
        programme6.setNom("Nom");

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur2);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file3);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest2);
        internOffer2.setProgramme(programme6);
        internOffer2.setSalaryByHour(10.0d);
        internOffer2.setSession("Session");
        internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer2.setState(State.ACCEPTED);
        internOffer2.setStatus("Status");
        internOffer2.setTitle("Dr");

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

        InternshipCandidates internshipCandidates5 = new InternshipCandidates();
        internshipCandidates5.setEtudiant(new Etudiant());
        internshipCandidates5.setFiles(new ArrayList<>());
        internshipCandidates5.setId(1L);
        internshipCandidates5.setInternOffer(new InternOffer());
        internshipCandidates5.setState(State.ACCEPTED);
        internshipCandidates5.setStudentCv(new File());

        File studentCv2 = new File();
        studentCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
        studentCv2.setEtudiant(etudiant8);
        studentCv2.setFileName("foo.txt");
        studentCv2.setId(1L);
        studentCv2.setInternshipCandidates(internshipCandidates5);
        studentCv2.setIsAccepted(State.ACCEPTED);

        InternshipCandidates internshipCandidates6 = new InternshipCandidates();
        internshipCandidates6.setEtudiant(etudiant7);
        internshipCandidates6.setFiles(new ArrayList<>());
        internshipCandidates6.setId(1L);
        internshipCandidates6.setInternOffer(internOffer2);
        internshipCandidates6.setState(State.ACCEPTED);
        internshipCandidates6.setStudentCv(studentCv2);

        File file4 = new File();
        file4.setContent("AXAXAXAX".getBytes("UTF-8"));
        file4.setEtudiant(etudiant6);
        file4.setFileName("foo.txt");
        file4.setId(1L);
        file4.setInternshipCandidates(internshipCandidates6);
        file4.setIsAccepted(State.ACCEPTED);

        TemplateContract templateContract2 = new TemplateContract();
        templateContract2.setCreatedDate(LocalDate.of(1970, 1, 1));
        templateContract2.setFile(file4);
        templateContract2.setId(1L);
        templateContract2.setIsActive(true);
        when(templateContractRepository.save(Mockito.<TemplateContract>any())).thenReturn(templateContract2);
        when(templateContractRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        templateContractService.unsetDefaultPDF();
        verify(templateContractRepository).findTemplateContractByIsActive();
        verify(templateContractRepository).save(Mockito.<TemplateContract>any());
        assertFalse(templateContractService.findCurrent().getIsActive());
    }

    /**
     * Method under test: {@link TemplateContractService#unsetDefaultPDF()}
     */
    @Test
    void testUnsetDefaultPDF2() throws UnsupportedEncodingException {
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

        TemplateContract templateContract = new TemplateContract();
        templateContract.setCreatedDate(LocalDate.of(1970, 1, 1));
        templateContract.setFile(file2);
        templateContract.setId(1L);
        templateContract.setIsActive(true);
        when(templateContractRepository.save(Mockito.<TemplateContract>any()))
                .thenThrow(new EmptyResultDataAccessException(3));
        when(templateContractRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        assertThrows(DatabaseException.class, () -> templateContractService.unsetDefaultPDF());
        verify(templateContractRepository).findTemplateContractByIsActive();
        verify(templateContractRepository).save(Mockito.<TemplateContract>any());
    }

    /**
     * Method under test: {@link TemplateContractService#unsetDefaultPDF()}
     */
    @Test
    void testUnsetDefaultPDF3() throws UnsupportedEncodingException {
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

        TemplateContract templateContract = new TemplateContract();
        templateContract.setCreatedDate(LocalDate.of(1970, 1, 1));
        templateContract.setFile(file2);
        templateContract.setId(1L);
        templateContract.setIsActive(true);
        when(templateContractRepository.save(Mockito.<TemplateContract>any())).thenThrow(new DatabaseException("foo"));
        when(templateContractRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        assertThrows(ServiceException.class, () -> templateContractService.unsetDefaultPDF());
        verify(templateContractRepository).findTemplateContractByIsActive();
        verify(templateContractRepository).save(Mockito.<TemplateContract>any());
    }

    /**
     * Method under test: {@link TemplateContractService#findCurrent()}
     */
    @Test
    void testFindCurrent() throws UnsupportedEncodingException {
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

        TemplateContract templateContract = new TemplateContract();
        templateContract.setCreatedDate(LocalDate.of(1970, 1, 1));
        templateContract.setFile(file2);
        templateContract.setId(1L);
        templateContract.setIsActive(true);
        when(templateContractRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        TemplateContract actualFindCurrentResult = templateContractService.findCurrent();
        verify(templateContractRepository).findTemplateContractByIsActive();
        assertSame(templateContract, actualFindCurrentResult);
    }

    /**
     * Method under test: {@link TemplateContractService#getCurrentPDF()}
     */
    @Test
    void testGetCurrentPDF() throws UnsupportedEncodingException {
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

        TemplateContract templateContract = new TemplateContract();
        templateContract.setCreatedDate(LocalDate.of(1970, 1, 1));
        templateContract.setFile(file2);
        templateContract.setId(1L);
        templateContract.setIsActive(true);
        when(templateContractRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        TemplateContractDto actualCurrentPDF = templateContractService.getCurrentPDF();
        verify(templateContractRepository).findTemplateContractByIsActive();
        assertEquals("1970-01-01", actualCurrentPDF.getCreatedDate());
        assertEquals("QVhBWEFYQVg=", actualCurrentPDF.getContent());
        assertEquals("foo.txt", actualCurrentPDF.getFileName());
        assertEquals(1L, actualCurrentPDF.getFileId());
        assertTrue(actualCurrentPDF.getIsActive());
    }

    /**
     * Method under test: {@link TemplateContractService#getCurrentPDF()}
     */
    @Test
    void testGetCurrentPDF2() throws UnsupportedEncodingException {
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

        File activeCv3 = new File();
        activeCv3.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv3.setEtudiant(new Etudiant());
        activeCv3.setFileName("foo.txt");
        activeCv3.setId(1L);
        activeCv3.setInternshipCandidates(new InternshipCandidates());
        activeCv3.setIsAccepted(State.ACCEPTED);

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

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
        etudiant5.setProgramme(programme4);
        etudiant5.setRole(Role.employer);

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

        File studentCv2 = new File();
        studentCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
        studentCv2.setEtudiant(new Etudiant());
        studentCv2.setFileName("foo.txt");
        studentCv2.setId(1L);
        studentCv2.setInternshipCandidates(new InternshipCandidates());
        studentCv2.setIsAccepted(State.ACCEPTED);

        InternshipCandidates internshipCandidates4 = new InternshipCandidates();
        internshipCandidates4.setEtudiant(etudiant6);
        internshipCandidates4.setFiles(new ArrayList<>());
        internshipCandidates4.setId(1L);
        internshipCandidates4.setInternOffer(internOffer2);
        internshipCandidates4.setState(State.ACCEPTED);
        internshipCandidates4.setStudentCv(studentCv2);

        File activeCv4 = new File();
        activeCv4.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv4.setEtudiant(etudiant5);
        activeCv4.setFileName("foo.txt");
        activeCv4.setId(1L);
        activeCv4.setInternshipCandidates(internshipCandidates4);
        activeCv4.setIsAccepted(State.ACCEPTED);

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

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
        etudiant7.setProgramme(programme5);
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

        InternshipCandidates internshipCandidates5 = new InternshipCandidates();
        internshipCandidates5.setEtudiant(new Etudiant());
        internshipCandidates5.setFiles(new ArrayList<>());
        internshipCandidates5.setId(1L);
        internshipCandidates5.setInternOffer(new InternOffer());
        internshipCandidates5.setState(State.ACCEPTED);
        internshipCandidates5.setStudentCv(new File());

        File activeCv5 = new File();
        activeCv5.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv5.setEtudiant(etudiant8);
        activeCv5.setFileName("foo.txt");
        activeCv5.setId(1L);
        activeCv5.setInternshipCandidates(internshipCandidates5);
        activeCv5.setIsAccepted(State.ACCEPTED);

        Programme programme6 = new Programme();
        programme6.setDescription("The characteristics of someone or something");
        programme6.setId(1L);
        programme6.setNom("Nom");

        Etudiant etudiant9 = new Etudiant();
        etudiant9.setActiveCv(activeCv5);
        etudiant9.setCv(new ArrayList<>());
        etudiant9.setEmail("jane.doe@example.org");
        etudiant9.setId(1L);
        etudiant9.setInternshipsCandidate(new ArrayList<>());
        etudiant9.setMatricule("Matricule");
        etudiant9.setNom("Nom");
        etudiant9.setPassword("iloveyou");
        etudiant9.setPhone("6625550144");
        etudiant9.setPrenom("Prenom");
        etudiant9.setProgramme(programme6);
        etudiant9.setRole(Role.employer);

        Programme programme7 = new Programme();
        programme7.setDescription("The characteristics of someone or something");
        programme7.setId(1L);
        programme7.setNom("Nom");

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(programme7);
        employeur2.setRole(Role.employer);

        Etudiant etudiant10 = new Etudiant();
        etudiant10.setActiveCv(new File());
        etudiant10.setCv(new ArrayList<>());
        etudiant10.setEmail("jane.doe@example.org");
        etudiant10.setId(1L);
        etudiant10.setInternshipsCandidate(new ArrayList<>());
        etudiant10.setMatricule("Matricule");
        etudiant10.setNom("Nom");
        etudiant10.setPassword("iloveyou");
        etudiant10.setPhone("6625550144");
        etudiant10.setPrenom("Prenom");
        etudiant10.setProgramme(new Programme());
        etudiant10.setRole(Role.employer);

        InternshipCandidates internshipCandidates6 = new InternshipCandidates();
        internshipCandidates6.setEtudiant(new Etudiant());
        internshipCandidates6.setFiles(new ArrayList<>());
        internshipCandidates6.setId(1L);
        internshipCandidates6.setInternOffer(new InternOffer());
        internshipCandidates6.setState(State.ACCEPTED);
        internshipCandidates6.setStudentCv(new File());

        File file3 = new File();
        file3.setContent("AXAXAXAX".getBytes("UTF-8"));
        file3.setEtudiant(etudiant10);
        file3.setFileName("foo.txt");
        file3.setId(1L);
        file3.setInternshipCandidates(internshipCandidates6);
        file3.setIsAccepted(State.ACCEPTED);

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
        internOffer3.setSession("Session");
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(internOffer3);
        offerReviewRequest2.setInternshipmanager(internshipmanager);

        Programme programme8 = new Programme();
        programme8.setDescription("The characteristics of someone or something");
        programme8.setId(1L);
        programme8.setNom("Nom");

        InternOffer internOffer4 = new InternOffer();
        internOffer4.setDescription("The characteristics of someone or something");
        internOffer4.setEmployeur(employeur2);
        internOffer4.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer4.setFile(file3);
        internOffer4.setId(1L);
        internOffer4.setInternshipCandidates(new ArrayList<>());
        internOffer4.setLocation("Location");
        internOffer4.setOfferReviewRequest(offerReviewRequest2);
        internOffer4.setProgramme(programme8);
        internOffer4.setSalaryByHour(10.0d);
        internOffer4.setSession("Session");
        internOffer4.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer4.setState(State.ACCEPTED);
        internOffer4.setStatus("Status");
        internOffer4.setTitle("Dr");

        File activeCv6 = new File();
        activeCv6.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv6.setEtudiant(new Etudiant());
        activeCv6.setFileName("foo.txt");
        activeCv6.setId(1L);
        activeCv6.setInternshipCandidates(new InternshipCandidates());
        activeCv6.setIsAccepted(State.ACCEPTED);

        Programme programme9 = new Programme();
        programme9.setDescription("The characteristics of someone or something");
        programme9.setId(1L);
        programme9.setNom("Nom");

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
        etudiant11.setProgramme(programme9);
        etudiant11.setRole(Role.employer);

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

        InternOffer internOffer5 = new InternOffer();
        internOffer5.setDescription("The characteristics of someone or something");
        internOffer5.setEmployeur(new Employeur());
        internOffer5.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer5.setFile(new File());
        internOffer5.setId(1L);
        internOffer5.setInternshipCandidates(new ArrayList<>());
        internOffer5.setLocation("Location");
        internOffer5.setOfferReviewRequest(new OfferReviewRequest());
        internOffer5.setProgramme(new Programme());
        internOffer5.setSalaryByHour(10.0d);
        internOffer5.setSession("Session");
        internOffer5.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer5.setState(State.ACCEPTED);
        internOffer5.setStatus("Status");
        internOffer5.setTitle("Dr");

        File studentCv3 = new File();
        studentCv3.setContent("AXAXAXAX".getBytes("UTF-8"));
        studentCv3.setEtudiant(new Etudiant());
        studentCv3.setFileName("foo.txt");
        studentCv3.setId(1L);
        studentCv3.setInternshipCandidates(new InternshipCandidates());
        studentCv3.setIsAccepted(State.ACCEPTED);

        InternshipCandidates internshipCandidates7 = new InternshipCandidates();
        internshipCandidates7.setEtudiant(etudiant12);
        internshipCandidates7.setFiles(new ArrayList<>());
        internshipCandidates7.setId(1L);
        internshipCandidates7.setInternOffer(internOffer5);
        internshipCandidates7.setState(State.ACCEPTED);
        internshipCandidates7.setStudentCv(studentCv3);

        File studentCv4 = new File();
        studentCv4.setContent("AXAXAXAX".getBytes("UTF-8"));
        studentCv4.setEtudiant(etudiant11);
        studentCv4.setFileName("foo.txt");
        studentCv4.setId(1L);
        studentCv4.setInternshipCandidates(internshipCandidates7);
        studentCv4.setIsAccepted(State.ACCEPTED);

        InternshipCandidates internshipCandidates8 = new InternshipCandidates();
        internshipCandidates8.setEtudiant(etudiant9);
        internshipCandidates8.setFiles(new ArrayList<>());
        internshipCandidates8.setId(1L);
        internshipCandidates8.setInternOffer(internOffer4);
        internshipCandidates8.setState(State.ACCEPTED);
        internshipCandidates8.setStudentCv(studentCv4);

        File file4 = new File();
        file4.setContent("AXAXAXAX".getBytes("UTF-8"));
        file4.setEtudiant(etudiant7);
        file4.setFileName("foo.txt");
        file4.setId(1L);
        file4.setInternshipCandidates(internshipCandidates8);
        file4.setIsAccepted(State.ACCEPTED);
        TemplateContract templateContract = mock(TemplateContract.class);
        when(templateContract.getFile()).thenReturn(file4);
        when(templateContract.getIsActive()).thenReturn(true);
        when(templateContract.getCreatedDate()).thenReturn(LocalDate.of(1970, 1, 1));
        doNothing().when(templateContract).setCreatedDate(Mockito.<LocalDate>any());
        doNothing().when(templateContract).setFile(Mockito.<File>any());
        doNothing().when(templateContract).setId(anyLong());
        doNothing().when(templateContract).setIsActive(Mockito.<Boolean>any());
        templateContract.setCreatedDate(LocalDate.of(1970, 1, 1));
        templateContract.setFile(file2);
        templateContract.setId(1L);
        templateContract.setIsActive(true);
        when(templateContractRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        TemplateContractDto actualCurrentPDF = templateContractService.getCurrentPDF();
        verify(templateContract).getCreatedDate();
        verify(templateContract, atLeast(1)).getFile();
        verify(templateContract).getIsActive();
        verify(templateContract).setCreatedDate(Mockito.<LocalDate>any());
        verify(templateContract).setFile(Mockito.<File>any());
        verify(templateContract).setId(anyLong());
        verify(templateContract).setIsActive(Mockito.<Boolean>any());
        verify(templateContractRepository).findTemplateContractByIsActive();
        assertEquals("1970-01-01", actualCurrentPDF.getCreatedDate());
        assertEquals("QVhBWEFYQVg=", actualCurrentPDF.getContent());
        assertEquals("foo.txt", actualCurrentPDF.getFileName());
        assertEquals(1L, actualCurrentPDF.getFileId());
        assertTrue(actualCurrentPDF.getIsActive());
    }

    /**
     * Method under test: {@link TemplateContractService#getCurrentPDF()}
     */
    @Test
    void testGetCurrentPDF3() throws UnsupportedEncodingException {
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
        TemplateContract templateContract = mock(TemplateContract.class);
        when(templateContract.getIsActive())
                .thenThrow(new RuntimeException("Erreur lors de la récupération du PDF par défaut"));
        when(templateContract.getCreatedDate()).thenReturn(LocalDate.of(1970, 1, 1));
        doNothing().when(templateContract).setCreatedDate(Mockito.<LocalDate>any());
        doNothing().when(templateContract).setFile(Mockito.<File>any());
        doNothing().when(templateContract).setId(anyLong());
        doNothing().when(templateContract).setIsActive(Mockito.<Boolean>any());
        templateContract.setCreatedDate(LocalDate.of(1970, 1, 1));
        templateContract.setFile(file2);
        templateContract.setId(1L);
        templateContract.setIsActive(true);
        when(templateContractRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        assertThrows(ServiceException.class, () -> templateContractService.getCurrentPDF());
        verify(templateContract).getCreatedDate();
        verify(templateContract).getIsActive();
        verify(templateContract).setCreatedDate(Mockito.<LocalDate>any());
        verify(templateContract).setFile(Mockito.<File>any());
        verify(templateContract).setId(anyLong());
        verify(templateContract).setIsActive(Mockito.<Boolean>any());
        verify(templateContractRepository).findTemplateContractByIsActive();
    }
}
