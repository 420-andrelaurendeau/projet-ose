package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternshipApplicationDto;
import com.sap.ose.projetose.models.InternshipOffer;
import com.sap.ose.projetose.models.Student;
import com.sap.ose.projetose.models.File;
import com.sap.ose.projetose.models.InternshipApplication;
import com.sap.ose.projetose.repository.InternshipApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipApplicationService {

    private final InternshipApplicationRepository internshipApplicationRepository;
    private final InternshipOfferService internshipOfferService;
    private final StudentService studentService;

    private final Logger logger = LoggerFactory.getLogger(InternshipApplicationService.class);

    @Transactional
    public InternshipApplicationDto saveApplication(InternshipApplicationDto internshipApplicationDto){
        try{
            InternshipApplication internshipApplication = internshipApplicationDto.toInternshipApplication();
            Student student = studentService.getStudentById(internshipApplicationDto.getCandidateDto().getId());
            InternshipOffer internshipOffer = internshipOfferService.findById(internshipApplicationDto.getInternshipOfferDto().getId());
            List<File> files = internshipApplicationDto.getFileDtos() == null ? new ArrayList<>() : internshipApplicationDto.getFileDtos().stream().map(FileDto::toFile).toList();

            internshipApplication.setStudent(student);
            internshipApplication.setInternshipOffer(internshipOffer);
            internshipApplication.setFiles(files);

            internshipApplicationRepository.save(internshipApplication);
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
    public InternshipApplicationDto saveApplication(InternshipApplication internshipApplication){
        try{
            internshipApplicationRepository.save(internshipApplication);
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
