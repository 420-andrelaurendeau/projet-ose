package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.InternshipCandidates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipCandidatesRepository extends JpaRepository<InternshipCandidates, Long> {

    @Query("select i from InternOffer i where i.state = com.sap.ose.projetose.modeles.State.PENDING")
    List<InternshipCandidates> findAllPending();

    @Query("select i from InternOffer i where i.state = com.sap.ose.projetose.modeles.State.ACCEPTED")
    List<InternshipCandidates> findAllAccepted();

    @Query("select i from InternOffer i where i.state = com.sap.ose.projetose.modeles.State.DECLINED")
    List<InternshipCandidates> findAllDeclined();
}
