package com.sap.ose.projetose.service;

import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.ProgramNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.controller.ReactOseController;
import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeService {

    ProgrammeRepository programmeRepository;
    Logger logger = LoggerFactory.getLogger(ReactOseController.class);
    private final ProgrammeRepository programmeRepository;
    private final Logger logger = LoggerFactory.getLogger(ProgrammeService.class);

    @Autowired
    public ProgrammeService(ProgrammeRepository programmeRepository) {
        this.programmeRepository = programmeRepository;
    }

    Programme findById(long id) {
        try {
            return programmeRepository.findById(id).orElseThrow(ProgramNotFoundException::new);

        } catch (ProgramNotFoundException e) {
            logger.error("Programme non trouvé avec l'Id" + id);
            throw e;
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès aux données lors de la récupération du programme avec l'ID :" + id, e);
            throw new DatabaseException("Erreur lors de la récupération du programme") {
            };
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération du programme avec l'ID :" + id, e);
            throw new ServiceException("Erreur lors de la récupération du programme");
        }
    }

    @Transactional
    public ProgrammeDto saveProgramme(String nom, String description) {
        try{
            return new ProgrammeDto(programmeRepository.save(new Programme(nom, description)));

        }catch (DataAccessException e){
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde du programme") {};
        }
    }

    @Transactional
    public Optional<ProgrammeDto> saveProgramme(ProgrammeDto programmeDTO) {
        return Optional.of(new ProgrammeDto(programmeRepository.save(programmeDTO.fromDto())));
    }

    public List<ProgrammeDto> getProgrammes() {
        List<ProgrammeDto> dtos = new ArrayList<>();
        for (Programme programme : programmeRepository.findAll()) {
            dtos.add(new ProgrammeDto(programme));
        }
        return dtos;
    }

    public ProgrammeDto getProgrammeById(Long id) {
        Optional<Programme> programme = programmeRepository.findById(id);
        return programme.map(value -> new ProgrammeDto(value.getId(), value.getNom(), value.getDescription())).orElse(null);
    }
}
