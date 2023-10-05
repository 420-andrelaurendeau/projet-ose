package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.dto.StudentApplicationsDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.models.*;
import com.sap.ose.projetose.repository.EtudiantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {EtudiantService.class})
@ExtendWith(SpringExtension.class)
public class StudentServiceTest {

    @Autowired
    EtudiantService etudiantService;

    @MockBean
    EtudiantRepository etudiantRepository;

    Student student = new Student();

    @BeforeEach
    public void setup() {
        InternshipOffer internshipOffer = new InternshipOffer();
        internshipOffer.setDescription("The characteristics of someone or something");
        internshipOffer.setEmployer(new Employer());
        internshipOffer.setEndDate(LocalDate.now());
        internshipOffer.setFile(new File());
        internshipOffer.setState(ApprovalStatus.APPROVED);
        internshipOffer.setId(1L);
        internshipOffer.setInternshipCandidates(null);
        internshipOffer.setLocation("Location");
        internshipOffer.setProgram(null);
        internshipOffer.setSalaryByHour(10.0d);
        internshipOffer.setProgram(new Program());
        internshipOffer.setStartDate(LocalDate.now());
        internshipOffer.setTitle("Dr");


        student = new Student();
        student.setCvList(null);
        student.setEmail("jane.doe@example.org");
        student.setId(1L);
        student.setMatricule("Matricule");
        student.setLastName("Nom");
        student.setPassword("iloveyou");
        student.setPhoneNumber("6625550144");
        student.setFirstName("Prenom");
        student.setProgram(new Program());
        student.setInternshipApplications(List.of(
                new InternshipApplication(
                        student,
                        internshipOffer,
                        List.of(new File())
                )
        ));
    }

    @Test
    public void getOffersAppliedByEtudiant_NotStudentAppliedOffers() {

        student.setInternshipApplications(null);

        when(etudiantRepository.findById(anyLong())).thenReturn(Optional.of(student));

        List<StudentApplicationsDto> result = etudiantService.getOffersAppliedByEtudiant(1);

        assertEquals(0, result.size());
    }

    @Test
    public void getOffersAppliedByEtudiant_StudentAppliedOffers() {

        List<StudentApplicationsDto> mockedStudentApplied = List.of(
                new StudentApplicationsDto(
                        new InternshipOfferDto(student.getInternshipApplications().get(0).getInternshipOffer()),
                        List.of(new FileDto())
                )
        );

        when(etudiantRepository.findById(anyLong())).thenReturn(Optional.of(student));

        List<StudentApplicationsDto> result = etudiantService.getOffersAppliedByEtudiant(1L);

        verify(etudiantRepository).findById(1L);

        assertEquals(mockedStudentApplied.size(), result.size());
        assertNull(result.get(0).getAppliedOffer().getInternshipApplicationIds());
        assertEquals(mockedStudentApplied.get(0).getAppliedOffer().getId(), result.get(0).getAppliedOffer().getId());
    }


    @Test
    public void getOffersAppliedByEtudiant_EtudiantNotFound() {

        when(etudiantRepository.findById(anyLong())).thenThrow(new EtudiantNotFoundException());

        EtudiantNotFoundException result = assertThrows(EtudiantNotFoundException.class, () -> etudiantService.getOffersAppliedByEtudiant(1));
        assertEquals("Étudiant non trouvé", result.getMessage());
    }


    @Test
    public void getOffersAppliedByEtudiant_DataAccessException() {
        when(etudiantRepository.findById(anyLong())).thenThrow(new DataAccessException("") {});

        DatabaseException result = assertThrows(DatabaseException.class, () -> etudiantService.getOffersAppliedByEtudiant(1));
        assertEquals("Erreur lors de la récupération des offres appliquées par l'étudiant", result.getMessage());
    }

    @Test
    public void getOffersAppliedByEtudiant_ServiceException() {
        when(etudiantRepository.findById(anyLong())).thenThrow(new RuntimeException());

        ServiceException result = assertThrows(ServiceException.class, () -> etudiantService.getOffersAppliedByEtudiant(1));
        assertEquals("Erreur lors de la récupération des offres appliquées par l'étudiant", result.getMessage());
    }

}
