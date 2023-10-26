package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.OfferReviewRequestDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Internshipmanager;
import com.sap.ose.projetose.modeles.OfferReviewRequest;
import com.sap.ose.projetose.repository.InternOfferRepository;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import com.sap.ose.projetose.repository.OfferReviewRequestRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OfferReviewRequestService {

    private final OfferReviewRequestRepository offerReviewRequestRepository;
    private final InternOfferService internOfferService;
    private final InternshipmanagerService internshipmanagerService;
    private final Logger logger = LoggerFactory.getLogger(OfferReviewRequestService.class);




    @Transactional
    public InternOfferDto saveOfferReviewRequest(OfferReviewRequestDto offerReviewRequestDto) {
        try {
            if (internOfferService.isApprovedOrDeclineById(offerReviewRequestDto.getInternOfferId()))
                throw new OfferAlreadyReviewException();

            InternOffer internOffer = internOfferService.findById(offerReviewRequestDto.getInternOfferId());
            Internshipmanager internshipmanager = internshipmanagerService.findById(offerReviewRequestDto.getInternshipmanagerId());

            OfferReviewRequest offerReviewRequest = offerReviewRequestDto.fromDto();
            offerReviewRequest.setInternOffer(internOffer);
            offerReviewRequest.setInternshipmanager(internshipmanager);

            internOffer.setState(offerReviewRequestDto.getState());
            internOffer.setOfferReviewRequest(offerReviewRequest);

            offerReviewRequestRepository.save(offerReviewRequest);

            return new InternOfferDto(internOffer);
        } catch (OfferAlreadyReviewException e) {
            logger.error("L'offre a déjà été approuvée pour l'Id" + offerReviewRequestDto.getInternOfferId(), e);
            throw e;
        } catch (OfferNotFoundException e) {
            logger.error("Offre d'emploi non trouvée pour l'Id : " + offerReviewRequestDto.getInternOfferId(), e);
            throw e;
        } catch (InternshipmanagerNotFoundException e) {
            logger.error("Gestionnaire de stage non trouvée pour l'Id : " + offerReviewRequestDto.getInternshipmanagerId(), e);
            throw e;
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès à la base de données lors de la sauvegarde de la revue de l'offre d'emploi", e);
            throw new DatabaseException("Erreur d'accès à la base de données lors de la sauvegarde de la revue de l'offre d'emploi.");
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la sauvegarde de la revue de l'offre d'emploi.", e);
            throw new ServiceException("Erreur inconnue lors de la sauvegarde de la revue de l'offre d'emploi.");
        }
    }

    public OfferReviewRequestDto getOfferReviewRequest(Long id) {
        try {
            OfferReviewRequest offerReviewRequest = offerReviewRequestRepository.findById(id).orElseThrow(null);
            return new OfferReviewRequestDto(offerReviewRequest);
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès à la base de données lors de la récupération de la revue de l'offre d'emploi", e);
            throw new DatabaseException("Erreur d'accès à la base de données lors de la récupération de la revue de l'offre d'emploi.");
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération de la revue de l'offre d'emploi.", e);
            throw new ServiceException("Erreur inconnue lors de la récupération de la revue de l'offre d'emploi.");
        }
    }
}
