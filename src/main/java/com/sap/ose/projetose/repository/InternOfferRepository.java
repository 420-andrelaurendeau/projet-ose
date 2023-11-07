package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT i FROM InternOffer i WHERE i.employeur.id = ?1")
    List<InternOffer> findByEmployeurId(int id);


    Page<InternOffer>   findAllByState(State state, Pageable pageable);


    @Query("SELECT i.state, COUNT(i) FROM InternOffer i GROUP BY i.state")
    List<Object[]> getCountByState();

    @Query("SELECT i FROM InternOffer i WHERE i.employeur.id = ?1")
    Page<InternOffer> findAllById(Long id, Pageable pageable);

    @Query("SELECT DISTINCT i.session FROM InternOffer i")
    List<String> getAllSeasons();
}

