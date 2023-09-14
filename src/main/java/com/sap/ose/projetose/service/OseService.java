package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EtudiantDTO;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OseService {
    private final EtudiantRepository etudiantRepository;

    public OseService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public EtudiantDTO saveEtudiant(String nom, String prenom, String courriel, String motDePasse) {
        return new EtudiantDTO(etudiantRepository.save(new Etudiant(nom, prenom, courriel, motDePasse)));
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
}
