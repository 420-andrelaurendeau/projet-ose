package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Employeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeurRepository extends JpaRepository<Employeur, Long> {


    // find by email
    @Query("SELECT e FROM Employeur e WHERE e.email = ?1")
    Optional<Employeur> findByEmail(String email);

}
