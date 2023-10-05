package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Student, Long> {

    @Query("SELECT e FROM Student e WHERE e.email = ?1")
    Optional<Student> findByCourriel(String email);

    @Query("SELECT e FROM Student e WHERE e.matricule = ?1")
    Optional<Student> findByMatricule(String matricule);
}
