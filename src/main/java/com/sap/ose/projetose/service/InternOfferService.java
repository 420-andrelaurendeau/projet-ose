package com.sap.ose.projetose.service;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.sap.ose.projetose.controller.ReactOseController;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.modeles.State;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.InternOfferRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InternOfferService {

    private final InternOfferRepository offerJobRepository;
    private final EmployeurRepository employeurRepository;
    private final ProgrammeService programmeService;
    private final EmployeurService employeurService;
    Logger logger = LoggerFactory.getLogger(ReactOseController.class);

    @Autowired
    public InternOfferService(InternOfferRepository offerJobRepository, EmployeurRepository employeurRepository, ProgrammeService programmeService, EmployeurService employeurService) {
        this.offerJobRepository = offerJobRepository;
        this.employeurRepository = employeurRepository;
        this.programmeService = programmeService;
        this.employeurService = employeurService;
    }


    @Transactional
    public InternOfferDto saveInterOfferJob(InternOfferDto internOfferDto) {
        try {

            if (isApprovedOrDeclineById(internOfferDto.getId()))
                throw new OfferAlreadyReviewException("L'offre a déjà été approuvée et ne peut pas être modifiée.");

            Programme programme = programmeService.findById(internOfferDto.getProgrammeId());
            Employeur employeur = employeurService.findById(internOfferDto.getEmployeurId());

            InternOffer internOffer = new InternOffer(internOfferDto.fromDto());
            internOffer.setProgramme(programme);
            internOffer.setEmployeur(employeur);
            internOffer.setSession(getInternOfferByDates(internOfferDto.fromDto().getStartDate()));
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
    public List<InternOfferDto> getInternOfferAccepted() {
        List<InternOffer> internOfferList = offerJobRepository.findAllApproved();
        List<InternOfferDto> internOfferDtoList = new ArrayList<>();;

        for (InternOffer offre : internOfferList) {
            InternOfferDto internOfferDto = new InternOfferDto(offre);
            internOfferDtoList.add(internOfferDto);
        }
        return internOfferDtoList;
    }

    @Transactional
    public List<InternOfferDto> getInternOfferPending() {
        List<InternOffer> internOfferList = offerJobRepository.findAllPending();
        List<InternOfferDto> internOfferDtoList = new ArrayList<>();;

        for (InternOffer offre : internOfferList) {
            InternOfferDto internOfferDto = new InternOfferDto(offre);
            internOfferDtoList.add(internOfferDto);
        }
        return internOfferDtoList;
    }

    @Transactional
    public List<InternOfferDto> getInternOfferDeclined() {
        List<InternOffer> internOfferList = offerJobRepository.findAllDeclined();
        List<InternOfferDto> internOfferDtoList = new ArrayList<>();;

        for (InternOffer offre : internOfferList) {
            InternOfferDto internOfferDto = new InternOfferDto(offre);
            internOfferDtoList.add(internOfferDto);
        }
        return internOfferDtoList;
    }

    InternOfferDto getInterOfferById(Long id) {
        InternOffer internOffer = offerJobRepository.findById(id).orElse(null);
        return new InternOfferDto(internOffer);
    }

    public InternOffer findById(long id) {
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

    public List<InternOfferDto> getAllInternOffers() {
        List<InternOfferDto> internOfferDtoList = new ArrayList<>();
        for (InternOffer offer : offerJobRepository.findAll()) {
            internOfferDtoList.add(new InternOfferDto(offer));
        }
        return internOfferDtoList;
    }

    @Transactional
    public Page<InternOfferDto> getSortedByPage(int page, int size, Sort sort, String state) {
        try {
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<InternOfferDto> pageOffer;

            if (state == null)
                pageOffer = offerJobRepository.findAll(pageable).map(InternOfferDto::new);
            else {
                State stateEnum = State.valueOf(state);
                pageOffer = offerJobRepository.findAllByState(stateEnum, pageable).map(InternOfferDto::new);
            }

            return pageOffer;

        } catch (PropertyReferenceException e) {
            logger.error("Le champ de tri n'est pas valide : " + sort);
            throw new BadSortingFieldException(sort.toString());
        } catch (IllegalArgumentException e) {
            logger.error("L'état de l'offre d'emploi est invalide : " + state, e);
            throw new InvalidStateException(state);
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès à la base de données lors de la récupération des offres d'emploi.", e);
            throw new DatabaseException();
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération des offres d'emploi.", e);
            throw new ServiceException("Erreur lors de la récupération des offres d'emploi.");
        }
    }

    public Map<String, Long> getCountByState() {
        HashMap<String, Long> countMap = new HashMap<>(Map.of("PENDING", 0L, "ACCEPTED", 0L, "DECLINED", 0L, "TOTAL", 0L));
        try {
            List<Object[]> counts = offerJobRepository.getCountByState();
            long totalOffers = 0;
            for (Object[] count : counts) {
                Long stateCount = (Long) count[1];
                countMap.put(count[0].toString(), stateCount);

                totalOffers += stateCount;

            }

            countMap.put("TOTAL", totalOffers);

            countMap.forEach((key, value) -> logger.info(key + " : " + value));
            return countMap;
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès à la base de données lors de la récupération des offres d'emploi.", e);
            throw new DatabaseException();
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération des offres d'emploi.", e);
            throw new ServiceException("Erreur lors de la récupération des offres d'emploi.");
        }
    }

    boolean isApprovedOrDeclineById(long id) {
        return offerJobRepository.findById(id).filter(offer -> offer.getState() == State.ACCEPTED || offer.getState() == State.DECLINED).isPresent();
    }

    @Transactional
    public List<InternOfferDto> getInternOffer(){
        List<InternOfferDto> internOfferDtos = new ArrayList<>();
        for (InternOffer internOffer : offerJobRepository.findAll()) {
            internOfferDtos.add(new InternOfferDto());
        }
        return internOfferDtos;
    }

    @Transactional
    public Page<InternOfferDto> getInternOfferByEmployeurEmail(String email, int page, int size, String sortField, String sortDirection) {

        Sort sort = null;
        try {
            sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                    Sort.by(sortField).descending();
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<InternOfferDto> pageOffer;
            Long id = employeurRepository.findByEmail(email).get().getId();
            pageOffer = offerJobRepository.findAllById(id, pageable).map(InternOfferDto::new);
            return pageOffer;
        } catch (PropertyReferenceException e) {
            logger.error("Le champ de tri n'est pas valide : " + sort);
            assert sort != null;
            throw new BadSortingFieldException(sort.toString());
        } catch (IllegalArgumentException e) {
            logger.error("L'état de l'offre d'emploi est invalide : " + sort);
            assert sort != null;
            throw new InvalidStateException(sort.toString());
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès à la base de données lors de la récupération des offres d'emploi.", e);
            throw new DatabaseException();
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération des offres d'emploi.", e);
            throw new ServiceException("Erreur lors de la récupération des offres d'emploi.");
        }
    }

    public InternOfferDto getById(Long id) {
        InternOffer internOffer = offerJobRepository.findById(id).orElse(null);
        return new InternOfferDto(internOffer);
    }

    public String getInternOfferByDates(LocalDate date){
        int month = date.getMonthValue(); // Retrieve the month as an integer (1-12)

        if (month >= 3 && month <= 5) {
            return "Spring"+date.getYear();
        } else if (month >= 6 && month <= 8) {
            return "Summer"+date.getYear();
        } else if (month >= 9 && month <= 11) {
            return "Autumn"+date.getYear();
        } else if (month == 12 || month <= 2) {
           return "Winter"+date.getYear();
        }else {
            return "No specific offers for this month";
        }

    }

    public List<String> getAllSeasons() {
        return offerJobRepository.getAllSeasons();
    }

    @Transactional
    public List<InternOfferDto> getStudentOfferBySeason(String season) {
        List<InternOffer> internOffers = offerJobRepository.getStudentOffersBySeason(season);
        List<InternOfferDto> internOfferDtoList = new ArrayList<>();

        for (InternOffer i : internOffers){
            internOfferDtoList.add(new InternOfferDto(i));
        }

        return internOfferDtoList;
    }

    public List<InternOfferDto> getEmployeurOfferBySeason(String selectedOption, String email) {

    }
}