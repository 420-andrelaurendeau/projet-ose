package com.sap.ose.projetose.services;

import com.sap.ose.projetose.models.InternshipOffer;
import com.sap.ose.projetose.models.OfferReviewRequest;
import com.sap.ose.projetose.repositories.OfferReviewRequestRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InternshipOfferReviewRequestService {
    private final OfferReviewRequestRepository offerReviewRequestRepository;


    @Transactional
    public OfferReviewRequest createRequest(InternshipOffer internshipOffer) {
        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();

        offerReviewRequest.setInternshipOffer(internshipOffer);

        return offerReviewRequestRepository.save(offerReviewRequest);
    }
}
