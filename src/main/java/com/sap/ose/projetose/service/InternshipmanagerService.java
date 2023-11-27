package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.FileDtoAll;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.InternshipmanagerDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.FileEntityRepository;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InternshipmanagerService {
    private final InternOfferService internOfferService;
    private final ProgrammeService programmeService;
    private final InternshipmanagerRepository internshipmanagerRepository;
    private final FileEntityRepository fileEntityRepository;
    private final EtudiantRepository etudiantRepository;
    private final NotificationService notificationService;

    Logger logger = LoggerFactory.getLogger(InternshipmanagerService.class);

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

    List<Internshipmanager> findAllManagers(){
        return internshipmanagerRepository.findAll();
    }

    public Internshipmanager findById(long id) {
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
    public Internshipmanager save(InternshipmanagerDto internshipmanagerDto) {
        Internshipmanager internshipmanager;
        try {
            Programme program = programmeService.findById(internshipmanagerDto.getProgrammeId());

            internshipmanager = internshipmanagerDto.fromDto();
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
        return internshipmanager;
    }

    @Transactional
    public void save(Internshipmanager internshipmanager) {
        try {
            Programme program = programmeService.findById(internshipmanager.getId());

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

    public Page<InternOfferDto> getSortedOffersByPage(int page, int size, String state, String sortField, String sortDirection, String session) {
        try {

            Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                    Sort.by(sortField).descending();
            Page<InternOfferDto> pageOffersDto = internOfferService.getSortedByPage(page, size, sort, state,session);
            return pageOffersDto;
        } catch (BadSortingFieldException e) {
            throw e;
        } catch (InvalidStateException e) {
            throw e;
        } catch (DatabaseException e) {
            logger.error("Erreur d'accès a la base de  données lors de la récupération des offres de stage", e);
            throw e;
        } catch (ServiceException e) {
            logger.error("Erreur inconnue lors de la récupération des offres de stage", e);
            throw e;
        }
    }
    @Transactional
    public List<FileDtoAll> getPendingCv() {
        try {
            //noinspection UnnecessaryLocalVariable
            List<FileDtoAll> pendingCv = fileEntityRepository.findAllStudentCvPending().isPresent()
                    ? fileEntityRepository.findAllStudentCvPending()
                                          .get()
                                          .stream()
                                          .map(FileDtoAll::new)
                                          .toList()
                    : null;
            return pendingCv;
        } catch (DatabaseException e) {
            logger.error("Erreur d'accès a la base de  données lors de la récupération des CV", e);
            throw e;
        } catch (ServiceException e) {
            logger.error("Erreur inconnue lors de la récupération des CV", e);
            throw e;
        }
    }
    @Transactional
    public FileDtoAll acceptCv(Long id) {
        try {
            File file = fileEntityRepository.findById(id).orElse(null);
            Etudiant etudiant = etudiantRepository.getReferenceById(file.getEtudiant().getId());
            file.setIsAccepted(State.ACCEPTED);
            file.setEtudiant(etudiant);
            fileEntityRepository.save(file);
            notificationService.saveNotificationByUser(etudiant.getId(),Notificationsi18n.cvAccepter);
            return new FileDtoAll(file);
        } catch (DatabaseException e) {
            logger.error("Erreur d'accès a la base de  données lors de la récupération des CV", e);
            throw e;
        } catch (ServiceException e) {
            logger.error("Erreur inconnue lors de la récupération des CV", e);
            throw e;
        }
    }
    @Transactional
    public FileDtoAll declineCv(Long id) {
        try {
            File file = fileEntityRepository.findById(id).orElse(null);
            Etudiant etudiant = etudiantRepository.getReferenceById(Optional.of(file.getEtudiant())
                    .map(Utilisateur::getId)
                    .orElseThrow());

            file.setIsAccepted(State.DECLINED);
            file.setEtudiant(etudiant);
            fileEntityRepository.save(file);
            notificationService.saveNotificationByUser(id,Notificationsi18n.cvRefuser);
            return new FileDtoAll(file);
        } catch (DatabaseException e) {
            logger.error("Erreur d'accès a la base de  données lors de la récupération des CV", e);
            throw e;
        } catch (ServiceException e) {
            logger.error("Erreur inconnue lors de la récupération des CV", e);
            throw e;
        }
    }
}
