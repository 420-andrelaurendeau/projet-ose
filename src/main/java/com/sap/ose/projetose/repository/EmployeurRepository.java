package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.models.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeurRepository extends JpaRepository<Employer, Long> {


    // find by email
    @Query("SELECT e FROM Employer e WHERE e.email = ?1")
    Optional<Employer> findByEmail(String email);

}
