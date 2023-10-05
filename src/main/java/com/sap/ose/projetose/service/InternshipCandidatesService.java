package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternshipApplicationDto;
import com.sap.ose.projetose.models.InternshipOffer;
import com.sap.ose.projetose.models.Student;
import com.sap.ose.projetose.models.File;
import com.sap.ose.projetose.models.InternshipApplication;
import com.sap.ose.projetose.repository.InternshipCandidatesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InternshipCandidatesService {

    private final InternshipCandidatesRepository internshipCandidatesRepository;
    private final InternOfferService internOfferService;
    private final EtudiantService etudiantService;

    private final FileService fileService;

    private final Logger logger = LoggerFactory.getLogger(InternshipCandidatesService.class);

    public InternshipCandidatesService(InternshipCandidatesRepository internshipCandidatesRepository, InternOfferService internOfferService, EtudiantService etudiantService, FileService fileService) {
        this.internshipCandidatesRepository = internshipCandidatesRepository;
        this.internOfferService = internOfferService;
        this.etudiantService = etudiantService;
        this.fileService = fileService;
    }

    @Transactional
    public InternshipApplicationDto saveCandidates(InternshipApplicationDto internshipApplicationDto){
        try{
            InternshipApplication internshipApplication = internshipApplicationDto.toInternshipApplication();
            Student student = etudiantService.findEtudiantById(internshipApplicationDto.getStudentDto().getId());
            InternshipOffer internshipOffer = internOfferService.findById(internshipApplicationDto.getInternshipOfferDto().getId());
            List<File> files = internshipApplicationDto.getFileDtos() == null ? new ArrayList<>() : internshipApplicationDto.getFileDtos().stream().map(FileDto::fromDto).toList();

            internshipApplication.setStudent(student);
            internshipApplication.setInternshipOffer(internshipOffer);
            internshipApplication.setFiles(files);

            internshipCandidatesRepository.save(internshipApplication);
            return new InternshipApplicationDto(internshipApplication);

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
    public InternshipApplicationDto saveCandidates(InternshipApplication internshipApplication){
        try{
            internshipCandidatesRepository.save(internshipApplication);
            return new InternshipApplicationDto(internshipApplication);
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
}
