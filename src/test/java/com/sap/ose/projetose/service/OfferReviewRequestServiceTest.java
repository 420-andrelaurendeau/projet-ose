package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.OfferReviewRequestDto;
import com.sap.ose.projetose.repository.OfferReviewRequestRepository;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OfferReviewRequestService.class})
@ExtendWith(SpringExtension.class)
public class OfferReviewRequestServiceTest {

    @Autowired
    private OfferReviewRequestService offerReviewRequestService;

    @MockBean
    private OfferReviewRequestRepository offerReviewRequestRepository;

    private final OfferReviewRequestDto offerReviewRequestDto = new OfferReviewRequestDto();

    @BeforeEach
    public void setUp() {
        this.offerReviewRequestDto.setId(0);
        this.offerReviewRequestDto.setComment("Comment");
        this.offerReviewRequestDto.setInternOfferId(1);
        this.offerReviewRequestDto.setInternshipmanagerId(1);
    }


    @Test
    public void saveOfferReviewRequest_ProgrammeNotFound() {
        when(programmeService.findById(anyLong())).thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(EmptyResultDataAccessException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
    }

    @Test
    public void saveOfferReviewRequest_EmployeurNotFound() {
        when(employeurService.findById(anyLong())).thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(EmptyResultDataAccessException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
    }

    @Test
    public void saveOfferReviewRequest_DataIntegrityError() {
        when(internOfferRepository.save(any())).thenThrow(new DataIntegrityViolationException("Test case") {
        });

        assertThrows(DataIntegrityViolationException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
    }

    @Test
    public void saveInterOfferJob_DataAccessError() {
        when(internOfferRepository.save(any())).thenThrow(new DataAccessException("Test case") {
        });

        assertThrows(DataAccessException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
    }

    @Test
    public void saveInterOfferJob_UnknownError() {
        when(internOfferRepository.save(any())).thenThrow(new IllegalArgumentException("Test case"));

        assertThrows(RuntimeException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
    }
}

