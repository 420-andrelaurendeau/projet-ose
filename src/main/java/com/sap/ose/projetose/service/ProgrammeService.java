package com.sap.ose.projetose.service;

import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.ProgramNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.dto.ProgramDto;
import com.sap.ose.projetose.models.Program;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeService {

    private final ProgrammeRepository programmeRepository;
    private final Logger logger = LoggerFactory.getLogger(ProgrammeService.class);

    @Autowired
    public ProgrammeService(ProgrammeRepository programmeRepository) {
        this.programmeRepository = programmeRepository;
    }

    Program findById(long id) {
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
    public ProgramDto saveProgramme(String nom, String description) {
        try{
            return new ProgramDto(programmeRepository.save(new Program(nom, description)));

        }catch (DataAccessException e){
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde du programme") {};
        }
    }

    @Transactional
    public Optional<ProgramDto> saveProgramme(ProgramDto programDTO) {
        return Optional.of(new ProgramDto(programmeRepository.save(programDTO.toNewProgram())));
    }

    public List<ProgramDto> getProgrammes() {
        List<ProgramDto> dtos = new ArrayList<>();
        for (Program program : programmeRepository.findAll()) {
            dtos.add(new ProgramDto(program));
        }
        return dtos;
    }

    public ProgramDto getProgrammeById(Long id) {
        Optional<Program> programme = programmeRepository.findById(id);
        return programme.map(value -> new ProgramDto(value.getId(), value.getNom(), value.getDescription())).orElse(null);
    }
}
