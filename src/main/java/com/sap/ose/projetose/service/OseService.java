package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import com.sap.ose.projetose.dto.UtilisateurDto;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.repository.EmployeurRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
            EtudiantDto etudiantDto = new EtudiantDto();
            etudiantDto.setId(etudiant.getId());
            etudiantDto.setNom(etudiant.getNom());
            etudiantDto.setPrenom(etudiant.getPrenom());
            etudiantDto.setEmail(etudiant.getEmail());
            etudiantDto.setPhone(etudiant.getPhone());
            etudiantDto.setMatricule(etudiant.getMatricule());
            etudiantDto.setCv(etudiant.getCv().stream().map(File::getId).toList());
            etudiantDto.setProgramme_id(etudiant.getProgramme().getId());
            etudiantDtos.add(etudiantDto);
        });

        employeurs.get().forEach(employeur -> {
            EmployeurDto employeurDto = new EmployeurDto();
            employeurDto.setId(employeur.getId());
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
