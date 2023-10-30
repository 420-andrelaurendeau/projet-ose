package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternshipmanagerDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.InternshipManagerNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.Internshipmanager;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InternshipmanagerService {

    private final InternshipmanagerRepository internshipmanagerRepository;
    private final ProgrammeService programmeService;

    Logger logger = LoggerFactory.getLogger(InternshipmanagerService.class);

    @Transactional
    public InternshipmanagerDto getInternshipManagerById(long id) {
        try {
            Internshipmanager internshipmanager = internshipmanagerRepository.findById(id).orElseThrow(InternshipManagerNotFoundException::new);
            return new InternshipmanagerDto(internshipmanager);
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

    Internshipmanager findInternshipManagerById(long id) {

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
