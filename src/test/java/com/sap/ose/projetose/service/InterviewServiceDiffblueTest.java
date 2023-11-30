package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.InterviewDTO;
import com.sap.ose.projetose.dto.InterviewRequestInDto;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import com.sap.ose.projetose.modeles.Internshipmanager;
import com.sap.ose.projetose.modeles.Interview;
import com.sap.ose.projetose.modeles.OfferReviewRequest;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.modeles.Role;
import com.sap.ose.projetose.modeles.State;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.FileEntityRepository;
import com.sap.ose.projetose.repository.InternOfferRepository;
import com.sap.ose.projetose.repository.InternshipCandidatesRepository;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import com.sap.ose.projetose.repository.InterviewRepository;
import com.sap.ose.projetose.repository.NotificationRepository;
import com.sap.ose.projetose.repository.ProgrammeRepository;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {InterviewService.class})
@ExtendWith(SpringExtension.class)
class InterviewServiceDiffblueTest {
  @MockBean
  private EmployeurRepository employeurRepository;

  @MockBean
  private EmployeurService employeurService;

  @MockBean
  private EtudiantRepository etudiantRepository;

  @MockBean
  private EtudiantService etudiantService;

  @MockBean
  private InternOfferRepository internOfferRepository;

  @MockBean
  private InterviewRepository interviewRepository;

  @Autowired
  private InterviewService interviewService;

  /**
   * Method under test: {@link InterviewService#saveInterview(InterviewRequestInDto)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testSaveInterview() {
    // TODO: Complete this test.
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    InterviewRequestInDto interviewRequestInDto = null;

    // Act
    Optional<InterviewDTO> actualSaveInterviewResult = this.interviewService.saveInterview(interviewRequestInDto);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Method under test: {@link InterviewService#getInterviewById(Long)}
   */
  @Test
  void testGetInterviewById() throws UnsupportedEncodingException {
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

    File file = new File();
    file.setContent("AXAXAXAX".getBytes("UTF-8"));
    file.setEtudiant(etudiant);
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
    internOffer.setSession("Session");
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

    Programme programme2 = new Programme();
    programme2.setDescription("The characteristics of someone or something");
    programme2.setId(1L);
    programme2.setNom("Nom");

    InternOffer internshipOffer = new InternOffer();
    internshipOffer.setDescription("The characteristics of someone or something");
    internshipOffer.setEmployeur(employeur);
    internshipOffer.setEndDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setFile(file);
    internshipOffer.setId(1L);
    internshipOffer.setInternshipCandidates(new ArrayList<>());
    internshipOffer.setLocation("Location");
    internshipOffer.setOfferReviewRequest(offerReviewRequest);
    internshipOffer.setProgramme(programme2);
    internshipOffer.setSalaryByHour(10.0d);
    internshipOffer.setSession("Session");
    internshipOffer.setStartDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setState(State.ACCEPTED);
    internshipOffer.setStatus("Status");
    internshipOffer.setTitle("Dr");

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

    InternshipCandidates internshipCandidates2 = new InternshipCandidates();
    internshipCandidates2.setEtudiant(new Etudiant());
    internshipCandidates2.setFiles(new ArrayList<>());
    internshipCandidates2.setId(1L);
    internshipCandidates2.setInternOffer(new InternOffer());
    internshipCandidates2.setState(State.ACCEPTED);
    internshipCandidates2.setStudentCv(new File());

    File activeCv = new File();
    activeCv.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv.setEtudiant(etudiant2);
    activeCv.setFileName("foo.txt");
    activeCv.setId(1L);
    activeCv.setInternshipCandidates(internshipCandidates2);
    activeCv.setIsAccepted(State.ACCEPTED);

    Programme programme3 = new Programme();
    programme3.setDescription("The characteristics of someone or something");
    programme3.setId(1L);
    programme3.setNom("Nom");

    Etudiant student = new Etudiant();
    student.setActiveCv(activeCv);
    student.setCv(new ArrayList<>());
    student.setEmail("jane.doe@example.org");
    student.setId(1L);
    student.setInternshipsCandidate(new ArrayList<>());
    student.setMatricule("Matricule");
    student.setNom("Nom");
    student.setPassword("iloveyou");
    student.setPhone("6625550144");
    student.setPrenom("Prenom");
    student.setProgramme(programme3);
    student.setRole(Role.employer);

    Interview interview = new Interview();
    interview.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    interview.setDescription("The characteristics of someone or something");
    interview.setId(1L);
    interview.setInternshipOffer(internshipOffer);
    interview.setState(State.ACCEPTED);
    interview.setStudent(student);
    Optional<Interview> ofResult = Optional.of(interview);
    when(interviewRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    Interview actualInterviewById = interviewService.getInterviewById(1L);
    verify(interviewRepository).findById(Mockito.<Long>any());
    assertSame(interview, actualInterviewById);
  }

  /**
   * Method under test: {@link InterviewService#studentHasInterviewWithEmployeur(Long, Long)}
   */
  @Test
  void testStudentHasInterviewWithEmployeur() {
    when(interviewRepository.findAll()).thenReturn(new ArrayList<>());
    Boolean actualStudentHasInterviewWithEmployeurResult = interviewService.studentHasInterviewWithEmployeur(1L, 1L);
    verify(interviewRepository).findAll();
    assertFalse(actualStudentHasInterviewWithEmployeurResult);
  }

  /**
   * Method under test: {@link InterviewService#studentHasInterviewWithEmployeur(Long, Long)}
   */
  @Test
  void testStudentHasInterviewWithEmployeur2() throws UnsupportedEncodingException {
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

    File file = new File();
    file.setContent("AXAXAXAX".getBytes("UTF-8"));
    file.setEtudiant(etudiant);
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
    internOffer.setSession("Session");
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

    Programme programme2 = new Programme();
    programme2.setDescription("The characteristics of someone or something");
    programme2.setId(1L);
    programme2.setNom("Nom");

    InternOffer internshipOffer = new InternOffer();
    internshipOffer.setDescription("The characteristics of someone or something");
    internshipOffer.setEmployeur(employeur);
    internshipOffer.setEndDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setFile(file);
    internshipOffer.setId(1L);
    internshipOffer.setInternshipCandidates(new ArrayList<>());
    internshipOffer.setLocation("Location");
    internshipOffer.setOfferReviewRequest(offerReviewRequest);
    internshipOffer.setProgramme(programme2);
    internshipOffer.setSalaryByHour(10.0d);
    internshipOffer.setSession("Session");
    internshipOffer.setStartDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setState(State.ACCEPTED);
    internshipOffer.setStatus("Status");
    internshipOffer.setTitle("Dr");

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

    InternshipCandidates internshipCandidates2 = new InternshipCandidates();
    internshipCandidates2.setEtudiant(new Etudiant());
    internshipCandidates2.setFiles(new ArrayList<>());
    internshipCandidates2.setId(1L);
    internshipCandidates2.setInternOffer(new InternOffer());
    internshipCandidates2.setState(State.ACCEPTED);
    internshipCandidates2.setStudentCv(new File());

    File activeCv = new File();
    activeCv.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv.setEtudiant(etudiant2);
    activeCv.setFileName("foo.txt");
    activeCv.setId(1L);
    activeCv.setInternshipCandidates(internshipCandidates2);
    activeCv.setIsAccepted(State.ACCEPTED);

    Programme programme3 = new Programme();
    programme3.setDescription("The characteristics of someone or something");
    programme3.setId(1L);
    programme3.setNom("Nom");

    Etudiant student = new Etudiant();
    student.setActiveCv(activeCv);
    student.setCv(new ArrayList<>());
    student.setEmail("jane.doe@example.org");
    student.setId(1L);
    student.setInternshipsCandidate(new ArrayList<>());
    student.setMatricule("Matricule");
    student.setNom("Nom");
    student.setPassword("iloveyou");
    student.setPhone("6625550144");
    student.setPrenom("Prenom");
    student.setProgramme(programme3);
    student.setRole(Role.employer);

    Interview interview = new Interview();
    interview.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    interview.setDescription("The characteristics of someone or something");
    interview.setId(1L);
    interview.setInternshipOffer(internshipOffer);
    interview.setState(State.ACCEPTED);
    interview.setStudent(student);

    ArrayList<Interview> interviewList = new ArrayList<>();
    interviewList.add(interview);
    when(interviewRepository.findAll()).thenReturn(interviewList);
    Boolean actualStudentHasInterviewWithEmployeurResult = interviewService.studentHasInterviewWithEmployeur(1L, 1L);
    verify(interviewRepository).findAll();
    assertTrue(actualStudentHasInterviewWithEmployeurResult);
  }

  /**
   * Method under test: {@link InterviewService#studentHasInterviewWithEmployeur(Long, Long)}
   */
  @Test
  void testStudentHasInterviewWithEmployeur3() throws UnsupportedEncodingException {
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

    File file = new File();
    file.setContent("AXAXAXAX".getBytes("UTF-8"));
    file.setEtudiant(etudiant);
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
    internOffer.setSession("Session");
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

    Programme programme2 = new Programme();
    programme2.setDescription("The characteristics of someone or something");
    programme2.setId(1L);
    programme2.setNom("Nom");

    InternOffer internshipOffer = new InternOffer();
    internshipOffer.setDescription("The characteristics of someone or something");
    internshipOffer.setEmployeur(employeur);
    internshipOffer.setEndDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setFile(file);
    internshipOffer.setId(1L);
    internshipOffer.setInternshipCandidates(new ArrayList<>());
    internshipOffer.setLocation("Location");
    internshipOffer.setOfferReviewRequest(offerReviewRequest);
    internshipOffer.setProgramme(programme2);
    internshipOffer.setSalaryByHour(10.0d);
    internshipOffer.setSession("Session");
    internshipOffer.setStartDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setState(State.ACCEPTED);
    internshipOffer.setStatus("Status");
    internshipOffer.setTitle("Dr");

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

    InternshipCandidates internshipCandidates2 = new InternshipCandidates();
    internshipCandidates2.setEtudiant(new Etudiant());
    internshipCandidates2.setFiles(new ArrayList<>());
    internshipCandidates2.setId(1L);
    internshipCandidates2.setInternOffer(new InternOffer());
    internshipCandidates2.setState(State.ACCEPTED);
    internshipCandidates2.setStudentCv(new File());

    File activeCv = new File();
    activeCv.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv.setEtudiant(etudiant2);
    activeCv.setFileName("foo.txt");
    activeCv.setId(1L);
    activeCv.setInternshipCandidates(internshipCandidates2);
    activeCv.setIsAccepted(State.ACCEPTED);

    Programme programme3 = new Programme();
    programme3.setDescription("The characteristics of someone or something");
    programme3.setId(1L);
    programme3.setNom("Nom");

    Etudiant student = new Etudiant();
    student.setActiveCv(activeCv);
    student.setCv(new ArrayList<>());
    student.setEmail("jane.doe@example.org");
    student.setId(1L);
    student.setInternshipsCandidate(new ArrayList<>());
    student.setMatricule("Matricule");
    student.setNom("Nom");
    student.setPassword("iloveyou");
    student.setPhone("6625550144");
    student.setPrenom("Prenom");
    student.setProgramme(programme3);
    student.setRole(Role.employer);

    Interview interview = new Interview();
    interview.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    interview.setDescription("The characteristics of someone or something");
    interview.setId(1L);
    interview.setInternshipOffer(internshipOffer);
    interview.setState(State.ACCEPTED);
    interview.setStudent(student);

    Programme programme4 = new Programme();
    programme4.setDescription("Description");
    programme4.setId(2L);
    programme4.setNom("com.sap.ose.projetose.modeles.Programme");

    Employeur employeur2 = new Employeur();
    employeur2.setEmail("john.smith@example.org");
    employeur2.setEntreprise("com.sap.ose.projetose.modeles.Employeur");
    employeur2.setId(2L);
    employeur2.setInternOffers(new ArrayList<>());
    employeur2.setNom("com.sap.ose.projetose.modeles.Employeur");
    employeur2.setPassword("Password");
    employeur2.setPhone("8605550118");
    employeur2.setPrenom("com.sap.ose.projetose.modeles.Employeur");
    employeur2.setProgramme(programme4);
    employeur2.setRole(Role.student);

    Etudiant etudiant3 = new Etudiant();
    etudiant3.setActiveCv(new File());
    etudiant3.setCv(new ArrayList<>());
    etudiant3.setEmail("john.smith@example.org");
    etudiant3.setId(2L);
    etudiant3.setInternshipsCandidate(new ArrayList<>());
    etudiant3.setMatricule("com.sap.ose.projetose.modeles.Etudiant");
    etudiant3.setNom("com.sap.ose.projetose.modeles.Etudiant");
    etudiant3.setPassword("Password");
    etudiant3.setPhone("8605550118");
    etudiant3.setPrenom("com.sap.ose.projetose.modeles.Etudiant");
    etudiant3.setProgramme(new Programme());
    etudiant3.setRole(Role.student);

    InternshipCandidates internshipCandidates3 = new InternshipCandidates();
    internshipCandidates3.setEtudiant(new Etudiant());
    internshipCandidates3.setFiles(new ArrayList<>());
    internshipCandidates3.setId(2L);
    internshipCandidates3.setInternOffer(new InternOffer());
    internshipCandidates3.setState(State.PENDING);
    internshipCandidates3.setStudentCv(new File());

    File file2 = new File();
    file2.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    file2.setEtudiant(etudiant3);
    file2.setFileName("File Name");
    file2.setId(2L);
    file2.setInternshipCandidates(internshipCandidates3);
    file2.setIsAccepted(State.PENDING);

    InternOffer internOffer2 = new InternOffer();
    internOffer2.setDescription("Description");
    internOffer2.setEmployeur(new Employeur());
    internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
    internOffer2.setFile(new File());
    internOffer2.setId(2L);
    internOffer2.setInternshipCandidates(new ArrayList<>());
    internOffer2.setLocation("com.sap.ose.projetose.modeles.InternOffer");
    internOffer2.setOfferReviewRequest(new OfferReviewRequest());
    internOffer2.setProgramme(new Programme());
    internOffer2.setSalaryByHour(0.5d);
    internOffer2.setSession("com.sap.ose.projetose.modeles.InternOffer");
    internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
    internOffer2.setState(State.PENDING);
    internOffer2.setStatus("com.sap.ose.projetose.modeles.InternOffer");
    internOffer2.setTitle("Mr");

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
    offerReviewRequest2.setInternOffer(internOffer2);
    offerReviewRequest2.setInternshipmanager(internshipmanager2);

    Programme programme5 = new Programme();
    programme5.setDescription("Description");
    programme5.setId(2L);
    programme5.setNom("com.sap.ose.projetose.modeles.Programme");

    InternOffer internshipOffer2 = new InternOffer();
    internshipOffer2.setDescription("Description");
    internshipOffer2.setEmployeur(employeur2);
    internshipOffer2.setEndDate(LocalDate.of(1970, 1, 1));
    internshipOffer2.setFile(file2);
    internshipOffer2.setId(2L);
    internshipOffer2.setInternshipCandidates(new ArrayList<>());
    internshipOffer2.setLocation("com.sap.ose.projetose.modeles.InternOffer");
    internshipOffer2.setOfferReviewRequest(offerReviewRequest2);
    internshipOffer2.setProgramme(programme5);
    internshipOffer2.setSalaryByHour(0.5d);
    internshipOffer2.setSession("com.sap.ose.projetose.modeles.InternOffer");
    internshipOffer2.setStartDate(LocalDate.of(1970, 1, 1));
    internshipOffer2.setState(State.PENDING);
    internshipOffer2.setStatus("com.sap.ose.projetose.modeles.InternOffer");
    internshipOffer2.setTitle("Mr");

    Etudiant etudiant4 = new Etudiant();
    etudiant4.setActiveCv(new File());
    etudiant4.setCv(new ArrayList<>());
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

    InternshipCandidates internshipCandidates4 = new InternshipCandidates();
    internshipCandidates4.setEtudiant(new Etudiant());
    internshipCandidates4.setFiles(new ArrayList<>());
    internshipCandidates4.setId(2L);
    internshipCandidates4.setInternOffer(new InternOffer());
    internshipCandidates4.setState(State.PENDING);
    internshipCandidates4.setStudentCv(new File());

    File activeCv2 = new File();
    activeCv2.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    activeCv2.setEtudiant(etudiant4);
    activeCv2.setFileName("File Name");
    activeCv2.setId(2L);
    activeCv2.setInternshipCandidates(internshipCandidates4);
    activeCv2.setIsAccepted(State.PENDING);

    Programme programme6 = new Programme();
    programme6.setDescription("Description");
    programme6.setId(2L);
    programme6.setNom("com.sap.ose.projetose.modeles.Programme");

    Etudiant student2 = new Etudiant();
    student2.setActiveCv(activeCv2);
    student2.setCv(new ArrayList<>());
    student2.setEmail("john.smith@example.org");
    student2.setId(2L);
    student2.setInternshipsCandidate(new ArrayList<>());
    student2.setMatricule("com.sap.ose.projetose.modeles.Etudiant");
    student2.setNom("com.sap.ose.projetose.modeles.Etudiant");
    student2.setPassword("Password");
    student2.setPhone("8605550118");
    student2.setPrenom("com.sap.ose.projetose.modeles.Etudiant");
    student2.setProgramme(programme6);
    student2.setRole(Role.student);

    Interview interview2 = new Interview();
    interview2.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    interview2.setDescription("Description");
    interview2.setId(2L);
    interview2.setInternshipOffer(internshipOffer2);
    interview2.setState(State.PENDING);
    interview2.setStudent(student2);

    ArrayList<Interview> interviewList = new ArrayList<>();
    interviewList.add(interview2);
    interviewList.add(interview);
    when(interviewRepository.findAll()).thenReturn(interviewList);
    Boolean actualStudentHasInterviewWithEmployeurResult = interviewService.studentHasInterviewWithEmployeur(1L, 1L);
    verify(interviewRepository).findAll();
    assertTrue(actualStudentHasInterviewWithEmployeurResult);
  }

  /**
   * Method under test: {@link InterviewService#getInterviewsByStudentId(long, int, int, String, String, String)}
   */
  @Test
  void testGetInterviewsByStudentId() {
    when(interviewRepository.findAllByStudentId(anyLong(), Mockito.<Pageable>any(), Mockito.<String>any()))
            .thenReturn(new PageImpl<>(new ArrayList<>()));
    Page<InterviewDTO> actualInterviewsByStudentId = interviewService.getInterviewsByStudentId(1L, 1, 3, "Sort Field",
            "Sort Direction", "Season");
    verify(interviewRepository).findAllByStudentId(anyLong(), Mockito.<Pageable>any(), Mockito.<String>any());
    assertTrue(actualInterviewsByStudentId.toList().isEmpty());
  }

  /**
   * Method under test: {@link InterviewService#getInterviewsByStudentId(long, int, int, String, String, String)}
   */
  @Test
  void testGetInterviewsByStudentId2() {
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

    File file = new File();
    file.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    file.setEtudiant(etudiant);
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
    internOffer.setSession("Session");
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

    Programme programme2 = new Programme();
    programme2.setDescription("The characteristics of someone or something");
    programme2.setId(1L);
    programme2.setNom("Nom");

    InternOffer internshipOffer = new InternOffer();
    internshipOffer.setDescription("The characteristics of someone or something");
    internshipOffer.setEmployeur(employeur);
    internshipOffer.setEndDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setFile(file);
    internshipOffer.setId(1L);
    internshipOffer.setInternshipCandidates(new ArrayList<>());
    internshipOffer.setLocation("Location");
    internshipOffer.setOfferReviewRequest(offerReviewRequest);
    internshipOffer.setProgramme(programme2);
    internshipOffer.setSalaryByHour(10.0d);
    internshipOffer.setSession("Session");
    internshipOffer.setStartDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setState(State.ACCEPTED);
    internshipOffer.setStatus("Status");
    internshipOffer.setTitle("Dr");

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

    InternshipCandidates internshipCandidates2 = new InternshipCandidates();
    internshipCandidates2.setEtudiant(new Etudiant());
    internshipCandidates2.setFiles(new ArrayList<>());
    internshipCandidates2.setId(1L);
    internshipCandidates2.setInternOffer(new InternOffer());
    internshipCandidates2.setState(State.ACCEPTED);
    internshipCandidates2.setStudentCv(new File());

    File activeCv = new File();
    activeCv.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    activeCv.setEtudiant(etudiant2);
    activeCv.setFileName("foo.txt");
    activeCv.setId(1L);
    activeCv.setInternshipCandidates(internshipCandidates2);
    activeCv.setIsAccepted(State.ACCEPTED);

    Programme programme3 = new Programme();
    programme3.setDescription("The characteristics of someone or something");
    programme3.setId(1L);
    programme3.setNom("Nom");

    Etudiant student = new Etudiant();
    student.setActiveCv(activeCv);
    student.setCv(new ArrayList<>());
    student.setEmail("jane.doe@example.org");
    student.setId(1L);
    student.setInternshipsCandidate(new ArrayList<>());
    student.setMatricule("Matricule");
    student.setNom("Nom");
    student.setPassword("iloveyou");
    student.setPhone("6625550144");
    student.setPrenom("Prenom");
    student.setProgramme(programme3);
    student.setRole(Role.employer);

    Interview interview = new Interview();
    interview.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    interview.setDescription("The characteristics of someone or something");
    interview.setId(1L);
    interview.setInternshipOffer(internshipOffer);
    interview.setState(State.ACCEPTED);
    interview.setStudent(student);

    ArrayList<Interview> content = new ArrayList<>();
    content.add(interview);
    PageImpl<Interview> pageImpl = new PageImpl<>(content);
    when(interviewRepository.findAllByStudentId(anyLong(), Mockito.<Pageable>any(), Mockito.<String>any()))
            .thenReturn(pageImpl);
    Page<InterviewDTO> actualInterviewsByStudentId = interviewService.getInterviewsByStudentId(1L, 1, 3, "Sort Field",
            "Sort Direction", "Season");
    verify(interviewRepository).findAllByStudentId(anyLong(), Mockito.<Pageable>any(), Mockito.<String>any());
    assertEquals(1, actualInterviewsByStudentId.toList().size());
  }

  /**
   * Method under test: {@link InterviewService#getInterviewsByStudentId(long, int, int, String, String, String)}
   */
  @Test
  void testGetInterviewsByStudentId3() {
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

    File file = new File();
    file.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    file.setEtudiant(etudiant);
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
    internOffer.setSession("Session");
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

    Programme programme2 = new Programme();
    programme2.setDescription("The characteristics of someone or something");
    programme2.setId(1L);
    programme2.setNom("Nom");

    InternOffer internshipOffer = new InternOffer();
    internshipOffer.setDescription("The characteristics of someone or something");
    internshipOffer.setEmployeur(employeur);
    internshipOffer.setEndDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setFile(file);
    internshipOffer.setId(1L);
    internshipOffer.setInternshipCandidates(new ArrayList<>());
    internshipOffer.setLocation("Location");
    internshipOffer.setOfferReviewRequest(offerReviewRequest);
    internshipOffer.setProgramme(programme2);
    internshipOffer.setSalaryByHour(10.0d);
    internshipOffer.setSession("Session");
    internshipOffer.setStartDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setState(State.ACCEPTED);
    internshipOffer.setStatus("Status");
    internshipOffer.setTitle("Dr");

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

    InternshipCandidates internshipCandidates2 = new InternshipCandidates();
    internshipCandidates2.setEtudiant(new Etudiant());
    internshipCandidates2.setFiles(new ArrayList<>());
    internshipCandidates2.setId(1L);
    internshipCandidates2.setInternOffer(new InternOffer());
    internshipCandidates2.setState(State.ACCEPTED);
    internshipCandidates2.setStudentCv(new File());

    File activeCv = new File();
    activeCv.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    activeCv.setEtudiant(etudiant2);
    activeCv.setFileName("foo.txt");
    activeCv.setId(1L);
    activeCv.setInternshipCandidates(internshipCandidates2);
    activeCv.setIsAccepted(State.ACCEPTED);

    Programme programme3 = new Programme();
    programme3.setDescription("The characteristics of someone or something");
    programme3.setId(1L);
    programme3.setNom("Nom");

    Etudiant student = new Etudiant();
    student.setActiveCv(activeCv);
    student.setCv(new ArrayList<>());
    student.setEmail("jane.doe@example.org");
    student.setId(1L);
    student.setInternshipsCandidate(new ArrayList<>());
    student.setMatricule("Matricule");
    student.setNom("Nom");
    student.setPassword("iloveyou");
    student.setPhone("6625550144");
    student.setPrenom("Prenom");
    student.setProgramme(programme3);
    student.setRole(Role.employer);

    Interview interview = new Interview();
    interview.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    interview.setDescription("The characteristics of someone or something");
    interview.setId(1L);
    interview.setInternshipOffer(internshipOffer);
    interview.setState(State.ACCEPTED);
    interview.setStudent(student);

    Programme programme4 = new Programme();
    programme4.setDescription("Description");
    programme4.setId(2L);
    programme4.setNom("com.sap.ose.projetose.modeles.Programme");

    Employeur employeur2 = new Employeur();
    employeur2.setEmail("john.smith@example.org");
    employeur2.setEntreprise("com.sap.ose.projetose.modeles.Employeur");
    employeur2.setId(2L);
    employeur2.setInternOffers(new ArrayList<>());
    employeur2.setNom("com.sap.ose.projetose.modeles.Employeur");
    employeur2.setPassword("Password");
    employeur2.setPhone("8605550118");
    employeur2.setPrenom("com.sap.ose.projetose.modeles.Employeur");
    employeur2.setProgramme(programme4);
    employeur2.setRole(Role.student);

    Etudiant etudiant3 = new Etudiant();
    etudiant3.setActiveCv(new File());
    etudiant3.setCv(new ArrayList<>());
    etudiant3.setEmail("john.smith@example.org");
    etudiant3.setId(2L);
    etudiant3.setInternshipsCandidate(new ArrayList<>());
    etudiant3.setMatricule("com.sap.ose.projetose.modeles.Etudiant");
    etudiant3.setNom("com.sap.ose.projetose.modeles.Etudiant");
    etudiant3.setPassword("Password");
    etudiant3.setPhone("8605550118");
    etudiant3.setPrenom("com.sap.ose.projetose.modeles.Etudiant");
    etudiant3.setProgramme(new Programme());
    etudiant3.setRole(Role.student);

    InternshipCandidates internshipCandidates3 = new InternshipCandidates();
    internshipCandidates3.setEtudiant(new Etudiant());
    internshipCandidates3.setFiles(new ArrayList<>());
    internshipCandidates3.setId(2L);
    internshipCandidates3.setInternOffer(new InternOffer());
    internshipCandidates3.setState(State.PENDING);
    internshipCandidates3.setStudentCv(new File());

    File file2 = new File();
    file2.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    file2.setEtudiant(etudiant3);
    file2.setFileName("File Name");
    file2.setId(2L);
    file2.setInternshipCandidates(internshipCandidates3);
    file2.setIsAccepted(State.PENDING);

    InternOffer internOffer2 = new InternOffer();
    internOffer2.setDescription("Description");
    internOffer2.setEmployeur(new Employeur());
    internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
    internOffer2.setFile(new File());
    internOffer2.setId(2L);
    internOffer2.setInternshipCandidates(new ArrayList<>());
    internOffer2.setLocation("com.sap.ose.projetose.modeles.InternOffer");
    internOffer2.setOfferReviewRequest(new OfferReviewRequest());
    internOffer2.setProgramme(new Programme());
    internOffer2.setSalaryByHour(0.5d);
    internOffer2.setSession("com.sap.ose.projetose.modeles.InternOffer");
    internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
    internOffer2.setState(State.PENDING);
    internOffer2.setStatus("com.sap.ose.projetose.modeles.InternOffer");
    internOffer2.setTitle("Mr");

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
    offerReviewRequest2.setInternOffer(internOffer2);
    offerReviewRequest2.setInternshipmanager(internshipmanager2);

    Programme programme5 = new Programme();
    programme5.setDescription("Description");
    programme5.setId(2L);
    programme5.setNom("com.sap.ose.projetose.modeles.Programme");

    InternOffer internshipOffer2 = new InternOffer();
    internshipOffer2.setDescription("Description");
    internshipOffer2.setEmployeur(employeur2);
    internshipOffer2.setEndDate(LocalDate.of(1970, 1, 1));
    internshipOffer2.setFile(file2);
    internshipOffer2.setId(2L);
    internshipOffer2.setInternshipCandidates(new ArrayList<>());
    internshipOffer2.setLocation("com.sap.ose.projetose.modeles.InternOffer");
    internshipOffer2.setOfferReviewRequest(offerReviewRequest2);
    internshipOffer2.setProgramme(programme5);
    internshipOffer2.setSalaryByHour(0.5d);
    internshipOffer2.setSession("com.sap.ose.projetose.modeles.InternOffer");
    internshipOffer2.setStartDate(LocalDate.of(1970, 1, 1));
    internshipOffer2.setState(State.PENDING);
    internshipOffer2.setStatus("com.sap.ose.projetose.modeles.InternOffer");
    internshipOffer2.setTitle("Mr");

    Etudiant etudiant4 = new Etudiant();
    etudiant4.setActiveCv(new File());
    etudiant4.setCv(new ArrayList<>());
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

    InternshipCandidates internshipCandidates4 = new InternshipCandidates();
    internshipCandidates4.setEtudiant(new Etudiant());
    internshipCandidates4.setFiles(new ArrayList<>());
    internshipCandidates4.setId(2L);
    internshipCandidates4.setInternOffer(new InternOffer());
    internshipCandidates4.setState(State.PENDING);
    internshipCandidates4.setStudentCv(new File());

    File activeCv2 = new File();
    activeCv2.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    activeCv2.setEtudiant(etudiant4);
    activeCv2.setFileName("File Name");
    activeCv2.setId(2L);
    activeCv2.setInternshipCandidates(internshipCandidates4);
    activeCv2.setIsAccepted(State.PENDING);

    Programme programme6 = new Programme();
    programme6.setDescription("Description");
    programme6.setId(2L);
    programme6.setNom("com.sap.ose.projetose.modeles.Programme");

    Etudiant student2 = new Etudiant();
    student2.setActiveCv(activeCv2);
    student2.setCv(new ArrayList<>());
    student2.setEmail("john.smith@example.org");
    student2.setId(2L);
    student2.setInternshipsCandidate(new ArrayList<>());
    student2.setMatricule("com.sap.ose.projetose.modeles.Etudiant");
    student2.setNom("com.sap.ose.projetose.modeles.Etudiant");
    student2.setPassword("Password");
    student2.setPhone("8605550118");
    student2.setPrenom("com.sap.ose.projetose.modeles.Etudiant");
    student2.setProgramme(programme6);
    student2.setRole(Role.student);

    Interview interview2 = new Interview();
    interview2.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    interview2.setDescription("Description");
    interview2.setId(2L);
    interview2.setInternshipOffer(internshipOffer2);
    interview2.setState(State.PENDING);
    interview2.setStudent(student2);

    ArrayList<Interview> content = new ArrayList<>();
    content.add(interview2);
    content.add(interview);
    PageImpl<Interview> pageImpl = new PageImpl<>(content);
    when(interviewRepository.findAllByStudentId(anyLong(), Mockito.<Pageable>any(), Mockito.<String>any()))
            .thenReturn(pageImpl);
    Page<InterviewDTO> actualInterviewsByStudentId = interviewService.getInterviewsByStudentId(1L, 1, 3, "Sort Field",
            "Sort Direction", "Season");
    verify(interviewRepository).findAllByStudentId(anyLong(), Mockito.<Pageable>any(), Mockito.<String>any());
    assertEquals(2, actualInterviewsByStudentId.toList().size());
  }

  /**
   * Method under test: {@link InterviewService#getInterviewsByEmployerId(long, int, int, String, String, String)}
   */
  @Test
  void testGetInterviewsByEmployerId() {
    when(interviewRepository.findAllByEmployerId(anyLong(), Mockito.<Pageable>any(), Mockito.<String>any()))
            .thenReturn(new PageImpl<>(new ArrayList<>()));
    Page<InterviewDTO> actualInterviewsByEmployerId = interviewService.getInterviewsByEmployerId(1L, 1, 3, "Sort Field",
            "Sort Direction", "Season");
    verify(interviewRepository).findAllByEmployerId(anyLong(), Mockito.<Pageable>any(), Mockito.<String>any());
    assertTrue(actualInterviewsByEmployerId.toList().isEmpty());
  }

  /**
   * Method under test: {@link InterviewService#getInterviewsByEmployerId(long, int, int, String, String, String)}
   */
  @Test
  void testGetInterviewsByEmployerId2() {
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

    File file = new File();
    file.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    file.setEtudiant(etudiant);
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
    internOffer.setSession("Session");
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

    Programme programme2 = new Programme();
    programme2.setDescription("The characteristics of someone or something");
    programme2.setId(1L);
    programme2.setNom("Nom");

    InternOffer internshipOffer = new InternOffer();
    internshipOffer.setDescription("The characteristics of someone or something");
    internshipOffer.setEmployeur(employeur);
    internshipOffer.setEndDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setFile(file);
    internshipOffer.setId(1L);
    internshipOffer.setInternshipCandidates(new ArrayList<>());
    internshipOffer.setLocation("Location");
    internshipOffer.setOfferReviewRequest(offerReviewRequest);
    internshipOffer.setProgramme(programme2);
    internshipOffer.setSalaryByHour(10.0d);
    internshipOffer.setSession("Session");
    internshipOffer.setStartDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setState(State.ACCEPTED);
    internshipOffer.setStatus("Status");
    internshipOffer.setTitle("Dr");

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

    InternshipCandidates internshipCandidates2 = new InternshipCandidates();
    internshipCandidates2.setEtudiant(new Etudiant());
    internshipCandidates2.setFiles(new ArrayList<>());
    internshipCandidates2.setId(1L);
    internshipCandidates2.setInternOffer(new InternOffer());
    internshipCandidates2.setState(State.ACCEPTED);
    internshipCandidates2.setStudentCv(new File());

    File activeCv = new File();
    activeCv.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    activeCv.setEtudiant(etudiant2);
    activeCv.setFileName("foo.txt");
    activeCv.setId(1L);
    activeCv.setInternshipCandidates(internshipCandidates2);
    activeCv.setIsAccepted(State.ACCEPTED);

    Programme programme3 = new Programme();
    programme3.setDescription("The characteristics of someone or something");
    programme3.setId(1L);
    programme3.setNom("Nom");

    Etudiant student = new Etudiant();
    student.setActiveCv(activeCv);
    student.setCv(new ArrayList<>());
    student.setEmail("jane.doe@example.org");
    student.setId(1L);
    student.setInternshipsCandidate(new ArrayList<>());
    student.setMatricule("Matricule");
    student.setNom("Nom");
    student.setPassword("iloveyou");
    student.setPhone("6625550144");
    student.setPrenom("Prenom");
    student.setProgramme(programme3);
    student.setRole(Role.employer);

    Interview interview = new Interview();
    interview.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    interview.setDescription("The characteristics of someone or something");
    interview.setId(1L);
    interview.setInternshipOffer(internshipOffer);
    interview.setState(State.ACCEPTED);
    interview.setStudent(student);

    ArrayList<Interview> content = new ArrayList<>();
    content.add(interview);
    PageImpl<Interview> pageImpl = new PageImpl<>(content);
    when(interviewRepository.findAllByEmployerId(anyLong(), Mockito.<Pageable>any(), Mockito.<String>any()))
            .thenReturn(pageImpl);
    Page<InterviewDTO> actualInterviewsByEmployerId = interviewService.getInterviewsByEmployerId(1L, 1, 3, "Sort Field",
            "Sort Direction", "Season");
    verify(interviewRepository).findAllByEmployerId(anyLong(), Mockito.<Pageable>any(), Mockito.<String>any());
    assertEquals(1, actualInterviewsByEmployerId.toList().size());
  }

  /**
   * Method under test: {@link InterviewService#getInterviewsByEmployerId(long, int, int, String, String, String)}
   */
  @Test
  void testGetInterviewsByEmployerId3() {
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

    File file = new File();
    file.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    file.setEtudiant(etudiant);
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
    internOffer.setSession("Session");
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

    Programme programme2 = new Programme();
    programme2.setDescription("The characteristics of someone or something");
    programme2.setId(1L);
    programme2.setNom("Nom");

    InternOffer internshipOffer = new InternOffer();
    internshipOffer.setDescription("The characteristics of someone or something");
    internshipOffer.setEmployeur(employeur);
    internshipOffer.setEndDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setFile(file);
    internshipOffer.setId(1L);
    internshipOffer.setInternshipCandidates(new ArrayList<>());
    internshipOffer.setLocation("Location");
    internshipOffer.setOfferReviewRequest(offerReviewRequest);
    internshipOffer.setProgramme(programme2);
    internshipOffer.setSalaryByHour(10.0d);
    internshipOffer.setSession("Session");
    internshipOffer.setStartDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setState(State.ACCEPTED);
    internshipOffer.setStatus("Status");
    internshipOffer.setTitle("Dr");

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

    InternshipCandidates internshipCandidates2 = new InternshipCandidates();
    internshipCandidates2.setEtudiant(new Etudiant());
    internshipCandidates2.setFiles(new ArrayList<>());
    internshipCandidates2.setId(1L);
    internshipCandidates2.setInternOffer(new InternOffer());
    internshipCandidates2.setState(State.ACCEPTED);
    internshipCandidates2.setStudentCv(new File());

    File activeCv = new File();
    activeCv.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    activeCv.setEtudiant(etudiant2);
    activeCv.setFileName("foo.txt");
    activeCv.setId(1L);
    activeCv.setInternshipCandidates(internshipCandidates2);
    activeCv.setIsAccepted(State.ACCEPTED);

    Programme programme3 = new Programme();
    programme3.setDescription("The characteristics of someone or something");
    programme3.setId(1L);
    programme3.setNom("Nom");

    Etudiant student = new Etudiant();
    student.setActiveCv(activeCv);
    student.setCv(new ArrayList<>());
    student.setEmail("jane.doe@example.org");
    student.setId(1L);
    student.setInternshipsCandidate(new ArrayList<>());
    student.setMatricule("Matricule");
    student.setNom("Nom");
    student.setPassword("iloveyou");
    student.setPhone("6625550144");
    student.setPrenom("Prenom");
    student.setProgramme(programme3);
    student.setRole(Role.employer);

    Interview interview = new Interview();
    interview.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    interview.setDescription("The characteristics of someone or something");
    interview.setId(1L);
    interview.setInternshipOffer(internshipOffer);
    interview.setState(State.ACCEPTED);
    interview.setStudent(student);

    Programme programme4 = new Programme();
    programme4.setDescription("Description");
    programme4.setId(2L);
    programme4.setNom("com.sap.ose.projetose.modeles.Programme");

    Employeur employeur2 = new Employeur();
    employeur2.setEmail("john.smith@example.org");
    employeur2.setEntreprise("com.sap.ose.projetose.modeles.Employeur");
    employeur2.setId(2L);
    employeur2.setInternOffers(new ArrayList<>());
    employeur2.setNom("com.sap.ose.projetose.modeles.Employeur");
    employeur2.setPassword("Password");
    employeur2.setPhone("8605550118");
    employeur2.setPrenom("com.sap.ose.projetose.modeles.Employeur");
    employeur2.setProgramme(programme4);
    employeur2.setRole(Role.student);

    Etudiant etudiant3 = new Etudiant();
    etudiant3.setActiveCv(new File());
    etudiant3.setCv(new ArrayList<>());
    etudiant3.setEmail("john.smith@example.org");
    etudiant3.setId(2L);
    etudiant3.setInternshipsCandidate(new ArrayList<>());
    etudiant3.setMatricule("com.sap.ose.projetose.modeles.Etudiant");
    etudiant3.setNom("com.sap.ose.projetose.modeles.Etudiant");
    etudiant3.setPassword("Password");
    etudiant3.setPhone("8605550118");
    etudiant3.setPrenom("com.sap.ose.projetose.modeles.Etudiant");
    etudiant3.setProgramme(new Programme());
    etudiant3.setRole(Role.student);

    InternshipCandidates internshipCandidates3 = new InternshipCandidates();
    internshipCandidates3.setEtudiant(new Etudiant());
    internshipCandidates3.setFiles(new ArrayList<>());
    internshipCandidates3.setId(2L);
    internshipCandidates3.setInternOffer(new InternOffer());
    internshipCandidates3.setState(State.PENDING);
    internshipCandidates3.setStudentCv(new File());

    File file2 = new File();
    file2.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    file2.setEtudiant(etudiant3);
    file2.setFileName("File Name");
    file2.setId(2L);
    file2.setInternshipCandidates(internshipCandidates3);
    file2.setIsAccepted(State.PENDING);

    InternOffer internOffer2 = new InternOffer();
    internOffer2.setDescription("Description");
    internOffer2.setEmployeur(new Employeur());
    internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
    internOffer2.setFile(new File());
    internOffer2.setId(2L);
    internOffer2.setInternshipCandidates(new ArrayList<>());
    internOffer2.setLocation("com.sap.ose.projetose.modeles.InternOffer");
    internOffer2.setOfferReviewRequest(new OfferReviewRequest());
    internOffer2.setProgramme(new Programme());
    internOffer2.setSalaryByHour(0.5d);
    internOffer2.setSession("com.sap.ose.projetose.modeles.InternOffer");
    internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
    internOffer2.setState(State.PENDING);
    internOffer2.setStatus("com.sap.ose.projetose.modeles.InternOffer");
    internOffer2.setTitle("Mr");

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
    offerReviewRequest2.setInternOffer(internOffer2);
    offerReviewRequest2.setInternshipmanager(internshipmanager2);

    Programme programme5 = new Programme();
    programme5.setDescription("Description");
    programme5.setId(2L);
    programme5.setNom("com.sap.ose.projetose.modeles.Programme");

    InternOffer internshipOffer2 = new InternOffer();
    internshipOffer2.setDescription("Description");
    internshipOffer2.setEmployeur(employeur2);
    internshipOffer2.setEndDate(LocalDate.of(1970, 1, 1));
    internshipOffer2.setFile(file2);
    internshipOffer2.setId(2L);
    internshipOffer2.setInternshipCandidates(new ArrayList<>());
    internshipOffer2.setLocation("com.sap.ose.projetose.modeles.InternOffer");
    internshipOffer2.setOfferReviewRequest(offerReviewRequest2);
    internshipOffer2.setProgramme(programme5);
    internshipOffer2.setSalaryByHour(0.5d);
    internshipOffer2.setSession("com.sap.ose.projetose.modeles.InternOffer");
    internshipOffer2.setStartDate(LocalDate.of(1970, 1, 1));
    internshipOffer2.setState(State.PENDING);
    internshipOffer2.setStatus("com.sap.ose.projetose.modeles.InternOffer");
    internshipOffer2.setTitle("Mr");

    Etudiant etudiant4 = new Etudiant();
    etudiant4.setActiveCv(new File());
    etudiant4.setCv(new ArrayList<>());
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

    InternshipCandidates internshipCandidates4 = new InternshipCandidates();
    internshipCandidates4.setEtudiant(new Etudiant());
    internshipCandidates4.setFiles(new ArrayList<>());
    internshipCandidates4.setId(2L);
    internshipCandidates4.setInternOffer(new InternOffer());
    internshipCandidates4.setState(State.PENDING);
    internshipCandidates4.setStudentCv(new File());

    File activeCv2 = new File();
    activeCv2.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    activeCv2.setEtudiant(etudiant4);
    activeCv2.setFileName("File Name");
    activeCv2.setId(2L);
    activeCv2.setInternshipCandidates(internshipCandidates4);
    activeCv2.setIsAccepted(State.PENDING);

    Programme programme6 = new Programme();
    programme6.setDescription("Description");
    programme6.setId(2L);
    programme6.setNom("com.sap.ose.projetose.modeles.Programme");

    Etudiant student2 = new Etudiant();
    student2.setActiveCv(activeCv2);
    student2.setCv(new ArrayList<>());
    student2.setEmail("john.smith@example.org");
    student2.setId(2L);
    student2.setInternshipsCandidate(new ArrayList<>());
    student2.setMatricule("com.sap.ose.projetose.modeles.Etudiant");
    student2.setNom("com.sap.ose.projetose.modeles.Etudiant");
    student2.setPassword("Password");
    student2.setPhone("8605550118");
    student2.setPrenom("com.sap.ose.projetose.modeles.Etudiant");
    student2.setProgramme(programme6);
    student2.setRole(Role.student);

    Interview interview2 = new Interview();
    interview2.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    interview2.setDescription("Description");
    interview2.setId(2L);
    interview2.setInternshipOffer(internshipOffer2);
    interview2.setState(State.PENDING);
    interview2.setStudent(student2);

    ArrayList<Interview> content = new ArrayList<>();
    content.add(interview2);
    content.add(interview);
    PageImpl<Interview> pageImpl = new PageImpl<>(content);
    when(interviewRepository.findAllByEmployerId(anyLong(), Mockito.<Pageable>any(), Mockito.<String>any()))
            .thenReturn(pageImpl);
    Page<InterviewDTO> actualInterviewsByEmployerId = interviewService.getInterviewsByEmployerId(1L, 1, 3, "Sort Field",
            "Sort Direction", "Season");
    verify(interviewRepository).findAllByEmployerId(anyLong(), Mockito.<Pageable>any(), Mockito.<String>any());
    assertEquals(2, actualInterviewsByEmployerId.toList().size());
  }

  /**
   * Method under test: {@link InterviewService#getInterviewsCountByStudentId(long)}
   */
  @Test
  void testGetInterviewsCountByStudentId() {
    Optional<Long> actualInterviewsCountByStudentId = interviewService.getInterviewsCountByStudentId(1L);
    assertEquals(1L, actualInterviewsCountByStudentId.get());
    assertTrue(actualInterviewsCountByStudentId.isPresent());
  }

  /**
   * Method under test: {@link InterviewService#studentAcceptsInterviewByStudentId(long, long)}
   */
  @Test
  void testStudentAcceptsInterviewByStudentId() throws UnsupportedEncodingException {
    //   Diffblue Cover was unable to write a Spring test,
    //   so wrote a non-Spring test instead.
    //   Diffblue AI was unable to find a test

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

    File activeCv3 = new File();
    activeCv3.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv3.setEtudiant(etudiant2);
    activeCv3.setFileName("foo.txt");
    activeCv3.setId(1L);
    activeCv3.setInternshipCandidates(internshipCandidates3);
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
    Interview interview = mock(Interview.class);
    doNothing().when(interview).setState(Mockito.<State>any());
    doNothing().when(interview).setStudent(Mockito.<Etudiant>any());
    when(interview.getStudent()).thenReturn(etudiant5);
    Optional<Interview> ofResult = Optional.of(interview);

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

    Etudiant etudiant6 = new Etudiant();
    etudiant6.setActiveCv(activeCv4);
    etudiant6.setCv(new ArrayList<>());
    etudiant6.setEmail("jane.doe@example.org");
    etudiant6.setId(1L);
    etudiant6.setInternshipsCandidate(new ArrayList<>());
    etudiant6.setMatricule("Matricule");
    etudiant6.setNom("Nom");
    etudiant6.setPassword("iloveyou");
    etudiant6.setPhone("6625550144");
    etudiant6.setPrenom("Prenom");
    etudiant6.setProgramme(programme6);
    etudiant6.setRole(Role.employer);

    Etudiant etudiant7 = new Etudiant();
    etudiant7.setActiveCv(new File());
    etudiant7.setCv(new ArrayList<>());
    etudiant7.setEmail("jane.doe@example.org");
    etudiant7.setId(1L);
    etudiant7.setInternshipsCandidate(new ArrayList<>());
    etudiant7.setMatricule("Matricule");
    etudiant7.setNom("Nom");
    etudiant7.setPassword("iloveyou");
    etudiant7.setPhone("6625550144");
    etudiant7.setPrenom("Prenom");
    etudiant7.setProgramme(new Programme());
    etudiant7.setRole(Role.employer);

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
    internshipCandidates4.setEtudiant(etudiant7);
    internshipCandidates4.setFiles(new ArrayList<>());
    internshipCandidates4.setId(1L);
    internshipCandidates4.setInternOffer(internOffer2);
    internshipCandidates4.setState(State.ACCEPTED);
    internshipCandidates4.setStudentCv(studentCv2);

    File file2 = new File();
    file2.setContent("AXAXAXAX".getBytes("UTF-8"));
    file2.setEtudiant(etudiant6);
    file2.setFileName("foo.txt");
    file2.setId(1L);
    file2.setInternshipCandidates(internshipCandidates4);
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

    InternOffer internOffer3 = new InternOffer();
    internOffer3.setDescription("The characteristics of someone or something");
    internOffer3.setEmployeur(employeur3);
    internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
    internOffer3.setFile(file3);
    internOffer3.setId(1L);
    internOffer3.setInternshipCandidates(new ArrayList<>());
    internOffer3.setLocation("Location");
    internOffer3.setOfferReviewRequest(offerReviewRequest2);
    internOffer3.setProgramme(programme7);
    internOffer3.setSalaryByHour(10.0d);
    internOffer3.setSession("Session");
    internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
    internOffer3.setState(State.ACCEPTED);
    internOffer3.setStatus("Status");
    internOffer3.setTitle("Dr");

    Programme programme8 = new Programme();
    programme8.setDescription("The characteristics of someone or something");
    programme8.setId(1L);
    programme8.setNom("Nom");

    Internshipmanager internshipmanager = new Internshipmanager();
    internshipmanager.setEmail("jane.doe@example.org");
    internshipmanager.setId(1L);
    internshipmanager.setNom("Nom");
    internshipmanager.setPassword("iloveyou");
    internshipmanager.setPhone("6625550144");
    internshipmanager.setPrenom("Prenom");
    internshipmanager.setProgramme(programme8);
    internshipmanager.setRole(Role.employer);

    OfferReviewRequest offerReviewRequest3 = new OfferReviewRequest();
    offerReviewRequest3.setComment("Comment");
    offerReviewRequest3.setId(1L);
    offerReviewRequest3.setInternOffer(internOffer3);
    offerReviewRequest3.setInternshipmanager(internshipmanager);

    Programme programme9 = new Programme();
    programme9.setDescription("The characteristics of someone or something");
    programme9.setId(1L);
    programme9.setNom("Nom");

    InternOffer internshipOffer = new InternOffer();
    internshipOffer.setDescription("The characteristics of someone or something");
    internshipOffer.setEmployeur(employeur2);
    internshipOffer.setEndDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setFile(file2);
    internshipOffer.setId(1L);
    internshipOffer.setInternshipCandidates(new ArrayList<>());
    internshipOffer.setLocation("Location");
    internshipOffer.setOfferReviewRequest(offerReviewRequest3);
    internshipOffer.setProgramme(programme9);
    internshipOffer.setSalaryByHour(10.0d);
    internshipOffer.setSession("Session");
    internshipOffer.setStartDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setState(State.ACCEPTED);
    internshipOffer.setStatus("Status");
    internshipOffer.setTitle("Dr");

    File activeCv5 = new File();
    activeCv5.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv5.setEtudiant(new Etudiant());
    activeCv5.setFileName("foo.txt");
    activeCv5.setId(1L);
    activeCv5.setInternshipCandidates(new InternshipCandidates());
    activeCv5.setIsAccepted(State.ACCEPTED);

    Programme programme10 = new Programme();
    programme10.setDescription("The characteristics of someone or something");
    programme10.setId(1L);
    programme10.setNom("Nom");

    Etudiant etudiant8 = new Etudiant();
    etudiant8.setActiveCv(activeCv5);
    etudiant8.setCv(new ArrayList<>());
    etudiant8.setEmail("jane.doe@example.org");
    etudiant8.setId(1L);
    etudiant8.setInternshipsCandidate(new ArrayList<>());
    etudiant8.setMatricule("Matricule");
    etudiant8.setNom("Nom");
    etudiant8.setPassword("iloveyou");
    etudiant8.setPhone("6625550144");
    etudiant8.setPrenom("Prenom");
    etudiant8.setProgramme(programme10);
    etudiant8.setRole(Role.employer);

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

    File studentCv3 = new File();
    studentCv3.setContent("AXAXAXAX".getBytes("UTF-8"));
    studentCv3.setEtudiant(new Etudiant());
    studentCv3.setFileName("foo.txt");
    studentCv3.setId(1L);
    studentCv3.setInternshipCandidates(new InternshipCandidates());
    studentCv3.setIsAccepted(State.ACCEPTED);

    InternshipCandidates internshipCandidates5 = new InternshipCandidates();
    internshipCandidates5.setEtudiant(etudiant9);
    internshipCandidates5.setFiles(new ArrayList<>());
    internshipCandidates5.setId(1L);
    internshipCandidates5.setInternOffer(internOffer4);
    internshipCandidates5.setState(State.ACCEPTED);
    internshipCandidates5.setStudentCv(studentCv3);

    File activeCv6 = new File();
    activeCv6.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv6.setEtudiant(etudiant8);
    activeCv6.setFileName("foo.txt");
    activeCv6.setId(1L);
    activeCv6.setInternshipCandidates(internshipCandidates5);
    activeCv6.setIsAccepted(State.ACCEPTED);

    Programme programme11 = new Programme();
    programme11.setDescription("The characteristics of someone or something");
    programme11.setId(1L);
    programme11.setNom("Nom");

    Etudiant student = new Etudiant();
    student.setActiveCv(activeCv6);
    student.setCv(new ArrayList<>());
    student.setEmail("jane.doe@example.org");
    student.setId(1L);
    student.setInternshipsCandidate(new ArrayList<>());
    student.setMatricule("Matricule");
    student.setNom("Nom");
    student.setPassword("iloveyou");
    student.setPhone("6625550144");
    student.setPrenom("Prenom");
    student.setProgramme(programme11);
    student.setRole(Role.employer);

    Interview interview2 = new Interview();
    interview2.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    interview2.setDescription("The characteristics of someone or something");
    interview2.setId(1L);
    interview2.setInternshipOffer(internshipOffer);
    interview2.setState(State.ACCEPTED);
    interview2.setStudent(student);
    InterviewRepository interviewRepository = mock(InterviewRepository.class);
    when(interviewRepository.save(Mockito.<Interview>any())).thenReturn(interview2);
    when(interviewRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    EtudiantRepository etudiantRepository = mock(EtudiantRepository.class);
    Optional<Etudiant> ofResult2 = Optional.of(mock(Etudiant.class));
    when(etudiantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
    InternOfferRepository internOfferRepository = mock(InternOfferRepository.class);
    EtudiantRepository etudiantRepository2 = mock(EtudiantRepository.class);
    ProgrammeService programmeService = new ProgrammeService(mock(ProgrammeRepository.class));
    FileEntityRepository fileEntityRepository = mock(FileEntityRepository.class);
    NotificationRepository notificationRepository = mock(NotificationRepository.class);
    EtudiantService etudiantService = new EtudiantService(etudiantRepository2, programmeService, fileEntityRepository,
            new NotificationService(notificationRepository, new UtilisateurService(mock(EmployeurRepository.class),
                    mock(EtudiantRepository.class), mock(InternshipmanagerRepository.class)), null),
            mock(InternshipCandidatesRepository.class));

    EmployeurRepository employeurRepository = mock(EmployeurRepository.class);
    Optional<Boolean> actualStudentAcceptsInterviewByStudentIdResult = (new InterviewService(internOfferRepository,
            interviewRepository, etudiantService,
            new EmployeurService(employeurRepository, new ProgrammeService(mock(ProgrammeRepository.class))),
            mock(EmployeurRepository.class), etudiantRepository)).studentAcceptsInterviewByStudentId(1L, 1L);
    verify(interview).getStudent();
    verify(interview).setState(Mockito.<State>any());
    verify(interview).setStudent(Mockito.<Etudiant>any());
    verify(etudiantRepository).findById(Mockito.<Long>any());
    verify(interviewRepository).findById(Mockito.<Long>any());
    verify(interviewRepository).save(Mockito.<Interview>any());
    assertTrue(actualStudentAcceptsInterviewByStudentIdResult.get());
    assertTrue(actualStudentAcceptsInterviewByStudentIdResult.isPresent());
  }

  /**
   * Method under test: {@link InterviewService#studentDeclineInterviewByStudentId(long, long)}
   */
  @Test
  void testStudentDeclineInterviewByStudentId() throws UnsupportedEncodingException {
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

    File file = new File();
    file.setContent("AXAXAXAX".getBytes("UTF-8"));
    file.setEtudiant(etudiant);
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
    internOffer.setSession("Session");
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

    Programme programme2 = new Programme();
    programme2.setDescription("The characteristics of someone or something");
    programme2.setId(1L);
    programme2.setNom("Nom");

    InternOffer internshipOffer = new InternOffer();
    internshipOffer.setDescription("The characteristics of someone or something");
    internshipOffer.setEmployeur(employeur);
    internshipOffer.setEndDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setFile(file);
    internshipOffer.setId(1L);
    internshipOffer.setInternshipCandidates(new ArrayList<>());
    internshipOffer.setLocation("Location");
    internshipOffer.setOfferReviewRequest(offerReviewRequest);
    internshipOffer.setProgramme(programme2);
    internshipOffer.setSalaryByHour(10.0d);
    internshipOffer.setSession("Session");
    internshipOffer.setStartDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setState(State.ACCEPTED);
    internshipOffer.setStatus("Status");
    internshipOffer.setTitle("Dr");

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

    InternshipCandidates internshipCandidates2 = new InternshipCandidates();
    internshipCandidates2.setEtudiant(new Etudiant());
    internshipCandidates2.setFiles(new ArrayList<>());
    internshipCandidates2.setId(1L);
    internshipCandidates2.setInternOffer(new InternOffer());
    internshipCandidates2.setState(State.ACCEPTED);
    internshipCandidates2.setStudentCv(new File());

    File activeCv = new File();
    activeCv.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv.setEtudiant(etudiant2);
    activeCv.setFileName("foo.txt");
    activeCv.setId(1L);
    activeCv.setInternshipCandidates(internshipCandidates2);
    activeCv.setIsAccepted(State.ACCEPTED);

    Programme programme3 = new Programme();
    programme3.setDescription("The characteristics of someone or something");
    programme3.setId(1L);
    programme3.setNom("Nom");

    Etudiant student = new Etudiant();
    student.setActiveCv(activeCv);
    student.setCv(new ArrayList<>());
    student.setEmail("jane.doe@example.org");
    student.setId(1L);
    student.setInternshipsCandidate(new ArrayList<>());
    student.setMatricule("Matricule");
    student.setNom("Nom");
    student.setPassword("iloveyou");
    student.setPhone("6625550144");
    student.setPrenom("Prenom");
    student.setProgramme(programme3);
    student.setRole(Role.employer);

    Interview interview = new Interview();
    interview.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    interview.setDescription("The characteristics of someone or something");
    interview.setId(1L);
    interview.setInternshipOffer(internshipOffer);
    interview.setState(State.ACCEPTED);
    interview.setStudent(student);
    Optional<Interview> ofResult = Optional.of(interview);

    Programme programme4 = new Programme();
    programme4.setDescription("The characteristics of someone or something");
    programme4.setId(1L);
    programme4.setNom("Nom");

    Employeur employeur2 = new Employeur();
    employeur2.setEmail("jane.doe@example.org");
    employeur2.setEntreprise("Entreprise");
    employeur2.setId(1L);
    employeur2.setInternOffers(new ArrayList<>());
    employeur2.setNom("Nom");
    employeur2.setPassword("iloveyou");
    employeur2.setPhone("6625550144");
    employeur2.setPrenom("Prenom");
    employeur2.setProgramme(programme4);
    employeur2.setRole(Role.employer);

    File activeCv2 = new File();
    activeCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv2.setEtudiant(new Etudiant());
    activeCv2.setFileName("foo.txt");
    activeCv2.setId(1L);
    activeCv2.setInternshipCandidates(new InternshipCandidates());
    activeCv2.setIsAccepted(State.ACCEPTED);

    Programme programme5 = new Programme();
    programme5.setDescription("The characteristics of someone or something");
    programme5.setId(1L);
    programme5.setNom("Nom");

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
    etudiant3.setProgramme(programme5);
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

    File studentCv = new File();
    studentCv.setContent("AXAXAXAX".getBytes("UTF-8"));
    studentCv.setEtudiant(new Etudiant());
    studentCv.setFileName("foo.txt");
    studentCv.setId(1L);
    studentCv.setInternshipCandidates(new InternshipCandidates());
    studentCv.setIsAccepted(State.ACCEPTED);

    InternshipCandidates internshipCandidates3 = new InternshipCandidates();
    internshipCandidates3.setEtudiant(etudiant4);
    internshipCandidates3.setFiles(new ArrayList<>());
    internshipCandidates3.setId(1L);
    internshipCandidates3.setInternOffer(internOffer2);
    internshipCandidates3.setState(State.ACCEPTED);
    internshipCandidates3.setStudentCv(studentCv);

    File file2 = new File();
    file2.setContent("AXAXAXAX".getBytes("UTF-8"));
    file2.setEtudiant(etudiant3);
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

    Programme programme6 = new Programme();
    programme6.setDescription("The characteristics of someone or something");
    programme6.setId(1L);
    programme6.setNom("Nom");

    InternOffer internOffer3 = new InternOffer();
    internOffer3.setDescription("The characteristics of someone or something");
    internOffer3.setEmployeur(employeur3);
    internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
    internOffer3.setFile(file3);
    internOffer3.setId(1L);
    internOffer3.setInternshipCandidates(new ArrayList<>());
    internOffer3.setLocation("Location");
    internOffer3.setOfferReviewRequest(offerReviewRequest2);
    internOffer3.setProgramme(programme6);
    internOffer3.setSalaryByHour(10.0d);
    internOffer3.setSession("Session");
    internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
    internOffer3.setState(State.ACCEPTED);
    internOffer3.setStatus("Status");
    internOffer3.setTitle("Dr");

    Programme programme7 = new Programme();
    programme7.setDescription("The characteristics of someone or something");
    programme7.setId(1L);
    programme7.setNom("Nom");

    Internshipmanager internshipmanager2 = new Internshipmanager();
    internshipmanager2.setEmail("jane.doe@example.org");
    internshipmanager2.setId(1L);
    internshipmanager2.setNom("Nom");
    internshipmanager2.setPassword("iloveyou");
    internshipmanager2.setPhone("6625550144");
    internshipmanager2.setPrenom("Prenom");
    internshipmanager2.setProgramme(programme7);
    internshipmanager2.setRole(Role.employer);

    OfferReviewRequest offerReviewRequest3 = new OfferReviewRequest();
    offerReviewRequest3.setComment("Comment");
    offerReviewRequest3.setId(1L);
    offerReviewRequest3.setInternOffer(internOffer3);
    offerReviewRequest3.setInternshipmanager(internshipmanager2);

    Programme programme8 = new Programme();
    programme8.setDescription("The characteristics of someone or something");
    programme8.setId(1L);
    programme8.setNom("Nom");

    InternOffer internshipOffer2 = new InternOffer();
    internshipOffer2.setDescription("The characteristics of someone or something");
    internshipOffer2.setEmployeur(employeur2);
    internshipOffer2.setEndDate(LocalDate.of(1970, 1, 1));
    internshipOffer2.setFile(file2);
    internshipOffer2.setId(1L);
    internshipOffer2.setInternshipCandidates(new ArrayList<>());
    internshipOffer2.setLocation("Location");
    internshipOffer2.setOfferReviewRequest(offerReviewRequest3);
    internshipOffer2.setProgramme(programme8);
    internshipOffer2.setSalaryByHour(10.0d);
    internshipOffer2.setSession("Session");
    internshipOffer2.setStartDate(LocalDate.of(1970, 1, 1));
    internshipOffer2.setState(State.ACCEPTED);
    internshipOffer2.setStatus("Status");
    internshipOffer2.setTitle("Dr");

    File activeCv3 = new File();
    activeCv3.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv3.setEtudiant(new Etudiant());
    activeCv3.setFileName("foo.txt");
    activeCv3.setId(1L);
    activeCv3.setInternshipCandidates(new InternshipCandidates());
    activeCv3.setIsAccepted(State.ACCEPTED);

    Programme programme9 = new Programme();
    programme9.setDescription("The characteristics of someone or something");
    programme9.setId(1L);
    programme9.setNom("Nom");

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
    etudiant5.setProgramme(programme9);
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
    internshipCandidates4.setEtudiant(etudiant6);
    internshipCandidates4.setFiles(new ArrayList<>());
    internshipCandidates4.setId(1L);
    internshipCandidates4.setInternOffer(internOffer4);
    internshipCandidates4.setState(State.ACCEPTED);
    internshipCandidates4.setStudentCv(studentCv2);

    File activeCv4 = new File();
    activeCv4.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv4.setEtudiant(etudiant5);
    activeCv4.setFileName("foo.txt");
    activeCv4.setId(1L);
    activeCv4.setInternshipCandidates(internshipCandidates4);
    activeCv4.setIsAccepted(State.ACCEPTED);

    Programme programme10 = new Programme();
    programme10.setDescription("The characteristics of someone or something");
    programme10.setId(1L);
    programme10.setNom("Nom");

    Etudiant student2 = new Etudiant();
    student2.setActiveCv(activeCv4);
    student2.setCv(new ArrayList<>());
    student2.setEmail("jane.doe@example.org");
    student2.setId(1L);
    student2.setInternshipsCandidate(new ArrayList<>());
    student2.setMatricule("Matricule");
    student2.setNom("Nom");
    student2.setPassword("iloveyou");
    student2.setPhone("6625550144");
    student2.setPrenom("Prenom");
    student2.setProgramme(programme10);
    student2.setRole(Role.employer);

    Interview interview2 = new Interview();
    interview2.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    interview2.setDescription("The characteristics of someone or something");
    interview2.setId(1L);
    interview2.setInternshipOffer(internshipOffer2);
    interview2.setState(State.ACCEPTED);
    interview2.setStudent(student2);
    when(interviewRepository.save(Mockito.<Interview>any())).thenReturn(interview2);
    when(interviewRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    Optional<Boolean> actualStudentDeclineInterviewByStudentIdResult = interviewService
            .studentDeclineInterviewByStudentId(1L, 1L);
    verify(interviewRepository).findById(Mockito.<Long>any());
    verify(interviewRepository).save(Mockito.<Interview>any());
    assertTrue(actualStudentDeclineInterviewByStudentIdResult.get());
    assertTrue(actualStudentDeclineInterviewByStudentIdResult.isPresent());
  }

  /**
   * Method under test: {@link InterviewService#updateInterview(InterviewRequestInDto)}
   */
  @Test
  void testUpdateInterview() throws UnsupportedEncodingException {
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

    File file = new File();
    file.setContent("AXAXAXAX".getBytes("UTF-8"));
    file.setEtudiant(etudiant);
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
    internOffer.setSession("Session");
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

    Programme programme2 = new Programme();
    programme2.setDescription("The characteristics of someone or something");
    programme2.setId(1L);
    programme2.setNom("Nom");

    InternOffer internshipOffer = new InternOffer();
    internshipOffer.setDescription("The characteristics of someone or something");
    internshipOffer.setEmployeur(employeur);
    internshipOffer.setEndDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setFile(file);
    internshipOffer.setId(1L);
    internshipOffer.setInternshipCandidates(new ArrayList<>());
    internshipOffer.setLocation("Location");
    internshipOffer.setOfferReviewRequest(offerReviewRequest);
    internshipOffer.setProgramme(programme2);
    internshipOffer.setSalaryByHour(10.0d);
    internshipOffer.setSession("Session");
    internshipOffer.setStartDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setState(State.ACCEPTED);
    internshipOffer.setStatus("Status");
    internshipOffer.setTitle("Dr");

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

    InternshipCandidates internshipCandidates2 = new InternshipCandidates();
    internshipCandidates2.setEtudiant(new Etudiant());
    internshipCandidates2.setFiles(new ArrayList<>());
    internshipCandidates2.setId(1L);
    internshipCandidates2.setInternOffer(new InternOffer());
    internshipCandidates2.setState(State.ACCEPTED);
    internshipCandidates2.setStudentCv(new File());

    File activeCv = new File();
    activeCv.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv.setEtudiant(etudiant2);
    activeCv.setFileName("foo.txt");
    activeCv.setId(1L);
    activeCv.setInternshipCandidates(internshipCandidates2);
    activeCv.setIsAccepted(State.ACCEPTED);

    Programme programme3 = new Programme();
    programme3.setDescription("The characteristics of someone or something");
    programme3.setId(1L);
    programme3.setNom("Nom");

    Etudiant student = new Etudiant();
    student.setActiveCv(activeCv);
    student.setCv(new ArrayList<>());
    student.setEmail("jane.doe@example.org");
    student.setId(1L);
    student.setInternshipsCandidate(new ArrayList<>());
    student.setMatricule("Matricule");
    student.setNom("Nom");
    student.setPassword("iloveyou");
    student.setPhone("6625550144");
    student.setPrenom("Prenom");
    student.setProgramme(programme3);
    student.setRole(Role.employer);

    Interview interview = new Interview();
    interview.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    interview.setDescription("The characteristics of someone or something");
    interview.setId(1L);
    interview.setInternshipOffer(internshipOffer);
    interview.setState(State.ACCEPTED);
    interview.setStudent(student);
    Optional<Interview> ofResult = Optional.of(interview);

    Programme programme4 = new Programme();
    programme4.setDescription("The characteristics of someone or something");
    programme4.setId(1L);
    programme4.setNom("Nom");

    Employeur employeur2 = new Employeur();
    employeur2.setEmail("jane.doe@example.org");
    employeur2.setEntreprise("Entreprise");
    employeur2.setId(1L);
    ArrayList<InternOffer> internOffers = new ArrayList<>();
    employeur2.setInternOffers(internOffers);
    employeur2.setNom("Nom");
    employeur2.setPassword("iloveyou");
    employeur2.setPhone("6625550144");
    employeur2.setPrenom("Prenom");
    employeur2.setProgramme(programme4);
    employeur2.setRole(Role.employer);

    File activeCv2 = new File();
    activeCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv2.setEtudiant(new Etudiant());
    activeCv2.setFileName("foo.txt");
    activeCv2.setId(1L);
    activeCv2.setInternshipCandidates(new InternshipCandidates());
    activeCv2.setIsAccepted(State.ACCEPTED);

    Programme programme5 = new Programme();
    programme5.setDescription("The characteristics of someone or something");
    programme5.setId(1L);
    programme5.setNom("Nom");

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
    etudiant3.setProgramme(programme5);
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

    File studentCv = new File();
    studentCv.setContent("AXAXAXAX".getBytes("UTF-8"));
    studentCv.setEtudiant(new Etudiant());
    studentCv.setFileName("foo.txt");
    studentCv.setId(1L);
    studentCv.setInternshipCandidates(new InternshipCandidates());
    studentCv.setIsAccepted(State.ACCEPTED);

    InternshipCandidates internshipCandidates3 = new InternshipCandidates();
    internshipCandidates3.setEtudiant(etudiant4);
    internshipCandidates3.setFiles(new ArrayList<>());
    internshipCandidates3.setId(1L);
    internshipCandidates3.setInternOffer(internOffer2);
    internshipCandidates3.setState(State.ACCEPTED);
    internshipCandidates3.setStudentCv(studentCv);

    File file2 = new File();
    file2.setContent("AXAXAXAX".getBytes("UTF-8"));
    file2.setEtudiant(etudiant3);
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

    Programme programme6 = new Programme();
    programme6.setDescription("The characteristics of someone or something");
    programme6.setId(1L);
    programme6.setNom("Nom");

    InternOffer internOffer3 = new InternOffer();
    internOffer3.setDescription("The characteristics of someone or something");
    internOffer3.setEmployeur(employeur3);
    internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
    internOffer3.setFile(file3);
    internOffer3.setId(1L);
    internOffer3.setInternshipCandidates(new ArrayList<>());
    internOffer3.setLocation("Location");
    internOffer3.setOfferReviewRequest(offerReviewRequest2);
    internOffer3.setProgramme(programme6);
    internOffer3.setSalaryByHour(10.0d);
    internOffer3.setSession("Session");
    internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
    internOffer3.setState(State.ACCEPTED);
    internOffer3.setStatus("Status");
    internOffer3.setTitle("Dr");

    Programme programme7 = new Programme();
    programme7.setDescription("The characteristics of someone or something");
    programme7.setId(1L);
    programme7.setNom("Nom");

    Internshipmanager internshipmanager2 = new Internshipmanager();
    internshipmanager2.setEmail("jane.doe@example.org");
    internshipmanager2.setId(1L);
    internshipmanager2.setNom("Nom");
    internshipmanager2.setPassword("iloveyou");
    internshipmanager2.setPhone("6625550144");
    internshipmanager2.setPrenom("Prenom");
    internshipmanager2.setProgramme(programme7);
    internshipmanager2.setRole(Role.employer);

    OfferReviewRequest offerReviewRequest3 = new OfferReviewRequest();
    offerReviewRequest3.setComment("Comment");
    offerReviewRequest3.setId(1L);
    offerReviewRequest3.setInternOffer(internOffer3);
    offerReviewRequest3.setInternshipmanager(internshipmanager2);

    Programme programme8 = new Programme();
    programme8.setDescription("The characteristics of someone or something");
    programme8.setId(1L);
    programme8.setNom("Nom");

    InternOffer internshipOffer2 = new InternOffer();
    internshipOffer2.setDescription("The characteristics of someone or something");
    internshipOffer2.setEmployeur(employeur2);
    internshipOffer2.setEndDate(LocalDate.of(1970, 1, 1));
    internshipOffer2.setFile(file2);
    internshipOffer2.setId(1L);
    internshipOffer2.setInternshipCandidates(new ArrayList<>());
    internshipOffer2.setLocation("Location");
    internshipOffer2.setOfferReviewRequest(offerReviewRequest3);
    internshipOffer2.setProgramme(programme8);
    internshipOffer2.setSalaryByHour(10.0d);
    internshipOffer2.setSession("Session");
    internshipOffer2.setStartDate(LocalDate.of(1970, 1, 1));
    internshipOffer2.setState(State.ACCEPTED);
    internshipOffer2.setStatus("Status");
    internshipOffer2.setTitle("Dr");

    File activeCv3 = new File();
    activeCv3.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv3.setEtudiant(new Etudiant());
    activeCv3.setFileName("foo.txt");
    activeCv3.setId(1L);
    activeCv3.setInternshipCandidates(new InternshipCandidates());
    activeCv3.setIsAccepted(State.ACCEPTED);

    Programme programme9 = new Programme();
    programme9.setDescription("The characteristics of someone or something");
    programme9.setId(1L);
    programme9.setNom("Nom");

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
    etudiant5.setProgramme(programme9);
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
    internshipCandidates4.setEtudiant(etudiant6);
    internshipCandidates4.setFiles(new ArrayList<>());
    internshipCandidates4.setId(1L);
    internshipCandidates4.setInternOffer(internOffer4);
    internshipCandidates4.setState(State.ACCEPTED);
    internshipCandidates4.setStudentCv(studentCv2);

    File activeCv4 = new File();
    activeCv4.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv4.setEtudiant(etudiant5);
    activeCv4.setFileName("foo.txt");
    activeCv4.setId(1L);
    activeCv4.setInternshipCandidates(internshipCandidates4);
    activeCv4.setIsAccepted(State.ACCEPTED);

    Programme programme10 = new Programme();
    programme10.setDescription("The characteristics of someone or something");
    programme10.setId(1L);
    programme10.setNom("Nom");

    Etudiant student2 = new Etudiant();
    student2.setActiveCv(activeCv4);
    student2.setCv(new ArrayList<>());
    student2.setEmail("jane.doe@example.org");
    student2.setId(1L);
    student2.setInternshipsCandidate(new ArrayList<>());
    student2.setMatricule("Matricule");
    student2.setNom("Nom");
    student2.setPassword("iloveyou");
    student2.setPhone("6625550144");
    student2.setPrenom("Prenom");
    student2.setProgramme(programme10);
    student2.setRole(Role.employer);

    Interview interview2 = new Interview();
    interview2.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    interview2.setDescription("The characteristics of someone or something");
    interview2.setId(1L);
    interview2.setInternshipOffer(internshipOffer2);
    interview2.setState(State.ACCEPTED);
    interview2.setStudent(student2);
    when(interviewRepository.save(Mockito.<Interview>any())).thenReturn(interview2);
    when(interviewRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    Optional<InterviewDTO> actualUpdateInterviewResult = interviewService.updateInterview(new InterviewRequestInDto());
    verify(interviewRepository).findById(Mockito.<Long>any());
    verify(interviewRepository).save(Mockito.<Interview>any());
    InterviewDTO getResult = actualUpdateInterviewResult.get();
    InternOfferDto internOffer5 = getResult.getInternOffer();
    assertEquals("1970-01-01", internOffer5.getEndDate());
    assertEquals("1970-01-01", internOffer5.getStartDate());
    EtudiantDto student3 = getResult.getStudent();
    assertEquals("6625550144", student3.getPhone());
    assertEquals("Dr", internOffer5.getTitle());
    assertEquals("Entreprise", internOffer5.getEmployeurEntreprise());
    assertEquals("Location", internOffer5.getLocation());
    assertEquals("Matricule", student3.getMatricule());
    assertEquals("Nom", internOffer5.getEmployeurNom());
    assertEquals("Nom", internOffer5.getProgrammeNom());
    assertEquals("Nom", student3.getNom());
    assertEquals("Prenom", internOffer5.getEmployeurPrenom());
    assertEquals("Prenom", student3.getPrenom());
    assertEquals("Session", internOffer5.getSession());
    assertEquals("The characteristics of someone or something", internOffer5.getDescription());
    FileDto file4 = internOffer5.getFile();
    assertEquals("foo.txt", file4.getFileName());
    assertEquals("jane.doe@example.org", student3.getEmail());
    assertNull(getResult.getDescription());
    assertNull(getResult.getDate());
    assertEquals(10.0d, internOffer5.getSalaryByHour());
    assertEquals(1L, student3.getProgramme_id());
    assertEquals(1L, file4.getId());
    assertEquals(1L, internOffer5.getEmployeurId());
    assertEquals(1L, internOffer5.getId());
    assertEquals(1L, internOffer5.getOfferReviewRequestId());
    assertEquals(1L, internOffer5.getProgrammeId());
    assertEquals(1L, getResult.getId());
    assertEquals(1L, student3.getId());
    assertEquals(State.ACCEPTED, file4.getIsAccepted());
    assertEquals(State.ACCEPTED, internOffer5.getState());
    assertEquals(State.PENDING, getResult.getState());
    assertTrue(student3.getCv().isEmpty());
    assertTrue(actualUpdateInterviewResult.isPresent());
    assertEquals(internOffers, internOffer5.getInternshipCandidates());
    byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedContent, file4.getContent());
  }
}
