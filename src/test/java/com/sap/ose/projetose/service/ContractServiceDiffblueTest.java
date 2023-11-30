package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sap.ose.projetose.dto.ContractDto;
import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.Contract;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import com.sap.ose.projetose.modeles.Internshipmanager;
import com.sap.ose.projetose.modeles.OfferReviewRequest;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.modeles.Role;
import com.sap.ose.projetose.modeles.Stage;
import com.sap.ose.projetose.modeles.State;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ContractServiceDiffblueTest {
    @Autowired
    private ContractService contractService;

    /**
     * Method under test: {@link ContractService#getById(long)}
     */
    @Test
    void testGetById() {
        // Arrange, Act and Assert
        assertThrows(IllegalStateException.class, () -> contractService.getById(1L));
    }

    /**
     * Method under test: {@link ContractService#saveContractDto(ContractDto)}
     */
    @Test
    void testSaveContractDto() {
        // Arrange, Act and Assert
        assertThrows(IllegalStateException.class, () -> contractService.saveContractDto(new ContractDto()));
        assertThrows(IllegalStateException.class, () -> contractService.saveContractDto(null));
    }

    /**
     * Method under test: {@link ContractService#saveContractDto(ContractDto)}
     */
    @Test
    void testSaveContractDto2() {
        // Arrange
        ContractDto contractDto = new ContractDto();
        contractDto.setContent("Content");

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> contractService.saveContractDto(contractDto));
    }

    /**
     * Method under test: {@link ContractService#saveContractDto(ContractDto)}
     */
    @Test
    void testSaveContractDto3() {
        // Arrange
        EmployeurDto employeur = new EmployeurDto("Impossible de sauvegarder le contrat", 1L);

        EtudiantDto etudiantDto = new EtudiantDto();

        ContractDto contractDto = new ContractDto(1L, employeur, etudiantDto, new InternOfferDto(), true, true, true, 1L,
                "Not all who wander are lost", "foo.txt");
        contractDto.setContent("Content");

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> contractService.saveContractDto(contractDto));
    }

    /**
     * Method under test: {@link ContractService#saveContractStudentDto(ContractDto)}
     */
    @Test
    void testSaveContractStudentDto() {
        // Arrange, Act and Assert
        assertThrows(IllegalStateException.class, () -> contractService.saveContractStudentDto(new ContractDto()));
        assertThrows(IllegalStateException.class, () -> contractService.saveContractStudentDto(null));
    }

    /**
     * Method under test: {@link ContractService#saveContractStudentDto(ContractDto)}
     */
    @Test
    void testSaveContractStudentDto2() {
        // Arrange
        ContractDto contractDto = new ContractDto();
        contractDto.setContent("Content");

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> contractService.saveContractStudentDto(contractDto));
    }

    /**
     * Method under test: {@link ContractService#saveContractStudentDto(ContractDto)}
     */
    @Test
    void testSaveContractStudentDto3() {
        // Arrange
        EmployeurDto employeur = new EmployeurDto("Impossible de sauvegarder le contrat", 1L);

        EtudiantDto etudiantDto = new EtudiantDto();

        ContractDto contractDto = new ContractDto(1L, employeur, etudiantDto, new InternOfferDto(), true, true, true, 1L,
                "Not all who wander are lost", "foo.txt");
        contractDto.setContent("Content");

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> contractService.saveContractStudentDto(contractDto));
    }

    /**
     * Method under test: {@link ContractService#saveContractEmployerDto(ContractDto)}
     */
    @Test
    void testSaveContractEmployerDto() {
        // Arrange, Act and Assert
        assertThrows(IllegalStateException.class, () -> contractService.saveContractEmployerDto(new ContractDto()));
        assertThrows(IllegalStateException.class, () -> contractService.saveContractEmployerDto(null));
    }

    /**
     * Method under test: {@link ContractService#saveContractEmployerDto(ContractDto)}
     */
    @Test
    void testSaveContractEmployerDto2() {
        // Arrange
        ContractDto contractDto = new ContractDto();
        contractDto.setContent("Content");

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> contractService.saveContractEmployerDto(contractDto));
    }

    /**
     * Method under test: {@link ContractService#saveContractEmployerDto(ContractDto)}
     */
    @Test
    void testSaveContractEmployerDto3() {
        // Arrange
        EmployeurDto employeur = new EmployeurDto("Impossible de sauvegarder le contrat", 1L);

        EtudiantDto etudiantDto = new EtudiantDto();

        ContractDto contractDto = new ContractDto(1L, employeur, etudiantDto, new InternOfferDto(), true, true, true, 1L,
                "Not all who wander are lost", "foo.txt");
        contractDto.setContent("Content");

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> contractService.saveContractEmployerDto(contractDto));
    }

    /**
     * Method under test: {@link ContractService#createContract(Stage)}
     */
    @Test
    void testCreateContract() throws UnsupportedEncodingException {
        // Arrange
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

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");

        InternOffer internOffer = new InternOffer();
        internOffer.setDescription("The characteristics of someone or something");
        internOffer.setEmployeur(employeur2);
        internOffer.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer.setFile(file2);
        internOffer.setId(1L);
        internOffer.setInternshipCandidates(new ArrayList<>());
        internOffer.setLocation("Location");
        internOffer.setOfferReviewRequest(offerReviewRequest);
        internOffer.setProgramme(programme2);
        internOffer.setSalaryByHour(10.0d);
        internOffer.setSession("Session");
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setState(State.ACCEPTED);
        internOffer.setStatus("Status");
        internOffer.setTitle("Dr");

        File activeCv = new File();
        activeCv.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv.setEtudiant(new Etudiant());
        activeCv.setFileName("foo.txt");
        activeCv.setId(1L);
        activeCv.setInternshipCandidates(new InternshipCandidates());
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

        Contract contract = new Contract();
        contract.setEmployeur(employeur);
        contract.setFile(file);
        contract.setId(1L);
        contract.setInternOffer(internOffer);
        contract.setSignatureEmployer(true);
        contract.setSignatureInternShipManager(true);
        contract.setSignatureStudent(true);
        contract.setStudent(student);

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        Employeur employeur3 = new Employeur();
        employeur3.setEmail("jane.doe@example.org");
        employeur3.setEntreprise("Entreprise");
        employeur3.setId(1L);
        employeur3.setInternOffers(new ArrayList<>());
        employeur3.setNom("Nom");
        employeur3.setPassword("iloveyou");
        employeur3.setPhone("6625550144");
        employeur3.setPrenom("Prenom");
        employeur3.setProgramme(programme4);
        employeur3.setRole(Role.employer);

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        Employeur employeur4 = new Employeur();
        employeur4.setEmail("jane.doe@example.org");
        employeur4.setEntreprise("Entreprise");
        employeur4.setId(1L);
        employeur4.setInternOffers(new ArrayList<>());
        employeur4.setNom("Nom");
        employeur4.setPassword("iloveyou");
        employeur4.setPhone("6625550144");
        employeur4.setPrenom("Prenom");
        employeur4.setProgramme(programme5);
        employeur4.setRole(Role.employer);

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

        File file3 = new File();
        file3.setContent("AXAXAXAX".getBytes("UTF-8"));
        file3.setEtudiant(etudiant2);
        file3.setFileName("foo.txt");
        file3.setId(1L);
        file3.setInternshipCandidates(internshipCandidates2);
        file3.setIsAccepted(State.ACCEPTED);

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

        OfferReviewRequest offerReviewRequest2 = new OfferReviewRequest();
        offerReviewRequest2.setComment("Comment");
        offerReviewRequest2.setId(1L);
        offerReviewRequest2.setInternOffer(internOffer2);
        offerReviewRequest2.setInternshipmanager(internshipmanager);

        Programme programme6 = new Programme();
        programme6.setDescription("The characteristics of someone or something");
        programme6.setId(1L);
        programme6.setNom("Nom");

        InternOffer offer = new InternOffer();
        offer.setDescription("The characteristics of someone or something");
        offer.setEmployeur(employeur4);
        offer.setEndDate(LocalDate.of(1970, 1, 1));
        offer.setFile(file3);
        offer.setId(1L);
        offer.setInternshipCandidates(new ArrayList<>());
        offer.setLocation("Location");
        offer.setOfferReviewRequest(offerReviewRequest2);
        offer.setProgramme(programme6);
        offer.setSalaryByHour(10.0d);
        offer.setSession("Session");
        offer.setStartDate(LocalDate.of(1970, 1, 1));
        offer.setState(State.ACCEPTED);
        offer.setStatus("Status");
        offer.setTitle("Dr");

        Etudiant etudiant3 = new Etudiant();
        etudiant3.setActiveCv(new File());
        etudiant3.setCv(new ArrayList<>());
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

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(new Etudiant());
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(1L);
        internshipCandidates3.setInternOffer(new InternOffer());
        internshipCandidates3.setState(State.ACCEPTED);
        internshipCandidates3.setStudentCv(new File());

        File activeCv2 = new File();
        activeCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv2.setEtudiant(etudiant3);
        activeCv2.setFileName("foo.txt");
        activeCv2.setId(1L);
        activeCv2.setInternshipCandidates(internshipCandidates3);
        activeCv2.setIsAccepted(State.ACCEPTED);

        Programme programme7 = new Programme();
        programme7.setDescription("The characteristics of someone or something");
        programme7.setId(1L);
        programme7.setNom("Nom");

        Etudiant student2 = new Etudiant();
        student2.setActiveCv(activeCv2);
        student2.setCv(new ArrayList<>());
        student2.setEmail("jane.doe@example.org");
        student2.setId(1L);
        student2.setInternshipsCandidate(new ArrayList<>());
        student2.setMatricule("Matricule");
        student2.setNom("Nom");
        student2.setPassword("iloveyou");
        student2.setPhone("6625550144");
        student2.setPrenom("Prenom");
        student2.setProgramme(programme7);
        student2.setRole(Role.employer);

        Stage stage = new Stage();
        stage.setContract(contract);
        stage.setEmployeur(employeur3);
        stage.setId(1L);
        stage.setOffer(offer);
        stage.setStateEmployeur(State.ACCEPTED);
        stage.setStateStudent(State.ACCEPTED);
        stage.setStudent(student2);

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> contractService.createContract(stage));
    }

    /**
     * Method under test: {@link ContractService#findById(long)}
     */
    @Test
    void testFindById() {
        // Arrange, Act and Assert
        assertThrows(IllegalStateException.class, () -> contractService.findById(1L));
    }

    /**
     * Method under test: {@link ContractService#getAllByStudentID(long, int, int, Sort)}
     */
    @Test
    void testGetAllByStudentID() {
        // Arrange
        Sort sort = Sort.unsorted();

        // Act and Assert
        assertTrue(contractService.getAllByStudentID(1L, 1, 3, sort).toList().isEmpty());
        assertTrue(sort.toList().isEmpty());
    }

    /**
     * Method under test: {@link ContractService#getAllByStudentID(long, int, int, Sort)}
     */
    @Test
    void testGetAllByStudentID2() {
        // Arrange
        Sort sort = Sort.unsorted();

        // Act and Assert
        assertTrue(contractService.getAllByStudentID(2L, 1, 3, sort).toList().isEmpty());
        assertTrue(sort.toList().isEmpty());
    }

    /**
     * Method under test: {@link ContractService#getAllByStudentID(long, int, int, Sort)}
     */
    @Test
    void testGetAllByStudentID3() {
        // Arrange, Act and Assert
        assertThrows(ServiceException.class, () -> contractService.getAllByStudentID(1L, -1, 3, Sort.unsorted()));
    }
}
