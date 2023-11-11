package com.sap.ose.projetose.service;

import com.sap.ose.projetose.repository.PDFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PDFService {

    private PDFRepository pdfRepository;

    @Autowired
    public PDFService(PDFRepository pdfRepository) {
        this.pdfRepository = pdfRepository;
    }

}
