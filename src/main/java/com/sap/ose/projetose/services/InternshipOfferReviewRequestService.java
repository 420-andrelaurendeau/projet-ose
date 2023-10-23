package com.sap.ose.projetose.services;

import com.sap.ose.projetose.dtos.InternshipOfferDto;
import com.sap.ose.projetose.dtos.NewInternshipOfferDto;
import com.sap.ose.projetose.dtos.OfferReviewRequestDto;
import com.sap.ose.projetose.exceptions.*;
import com.sap.ose.projetose.models.InternshipManager;
import com.sap.ose.projetose.models.InternshipOffer;
import com.sap.ose.projetose.models.OfferReviewRequest;
import com.sap.ose.projetose.repositories.OfferReviewRequestRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InternshipOfferReviewRequestService {
    private final OfferReviewRequestRepository offerReviewRequestRepository;
    private final InternshipOfferService internshipOfferService;
    private final InternshipManagerService internshipmanagerService;
    private final Logger logger = LoggerFactory.getLogger(InternshipOfferReviewRequestService.class);


    @Transactional
    public OfferReviewRequestDto createRequest(NewInternshipOfferDto internshipOfferDto) {
        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();



        return new OfferReviewRequestDto(offerReviewRequestRepository.save(offerReviewRequest));
    }
}
