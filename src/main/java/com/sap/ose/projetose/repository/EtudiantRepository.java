package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    @Query("SELECT e FROM Etudiant e WHERE e.email = ?1")
    Optional<Etudiant> findByCourriel(String email);

    @Query("SELECT e FROM Etudiant e WHERE e.matricule = ?1")
    Optional<Etudiant> findByMatricule(String matricule);

    @Query("SELECT etudiant FROM Etudiant etudiant JOIN etudiant.cv cv WHERE cv.isAccepted = false")
    List<Etudiant> findEtudiantByCvIsAcceptedFalse();
}
