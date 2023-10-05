package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.models.InternshipOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternOfferRepository extends JpaRepository<InternshipOffer, Long> {
    @Query("select i from InternshipOffer i where i.state = com.sap.ose.projetose.models.ApprovalStatus.APPROVED")
    List<InternshipOffer> findAllApproved();

    @Query("select i from InternshipOffer i where i.state = com.sap.ose.projetose.models.ApprovalStatus.PENDING")
    List<InternshipOffer> findAllPending();

    @Query("select i from InternshipOffer i where i.state = com.sap.ose.projetose.models.ApprovalStatus.REJECTED")
    List<InternshipOffer> findAllDeclined();

    @Query("SELECT i FROM InternshipOffer i WHERE i.employeur.id = ?1")
    List<InternshipOffer> findByEmployeurId(int id);

}

