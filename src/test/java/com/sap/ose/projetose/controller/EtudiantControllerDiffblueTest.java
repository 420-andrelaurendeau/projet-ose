package com.sap.ose.projetose.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
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
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.FileEntityRepository;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import com.sap.ose.projetose.service.EtudiantService;
import com.sap.ose.projetose.service.ProgrammeService;

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
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EtudiantController.class, EtudiantService.class, ProgrammeService.class})
@ExtendWith(SpringExtension.class)
class EtudiantControllerDiffblueTest {
  @Autowired
  private EtudiantController etudiantController;

  @MockBean
  private EtudiantRepository etudiantRepository;

  @MockBean
  private FileEntityRepository fileEntityRepository;

  @MockBean
  private ProgrammeRepository programmeRepository;

  /**
   * Method under test: {@link EtudiantController#saveEtudiant(Etudiant)}
   */
  @Test
  void testSaveEtudiant() throws UnsupportedEncodingException {
    //   Diffblue Cover was unable to write a Spring test,
    //   so wrote a non-Spring test instead.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   com.fasterxml.jackson.databind.JsonMappingException: Cannot invoke "com.sap.ose.projetose.modeles.Role.name()" because "<local2>.role" is null (through reference chain: com.sap.ose.projetose.modeles.Etudiant["activeCv"]->com.sap.ose.projetose.modeles.File["etudiant"]->com.sap.ose.projetose.modeles.Etudiant["activeCv"]->com.sap.ose.projetose.modeles.File["internshipCandidates"]->com.sap.ose.projetose.modeles.InternshipCandidates["etudiant"]->com.sap.ose.projetose.modeles.Etudiant["authorities"])
    //       at com.fasterxml.jackson.databind.JsonMappingException.wrapWithPath(JsonMappingException.java:402)
    //       at com.fasterxml.jackson.databind.JsonMappingException.wrapWithPath(JsonMappingException.java:361)
    //       at com.fasterxml.jackson.databind.ser.std.StdSerializer.wrapAndThrow(StdSerializer.java:323)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:780)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
    //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
    //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
    //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
    //   java.lang.NullPointerException: Cannot invoke "com.sap.ose.projetose.modeles.Role.name()" because "<local2>.role" is null
    //       at com.sap.ose.projetose.modeles.Utilisateur.getAuthorities(Utilisateur.java:55)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:688)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
    //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
    //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
    //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
    //   See https://diff.blue/R013 to resolve this issue.

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
    EtudiantRepository etudiantRepository = mock(EtudiantRepository.class);
    when(etudiantRepository.save(Mockito.<Etudiant>any())).thenReturn(etudiant5);

    Programme programme5 = new Programme();
    programme5.setDescription("The characteristics of someone or something");
    programme5.setId(1L);
    programme5.setNom("Nom");
    Optional<Programme> ofResult = Optional.of(programme5);
    ProgrammeRepository programmeRepository = mock(ProgrammeRepository.class);
    when(programmeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    EtudiantController etudiantController = new EtudiantController(new EtudiantService(etudiantRepository,
            new ProgrammeService(programmeRepository), mock(FileEntityRepository.class)));

    Programme programme6 = new Programme();
    programme6.setDescription("The characteristics of someone or something");
    programme6.setId(1L);
    programme6.setNom("Nom");
    Etudiant etudiant6 = mock(Etudiant.class);
    doNothing().when(etudiant6).setProgramme(Mockito.<Programme>any());
    when(etudiant6.getProgramme()).thenReturn(programme6);
    ResponseEntity<Etudiant> actualSaveEtudiantResult = etudiantController.saveEtudiant(etudiant6);
    verify(etudiant6).getProgramme();
    verify(etudiant6).setProgramme(Mockito.<Programme>any());
    verify(programmeRepository).findById(Mockito.<Long>any());
    verify(etudiantRepository).save(Mockito.<Etudiant>any());
    assertEquals(200, actualSaveEtudiantResult.getStatusCodeValue());
    assertTrue(actualSaveEtudiantResult.hasBody());
    assertTrue(actualSaveEtudiantResult.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link EtudiantController#addCv(String, File)}
   */
  @Test
  void testAddCv() throws UnsupportedEncodingException {
    //   Diffblue Cover was unable to write a Spring test,
    //   so wrote a non-Spring test instead.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   com.fasterxml.jackson.databind.JsonMappingException: Cannot invoke "com.sap.ose.projetose.modeles.Role.name()" because "this.role" is null (through reference chain: com.sap.ose.projetose.modeles.File["etudiant"]->com.sap.ose.projetose.modeles.Etudiant["activeCv"]->com.sap.ose.projetose.modeles.File["etudiant"]->com.sap.ose.projetose.modeles.Etudiant["activeCv"]->com.sap.ose.projetose.modeles.File["etudiant"]->com.sap.ose.projetose.modeles.Etudiant["authorities"])
    //       at com.fasterxml.jackson.databind.JsonMappingException.wrapWithPath(JsonMappingException.java:402)
    //       at com.fasterxml.jackson.databind.JsonMappingException.wrapWithPath(JsonMappingException.java:361)
    //       at com.fasterxml.jackson.databind.ser.std.StdSerializer.wrapAndThrow(StdSerializer.java:323)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:780)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
    //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
    //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
    //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
    //   java.lang.NullPointerException: Cannot invoke "com.sap.ose.projetose.modeles.Role.name()" because "this.role" is null
    //       at com.sap.ose.projetose.modeles.Utilisateur.getAuthorities(Utilisateur.java:55)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:688)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
    //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
    //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
    //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
    //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
    //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
    //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
    //   See https://diff.blue/R013 to resolve this issue.

    Etudiant etudiant = mock(Etudiant.class);
    doNothing().when(etudiant).setCv(Mockito.<List<File>>any());
    Optional<Etudiant> ofResult = Optional.of(etudiant);

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

    InternshipCandidates internshipCandidates = new InternshipCandidates();
    internshipCandidates.setEtudiant(new Etudiant());
    internshipCandidates.setFiles(new ArrayList<>());
    internshipCandidates.setId(1L);
    internshipCandidates.setInternOffer(new InternOffer());
    internshipCandidates.setState(State.ACCEPTED);
    internshipCandidates.setStudentCv(new File());

    File activeCv = new File();
    activeCv.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv.setEtudiant(etudiant2);
    activeCv.setFileName("foo.txt");
    activeCv.setId(1L);
    activeCv.setInternshipCandidates(internshipCandidates);
    activeCv.setIsAccepted(State.ACCEPTED);

    Programme programme = new Programme();
    programme.setDescription("The characteristics of someone or something");
    programme.setId(1L);
    programme.setNom("Nom");

    Etudiant etudiant3 = new Etudiant();
    etudiant3.setActiveCv(activeCv);
    etudiant3.setCv(new ArrayList<>());
    etudiant3.setEmail("jane.doe@example.org");
    etudiant3.setId(1L);
    etudiant3.setInternshipsCandidate(new ArrayList<>());
    etudiant3.setMatricule("Matricule");
    etudiant3.setNom("Nom");
    etudiant3.setPassword("iloveyou");
    etudiant3.setPhone("6625550144");
    etudiant3.setPrenom("Prenom");
    etudiant3.setProgramme(programme);
    etudiant3.setRole(Role.employer);

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

    Etudiant etudiant4 = new Etudiant();
    etudiant4.setActiveCv(activeCv2);
    etudiant4.setCv(new ArrayList<>());
    etudiant4.setEmail("jane.doe@example.org");
    etudiant4.setId(1L);
    etudiant4.setInternshipsCandidate(new ArrayList<>());
    etudiant4.setMatricule("Matricule");
    etudiant4.setNom("Nom");
    etudiant4.setPassword("iloveyou");
    etudiant4.setPhone("6625550144");
    etudiant4.setPrenom("Prenom");
    etudiant4.setProgramme(programme2);
    etudiant4.setRole(Role.employer);

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

    InternshipCandidates internshipCandidates2 = new InternshipCandidates();
    internshipCandidates2.setEtudiant(new Etudiant());
    internshipCandidates2.setFiles(new ArrayList<>());
    internshipCandidates2.setId(1L);
    internshipCandidates2.setInternOffer(new InternOffer());
    internshipCandidates2.setState(State.ACCEPTED);
    internshipCandidates2.setStudentCv(new File());

    File studentCv = new File();
    studentCv.setContent("AXAXAXAX".getBytes("UTF-8"));
    studentCv.setEtudiant(etudiant5);
    studentCv.setFileName("foo.txt");
    studentCv.setId(1L);
    studentCv.setInternshipCandidates(internshipCandidates2);
    studentCv.setIsAccepted(State.ACCEPTED);

    InternshipCandidates internshipCandidates3 = new InternshipCandidates();
    internshipCandidates3.setEtudiant(etudiant4);
    internshipCandidates3.setFiles(new ArrayList<>());
    internshipCandidates3.setId(1L);
    internshipCandidates3.setInternOffer(internOffer);
    internshipCandidates3.setState(State.ACCEPTED);
    internshipCandidates3.setStudentCv(studentCv);

    File activeCv3 = new File();
    activeCv3.setContent("AXAXAXAX".getBytes("UTF-8"));
    activeCv3.setEtudiant(etudiant3);
    activeCv3.setFileName("foo.txt");
    activeCv3.setId(1L);
    activeCv3.setInternshipCandidates(internshipCandidates3);
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
    EtudiantRepository etudiantRepository = mock(EtudiantRepository.class);
    when(etudiantRepository.save(Mockito.<Etudiant>any())).thenReturn(etudiant6);
    when(etudiantRepository.findByMatricule(Mockito.<String>any())).thenReturn(ofResult);
    EtudiantController etudiantController = new EtudiantController(new EtudiantService(etudiantRepository,
            new ProgrammeService(mock(ProgrammeRepository.class)), mock(FileEntityRepository.class)));
    File cv = mock(File.class);
    doNothing().when(cv).setEtudiant(Mockito.<Etudiant>any());
    ResponseEntity<EtudiantDto> actualAddCvResult = etudiantController.addCv("Matricule", cv);
    verify(etudiant).setCv(Mockito.<List<File>>any());
    verify(cv).setEtudiant(Mockito.<Etudiant>any());
    verify(etudiantRepository).findByMatricule(Mockito.<String>any());
    verify(etudiantRepository).save(Mockito.<Etudiant>any());
    EtudiantDto body = actualAddCvResult.getBody();
    assertEquals("6625550144", body.getPhone());
    assertEquals("Matricule", body.getMatricule());
    assertEquals("Nom", body.getNom());
    assertEquals("Prenom", body.getPrenom());
    assertEquals("jane.doe@example.org", body.getEmail());
    assertEquals(1L, body.getProgramme_id());
    assertEquals(1L, body.getId());
    assertEquals(200, actualAddCvResult.getStatusCodeValue());
    assertTrue(body.getCv().isEmpty());
    assertTrue(actualAddCvResult.hasBody());
    assertTrue(actualAddCvResult.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link EtudiantController#setDefaultCv(long, long)}
   */
  @Test
  void testSetDefaultCv() {
    //   Diffblue Cover was unable to write a Spring test,
    //   so wrote a non-Spring test instead.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   jakarta.servlet.ServletException: Request processing failed: com.sap.ose.projetose.exception.ServiceException: Erreur lors de la récupération des CV de l'étudiant
    //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
    //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
    //   com.sap.ose.projetose.exception.ServiceException: Erreur lors de la récupération des CV de l'étudiant
    //       at com.sap.ose.projetose.service.EtudiantService.setDefaultCv(EtudiantService.java:213)
    //       at com.sap.ose.projetose.controller.EtudiantController.setDefaultCv(EtudiantController.java:80)
    //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
    //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
    //   See https://diff.blue/R013 to resolve this issue.

    EtudiantService etudiantService = mock(EtudiantService.class);
    when(etudiantService.setDefaultCv(anyLong(), anyLong())).thenReturn(new FileDtoAll());
    ResponseEntity<FileDtoAll> actualSetDefaultCvResult = (new EtudiantController(etudiantService)).setDefaultCv(1L,
            1L);
    verify(etudiantService).setDefaultCv(anyLong(), anyLong());
    assertEquals(200, actualSetDefaultCvResult.getStatusCodeValue());
    assertTrue(actualSetDefaultCvResult.hasBody());
    assertTrue(actualSetDefaultCvResult.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link EtudiantController#getDefaultCv(long)}
   */
  @Test
  void testGetDefaultCv() throws Exception {
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student/{id}/defaultCv", 1L);
    MockMvcBuilders.standaloneSetup(etudiantController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content()
                    .string(
                            "{\"id\":1,\"content\":\"QVhBWEFYQVg=\",\"fileName\":\"foo.txt\",\"isAccepted\":\"ACCEPTED\",\"etudiant\":{\"id\":1,\"nom"
                                    + "\":\"Nom\",\"prenom\":\"Prenom\",\"phone\":\"6625550144\",\"email\":\"jane.doe@example.org\",\"matricule\":\"Matricule"
                                    + "\",\"programme_id\":1,\"cv\":[],\"internships_id\":null}}"));
  }

  /**
   * Method under test: {@link EtudiantController#getEtudiant(Long)}
   */
  @Test
  void testGetEtudiant() throws Exception {
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student/{id}", 1L);
    MockMvcBuilders.standaloneSetup(etudiantController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content()
                    .string(
                            "{\"id\":0,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"phone\":\"6625550144\",\"email\":\"jane.doe@example.org\",\"matricule"
                                    + "\":\"Matricule\",\"programme_id\":1,\"cv\":[],\"internships_id\":[]}"));
  }

  /**
   * Method under test: {@link EtudiantController#getEtudiants()}
   */
  @Test
  void testGetEtudiants() throws Exception {
    when(etudiantRepository.findAll()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student/etudiants");
    MockMvcBuilders.standaloneSetup(etudiantController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link EtudiantController#getEtudiants()}
   */
  @Test
  void testGetEtudiants2() throws Exception {
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
    programme.setNom("getEtudiants");

    Etudiant etudiant = new Etudiant();
    etudiant.setActiveCv(activeCv);
    etudiant.setCv(new ArrayList<>());
    etudiant.setEmail("jane.doe@example.org");
    etudiant.setId(1L);
    etudiant.setInternshipsCandidate(new ArrayList<>());
    etudiant.setMatricule("getEtudiants");
    etudiant.setNom("getEtudiants");
    etudiant.setPassword("iloveyou");
    etudiant.setPhone("6625550144");
    etudiant.setPrenom("getEtudiants");
    etudiant.setProgramme(programme);
    etudiant.setRole(Role.employer);

    Etudiant etudiant2 = new Etudiant();
    etudiant2.setActiveCv(new File());
    etudiant2.setCv(new ArrayList<>());
    etudiant2.setEmail("jane.doe@example.org");
    etudiant2.setId(1L);
    etudiant2.setInternshipsCandidate(new ArrayList<>());
    etudiant2.setMatricule("getEtudiants");
    etudiant2.setNom("getEtudiants");
    etudiant2.setPassword("iloveyou");
    etudiant2.setPhone("6625550144");
    etudiant2.setPrenom("getEtudiants");
    etudiant2.setProgramme(new Programme());
    etudiant2.setRole(Role.employer);

    InternOffer internOffer = new InternOffer();
    internOffer.setDescription("The characteristics of someone or something");
    internOffer.setEmployeur(new Employeur());
    internOffer.setEndDate(LocalDate.of(1970, 1, 1));
    internOffer.setFile(new File());
    internOffer.setId(1L);
    internOffer.setInternshipCandidates(new ArrayList<>());
    internOffer.setLocation("getEtudiants");
    internOffer.setOfferReviewRequest(new OfferReviewRequest());
    internOffer.setProgramme(new Programme());
    internOffer.setSalaryByHour(10.0d);
    internOffer.setSession("getEtudiants");
    internOffer.setStartDate(LocalDate.of(1970, 1, 1));
    internOffer.setState(State.ACCEPTED);
    internOffer.setStatus("getEtudiants");
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
    programme2.setNom("getEtudiants");

    Etudiant etudiant3 = new Etudiant();
    etudiant3.setActiveCv(activeCv2);
    etudiant3.setCv(new ArrayList<>());
    etudiant3.setEmail("jane.doe@example.org");
    etudiant3.setId(1L);
    etudiant3.setInternshipsCandidate(new ArrayList<>());
    etudiant3.setMatricule("getEtudiants");
    etudiant3.setNom("getEtudiants");
    etudiant3.setPassword("iloveyou");
    etudiant3.setPhone("6625550144");
    etudiant3.setPrenom("getEtudiants");
    etudiant3.setProgramme(programme2);
    etudiant3.setRole(Role.employer);

    ArrayList<Etudiant> etudiantList = new ArrayList<>();
    etudiantList.add(etudiant3);
    when(etudiantRepository.findAll()).thenReturn(etudiantList);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student/etudiants");
    MockMvcBuilders.standaloneSetup(etudiantController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content()
                    .string(
                            "[{\"id\":0,\"nom\":\"getEtudiants\",\"prenom\":\"getEtudiants\",\"phone\":\"6625550144\",\"email\":\"jane.doe@example"
                                    + ".org\",\"matricule\":\"getEtudiants\",\"programme_id\":1,\"cv\":[],\"internships_id\":[]}]"));
  }

  /**
   * Method under test: {@link EtudiantController#getEtudiants()}
   */
  @Test
  void testGetEtudiants3() throws Exception {
    when(etudiantRepository.findAll()).thenReturn(new ArrayList<>());
    SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(etudiantController)
            .build()
            .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  /**
   * Method under test: {@link EtudiantController#getEtudiantsWithCv(long)}
   */
  @Test
  void testGetEtudiantsWithCv() throws Exception {
    Optional<List<File>> ofResult = Optional.of(new ArrayList<>());
    when(fileEntityRepository.findAllByEtudiant_IdIs(anyLong())).thenReturn(ofResult);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student/{id}/cvs", 1L);
    MockMvcBuilders.standaloneSetup(etudiantController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link EtudiantController#getEtudiantsWithCv(long)}
   */
  @Test
  void testGetEtudiantsWithCv2() throws Exception {
    Etudiant etudiant = new Etudiant();
    etudiant.setActiveCv(new File());
    etudiant.setCv(new ArrayList<>());
    etudiant.setEmail("jane.doe@example.org");
    etudiant.setId(1L);
    etudiant.setInternshipsCandidate(new ArrayList<>());
    etudiant.setMatricule("getEtudiantsWithCv");
    etudiant.setNom("getEtudiantsWithCv");
    etudiant.setPassword("iloveyou");
    etudiant.setPhone("6625550144");
    etudiant.setPrenom("getEtudiantsWithCv");
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
    activeCv.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    activeCv.setEtudiant(etudiant);
    activeCv.setFileName("foo.txt");
    activeCv.setId(1L);
    activeCv.setInternshipCandidates(internshipCandidates);
    activeCv.setIsAccepted(State.ACCEPTED);

    Programme programme = new Programme();
    programme.setDescription("The characteristics of someone or something");
    programme.setId(1L);
    programme.setNom("getEtudiantsWithCv");

    Etudiant etudiant2 = new Etudiant();
    etudiant2.setActiveCv(activeCv);
    etudiant2.setCv(new ArrayList<>());
    etudiant2.setEmail("jane.doe@example.org");
    etudiant2.setId(1L);
    etudiant2.setInternshipsCandidate(new ArrayList<>());
    etudiant2.setMatricule("getEtudiantsWithCv");
    etudiant2.setNom("getEtudiantsWithCv");
    etudiant2.setPassword("iloveyou");
    etudiant2.setPhone("6625550144");
    etudiant2.setPrenom("getEtudiantsWithCv");
    etudiant2.setProgramme(programme);
    etudiant2.setRole(Role.employer);

    File activeCv2 = new File();
    activeCv2.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    activeCv2.setEtudiant(new Etudiant());
    activeCv2.setFileName("foo.txt");
    activeCv2.setId(1L);
    activeCv2.setInternshipCandidates(new InternshipCandidates());
    activeCv2.setIsAccepted(State.ACCEPTED);

    Programme programme2 = new Programme();
    programme2.setDescription("The characteristics of someone or something");
    programme2.setId(1L);
    programme2.setNom("getEtudiantsWithCv");

    Etudiant etudiant3 = new Etudiant();
    etudiant3.setActiveCv(activeCv2);
    etudiant3.setCv(new ArrayList<>());
    etudiant3.setEmail("jane.doe@example.org");
    etudiant3.setId(1L);
    etudiant3.setInternshipsCandidate(new ArrayList<>());
    etudiant3.setMatricule("getEtudiantsWithCv");
    etudiant3.setNom("getEtudiantsWithCv");
    etudiant3.setPassword("iloveyou");
    etudiant3.setPhone("6625550144");
    etudiant3.setPrenom("getEtudiantsWithCv");
    etudiant3.setProgramme(programme2);
    etudiant3.setRole(Role.employer);

    Employeur employeur = new Employeur();
    employeur.setEmail("jane.doe@example.org");
    employeur.setEntreprise("getEtudiantsWithCv");
    employeur.setId(1L);
    employeur.setInternOffers(new ArrayList<>());
    employeur.setNom("getEtudiantsWithCv");
    employeur.setPassword("iloveyou");
    employeur.setPhone("6625550144");
    employeur.setPrenom("getEtudiantsWithCv");
    employeur.setProgramme(new Programme());
    employeur.setRole(Role.employer);

    File file = new File();
    file.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    file.setEtudiant(new Etudiant());
    file.setFileName("foo.txt");
    file.setId(1L);
    file.setInternshipCandidates(new InternshipCandidates());
    file.setIsAccepted(State.ACCEPTED);

    OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
    offerReviewRequest.setComment("getEtudiantsWithCv");
    offerReviewRequest.setId(1L);
    offerReviewRequest.setInternOffer(new InternOffer());
    offerReviewRequest.setInternshipmanager(new Internshipmanager());

    Programme programme3 = new Programme();
    programme3.setDescription("The characteristics of someone or something");
    programme3.setId(1L);
    programme3.setNom("getEtudiantsWithCv");

    InternOffer internOffer = new InternOffer();
    internOffer.setDescription("The characteristics of someone or something");
    internOffer.setEmployeur(employeur);
    internOffer.setEndDate(LocalDate.of(1970, 1, 1));
    internOffer.setFile(file);
    internOffer.setId(1L);
    internOffer.setInternshipCandidates(new ArrayList<>());
    internOffer.setLocation("getEtudiantsWithCv");
    internOffer.setOfferReviewRequest(offerReviewRequest);
    internOffer.setProgramme(programme3);
    internOffer.setSalaryByHour(10.0d);
    internOffer.setSession("getEtudiantsWithCv");
    internOffer.setStartDate(LocalDate.of(1970, 1, 1));
    internOffer.setState(State.ACCEPTED);
    internOffer.setStatus("getEtudiantsWithCv");
    internOffer.setTitle("Dr");

    Etudiant etudiant4 = new Etudiant();
    etudiant4.setActiveCv(new File());
    etudiant4.setCv(new ArrayList<>());
    etudiant4.setEmail("jane.doe@example.org");
    etudiant4.setId(1L);
    etudiant4.setInternshipsCandidate(new ArrayList<>());
    etudiant4.setMatricule("getEtudiantsWithCv");
    etudiant4.setNom("getEtudiantsWithCv");
    etudiant4.setPassword("iloveyou");
    etudiant4.setPhone("6625550144");
    etudiant4.setPrenom("getEtudiantsWithCv");
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
    studentCv.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
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
    file2.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    file2.setEtudiant(etudiant2);
    file2.setFileName("foo.txt");
    file2.setId(1L);
    file2.setInternshipCandidates(internshipCandidates3);
    file2.setIsAccepted(State.ACCEPTED);

    ArrayList<File> fileList = new ArrayList<>();
    fileList.add(file2);
    Optional<List<File>> ofResult = Optional.of(fileList);
    when(fileEntityRepository.findAllByEtudiant_IdIs(anyLong())).thenReturn(ofResult);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student/{id}/cvs", 1L);
    MockMvcBuilders.standaloneSetup(etudiantController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content()
                    .string(
                            "[{\"id\":1,\"content\":\"QQFBAUEBQQE=\",\"fileName\":\"foo.txt\",\"isAccepted\":\"ACCEPTED\",\"etudiant\":{\"id\":1,\"nom"
                                    + "\":\"getEtudiantsWithCv\",\"prenom\":\"getEtudiantsWithCv\",\"phone\":\"6625550144\",\"email\":\"jane.doe@example"
                                    + ".org\",\"matricule\":\"getEtudiantsWithCv\",\"programme_id\":1,\"cv\":[],\"internships_id\":null}}]"));
  }

  /**
   * Method under test: {@link EtudiantController#getEtudiantsWithCv(long)}
   */
  @Test
  void testGetEtudiantsWithCv3() throws Exception {
    Etudiant etudiant = new Etudiant();
    etudiant.setActiveCv(new File());
    etudiant.setCv(new ArrayList<>());
    etudiant.setEmail("jane.doe@example.org");
    etudiant.setId(1L);
    etudiant.setInternshipsCandidate(new ArrayList<>());
    etudiant.setMatricule("getEtudiantsWithCv");
    etudiant.setNom("getEtudiantsWithCv");
    etudiant.setPassword("iloveyou");
    etudiant.setPhone("6625550144");
    etudiant.setPrenom("getEtudiantsWithCv");
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
    activeCv.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    activeCv.setEtudiant(etudiant);
    activeCv.setFileName("foo.txt");
    activeCv.setId(1L);
    activeCv.setInternshipCandidates(internshipCandidates);
    activeCv.setIsAccepted(State.ACCEPTED);

    Programme programme = new Programme();
    programme.setDescription("The characteristics of someone or something");
    programme.setId(1L);
    programme.setNom("getEtudiantsWithCv");

    Etudiant etudiant2 = new Etudiant();
    etudiant2.setActiveCv(activeCv);
    etudiant2.setCv(new ArrayList<>());
    etudiant2.setEmail("jane.doe@example.org");
    etudiant2.setId(1L);
    etudiant2.setInternshipsCandidate(new ArrayList<>());
    etudiant2.setMatricule("getEtudiantsWithCv");
    etudiant2.setNom("getEtudiantsWithCv");
    etudiant2.setPassword("iloveyou");
    etudiant2.setPhone("6625550144");
    etudiant2.setPrenom("getEtudiantsWithCv");
    etudiant2.setProgramme(programme);
    etudiant2.setRole(Role.employer);

    File activeCv2 = new File();
    activeCv2.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    activeCv2.setEtudiant(new Etudiant());
    activeCv2.setFileName("foo.txt");
    activeCv2.setId(1L);
    activeCv2.setInternshipCandidates(new InternshipCandidates());
    activeCv2.setIsAccepted(State.ACCEPTED);

    Programme programme2 = new Programme();
    programme2.setDescription("The characteristics of someone or something");
    programme2.setId(1L);
    programme2.setNom("getEtudiantsWithCv");

    Etudiant etudiant3 = new Etudiant();
    etudiant3.setActiveCv(activeCv2);
    etudiant3.setCv(new ArrayList<>());
    etudiant3.setEmail("jane.doe@example.org");
    etudiant3.setId(1L);
    etudiant3.setInternshipsCandidate(new ArrayList<>());
    etudiant3.setMatricule("getEtudiantsWithCv");
    etudiant3.setNom("getEtudiantsWithCv");
    etudiant3.setPassword("iloveyou");
    etudiant3.setPhone("6625550144");
    etudiant3.setPrenom("getEtudiantsWithCv");
    etudiant3.setProgramme(programme2);
    etudiant3.setRole(Role.employer);

    Employeur employeur = new Employeur();
    employeur.setEmail("jane.doe@example.org");
    employeur.setEntreprise("getEtudiantsWithCv");
    employeur.setId(1L);
    employeur.setInternOffers(new ArrayList<>());
    employeur.setNom("getEtudiantsWithCv");
    employeur.setPassword("iloveyou");
    employeur.setPhone("6625550144");
    employeur.setPrenom("getEtudiantsWithCv");
    employeur.setProgramme(new Programme());
    employeur.setRole(Role.employer);

    File file = new File();
    file.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    file.setEtudiant(new Etudiant());
    file.setFileName("foo.txt");
    file.setId(1L);
    file.setInternshipCandidates(new InternshipCandidates());
    file.setIsAccepted(State.ACCEPTED);

    OfferReviewRequest offerReviewRequest = new OfferReviewRequest();
    offerReviewRequest.setComment("getEtudiantsWithCv");
    offerReviewRequest.setId(1L);
    offerReviewRequest.setInternOffer(new InternOffer());
    offerReviewRequest.setInternshipmanager(new Internshipmanager());

    Programme programme3 = new Programme();
    programme3.setDescription("The characteristics of someone or something");
    programme3.setId(1L);
    programme3.setNom("getEtudiantsWithCv");

    InternOffer internOffer = new InternOffer();
    internOffer.setDescription("The characteristics of someone or something");
    internOffer.setEmployeur(employeur);
    internOffer.setEndDate(LocalDate.of(1970, 1, 1));
    internOffer.setFile(file);
    internOffer.setId(1L);
    internOffer.setInternshipCandidates(new ArrayList<>());
    internOffer.setLocation("getEtudiantsWithCv");
    internOffer.setOfferReviewRequest(offerReviewRequest);
    internOffer.setProgramme(programme3);
    internOffer.setSalaryByHour(10.0d);
    internOffer.setSession("getEtudiantsWithCv");
    internOffer.setStartDate(LocalDate.of(1970, 1, 1));
    internOffer.setState(State.ACCEPTED);
    internOffer.setStatus("getEtudiantsWithCv");
    internOffer.setTitle("Dr");

    Etudiant etudiant4 = new Etudiant();
    etudiant4.setActiveCv(new File());
    etudiant4.setCv(new ArrayList<>());
    etudiant4.setEmail("jane.doe@example.org");
    etudiant4.setId(1L);
    etudiant4.setInternshipsCandidate(new ArrayList<>());
    etudiant4.setMatricule("getEtudiantsWithCv");
    etudiant4.setNom("getEtudiantsWithCv");
    etudiant4.setPassword("iloveyou");
    etudiant4.setPhone("6625550144");
    etudiant4.setPrenom("getEtudiantsWithCv");
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
    studentCv.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
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
    file2.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
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
    etudiant5.setMatricule("Matricule");
    etudiant5.setNom("Nom");
    etudiant5.setPassword("getEtudiantsWithCv");
    etudiant5.setPhone("8605550118");
    etudiant5.setPrenom("Prenom");
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
    activeCv3.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    activeCv3.setEtudiant(etudiant5);
    activeCv3.setFileName("getEtudiantsWithCv");
    activeCv3.setId(2L);
    activeCv3.setInternshipCandidates(internshipCandidates4);
    activeCv3.setIsAccepted(State.PENDING);

    Programme programme4 = new Programme();
    programme4.setDescription("getEtudiantsWithCv");
    programme4.setId(2L);
    programme4.setNom("Nom");

    Etudiant etudiant6 = new Etudiant();
    etudiant6.setActiveCv(activeCv3);
    etudiant6.setCv(new ArrayList<>());
    etudiant6.setEmail("john.smith@example.org");
    etudiant6.setId(2L);
    etudiant6.setInternshipsCandidate(new ArrayList<>());
    etudiant6.setMatricule("Matricule");
    etudiant6.setNom("Nom");
    etudiant6.setPassword("getEtudiantsWithCv");
    etudiant6.setPhone("8605550118");
    etudiant6.setPrenom("Prenom");
    etudiant6.setProgramme(programme4);
    etudiant6.setRole(Role.student);

    File activeCv4 = new File();
    activeCv4.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    activeCv4.setEtudiant(new Etudiant());
    activeCv4.setFileName("getEtudiantsWithCv");
    activeCv4.setId(2L);
    activeCv4.setInternshipCandidates(new InternshipCandidates());
    activeCv4.setIsAccepted(State.PENDING);

    Programme programme5 = new Programme();
    programme5.setDescription("getEtudiantsWithCv");
    programme5.setId(2L);
    programme5.setNom("Nom");

    Etudiant etudiant7 = new Etudiant();
    etudiant7.setActiveCv(activeCv4);
    etudiant7.setCv(new ArrayList<>());
    etudiant7.setEmail("john.smith@example.org");
    etudiant7.setId(2L);
    etudiant7.setInternshipsCandidate(new ArrayList<>());
    etudiant7.setMatricule("Matricule");
    etudiant7.setNom("Nom");
    etudiant7.setPassword("getEtudiantsWithCv");
    etudiant7.setPhone("8605550118");
    etudiant7.setPrenom("Prenom");
    etudiant7.setProgramme(programme5);
    etudiant7.setRole(Role.student);

    Employeur employeur2 = new Employeur();
    employeur2.setEmail("john.smith@example.org");
    employeur2.setEntreprise("Entreprise");
    employeur2.setId(2L);
    employeur2.setInternOffers(new ArrayList<>());
    employeur2.setNom("Nom");
    employeur2.setPassword("getEtudiantsWithCv");
    employeur2.setPhone("8605550118");
    employeur2.setPrenom("Prenom");
    employeur2.setProgramme(new Programme());
    employeur2.setRole(Role.student);

    File file3 = new File();
    file3.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    file3.setEtudiant(new Etudiant());
    file3.setFileName("getEtudiantsWithCv");
    file3.setId(2L);
    file3.setInternshipCandidates(new InternshipCandidates());
    file3.setIsAccepted(State.PENDING);

    OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
    offerReviewRequest2.setComment("Comment");
    offerReviewRequest2.setId(2L);
    offerReviewRequest2.setInternOffer(new InternOffer());
    offerReviewRequest2.setInternshipmanager(new Internshipmanager());

    Programme programme6 = new Programme();
    programme6.setDescription("getEtudiantsWithCv");
    programme6.setId(2L);
    programme6.setNom("Nom");

    InternOffer internOffer2 = new InternOffer();
    internOffer2.setDescription("getEtudiantsWithCv");
    internOffer2.setEmployeur(employeur2);
    internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
    internOffer2.setFile(file3);
    internOffer2.setId(2L);
    internOffer2.setInternshipCandidates(new ArrayList<>());
    internOffer2.setLocation("Location");
    internOffer2.setOfferReviewRequest(offerReviewRequest2);
    internOffer2.setProgramme(programme6);
    internOffer2.setSalaryByHour(0.5d);
    internOffer2.setSession("Session");
    internOffer2.setStartDate(LocalDate.of(1970, 1, 1));
    internOffer2.setState(State.PENDING);
    internOffer2.setStatus("Status");
    internOffer2.setTitle("Mr");

    Etudiant etudiant8 = new Etudiant();
    etudiant8.setActiveCv(new File());
    etudiant8.setCv(new ArrayList<>());
    etudiant8.setEmail("john.smith@example.org");
    etudiant8.setId(2L);
    etudiant8.setInternshipsCandidate(new ArrayList<>());
    etudiant8.setMatricule("Matricule");
    etudiant8.setNom("Nom");
    etudiant8.setPassword("getEtudiantsWithCv");
    etudiant8.setPhone("8605550118");
    etudiant8.setPrenom("Prenom");
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
    studentCv2.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    studentCv2.setEtudiant(etudiant8);
    studentCv2.setFileName("getEtudiantsWithCv");
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
    file4.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    file4.setEtudiant(etudiant6);
    file4.setFileName("getEtudiantsWithCv");
    file4.setId(2L);
    file4.setInternshipCandidates(internshipCandidates6);
    file4.setIsAccepted(State.PENDING);

    ArrayList<File> fileList = new ArrayList<>();
    fileList.add(file4);
    fileList.add(file2);
    Optional<List<File>> ofResult = Optional.of(fileList);
    when(fileEntityRepository.findAllByEtudiant_IdIs(anyLong())).thenReturn(ofResult);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student/{id}/cvs", 1L);
    MockMvcBuilders.standaloneSetup(etudiantController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content()
                    .string(
                            "[{\"id\":2,\"content\":\"QQFBAUEBQQE=\",\"fileName\":\"getEtudiantsWithCv\",\"isAccepted\":\"PENDING\",\"etudiant\":"
                                    + "{\"id\":2,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"phone\":\"8605550118\",\"email\":\"john.smith@example.org\",\"matricule"
                                    + "\":\"Matricule\",\"programme_id\":2,\"cv\":[],\"internships_id\":null}},{\"id\":1,\"content\":\"QQFBAUEBQQE=\","
                                    + "\"fileName\":\"foo.txt\",\"isAccepted\":\"ACCEPTED\",\"etudiant\":{\"id\":1,\"nom\":\"getEtudiantsWithCv\",\"prenom\":"
                                    + "\"getEtudiantsWithCv\",\"phone\":\"6625550144\",\"email\":\"jane.doe@example.org\",\"matricule\":\"getEtudiantsWithCv"
                                    + "\",\"programme_id\":1,\"cv\":[],\"internships_id\":null}}]"));
  }

  /**
   * Method under test: {@link EtudiantController#getOffersApplied(long)}
   */
  @Test
  void testGetOffersApplied() throws Exception {
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student/{id}/offersApplied", 1L);
    MockMvcBuilders.standaloneSetup(etudiantController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("[]"));
  }
}
