package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.modeles.InternOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternOfferRepository extends JpaRepository<InternOffer, Long> {
    @Query("select i from InternOffer i where i.state = com.sap.ose.projetose.modeles.State.ACCEPTED")
    List<InternOffer> findAllApproved();

    @Query("select i from InternOffer i where i.state = com.sap.ose.projetose.modeles.State.PENDING")
    List<InternOffer> findAllPending();

    @Query("select i from InternOffer i where i.state = com.sap.ose.projetose.modeles.State.DECLINED")
    List<InternOffer> findAllDeclined();

    @Query("select i from InternOffer  i where i.isAccepted = true")
    List<InternOffer> findAllApproved();
}

