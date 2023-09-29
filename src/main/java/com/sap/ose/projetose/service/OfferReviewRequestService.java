package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.OfferReviewRequestDto;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Internshipmanager;
import com.sap.ose.projetose.modeles.OfferReviewRequest;
import com.sap.ose.projetose.modeles.State;
import com.sap.ose.projetose.repository.InternOfferRepository;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import com.sap.ose.projetose.repository.OfferReviewRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OfferReviewRequestService {

    private final OfferReviewRequestRepository offerReviewRequestRepository;
    private final InternOfferService internOfferService;
    private final InternshipmanagerService internshipmanagerService;
    private final Logger logger = LoggerFactory.getLogger(OfferReviewRequestService.class);

    @Autowired
    public OfferReviewRequestService(OfferReviewRequestRepository offerReviewRequestRepository, InternOfferRepository internOfferRepository, InternOfferService internOfferService, ProgrammeService programmeService, InternshipmanagerService internshipmanagerService, InternshipmanagerRepository internshipmanagerRepository, InternshipmanagerService internshipmanagerService1) {
        this.offerReviewRequestRepository = offerReviewRequestRepository;
        this.internOfferService = internOfferService;
        this.internshipmanagerService = internshipmanagerService1;
    }


    @Transactional
    public void saveOfferReviewRequest(OfferReviewRequestDto offerReviewRequestDto) {

        try {
            InternOffer internOffer = internOfferService.getById(offerReviewRequestDto.getInternOfferId());
            internOffer.setState(State.DECLINED);

            Internshipmanager internshipmanager = internshipmanagerService.findById(offerReviewRequestDto.getInternshipmanagerId());

            OfferReviewRequest offerReviewRequest = offerReviewRequestDto.fromDto();
            offerReviewRequest.setInternOffer(internOffer);
            offerReviewRequest.setInternshipmanager(internshipmanager);

            internOffer.setOfferReviewRequest(offerReviewRequest);

            offerReviewRequestRepository.save(offerReviewRequest);
        } catch (DataIntegrityViolationException e) {
            logger.info(e.getMessage());
            throw new DataIntegrityViolationException("Erreur d'intégrité des données lors de la sauvegarde de l'offre d'emploi.");
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DataAccessException("Erreur d'accès aux données lors de la sauvegarde de l'offre d'emploi.") {
            };
        } catch (NullPointerException e) {
            logger.info(e.getMessage());
            throw new NullPointerException(e.getMessage());
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new RuntimeException("Erreur inconnue lors de la sauvegarde de l'offre d'emploi.");
        }

    }
}
