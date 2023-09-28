package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.OfferReviewRequestDto;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.InternOfferRepository;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import com.sap.ose.projetose.repository.OfferReviewRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OfferReviewRequestService {

    private final OfferReviewRequestRepository offerReviewRequestRepository;

    private final InternOfferService internOfferService;
    private final InternshipmanagerService internshipmanagerService;

    @Autowired
    public OfferReviewRequestService(OfferReviewRequestRepository offerReviewRequestRepository, InternOfferRepository internOfferRepository, InternOfferService internOfferService, ProgrammeService programmeService, InternshipmanagerService internshipmanagerService, InternshipmanagerRepository internshipmanagerRepository, InternshipmanagerService internshipmanagerService1) {
        this.offerReviewRequestRepository = offerReviewRequestRepository;
        this.internOfferService = internOfferService;
        this.internshipmanagerService = internshipmanagerService1;
    }


     @Transactional
    public void saveOfferReviewRequest(OfferReviewRequestDto offerReviewRequestDto) {
        OfferReviewRequest offerReviewRequest = offerReviewRequestDto.fromDto();

        InternOffer internOffer = internOfferService.getById(offerReviewRequestDto.getInternOfferId());
        Internshipmanager internshipmanager = internshipmanagerService.findById(offerReviewRequestDto.getInternshipmanagerId());

        internOffer.setState(State.DECLINED);
        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        internOffer.setOfferReviewRequest(offerReviewRequest);

        offerReviewRequestRepository.save(offerReviewRequest);
    }
}
