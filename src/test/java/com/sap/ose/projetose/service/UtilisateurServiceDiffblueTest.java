package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
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
import com.sap.ose.projetose.modeles.Utilisateur;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;

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

@ContextConfiguration(classes = {UtilisateurService.class})
@ExtendWith(SpringExtension.class)
class UtilisateurServiceDiffblueTest {
    @MockBean
    private EmployeurRepository employeurRepository;

    @MockBean
    private EtudiantRepository etudiantRepository;

    @MockBean
    private InternshipmanagerRepository internshipmanagerRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    /**
     * Method under test: {@link UtilisateurService#getUserByEmail(String)}
     */
    @Test
    void testGetUserByEmail() {
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
        Utilisateur actualUserByEmail = utilisateurService.getUserByEmail("jane.doe@example.org");
        verify(employeurRepository).findByEmail(Mockito.<String>any());
        assertSame(employeur, actualUserByEmail);
    }

    /**
     * Method under test: {@link UtilisateurService#getUserByEmail(String)}
     */
    @Test
    void testGetUserByEmail2() throws UnsupportedEncodingException {
        Optional<Employeur> emptyResult = Optional.empty();
        when(employeurRepository.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);

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
        when(etudiantRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        Utilisateur actualUserByEmail = utilisateurService.getUserByEmail("jane.doe@example.org");
        verify(employeurRepository).findByEmail(Mockito.<String>any());
        verify(etudiantRepository).findByEmail(Mockito.<String>any());
        assertSame(etudiant3, actualUserByEmail);
    }

    /**
     * Method under test: {@link UtilisateurService#getUserById(long)}
     */
    @Test
    void testGetUserById() {
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
        when(employeurRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Utilisateur actualUserById = utilisateurService.getUserById(1L);
        verify(employeurRepository).findById(Mockito.<Long>any());
        assertSame(employeur, actualUserById);
    }

    /**
     * Method under test: {@link UtilisateurService#getUserById(long)}
     */
    @Test
    void testGetUserById2() throws UnsupportedEncodingException {
        Optional<Employeur> emptyResult = Optional.empty();
        when(employeurRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

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
        Utilisateur actualUserById = utilisateurService.getUserById(1L);
        verify(employeurRepository).findById(Mockito.<Long>any());
        verify(etudiantRepository).findById(Mockito.<Long>any());
        assertSame(etudiant3, actualUserById);
    }

    /**
     * Method under test: {@link UtilisateurService#getUserByEmailRole(String)}
     */
    @Test
    void testGetUserByEmailRole() {
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
        Role actualUserByEmailRole = utilisateurService.getUserByEmailRole("jane.doe@example.org");
        verify(employeurRepository).findByEmail(Mockito.<String>any());
        assertEquals(Role.employer, actualUserByEmailRole);
    }

    /**
     * Method under test: {@link UtilisateurService#getUserByEmailRole(String)}
     */
    @Test
    void testGetUserByEmailRole2() {
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
        employeur.setRole(Role.student);
        Optional<Employeur> ofResult = Optional.of(employeur);
        when(employeurRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        Role actualUserByEmailRole = utilisateurService.getUserByEmailRole("jane.doe@example.org");
        verify(employeurRepository).findByEmail(Mockito.<String>any());
        assertEquals(Role.student, actualUserByEmailRole);
    }

    /**
     * Method under test: {@link UtilisateurService#getUserByEmailRole(String)}
     */
    @Test
    void testGetUserByEmailRole3() {
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
        employeur.setRole(Role.internshipmanager);
        Optional<Employeur> ofResult = Optional.of(employeur);
        when(employeurRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        Role actualUserByEmailRole = utilisateurService.getUserByEmailRole("jane.doe@example.org");
        verify(employeurRepository).findByEmail(Mockito.<String>any());
        assertEquals(Role.internshipmanager, actualUserByEmailRole);
    }

    /**
     * Method under test: {@link UtilisateurService#getUserByEmailRole(String)}
     */
    @Test
    void testGetUserByEmailRole4() throws UnsupportedEncodingException {
        Optional<Employeur> emptyResult = Optional.empty();
        when(employeurRepository.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);

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
        when(etudiantRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        Role actualUserByEmailRole = utilisateurService.getUserByEmailRole("jane.doe@example.org");
        verify(employeurRepository).findByEmail(Mockito.<String>any());
        verify(etudiantRepository).findByEmail(Mockito.<String>any());
        assertEquals(Role.employer, actualUserByEmailRole);
    }

    /**
     * Method under test: {@link UtilisateurService#findAllEtudiant()}
     */
    @Test
    void testFindAllEtudiant() {
        ArrayList<Etudiant> etudiantList = new ArrayList<>();
        when(etudiantRepository.findAll()).thenReturn(etudiantList);
        List<Etudiant> actualFindAllEtudiantResult = utilisateurService.findAllEtudiant();
        verify(etudiantRepository).findAll();
        assertTrue(actualFindAllEtudiantResult.isEmpty());
        assertSame(etudiantList, actualFindAllEtudiantResult);
    }

    /**
     * Method under test: {@link UtilisateurService#findAllEmployeur()}
     */
    @Test
    void testFindAllEmployeur() {
        ArrayList<Employeur> employeurList = new ArrayList<>();
        when(employeurRepository.findAll()).thenReturn(employeurList);
        List<Employeur> actualFindAllEmployeurResult = utilisateurService.findAllEmployeur();
        verify(employeurRepository).findAll();
        assertTrue(actualFindAllEmployeurResult.isEmpty());
        assertSame(employeurList, actualFindAllEmployeurResult);
    }

    /**
     * Method under test: {@link UtilisateurService#findAllManagers()}
     */
    @Test
    void testFindAllManagers() {
        ArrayList<Internshipmanager> internshipmanagerList = new ArrayList<>();
        when(internshipmanagerRepository.findAll()).thenReturn(internshipmanagerList);
        List<Internshipmanager> actualFindAllManagersResult = utilisateurService.findAllManagers();
        verify(internshipmanagerRepository).findAll();
        assertTrue(actualFindAllManagersResult.isEmpty());
        assertSame(internshipmanagerList, actualFindAllManagersResult);
    }

    /**
     * Method under test: {@link UtilisateurService#findAllEtudiantByProgram(long)}
     */
    @Test
    void testFindAllEtudiantByProgram() {
        ArrayList<Etudiant> etudiantList = new ArrayList<>();
        when(etudiantRepository.findAllEtudiantByProgram(anyLong())).thenReturn(etudiantList);
        List<Etudiant> actualFindAllEtudiantByProgramResult = utilisateurService.findAllEtudiantByProgram(1L);
        verify(etudiantRepository).findAllEtudiantByProgram(anyLong());
        assertTrue(actualFindAllEtudiantByProgramResult.isEmpty());
        assertSame(etudiantList, actualFindAllEtudiantByProgramResult);
    }

    /**
     * Method under test: {@link UtilisateurService#findAllEmployeurByProgram(long)}
     */
    @Test
    void testFindAllEmployeurByProgram() {
        ArrayList<Employeur> employeurList = new ArrayList<>();
        when(employeurRepository.findAllEmployeurByProgram(anyLong())).thenReturn(employeurList);
        List<Employeur> actualFindAllEmployeurByProgramResult = utilisateurService.findAllEmployeurByProgram(1L);
        verify(employeurRepository).findAllEmployeurByProgram(anyLong());
        assertTrue(actualFindAllEmployeurByProgramResult.isEmpty());
        assertSame(employeurList, actualFindAllEmployeurByProgramResult);
    }
}
