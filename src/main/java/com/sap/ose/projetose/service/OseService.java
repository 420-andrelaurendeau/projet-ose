package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EtudiantDTO;
import com.sap.ose.projetose.dto.ProgrammeDTO;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OseService {
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
}
