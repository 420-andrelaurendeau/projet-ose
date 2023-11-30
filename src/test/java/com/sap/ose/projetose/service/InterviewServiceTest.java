package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.InterviewDTO;
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
import com.sap.ose.projetose.repository.InternOfferRepository;
import com.sap.ose.projetose.repository.InterviewRepository;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {InterviewService.class})
@ExtendWith(SpringExtension.class)
class InterviewServiceTest {
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
  * Method under test: {@link InterviewService#deleteInterviewById(Long)}
  */
  @Test
  void testDeleteInterviewById() {
    doNothing().when(interviewRepository).deleteById(Mockito.<Long>any());
    interviewService.deleteInterviewById(1L);
    verify(interviewRepository).deleteById(Mockito.<Long>any());
    assertTrue(interviewService.logger instanceof ch.qos.logback.classic.Logger);
  }

  /**
  * Method under test: {@link InterviewService#getInterview(long, long)}
  */
  @Test
  void testGetInterview() throws UnsupportedEncodingException {
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

    InternOffer internshipOffer = new InternOffer();
    internshipOffer.setDescription("The characteristics of someone or something");
    internshipOffer.setEmployeur(employeur);
    internshipOffer.setEndDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setFile(file);
    internshipOffer.setId(1L);
    internshipOffer.setInternshipCandidates(new ArrayList<>());
    internshipOffer.setLocation("Location");
    internshipOffer.setOfferReviewRequest(offerReviewRequest2);
    internshipOffer.setProgramme(programme5);
    internshipOffer.setSalaryByHour(10.0d);
    internshipOffer.setSession("Session");
    internshipOffer.setStartDate(LocalDate.of(1970, 1, 1));
    internshipOffer.setState(State.ACCEPTED);
    internshipOffer.setStatus("Status");
    internshipOffer.setTitle("Dr");

    File activeCv2 = new File();
    activeCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv2.setEtudiant(new Etudiant());
    activeCv2.setFileName("foo.txt");
    activeCv2.setId(1L);
    activeCv2.setInternshipCandidates(new InternshipCandidates());
    activeCv2.setIsAccepted(State.ACCEPTED);

    Programme programme6 = new Programme();
    programme6.setDescription("The characteristics of someone or something");
    programme6.setId(1L);
    programme6.setNom("Nom");

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
    etudiant3.setProgramme(programme6);
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

    File studentCv2 = new File();
    studentCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
    studentCv2.setEtudiant(new Etudiant());
    studentCv2.setFileName("foo.txt");
    studentCv2.setId(1L);
    studentCv2.setInternshipCandidates(new InternshipCandidates());
    studentCv2.setIsAccepted(State.ACCEPTED);

    InternshipCandidates internshipCandidates2 = new InternshipCandidates();
    internshipCandidates2.setEtudiant(etudiant4);
    internshipCandidates2.setFiles(new ArrayList<>());
    internshipCandidates2.setId(1L);
    internshipCandidates2.setInternOffer(internOffer3);
    internshipCandidates2.setState(State.ACCEPTED);
    internshipCandidates2.setStudentCv(studentCv2);

    File activeCv3 = new File();
    activeCv3.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv3.setEtudiant(etudiant3);
    activeCv3.setFileName("foo.txt");
    activeCv3.setId(1L);
    activeCv3.setInternshipCandidates(internshipCandidates2);
    activeCv3.setIsAccepted(State.ACCEPTED);

    Programme programme7 = new Programme();
    programme7.setDescription("The characteristics of someone or something");
    programme7.setId(1L);
    programme7.setNom("Nom");

    Etudiant student = new Etudiant();
    student.setActiveCv(activeCv3);
    student.setCv(new ArrayList<>());
    student.setEmail("jane.doe@example.org");
    student.setId(1L);
    student.setInternshipsCandidate(new ArrayList<>());
    student.setMatricule("Matricule");
    student.setNom("Nom");
    student.setPassword("iloveyou");
    student.setPhone("6625550144");
    student.setPrenom("Prenom");
    student.setProgramme(programme7);
    student.setRole(Role.employer);

    Interview interview = new Interview();
    interview.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    interview.setDescription("The characteristics of someone or something");
    interview.setId(1L);
    interview.setInternshipOffer(internshipOffer);
    interview.setState(State.ACCEPTED);
    interview.setStudent(student);
    when(interviewRepository.findByStudentIdAndInternOfferId(anyLong(), anyLong())).thenReturn(interview);
    Optional<InterviewDTO> actualInterview = interviewService.getInterview(1L, 1L);
    verify(interviewRepository).findByStudentIdAndInternOfferId(anyLong(), anyLong());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    InterviewDTO getResult = actualInterview.get();
    assertEquals("1969-12-31", simpleDateFormat.format(getResult.getDate()));
    InternOfferDto internOffer4 = getResult.getInternOffer();
    assertEquals("1970-01-01", internOffer4.getEndDate());
    assertEquals("1970-01-01", internOffer4.getStartDate());
    EtudiantDto student2 = getResult.getStudent();
    assertEquals("6625550144", student2.getPhone());
    assertEquals("Dr", internOffer4.getTitle());
    assertEquals("Entreprise", internOffer4.getEmployeurEntreprise());
    assertEquals("Location", internOffer4.getLocation());
    assertEquals("Matricule", student2.getMatricule());
    assertEquals("Nom", internOffer4.getEmployeurNom());
    assertEquals("Nom", internOffer4.getProgrammeNom());
    assertEquals("Nom", student2.getNom());
    assertEquals("Prenom", internOffer4.getEmployeurPrenom());
    assertEquals("Prenom", student2.getPrenom());
    assertEquals("Session", internOffer4.getSession());
    assertEquals("The characteristics of someone or something", internOffer4.getDescription());
    assertEquals("The characteristics of someone or something", getResult.getDescription());
    FileDto file3 = internOffer4.getFile();
    assertEquals("foo.txt", file3.getFileName());
    assertEquals("jane.doe@example.org", student2.getEmail());
    assertEquals(10.0d, internOffer4.getSalaryByHour());
    assertEquals(1L, student2.getProgramme_id());
    assertEquals(1L, file3.getId());
    assertEquals(1L, internOffer4.getEmployeurId());
    assertEquals(1L, internOffer4.getId());
    assertEquals(1L, internOffer4.getOfferReviewRequestId());
    assertEquals(1L, internOffer4.getProgrammeId());
    assertEquals(1L, student2.getId());
    assertEquals(State.ACCEPTED, file3.getIsAccepted());
    assertEquals(State.ACCEPTED, internOffer4.getState());
    assertEquals(State.ACCEPTED, getResult.getState());
    assertTrue(student2.getCv().isEmpty());
    assertTrue(actualInterview.isPresent());
    assertEquals(internOffers, internOffer4.getInternshipCandidates());
    byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedContent, file3.getContent());
  }

  /**
  * Method under test: {@link InterviewService#getAllInterviews()}
  */
  @Test
  void testGetAllInterviews() {
    when(interviewRepository.findAll()).thenReturn(new ArrayList<>());
    List<InterviewDTO> actualAllInterviews = interviewService.getAllInterviews();
    verify(interviewRepository).findAll();
    assertTrue(actualAllInterviews.isEmpty());
  }

  /**
  * Method under test: {@link InterviewService#getAllInterviews()}
  */
  @Test
  void testGetAllInterviews2() throws UnsupportedEncodingException {
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
    List<InterviewDTO> actualAllInterviews = interviewService.getAllInterviews();
    verify(interviewRepository).findAll();
    assertEquals(1, actualAllInterviews.size());
  }

  /**
  * Method under test: {@link InterviewService#getAllInterviews()}
  */
  @Test
  void testGetAllInterviews3() throws UnsupportedEncodingException {
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
    file2.setContent("AXAXAXAX".getBytes("UTF-8"));
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
    activeCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
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
    List<InterviewDTO> actualAllInterviews = interviewService.getAllInterviews();
    verify(interviewRepository).findAll();
    assertEquals(2, actualAllInterviews.size());
  }
}
