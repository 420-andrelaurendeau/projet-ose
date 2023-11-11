package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.PDFDto;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.PDF;
import com.sap.ose.projetose.modeles.State;
import com.sap.ose.projetose.repository.PDFRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PDFService {

    private PDFRepository pdfRepository;

    private final Logger logger = LoggerFactory.getLogger(PDFService.class);

    @Autowired
    public PDFService(PDFRepository pdfRepository) {
        this.pdfRepository = pdfRepository;
    }

    @Transactional
    public PDFDto save(PDFDto pdfDto) {
        try {
            unsetDefaultPDF();

            File newFile = new File(pdfDto.getContent(), pdfDto.getFileName(), State.ACCEPTED);
            PDF newPdf = new PDF(newFile);
            PDF createdPDF = pdfRepository.save(newPdf);

            return new PDFDto(createdPDF);
        } catch (Exception e) {
            logger.error("Erreur lors de la sauvegarde du PDF", e);
            throw e;
        }
    }

    @Transactional
    public void unsetDefaultPDF(){
        try {
            PDF pdf = pdfRepository.findPDFByIsActive();
            pdf.setIsActive(false);
            pdfRepository.save(pdf);
        } catch (Exception e) {
            logger.error("Erreur lors de la mise à jour du PDF par défaut", e);
            throw e;
        }
    }

    @Transactional
    public PDFDto getCurrentPDF(){
        try {
            PDF pdf = pdfRepository.findPDFByIsActive();
            return new PDFDto(pdf);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération du PDF par défaut", e);
            throw e;
        }
    }
}
