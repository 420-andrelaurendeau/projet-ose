package com.sap.ose.projetose.service;

import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.ProgramNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProgrammeService {

    private final ProgrammeRepository programmeRepository;
    private final Logger logger = LoggerFactory.getLogger(ProgrammeService.class);

    @Autowired
    public ProgrammeService(ProgrammeRepository programmeRepository) {
        this.programmeRepository = programmeRepository;
    }

    Programme findById(long id) {
        try {
            return programmeRepository.findById(id).orElseThrow(() -> {
                logger.error("Programme non trouvé avec l'Id"+ id);
                return new ProgramNotFoundException();
            });
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès aux données lors de la récupération du programme avec l'ID :" + id, e);
            throw new DatabaseException("Erreur lors de la récupération du programme") {
            };
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération du programme avec l'ID :" + id, e);
            throw new ServiceException("Erreur lors de la récupération du programme");
        }
    }
}
