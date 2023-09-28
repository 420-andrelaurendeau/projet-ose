package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.InternOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternOfferRepository extends JpaRepository<InternOffer, Long> {

    @Query("select i from InternOffer  i where i.isAccepted = true")
    List<InternOffer> findAllApproved();
}

