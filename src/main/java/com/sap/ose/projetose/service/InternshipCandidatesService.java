package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternshipCandidatesDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.EtudiantNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.FileEntityRepository;
import com.sap.ose.projetose.repository.InternshipCandidatesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class InternshipCandidatesService {

    private final InternshipCandidatesRepository internshipCandidatesRepository;
    private final InternOfferService internOfferService;
    private final EtudiantService etudiantService;
    private final InterviewService interviewService;
    private final FileEntityRepository fileEntityRepository;

    private final NotificationService notificationService;
    private final Logger logger = LoggerFactory.getLogger(InternshipCandidatesService.class);

    public InternshipCandidatesService(InternshipCandidatesRepository internshipCandidatesRepository, InternOfferService internOfferService, EtudiantService etudiantService, InterviewService interviewService, FileEntityRepository fileEntityRepository, NotificationService notificationService) {
        this.internshipCandidatesRepository = internshipCandidatesRepository;
        this.internOfferService = internOfferService;
        this.etudiantService = etudiantService;
        this.interviewService = interviewService;
        this.fileEntityRepository = fileEntityRepository;
        this.notificationService = notificationService;
    }

    @Transactional
    public InternshipCandidatesDto saveCandidates(InternshipCandidatesDto internshipCandidatesDto){
        try{
            InternshipCandidates internshipCandidates = new InternshipCandidates(internshipCandidatesDto.fromDto());
            Etudiant etudiant = etudiantService.findEtudiantById(internshipCandidatesDto.getEtudiant().getId());
            InternOffer internOffer = internOfferService.findById(internshipCandidatesDto.getInternOfferJob().getId());
            List<File> files = internshipCandidatesDto.getFiles() == null ? new ArrayList<>() : internshipCandidatesDto.getFiles().stream().map(FileDto::fromDto).toList();

            internshipCandidates.setEtudiant(etudiant);
            internshipCandidates.setInternOffer(internOffer);
            internshipCandidates.setFiles(files);

            for (File file : files) {
                file.setInternshipCandidates(internshipCandidates);
            }

            fileEntityRepository.saveAll(files);

            internshipCandidatesRepository.save(internshipCandidates);

            notificationService.saveNotificationByUser(internOffer.getEmployeur().getId(), Notificationsi18n.studentApplyOnOffer);
            return new InternshipCandidatesDto(internshipCandidates);

        }catch (DataAccessException e){
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde du candidats") {};
        }catch (NullPointerException e) {
            logger.info(e.getMessage());
            throw new NullPointerException(e.getMessage());
        } catch (Exception e) {
            logger.info(e.getMessage());
            logger.info(e.getCause().getMessage());
            throw new RuntimeException("Erreur inconnue lors de la sauvegarde de l'offre d'emploi.");
        }
    }
    @Transactional
    public InternshipCandidatesDto saveCandidates(InternshipCandidates internshipCandidates){
        try{
            internshipCandidatesRepository.save(internshipCandidates);
            return new InternshipCandidatesDto(internshipCandidates);
        }catch (DataAccessException e){
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde du candidats") {};
        }catch (NullPointerException e) {
            logger.info(e.getMessage());
            throw new NullPointerException(e.getMessage());
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new RuntimeException("Erreur inconnue lors de la sauvegarde de l'offre d'emploi.");
        }
    }

    @Transactional
    public List<InternshipCandidatesDto> getInternshipCandidatesByOfferId(Long id){
        try{
            List<InternshipCandidates> internshipCandidates = internshipCandidatesRepository.findAllByInternOfferId(id);
            return InternshipCandidatesDto.fromList(internshipCandidates);
        }catch (DataAccessException e){
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde du candidats") {};
        }catch (NullPointerException e) {
            logger.info(e.getMessage());
            throw new NullPointerException(e.getMessage());
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new RuntimeException("Erreur inconnue lors de la sauvegarde de l'offre d'emploi.");
        }
    }

@Transactional
    public List<InternshipCandidatesDto> getInternshipCandidatesByIds(String ids) {
        try{
            List<Long> idsLong = new ArrayList<>();
            for (String id : ids.split(",")) {
                idsLong.add(Long.parseLong(id));
            }
            List<InternshipCandidates> internshipCandidates = internshipCandidatesRepository.findAllById(idsLong);
            List<InternshipCandidatesDto> internshipCandidatesDtoList = InternshipCandidatesDto.fromList(internshipCandidates);
           for (InternshipCandidatesDto internshipCandidatesDto : internshipCandidatesDtoList) {
                Date date = Objects.requireNonNull(interviewService.getInterview(internshipCandidatesDto.getEtudiant().getId(), internshipCandidatesDto.getInternOfferJob().getId()).orElse(null)).getDate();
                internshipCandidatesDto.setDate(date);
           }
            return internshipCandidatesDtoList;
        }catch (DataAccessException e){
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde du candidats") {};
        }catch (NullPointerException e) {
            logger.info(e.getMessage());
            throw new NullPointerException(e.getMessage());
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new RuntimeException("Erreur inconnue lors de la sauvegarde de l'offre d'emploi.");
        }
    }

    @Transactional
    public InternshipCandidatesDto acceptCandidates(Long internshipCandidatesId) {
        try{
            InternshipCandidates internshipCandidates = internshipCandidatesRepository.findById(internshipCandidatesId).orElseThrow(() -> new EtudiantNotFoundException("Candidat non trouvé"));
            internshipCandidates.setState(State.ACCEPTED);
            internshipCandidatesRepository.save(internshipCandidates);
            return new InternshipCandidatesDto(internshipCandidates);
        }catch (DataAccessException e){
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde du candidats") {};
        }catch (NullPointerException e) {
            logger.info(e.getMessage());
            throw new NullPointerException(e.getMessage());
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new RuntimeException("Erreur inconnue lors de la sauvegarde de l'offre d'emploi.");
        }
    }

    @Transactional
    public InternshipCandidatesDto declineCandidates(Long internshipCandidatesId) {
        try{
            InternshipCandidates internshipCandidates = internshipCandidatesRepository.findById(internshipCandidatesId).orElseThrow(() -> new EtudiantNotFoundException("Candidat non trouvé"));
            internshipCandidates.setState(State.DECLINED);
            internshipCandidatesRepository.save(internshipCandidates);
            return new InternshipCandidatesDto(internshipCandidates);
        }catch (DataAccessException e){
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde du candidats") {};
        }catch (NullPointerException e) {
            logger.info(e.getMessage());
            throw new NullPointerException(e.getMessage());
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new RuntimeException("Erreur inconnue lors de la sauvegarde de l'offre d'emploi.");
        }
    }

    @Transactional
    public List<InternshipCandidatesDto> getDeclinedCandidates() {
        try {
            List<InternshipCandidates> internshipCandidatesList = internshipCandidatesRepository.findAllDeclined();
            List<InternshipCandidatesDto> internshipCandidatesDtoList = new ArrayList<>();
            for (InternshipCandidates internshipCandidates : internshipCandidatesList) {
                internshipCandidatesDtoList.add(new InternshipCandidatesDto(internshipCandidates));
            }
            return internshipCandidatesDtoList;
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès à la base de données lors de la récupération des candidats refusés", e);
            throw new DatabaseException("Erreur lors de la récupération des candidats refusés.");
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération des candidats refusés", e);
            throw new ServiceException("Erreur lors de la récupération des candidats refusés.");
        }
    }

    @Transactional
    public List<InternshipCandidatesDto> getPendingCandidates() {
        try {
            List<InternshipCandidates> internshipCandidatesList = internshipCandidatesRepository.findAllPending();
            List<InternshipCandidatesDto> internshipCandidatesDtoList = new ArrayList<>();
            for (InternshipCandidates internshipCandidates : internshipCandidatesList) {
                internshipCandidatesDtoList.add(new InternshipCandidatesDto(internshipCandidates));
            }
            return internshipCandidatesDtoList;
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès à la base de données lors de la récupération des candidats en attente", e);
            throw new DatabaseException("Erreur lors de la récupération des candidats en attente.");
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération des candidats en attente", e);
            throw new ServiceException("Erreur lors de la récupération des candidats en attente.");
        }
    }
    @Transactional
    public List<InternshipCandidatesDto> getAcceptedCandidates() {
        try {
            List<InternshipCandidates> internshipCandidatesList = internshipCandidatesRepository.findAllAccepted();
            List<InternshipCandidatesDto> internshipCandidatesDtoList = new ArrayList<>();
            for (InternshipCandidates internshipCandidates : internshipCandidatesList) {
                internshipCandidatesDtoList.add(new InternshipCandidatesDto(internshipCandidates));
            }
            return internshipCandidatesDtoList;
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès à la base de données lors de la récupération des candidats acceptés", e);
            throw new DatabaseException("Erreur lors de la récupération des candidats acceptés.");
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération des candidats acceptés", e);
            throw new ServiceException("Erreur lors de la récupération des candidats acceptés.");
        }
    }

    public InternshipCandidatesDto getInternshipCandidateById(long id){
        Optional<InternshipCandidates> internshipCandidates = internshipCandidatesRepository.findById(id);
        return internshipCandidates.map(value -> new InternshipCandidatesDto()).orElse(null);

    }

    @Transactional
    public List<InternshipCandidatesDto> getCandidates() {
        try {
            List<InternshipCandidates> internshipCandidatesList = internshipCandidatesRepository.findAll();
            List<InternshipCandidatesDto> internshipCandidatesDtoList = new ArrayList<>();
            for (InternshipCandidates internshipCandidates : internshipCandidatesList) {
                internshipCandidatesDtoList.add(new InternshipCandidatesDto(internshipCandidates));
            }
            return internshipCandidatesDtoList;
        } catch (DataAccessException e) {
            logger.error("Erreur d'accès à la base de données lors de la récupération des candidats", e);
            throw new DatabaseException("Erreur lors de la récupération des candidats.");
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération des candidats", e);
            throw new ServiceException("Erreur lors de la récupération des candidats.");
        }
    }
}