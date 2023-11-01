package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.InternshipmanagerDto;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.Internshipmanager;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.dto.UtilisateurDto;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OseService {

    private final EtudiantRepository etudiantRepository;

    private final EmployeurRepository employeurRepository;

    private final InternshipmanagerRepository internshipmanagerRepository;

    public OseService(EtudiantRepository etudiantRepository, EmployeurRepository employeurRepository, InternshipmanagerRepository internshipmanagerRepository) {
        this.etudiantRepository = etudiantRepository;

        this.employeurRepository = employeurRepository;
        this.internshipmanagerRepository = internshipmanagerRepository;
    }

    @Transactional
    public List<UtilisateurDto> getAllUsers() {
        Optional<List<Employeur>> employeurs = Optional.of(employeurRepository.findAll());
        Optional<List<Etudiant>> etudiants = Optional.of(etudiantRepository.findAll());
        Optional<List<Internshipmanager>> internshipmanagers = Optional.of(internshipmanagerRepository.findAll());
        List<EtudiantDto> etudiantDtos = new ArrayList<>();
        List<EmployeurDto> employeurDtos = new ArrayList<>();
        List<InternshipmanagerDto> internshipmanagerDtos = new ArrayList<>();

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

        internshipmanagers.get().forEach(internshipmanager -> {
            InternshipmanagerDto internshipmanagerDto = new InternshipmanagerDto(internshipmanager);
            internshipmanagerDto.setId(internshipmanager.getId());
            internshipmanagerDtos.add(internshipmanagerDto);
        });

        List<UtilisateurDto> utilisateurs = new ArrayList<>(etudiantDtos);
        utilisateurs.addAll(employeurDtos);
        utilisateurs.addAll(internshipmanagerDtos);
        return utilisateurs;
    }




}
