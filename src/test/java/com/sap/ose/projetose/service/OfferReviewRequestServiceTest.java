package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.OfferReviewRequestDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Internshipmanager;
import com.sap.ose.projetose.modeles.OfferReviewRequest;
import com.sap.ose.projetose.repository.InternOfferRepository;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import com.sap.ose.projetose.repository.OfferReviewRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        this.offerReviewRequestDto.setInternOfferId(0L);
        this.offerReviewRequestDto.setInternshipmanagerId(1L);
    }

    @Test
    public void saveInterOfferJob_Creation() {
        InternOffer mockedInterOffer = new InternOffer("fdf", "title", "location", 1, null, null, null, null, null, null, null, null);
        Internshipmanager mockedInternshipmanager = new Internshipmanager(1L, "nom", "name", "lastName", "email", "password", null);
        OfferReviewRequest mockedOfferReviewRequest = offerReviewRequestDto.fromDto();
        mockedOfferReviewRequest.setInternOffer(mockedInterOffer);
        mockedOfferReviewRequest.setInternshipmanager(mockedInternshipmanager);

        when(internOfferService.findById(anyLong())).thenReturn(mockedInterOffer);
        when(internshipmanagerService.findById(anyLong())).thenReturn(mockedInternshipmanager);
        when(offerReviewRequestRepository.save(any(OfferReviewRequest.class))).thenReturn(mockedOfferReviewRequest);

        ArgumentCaptor<OfferReviewRequest> captor = ArgumentCaptor.forClass(OfferReviewRequest.class);
        offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto);

        verify(offerReviewRequestRepository).save(captor.capture());
        OfferReviewRequest savedOffer = captor.getValue();

        assertEquals(offerReviewRequestDto.getId(), savedOffer.getId());
        assertEquals(offerReviewRequestDto.getInternOfferId(), savedOffer.getInternOffer().getId());
        assertEquals(offerReviewRequestDto.getInternshipmanagerId(), savedOffer.getInternshipmanager().getId());

    }

    @Test
    public void saveOfferReviewRequest_OfferAlreadyApprovedException() {
        when(internOfferService.isApprovedById(anyLong())).thenReturn(true);

        OfferAlreadyApprovedException result = assertThrows(OfferAlreadyApprovedException.class, () -> offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("L'offre déjà approuvée.", result.getMessage());
    }

    @Test
    public void saveOfferReviewRequest_InterOfferNotFound() {
        when(internOfferService.findById(anyLong())).thenThrow(new OfferNotFoundException());

        OfferNotFoundException result = assertThrows(OfferNotFoundException.class, () -> offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("Offre d'emploi non trouvée.", result.getMessage());
    }


    @Test
    public void saveOfferReviewRequest_InternshipmanagerNotFound() {
        when(internOfferService.findById(anyLong())).thenThrow(new InternshipmanagerNotFoundException());

        InternshipmanagerNotFoundException result = assertThrows(InternshipmanagerNotFoundException.class, () -> offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("Gestionnaire de stage non trouvé.", result.getMessage());
    }

    @Test
    public void saveOfferReviewRequest_DataAccessError() {
        when(internOfferService.findById(anyLong())).thenReturn(new InternOffer());
        when(offerReviewRequestRepository.save(any())).thenThrow(new DataAccessException("") {
        });

        DatabaseException result = assertThrows(DatabaseException.class, () -> offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("Erreur d'accès à la base de données lors de la sauvegarde de la revue de l'offre d'emploi.", result.getMessage());
    }

    @Test
    public void saveOfferReviewRequest_UnknownError() {
        when(internOfferService.findById(anyLong())).thenReturn(new InternOffer());
        when(offerReviewRequestRepository.save(any())).thenThrow(new IllegalArgumentException("Test case"));

        ServiceException result = assertThrows(ServiceException.class, () -> offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("Erreur inconnue lors de la sauvegarde de la revue de l'offre d'emploi.", result.getMessage());
    }
}

