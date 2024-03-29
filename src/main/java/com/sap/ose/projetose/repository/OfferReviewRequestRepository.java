package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.OfferReviewRequest;
import com.sap.ose.projetose.modeles.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferReviewRequestRepository extends JpaRepository<OfferReviewRequest, Long> {

}
