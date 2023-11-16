package com.sap.ose.projetose.service;

import com.sap.ose.projetose.controller.ReactOseController;
import com.sap.ose.projetose.dto.*;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.modeles.Contract;
import com.sap.ose.projetose.repository.StageRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.*;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StageService {


    private final StageRepository stageRepository;

    private final InternOfferService internOfferService;

    private final EtudiantService etudiantService;

    private final ContractService contractService;
    Logger logger = LoggerFactory.getLogger(ReactOseController.class);

    @Autowired
    public StageService(StageRepository stageRepository, InternOfferService internOfferService, EtudiantService etudiantService, ContractService contractService) {
        this.stageRepository = stageRepository;
        this.internOfferService = internOfferService;
        this.etudiantService = etudiantService;
        this.contractService = contractService;
    }

    @Transactional
    public List<StageDto> getAllStage() {
        return stageRepository.findAll().stream().map(stage -> new StageDto(stage.getId(), stage.getStudent().getId(), new InternOfferDto(stage.getOffer()), stage.getStateStudent(), stage.getStateEmployeur(), stage.getContract() != null ? stage.getContract().id : 0)).toList();
    }

    @Transactional
    public void save(Stage stage) {
        Employeur employeur = stage.getOffer().getEmployeur();
        stage.setEmployeur(employeur);
        stageRepository.save(stage);
    }

    @Transactional
    public Optional<StageDto> save(StageDto stageDto) {
        Etudiant etudiant = etudiantService.findEtudiantById(stageDto.getStudent_id());
        InternOffer internOffer = internOfferService.findById(stageDto.getOffer().getId());
        Employeur employeur = internOffer.getEmployeur();

        Stage stage = new Stage();
        stage.setStudent(etudiant);
        stage.setEmployeur(employeur);
        stage.setStateStudent(State.PENDING);
        stage.setStateEmployeur(State.PENDING);
        stage.setOffer(internOffer);

        stageRepository.save(stage);

        StageDto stageReturn = new StageDto(stage.getId(), stage.getStudent().getId(), new InternOfferDto(stage.getOffer()), stage.getStateStudent(), stage.getStateEmployeur(), stage.getContract() != null ? stage.getContract().id : 0);
        return Optional.of(stageReturn);
    }

    // TODO TEST temporaire
    @Transactional
    public StageDto saveTEST(StageDto stageDto) {
        Etudiant etudiant = etudiantService.findEtudiantById(stageDto.getStudent_id());
        InternOffer internOffer = internOfferService.findById(stageDto.getOffer().getId());
        Employeur employeur = internOffer.getEmployeur();

        Stage stage = new Stage();
        stage.setStudent(etudiant);
        stage.setEmployeur(employeur);
        stage.setStateStudent(stageDto.getStateStudent());
        stage.setStateEmployeur(stageDto.getStateEmployeur());
        stage.setOffer(internOffer);

        stage = stageRepository.save(stage);
        if(isContractAccepted(stage.getId()))
            stage = setContract(stage);

        return new StageDto(stage);
    }

    @Transactional
    public List<StageDto> getStageStudentPending(long studentId) {
        return stageRepository.findAllStudentPending(studentId).isPresent() ? stageRepository.findAllStudentPending(studentId).get().stream().map(stage -> new StageDto(stage.getId(), stage.getStudent().getId(), new InternOfferDto(stage.getOffer()), stage.getStateStudent(), stage.getStateEmployeur(), stage.getContract() != null ? stage.getContract().id : 0)).toList() : null;
    }

    @Transactional
    public Optional<Boolean> setStudentAccepted(StageDto stageDto) {

        Stage stage = stageRepository.findStageById(stageDto.getId()).orElse(null);
        if (stage != null) {
            stage.setStateStudent(State.ACCEPTED);
            if(isContractAccepted(stageDto.getId()))
                stage = setContract(stage);
            stageRepository.save(stage);

            return Optional.of(true);
        }
        return Optional.of(false);
    }

    @Transactional
    public Optional<Boolean> setStudentDeclined(StageDto stageDto) {
        Stage stage = stageRepository.findById(stageDto.getId()).orElse(null);
        if (stage != null) {
            stage.setStateStudent(State.DECLINED);
            if(isContractAccepted(stageDto.getId()))
                stage = setContract(stage);
            stageRepository.save(stage);

            return Optional.of(true);
        }
        return Optional.of(false);
    }

    @Transactional
    public StageDto saveEmployerOpinion(long stageId , String opinionState) {
        try {
            Stage savedStage = setEmployerOpinion(stageId, opinionState);

            if(isContractAccepted(stageId))
                savedStage = setContract(savedStage);

            return new StageDto(savedStage);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public Stage setEmployerOpinion(long stageId, String opinionState) {
        try {
            State stateEmployer = State.valueOf(opinionState);
            Stage stage = stageRepository.findStageById(stageId).orElseThrow( () -> new StageNotFoundException("Erreur lors de la récupération des offres d'emploi."));

            stage.setStateEmployeur(stateEmployer);

            return stageRepository.save(stage);
        }  catch (IllegalArgumentException | StageNotFoundException e) {
            logger.error("Erreur d'argument avec l'état de l'employeur sur l'entente de stage", e);
            throw e;
        } catch (DataAccessException e) {
            logger.error("Erreur de basse de données avec l'état de l'employeur sur l'entente de stage", e);
            throw new DatabaseException("");
        } catch (Exception e) {
            logger.error("Erreur avec l'état de l'employeur sur l'entente de stage", e);
            throw new ServiceException("");
        }
    }

    @Transactional
    public Stage setContract(Stage stage) {
        try {
            long idContract = contractService.createContract(stage);
            Contract contract = contractService.findById(idContract);
            stage.setContract(contract);
            return stage;
        } catch (Exception e) {
            logger.error("Erreur lors de la création du contrat.", e);
            throw new ServiceException("");
        }
    }

    boolean isContractAccepted(long stageId) {
        try {
            return stageRepository.isContractAccepted(stageId);
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès à la base de données lors de la vérification des états d'entente de stage.", e);
            throw new DatabaseException("");
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la vérification des états d'entente de stage.", e);
            throw new ServiceException("");
        }
    }

    @Transactional
    public Map<String, Long> getCountByStateGS(){
        List<Object[]> counts = stageRepository.getCountByState();
        return getCountByState(counts);
    }

    @Transactional
    public Map<String, Long> getCountByStateEmployeur(long id){
        List<Object[]> counts = stageRepository.getCountByStateByEmployeur(id);
        return getCountByState(counts);
    }

    @Transactional
    public Map<String, Long> getCountByStateStudent(long id){
        List<Object[]> counts = stageRepository.getCountByStateByStudent(id);
        return getCountByState(counts);
    }

    @Transactional
    public Map<String, Long> getCountByState(List<Object[]> counts) {
        HashMap<String, Long> countMap = new HashMap<>(Map.of("PENDING", 0L, "ACCEPTED", 0L, "DECLINED", 0L, "TOTAL", 0L));

        try {
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
            logger.error("Erreur d'accès à la base de données lors de la récupération du compte des ententes de stages.", e);
            throw new DatabaseException();
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération du compte des ententes de stages.", e);
            throw new ServiceException("Erreur lors de la récupération du compte des ententes de stages.");
        }
    }



    private Page<InternshipAgreementDto> stageToDtoPage(Page<Stage> stagePage){
        return stagePage.map(stage -> new InternshipAgreementDto(
                stage.getId(),
                new EmployeurDto(stage.getEmployeur()),
                new EtudiantDto(stage.getStudent()),
                new InternOfferDto(stage.getOffer()),
                stage.getStateStudent(),
                stage.getStateEmployeur(),
                stage.getContract() != null ? stage.getContract().id : 0
        ));
    }
    @Transactional
    public Page<InternshipAgreementDto> getSortedByPageOfEmployeur(int page, int size, Sort sort, String state, long id) {
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<InternshipAgreementDto> internshipAgreementDtos;

        if (state == null)
            internshipAgreementDtos = stageToDtoPage(stageRepository.findAllByEmployeurId(id, pageable));
        else {
            State stateEnum = State.valueOf(state);
            internshipAgreementDtos = stageToDtoPage(stageRepository.findAllByStateEmployeur(stateEnum.name(), pageable, id));
        }

        return internshipAgreementDtos;
    }

    @Transactional
    public Page<InternshipAgreementDto> getSortedByPageOfStudent(int page, int size, Sort sort, String state, long id){
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<InternshipAgreementDto> internshipAgreementDtos;
        if (state == null)
            internshipAgreementDtos = stageToDtoPage(stageRepository.findAllByStudentId(id, pageable));
        else {
            State stateEnum = State.valueOf(state);
            internshipAgreementDtos = stageToDtoPage(stageRepository.findAllByStateStudent(stateEnum.name(), pageable, id));
        }
        return internshipAgreementDtos;
    }

    @Transactional
    public Page<InternshipAgreementDto> getSortedByPage(int page, int size, Sort sort, String state, String session) {
        try {
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<InternshipAgreementDto> internshipAgreementDtos;

            if (state == null)
                internshipAgreementDtos = stageToDtoPage(stageRepository.findAllBySession(pageable, session));
            else {
                State stateEnum = State.valueOf(state);
                internshipAgreementDtos = stageToDtoPage(stageRepository.findAllByStateAndSession(stateEnum.name(),pageable, session));
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
}
