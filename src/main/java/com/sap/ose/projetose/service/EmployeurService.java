package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EmployerDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.EmployerNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.models.Employer;
import com.sap.ose.projetose.models.Program;
import com.sap.ose.projetose.repository.EmployeurRepository;
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
public class EmployeurService {
    private final Logger logger = LoggerFactory.getLogger(EmployeurService.class);

    private final EmployeurRepository employeurRepository;
    private final ProgrammeService programmeService;

    Employer findById(long id) {
        try {
            return employeurRepository.findById(id).orElseThrow(EmployerNotFoundException::new);
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
    public Employer saveEmployeur(String nom, String prenom, String phone, String email, String password, String nomEntreprise, long programme_id ){
        try {
            Program program = programmeService.getProgrammeById(programme_id).toNewProgram();
            return employeurRepository.save(new Employer(nom,prenom,phone,email,password,nomEntreprise, program));

        }catch (DataAccessException e){
            logger.info(e.getMessage());
            throw new DataAccessException("Erreur d'accès aux données lors de la sauvegarde de l'employeur.") {};
        }
    }

    @Transactional
    public Optional<Employer> saveEmployeur(Employer employer){
        try {
            return Optional.of(employeurRepository.save(employer));
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde de l'employeur") {};
        }
    }

    public List<EmployerDto> getAllEmployeur(){
        List<EmployerDto> employerDTOS = new ArrayList<>();
        for(Employer employer : employeurRepository.findAll()){
            employerDTOS.add(new EmployerDto(employer.getLastName(), employer.getFirstName(), employer.getPhoneNumber(), employer.getEmail(), employer.getEntreprise()));
        }
        return employerDTOS;
    }

    EmployerDto getEmployeurById(Long id){
        return new EmployerDto(Objects.requireNonNull(employeurRepository.findById(id).orElse(null))) ;
    }
}
