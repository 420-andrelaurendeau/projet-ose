package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.FileTransferDto;
import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.models.Employer;
import com.sap.ose.projetose.models.InternshipOffer;
import com.sap.ose.projetose.models.StudyProgram;
import com.sap.ose.projetose.models.ApprovalStatus;
import com.sap.ose.projetose.repository.EmployerRepository;
import com.sap.ose.projetose.repository.InternshipOfferRepository;
import com.sap.ose.projetose.repository.StudyProgramRepository;
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

@ContextConfiguration(classes = {InternshipOfferService.class})
@ExtendWith(SpringExtension.class)
class InternshipOfferServiceTest {

    private final InternshipOfferDto internshipOfferDto = new InternshipOfferDto();
    @Autowired
    private InternshipOfferService internshipOfferService;
    @MockBean
    private StudyProgramService studyProgramService;
    @MockBean
    private StudyProgramRepository studyProgramRepository;
    @MockBean
    private EmployerService employerService;
    @MockBean
    private EmployerRepository employerRepository;
    @MockBean
    private InternshipOfferRepository internshipOfferRepository;

    @BeforeEach
    public void setUp() {
        this.internshipOfferDto.setId(1L);
        this.internshipOfferDto.setDescription("The characteristics of someone or something");
        this.internshipOfferDto.setEmployerEnterprise("Employeur Entreprise");
        this.internshipOfferDto.setEmployerId(1L);
        this.internshipOfferDto.setEmployerLastName("Employeur Nom");
        this.internshipOfferDto.setEmployerFirstName("Employeur Prenom");
        this.internshipOfferDto.setEndDate("2020-03-01");
        this.internshipOfferDto.setFile(new FileTransferDto());
        this.internshipOfferDto.setInternshipApplicationIds(new ArrayList<>());
        this.internshipOfferDto.setLocation("Location");
        this.internshipOfferDto.setProgramId(1);
        this.internshipOfferDto.setProgramName("Programme Nom");
        this.internshipOfferDto.setSalaryByHour(10.0d);
        this.internshipOfferDto.setStartDate("2020-03-01");
        this.internshipOfferDto.setTitle("Dr");
    }

    @Test
    public void saveInterOfferJob_Creation() {
        StudyProgram mockedStudyProgram = new StudyProgram(1, "Programme Nom", "Programme Description");
        Employer mockedEmployer = new Employer(1, "Employeur Nom", "Employeur Prenom", "Employeur Entreprise", "Employeur Email", "dsdsfsf", "fdfdd", new StudyProgram());
        InternshipOffer mockedInternshipOffer = internshipOfferDto.toInternshipOffer();
        mockedInternshipOffer.setStudyProgram(mockedStudyProgram);
        mockedInternshipOffer.setEmployer(mockedEmployer);

        when(studyProgramService.findProgramById(mockedStudyProgram.getId())).thenReturn(mockedStudyProgram);
        when(employerService.findById(mockedEmployer.getId())).thenReturn(mockedEmployer);
        when(internshipOfferRepository.save(any(InternshipOffer.class))).thenReturn(mockedInternshipOffer);

        ArgumentCaptor<InternshipOffer> captor = ArgumentCaptor.forClass(InternshipOffer.class);
        internshipOfferService.createOrUpdateInternshipOffer(internshipOfferDto);

        verify(internshipOfferRepository).save(captor.capture());
        InternshipOffer savedOffer = captor.getValue();

        assertEquals(internshipOfferDto.getId(), savedOffer.getId());
        assertEquals(internshipOfferDto.getProgramId(), savedOffer.getStudyProgram().getId());
        assertEquals(internshipOfferDto.getEmployerId(), savedOffer.getEmployer().getId());

    }


    @Test
    public void saveInterOfferJob_OfferAlreadyApprovedException() {
        InternshipOffer mockOffer = new InternshipOffer();
        mockOffer.setState(ApprovalStatus.APPROVED);
        when(internshipOfferRepository.findById(anyLong())).thenReturn(Optional.of(mockOffer));

        OfferAlreadyReviewedException result = assertThrows(OfferAlreadyReviewedException.class, () -> internshipOfferService.createOrUpdateInternshipOffer(internshipOfferDto));
        assertEquals("L'offre a déjà été approuvée et ne peut pas être modifiée.", result.getMessage());
    }


    @Test
    public void saveInterOfferJob_ProgrammeNotFound() {
        when(studyProgramService.findProgramById(anyLong())).thenThrow(ProgramNotFoundException.class);

        ProgramNotFoundException result = assertThrows(ProgramNotFoundException.class, () -> internshipOfferService.createOrUpdateInternshipOffer(internshipOfferDto));
        assertEquals("Programme non trouvé", result.getMessage());
    }

    @Test
    public void saveInterOfferJob_EmployeurNotFound() {
        when(employerService.findById(anyLong())).thenThrow(EmployerNotFoundException.class);

        EmployerNotFoundException result = assertThrows(EmployerNotFoundException.class, () -> internshipOfferService.createOrUpdateInternshipOffer(internshipOfferDto));
        assertEquals("Employeur non trouvé", result.getMessage());
    }

    @Test
    public void saveInterOfferJob_DataAccessError() {
        when(internshipOfferRepository.save(any())).thenThrow(new DataAccessException("") {
        });

        DatabaseException result = assertThrows(DatabaseException.class, () -> internshipOfferService.createOrUpdateInternshipOffer(internshipOfferDto));
        assertEquals("Erreur lors de la sauvegarde de l'offre d'emploi.", result.getMessage());
    }

    @Test
    public void saveInterOfferJob_UnknownError() {
        when(internshipOfferRepository.save(any())).thenThrow(IllegalArgumentException.class);

        ServiceException result = assertThrows(ServiceException.class, () -> internshipOfferService.createOrUpdateInternshipOffer(internshipOfferDto));
        assertEquals("Erreur lors de la sauvegarde de l'offre d'emploi.", result.getMessage());
    }


    @Test
    public void findById_Success() {
        InternshipOffer mockOffer = internshipOfferDto.toInternshipOffer();
        when(internshipOfferRepository.findById(anyLong())).thenReturn(Optional.of(mockOffer));

        InternshipOffer result = internshipOfferService.findById(anyLong());

        Assertions.assertEquals(mockOffer, result);
    }

    @Test
    public void findById_NotFound() {
        when(internshipOfferRepository.findById(anyLong())).thenThrow(OfferNotFoundException.class);

        OfferNotFoundException result = assertThrows(OfferNotFoundException.class, () -> internshipOfferService.findById(anyLong()));
        assertEquals("Offre d'emploi non trouvée.", result.getMessage());
    }

    @Test
    public void findById_DatabaseException() {
        when(internshipOfferRepository.findById(anyLong())).thenThrow(new DataAccessException("") {
        });

        DatabaseException result = assertThrows(DatabaseException.class, () -> internshipOfferService.findById(anyLong()));
        assertEquals("Erreur lors de la récupération de l'offre d'emploi.", result.getMessage());
    }

    @Test
    public void findById_ServiceException() {
        when(internshipOfferRepository.findById(anyLong())).thenThrow(RuntimeException.class);

        ServiceException result = assertThrows(ServiceException.class, () -> internshipOfferService.findById(anyLong()));
        assertEquals("Erreur lors de la récupération de l'offre d'emploi.", result.getMessage());
    }
}

