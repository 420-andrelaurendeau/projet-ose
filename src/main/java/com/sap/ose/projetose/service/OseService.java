package com.sap.ose.projetose.service;

<<<<<<< HEAD
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.ProgrammeDTO;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.dto.UtilisateurDto;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.repository.EmployeurRepository;

=======
import com.sap.ose.projetose.dto.EmployeurDTO;
import com.sap.ose.projetose.dto.ProgrammeDTO;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.EmployeurRepositary;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> origin/EQ5-12_EmpInscris
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public void saveEmployeur(Employeur employeur) {
        employeurRepository.save(employeur);
    }

    public EmployeurDTO saveEmployeur(String nom, String prenom, String phone,String email,String password,String nomEntreprise,int programme ){
        return new EmployeurDTO(employeurRepositary.save(new Employeur(nom,prenom,phone,email,password,nomEntreprise,programme)));
    }

    public Optional<EmployeurDTO> saveEmployeur(EmployeurDTO employeurDTO){
        return Optional.of(new EmployeurDTO(employeurRepositary.save(employeurDTO.fromDTO())));
    }

    public List<EmployeurDTO> getAllEmployeur(){
        List<EmployeurDTO> employeurDTOS = new ArrayList<>();
        for(Employeur employeur : employeurRepositary.findAll()){
            employeurDTOS.add(new EmployeurDTO(employeur));
        }
        return employeurDTOS;
    }

    public EmployeurDTO getEmployeurById(int id){
        return new EmployeurDTO(employeurRepositary.findById(id).orElse(null));
    }
}
