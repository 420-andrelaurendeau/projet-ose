package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> { }
