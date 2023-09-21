package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EmployeurDTO;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.repository.EmployeurRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OseService {
    private final EmployeurRepositary employeurRepositary;

    @Autowired
    public OseService(EmployeurRepositary employeurRepositary) {
        this.employeurRepositary = employeurRepositary;
    }

    public EmployeurDTO saveEmployeur(String nom, String prenom, String phone,String email,String password,String nomEntreprise,String programme ){
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
}
