package com.sap.ose.projetose.service;

import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.Role;
import com.sap.ose.projetose.modeles.Utilisateur;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    private final EmployeurRepository employeurRepository;
    private final EtudiantRepository etudiantRepository;

    public UtilisateurService(EmployeurRepository employeurRepository, EtudiantRepository etudiantRepository) {
        this.employeurRepository = employeurRepository;
        this.etudiantRepository = etudiantRepository;
    }

    public Utilisateur getUserByEmail(String email) {
        Employeur employeur = employeurRepository.findByEmail(email).orElse(null);
        if (employeur != null) {
            return employeur;
        }

        Etudiant etudiant = etudiantRepository.findByEmail(email).orElse(null);
        if (etudiant != null) {
            return etudiant;
        }

        return null;
    }

    public Role getUserByEmailRole(String email){
        Employeur employeur = employeurRepository.findByEmail(email).orElse(null);
        if (employeur != null) {
            return employeur.getRole();
        }

        Etudiant etudiant = etudiantRepository.findByEmail(email).orElse(null);
        if (etudiant != null) {
            return etudiant.getRole();
        }

        return null;
    }
}
