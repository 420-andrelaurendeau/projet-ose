package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternOfferRepository extends JpaRepository<InternOffer, Long> {
    @Query("select i from InternOffer i where i.state = com.sap.ose.projetose.modeles.State.ACCEPTED")
    Page<InternOffer> findAllApproved(Pageable pageable);

    @Query("select i from InternOffer i where i.state = com.sap.ose.projetose.modeles.State.PENDING")
    List<InternOffer> findAllPending();

    @Query("select i from InternOffer i where i.state = com.sap.ose.projetose.modeles.State.DECLINED")
    List<InternOffer> findAllDeclined();

    @Query("SELECT i FROM InternOffer i WHERE i.employeur.id = ?1")
    List<InternOffer> findByEmployeurId(int id);


    @Query("SELECT i FROM InternOffer i WHERE (:session IS NULL OR :session = '') OR i.session = :session")
    Page<InternOffer> findAllBySession(Pageable pageable, @Param("session") String session);

    @Query("SELECT i FROM InternOffer i WHERE i.state = :state AND (:session IS NULL OR :session = '' OR i.session = :session)")
    Page<InternOffer> findAllByStateAndSession(@Param("state") State state, @Param("session") String session, Pageable pageable);



    @Query("SELECT i.state, COUNT(i) FROM InternOffer i GROUP BY i.state")
    List<Object[]> getCountByState();

    @Query("SELECT i FROM InternOffer i WHERE i.employeur.id = ?1 AND (?2 is null OR ?2 = '' OR i.session = ?2)")
    Page<InternOffer> findAllById(Long id, String session, Pageable pageable);

    @Query("SELECT i FROM InternOffer i WHERE i.employeur.id = ?1")
    List<InternOffer> findInternOffersById(Long id);

    @Query("SELECT i FROM InternOffer i WHERE i.session = ?1 AND i.employeur.id = ?2")
    List<InternOffer> findInternOffersSeasonById(String session, Long id);

    @Query("SELECT DISTINCT i.session FROM InternOffer i WHERE i.employeur.id = ?1 ")
    List<String> findEmployeurOffersSeasons(Long id);

    @Query("SELECT DISTINCT i.session FROM InternOffer i WHERE i.state = 0")
    List<String> getOfferApprovedSeasons();

    @Query("SELECT DISTINCT i.session FROM InternOffer i")
    List<String> getAllOfferSeasons();

    @Query("SELECT i FROM InternOffer i WHERE i.session = ?1 AND i.state = 0")
    List<InternOffer> getStudentOffersBySeason(String season);

    @Query("SELECT i FROM InternOffer i WHERE i.session = ?1")
    List<InternOffer> findOfferBySeason(String session);
}

