package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EmployerDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.EmployerNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.models.Employer;
import com.sap.ose.projetose.models.Formation;
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

    private final EmployerRepository employerRepository;
    private final Logger logger = LoggerFactory.getLogger(EmployerService.class);
    private final FormationService formationService;

    Employer findById(long id) {
        try {
            System.out.println(id);
            return employerRepository.findById(id).orElseThrow(EmployerNotFoundException::new);
        } catch (EmployerNotFoundException e) {
            logger.error("Employeur non trouvé avec l'id" + id);
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
            Formation formation = formationService.getProgrammeById(programme_id).fromDto();
            return employerRepository.save(new Employer(nom,prenom,phone,email,password,nomEntreprise, formation));

        }catch (DataAccessException e){
            logger.info(e.getMessage());
            throw new DataAccessException("Erreur d'accès aux données lors de la sauvegarde de l'employeur.") {};
        }
    }

    @Transactional
    public Optional<Employer> saveEmployeur(Employer employer){
        try {
            return Optional.of(employerRepository.save(employer));
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde de l'employer") {};
        }
    }

    public List<EmployerDto> getAllEmployeur(){
        List<EmployerDto> employerDTOS = new ArrayList<>();
        for(Employer employer : employerRepository.findAll()){
            employerDTOS.add(new EmployerDto(employer.getNom(), employer.getPrenom(), employer.getPhone(), employer.getEmail(), employer.getEntreprise()));
        }
        return employerDTOS;
    }

    EmployerDto getEmployeurById(Long id){
        return new EmployerDto(Objects.requireNonNull(employerRepository.findById(id).orElse(null))) ;
    }
}
