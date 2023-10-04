package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.models.AssessmentState;
import com.sap.ose.projetose.models.InternshipOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipOfferRepository extends JpaRepository<InternshipOffer, Long> {
    List<InternshipOffer> findAllByState(AssessmentState state);

    default List<InternshipOffer> findAllApproved() {
        return findAllByState(AssessmentState.APPROVED);
    }

    default List<InternshipOffer> findAllPending() {
        return findAllByState(AssessmentState.PENDING);
    }

    default List<InternshipOffer> findAllRejected() {
        return findAllByState(AssessmentState.REJECTED);
    }
}

