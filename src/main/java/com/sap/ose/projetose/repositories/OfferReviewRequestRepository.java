package com.sap.ose.projetose.repositories;

import com.sap.ose.projetose.models.OfferReviewRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferReviewRequestRepository extends JpaRepository<OfferReviewRequest, Long> {

}
