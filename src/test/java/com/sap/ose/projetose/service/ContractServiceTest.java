package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sap.ose.projetose.dto.ContractDto;
import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.FileDto;
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
import com.sap.ose.projetose.modeles.TemplateContract;
import com.sap.ose.projetose.repository.ContractRepository;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.FileEntityRepository;
import com.sap.ose.projetose.repository.InternOfferRepository;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import com.sap.ose.projetose.repository.NotificationRepository;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import com.sap.ose.projetose.repository.TemplateContractRepository;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ContractService.class})
@ExtendWith(SpringExtension.class)
class ContractServiceTest {
    @MockBean
    private ContractRepository contractRepository;

    @Autowired
    private ContractService contractService;

    @MockBean
    private EmployeurService employeurService;

    @MockBean
    private EtudiantService etudiantService;

    @MockBean
    private FileService fileService;

    @MockBean
    private InternOfferService internOfferService;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private TemplateContractService templateContractService;

    /**
     * Method under test: {@link ContractService#getById(long)}
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

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(programme3);
        employeur2.setRole(Role.employer);

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

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(new Etudiant());
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(new InternOffer());
        internshipCandidates2.setState(State.ACCEPTED);
        internshipCandidates2.setStudentCv(new File());

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(etudiant3);
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(internshipCandidates2);
        file2.setIsAccepted(State.ACCEPTED);

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

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur2);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file2);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest);
        internOffer3.setProgramme(programme4);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setSession("Session");
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

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

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(new Etudiant());
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(1L);
        internshipCandidates3.setInternOffer(new InternOffer());
        internshipCandidates3.setState(State.ACCEPTED);
        internshipCandidates3.setStudentCv(new File());

        File activeCv2 = new File();
        activeCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv2.setEtudiant(etudiant4);
        activeCv2.setFileName("foo.txt");
        activeCv2.setId(1L);
        activeCv2.setInternshipCandidates(internshipCandidates3);
        activeCv2.setIsAccepted(State.ACCEPTED);

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        Etudiant student = new Etudiant();
        student.setActiveCv(activeCv2);
        student.setCv(new ArrayList<>());
        student.setEmail("jane.doe@example.org");
        student.setId(1L);
        student.setInternshipsCandidate(new ArrayList<>());
        student.setMatricule("Matricule");
        student.setNom("Nom");
        student.setPassword("iloveyou");
        student.setPhone("6625550144");
        student.setPrenom("Prenom");
        student.setProgramme(programme5);
        student.setRole(Role.employer);

        Contract contract = new Contract();
        contract.setEmployeur(employeur);
        contract.setFile(file);
        contract.setId(1L);
        contract.setInternOffer(internOffer3);
        contract.setSignatureEmployer(true);
        contract.setSignatureInternShipManager(true);
        contract.setSignatureStudent(true);
        contract.setStudent(student);
        Optional<Contract> ofResult = Optional.of(contract);
        when(contractRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        ContractDto actualById = contractService.getById(1L);
        verify(contractRepository).findById(Mockito.<Long>any());
        InternOfferDto internOfferDto = actualById.getInternOfferDto();
        assertEquals("1970-01-01", internOfferDto.getEndDate());
        assertEquals("1970-01-01", internOfferDto.getStartDate());
        EmployeurDto employeur3 = actualById.getEmployeur();
        assertEquals("6625550144", employeur3.getPhone());
        EtudiantDto etudiantDto = actualById.getEtudiantDto();
        assertEquals("6625550144", etudiantDto.getPhone());
        assertEquals("Dr", internOfferDto.getTitle());
        assertEquals("Entreprise", employeur3.getEntreprise());
        assertEquals("Entreprise", internOfferDto.getEmployeurEntreprise());
        assertEquals("Location", internOfferDto.getLocation());
        assertEquals("Matricule", etudiantDto.getMatricule());
        assertEquals("Nom", internOfferDto.getEmployeurNom());
        assertEquals("Nom", internOfferDto.getProgrammeNom());
        assertEquals("Nom", employeur3.getNom());
        assertEquals("Nom", etudiantDto.getNom());
        assertEquals("Prenom", internOfferDto.getEmployeurPrenom());
        assertEquals("Prenom", employeur3.getPrenom());
        assertEquals("Prenom", etudiantDto.getPrenom());
        assertEquals("QVhBWEFYQVg=", actualById.getContent());
        assertEquals("Session", internOfferDto.getSession());
        assertEquals("The characteristics of someone or something", internOfferDto.getDescription());
        assertEquals("foo.txt", actualById.getFileName());
        FileDto file3 = internOfferDto.getFile();
        assertEquals("foo.txt", file3.getFileName());
        assertEquals("jane.doe@example.org", employeur3.getEmail());
        assertEquals("jane.doe@example.org", etudiantDto.getEmail());
        assertEquals(10.0d, internOfferDto.getSalaryByHour());
        assertEquals(1L, actualById.getFileId());
        assertEquals(1L, actualById.getId());
        assertEquals(1L, employeur3.getProgramme_id());
        assertEquals(1L, etudiantDto.getProgramme_id());
        assertEquals(1L, file3.getId());
        assertEquals(1L, internOfferDto.getEmployeurId());
        assertEquals(1L, internOfferDto.getId());
        assertEquals(1L, internOfferDto.getOfferReviewRequestId());
        assertEquals(1L, internOfferDto.getProgrammeId());
        assertEquals(1L, employeur3.getId());
        assertEquals(1L, etudiantDto.getId());
        assertEquals(State.ACCEPTED, file3.getIsAccepted());
        assertEquals(State.ACCEPTED, internOfferDto.getState());
        assertTrue(actualById.isSignatureEmployer());
        assertTrue(actualById.isSignatureInternShipManager());
        assertTrue(actualById.isSignatureStudent());
        assertTrue(etudiantDto.getCv().isEmpty());
        assertEquals(internOffers, internOfferDto.getInternshipCandidates());
        assertSame(actualById.employeur, employeur3);
        assertSame(actualById.etudiantDto, etudiantDto);
        assertSame(actualById.internOfferDto, internOfferDto);
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, file3.getContent());
    }

    /**
     * Method under test: {@link ContractService#saveContractDto(ContractDto)}
     */
    @Test
    void testSaveContractDto() throws UnsupportedEncodingException {
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
        when(fileService.findById(Mockito.<Long>any())).thenReturn(file2);
        assertThrows(IllegalStateException.class, () -> contractService.saveContractDto(new ContractDto()));
        verify(fileService).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ContractService#saveContractStudentDto(ContractDto)}
     */
    @Test
    void testSaveContractStudentDto() throws UnsupportedEncodingException {
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
        when(fileService.findById(Mockito.<Long>any())).thenReturn(file2);
        assertThrows(IllegalStateException.class, () -> contractService.saveContractStudentDto(new ContractDto()));
        verify(fileService).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ContractService#saveContractEmployerDto(ContractDto)}
     */
    @Test
    void testSaveContractEmployerDto() throws UnsupportedEncodingException {
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
        when(fileService.findById(Mockito.<Long>any())).thenReturn(file2);
        assertThrows(IllegalStateException.class, () -> contractService.saveContractEmployerDto(new ContractDto()));
        verify(fileService).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ContractService#createContract(Stage)}
     */
    @Test
    void testCreateContract() throws UnsupportedEncodingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalStateException: Impossible de sauvegarder le contrat
        //       at com.sap.ose.projetose.service.ContractService.createContract(ContractService.java:145)
        //   See https://diff.blue/R013 to resolve this issue.

        ContractRepository contractRepository = mock(ContractRepository.class);
        when(contractRepository.save(Mockito.<Contract>any()))
                .thenThrow(new IllegalStateException("Erreur lors de la création du contrat"));

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
        TemplateContractRepository templateContractRepository = mock(TemplateContractRepository.class);
        when(templateContractRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        TemplateContractService templateContractService = new TemplateContractService(templateContractRepository);
        EmployeurRepository employeurRepository = mock(EmployeurRepository.class);
        EmployeurService employeurService = new EmployeurService(employeurRepository,
                new ProgrammeService(mock(ProgrammeRepository.class)));

        FileService fileService = new FileService(mock(FileEntityRepository.class));
        InternOfferRepository offerJobRepository = mock(InternOfferRepository.class);
        EmployeurRepository employeurRepository2 = mock(EmployeurRepository.class);
        ProgrammeService programmeService = new ProgrammeService(mock(ProgrammeRepository.class));
        EmployeurRepository employeurRepository3 = mock(EmployeurRepository.class);
        EmployeurService employeurService2 = new EmployeurService(employeurRepository3,
                new ProgrammeService(mock(ProgrammeRepository.class)));

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

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

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
        etudiant6.setProgramme(programme5);
        etudiant6.setRole(Role.employer);

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

        InternOffer internOffer2 = new InternOffer();
        internOffer2.setDescription("The characteristics of someone or something");
        internOffer2.setEmployeur(employeur3);
        internOffer2.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer2.setFile(file3);
        internOffer2.setId(1L);
        internOffer2.setInternshipCandidates(new ArrayList<>());
        internOffer2.setLocation("Location");
        internOffer2.setOfferReviewRequest(offerReviewRequest2);
        internOffer2.setProgramme(programme7);
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

        File activeCv5 = new File();
        activeCv5.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv5.setEtudiant(etudiant6);
        activeCv5.setFileName("foo.txt");
        activeCv5.setId(1L);
        activeCv5.setInternshipCandidates(internshipCandidates6);
        activeCv5.setIsAccepted(State.ACCEPTED);

        Programme programme8 = new Programme();
        programme8.setDescription("The characteristics of someone or something");
        programme8.setId(1L);
        programme8.setNom("Nom");

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
        etudiant9.setProgramme(programme8);
        etudiant9.setRole(Role.employer);

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

        InternshipCandidates internshipCandidates7 = new InternshipCandidates();
        internshipCandidates7.setEtudiant(new Etudiant());
        internshipCandidates7.setFiles(new ArrayList<>());
        internshipCandidates7.setId(1L);
        internshipCandidates7.setInternOffer(new InternOffer());
        internshipCandidates7.setState(State.ACCEPTED);
        internshipCandidates7.setStudentCv(new File());

        File activeCv6 = new File();
        activeCv6.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv6.setEtudiant(etudiant10);
        activeCv6.setFileName("foo.txt");
        activeCv6.setId(1L);
        activeCv6.setInternshipCandidates(internshipCandidates7);
        activeCv6.setIsAccepted(State.ACCEPTED);

        Programme programme10 = new Programme();
        programme10.setDescription("The characteristics of someone or something");
        programme10.setId(1L);
        programme10.setNom("Nom");

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
        etudiant11.setProgramme(programme10);
        etudiant11.setRole(Role.employer);

        File activeCv7 = new File();
        activeCv7.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv7.setEtudiant(new Etudiant());
        activeCv7.setFileName("foo.txt");
        activeCv7.setId(1L);
        activeCv7.setInternshipCandidates(new InternshipCandidates());
        activeCv7.setIsAccepted(State.ACCEPTED);

        Programme programme11 = new Programme();
        programme11.setDescription("The characteristics of someone or something");
        programme11.setId(1L);
        programme11.setNom("Nom");

        Etudiant etudiant12 = new Etudiant();
        etudiant12.setActiveCv(activeCv7);
        etudiant12.setCv(new ArrayList<>());
        etudiant12.setEmail("jane.doe@example.org");
        etudiant12.setId(1L);
        etudiant12.setInternshipsCandidate(new ArrayList<>());
        etudiant12.setMatricule("Matricule");
        etudiant12.setNom("Nom");
        etudiant12.setPassword("iloveyou");
        etudiant12.setPhone("6625550144");
        etudiant12.setPrenom("Prenom");
        etudiant12.setProgramme(programme11);
        etudiant12.setRole(Role.employer);

        Employeur employeur5 = new Employeur();
        employeur5.setEmail("jane.doe@example.org");
        employeur5.setEntreprise("Entreprise");
        employeur5.setId(1L);
        employeur5.setInternOffers(new ArrayList<>());
        employeur5.setNom("Nom");
        employeur5.setPassword("iloveyou");
        employeur5.setPhone("6625550144");
        employeur5.setPrenom("Prenom");
        employeur5.setProgramme(new Programme());
        employeur5.setRole(Role.employer);

        File file4 = new File();
        file4.setContent("AXAXAXAX".getBytes("UTF-8"));
        file4.setEtudiant(new Etudiant());
        file4.setFileName("foo.txt");
        file4.setId(1L);
        file4.setInternshipCandidates(new InternshipCandidates());
        file4.setIsAccepted(State.ACCEPTED);

        OfferReviewRequest offerReviewRequest3 = new OfferReviewRequest();
        offerReviewRequest3.setComment("Comment");
        offerReviewRequest3.setId(1L);
        offerReviewRequest3.setInternOffer(new InternOffer());
        offerReviewRequest3.setInternshipmanager(new Internshipmanager());

        Programme programme12 = new Programme();
        programme12.setDescription("The characteristics of someone or something");
        programme12.setId(1L);
        programme12.setNom("Nom");

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur5);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file4);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest3);
        internOffer3.setProgramme(programme12);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setSession("Session");
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

        Etudiant etudiant13 = new Etudiant();
        etudiant13.setActiveCv(new File());
        etudiant13.setCv(new ArrayList<>());
        etudiant13.setEmail("jane.doe@example.org");
        etudiant13.setId(1L);
        etudiant13.setInternshipsCandidate(new ArrayList<>());
        etudiant13.setMatricule("Matricule");
        etudiant13.setNom("Nom");
        etudiant13.setPassword("iloveyou");
        etudiant13.setPhone("6625550144");
        etudiant13.setPrenom("Prenom");
        etudiant13.setProgramme(new Programme());
        etudiant13.setRole(Role.employer);

        InternshipCandidates internshipCandidates8 = new InternshipCandidates();
        internshipCandidates8.setEtudiant(new Etudiant());
        internshipCandidates8.setFiles(new ArrayList<>());
        internshipCandidates8.setId(1L);
        internshipCandidates8.setInternOffer(new InternOffer());
        internshipCandidates8.setState(State.ACCEPTED);
        internshipCandidates8.setStudentCv(new File());

        File studentCv3 = new File();
        studentCv3.setContent("AXAXAXAX".getBytes("UTF-8"));
        studentCv3.setEtudiant(etudiant13);
        studentCv3.setFileName("foo.txt");
        studentCv3.setId(1L);
        studentCv3.setInternshipCandidates(internshipCandidates8);
        studentCv3.setIsAccepted(State.ACCEPTED);

        InternshipCandidates internshipCandidates9 = new InternshipCandidates();
        internshipCandidates9.setEtudiant(etudiant12);
        internshipCandidates9.setFiles(new ArrayList<>());
        internshipCandidates9.setId(1L);
        internshipCandidates9.setInternOffer(internOffer3);
        internshipCandidates9.setState(State.ACCEPTED);
        internshipCandidates9.setStudentCv(studentCv3);

        File file5 = new File();
        file5.setContent("AXAXAXAX".getBytes("UTF-8"));
        file5.setEtudiant(etudiant11);
        file5.setFileName("foo.txt");
        file5.setId(1L);
        file5.setInternshipCandidates(internshipCandidates9);
        file5.setIsAccepted(State.ACCEPTED);

        Programme programme13 = new Programme();
        programme13.setDescription("The characteristics of someone or something");
        programme13.setId(1L);
        programme13.setNom("Nom");

        Employeur employeur6 = new Employeur();
        employeur6.setEmail("jane.doe@example.org");
        employeur6.setEntreprise("Entreprise");
        employeur6.setId(1L);
        employeur6.setInternOffers(new ArrayList<>());
        employeur6.setNom("Nom");
        employeur6.setPassword("iloveyou");
        employeur6.setPhone("6625550144");
        employeur6.setPrenom("Prenom");
        employeur6.setProgramme(programme13);
        employeur6.setRole(Role.employer);

        Etudiant etudiant14 = new Etudiant();
        etudiant14.setActiveCv(new File());
        etudiant14.setCv(new ArrayList<>());
        etudiant14.setEmail("jane.doe@example.org");
        etudiant14.setId(1L);
        etudiant14.setInternshipsCandidate(new ArrayList<>());
        etudiant14.setMatricule("Matricule");
        etudiant14.setNom("Nom");
        etudiant14.setPassword("iloveyou");
        etudiant14.setPhone("6625550144");
        etudiant14.setPrenom("Prenom");
        etudiant14.setProgramme(new Programme());
        etudiant14.setRole(Role.employer);

        InternshipCandidates internshipCandidates10 = new InternshipCandidates();
        internshipCandidates10.setEtudiant(new Etudiant());
        internshipCandidates10.setFiles(new ArrayList<>());
        internshipCandidates10.setId(1L);
        internshipCandidates10.setInternOffer(new InternOffer());
        internshipCandidates10.setState(State.ACCEPTED);
        internshipCandidates10.setStudentCv(new File());

        File file6 = new File();
        file6.setContent("AXAXAXAX".getBytes("UTF-8"));
        file6.setEtudiant(etudiant14);
        file6.setFileName("foo.txt");
        file6.setId(1L);
        file6.setInternshipCandidates(internshipCandidates10);
        file6.setIsAccepted(State.ACCEPTED);

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

        Internshipmanager internshipmanager = new Internshipmanager();
        internshipmanager.setEmail("jane.doe@example.org");
        internshipmanager.setId(1L);
        internshipmanager.setNom("Nom");
        internshipmanager.setPassword("iloveyou");
        internshipmanager.setPhone("6625550144");
        internshipmanager.setPrenom("Prenom");
        internshipmanager.setProgramme(new Programme());
        internshipmanager.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest4 = new OfferReviewRequest();
        offerReviewRequest4.setComment("Comment");
        offerReviewRequest4.setId(1L);
        offerReviewRequest4.setInternOffer(internOffer4);
        offerReviewRequest4.setInternshipmanager(internshipmanager);

        Programme programme14 = new Programme();
        programme14.setDescription("The characteristics of someone or something");
        programme14.setId(1L);
        programme14.setNom("Nom");

        InternOffer internOffer5 = new InternOffer();
        internOffer5.setDescription("The characteristics of someone or something");
        internOffer5.setEmployeur(employeur6);
        internOffer5.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer5.setFile(file6);
        internOffer5.setId(1L);
        internOffer5.setInternshipCandidates(new ArrayList<>());
        internOffer5.setLocation("Location");
        internOffer5.setOfferReviewRequest(offerReviewRequest4);
        internOffer5.setProgramme(programme14);
        internOffer5.setSalaryByHour(10.0d);
        internOffer5.setSession("Session");
        internOffer5.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer5.setState(State.ACCEPTED);
        internOffer5.setStatus("Status");
        internOffer5.setTitle("Dr");

        Programme programme15 = new Programme();
        programme15.setDescription("The characteristics of someone or something");
        programme15.setId(1L);
        programme15.setNom("Nom");

        Internshipmanager internshipmanager2 = new Internshipmanager();
        internshipmanager2.setEmail("jane.doe@example.org");
        internshipmanager2.setId(1L);
        internshipmanager2.setNom("Nom");
        internshipmanager2.setPassword("iloveyou");
        internshipmanager2.setPhone("6625550144");
        internshipmanager2.setPrenom("Prenom");
        internshipmanager2.setProgramme(programme15);
        internshipmanager2.setRole(Role.employer);

        OfferReviewRequest offerReviewRequest5 = new OfferReviewRequest();
        offerReviewRequest5.setComment("Comment");
        offerReviewRequest5.setId(1L);
        offerReviewRequest5.setInternOffer(internOffer5);
        offerReviewRequest5.setInternshipmanager(internshipmanager2);

        Programme programme16 = new Programme();
        programme16.setDescription("The characteristics of someone or something");
        programme16.setId(1L);
        programme16.setNom("Nom");

        InternOffer internOffer6 = new InternOffer();
        internOffer6.setDescription("The characteristics of someone or something");
        internOffer6.setEmployeur(employeur4);
        internOffer6.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer6.setFile(file5);
        internOffer6.setId(1L);
        internOffer6.setInternshipCandidates(new ArrayList<>());
        internOffer6.setLocation("Location");
        internOffer6.setOfferReviewRequest(offerReviewRequest5);
        internOffer6.setProgramme(programme16);
        internOffer6.setSalaryByHour(10.0d);
        internOffer6.setSession("Session");
        internOffer6.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer6.setState(State.ACCEPTED);
        internOffer6.setStatus("Status");
        internOffer6.setTitle("Dr");
        Stage stage = mock(Stage.class);
        when(stage.getEmployeur()).thenReturn(employeur2);
        when(stage.getStudent()).thenReturn(etudiant9);
        when(stage.getOffer()).thenReturn(internOffer6);
        assertThrows(IllegalStateException.class, () -> contractService.createContract(stage));
        verify(stage).getEmployeur();
        verify(stage).getOffer();
        verify(stage).getStudent();
        verify(templateContractRepository).findTemplateContractByIsActive();
        verify(contractRepository).save(Mockito.<Contract>any());
    }

    /**
     * Method under test: {@link ContractService#findById(long)}
     */
    @Test
    void testFindById() throws UnsupportedEncodingException {
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

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(programme3);
        employeur2.setRole(Role.employer);

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

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(new Etudiant());
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(new InternOffer());
        internshipCandidates2.setState(State.ACCEPTED);
        internshipCandidates2.setStudentCv(new File());

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(etudiant3);
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(internshipCandidates2);
        file2.setIsAccepted(State.ACCEPTED);

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

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur2);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file2);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest);
        internOffer3.setProgramme(programme4);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setSession("Session");
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

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

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(new Etudiant());
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(1L);
        internshipCandidates3.setInternOffer(new InternOffer());
        internshipCandidates3.setState(State.ACCEPTED);
        internshipCandidates3.setStudentCv(new File());

        File activeCv2 = new File();
        activeCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv2.setEtudiant(etudiant4);
        activeCv2.setFileName("foo.txt");
        activeCv2.setId(1L);
        activeCv2.setInternshipCandidates(internshipCandidates3);
        activeCv2.setIsAccepted(State.ACCEPTED);

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        Etudiant student = new Etudiant();
        student.setActiveCv(activeCv2);
        student.setCv(new ArrayList<>());
        student.setEmail("jane.doe@example.org");
        student.setId(1L);
        student.setInternshipsCandidate(new ArrayList<>());
        student.setMatricule("Matricule");
        student.setNom("Nom");
        student.setPassword("iloveyou");
        student.setPhone("6625550144");
        student.setPrenom("Prenom");
        student.setProgramme(programme5);
        student.setRole(Role.employer);

        Contract contract = new Contract();
        contract.setEmployeur(employeur);
        contract.setFile(file);
        contract.setId(1L);
        contract.setInternOffer(internOffer3);
        contract.setSignatureEmployer(true);
        contract.setSignatureInternShipManager(true);
        contract.setSignatureStudent(true);
        contract.setStudent(student);
        Optional<Contract> ofResult = Optional.of(contract);
        when(contractRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Contract actualFindByIdResult = contractService.findById(1L);
        verify(contractRepository).findById(Mockito.<Long>any());
        assertSame(contract, actualFindByIdResult);
    }

    /**
     * Method under test: {@link ContractService#getAllByStudentID(long, int, int, Sort)}
     */
    @Test
    void testGetAllByStudentID() {
        when(contractRepository.findAllByStudentId(anyLong(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        Sort sort = Sort.unsorted();
        Page<ContractDto> actualAllByStudentID = contractService.getAllByStudentID(1L, 1, 3, sort);
        verify(contractRepository).findAllByStudentId(anyLong(), Mockito.<Pageable>any());
        assertTrue(actualAllByStudentID.toList().isEmpty());
        assertTrue(sort.toList().isEmpty());
    }

    /**
     * Method under test: {@link ContractService#getAllByStudentID(long, int, int, Sort)}
     */
    @Test
    void testGetAllByStudentID2() throws UnsupportedEncodingException {
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

        Programme programme3 = new Programme();
        programme3.setDescription("The characteristics of someone or something");
        programme3.setId(1L);
        programme3.setNom("Nom");

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1L);
        employeur2.setInternOffers(new ArrayList<>());
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(programme3);
        employeur2.setRole(Role.employer);

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

        InternshipCandidates internshipCandidates2 = new InternshipCandidates();
        internshipCandidates2.setEtudiant(new Etudiant());
        internshipCandidates2.setFiles(new ArrayList<>());
        internshipCandidates2.setId(1L);
        internshipCandidates2.setInternOffer(new InternOffer());
        internshipCandidates2.setState(State.ACCEPTED);
        internshipCandidates2.setStudentCv(new File());

        File file2 = new File();
        file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        file2.setEtudiant(etudiant3);
        file2.setFileName("foo.txt");
        file2.setId(1L);
        file2.setInternshipCandidates(internshipCandidates2);
        file2.setIsAccepted(State.ACCEPTED);

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

        Programme programme4 = new Programme();
        programme4.setDescription("The characteristics of someone or something");
        programme4.setId(1L);
        programme4.setNom("Nom");

        InternOffer internOffer3 = new InternOffer();
        internOffer3.setDescription("The characteristics of someone or something");
        internOffer3.setEmployeur(employeur2);
        internOffer3.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer3.setFile(file2);
        internOffer3.setId(1L);
        internOffer3.setInternshipCandidates(new ArrayList<>());
        internOffer3.setLocation("Location");
        internOffer3.setOfferReviewRequest(offerReviewRequest);
        internOffer3.setProgramme(programme4);
        internOffer3.setSalaryByHour(10.0d);
        internOffer3.setSession("Session");
        internOffer3.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer3.setState(State.ACCEPTED);
        internOffer3.setStatus("Status");
        internOffer3.setTitle("Dr");

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

        InternshipCandidates internshipCandidates3 = new InternshipCandidates();
        internshipCandidates3.setEtudiant(new Etudiant());
        internshipCandidates3.setFiles(new ArrayList<>());
        internshipCandidates3.setId(1L);
        internshipCandidates3.setInternOffer(new InternOffer());
        internshipCandidates3.setState(State.ACCEPTED);
        internshipCandidates3.setStudentCv(new File());

        File activeCv2 = new File();
        activeCv2.setContent("AXAXAXAX".getBytes("UTF-8"));
        activeCv2.setEtudiant(etudiant4);
        activeCv2.setFileName("foo.txt");
        activeCv2.setId(1L);
        activeCv2.setInternshipCandidates(internshipCandidates3);
        activeCv2.setIsAccepted(State.ACCEPTED);

        Programme programme5 = new Programme();
        programme5.setDescription("The characteristics of someone or something");
        programme5.setId(1L);
        programme5.setNom("Nom");

        Etudiant student = new Etudiant();
        student.setActiveCv(activeCv2);
        student.setCv(new ArrayList<>());
        student.setEmail("jane.doe@example.org");
        student.setId(1L);
        student.setInternshipsCandidate(new ArrayList<>());
        student.setMatricule("Matricule");
        student.setNom("Nom");
        student.setPassword("iloveyou");
        student.setPhone("6625550144");
        student.setPrenom("Prenom");
        student.setProgramme(programme5);
        student.setRole(Role.employer);

        Contract contract = new Contract();
        contract.setEmployeur(employeur);
        contract.setFile(file);
        contract.setId(1L);
        contract.setInternOffer(internOffer3);
        contract.setSignatureEmployer(true);
        contract.setSignatureInternShipManager(true);
        contract.setSignatureStudent(true);
        contract.setStudent(student);

        ArrayList<Contract> content = new ArrayList<>();
        content.add(contract);
        PageImpl<Contract> pageImpl = new PageImpl<>(content);
        when(contractRepository.findAllByStudentId(anyLong(), Mockito.<Pageable>any())).thenReturn(pageImpl);
        Sort sort = Sort.unsorted();
        Page<ContractDto> actualAllByStudentID = contractService.getAllByStudentID(1L, 1, 3, sort);
        verify(contractRepository).findAllByStudentId(anyLong(), Mockito.<Pageable>any());
        assertEquals(1, actualAllByStudentID.toList().size());
        assertTrue(sort.toList().isEmpty());
    }

    /**
     * Method under test: {@link ContractService#getAllByStudentID(long, int, int, Sort)}
     */
    @Test
    void testGetAllByStudentID3() {
        when(contractRepository.findAllByStudentId(anyLong(), Mockito.<Pageable>any())).thenReturn(null);
        assertThrows(ServiceException.class, () -> contractService.getAllByStudentID(1L, 1, 3, Sort.unsorted()));
        verify(contractRepository).findAllByStudentId(anyLong(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link ContractService#getAllByStudentID(long, int, int, Sort)}
     */
    @Test
    void testGetAllByStudentID4() {
        assertThrows(ServiceException.class, () -> contractService.getAllByStudentID(1L, -1, 3, Sort.unsorted()));
    }

    /**
     * Method under test: {@link ContractService#getAllByStudentID(long, int, int, Sort)}
     */
    @Test
    void testGetAllByStudentID5() {
        when(contractRepository.findAllByStudentId(anyLong(), Mockito.<Pageable>any()))
                .thenThrow(new IllegalStateException("Erreur lors de la récupération des contrats"));
        assertThrows(ServiceException.class, () -> contractService.getAllByStudentID(1L, 1, 3, Sort.unsorted()));
        verify(contractRepository).findAllByStudentId(anyLong(), Mockito.<Pageable>any());
    }
}
