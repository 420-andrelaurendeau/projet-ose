package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.EmployeurRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EmployeurService {

    private final EmployeurRepository employeurRepository;
    private final ProgrammeService programmeService;

    public EmployeurService(EmployeurRepository employeurRepository, ProgrammeService programmeService) {
        this.employeurRepository = employeurRepository;
        this.programmeService = programmeService;
    }

    public Employeur saveEmployeur(String nom, String prenom, String phone, String email, String password, String nomEntreprise, long programme_id ){
        Programme programme = programmeService.getProgrammeById(programme_id).orElse(null);
        return employeurRepository.save(new Employeur(nom,prenom,phone,email,password,nomEntreprise,programme));
    }

    public Optional<Employeur> saveEmployeur(Employeur employeur){
        return Optional.of(employeurRepository.save(employeur));
    }

    public List<EmployeurDto> getAllEmployeur(){
        List<EmployeurDto> employeurDTOS = new ArrayList<>();
        for(Employeur employeur : employeurRepository.findAll()){
            employeurDTOS.add(new EmployeurDto(employeur.getNom(),employeur.getPrenom(),employeur.getPhone(),employeur.getEmail(),employeur.getEntreprise()));
        }
        return employeurDTOS;
    }

    public EmployeurDto getEmployeurById(Long id){
        return new EmployeurDto(Objects.requireNonNull(employeurRepository.findById(id).orElse(null))) ;
    }
}
