package com.sap.ose.projetose.service;

import com.sap.ose.projetose.controller.ReactOseController;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.repository.EtudiantRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {
    private final EtudiantRepository etudiantRepository;
    Logger logger = LoggerFactory.getLogger(ReactOseController.class);

    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @Transactional
    public Optional<Etudiant> saveEtudiant(Etudiant etudiant) {
        try {
            return Optional.of(etudiantRepository.save(etudiant));
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DataAccessException("Error lors de la sauvegarde de l'etudiant") {};
        }

    }

    public List<EtudiantDto> getEtudiants() {
        List<EtudiantDto> dtos = new ArrayList<>();
        for (Etudiant etudiant : etudiantRepository.findAll()) {
            dtos.add(new EtudiantDto(etudiant.getNom(), etudiant.getPrenom(), etudiant.getPhone(), etudiant.getEmail(), etudiant.getMatricule(), etudiant.getProgramme().getId(), etudiant.getCv()));
        }
        return dtos;
    }
    public EtudiantDto getEtudiantById(Long id) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        return etudiant.map(value -> new EtudiantDto(value.getNom(), value.getPrenom(), value.getPhone(), value.getEmail(), value.getMatricule(), value.getProgramme().getId(), value.getCv())).orElse(null);
    }

    Etudiant getEtudiantByCourriel(String courriel) {
        return etudiantRepository.findByCourriel(courriel).orElse(null);
    }

}
