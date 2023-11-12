package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.PDFDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.PDF;
import com.sap.ose.projetose.modeles.State;
import com.sap.ose.projetose.repository.PDFRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class PDFService {

    private final Logger logger = LoggerFactory.getLogger(PDFService.class);
    private PDFRepository pdfRepository;

    @Autowired
    public PDFService(PDFRepository pdfRepository) {
        this.pdfRepository = pdfRepository;
    }

    @Transactional
    public PDFDto save(PDFDto pdfDto) {
        try {
            unsetDefaultPDF();

            File newFile = new File(pdfDto.getContent(), pdfDto.getFileName(), State.ACCEPTED);
            PDF newPDF = new PDF(newFile);

            PDF savedPDF = pdfRepository.save(newPDF);
            return new PDFDto(savedPDF);
        } catch (DataAccessException | DatabaseException e) {
            logger.error("Erreur lors de la sauvegarde du PDF", e);
            throw new DatabaseException("");
        } catch (Exception e) {
            logger.error("Erreur lors de la sauvegarde du PDF", e);
            throw new ServiceException("");
        }
    }

    @Transactional
    public void unsetDefaultPDF() {
        try {
            PDF pdf = pdfRepository.findPDFByIsActive();
            if (pdf != null) {
                pdf.setIsActive(false);
                pdfRepository.save(pdf);
            }
        } catch (DataAccessException e) {
            logger.error("Erreur lors de la mise à jour du PDF par défaut", e);
            throw new DatabaseException("");
        } catch (Exception e) {
            logger.error("Erreur lors de la mise à jour du PDF par défaut", e);
            throw new ServiceException("");
        }
    }


    @Transactional
    public PDF findCurrent() {
        try {
            return pdfRepository.findPDFByIsActive();
        } catch (DataAccessException e) {
            logger.error("Erreur lors de la récupération du PDF", e);
            throw new DatabaseException("");
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération du PDF", e);
            throw new ServiceException("");
        }
    }

    @Transactional
    public PDFDto getCurrentPDF() {
        try {
            PDF pdf = pdfRepository.findPDFByIsActive();
            return new PDFDto(pdf);
        } catch (DataAccessException e) {
            logger.error("Erreur lors de la récupération du PDF par défaut", e);
            throw new DatabaseException("");
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération du PDF par défaut", e);
            throw new ServiceException("");
        }
    }
}
