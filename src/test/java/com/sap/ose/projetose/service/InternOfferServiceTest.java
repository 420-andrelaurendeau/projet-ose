package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.modeles.State;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.InternOfferRepository;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {InternOfferService.class})
@ExtendWith(SpringExtension.class)
class InternOfferServiceTest {

    private final InternOfferDto internOfferDto = new InternOfferDto();
    @Autowired
    private InternOfferService internOfferService;
    @MockBean
    private ProgrammeService programmeService;
    @MockBean
    private ProgrammeRepository programmeRepository;
    @MockBean
    private EmployeurService employeurService;
    @MockBean
    private EmployeurRepository employeurRepository;
    @MockBean
    private InternOfferRepository internOfferRepository;

    @BeforeEach
    public void setUp() {
        this.internOfferDto.setId(1L);
        this.internOfferDto.setDescription("The characteristics of someone or something");
        this.internOfferDto.setEmployeurEntreprise("Employeur Entreprise");
        this.internOfferDto.setEmployeurId(1L);
        this.internOfferDto.setEmployeurNom("Employeur Nom");
        this.internOfferDto.setEmployeurPrenom("Employeur Prenom");
        this.internOfferDto.setEndDate("2020-03-01");
        this.internOfferDto.setFile(new FileDto());
        this.internOfferDto.setInternshipCandidates(new ArrayList<>());
        this.internOfferDto.setLocation("Location");
        this.internOfferDto.setProgrammeId(1);
        this.internOfferDto.setProgrammeNom("Programme Nom");
        this.internOfferDto.setSalaryByHour(10.0d);
        this.internOfferDto.setStartDate("2020-03-01");
        this.internOfferDto.setTitle("Dr");
    }

    @Test
    public void saveInterOfferJob_Creation() {
        Programme mockedProgramme = new Programme(1L, "Programme Nom", "Programme Description");
        Employeur mockedEmployeur = new Employeur(1, "Employeur Nom", "Employeur Prenom", "Employeur Entreprise", "Employeur Email", "dsdsfsf", "fdfdd", new Programme());
        InternOffer mockedInternOffer = internOfferDto.fromDto();
        mockedInternOffer.setProgramme(mockedProgramme);
        mockedInternOffer.setEmployeur(mockedEmployeur);

        when(programmeService.findById(anyLong())).thenReturn(mockedProgramme);
        when(employeurService.findById(anyLong())).thenReturn(mockedEmployeur);
        when(internOfferRepository.save(any(InternOffer.class))).thenReturn(mockedInternOffer);

        ArgumentCaptor<InternOffer> captor = ArgumentCaptor.forClass(InternOffer.class);
        internOfferService.saveInterOfferJob(internOfferDto);

        verify(internOfferRepository).save(captor.capture());
        InternOffer savedOffer = captor.getValue();

        assertEquals(internOfferDto.getId(), savedOffer.getId());
        assertEquals(internOfferDto.getProgrammeId(), savedOffer.getProgramme().getId());
        assertEquals(internOfferDto.getEmployeurId(), savedOffer.getEmployeur().getId());

    }


    @Test
    public void saveInterOfferJob_OfferAlreadyApprovedException() {
        InternOffer mockOffer = new InternOffer();
        mockOffer.setState(State.ACCEPTED);
        when(internOfferRepository.findById(anyLong())).thenReturn(Optional.of(mockOffer));

        OfferAlreadyReviewException result = assertThrows(OfferAlreadyReviewException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
        assertEquals("L'offre a déjà été approuvée et ne peut pas être modifiée.", result.getMessage());
    }


    @Test
    public void saveInterOfferJob_ProgrammeNotFound() {
        when(programmeService.findById(anyLong())).thenThrow(ProgramNotFoundException.class);

        ProgramNotFoundException result = assertThrows(ProgramNotFoundException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
        assertEquals("Programme non trouvé", result.getMessage());
    }

    @Test
    public void saveInterOfferJob_EmployeurNotFound() {
        when(employeurService.findById(anyLong())).thenThrow(EmployerNotFoundException.class);

        EmployerNotFoundException result = assertThrows(EmployerNotFoundException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
        assertEquals("Employeur non trouvé", result.getMessage());
    }

    @Test
    public void saveInterOfferJob_DataAccessError() {
        when(internOfferRepository.save(any())).thenThrow(new DataAccessException("") {
        });

        DatabaseException result = assertThrows(DatabaseException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
        assertEquals("Erreur lors de la sauvegarde de l'offre d'emploi.", result.getMessage());
    }

    @Test
    public void saveInterOfferJob_UnknownError() {
        when(internOfferRepository.save(any())).thenThrow(IllegalArgumentException.class);

        ServiceException result = assertThrows(ServiceException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
        assertEquals("Erreur lors de la sauvegarde de l'offre d'emploi.", result.getMessage());
    }


    @Test
    public void findById_Success() {
        InternOffer mockOffer = internOfferDto.fromDto();
        when(internOfferRepository.findById(anyLong())).thenReturn(Optional.of(mockOffer));

        InternOffer result = internOfferService.findById(anyLong());

        Assertions.assertEquals(mockOffer, result);
    }

    @Test
    public void findById_NotFound() {
        when(internOfferRepository.findById(anyLong())).thenThrow(OfferNotFoundException.class);

        OfferNotFoundException result = assertThrows(OfferNotFoundException.class, () -> internOfferService.findById(anyLong()));
        assertEquals("Offre d'emploi non trouvée.", result.getMessage());
    }

    @Test
    public void findById_DatabaseException() {
        when(internOfferRepository.findById(anyLong())).thenThrow(new DataAccessException("") {
        });

        DatabaseException result = assertThrows(DatabaseException.class, () -> internOfferService.findById(anyLong()));
        assertEquals("Erreur lors de la récupération de l'offre d'emploi.", result.getMessage());
    }

    @Test
    public void findById_ServiceException() {
        when(internOfferRepository.findById(anyLong())).thenThrow(RuntimeException.class);

        ServiceException result = assertThrows(ServiceException.class, () -> internOfferService.findById(anyLong()));
        assertEquals("Erreur lors de la récupération de l'offre d'emploi.", result.getMessage());
    }
}

