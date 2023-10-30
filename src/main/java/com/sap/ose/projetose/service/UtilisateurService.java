package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.UtilisateurDto;
import com.sap.ose.projetose.modeles.Utilisateur;
import com.sap.ose.projetose.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    public List<UtilisateurDto> getAllUsers() {
        return utilisateurRepository.findAll().stream().map(Utilisateur::toDto).toList();
    }

    public Utilisateur getUserById(long uploaderId) {
        return utilisateurRepository.getReferenceById(uploaderId);
    }
}
