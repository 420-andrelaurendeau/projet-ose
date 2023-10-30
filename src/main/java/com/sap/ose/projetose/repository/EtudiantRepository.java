package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    Optional<Etudiant> findAllByEmailEqualsIgnoreCase(String email);

    Optional<Etudiant> findByMatriculeEqualsIgnoreCase(String matricule);
}
