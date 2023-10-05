package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternshipCandidatesDto;
import com.sap.ose.projetose.models.File;
import com.sap.ose.projetose.models.InternshipApplication;
import com.sap.ose.projetose.models.InternshipOffer;
import com.sap.ose.projetose.models.Student;
import com.sap.ose.projetose.repository.InternshipCandidatesRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipCandidatesService {
    private final InternshipCandidatesRepository internshipCandidatesRepository;
    private final InternshipOfferService internshipOfferService;
    private final StudentService studentService;

    private final FileService fileService;

    private final Logger logger = LoggerFactory.getLogger(InternshipCandidatesService.class);

    @Transactional
    public InternshipCandidatesDto saveCandidates(InternshipCandidatesDto internshipCandidatesDto){
        try{
            InternshipApplication internshipApplication = internshipCandidatesDto.fromDto();

            Student student = studentService.findEtudiantByMatricule(internshipCandidatesDto.getStudentMatricule());
            InternshipOffer internshipOffer = internshipOfferService.findById(internshipCandidatesDto.getInternshipOfferId());
            List<File> files = internshipCandidatesDto.getFiles().stream().map(fileDto -> fileService.findById(fileDto.getId())).toList();

            internshipApplication.setEtudiant(student);
            internshipApplication.setInternshipOffer(internshipOffer);
            internshipApplication.setFiles(files);

            internshipCandidatesRepository.save(internshipApplication);
            return new InternshipCandidatesDto(internshipApplication);

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
    public InternshipCandidatesDto saveCandidates(InternshipApplication internshipApplication){
        try{
            internshipCandidatesRepository.save(internshipApplication);
            return new InternshipCandidatesDto(internshipApplication);
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
