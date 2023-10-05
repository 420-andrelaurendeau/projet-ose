package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.models.Employer;
import com.sap.ose.projetose.models.InternshipOffer;
import com.sap.ose.projetose.models.Program;
import com.sap.ose.projetose.models.ApprovalStatus;
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
class InternshipOfferServiceTest {

    private final InternshipOfferDto internshipOfferDto = new InternshipOfferDto();
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
        this.internshipOfferDto.setId(1L);
        this.internshipOfferDto.setDescription("The characteristics of someone or something");
        this.internshipOfferDto.setEmployerEntreprise("Employeur Entreprise");
        this.internshipOfferDto.setEmployerId(1L);
        this.internshipOfferDto.setEmployerNom("Employeur Nom");
        this.internshipOfferDto.setEmployerPrenom("Employeur Prenom");
        this.internshipOfferDto.setEndDate("2020-03-01");
        this.internshipOfferDto.setFile(new FileDto());
        this.internshipOfferDto.setInternshipApplicationIds(new ArrayList<>());
        this.internshipOfferDto.setLocation("Location");
        this.internshipOfferDto.setProgramId(1);
        this.internshipOfferDto.setProgramNom("Programme Nom");
        this.internshipOfferDto.setSalaryByHour(10.0d);
        this.internshipOfferDto.setStartDate("2020-03-01");
        this.internshipOfferDto.setTitle("Dr");
    }

    @Test
    public void saveInterOfferJob_Creation() {
        Program mockedProgram = new Program(1, "Programme Nom", "Programme Description");
        Employer mockedEmployer = new Employer(1, "Employeur Nom", "Employeur Prenom", "Employeur Entreprise", "Employeur Email", "dsdsfsf", "fdfdd", new Program());
        InternshipOffer mockedInternshipOffer = internshipOfferDto.fromDto();
        mockedInternshipOffer.setProgram(mockedProgram);
        mockedInternshipOffer.setEmployer(mockedEmployer);

        when(programmeService.findById(mockedProgram.getId())).thenReturn(mockedProgram);
        when(employeurService.findById(mockedEmployer.getId())).thenReturn(mockedEmployer);
        when(internOfferRepository.save(any(InternshipOffer.class))).thenReturn(mockedInternshipOffer);

        ArgumentCaptor<InternshipOffer> captor = ArgumentCaptor.forClass(InternshipOffer.class);
        internOfferService.saveInterOfferJob(internshipOfferDto);

        verify(internOfferRepository).save(captor.capture());
        InternshipOffer savedOffer = captor.getValue();

        assertEquals(internshipOfferDto.getId(), savedOffer.getId());
        assertEquals(internshipOfferDto.getProgramId(), savedOffer.getProgram().getId());
        assertEquals(internshipOfferDto.getEmployerId(), savedOffer.getEmployer().getId());

    }


    @Test
    public void saveInterOfferJob_OfferAlreadyApprovedException() {
        InternshipOffer mockOffer = new InternshipOffer();
        mockOffer.setState(ApprovalStatus.APPROVED);
        when(internOfferRepository.findById(anyLong())).thenReturn(Optional.of(mockOffer));

        OfferAlreadyReviewedException result = assertThrows(OfferAlreadyReviewedException.class, () -> internOfferService.saveInterOfferJob(internshipOfferDto));
        assertEquals("L'offre a déjà été approuvée et ne peut pas être modifiée.", result.getMessage());
    }


    @Test
    public void saveInterOfferJob_ProgrammeNotFound() {
        when(programmeService.findById(anyLong())).thenThrow(ProgramNotFoundException.class);

        ProgramNotFoundException result = assertThrows(ProgramNotFoundException.class, () -> internOfferService.saveInterOfferJob(internshipOfferDto));
        assertEquals("Programme non trouvé", result.getMessage());
    }

    @Test
    public void saveInterOfferJob_EmployeurNotFound() {
        when(employeurService.findById(anyLong())).thenThrow(EmployerNotFoundException.class);

        EmployerNotFoundException result = assertThrows(EmployerNotFoundException.class, () -> internOfferService.saveInterOfferJob(internshipOfferDto));
        assertEquals("Employeur non trouvé", result.getMessage());
    }

    @Test
    public void saveInterOfferJob_DataAccessError() {
        when(internOfferRepository.save(any())).thenThrow(new DataAccessException("") {
        });

        DatabaseException result = assertThrows(DatabaseException.class, () -> internOfferService.saveInterOfferJob(internshipOfferDto));
        assertEquals("Erreur lors de la sauvegarde de l'offre d'emploi.", result.getMessage());
    }

    @Test
    public void saveInterOfferJob_UnknownError() {
        when(internOfferRepository.save(any())).thenThrow(IllegalArgumentException.class);

        ServiceException result = assertThrows(ServiceException.class, () -> internOfferService.saveInterOfferJob(internshipOfferDto));
        assertEquals("Erreur lors de la sauvegarde de l'offre d'emploi.", result.getMessage());
    }


    @Test
    public void findById_Success() {
        InternshipOffer mockOffer = internshipOfferDto.fromDto();
        when(internOfferRepository.findById(anyLong())).thenReturn(Optional.of(mockOffer));

        InternshipOffer result = internOfferService.findById(anyLong());

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

