package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Internshipmanager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InternshipmanagerRepository extends JpaRepository<Internshipmanager, Long> {

    @Query("SELECT i FROM Internshipmanager i WHERE i.email = ?1")
    Optional<Internshipmanager> findByEmail(String email);
}
