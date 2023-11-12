package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.TemplateContractDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.TemplateContract;
import com.sap.ose.projetose.modeles.State;
import com.sap.ose.projetose.repository.TemplateContractRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class TemplateContractService {

    private final Logger logger = LoggerFactory.getLogger(TemplateContractService.class);
    private TemplateContractRepository templateContractRepository;

    @Autowired
    public TemplateContractService(TemplateContractRepository templateContractRepository) {
        this.templateContractRepository = templateContractRepository;
    }

    @Transactional
    public TemplateContractDto save(TemplateContractDto templateContractDto) {
        try {
            unsetDefaultPDF();


            File newFile = new File(templateContractDto.getContent(), templateContractDto.getFileName(), State.ACCEPTED);
            TemplateContract newTemplateContract = new TemplateContract(newFile);

            TemplateContract savedTemplateContract = templateContractRepository.save(newTemplateContract);
            return new TemplateContractDto(savedTemplateContract);
        } catch (DataAccessException | DatabaseException e) {
            logger.error("Erreur lors de la sauvegarde du PDF", e);
            throw new DatabaseException("");
        } catch (Exception e) {
            logger.error("Erreur lors de la sauvegarde du PDF", e);
            throw new ServiceException("");
        }
    }

    @Transactional
    public void unsetDefaultPDF() {
        try {
            TemplateContract templateContract = templateContractRepository.findTemplateContractByIsActive();
            if (templateContract != null) {
                templateContract.setIsActive(false);
                templateContractRepository.save(templateContract);
            }
        } catch (DataAccessException e) {
            logger.error("Erreur lors de la mise à jour du PDF par défaut", e);
            throw new DatabaseException("");
        } catch (Exception e) {
            logger.error("Erreur lors de la mise à jour du PDF par défaut", e);
            throw new ServiceException("");
        }
    }


    @Transactional
    public TemplateContract findCurrent() {
        try {
            return templateContractRepository.findTemplateContractByIsActive();
        } catch (DataAccessException e) {
            logger.error("Erreur lors de la récupération du PDF", e);
            throw new DatabaseException("");
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération du PDF", e);
            throw new ServiceException("");
        }
    }

    @Transactional
    public TemplateContractDto getCurrentPDF() {
        try {
            TemplateContract templateContract = templateContractRepository.findTemplateContractByIsActive();
            return new TemplateContractDto(templateContract);
        } catch (DataAccessException e) {
            logger.error("Erreur lors de la récupération du PDF par défaut", e);
            throw new DatabaseException("");
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération du PDF par défaut", e);
            throw new ServiceException("");
        }
    }
}
