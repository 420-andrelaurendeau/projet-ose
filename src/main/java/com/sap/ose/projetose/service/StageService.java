package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.InterviewDTO;
import com.sap.ose.projetose.dto.StageDto;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.StageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StageService {

    private final StageRepository stageRepository;
    private final EmployeurService employeurService;
    private final InternOfferService internOfferService;
    private final EtudiantService etudiantService;

    public StageService(StageRepository stageRepository, EmployeurService employeurService, InternOfferService internOfferService, EtudiantService etudiantService) {
        this.stageRepository = stageRepository;
        this.employeurService = employeurService;
        this.internOfferService = internOfferService;
        this.etudiantService = etudiantService;
    }

    @Transactional
    public List<StageDto> getAllStage(){
        return stageRepository.findAll().stream().map(stage -> new StageDto(stage.getId(), stage.getStudent().getId(),stage.getOffer().getId(),stage.getStateStudent(),stage.getStateEmployeur())).toList();
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

        StageDto stageReturn = new StageDto(stage.getId(),stage.getStudent().getId(),stage.getOffer().getId(),stage.getStateStudent(),stage.getStateEmployeur());
        return Optional.of(stageReturn);
    }

    @Transactional
    public List<StageDto> getStageStudentPending(long studentId){
        return stageRepository.findAllStudentPending(studentId).isPresent() ? stageRepository.findAllStudentPending(studentId).get().stream().map(stage -> new StageDto(stage.getId(), stage.getStudent().getId(), stage.getOffer().getId(),stage.getStateStudent(),stage.getStateEmployeur())).toList() : null;
    }
}
