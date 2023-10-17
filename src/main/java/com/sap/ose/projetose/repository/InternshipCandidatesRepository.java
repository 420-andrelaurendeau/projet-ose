package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.InternshipCandidates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipCandidatesRepository extends JpaRepository<InternshipCandidates, Long> {

    @Query("SELECT ic FROM InternshipCandidates ic WHERE ic.internOffer.id = ?1")
    List<InternshipCandidates> findAllByInternOfferId(Long id);

}
