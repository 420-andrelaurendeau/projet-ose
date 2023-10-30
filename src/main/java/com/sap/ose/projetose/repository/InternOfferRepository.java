package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Etats;
import com.sap.ose.projetose.modeles.InternOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternOfferRepository extends JpaRepository<InternOffer, Long> {
    default List<InternOffer> findAllByStateIsApproved() {
        return findAllByStateEquals(Etats.APPROVED);
    }

    default List<InternOffer> findAllByStateIsPending() {
        return findAllByStateEquals(Etats.PENDING);
    }

    default List<InternOffer> findAllByStateIsRejected() {
        return findAllByStateEquals(Etats.REJECTED);
    }

    List<InternOffer> findAllByStateEquals(Etats status);

    List<InternOffer> findAllByEmployerId(long id);

}

