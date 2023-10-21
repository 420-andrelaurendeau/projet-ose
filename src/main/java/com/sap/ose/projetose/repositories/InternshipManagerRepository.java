package com.sap.ose.projetose.repositories;

import com.sap.ose.projetose.models.InternshipManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipManagerRepository extends JpaRepository<InternshipManager, Long> {
}
