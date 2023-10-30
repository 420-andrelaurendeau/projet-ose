package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findAllByEmailEqualsIgnoreCase(String email);
}
