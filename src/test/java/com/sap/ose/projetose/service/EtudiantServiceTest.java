package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.StudentAppliedOffersDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.EtudiantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
public class EtudiantServiceTest {

    @Autowired
    EtudiantService etudiantService;

    @MockBean
    EtudiantRepository etudiantRepository;

    Etudiant etudiant = new Etudiant();

    @BeforeEach
    public void setup() {
        InternOffer internOffer = new InternOffer();
        internOffer.setDescription("The characteristics of someone or something");
        internOffer.setEmployeur(new Employeur());
        internOffer.setEndDate(LocalDate.now());
        internOffer.setFile(new File());
        internOffer.setState(State.ACCEPTED);
        internOffer.setId(1L);
        internOffer.setInternshipCandidates(null);
        internOffer.setLocation("Location");
        internOffer.setProgramme(null);
        internOffer.setSalaryByHour(10.0d);
        internOffer.setProgramme(new Programme());
        internOffer.setStartDate(LocalDate.now());
        internOffer.setTitle("Dr");


        etudiant = new Etudiant();
        etudiant.setCv("Cv");
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1L);
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme(new Programme());
        etudiant.setInternshipsCandidate(List.of(
                new InternshipCandidates(
                        etudiant,
                        internOffer,
                        List.of(new File())
                )
        ));
    }

    @Test
    public void getOffersAppliedByEtudiant_NotStudentAppliedOffers() {

        etudiant.setInternshipsCandidate(null);

        when(etudiantRepository.findById(anyLong())).thenReturn(Optional.of(etudiant));

        List<StudentAppliedOffersDto> result = etudiantService.getOffersAppliedByEtudiant(1);

        assertEquals(0, result.size());
    }

    @Test
    public void getOffersAppliedByEtudiant_StudentAppliedOffers() {

        List<StudentAppliedOffersDto> mockedStudentApplied = List.of(
                new StudentAppliedOffersDto(
                        new InternOfferDto(etudiant.getInternshipsCandidate().get(0).getInternOffer()),
                        List.of(new FileDto())
                )
        );

        when(etudiantRepository.findById(anyLong())).thenReturn(Optional.of(etudiant));

        List<StudentAppliedOffersDto> result = etudiantService.getOffersAppliedByEtudiant(1L);

        verify(etudiantRepository).findById(1L);

        assertEquals(mockedStudentApplied.size(), result.size());
        assertNull(result.get(0).getAppliedOffer().getInternshipCandidates());
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
