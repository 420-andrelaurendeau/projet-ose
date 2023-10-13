package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.InternshipmanagerDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.InternshipmanagerNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.Internshipmanager;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.modeles.State;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InternshipmanagerService {

    private final InternshipmanagerRepository internshipmanagerRepository;

    private final InternOfferService internOfferService;
    private final ProgrammeService programmeService;

    Logger logger = LoggerFactory.getLogger(InternshipmanagerService.class);

    @Autowired
    public InternshipmanagerService(InternshipmanagerRepository internshipmanagerRepository, InternOfferService internOfferService, ProgrammeService programmeService) {
        this.internshipmanagerRepository = internshipmanagerRepository;
        this.internOfferService = internOfferService;
        this.programmeService = programmeService;
    }

    @Transactional
    public InternshipmanagerDto getById(long id) {
        try {
            Internshipmanager internshipmanager = internshipmanagerRepository.findById(id).orElseThrow(InternshipmanagerNotFoundException::new);
            return new InternshipmanagerDto(internshipmanager);
        } catch (InternshipmanagerNotFoundException e) {
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

    Internshipmanager findById(long id) {

        try {
            return internshipmanagerRepository.findById(id).orElseThrow(InternshipmanagerNotFoundException::new);
        } catch (InternshipmanagerNotFoundException e) {
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
    public void save(InternshipmanagerDto internshipmanagerDto) {
        try {
            Programme program = programmeService.findById(internshipmanagerDto.getProgrammeId());

            Internshipmanager internshipmanager = internshipmanagerDto.fromDto();
            internshipmanager.setProgramme(program);

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

    public Page<InternOfferDto> getOffers(int page, int size) {
        try {

            Page<InternOfferDto> pageOffersDto = internOfferService.getPagableAllInternOffers(page, size);
            return pageOffersDto;
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès a la base de  données lors de la récupération des offres de stage", e);
            throw new DatabaseException("Erreur d'accès a la base de  données lors de la récupération des offres de stage") {
            };
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération des offres de stage", e);
            throw new ServiceException("Erreur inconnue lors de la récupération des offres de stage");
        }
    }
    public Page<InternOfferDto> getOffers(int page, int size, String state) {
        try {
            State stateEnum = State.valueOf(state);
            Page<InternOfferDto> pageOffersDto = internOfferService.getPagableInternOffersByState(page, size, stateEnum);
            return pageOffersDto;
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès a la base de  données lors de la récupération des offres de stage", e);
            throw new DatabaseException("Erreur d'accès a la base de  données lors de la récupération des offres de stage") {
            };
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération des offres de stage", e);
            throw new ServiceException("Erreur inconnue lors de la récupération des offres de stage");
        }
    }
}
