package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EmployerDto;
import com.sap.ose.projetose.dto.NewEmployerDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.EmployerNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.models.Employer;
import com.sap.ose.projetose.models.StudyProgram;
import com.sap.ose.projetose.repository.EmployerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployerService {
    private final Logger logger = LoggerFactory.getLogger(EmployerService.class);

    private final EmployerRepository employerRepository;
    private final StudyProgramService studyProgramService;

    Employer findById(long id) {
        try {
            return employerRepository.findById(id).orElseThrow(EmployerNotFoundException::new);
        } catch (EmployerNotFoundException e) {
            logger.error("Employer non trouvé avec l'id" + id);
            throw e;
        } catch (DataAccessException e) {
            logger.info("Erreur d'accès a la base de donné lors de la récupération de l'employeuravec l'Id :" + id, e);
            throw new DatabaseException("Erreur d'accès a la base de donné lors de la récupération de l'employeur");
        } catch (Exception e) {
            logger.info("Erreur inconnue lors de la récupération de l'employé avec l'Id : " + id, e);
            throw new ServiceException("Erreur inconnue lors de la récupération de l'employeur");
        }
    }

    @Transactional
    public Optional<EmployerDto> createEmployer(NewEmployerDto employerDto){
        try {
            Employer employer = new Employer();
            StudyProgram StudyProgram = studyProgramService.findProgramById(employerDto.getStudyProgramId());

            employer.setLastName(employerDto.getLastName());
            employer.setFirstName(employerDto.getFirstName());
            employer.setPhoneNumber(employerDto.getPhoneNumber());
            employer.setEmail(employerDto.getEmail());
            employer.setPassword(employerDto.getPassword());
            employer.setStudyProgram(StudyProgram);
            employer.setEnterprise(employerDto.getEnterprise());

            return Optional.of(employerRepository.save(employer)).map(EmployerDto::new);
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde de l'employeur") {};
        }
    }
}
