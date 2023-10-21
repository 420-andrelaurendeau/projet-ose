package com.sap.ose.projetose.repositories;

import com.sap.ose.projetose.models.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
    Optional<Employer> findAllByEmailEqualsIgnoreCase(String email);

}