package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammeRepository extends JpaRepository<Programme, Integer> {
}