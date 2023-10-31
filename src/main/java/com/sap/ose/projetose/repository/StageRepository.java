package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Interview;
import com.sap.ose.projetose.modeles.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
}
