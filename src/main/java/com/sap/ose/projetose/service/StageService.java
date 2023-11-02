package com.sap.ose.projetose.service;

import com.sap.ose.projetose.controller.ReactOseController;
import com.sap.ose.projetose.dto.*;
import com.sap.ose.projetose.exception.BadSortingFieldException;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.InvalidStateException;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.StageRepository;
import jakarta.transaction.Transactional;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.*;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StageService {

    private final StageRepository stageRepository;
    private final EmployeurService employeurService;
    private final InternOfferService internOfferService;
    private final EtudiantService etudiantService;

    Logger logger = LoggerFactory.getLogger(ReactOseController.class);

    public StageService(StageRepository stageRepository, EmployeurService employeurService, InternOfferService internOfferService, EtudiantService etudiantService) {
        this.stageRepository = stageRepository;
        this.employeurService = employeurService;
        this.internOfferService = internOfferService;
        this.etudiantService = etudiantService;
    }

    @Transactional
    public List<StageDto> getAllStage() {
        return stageRepository.findAll().stream().map(stage -> new StageDto(stage.getId(), stage.getStudent().getId(), stage.getOffer().getId(), stage.getStateStudent(), stage.getStateEmployeur())).toList();
    }

    @Transactional
    public Optional<StageDto> save(StageDto stageDto) {
        Etudiant etudiant = etudiantService.findEtudiantById(stageDto.getStudent_id());
        InternOffer internOffer = internOfferService.findById(stageDto.getOffer_id());
        Employeur employeur = internOffer.getEmployeur();

        Stage stage = new Stage();
        stage.setStudent(etudiant);
        stage.setEmployeur(employeur);
        stage.setStateStudent(State.PENDING);
        stage.setStateEmployeur(State.PENDING);
        stage.setOffer(internOffer);

        stageRepository.save(stage);

        StageDto stageReturn = new StageDto(stage.getId(), stage.getStudent().getId(), stage.getOffer().getId(), stage.getStateStudent(), stage.getStateEmployeur());
        return Optional.of(stageReturn);
    }

    @Transactional
    public List<StageDto> getStageStudentPending(long studentId) {
        return stageRepository.findAllStudentPending(studentId).isPresent() ? stageRepository.findAllStudentPending(studentId).get().stream().map(stage -> new StageDto(stage.getId(), stage.getStudent().getId(), stage.getOffer().getId(), stage.getStateStudent(), stage.getStateEmployeur())).toList() : null;
    }

    @Transactional
    public Map<String, Long> getCountByState() {
        HashMap<String, Long> countMap = new HashMap<>(Map.of("PENDING", 0L, "ACCEPTED", 0L, "DECLINED", 0L, "TOTAL", 0L));

        try {
            List<Object[]> counts = stageRepository.getCountByState();
            long totalOffers = 0;

            for (Object[] count : counts) {
                String state = (String) count[0];
                Long stateCount = (Long) count[1];

                countMap.put(state, stateCount);

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


    @Transactional
    public Page<InternshipAgreementDto> getSortedByPage(int page, int size, Sort sort, String state) {
        try {
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<InternshipAgreementDto> internshipAgreementDtos;

            if (state == null)
                internshipAgreementDtos = stageRepository.findAll(pageable).map(stage -> new InternshipAgreementDto(
                        stage.getId(),
                        new EmployeurDto(stage.getEmployeur()),
                        new EtudiantDto(stage.getStudent()),
                        new InternOfferDto(stage.getOffer()),
                        stage.getStateStudent(),
                        stage.getStateEmployeur()
                ));
            else {
                internshipAgreementDtos = stageRepository.findAllByState(state, pageable).map(stage -> new InternshipAgreementDto(
                        stage.getId(),
                        new EmployeurDto(stage.getEmployeur()),
                        new EtudiantDto(stage.getStudent()),
                        new InternOfferDto(stage.getOffer()),
                        stage.getStateStudent(),
                        stage.getStateEmployeur()
                ));
            }

            return internshipAgreementDtos;

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
            throw new com.sap.ose.projetose.exception.ServiceException("Erreur lors de la récupération des offres d'emploi.");
        }
    }

    public InternshipAgreementDto findById(long id) {
        try {
            Stage stage = stageRepository.findById(id).orElseThrow();
            return new InternshipAgreementDto(
                    stage.getId(),
                    new EmployeurDto(stage.getEmployeur()),
                    new EtudiantDto(stage.getStudent()),
                    new InternOfferDto(stage.getOffer()),
                    stage.getStateStudent(),
                    stage.getStateEmployeur()
            );
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès à la base de données lors de la récupération des offres d'emploi.", e);
            throw new DatabaseException();
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération des offres d'emploi.", e);
            throw new com.sap.ose.projetose.exception.ServiceException("Erreur lors de la récupération des offres d'emploi.");
        }
    }

    Stage getById(long id) {
        return stageRepository.findById(id).get();
    }
}
