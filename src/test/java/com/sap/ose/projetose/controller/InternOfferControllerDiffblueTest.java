package com.sap.ose.projetose.controller;

import static org.mockito.Mockito.when;

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
import com.sap.ose.projetose.repository.ProgrammeRepository;
import com.sap.ose.projetose.service.EmployeurService;
import com.sap.ose.projetose.service.InternOfferService;
import com.sap.ose.projetose.service.ProgrammeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {InternOfferController.class, InternOfferService.class, ProgrammeService.class,
        EmployeurService.class})
@ExtendWith(SpringExtension.class)
class InternOfferControllerDiffblueTest {
    @MockBean
    private EmployeurRepository employeurRepository;

    @Autowired
    private InternOfferController internOfferController;

    @MockBean
    private InternOfferRepository internOfferRepository;

    @MockBean
    private ProgrammeRepository programmeRepository;

    /**
     * Method under test: {@link InternOfferController#getAllOfferSeasons()}
     */
    @Test
    void testGetAllOfferSeasons() throws Exception {
        when(internOfferRepository.getAllOfferSeasons()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/interOfferJob/getAllPossibleSeasons");
        MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link InternOfferController#getAllOfferSeasons()}
     */
    @Test
    void testGetAllOfferSeasons2() throws Exception {
        when(internOfferRepository.getAllOfferSeasons()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link InternOfferController#getOfferApprovedSeasons()}
     */
    @Test
    void testGetOfferApprovedSeasons() throws Exception {
        when(internOfferRepository.getOfferApprovedSeasons()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/interOfferJob/getOfferApprovedSeasons");
        MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link InternOfferController#getAllOffers()}
     */
    @Test
    void testGetAllOffers() throws Exception {
        when(internOfferRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/interOfferJob/getAllOffers");
        MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link InternOfferController#getAllOffers()}
     */
    @Test
    void testGetAllOffers2() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/interOfferJob/getAllOffers");
        MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"title\":\"Dr\",\"location\":\"Location\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"salaryByHour\":10.0,\"startDate\":\"1970-01-01\",\"endDate\":\"1970-01-01\",\"internshipCandidates"
                                        + "\":[],\"programmeId\":1,\"file\":{\"id\":1,\"content\":\"QVhBWEFYQVg=\",\"fileName\":\"foo.txt\",\"isAccepted\":\"ACCEPTED"
                                        + "\"},\"employeurId\":1,\"programmeNom\":\"Nom\",\"employeurPrenom\":\"Prenom\",\"employeurNom\":\"Nom\",\"employeurEntreprise"
                                        + "\":\"Entreprise\",\"offerReviewRequestId\":1,\"state\":\"ACCEPTED\",\"session\":\"Session\"}]"));
    }

    /**
     * Method under test: {@link InternOfferController#getAllOffers(String)}
     */
    @Test
    void testGetAllOffers3() throws Exception {
        when(internOfferRepository.findOfferBySeason(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/interOfferJob/{session}/all",
                "Session");
        MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link InternOfferController#getAllOffers(String)}
     */
    @Test
    void testGetAllOffers4() throws Exception {
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
        when(internOfferRepository.findOfferBySeason(Mockito.<String>any())).thenReturn(internOfferList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/interOfferJob/{session}/all",
                "Session");
        MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"title\":\"Dr\",\"location\":\"Location\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"salaryByHour\":10.0,\"startDate\":\"1970-01-01\",\"endDate\":\"1970-01-01\",\"internshipCandidates"
                                        + "\":[],\"programmeId\":1,\"file\":{\"id\":1,\"content\":\"QVhBWEFYQVg=\",\"fileName\":\"foo.txt\",\"isAccepted\":\"ACCEPTED"
                                        + "\"},\"employeurId\":1,\"programmeNom\":\"Nom\",\"employeurPrenom\":\"Prenom\",\"employeurNom\":\"Nom\",\"employeurEntreprise"
                                        + "\":\"Entreprise\",\"offerReviewRequestId\":1,\"state\":\"ACCEPTED\",\"session\":\"Session\"}]"));
    }

    /**
     * Method under test: {@link InternOfferController#getEmployeurOffers(String)}
     */
    @Test
    void testGetEmployeurOffers() throws Exception {
        when(internOfferRepository.findInternOffersById(Mockito.<Long>any())).thenReturn(new ArrayList<>());

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/interOfferJob/{email}/getOffers",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link InternOfferController#getEmployeurOffers(String)}
     */
    @Test
    void testGetEmployeurOffers2() throws Exception {
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
        when(internOfferRepository.findInternOffersById(Mockito.<Long>any())).thenReturn(internOfferList);

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/interOfferJob/{email}/getOffers",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"title\":\"Dr\",\"location\":\"Location\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"salaryByHour\":10.0,\"startDate\":\"1970-01-01\",\"endDate\":\"1970-01-01\",\"internshipCandidates"
                                        + "\":[],\"programmeId\":1,\"file\":{\"id\":1,\"content\":\"QVhBWEFYQVg=\",\"fileName\":\"foo.txt\",\"isAccepted\":\"ACCEPTED"
                                        + "\"},\"employeurId\":1,\"programmeNom\":\"Nom\",\"employeurPrenom\":\"Prenom\",\"employeurNom\":\"Nom\",\"employeurEntreprise"
                                        + "\":\"Entreprise\",\"offerReviewRequestId\":1,\"state\":\"ACCEPTED\",\"session\":\"Session\"}]"));
    }

    /**
     * Method under test: {@link InternOfferController#getEmployeurOffersBySeason(String, String)}
     */
    @Test
    void testGetEmployeurOffersBySeason() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/interOfferJob/{email}/season/{selectedOption}", "jane.doe@example.org", "Selected Option");
        MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link InternOfferController#getEmployeurOffersBySeason(String, String)}
     */
    @Test
    void testGetEmployeurOffersBySeason2() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/interOfferJob/{email}/season/{selectedOption}", "jane.doe@example.org", "Selected Option");
        MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"title\":\"Dr\",\"location\":\"Location\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"salaryByHour\":10.0,\"startDate\":\"1970-01-01\",\"endDate\":\"1970-01-01\",\"internshipCandidates"
                                        + "\":[],\"programmeId\":1,\"file\":{\"id\":1,\"content\":\"QVhBWEFYQVg=\",\"fileName\":\"foo.txt\",\"isAccepted\":\"ACCEPTED"
                                        + "\"},\"employeurId\":1,\"programmeNom\":\"Nom\",\"employeurPrenom\":\"Prenom\",\"employeurNom\":\"Nom\",\"employeurEntreprise"
                                        + "\":\"Entreprise\",\"offerReviewRequestId\":1,\"state\":\"ACCEPTED\",\"session\":\"Session\"}]"));
    }

    /**
     * Method under test: {@link InternOfferController#getEmployeurSeasons(String)}
     */
    @Test
    void testGetEmployeurSeasons() throws Exception {
        when(internOfferRepository.findEmployeurOffersSeasons(Mockito.<Long>any())).thenReturn(new ArrayList<>());

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/interOfferJob/{email}/getSeason",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link InternOfferController#getInternOfferJob(String)}
     */
    @Test
    void testGetInternOfferJob() throws Exception {
        when(internOfferRepository.findInternOffersById(Mockito.<Long>any())).thenReturn(new ArrayList<>());

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/interOfferJob/offersEmployeur/{email}", "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link InternOfferController#getInternOfferJob(String)}
     */
    @Test
    void testGetInternOfferJob2() throws Exception {
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
        when(internOfferRepository.findInternOffersById(Mockito.<Long>any())).thenReturn(internOfferList);

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/interOfferJob/offersEmployeur/{email}", "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"title\":\"Dr\",\"location\":\"Location\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"salaryByHour\":10.0,\"startDate\":\"1970-01-01\",\"endDate\":\"1970-01-01\",\"internshipCandidates"
                                        + "\":[],\"programmeId\":1,\"file\":{\"id\":1,\"content\":\"QVhBWEFYQVg=\",\"fileName\":\"foo.txt\",\"isAccepted\":\"ACCEPTED"
                                        + "\"},\"employeurId\":1,\"programmeNom\":\"Nom\",\"employeurPrenom\":\"Prenom\",\"employeurNom\":\"Nom\",\"employeurEntreprise"
                                        + "\":\"Entreprise\",\"offerReviewRequestId\":1,\"state\":\"ACCEPTED\",\"session\":\"Session\"}]"));
    }

    /**
     * Method under test: {@link InternOfferController#getInternOfferJob(String, int, int, String, String, String)}
     */
    @Test
    void testGetInternOfferJob3() throws Exception {
        when(internOfferRepository.findAllById(Mockito.<Long>any(), Mockito.<String>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/interOfferJob/OffersEmp/{email}",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":[],\"pageable\":\"INSTANCE\",\"totalPages\":1,\"totalElements\":0,\"last\":true,\"numberOfElements"
                                        + "\":0,\"first\":true,\"size\":0,\"number\":0,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"empty"
                                        + "\":true}"));
    }

    /**
     * Method under test: {@link InternOfferController#getInternOfferJob(String, int, int, String, String, String)}
     */
    @Test
    void testGetInternOfferJob4() throws Exception {
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
        activeCv.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
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

        File file = new File();
        file.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
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
        file2.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
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

        ArrayList<InternOffer> content = new ArrayList<>();
        content.add(internOffer3);
        PageImpl<InternOffer> pageImpl = new PageImpl<>(content);
        when(internOfferRepository.findAllById(Mockito.<Long>any(), Mockito.<String>any(), Mockito.<Pageable>any()))
                .thenReturn(pageImpl);

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/interOfferJob/OffersEmp/{email}",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":[{\"id\":1,\"title\":\"Dr\",\"location\":\"Location\",\"description\":\"The characteristics of someone"
                                        + " or something\",\"salaryByHour\":10.0,\"startDate\":\"1970-01-01\",\"endDate\":\"1970-01-01\",\"internshipCandidates"
                                        + "\":[],\"programmeId\":1,\"file\":{\"id\":1,\"content\":\"QQFBAUEBQQE=\",\"fileName\":\"foo.txt\",\"isAccepted\":\"ACCEPTED"
                                        + "\"},\"employeurId\":1,\"programmeNom\":\"Nom\",\"employeurPrenom\":\"Prenom\",\"employeurNom\":\"Nom\",\"employeurEntreprise"
                                        + "\":\"Entreprise\",\"offerReviewRequestId\":1,\"state\":\"ACCEPTED\",\"session\":\"Session\"}],\"pageable\":\"INSTANCE"
                                        + "\",\"totalPages\":1,\"totalElements\":1,\"last\":true,\"numberOfElements\":1,\"first\":true,\"size\":1,\"number\":0"
                                        + ",\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"empty\":false}"));
    }

    /**
     * Method under test: {@link InternOfferController#getInternOfferJob(String, int, int, String, String, String)}
     */
    @Test
    void testGetInternOfferJob5() throws Exception {
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
        activeCv.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
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

        File file = new File();
        file.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
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
        file2.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
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

        Programme programme6 = new Programme();
        programme6.setDescription("Description");
        programme6.setId(2L);
        programme6.setNom("com.sap.ose.projetose.modeles.Programme");

        Employeur employeur3 = new Employeur();
        employeur3.setEmail("john.smith@example.org");
        employeur3.setEntreprise("com.sap.ose.projetose.modeles.Employeur");
        employeur3.setId(2L);
        employeur3.setInternOffers(new ArrayList<>());
        employeur3.setNom("com.sap.ose.projetose.modeles.Employeur");
        employeur3.setPassword("Password");
        employeur3.setPhone("8605550118");
        employeur3.setPrenom("com.sap.ose.projetose.modeles.Employeur");
        employeur3.setProgramme(programme6);
        employeur3.setRole(Role.student);

        File activeCv2 = new File();
        activeCv2.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
        activeCv2.setEtudiant(new Etudiant());
        activeCv2.setFileName("File Name");
        activeCv2.setId(2L);
        activeCv2.setInternshipCandidates(new InternshipCandidates());
        activeCv2.setIsAccepted(State.PENDING);

        Programme programme7 = new Programme();
        programme7.setDescription("Description");
        programme7.setId(2L);
        programme7.setNom("com.sap.ose.projetose.modeles.Programme");

        Etudiant etudiant3 = new Etudiant();
        etudiant3.setActiveCv(activeCv2);
        etudiant3.setCv(new ArrayList<>());
        etudiant3.setEmail("john.smith@example.org");
        etudiant3.setId(2L);
        etudiant3.setInternshipsCandidate(new ArrayList<>());
        etudiant3.setMatricule("com.sap.ose.projetose.modeles.Etudiant");
        etudiant3.setNom("com.sap.ose.projetose.modeles.Etudiant");
        etudiant3.setPassword("Password");
        etudiant3.setPhone("8605550118");
        etudiant3.setPrenom("com.sap.ose.projetose.modeles.Etudiant");
        etudiant3.setProgramme(programme7);
        etudiant3.setRole(Role.student);

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

        InternOffer internOffer4 = new InternOffer();
        internOffer4.setDescription("Description");
        internOffer4.setEmployeur(new Employeur());
        internOffer4.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer4.setFile(new File());
        internOffer4.setId(2L);
        internOffer4.setInternshipCandidates(new ArrayList<>());
        internOffer4.setLocation("com.sap.ose.projetose.modeles.InternOffer");
        internOffer4.setOfferReviewRequest(new OfferReviewRequest());
        internOffer4.setProgramme(new Programme());
        internOffer4.setSalaryByHour(0.5d);
        internOffer4.setSession("com.sap.ose.projetose.modeles.InternOffer");
        internOffer4.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer4.setState(State.PENDING);
        internOffer4.setStatus("com.sap.ose.projetose.modeles.InternOffer");
        internOffer4.setTitle("Mr");

        File studentCv2 = new File();
        studentCv2.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
        studentCv2.setEtudiant(new Etudiant());
        studentCv2.setFileName("File Name");
        studentCv2.setId(2L);
        studentCv2.setInternshipCandidates(new InternshipCandidates());
        studentCv2.setIsAccepted(State.PENDING);

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(etudiant4);
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(2L);
        internshipCandidates2.setInternOffer(internOffer4);
        internshipCandidates2.setState(State.PENDING);
        internshipCandidates2.setStudentCv(studentCv2);

        File file3 = new File();
        file3.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
        file3.setEtudiant(etudiant3);
        file3.setFileName("File Name");
        file3.setId(2L);
        file3.setInternshipCandidates(internshipCandidates2);
        file3.setIsAccepted(State.PENDING);

        Employeur employeur4 = new Employeur();
        employeur4.setEmail("john.smith@example.org");
        employeur4.setEntreprise("com.sap.ose.projetose.modeles.Employeur");
        employeur4.setId(2L);
        employeur4.setInternOffers(new ArrayList<>());
        employeur4.setNom("com.sap.ose.projetose.modeles.Employeur");
        employeur4.setPassword("Password");
        employeur4.setPhone("8605550118");
        employeur4.setPrenom("com.sap.ose.projetose.modeles.Employeur");
        employeur4.setProgramme(new Programme());
        employeur4.setRole(Role.student);

        File file4 = new File();
        file4.setContent(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
        file4.setEtudiant(new Etudiant());
        file4.setFileName("File Name");
        file4.setId(2L);
        file4.setInternshipCandidates(new InternshipCandidates());
        file4.setIsAccepted(State.PENDING);

        OfferReviewRequest offerReviewRequest3 = new OfferReviewRequest();
        offerReviewRequest3.setComment("com.sap.ose.projetose.modeles.OfferReviewRequest");
        offerReviewRequest3.setId(2L);
        offerReviewRequest3.setInternOffer(new InternOffer());
        offerReviewRequest3.setInternshipmanager(new Internshipmanager());

        Programme programme8 = new Programme();
        programme8.setDescription("Description");
        programme8.setId(2L);
        programme8.setNom("com.sap.ose.projetose.modeles.Programme");

        InternOffer internOffer5 = new InternOffer();
        internOffer5.setDescription("Description");
        internOffer5.setEmployeur(employeur4);
        internOffer5.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer5.setFile(file4);
        internOffer5.setId(2L);
        internOffer5.setInternshipCandidates(new ArrayList<>());
        internOffer5.setLocation("com.sap.ose.projetose.modeles.InternOffer");
        internOffer5.setOfferReviewRequest(offerReviewRequest3);
        internOffer5.setProgramme(programme8);
        internOffer5.setSalaryByHour(0.5d);
        internOffer5.setSession("com.sap.ose.projetose.modeles.InternOffer");
        internOffer5.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer5.setState(State.PENDING);
        internOffer5.setStatus("com.sap.ose.projetose.modeles.InternOffer");
        internOffer5.setTitle("Mr");

        Programme programme9 = new Programme();
        programme9.setDescription("Description");
        programme9.setId(2L);
        programme9.setNom("com.sap.ose.projetose.modeles.Programme");

        Internshipmanager internshipmanager2 = new Internshipmanager();
        internshipmanager2.setEmail("john.smith@example.org");
        internshipmanager2.setId(2L);
        internshipmanager2.setNom("com.sap.ose.projetose.modeles.Internshipmanager");
        internshipmanager2.setPassword("Password");
        internshipmanager2.setPhone("8605550118");
        internshipmanager2.setPrenom("com.sap.ose.projetose.modeles.Internshipmanager");
        internshipmanager2.setProgramme(programme9);
        internshipmanager2.setRole(Role.student);

        OfferReviewRequest offerReviewRequest4 = new OfferReviewRequest();
        offerReviewRequest4.setComment("com.sap.ose.projetose.modeles.OfferReviewRequest");
        offerReviewRequest4.setId(2L);
        offerReviewRequest4.setInternOffer(internOffer5);
        offerReviewRequest4.setInternshipmanager(internshipmanager2);

        Programme programme10 = new Programme();
        programme10.setDescription("Description");
        programme10.setId(2L);
        programme10.setNom("com.sap.ose.projetose.modeles.Programme");

        InternOffer internOffer6 = new InternOffer();
        internOffer6.setDescription("Description");
        internOffer6.setEmployeur(employeur3);
        internOffer6.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer6.setFile(file3);
        internOffer6.setId(2L);
        internOffer6.setInternshipCandidates(new ArrayList<>());
        internOffer6.setLocation("com.sap.ose.projetose.modeles.InternOffer");
        internOffer6.setOfferReviewRequest(offerReviewRequest4);
        internOffer6.setProgramme(programme10);
        internOffer6.setSalaryByHour(0.5d);
        internOffer6.setSession("com.sap.ose.projetose.modeles.InternOffer");
        internOffer6.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer6.setState(State.PENDING);
        internOffer6.setStatus("com.sap.ose.projetose.modeles.InternOffer");
        internOffer6.setTitle("Mr");

        ArrayList<InternOffer> content = new ArrayList<>();
        content.add(internOffer6);
        content.add(internOffer3);
        PageImpl<InternOffer> pageImpl = new PageImpl<>(content);
        when(internOfferRepository.findAllById(Mockito.<Long>any(), Mockito.<String>any(), Mockito.<Pageable>any()))
                .thenReturn(pageImpl);

        Programme programme11 = new Programme();
        programme11.setDescription("The characteristics of someone or something");
        programme11.setId(1L);
        programme11.setNom("Nom");

        Employeur employeur5 = new Employeur();
        employeur5.setEmail("jane.doe@example.org");
        employeur5.setEntreprise("Entreprise");
        employeur5.setId(1L);
        employeur5.setInternOffers(new ArrayList<>());
        employeur5.setNom("Nom");
        employeur5.setPassword("iloveyou");
        employeur5.setPhone("6625550144");
        employeur5.setPrenom("Prenom");
        employeur5.setProgramme(programme11);
        employeur5.setRole(Role.employer);
        Optional<Employeur> ofResult = Optional.of(employeur5);
        when(employeurRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/interOfferJob/OffersEmp/{email}",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":[{\"id\":2,\"title\":\"Mr\",\"location\":\"com.sap.ose.projetose.modeles.InternOffer\",\"description"
                                        + "\":\"Description\",\"salaryByHour\":0.5,\"startDate\":\"1970-01-01\",\"endDate\":\"1970-01-01\",\"internshipCandidates"
                                        + "\":[],\"programmeId\":2,\"file\":{\"id\":2,\"content\":\"QQFBAUEBQQE=\",\"fileName\":\"File Name\",\"isAccepted\":"
                                        + "\"PENDING\"},\"employeurId\":2,\"programmeNom\":\"com.sap.ose.projetose.modeles.Programme\",\"employeurPrenom"
                                        + "\":\"com.sap.ose.projetose.modeles.Employeur\",\"employeurNom\":\"com.sap.ose.projetose.modeles.Employeur\""
                                        + ",\"employeurEntreprise\":\"com.sap.ose.projetose.modeles.Employeur\",\"offerReviewRequestId\":2,\"state\":"
                                        + "\"PENDING\",\"session\":\"com.sap.ose.projetose.modeles.InternOffer\"},{\"id\":1,\"title\":\"Dr\",\"location\":"
                                        + "\"Location\",\"description\":\"The characteristics of someone or something\",\"salaryByHour\":10.0,\"startDate"
                                        + "\":\"1970-01-01\",\"endDate\":\"1970-01-01\",\"internshipCandidates\":[],\"programmeId\":1,\"file\":{\"id\":1,\"content"
                                        + "\":\"QQFBAUEBQQE=\",\"fileName\":\"foo.txt\",\"isAccepted\":\"ACCEPTED\"},\"employeurId\":1,\"programmeNom\":\"Nom\","
                                        + "\"employeurPrenom\":\"Prenom\",\"employeurNom\":\"Nom\",\"employeurEntreprise\":\"Entreprise\",\"offerReviewRequestId"
                                        + "\":1,\"state\":\"ACCEPTED\",\"session\":\"Session\"}],\"pageable\":\"INSTANCE\",\"totalPages\":1,\"totalElements\":2,"
                                        + "\"last\":true,\"numberOfElements\":2,\"first\":true,\"size\":2,\"number\":0,\"sort\":{\"sorted\":false,\"unsorted\""
                                        + ":true,\"empty\":true},\"empty\":false}"));
    }
}
