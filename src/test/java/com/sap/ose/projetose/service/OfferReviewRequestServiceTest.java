package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.OfferReviewRequestDto;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Internshipmanager;
import com.sap.ose.projetose.modeles.OfferReviewRequest;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.InternOfferRepository;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import com.sap.ose.projetose.repository.OfferReviewRequestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
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
        this.offerReviewRequestDto.setInternshipmanagerId(0L);
    }
    @Test
    public void saveOfferReviewRequest_InterOfferNotFound() {
        when(internOfferService.findById(anyLong())).thenThrow(new EmptyResultDataAccessException(1));

        EmptyResultDataAccessException result = assertThrows(EmptyResultDataAccessException.class, () -> offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals(0, result.getActualSize());
    }

    @Test
    public void saveOfferReviewRequest_InternshipmanagerNotFound() {
        when(internOfferService.findById(anyLong())).thenThrow(new EmptyResultDataAccessException(1));

        EmptyResultDataAccessException result = assertThrows(EmptyResultDataAccessException.class, () -> offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals(0, result.getActualSize());
    }

    @Test
    public void saveOfferReviewRequest_DataIntegrityError() {
        when(internOfferService.findById(anyLong())).thenReturn(new InternOffer());
        when(offerReviewRequestRepository.save(any())).thenThrow(new DataIntegrityViolationException("Test case") {
        });

        DataIntegrityViolationException result = assertThrows(DataIntegrityViolationException.class, () -> offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("Erreur d'intégrité des données lors de la sauvegarde de la revue de l'offre d'emploi.", result.getMessage());
    }

    @Test
    public void saveInterOfferJob_DataAccessError() {
        when(internOfferService.findById(anyLong())).thenReturn(new InternOffer());
        when(offerReviewRequestRepository.save(any())).thenThrow(new DataAccessException("Test case") {
        });

        DataAccessException result = assertThrows(DataAccessException.class, () -> offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("Erreur d'accès aux données lors de la sauvegarde de la revue de l'offre d'emploi.", result.getMessage());
    }

    @Test
    public void saveInterOfferJob_UnknownError() {
        when(internOfferService.findById(anyLong())).thenReturn(new InternOffer());
        when(offerReviewRequestRepository.save(any())).thenThrow(new IllegalArgumentException("Test case"));

        RuntimeException result = assertThrows(RuntimeException.class, () -> offerReviewRequestService.saveOfferReviewRequest(offerReviewRequestDto));
        assertEquals("Erreur inconnue lors de la sauvegarde de la revue de l'offre d'emploi.", result.getMessage());
    }
}

