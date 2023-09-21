package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Employeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeurRepositary extends JpaRepository<Employeur, Integer> {
}
