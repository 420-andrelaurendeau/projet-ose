package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.PDF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PDFRepository extends JpaRepository<PDF, Long> {

    @Query(value = "SELECT * FROM pdf WHERE is_active = true", nativeQuery = true)
    PDF findPDFByIsActive();
}
