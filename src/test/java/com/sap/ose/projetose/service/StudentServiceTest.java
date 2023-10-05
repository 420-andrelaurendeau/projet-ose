package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.dto.StudentApplicationDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.models.*;
import com.sap.ose.projetose.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)
public class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @MockBean
    StudentRepository studentRepository;

    Student etudiant = new Student();

    @BeforeEach
    public void setup() {
        InternshipOffer internshipOffer = new InternshipOffer();
        internshipOffer.setDescription("The characteristics of someone or something");
        internshipOffer.setEmployer(new Employer());
        internshipOffer.setEndDate(LocalDate.now());
        internshipOffer.setFile(new File());
        internshipOffer.setState(AssessmentState.APPROVED);
        internshipOffer.setId(1L);
        internshipOffer.setInternshipCandidates(null);
        internshipOffer.setLocation("Location");
        internshipOffer.setFormation(null);
        internshipOffer.setSalaryByHour(10.0d);
        internshipOffer.setFormation(new Formation());
        internshipOffer.setStartDate(LocalDate.now());
        internshipOffer.setTitle("Dr");

        new File();
        etudiant = new Student();
        etudiant.setCv(new ArrayList<>());
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1L);
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setFormation(new Formation());
        etudiant.setInternshipsCandidate(List.of(
                new InternshipApplication(
                        etudiant,
                        internshipOffer,
                        List.of(new File())
                )
        ));
    }

    @Test
    public void getOffersAppliedByEtudiant_NotStudentAppliedOffers() {

        etudiant.setInternshipsCandidate(null);

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(etudiant));

        List<StudentApplicationDto> result = studentService.getOffersAppliedByEtudiant(1);

        assertEquals(0, result.size());
    }

    @Test
    public void getOffersAppliedByEtudiant_StudentAppliedOffers() {

        List<StudentApplicationDto> mockedStudentApplied = List.of(
                new StudentApplicationDto(
                        new InternshipOfferDto(etudiant.getInternshipsCandidate().get(0).getInternshipOffer()),
                        List.of(new FileDto())
                )
        );

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(etudiant));

        List<StudentApplicationDto> result = studentService.getOffersAppliedByEtudiant(1L);

        verify(studentRepository).findById(1L);

        assertEquals(mockedStudentApplied.size(), result.size());
        assertNull(result.get(0).getAppliedOffer().getInternshipCandidates());
        assertEquals(mockedStudentApplied.get(0).getAppliedOffer().getId(), result.get(0).getAppliedOffer().getId());
    }


    @Test
    public void getOffersAppliedByEtudiant_EtudiantNotFound() {

        when(studentRepository.findById(anyLong())).thenThrow(new StudentNotFoundException());

        StudentNotFoundException result = assertThrows(StudentNotFoundException.class, () -> studentService.getOffersAppliedByEtudiant(1));
        assertEquals("Étudiant non trouvé", result.getMessage());
    }


    @Test
    public void getOffersAppliedByEtudiant_DataAccessException() {
        when(studentRepository.findById(anyLong())).thenThrow(new DataAccessException("") {});

        DatabaseException result = assertThrows(DatabaseException.class, () -> studentService.getOffersAppliedByEtudiant(1));
        assertEquals("Erreur lors de la récupération des offres appliquées par l'étudiant", result.getMessage());
    }

    @Test
    public void getOffersAppliedByEtudiant_ServiceException() {
        when(studentRepository.findById(anyLong())).thenThrow(new RuntimeException());

        ServiceException result = assertThrows(ServiceException.class, () -> studentService.getOffersAppliedByEtudiant(1));
        assertEquals("Erreur lors de la récupération des offres appliquées par l'étudiant", result.getMessage());
    }

}
