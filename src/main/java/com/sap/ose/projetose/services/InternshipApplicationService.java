package com.sap.ose.projetose.services;

import com.sap.ose.projetose.dtos.InternshipApplicationDto;
import com.sap.ose.projetose.dtos.NewInternshipApplicationDto;
import com.sap.ose.projetose.models.InternshipOffer;
import com.sap.ose.projetose.models.Student;
import com.sap.ose.projetose.models.File;
import com.sap.ose.projetose.models.InternshipApplication;
import com.sap.ose.projetose.repositories.InternshipApplicationRepository;
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
    private final FileService fileService;
    private final StudentService studentService;

    private final Logger logger = LoggerFactory.getLogger(InternshipApplicationService.class);

    @Transactional
    public InternshipApplicationDto createApplication(NewInternshipApplicationDto internshipApplicationDto){
        try{
            Student student = studentService.getStudentById(internshipApplicationDto.getCandidateId());
            InternshipOffer internshipOffer = internshipOfferService.findById(internshipApplicationDto.getInternshipOfferDtoId());

            List<File> files = internshipApplicationDto.getFileTransferDtosId() == null
                    ? new ArrayList<>()
                    : internshipApplicationDto.getFileTransferDtosId()
                        .stream()
                        .map(fileService::newFile)
                        .toList();

            InternshipApplication internshipApplication = new InternshipApplication(student, internshipOffer, files);

            return new InternshipApplicationDto(internshipApplicationRepository.save(internshipApplication));

        } catch (DataAccessException e){
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde de candidature") {};
        } catch (NullPointerException e) {
            logger.info(e.getMessage());
            throw new NullPointerException(e.getMessage());
        } catch (Exception e) {
            logger.info(e.getMessage());
            logger.info(e.getCause().getMessage());
            throw new RuntimeException("Erreur rencontré lors de la sauvegarde de l'offre d'emploi.");
        }
    }
}
