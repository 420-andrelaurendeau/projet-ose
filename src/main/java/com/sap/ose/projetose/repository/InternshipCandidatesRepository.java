package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.models.InternshipCandidates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipCandidatesRepository extends JpaRepository<InternshipCandidates, Long> { }
