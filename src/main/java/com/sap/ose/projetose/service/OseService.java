package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.UtilisateurDto;
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
    public List<UtilisateurDto> getAllUsers() {
        Optional<List<Employeur>> employeurs = Optional.of(employeurRepository.findAll());
        Optional<List<Etudiant>> etudiants = Optional.of(etudiantRepository.findAll());
        List<EtudiantDto> etudiantDtos = new ArrayList<>();
        List<EmployeurDto> employeurDtos = new ArrayList<>();

        etudiants.get().forEach(etudiant -> {
            EtudiantDto etudiantDto = new EtudiantDto();
            etudiantDto.setNom(etudiant.getNom());
            etudiantDto.setPrenom(etudiant.getPrenom());
            etudiantDto.setEmail(etudiant.getEmail());
            etudiantDto.setPhone(etudiant.getPhone());
            etudiantDto.setMatricule(etudiant.getMatricule());
            etudiantDto.setCv(etudiant.getCv());
            etudiantDto.setProgramme(etudiant.getProgramme());
            etudiantDtos.add(etudiantDto);
        });

        employeurs.get().forEach(employeur -> {
            EmployeurDto employeurDto = new EmployeurDto();
            employeurDto.setNom(employeur.getNom());
            employeurDto.setPrenom(employeur.getPrenom());
            employeurDto.setPhone(employeur.getPhone());
            employeurDto.setEmail(employeur.getEmail());
            employeurDto.setEntreprise(employeur.getEntreprise());
            employeurDtos.add(employeurDto);
        });
        List<UtilisateurDto> utilisateurs = new ArrayList<>(etudiantDtos);
        utilisateurs.addAll(employeurDtos);
        return utilisateurs;
    }



}
