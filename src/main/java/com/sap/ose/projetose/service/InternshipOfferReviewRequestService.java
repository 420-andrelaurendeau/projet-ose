package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.dto.OfferReviewRequestDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.models.InternshipManager;
import com.sap.ose.projetose.models.InternshipOffer;
import com.sap.ose.projetose.models.OfferReviewRequest;
import com.sap.ose.projetose.repository.OfferReviewRequestRepository;
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
    public InternshipOfferDto saveOfferReviewRequest(OfferReviewRequestDto offerReviewRequestDto) {
        try {
            if (internshipOfferService.isApprovedOrDeclineById(offerReviewRequestDto.getInternshipOfferId()))
                throw new OfferAlreadyReviewedException();

            InternshipOffer internshipOffer = internshipOfferService.findById(offerReviewRequestDto.getInternshipOfferId());
            InternshipManager internshipmanager = internshipmanagerService.findInternshipManagerById(offerReviewRequestDto.getInternshipManagerId());

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
