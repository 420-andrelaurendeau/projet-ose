package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.dto.OfferReviewRequestDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.models.InternshipOffer;
import com.sap.ose.projetose.models.InternshipManager;
import com.sap.ose.projetose.models.OfferReviewRequest;
import com.sap.ose.projetose.repository.InternOfferRepository;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import com.sap.ose.projetose.repository.OfferReviewRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public InternshipOfferDto saveOfferReviewRequest(OfferReviewRequestDto offerReviewRequestDto) {
        try {
            if (internOfferService.isApprovedOrDeclineById(offerReviewRequestDto.getInternshipOfferId()))
                throw new OfferAlreadyReviewedException();

            InternshipOffer internshipOffer = internOfferService.findById(offerReviewRequestDto.getInternshipOfferId());
            InternshipManager internshipmanager = internshipmanagerService.findById(offerReviewRequestDto.getInternshipManagerId());

            OfferReviewRequest offerReviewRequest = offerReviewRequestDto.fromDto();
            offerReviewRequest.setInternshipOffer(internshipOffer);
            offerReviewRequest.setInternshipManager(internshipmanager);

            internshipOffer.setState(offerReviewRequestDto.getState());
            internshipOffer.setOfferReviewRequest(offerReviewRequest);

            offerReviewRequestRepository.save(offerReviewRequest);

            return new InternshipOfferDto(internshipOffer);
        } catch (OfferAlreadyReviewedException e) {
            logger.error("L'offre a déjà été approuvée pour l'Id" + offerReviewRequestDto.getInternshipOfferId(), e);
            throw e;
        } catch (OfferNotFoundException e) {
            logger.error("Offre d'emploi non trouvée pour l'Id : " + offerReviewRequestDto.getInternshipOfferId(), e);
            throw e;
        } catch (InternshipManagerNotFoundException e) {
            logger.error("Gestionnaire de stage non trouvée pour l'Id : " + offerReviewRequestDto.getInternshipManagerId(), e);
            throw e;
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès à la base de données lors de la sauvegarde de la revue de l'offre d'emploi", e);
            throw new DatabaseException("Erreur d'accès à la base de données lors de la sauvegarde de la revue de l'offre d'emploi.");
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la sauvegarde de la revue de l'offre d'emploi.", e);
            throw new ServiceException("Erreur inconnue lors de la sauvegarde de la revue de l'offre d'emploi.");
        }
    }
}
