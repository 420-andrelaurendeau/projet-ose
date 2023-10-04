package com.sap.ose.projetose.service;

import com.sap.ose.projetose.controller.ReactOseController;
import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.models.*;
import com.sap.ose.projetose.repository.InternshipOfferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipOfferService {

    private final InternshipOfferRepository offerJobRepository;
    private final FormationService formationService;
    private final EmployerService employerService;
    Logger logger = LoggerFactory.getLogger(ReactOseController.class);


    @Transactional
    public InternshipOfferDto saveInterOfferJob(InternshipOfferDto internshipOfferDto) {
        try {
            System.out.println(internshipOfferDto.getEmployeurId());

            if ( isApprovedOrDeclineById(internshipOfferDto.getId()))
                throw new OfferAlreadyReviewedException("L'offre a déjà été approuvée et ne peut pas être modifiée.");

            Formation formation = formationService.findById(internshipOfferDto.getProgrammeId());
            Employer employer = employerService.findById(internshipOfferDto.getEmployeurId());

            InternshipOffer internshipOffer = internshipOfferDto.fromDto();
            internshipOffer.setFormation(formation);
            internshipOffer.setEmployer(employer);
            internshipOffer.setState(State.PENDING);

            InternshipOffer savedOfferDto = offerJobRepository.save(internshipOffer);

            return new InternshipOfferDto(savedOfferDto);
        } catch (OfferAlreadyReviewedException e) {
            logger.error("L'offre a déjà été approuvée et ne peut pas être modifiée pour l'Id : " + internshipOfferDto.getId(), e);
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

    public List<InternshipOfferDto> getInternOfferAccepted(){
        List<InternshipOffer> internshipOfferList = offerJobRepository.findAllByStateIs();
        List<InternshipOfferDto> internshipOfferDtoList = new ArrayList<>();

        for (InternshipOffer offre : internshipOfferList){
            InternshipOfferDto internshipOfferDto = new InternshipOfferDto(offre);
            internshipOfferDtoList.add(internshipOfferDto);
        }
        return internshipOfferDtoList;
    }

    @Transactional
    public List<InternshipOfferDto> getInternOfferPending() {
        List<InternshipOffer> internshipOfferList = offerJobRepository.findAllPending();
        List<InternshipOfferDto> internshipOfferDtoList = new ArrayList<>();

        for (InternshipOffer offre : internshipOfferList){
            InternshipOfferDto internshipOfferDto = new InternshipOfferDto(offre);
            internshipOfferDtoList.add(internshipOfferDto);
        }
        return internshipOfferDtoList;
    }

    public List<InternshipOfferDto> getInternOfferDeclined(){
        List<InternshipOffer> internshipOfferList = offerJobRepository.findAllRejected();
        List<InternshipOfferDto> internshipOfferDtoList = new ArrayList<>();

        for (InternshipOffer offre : internshipOfferList){
            InternshipOfferDto internshipOfferDto = new InternshipOfferDto(offre);
            internshipOfferDtoList.add(internshipOfferDto);
        }
        return internshipOfferDtoList;
    }

    InternshipOfferDto getInterOfferById(Long id) {
        InternshipOffer internshipOffer = offerJobRepository.findById(id).orElse(null);
        return new InternshipOfferDto(internshipOffer);
    }

    InternshipOffer findById(long id){
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

    public List<InternshipOfferDto> getInternOffers(){
        List<InternshipOfferDto> internshipOfferDtoList = new ArrayList<>() ;
        for(InternshipOffer offer : offerJobRepository.findAll()){
            internshipOfferDtoList.add(new InternshipOfferDto(offer));
        }
        return internshipOfferDtoList;
    }

    boolean isApprovedOrDeclineById(long id) {
        return offerJobRepository.findById(id).filter(offer -> offer.getState() == State.ACCEPTED || offer.getState() == State.DECLINED).isPresent();
    }
}