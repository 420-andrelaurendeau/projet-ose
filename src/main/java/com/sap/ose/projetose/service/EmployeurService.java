package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.EmployerNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.EmployeurRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeurService {

    private final EmployeurRepository employeurRepository;
    private final Logger logger = LoggerFactory.getLogger(EmployeurService.class);
    private final ProgrammeService programmeService;


    @Autowired
    public EmployeurService(EmployeurRepository employeurRepository, ProgrammeService programmeService) {
        this.employeurRepository = employeurRepository;
        this.programmeService = programmeService;
    }

    Employeur findById(long id) {
        try {
            System.out.println(id);
            return employeurRepository.findById(id).orElseThrow(EmployerNotFoundException::new);
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
    public Employeur saveEmployeur(String nom, String prenom, String phone, String email, String password, String nomEntreprise, long programme_id ){
        try {
            Programme programme = programmeService.getProgrammeById(programme_id).fromDto();
            return employeurRepository.save(new Employeur(nom,prenom,phone,email,password,nomEntreprise,programme));

        }catch (DataAccessException e){
            logger.info(e.getMessage());
            throw new DataAccessException("Erreur d'accès aux données lors de la sauvegarde de l'employeur.") {};
        }
    }

    @Transactional
    public Optional<Employeur> saveEmployeur(Employeur employeur){
        try {
            return Optional.of(employeurRepository.save(employeur));
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde de l'employeur") {};
        }
    }

    public List<EmployeurDto> getAllEmployeur(){
        List<EmployeurDto> employeurDTOS = new ArrayList<>();
        for(Employeur employeur : employeurRepository.findAll()){
            employeurDTOS.add(new EmployeurDto(employeur.getNom(),employeur.getPrenom(),employeur.getPhone(),employeur.getEmail(),employeur.getEntreprise()));
        }
        return employeurDTOS;
    }

    EmployeurDto getEmployeurById(Long id){
        return new EmployeurDto(Objects.requireNonNull(employeurRepository.findById(id).orElse(null))) ;
    }
}