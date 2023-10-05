package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternshipManagerDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.InternshipManagerNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.models.Formation;
import com.sap.ose.projetose.models.InternshipManager;
import com.sap.ose.projetose.repository.InternshipManagerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InternshipManagerService {
    private final InternshipManagerRepository internshipmanagerRepository;
    private final FormationService formationService;

    Logger logger = LoggerFactory.getLogger(InternshipManagerService.class);

    @Transactional
    public InternshipManagerDto getById(long id) {
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

    InternshipManager findById(long id) {

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

    @Transactional
    public void save(InternshipManagerDto internshipmanagerDto) {
        try {
            Formation program = formationService.findById(internshipmanagerDto.getProgrammeId());

            InternshipManager internshipmanager = internshipmanagerDto.fromDto();
            internshipmanager.setFormation(program);

            internshipmanagerRepository.save(internshipmanager);

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
