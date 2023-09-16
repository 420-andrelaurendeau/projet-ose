package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.model.Employeur;
import com.sap.ose.projetose.model.Etudiant;
import com.sap.ose.projetose.model.Utilisateur;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    //get all Utilisateurs
    public List<Utilisateur> getAllUsers() {
        Optional<List<Employeur>> employeurs = Optional.of(employeurRepository.findAll());
        Optional<List<Etudiant>> etudiants = Optional.of(etudiantRepository.findAll());
        List<Utilisateur> utilisateurs = new ArrayList<>(employeurs.get());
        utilisateurs.addAll(etudiants.get());
        return utilisateurs;
    }



}
