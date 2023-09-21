package com.sap.ose.projetose.service;

<<<<<<< HEAD
import com.sap.ose.projetose.dto.EtudiantDTO;
import com.sap.ose.projetose.dto.ProgrammeDTO;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.ProgrammeRepository;
=======
import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.UtilisateurDto;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.EtudiantRepository;
>>>>>>> EQ5-30
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OseService {
<<<<<<< HEAD
    private final EtudiantRepository etudiantRepository;
    private final ProgrammeRepository programmeRepository;

    public OseService(EtudiantRepository etudiantRepository, ProgrammeRepository programmeRepository) {
        this.etudiantRepository = etudiantRepository;
        this.programmeRepository = programmeRepository;
    }

    public EtudiantDTO saveEtudiant(String nom, String prenom, String email, String password, String phone, int programme, String cv) {
        return new EtudiantDTO(etudiantRepository.save(new Etudiant(nom, prenom, phone, email, password ,programme, cv)));
    }

    public Optional<EtudiantDTO> saveEtudiant(EtudiantDTO etudiantDTO) {
        return Optional.of(new EtudiantDTO(etudiantRepository.save(etudiantDTO.fromDto())));
    }

    public List<EtudiantDTO> getEtudiants() {
        List<EtudiantDTO> dtos = new ArrayList<>();
        for (Etudiant etudiant : etudiantRepository.findAll()) {
            dtos.add(new EtudiantDTO(etudiant));
        }
        return dtos;
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

    public EtudiantDTO getEtudiantById(int id) {
        return new EtudiantDTO(etudiantRepository.findById(id).orElse(null));
    }
=======

    private final EmployeurRepository employeurRepository;
    private final EtudiantRepository etudiantRepository;

    public OseService(EmployeurRepository employeurRepository, EtudiantRepository etudiantRepository) {
        this.employeurRepository = employeurRepository;
        this.etudiantRepository = etudiantRepository;
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


    public void saveEtudiant(Etudiant etudiant) {
        etudiantRepository.save(etudiant);
    }

    public Etudiant getEtudiantById(Long id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    public Etudiant getEtudiantByCourriel(String courriel) {
        return etudiantRepository.findByCourriel(courriel).orElse(null);
    }

    public List<EtudiantDto> getAllEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        List<EtudiantDto> etudiantDtos = new ArrayList<>();
        for (Etudiant etudiant : etudiants) {
            EtudiantDto etudiantDto = new EtudiantDto(etudiant.getNom(), etudiant.getPrenom(), etudiant.getPhone(), etudiant.getEmail(), etudiant.getMatricule(), etudiant.getProgramme(), etudiant.getCv());
            etudiantDtos.add(etudiantDto);
        }
        return etudiantDtos;
    }

    public void saveEmployeur(Employeur employeur) {
        employeurRepository.save(employeur);
    }

>>>>>>> EQ5-30
}
