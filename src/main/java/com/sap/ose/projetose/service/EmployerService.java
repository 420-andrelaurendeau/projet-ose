package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EmployerDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    public Employer saveEmployer(String lastName, String firstName, String phone, String email, String password, String employerName, long programId ){
        try {
            StudyProgram studyProgram = studyProgramService.getStudyProgramById(programId).toNewProgram();
            return employerRepository.save(new Employer(lastName,firstName,phone,email,password,employerName, studyProgram));

        }catch (DataAccessException e){
            logger.info(e.getMessage());
            throw new DataAccessException("Erreur d'accès aux données lors de la sauvegarde de l'employeur.") {};
        }
    }

    @Transactional
    public Optional<Employer> saveEmployer(Employer employer){
        try {
            StudyProgram StudyProgram = studyProgramService.findProgramById(employer.getStudyProgram().getId());
            employer.setStudyProgram(StudyProgram);
            return Optional.of(employerRepository.save(employer));
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde de l'employeur") {};
        }
    }

    public List<EmployerDto> getAllEmployers(){
        List<EmployerDto> employerDTOS = new ArrayList<>();
        for(Employer employer : employerRepository.findAll()){
            employerDTOS.add(new EmployerDto(employer.getLastName(), employer.getFirstName(), employer.getPhoneNumber(), employer.getEmail(), employer.getEnterprise()));
        }
        return employerDTOS;
    }

    EmployerDto getEmployerById(Long id){
        return new EmployerDto(Objects.requireNonNull(employerRepository.findById(id).orElse(null))) ;
    }
}
