package com.sap.ose.projetose.service;

import com.sap.ose.projetose.model.Employeur;
import com.sap.ose.projetose.model.Etudiant;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OseService {

    private final EmployeurRepository employeurRepository;
    private final EtudiantRepository etudiantRepository;

    public OseService(EmployeurRepository employeurRepository, EtudiantRepository etudiantRepository) {
        this.employeurRepository = employeurRepository;
        this.etudiantRepository = etudiantRepository;
    }

    public String connection(String email, String password) {
        Optional<Employeur> employeur = employeurRepository.findByCourriel(email);
        if (employeur.isPresent()) {
            if (employeur.get().getPassword().equals(password)) {
                return employeur.get().getEmail();
            }
        }
        Optional<Etudiant> etudiant = etudiantRepository.findByCourriel(email);
        if (etudiant.isPresent()) {
            if (etudiant.get().getPassword().equals(password)) {
                return etudiant.get().getEmail();
            }
        }
        return "erreur";
    }
}
