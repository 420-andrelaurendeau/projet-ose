package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.InternshipCandidates;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipCandidatesRepository extends JpaRepository<InternshipCandidates, Long> {

    @Query("SELECT ic FROM InternshipCandidates ic WHERE ic.internOffer.id = ?1")
    List<InternshipCandidates> findAllByInternOfferId(Long id);

    @Query("select i from InternshipCandidates i where i.state = com.sap.ose.projetose.modeles.State.PENDING")
    List<InternshipCandidates> findAllPending();

    @Query("select i from InternshipCandidates i where i.state = com.sap.ose.projetose.modeles.State.ACCEPTED")
    List<InternshipCandidates> findAllAccepted();

    @Query("select i from InternshipCandidates i where i.state = com.sap.ose.projetose.modeles.State.DECLINED")
    List<InternshipCandidates> findAllDeclined();

    @Query("SELECT ic FROM InternshipCandidates ic WHERE ic.etudiant.id = :id AND (:session IS NULL OR :session = '' OR ic.internOffer.session = :session)")
    Page<InternshipCandidates> findAllByStudentId(@Param("id") Long id, @Param("session") String session, Pageable pageable);
}
