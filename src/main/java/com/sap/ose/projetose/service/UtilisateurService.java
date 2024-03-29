package com.sap.ose.projetose.service;

import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final EmployeurRepository employeurRepository;
    private final EtudiantRepository etudiantRepository;
    private final InternshipmanagerRepository internshipmanagerRepository;

    public UtilisateurService(EmployeurRepository employeurRepository, EtudiantRepository etudiantRepository, InternshipmanagerRepository internshipmanagerRepository) {
        this.employeurRepository = employeurRepository;
        this.etudiantRepository = etudiantRepository;
        this.internshipmanagerRepository = internshipmanagerRepository;
    }

    @Transactional
    public Utilisateur getUserByEmail(String email) {
        Employeur employeur = employeurRepository.findByEmail(email).orElse(null);
        if (employeur != null) {
            return employeur;
        }

        Etudiant etudiant = etudiantRepository.findByEmail(email).orElse(null);
        if (etudiant != null) {
            return etudiant;
        }

        Internshipmanager internshipmanager = internshipmanagerRepository.findByEmail(email).orElse(null);
        if (internshipmanager != null){
            return internshipmanager;
        }

        return null;
    }

    @Transactional
    public Utilisateur getUserById(long id) {
        Employeur employeur = employeurRepository.findById(id).orElse(null);
        if (employeur != null) {
            return employeur;
        }

        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        if (etudiant != null) {
            return etudiant;
        }

        Internshipmanager internshipmanager = internshipmanagerRepository.findById(id).orElse(null);
        if (internshipmanager != null){
            return internshipmanager;
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

        Internshipmanager internshipmanager = internshipmanagerRepository.findByEmail(email).orElse(null);
        if (internshipmanager != null){
            return internshipmanager.getRole();
        }

        return null;
    }

    List<Etudiant> findAllEtudiant() {
        return etudiantRepository.findAll();
    }

    List<Employeur> findAllEmployeur() {
        return employeurRepository.findAll();
    }

    List<Internshipmanager> findAllManagers() {
        return internshipmanagerRepository.findAll();
    }

    List<Etudiant> findAllEtudiantByProgram(long programId) {
        return etudiantRepository.findAllEtudiantByProgram(programId);
    }

    List<Employeur> findAllEmployeurByProgram(long programId) {
        return employeurRepository.findAllEmployeurByProgram(programId);
    }
}
