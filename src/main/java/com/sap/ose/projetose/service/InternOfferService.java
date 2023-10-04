package com.sap.ose.projetose.service;

import com.sap.ose.projetose.controller.ReactOseController;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.InternOfferRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InternOfferService {

    private final InternOfferRepository offerJobRepository;
    private final ProgrammeService programmeService;
    private final EmployeurService employeurService;
    Logger logger = LoggerFactory.getLogger(ReactOseController.class);

    @Autowired
    public InternOfferService(InternOfferRepository offerJobRepository, ProgrammeService programmeService, EmployeurService employeurService) {
        this.offerJobRepository = offerJobRepository;
        this.programmeService = programmeService;
        this.employeurService = employeurService;
    }


    @Transactional
    public InternOfferDto saveInterOfferJob(InternOfferDto internOfferDto) {
        try {
            System.out.println(internOfferDto.getEmployeurId());

            if ( isApprovedOrDeclineById(internOfferDto.getId()))
                throw new OfferAlreadyReviewException("L'offre a déjà été approuvée et ne peut pas être modifiée.");

            Programme programme = programmeService.findById(internOfferDto.getProgrammeId());
            Employeur employeur = employeurService.findById(internOfferDto.getEmployeurId());

            InternOffer internOffer = internOfferDto.fromDto();
            internOffer.setProgramme(programme);
            internOffer.setEmployeur(employeur);
            internOffer.setState(State.PENDING);

            InternOffer savedOfferDto = offerJobRepository.save(internOffer);

            return new InternOfferDto(savedOfferDto);
        } catch (OfferAlreadyReviewException e) {
            logger.error("L'offre a déjà été approuvée et ne peut pas être modifiée pour l'Id : " + internOfferDto.getId(), e);
            throw e;
        } catch (ProgramNotFoundException e) {
            throw new ProgramNotFoundException();
        } catch (EmployerNotFoundException e) {
            throw new EmployerNotFoundException();
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès à la base de données lors de la sauvegarde de l'offre d'emploi.", e);
            throw new DatabaseException("Erreur lors de la sauvegarde de l'offre d'emploi.");
        } catch (Exception e) {
            logger.error("Erreur inconnu lors de la sauvegarde de l'offre d'emploi.", e);
            throw new ServiceException("Erreur lors de la sauvegarde de l'offre d'emploi.");
        }
    }

    @Transactional
    public List<InternOfferDto> getInternOfferAccepted(){
        List<InternOffer> internOfferList = offerJobRepository.findAllApproved();
        List<InternOfferDto> internOfferDtoList = new ArrayList<>();;

        for (InternOffer offre : internOfferList){
            InternOfferDto internOfferDto = new InternOfferDto(offre);
            internOfferDtoList.add(internOfferDto);
        }
        return internOfferDtoList;
    }

    @Transactional
    public List<InternOfferDto> getInternOfferPending() {
        List<InternOffer> internOfferList = offerJobRepository.findAllPending();
        List<InternOfferDto> internOfferDtoList = new ArrayList<>();;

        for (InternOffer offre : internOfferList){
            InternOfferDto internOfferDto = new InternOfferDto(offre);
            internOfferDtoList.add(internOfferDto);
        }
        return internOfferDtoList;
    }

    @Transactional
    public List<InternOfferDto> getInternOfferDeclined(){
        List<InternOffer> internOfferList = offerJobRepository.findAllDeclined();
        List<InternOfferDto> internOfferDtoList = new ArrayList<>();;

        for (InternOffer offre : internOfferList){
            InternOfferDto internOfferDto = new InternOfferDto(offre);
            internOfferDtoList.add(internOfferDto);
        }
        return internOfferDtoList;
    }

    InternOfferDto getInterOfferById(Long id) {
        InternOffer internOffer = offerJobRepository.findById(id).orElse(null);
        return new InternOfferDto(internOffer);
    }

    InternOffer findById( long id){
        try {
            return offerJobRepository.findById(id).orElseThrow(OfferNotFoundException::new);
        } catch (OfferNotFoundException e) {
            logger.error("Offre d'emploi non trouvée pour l'Id : " + id);
            throw new OfferNotFoundException();
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès à la base de données lors de la récupération de l'offre d'emploi avec l'ID : " + id, e);
            throw new DatabaseException("Erreur lors de la récupération de l'offre d'emploi.");
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération de l'offre d'emploi avec l'ID : " + id, e);
            throw new ServiceException("Erreur lors de la récupération de l'offre d'emploi.");
        }
    }

    public List<InternOfferDto> getAllInternOffers(){
        List<InternOfferDto> internOfferDtoList = new ArrayList<>() ;
        for(InternOffer offer : offerJobRepository.findAll()){
            internOfferDtoList.add(new InternOfferDto(offer));
        }
        return internOfferDtoList;
    }

    boolean isApprovedOrDeclineById(long id) {
        return offerJobRepository.findById(id).filter(offer -> offer.getState() == State.ACCEPTED || offer.getState() == State.DECLINED).isPresent();
    }
}