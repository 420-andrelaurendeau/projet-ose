package com.sap.ose.projetose.service;

import com.sap.ose.projetose.controller.ReactOseController;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.repository.InternOfferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class InternOfferService {

    private final InternOfferRepository offerJobRepository;

    Logger logger = LoggerFactory.getLogger(ReactOseController.class);

    @Autowired
    public InternOfferService(InternOfferRepository offerJobRepository) {
        this.offerJobRepository = offerJobRepository;
    }

    public InternOffer saveInterOfferJob(InternOfferDto internOfferDto) {
        try {
            InternOffer internOffer = internOfferDto.fromDto();
            InternOffer internOffer1Success = offerJobRepository.save(internOffer);
            return internOffer1Success;

        } catch (DataIntegrityViolationException e) {
            logger.info(e.getMessage());
            throw new DataIntegrityViolationException("Erreur d'intégrité des données lors de la sauvegarde de l'offre d'emploi.");
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DataAccessException("Erreur d'accès aux données lors de la sauvegarde de l'offre d'emploi.") {};
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new RuntimeException("Erreur inconnue lors de la sauvegarde de l'offre d'emploi.");
        }
    }

}