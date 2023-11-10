package com.sap.ose.projetose.service;

import com.sap.ose.projetose.controller.ReactOseController;
import com.sap.ose.projetose.dto.*;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.EtudiantNotFoundException;
import com.sap.ose.projetose.exception.FileNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.FileEntityRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {
    private final EtudiantRepository etudiantRepository;

    private final FileEntityRepository fileEntityRepository;

    private final ProgrammeService programmeService;
    Logger logger = LoggerFactory.getLogger(ReactOseController.class);

    public EtudiantService(EtudiantRepository etudiantRepository, ProgrammeService programmeService, FileEntityRepository fileEntityRepository) {
        this.etudiantRepository = etudiantRepository;
        this.programmeService = programmeService;
        this.fileEntityRepository = fileEntityRepository;
    }

    @Transactional
    public Optional<Etudiant> saveEtudiant(Etudiant etudiant) {
        try {
            Programme programme = programmeService.findById(etudiant.getProgramme().getId());
            etudiant.setProgramme(programme);
            return Optional.of(etudiantRepository.save(etudiant));
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde de l'etudiant") {
            };
        }
    }

    @Transactional
    public Optional<EtudiantDto> saveEtudiantInscription(EtudiantInscriptionDto etudiant) {
        try {
            Etudiant savingStudent = etudiant.fromDto();
            Programme programme = programmeService.findById(etudiant.getProgramme_id());
            savingStudent.setProgramme(programme);
            return Optional.of(new EtudiantDto(etudiantRepository.save(savingStudent)));
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde de l'etudiant") {
            };
        }
    }

    public List<EtudiantDto> getEtudiants() {
        List<EtudiantDto> dtos = new ArrayList<>();
        for (Etudiant etudiant : etudiantRepository.findAll()) {
            dtos.add(new EtudiantDto(etudiant.getNom(), etudiant.getPrenom(), etudiant.getPhone(), etudiant.getEmail(), etudiant.getMatricule(), etudiant.getProgramme().getId(), etudiant.getCv().stream().map(File::getId).toList(), etudiant.getInternshipsCandidate().stream().map(InternshipCandidates::getId).toList()));
        }
        return dtos;
    }

    public EtudiantDto getEtudiantById(Long id) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        return etudiant.map(value -> new EtudiantDto(value.getNom(), value.getPrenom(), value.getPhone(), value.getEmail(), value.getMatricule(), value.getProgramme().getId(), value.getCv().stream().map(File::getId).toList(), value.getInternshipsCandidate().stream().map(InternshipCandidates::getId).toList())).orElse(null);
    }

    Etudiant findEtudiantById(Long id) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        return etudiant.orElse(null);
    }

    public Etudiant findByMatricule(String matricule){
        Optional<Etudiant> etudiant = etudiantRepository.findByMatricule(matricule);
        return etudiant.orElse(null);
    }

    @Transactional
    public EtudiantDto updateCVByMatricule(String matricule, File cv){
        Etudiant etudiant = findByMatricule(matricule);
        List<File> cvs = new ArrayList<>();
        cvs.add(cv);
        cv.setEtudiant(etudiant);
        etudiant.setCv(cvs);
        etudiantRepository.save(etudiant);
        return new EtudiantDto(etudiant);
    }

    Etudiant findByEmail(String courriel) {
        return etudiantRepository.findByEmail(courriel).orElse(null);
    }

    @Transactional
    public List<StudentAppliedOffersDto> getOffersAppliedByEtudiant(long id) {
        try {
            Etudiant etudiant = etudiantRepository.findById(id).orElseThrow(EtudiantNotFoundException::new);
            List<InternshipCandidates> offersApplied = etudiant.getInternshipsCandidate();

            if (offersApplied == null)
                return new ArrayList<>();

            return offersApplied.stream().map(
                    (offerApplied) -> {
                        StudentAppliedOffersDto dto = new StudentAppliedOffersDto();

                        InternOfferDto offerDto = new InternOfferDto(offerApplied.getInternOffer());
                        offerDto.setInternshipCandidates(null);

                        List<FileDto> fileDtos = offerApplied.getFiles().stream().map(FileDto::new).toList();

                        dto.setAppliedOffer(offerDto);
                        dto.setAppliedFiles(fileDtos);

                        return dto;
                    }).toList();

        } catch (EtudiantNotFoundException e) {
            logger.error("Etudiant non trouvé avec l'id" + id, e);
            throw e;
        } catch (DataAccessException e) {
            logger.error("Erreur lors de la récupération des offres appliquées par l'étudiant avec l'Id :" + id, e);
            throw new DatabaseException("Erreur lors de la récupération des offres appliquées par l'étudiant");
        } catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération des offres appliquées par l'étudiant avec l'id :" + id, e);
            throw new ServiceException("Erreur lors de la récupération des offres appliquées par l'étudiant");
        }
    }

    @Transactional
    public List<FileDtoAll> getAllCvfromStudentById(long id) {
        try {
            List<FileDtoAll> cvs = fileEntityRepository.findAllByEtudiant_IdIs(id).isPresent() ? fileEntityRepository.findAllByEtudiant_IdIs(id).get().stream().map(file -> new FileDtoAll(file.getId(),file.getContent(),file.getFileName(),file.getIsAccepted(), new EtudiantDto(file.getEtudiant()))).toList() : null;
            if (cvs == null) {
                throw new FileNotFoundException("Aucun CV trouvé pour l'étudiant avec l'id " + id);
            }
            return cvs;
        }
        catch (FileNotFoundException e) {
            logger.error("Aucun CV trouvé pour l'étudiant avec l'id " + id, e);
            throw e;
        }
        catch (DataAccessException e) {
            logger.error("Erreur lors de la récupération des CV de l'étudiant avec l'id " + id, e);
            throw new DatabaseException("Erreur lors de la récupération des CV de l'étudiant");
        }
        catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération des CV de l'étudiant avec l'id " + id, e);
            throw new ServiceException("Erreur lors de la récupération des CV de l'étudiant");
        }
    }
    @Transactional
    public FileDtoAll setDefaultCv(long id, long cvId) {
        try {
            FileDtoAll fileDtoAll = null;
            List<File> cvs = fileEntityRepository.findAllByEtudiant_IdIs(id).isPresent() ? fileEntityRepository.findAllByEtudiant_IdIs(id).get() : null;
            if (cvs == null || cvs.isEmpty()) {
                throw new FileNotFoundException("Aucun CV trouvé pour l'étudiant avec l'id " + id);
            }
            for (File cv : cvs) {
                if (cv.getId() == cvId) {
                    if (cv.getIsAccepted() != State.ACCEPTED) {
                        throw new ServiceException("Le CV n'est pas encore accepté");
                    }
                    fileDtoAll = new FileDtoAll(cv.getId(),cv.getContent(),cv.getFileName(),cv.getIsAccepted(), new EtudiantDto(cv.getEtudiant()));
                    cv.getEtudiant().setActiveCv(cv);
                }
                fileEntityRepository.save(cv);
            }
            if (fileDtoAll == null) {
                throw new FileNotFoundException("Aucun CV trouvé avec l'id " + id);
            }
            return fileDtoAll;
        }
        catch (ServiceException e) {
            logger.error("Le CV n'est pas encore accepté", e);
            throw e;
        }
        catch (FileNotFoundException e) {
            logger.error("Aucun CV trouvé pour l'étudiant avec l'id " + id, e);
            throw e;
        }
        catch (DataAccessException e) {
            logger.error("Erreur lors de la récupération des CV de l'étudiant avec l'id " + id, e);
            throw new DatabaseException("Erreur lors de la récupération des CV de l'étudiant");
        }
        catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération des CV de l'étudiant avec l'id " + id, e);
            throw new ServiceException("Erreur lors de la récupération des CV de l'étudiant");
        }
    }
    @Transactional
    public FileDtoAll getDefaultCv(long id) {
        try {
            return new FileDtoAll(etudiantRepository.findById(id).orElseThrow(FileNotFoundException::new).getActiveCv());
        }
        catch (FileNotFoundException e) {
            logger.error("Aucun CV trouvé pour l'étudiant avec l'id " + id, e);
            throw e;
        }
        catch (DataAccessException e) {
            logger.error("Erreur lors de la récupération des CV de l'étudiant avec l'id " + id, e);
            throw new DatabaseException("Erreur lors de la récupération des CV de l'étudiant");
        }
        catch (Exception e) {
            logger.error("Erreur inconnue lors de la récupération des CV de l'étudiant avec l'id " + id, e);
            throw new ServiceException("Erreur lors de la récupération des CV de l'étudiant");
        }
    }
}