package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternshipmanagerDto;
import com.sap.ose.projetose.modeles.Internshipmanager;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InternshipmanagerService {

    private final InternshipmanagerRepository internshipmanagerRepository;

    private final ProgrammeService programmeService;

    Logger logger = LoggerFactory.getLogger(InternshipmanagerService.class);

    @Autowired
    public InternshipmanagerService(InternshipmanagerRepository internshipmanagerRepository, ProgrammeService programmeService) {
        this.internshipmanagerRepository = internshipmanagerRepository;
        this.programmeService = programmeService;
    }

    @Transactional
    public InternshipmanagerDto getById(long id) {
        try {
            Internshipmanager internshipmanager = internshipmanagerRepository.findById(id).orElseThrow(() -> new NullPointerException("Internshipmanager non trouvé"));
            return new InternshipmanagerDto(internshipmanager);
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

    Internshipmanager findById(long id) {
        try {
            return internshipmanagerRepository.findById(id).orElseThrow(() -> new NullPointerException("Internshipmanager non trouvé"));
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
}
