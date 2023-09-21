package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EmployeurDTO;
import com.sap.ose.projetose.dto.ProgrammeDTO;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.EmployeurRepositary;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OseService {
    private final EmployeurRepositary employeurRepositary;
    private final ProgrammeRepository programmeRepository;

    @Autowired
    public OseService(EmployeurRepositary employeurRepositary, ProgrammeRepository programmeRepository) {
        this.employeurRepositary = employeurRepositary;
        this.programmeRepository = programmeRepository;
    }
    public EmployeurDTO saveEmployeur(String nom, String prenom, String phone,String email,String password,String nomEntreprise,int programme ){
        return new EmployeurDTO(employeurRepositary.save(new Employeur(nom,prenom,phone,email,password,nomEntreprise,programme)));
    }

    public Optional<EmployeurDTO> saveEmployeur(EmployeurDTO employeurDTO){
        return Optional.of(new EmployeurDTO(employeurRepositary.save(employeurDTO.fromDTO())));
    }

    public List<EmployeurDTO> getAllEmployeur(){
        List<EmployeurDTO> employeurDTOS = new ArrayList<>();
        for(Employeur employeur : employeurRepositary.findAll()){
            employeurDTOS.add(new EmployeurDTO(employeur));
        }
        return employeurDTOS;
    }

    public EmployeurDTO getEmployeurById(int id){
        return new EmployeurDTO(employeurRepositary.findById(id).orElse(null));
    }

    public ProgrammeDTO saveProgramme(String nom, String description) {
        return new ProgrammeDTO(programmeRepository.save(new Programme(nom, description)));
    }

    public Optional<ProgrammeDTO> saveProgramme(ProgrammeDTO programmeDTO) {
        return Optional.of(new ProgrammeDTO(programmeRepository.save(programmeDTO.fromDto())));
    }

    public List<ProgrammeDTO> getProgrammes() {
        List<ProgrammeDTO> dtos = new ArrayList<>();
        for (Programme programme : programmeRepository.findAll()) {
            dtos.add(new ProgrammeDTO(programme));
        }
        return dtos;
    }
}
