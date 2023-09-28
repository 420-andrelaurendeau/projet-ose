package com.sap.ose.projetose.service;

import com.sap.ose.projetose.controller.ReactOseController;
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
    private final EmployeurRepository employeurRepository;
    Logger logger = LoggerFactory.getLogger(ReactOseController.class);

    @Autowired
    public InternOfferService(InternOfferRepository offerJobRepository, EmployeurRepository employeurRepository, ProgrammeService programmeService) {
        this.offerJobRepository = offerJobRepository;
        this.employeurRepository = employeurRepository;
        this.programmeService = programmeService;
    }

    public InternOfferDto saveInterOfferJob(InternOfferDto internOfferDto) {
        try {

            Programme programme = programmeService.getProgrammeById(internOfferDto.getProgrammeId()).orElseThrow(() -> new NullPointerException("Programme non trouvé"));
            Employeur employeur = employeurRepository.findById(internOfferDto.getEmployeurId()).orElseThrow(() -> new NullPointerException("Employeur non trouvé"));

            InternOffer internOffer = internOfferDto.fromDto();
            internOffer.setProgramme(programme);
            internOffer.setEmployeur(employeur);

            InternOffer internOfferSuccess = offerJobRepository.save(internOffer);

            return new InternOfferDto(internOfferSuccess);

        } catch (DataIntegrityViolationException e) {
            logger.info(e.getMessage());
            throw new DataIntegrityViolationException("Erreur d'intégrité des données lors de la sauvegarde de l'offre d'emploi.");
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DataAccessException("Erreur d'accès aux données lors de la sauvegarde de l'offre d'emploi.") {};
        } catch (NullPointerException e) {
            logger.info(e.getMessage());
            throw new NullPointerException(e.getMessage());
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new RuntimeException("Erreur inconnue lors de la sauvegarde de l'offre d'emploi.");
        }
    }

    public List<InternOfferDto> getInternOfferAccepted(){
        List<InternOffer> internOfferList = offerJobRepository.findAllApproved();
        List<InternOfferDto> internOfferDtoList = new ArrayList<>();;

        for (InternOffer offre : internOfferList){
            InternOfferDto internOfferDto = new InternOfferDto(offre);
            internOfferDtoList.add(internOfferDto);
        }
        return internOfferDtoList;
    }

    public List<InternOfferDto> getInternOfferPending(){
        List<InternOffer> internOfferList = offerJobRepository.findAllPending();
        List<InternOfferDto> internOfferDtoList = new ArrayList<>();;

        for (InternOffer offre : internOfferList){
            InternOfferDto internOfferDto = new InternOfferDto(offre);
            internOfferDtoList.add(internOfferDto);
        }
        return internOfferDtoList;
    }

    public List<InternOfferDto> getInternOfferDeclined(){
        List<InternOffer> internOfferList = offerJobRepository.findAllDeclined();
        List<InternOfferDto> internOfferDtoList = new ArrayList<>();;

        for (InternOffer offre : internOfferList){
            InternOfferDto internOfferDto = new InternOfferDto(offre);
            internOfferDtoList.add(internOfferDto);
        }
        return internOfferDtoList;
    }


    InternOffer getById(long id) {
         try {
             InternOffer internOffer = offerJobRepository.findById(id).orElseThrow(() -> new NullPointerException("Offre non trouvée"));
             return internOffer;
         } catch (NullPointerException e) {
             logger.info(e.getMessage());
             throw new NullPointerException(e.getMessage());
         }
    }

}