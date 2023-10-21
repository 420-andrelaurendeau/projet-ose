package com.sap.ose.projetose.repositories;

import com.sap.ose.projetose.models.InternshipApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipApplicationRepository extends JpaRepository<InternshipApplication, Long> {
}
