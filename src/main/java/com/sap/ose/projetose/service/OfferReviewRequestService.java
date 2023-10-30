package com.sap.ose.projetose.service;

import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.OfferReviewRequest;
import com.sap.ose.projetose.repository.OfferReviewRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OfferReviewRequestService {
    private final OfferReviewRequestRepository offerReviewRequestRepository;


    @Transactional
    public OfferReviewRequest createRequest(InternOffer internOffer) {
        OfferReviewRequest offerReviewRequest = new OfferReviewRequest();

        offerReviewRequest.setInternOffer(internOffer);

        return offerReviewRequestRepository.save(offerReviewRequest);
    }
}
