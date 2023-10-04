package com.sap.ose.projetose.service;

import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.ProgramNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.dto.FormationDto;
import com.sap.ose.projetose.models.Formation;
import com.sap.ose.projetose.repository.FormationRepository;
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
public class FormationService {

    private final FormationRepository formationRepository;
    private final Logger logger = LoggerFactory.getLogger(FormationService.class);

    @Autowired
    public FormationService(FormationRepository formationRepository) {
        this.formationRepository = formationRepository;
    }

    Formation findById(long id) {
        try {
            return formationRepository.findById(id).orElseThrow(ProgramNotFoundException::new);

        } catch (ProgramNotFoundException e) {
            logger.error("Formation non trouvé avec l'Id" + id);
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
    public FormationDto saveProgramme(String nom, String description) {
        try{
            return new FormationDto(formationRepository.save(new Formation(nom, description)));

        }catch (DataAccessException e){
            logger.info(e.getMessage());
            throw new DataAccessException("ErrorPage lors de la sauvegarde du programme") {};
        }
    }

    @Transactional
    public Optional<FormationDto> saveProgramme(FormationDto formationDTO) {
        return Optional.of(new FormationDto(formationRepository.save(formationDTO.fromDto())));
    }

    public List<FormationDto> getProgrammes() {
        List<FormationDto> dtos = new ArrayList<>();
        for (Formation formation : formationRepository.findAll()) {
            dtos.add(new FormationDto(formation));
        }
        return dtos;
    }

    public FormationDto getProgrammeById(Long id) {
        Optional<Formation> programme = formationRepository.findById(id);
        return programme.map(value -> new FormationDto(value.getId(), value.getNom(), value.getDescription())).orElse(null);
    }
}
