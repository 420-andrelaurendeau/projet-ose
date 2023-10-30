package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Employeur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeurRepository extends JpaRepository<Employeur, Long> {
    Optional<Employeur> findAllByEmailEqualsIgnoreCase(String email);

}
