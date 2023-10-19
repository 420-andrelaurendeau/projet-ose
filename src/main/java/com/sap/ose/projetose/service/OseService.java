package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.dto.UtilisateurDto;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.repository.EmployeurRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OseService {

    private final EtudiantRepository etudiantRepository;

    private final EmployeurRepository employeurRepository;

    public OseService(EtudiantRepository etudiantRepository, EmployeurRepository employeurRepository) {
        this.etudiantRepository = etudiantRepository;

        this.employeurRepository = employeurRepository;
    }


    public List<UtilisateurDto> getAllUsers() {
        Optional<List<Employeur>> employeurs = Optional.of(employeurRepository.findAll());
        Optional<List<Etudiant>> etudiants = Optional.of(etudiantRepository.findAll());
        List<EtudiantDto> etudiantDtos = new ArrayList<>();
        List<EmployeurDto> employeurDtos = new ArrayList<>();

        etudiants.get().forEach(etudiant -> {
            EtudiantDto etudiantDto = new EtudiantDto(etudiant);
            etudiantDto.setId(etudiant.getId());
            etudiantDtos.add(etudiantDto);
        });

        employeurs.get().forEach(employeur -> {
            EmployeurDto employeurDto = new EmployeurDto(employeur);
            employeurDto.setId(employeur.getId());
            employeurDtos.add(employeurDto);
        });
        List<UtilisateurDto> utilisateurs = new ArrayList<>(etudiantDtos);
        utilisateurs.addAll(employeurDtos);
        return utilisateurs;
    }




}
