package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.dto.OfferReviewRequestDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.models.*;
import com.sap.ose.projetose.repository.InternOfferRepository;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import com.sap.ose.projetose.repository.OfferReviewRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {InternshipOfferReviewRequestService.class})
@ExtendWith(SpringExtension.class)
public class InternshipOfferReviewRequestServiceTest {

    private final OfferReviewRequestDto offerReviewRequestDto = new OfferReviewRequestDto();
    @Autowired
    private InternshipOfferReviewRequestService internshipOfferReviewRequestService;
    @MockBean
    private OfferReviewRequestRepository offerReviewRequestRepository;
    @MockBean
    private InternshipOfferService internshipOfferService;
    @MockBean
    private InternOfferRepository internOfferRepository;
    @MockBean
    private InternshipManagerService internshipmanagerService;
    @MockBean
    private InternshipmanagerRepository internshipmanagerRepository;
    @MockBean
    private StudyProgramService studyProgramService;

    @BeforeEach
    public void setUp() {
        this.offerReviewRequestDto.setId(0);
        this.offerReviewRequestDto.setComment("Comment");
        this.offerReviewRequestDto.setInternshipOfferId(0L);
        this.offerReviewRequestDto.setInternshipManagerId(1L);
    }

    @Test
    public void saveOfferReviewRequest_Test() throws Exception {

        StudyProgram mockedStudyProgram = new StudyProgram(1L, "Programme Nom", "Programme Description");
        Employer mockedEmployer = new Employer(1, "Employeur Nom", "Employeur Prenom", "Employeur Entreprise", "Employeur Email", "dsdsfsf", "fdfdd", new StudyProgram());
        File mockedFile = new File("hello".getBytes(StandardCharsets.UTF_8), "Test", true);
        InternshipOffer mockedInternshipOffer = new InternshipOffer("ff", "ff", "ff", 20.50, LocalDate.now(), LocalDate.now(), new ArrayList<>(), mockedStudyProgram, mockedFile, mockedEmployer, ApprovalStatus.PENDING, null);
        InternshipManager mockedInternshipManager = new InternshipManager(1L, "nom", "name", "lastName", "email", "password", null);
        OfferReviewRequest mockedOfferReviewRequest = offerReviewRequestDto.fromDto();
        mockedOfferReviewRequest.setInternshipOffer(mockedInternshipOffer);
        mockedOfferReviewRequest.setInternshipManager(mockedInternshipManager);

        when(internshipOfferService.isApprovedOrDeclineById(anyLong())).thenReturn(false);
        when(internshipOfferService.findById(anyLong())).thenReturn(mockedInternshipOffer);
        when(internshipmanagerService.findInternshipManagerById(anyLong())).thenReturn(mockedInternshipManager);
        when(offerReviewRequestRepository.save(any(OfferReviewRequest.class))).thenReturn(mockedOfferReviewRequest);


        InternshipOfferDto returnedDto = internshipOfferReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto);


        verify(offerReviewRequestRepository).save(any(OfferReviewRequest.class));
        verify(internshipOfferService).isApprovedOrDeclineById(offerReviewRequestDto.getInternshipOfferId());
        verify(internshipOfferService).findById(offerReviewRequestDto.getInternshipOfferId());
        verify(internshipmanagerService).findInternshipManagerById(offerReviewRequestDto.getInternshipManagerId());


        assertEquals(offerReviewRequestDto.getInternshipOfferId(), returnedDto.getId());
        assertEquals(offerReviewRequestDto.getState(), returnedDto.getState());


    }


    @Test
    public void saveOfferReviewRequest_OfferAlreadyApprovedException() {
        when(internshipOfferService.isApprovedOrDeclineById(anyLong())).thenReturn(true);

        OfferAlreadyReviewedException result = assertThrows(OfferAlreadyReviewedException.class, () -> internshipOfferReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("L'offre a déjà été revue", result.getMessage());
    }

    @Test
    public void saveOfferReviewRequest_InterOfferNotFound() {
        when(internshipOfferService.findById(anyLong())).thenThrow(new OfferNotFoundException());

        OfferNotFoundException result = assertThrows(OfferNotFoundException.class, () -> internshipOfferReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("Offre d'emploi non trouvée.", result.getMessage());
    }


    @Test
    public void saveOfferReviewRequest_InternshipmanagerNotFound() {
        when(internshipOfferService.findById(anyLong())).thenThrow(new InternshipManagerNotFoundException());

        InternshipManagerNotFoundException result = assertThrows(InternshipManagerNotFoundException.class, () -> internshipOfferReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("Gestionnaire de stage non trouvé.", result.getMessage());
    }

    @Test
    public void saveOfferReviewRequest_DataAccessError() {
        when(internshipOfferService.findById(anyLong())).thenReturn(new InternshipOffer());
        when(offerReviewRequestRepository.save(any())).thenThrow(new DataAccessException("") {
        });

        DatabaseException result = assertThrows(DatabaseException.class, () -> internshipOfferReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("Erreur d'accès à la base de données lors de la sauvegarde de la revue de l'offre d'emploi.", result.getMessage());
    }

    @Test
    public void saveOfferReviewRequest_UnknownError() {
        when(internshipOfferService.findById(anyLong())).thenReturn(new InternshipOffer());
        when(offerReviewRequestRepository.save(any())).thenThrow(new IllegalArgumentException("Test case"));

        ServiceException result = assertThrows(ServiceException.class, () -> internshipOfferReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("Erreur inconnue lors de la sauvegarde de la revue de l'offre d'emploi.", result.getMessage());
    }
}

