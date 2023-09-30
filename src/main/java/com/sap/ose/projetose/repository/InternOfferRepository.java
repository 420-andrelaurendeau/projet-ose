package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.InternOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InternOfferRepository extends JpaRepository<InternOffer, Long> {

    @Query("SELECT i FROM InternOffer i WHERE i.employeur.id = ?1")
    List<InternOffer> findByEmployeurId(int id);

}

