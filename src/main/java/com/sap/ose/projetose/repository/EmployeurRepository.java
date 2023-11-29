package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Employeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeurRepository extends JpaRepository<Employeur, Long> {

    @Query("SELECT e FROM Employeur e WHERE e.email = ?1")
    Optional<Employeur> findByEmail(String email);

    @Query("SELECT e FROM Employeur e WHERE e.programme.id = ?1")
    List<Employeur> findAllEmployeurByProgram(long programId);
}
