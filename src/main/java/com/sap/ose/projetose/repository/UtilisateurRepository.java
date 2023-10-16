package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    @Query("SELECT u FROM Utilisateur u WHERE u.email = ?1")
    Optional<Utilisateur> findByEmail(String email);
}
