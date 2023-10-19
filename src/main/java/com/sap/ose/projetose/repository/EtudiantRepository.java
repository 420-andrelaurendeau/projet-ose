package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    @Query("SELECT e FROM Etudiant e WHERE e.email = ?1")
    Optional<Etudiant> findByCourriel(String email);

    @Query("SELECT e FROM Etudiant e WHERE e.matricule = ?1")
    Optional<Etudiant> findByMatricule(String matricule);
}
