package com.sap.ose.projetose.services;

import com.sap.ose.projetose.dtos.InternshipManagerDto;
import com.sap.ose.projetose.exceptions.DatabaseException;
import com.sap.ose.projetose.exceptions.InternshipManagerNotFoundException;
import com.sap.ose.projetose.exceptions.ServiceException;
import com.sap.ose.projetose.models.InternshipManager;
import com.sap.ose.projetose.models.StudyProgram;
import com.sap.ose.projetose.repositories.InternshipManagerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class InternshipManagerService {

    private final InternshipManagerRepository internshipmanagerRepository;
    private final StudyProgramService studyProgramService;

    Logger logger = LoggerFactory.getLogger(InternshipManagerService.class);

    @Transactional
    public InternshipManagerDto getInternshipManagerById(long id) {
        try {
            InternshipManager internshipmanager = internshipmanagerRepository.findById(id).orElseThrow(InternshipManagerNotFoundException::new);
            return new InternshipManagerDto(internshipmanager);
        } catch (InternshipManagerNotFoundException e) {
            logger.error("Gestionnaire de stage non trouvée pour l'Id : " + id);
            throw e;
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès a la base de  données lors de la récupération du gestionnaire de stage avec l'Id : " + id, e);
            throw new DatabaseException("Erreur d'accès a la base de  données lors de la récupération du gestionnaire de stage") {
            };
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération du gestionnaire de stage avec l'Id : " + id, e);
            throw new ServiceException("Erreur inconnue lors de la récupération du gestionnaire de stage");
        }
    }

    InternshipManager findInternshipManagerById(long id) {

        try {
            return internshipmanagerRepository.findById(id).orElseThrow(InternshipManagerNotFoundException::new);
        } catch (InternshipManagerNotFoundException e) {
            logger.error("Gestionnaire de stage non trouvée pour l'Id : " + id);
            throw e;
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès a la base de  données lors de la récupération du gestionnaire de stage avec l'Id : " + id, e);
            throw new DatabaseException("Erreur d'accès a la base de  données lors de la récupération du gestionnaire de stage") {
            };
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération du gestionnaire de stage avec l'Id : " + id, e);
            throw new ServiceException("Erreur inconnue lors de la récupération du gestionnaire de stage");
        }
    }

}
