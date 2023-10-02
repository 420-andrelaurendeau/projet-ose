package com.sap.ose.projetose.service;

import com.sap.ose.projetose.controller.ReactOseController;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Etats;
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
import java.util.Optional;

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
            internOffer.setStatus(Etats.Pending.toString());
            internOffer.setInternshipCandidates(new ArrayList<>());

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
        List<InternOfferDto> internOfferAcceptedDtos = new ArrayList<>();
        for(InternOffer internOffer : offerJobRepository.findAll()){
            if (internOffer.getStatus().equals(Etats.Accepted.toString())){
                internOfferAcceptedDtos.add(new InternOfferDto(internOffer));
            }
        }
        return internOfferAcceptedDtos;
    }

    public List<InternOfferDto> getInternOffer(){
        List<InternOfferDto> internOfferDtos = new ArrayList<>();
        for(InternOffer internOffer : offerJobRepository.findAll()){
            internOfferDtos.add(new InternOfferDto());
        }
        return internOfferDtos;
    }

    @Transactional
    public List<InternOfferDto> getInternOfferByEmployeurEmail(String email){
        List<InternOfferDto> internOfferDtos = new ArrayList<>();
        List<InternOffer> internOffers = employeurRepository.findByEmail(email).get().getInternOffers();
        for(InternOffer internOffer : internOffers){
                internOfferDtos.add(new InternOfferDto(internOffer));
        }
        return internOfferDtos;
    }

}