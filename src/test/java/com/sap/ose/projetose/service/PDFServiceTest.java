package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.PDFDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.PDFRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PDFService.class})
@ExtendWith(SpringExtension.class)
class PDFServiceTest {
    @MockBean
    private PDFRepository pDFRepository;

    @Autowired
    private PDFService pDFService;

    private File file;
    private File file2;

    private PDF pdf;
    private PDF pdf2;

    @BeforeEach
    void setUp() throws UnsupportedEncodingException {

        this.file = new File();
        this.file.setContent("AXAXAXAX".getBytes("UTF-8"));
        this.file.setEtudiant(new Etudiant());
        this.file.setFileName("foo.txt");
        this.file.setId(1L);
        this.file.setInternshipCandidates(new InternshipCandidates());
        this.file.setIsAccepted(State.ACCEPTED);

        this.pdf = new PDF();
        this.pdf.setCreatedDate(LocalDate.of(1970, 1, 1));
        this.pdf.setFile(file);
        this.pdf.setId(1L);
        this.pdf.setIsActive(true);

        this.file2 = new File();
        this.file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        this.file2.setEtudiant(null);
        this.file2.setFileName("foo.txt");
        this.file2.setId(1L);
        this.file2.setInternshipCandidates(null);
        this.file2.setIsAccepted(State.ACCEPTED);

        this.pdf2 = new PDF();
        this.pdf2.setCreatedDate(LocalDate.of(1970, 1, 1));
        this.pdf2.setFile(file2);
        this.pdf2.setId(1L);
        this.pdf2.setIsActive(true);
    }


    @Test
    void save_CreatesPDFInRepository_WithNewPDFDto() throws UnsupportedEncodingException {

        when(pDFRepository.save(Mockito.<PDF>any())).thenReturn(pdf2);
        when(pDFRepository.findPDFByIsActive()).thenReturn(pdf);
        PDFDto actualSaveResult = pDFService.save(new PDFDto());
        verify(pDFRepository).findPDFByIsActive();
        verify(pDFRepository, atLeast(1)).save(Mockito.<PDF>any());
        assertEquals("1970-01-01", actualSaveResult.getCreatedDate());
        assertEquals("foo.txt", actualSaveResult.getFileName());
        assertEquals(1L, actualSaveResult.getFileId());
        assertTrue(actualSaveResult.getIsActive());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, actualSaveResult.getContent());
    }


    @Test
    void save_ThrowsDatabaseException_OnDataIntegrityViolation() {

        when(pDFRepository.save(Mockito.<PDF>any())).thenThrow(new DataIntegrityViolationException(""));
        when(pDFRepository.findPDFByIsActive()).thenReturn(pdf);
        assertThrows(DatabaseException.class, () -> pDFService.save(new PDFDto()));
        verify(pDFRepository).findPDFByIsActive();
        verify(pDFRepository).save(Mockito.<PDF>any());
    }


    @Test
    void save_ThrowsServiceException_OnPDFActiveStateError() {

        PDF pdf3 = mock(PDF.class);
        when(pdf3.getIsActive()).thenThrow(new ServiceException("Erreur lors de la sauvegarde du PDF"));
        when(pdf3.getCreatedDate()).thenReturn(LocalDate.of(1970, 1, 1));
        doNothing().when(pdf3).setCreatedDate(Mockito.<LocalDate>any());
        doNothing().when(pdf3).setFile(Mockito.<File>any());
        doNothing().when(pdf3).setId(anyLong());
        doNothing().when(pdf3).setIsActive(Mockito.<Boolean>any());
        pdf3.setCreatedDate(LocalDate.of(1970, 1, 1));
        pdf3.setFile(file);
        pdf3.setId(1L);
        pdf3.setIsActive(true);
        when(pDFRepository.save(Mockito.<PDF>any())).thenReturn(pdf3);
        when(pDFRepository.findPDFByIsActive()).thenReturn(pdf);
        assertThrows(ServiceException.class, () -> pDFService.save(new PDFDto()));
        verify(pdf3).getCreatedDate();
        verify(pdf3).getIsActive();
        verify(pdf3).setCreatedDate(Mockito.<LocalDate>any());
        verify(pdf3).setFile(Mockito.<File>any());
        verify(pdf3).setId(anyLong());
        verify(pdf3).setIsActive(Mockito.<Boolean>any());
        verify(pDFRepository).findPDFByIsActive();
        verify(pDFRepository, atLeast(1)).save(Mockito.<PDF>any());
    }


    @Test
    void unsetDefaultPDF_UnsetsActivePDF_WhenCalled() {

        when(pDFRepository.save(Mockito.<PDF>any())).thenReturn(pdf2);
        when(pDFRepository.findPDFByIsActive()).thenReturn(pdf);
        pDFService.unsetDefaultPDF();
        verify(pDFRepository).findPDFByIsActive();
        verify(pDFRepository).save(Mockito.<PDF>any());
    }


    @Test
    void unsetDefaultPDF_ThrowsDatabaseException_WhenDataIntegrityViolated() {

        when(pDFRepository.save(Mockito.<PDF>any())).thenThrow(new DataIntegrityViolationException(""));
        when(pDFRepository.findPDFByIsActive()).thenReturn(pdf);
        assertThrows(DatabaseException.class, () -> pDFService.unsetDefaultPDF());
        verify(pDFRepository).findPDFByIsActive();
        verify(pDFRepository).save(Mockito.<PDF>any());
    }

    @Test
    void getCurrentPDF_ReturnsActivePDF_WhenPDFIsActive() throws UnsupportedEncodingException {

        when(pDFRepository.findPDFByIsActive()).thenReturn(pdf);
        PDFDto actualCurrentPDF = pDFService.getCurrentPDF();
        verify(pDFRepository).findPDFByIsActive();
        assertEquals("1970-01-01", actualCurrentPDF.getCreatedDate());
        assertEquals("foo.txt", actualCurrentPDF.getFileName());
        assertEquals(1L, actualCurrentPDF.getFileId());
        assertTrue(actualCurrentPDF.getIsActive());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, actualCurrentPDF.getContent());
    }

    @Test
    void getCurrentPDF_ReturnsActivePDFWithExpectedAttributes_WhenPDFIsActive() throws UnsupportedEncodingException {

        PDF pdf = mock(PDF.class);
        when(pdf.getFile()).thenReturn(file);
        when(pdf.getIsActive()).thenReturn(true);
        when(pdf.getCreatedDate()).thenReturn(LocalDate.of(1970, 1, 1));
        doNothing().when(pdf).setCreatedDate(Mockito.<LocalDate>any());
        doNothing().when(pdf).setFile(Mockito.<File>any());
        doNothing().when(pdf).setId(anyLong());
        doNothing().when(pdf).setIsActive(Mockito.<Boolean>any());
        pdf.setCreatedDate(LocalDate.of(1970, 1, 1));
        pdf.setFile(file2);
        pdf.setId(1L);
        pdf.setIsActive(true);
        when(pDFRepository.findPDFByIsActive()).thenReturn(pdf);
        PDFDto actualCurrentPDF = pDFService.getCurrentPDF();
        verify(pdf).getCreatedDate();
        verify(pdf, atLeast(1)).getFile();
        verify(pdf).getIsActive();
        verify(pdf).setCreatedDate(Mockito.<LocalDate>any());
        verify(pdf).setFile(Mockito.<File>any());
        verify(pdf).setId(anyLong());
        verify(pdf).setIsActive(Mockito.<Boolean>any());
        verify(pDFRepository).findPDFByIsActive();
        assertEquals("1970-01-01", actualCurrentPDF.getCreatedDate());
        assertEquals("foo.txt", actualCurrentPDF.getFileName());
        assertEquals(1L, actualCurrentPDF.getFileId());
        assertTrue(actualCurrentPDF.getIsActive());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, actualCurrentPDF.getContent());
    }

    @Test
    void getCurrentPDF_ThrowsServiceException_WhenPDFIsActiveIsInvalid() throws UnsupportedEncodingException {

        PDF pdf = mock(PDF.class);
        when(pdf.getIsActive()).thenThrow(new ServiceException("Erreur lors de la récupération du PDF par défaut"));
        when(pdf.getCreatedDate()).thenReturn(LocalDate.of(1970, 1, 1));
        doNothing().when(pdf).setCreatedDate(Mockito.<LocalDate>any());
        doNothing().when(pdf).setFile(Mockito.<File>any());
        doNothing().when(pdf).setId(anyLong());
        doNothing().when(pdf).setIsActive(Mockito.<Boolean>any());
        pdf.setCreatedDate(LocalDate.of(1970, 1, 1));
        pdf.setFile(file2);
        pdf.setId(1L);
        pdf.setIsActive(true);
        when(pDFRepository.findPDFByIsActive()).thenReturn(pdf);
        assertThrows(ServiceException.class, () -> pDFService.getCurrentPDF());
        verify(pdf).getCreatedDate();
        verify(pdf).getIsActive();
        verify(pdf).setCreatedDate(Mockito.<LocalDate>any());
        verify(pdf).setFile(Mockito.<File>any());
        verify(pdf).setId(anyLong());
        verify(pdf).setIsActive(Mockito.<Boolean>any());
        verify(pDFRepository).findPDFByIsActive();
    }
}
