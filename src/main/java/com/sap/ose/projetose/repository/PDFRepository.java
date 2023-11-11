package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.PDF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PDFRepository extends JpaRepository<PDF, Long> {

    PDF findPDFByIsActive();
}
