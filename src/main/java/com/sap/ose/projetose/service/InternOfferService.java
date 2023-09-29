package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.InternOfferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InternOfferService {

    private final InternOfferRepository offerJobRepository;

    private final ProgrammeService programmeService;
    private final EmployeurService employeurService;
    private final Logger logger = LoggerFactory.getLogger(InternOfferService.class);

    @Autowired
    public InternOfferService(InternOfferRepository offerJobRepository, EmployeurRepository employeurRepository, ProgrammeService programmeService, EmployeurService employeurService) {
        this.offerJobRepository = offerJobRepository;
        this.employeurService = employeurService;
        this.programmeService = programmeService;
    }

    @Transactional
    public InternOfferDto saveInterOfferJob(InternOfferDto internOfferDto) {
        try {

            Programme programme = programmeService.findById(internOfferDto.getProgrammeId());
            Employeur employeur = employeurService.findById((int) internOfferDto.getEmployeurId());

            InternOffer internOffer = internOfferDto.fromDto();
            internOffer.setProgramme(programme);
            internOffer.setEmployeur(employeur);

            return new InternOfferDto(offerJobRepository.save(internOffer));

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

    public List<InternOfferDto> getInternOfferAccepted() {
        List<InternOffer> internOfferList = offerJobRepository.findAllApproved();
        List<InternOfferDto> internOfferDtoList = new ArrayList<>();
        ;

        for (InternOffer offre : internOfferList) {
            InternOfferDto internOfferDto = new InternOfferDto(offre);
            internOfferDtoList.add(internOfferDto);
        }
        return internOfferDtoList;
    }

    public List<InternOfferDto> getInternOfferPending() {
        List<InternOffer> internOfferList = offerJobRepository.findAllPending();
        List<InternOfferDto> internOfferDtoList = new ArrayList<>();
        ;

        for (InternOffer offre : internOfferList) {
            InternOfferDto internOfferDto = new InternOfferDto(offre);
            internOfferDtoList.add(internOfferDto);
        }
        return internOfferDtoList;
    }

    public List<InternOfferDto> getInternOfferDeclined() {
        List<InternOffer> internOfferList = offerJobRepository.findAllDeclined();
        List<InternOfferDto> internOfferDtoList = new ArrayList<>();
        ;

        for (InternOffer offre : internOfferList) {
            InternOfferDto internOfferDto = new InternOfferDto(offre);
            internOfferDtoList.add(internOfferDto);
        }
        return internOfferDtoList;
    }


    InternOffer getById(long id) {
        try {
            return offerJobRepository.findById(id).orElseThrow(() -> new NullPointerException("Offre non trouvée"));
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