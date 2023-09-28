package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.OfferReviewRequestDto;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Internshipmanager;
import com.sap.ose.projetose.modeles.OfferReviewRequest;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.OfferReviewRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferReviewRequestService {

    private final OfferReviewRequestRepository offerReviewRequestRepository;
    private final InternOfferService internOfferService;

    private final ProgrammeService programmeService;

    private final InternshipmanagerService internshipmanagerService;

    @Autowired
    public OfferReviewRequestService(OfferReviewRequestRepository offerReviewRequestRepository, InternOfferService internOfferService, ProgrammeService programmeService, InternshipmanagerService internshipmanagerService) {
        this.offerReviewRequestRepository = offerReviewRequestRepository;
        this.internOfferService = internOfferService;
        this.programmeService = programmeService;
        this.internshipmanagerService = internshipmanagerService;
    }


    public void saveOfferReviewRequest(OfferReviewRequestDto offerReviewRequestDto) {
        OfferReviewRequest offerReviewRequest = offerReviewRequestDto.fromDto();

        InternOffer internOffer = internOfferService.getById(offerReviewRequestDto.getInternOfferId()).fromDto();

        Internshipmanager internshipmanager = internshipmanagerService.getById(offerReviewRequestDto.getInternshipmanagerId()).fromDto();
        internshipmanager.setId(offerReviewRequestDto.getInternshipmanagerId());


        offerReviewRequest.setInternOffer(internOffer);
        offerReviewRequest.setInternshipmanager(internshipmanager);

        offerReviewRequestRepository.save(offerReviewRequest);
    }
}
