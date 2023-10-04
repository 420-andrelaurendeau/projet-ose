package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.models.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
    Optional<Employer> findByEmailEqualsIgnoreCase(String email);
}
