package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EtudiantDTO;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OseService {
    private final EtudiantRepository etudiantRepository;

    public OseService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public EtudiantDTO saveEtudiant(String nom, String prenom, String email, String courriel, int matricule) {
        return new EtudiantDTO(etudiantRepository.save(new Etudiant(nom, prenom, email, courriel, matricule)));
    }

    public Optional<EtudiantDTO> saveEtudiant(EtudiantDTO etudiantDTO) {
        return Optional.of(new EtudiantDTO(etudiantRepository.save(etudiantDTO.fromDto())));
    }
}
