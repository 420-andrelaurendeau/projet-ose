package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.InternOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternOfferRepository extends JpaRepository<InternOffer, Long> {

}

