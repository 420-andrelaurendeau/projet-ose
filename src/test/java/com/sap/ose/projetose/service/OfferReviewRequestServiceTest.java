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

@ContextConfiguration(classes = {OfferReviewRequestService.class})
@ExtendWith(SpringExtension.class)
public class OfferReviewRequestServiceTest {

    private final OfferReviewRequestDto offerReviewRequestDto = new OfferReviewRequestDto();
    @Autowired
    private OfferReviewRequestService offerReviewRequestService;
    @MockBean
    private OfferReviewRequestRepository offerReviewRequestRepository;
    @MockBean
    private InternOfferService internOfferService;
    @MockBean
    private InternOfferRepository internOfferRepository;
    @MockBean
    private InternshipmanagerService internshipmanagerService;
    @MockBean
    private InternshipmanagerRepository internshipmanagerRepository;
    @MockBean
    private ProgrammeService programmeService;

    @BeforeEach
    public void setUp() {
        this.offerReviewRequestDto.setId(0);
        this.offerReviewRequestDto.setComment("Comment");
        this.offerReviewRequestDto.setInternshipOfferId(0L);
        this.offerReviewRequestDto.setInternshipManagerId(1L);
    }

    @Test
    public void saveOfferReviewRequest_Test() throws Exception {

        Program mockedProgram = new Program(1L, "Programme Nom", "Programme Description");
        Employer mockedEmployer = new Employer(1, "Employeur Nom", "Employeur Prenom", "Employeur Entreprise", "Employeur Email", "dsdsfsf", "fdfdd", new Program());
        File mockedFile = new File("hello".getBytes(StandardCharsets.UTF_8), "Test", true);
        InternshipOffer mockedInternshipOffer = new InternshipOffer("ff", "ff", "ff", 20.50, LocalDate.now(), LocalDate.now(), new ArrayList<>(), mockedProgram, mockedFile, mockedEmployer, ApprovalStatus.PENDING, null);
        InternshipManager mockedInternshipManager = new InternshipManager(1L, "nom", "name", "lastName", "email", "password", null);
        OfferReviewRequest mockedOfferReviewRequest = offerReviewRequestDto.fromDto();
        mockedOfferReviewRequest.setInternshipOffer(mockedInternshipOffer);
        mockedOfferReviewRequest.setInternshipManager(mockedInternshipManager);

        when(internOfferService.isApprovedOrDeclineById(anyLong())).thenReturn(false);
        when(internOfferService.findById(anyLong())).thenReturn(mockedInternshipOffer);
        when(internshipmanagerService.findById(anyLong())).thenReturn(mockedInternshipManager);
        when(offerReviewRequestRepository.save(any(OfferReviewRequest.class))).thenReturn(mockedOfferReviewRequest);


        InternshipOfferDto returnedDto = offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto);


        verify(offerReviewRequestRepository).save(any(OfferReviewRequest.class));
        verify(internOfferService).isApprovedOrDeclineById(offerReviewRequestDto.getInternshipOfferId());
        verify(internOfferService).findById(offerReviewRequestDto.getInternshipOfferId());
        verify(internshipmanagerService).findById(offerReviewRequestDto.getInternshipManagerId());


        assertEquals(offerReviewRequestDto.getInternshipOfferId(), returnedDto.getId());
        assertEquals(offerReviewRequestDto.getState(), returnedDto.getState());


    }


    @Test
    public void saveOfferReviewRequest_OfferAlreadyApprovedException() {
        when(internOfferService.isApprovedOrDeclineById(anyLong())).thenReturn(true);

        OfferAlreadyReviewedException result = assertThrows(OfferAlreadyReviewedException.class, () -> offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("L'offre a déjà été revue", result.getMessage());
    }

    @Test
    public void saveOfferReviewRequest_InterOfferNotFound() {
        when(internOfferService.findById(anyLong())).thenThrow(new OfferNotFoundException());

        OfferNotFoundException result = assertThrows(OfferNotFoundException.class, () -> offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("Offre d'emploi non trouvée.", result.getMessage());
    }


    @Test
    public void saveOfferReviewRequest_InternshipmanagerNotFound() {
        when(internOfferService.findById(anyLong())).thenThrow(new InternshipManagerNotFoundException());

        InternshipManagerNotFoundException result = assertThrows(InternshipManagerNotFoundException.class, () -> offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("Gestionnaire de stage non trouvé.", result.getMessage());
    }

    @Test
    public void saveOfferReviewRequest_DataAccessError() {
        when(internOfferService.findById(anyLong())).thenReturn(new InternshipOffer());
        when(offerReviewRequestRepository.save(any())).thenThrow(new DataAccessException("") {
        });

        DatabaseException result = assertThrows(DatabaseException.class, () -> offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("Erreur d'accès à la base de données lors de la sauvegarde de la revue de l'offre d'emploi.", result.getMessage());
    }

    @Test
    public void saveOfferReviewRequest_UnknownError() {
        when(internOfferService.findById(anyLong())).thenReturn(new InternshipOffer());
        when(offerReviewRequestRepository.save(any())).thenThrow(new IllegalArgumentException("Test case"));

        ServiceException result = assertThrows(ServiceException.class, () -> offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("Erreur inconnue lors de la sauvegarde de la revue de l'offre d'emploi.", result.getMessage());
    }
}

