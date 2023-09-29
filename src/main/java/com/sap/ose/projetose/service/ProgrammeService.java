package com.sap.ose.projetose.service;

import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ProgrammeService {

    private final ProgrammeRepository programmeRepository;
    private final Logger logger = LoggerFactory.getLogger(ProgrammeService.class);

    @Autowired
    public ProgrammeService(ProgrammeRepository programmeRepository) {
        this.programmeRepository = programmeRepository;
    }

    Programme findById(int id) {
        try {
            return programmeRepository.findById(id).orElseThrow(() -> new NullPointerException("Programme non trouvé"));
        } catch (DataIntegrityViolationException e) {
            logger.info(e.getMessage());
            throw new DataIntegrityViolationException("Erreur d'intégrité des données lors de la sauvegarde de l'offre d'emploi.");
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DataAccessException("Erreur d'accès aux données lors de la sauvegarde de l'offre d'emploi.") {
            };
        } catch (NullPointerException e) {
            logger.info(e.getMessage());
            throw new NullPointerException(e.getMessage());
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new RuntimeException("Erreur inconnue lors de la sauvegarde de l'offre d'emploi.");
        }
    }

}
