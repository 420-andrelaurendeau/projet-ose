package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.models.InternshipApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipCandidatesRepository extends JpaRepository<InternshipApplication, Long> {
}
