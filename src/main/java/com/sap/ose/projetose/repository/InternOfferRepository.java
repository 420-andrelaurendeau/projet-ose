package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.models.ApprovalStatus;
import com.sap.ose.projetose.models.InternshipOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternOfferRepository extends JpaRepository<InternshipOffer, Long> {
    default List<InternshipOffer> findAllByStateIsApproved() {
        return findAllByStateEquals(ApprovalStatus.APPROVED);
    }
    default List<InternshipOffer> findAllByStateIsPending() {
        return findAllByStateEquals(ApprovalStatus.PENDING);
    }

    default List<InternshipOffer> findAllByStateIsRejected() {
        return findAllByStateEquals(ApprovalStatus.REJECTED);
    }

    List<InternshipOffer> findAllByStateEquals(ApprovalStatus status);

    List<InternshipOffer> findAllByEmployerId(long id);

}

