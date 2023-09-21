package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.modeles.Etudiant;
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
    private final ProgrammeRepository programmeRepository;
    private final EmployeurRepository employeurRepository;

    public OseService(EtudiantRepository etudiantRepository, ProgrammeRepository programmeRepository, EmployeurRepository employeurRepository) {
        this.etudiantRepository = etudiantRepository;
        this.programmeRepository = programmeRepository;
        this.employeurRepository = employeurRepository;
    }

    public Optional<Etudiant> saveEtudiant(Etudiant etudiant) {
        return Optional.of(etudiantRepository.save(etudiant));
    }

    public List<EtudiantDto> getEtudiants() {
        List<EtudiantDto> dtos = new ArrayList<>();
        for (Etudiant etudiant : etudiantRepository.findAll()) {
            dtos.add(new EtudiantDto(etudiant.getNom(), etudiant.getPrenom(), etudiant.getPhone(), etudiant.getEmail(), etudiant.getMatricule(), etudiant.getProgramme(), etudiant.getCv()));
        }
        return dtos;
    }

    public ProgrammeDto saveProgramme(String nom, String description) {
        return new ProgrammeDto(programmeRepository.save(new Programme(nom, description)));
    }

    public Optional<ProgrammeDto> saveProgramme(ProgrammeDto programmeDTO) {
        return Optional.of(new ProgrammeDto(programmeRepository.save(programmeDTO.fromDto())));
    }

    public List<ProgrammeDto> getProgrammes() {
        List<ProgrammeDto> dtos = new ArrayList<>();
        for (Programme programme : programmeRepository.findAll()) {
            dtos.add(new ProgrammeDto(programme));
        }
        return dtos;
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

    public EtudiantDto getEtudiantById(Long id) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        return etudiant.map(value -> new EtudiantDto(value.getNom(), value.getPrenom(), value.getPhone(), value.getEmail(), value.getMatricule(), value.getProgramme(), value.getCv())).orElse(null);
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

    public Employeur saveEmployeur(String nom, String prenom, String phone, String email, String password, String nomEntreprise, int programme ){
        return employeurRepository.save(new Employeur(nom,prenom,phone,email,password,nomEntreprise,programme));
    }

    public Optional<Employeur> saveEmployeur(Employeur employeur){
        return Optional.of(employeurRepository.save(employeur));
    }

    public List<EmployeurDto> getAllEmployeur(){
        List<EmployeurDto> employeurDTOS = new ArrayList<>();
        for(Employeur employeur : employeurRepository.findAll()){
            employeurDTOS.add(new EmployeurDto(employeur.getNom(),employeur.getPrenom(),employeur.getPhone(),employeur.getEmail(),employeur.getEntreprise()));
        }
        return employeurDTOS;
    }

    public EmployeurDto getEmployeurById(Long id){
        return new EmployeurDto(Objects.requireNonNull(employeurRepository.findById(id).orElse(null))) ;
    }
}
