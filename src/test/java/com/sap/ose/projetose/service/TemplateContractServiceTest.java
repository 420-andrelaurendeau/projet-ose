package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.TemplateContractDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.TemplateContractRepository;
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
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {TemplateContractService.class})
@ExtendWith(SpringExtension.class)
class TemplateContractServiceTest {
    @MockBean
    private TemplateContractRepository pDFRepository;

    @Autowired
    private TemplateContractService pDFService;
    private File file;
    private File file2;

    private TemplateContract templateContract;
    private TemplateContract templateContract2;

    @BeforeEach
    void setUp() throws UnsupportedEncodingException {

        this.file = new File();
        this.file.setContent("AXAXAXAX".getBytes("UTF-8"));
        this.file.setEtudiant(new Etudiant());
        this.file.setFileName("foo.txt");
        this.file.setId(1L);
        this.file.setInternshipCandidates(new InternshipCandidates());
        this.file.setIsAccepted(State.ACCEPTED);

        this.templateContract = new TemplateContract();
        this.templateContract.setCreatedDate(LocalDate.of(1970, 1, 1));
        this.templateContract.setFile(file);
        this.templateContract.setId(1L);
        this.templateContract.setIsActive(true);

        this.file2 = new File();
        this.file2.setContent("AXAXAXAX".getBytes("UTF-8"));
        this.file2.setEtudiant(null);
        this.file2.setFileName("foo.txt");
        this.file2.setId(1L);
        this.file2.setInternshipCandidates(null);
        this.file2.setIsAccepted(State.ACCEPTED);

        this.templateContract2 = new TemplateContract();
        this.templateContract2.setCreatedDate(LocalDate.of(1970, 1, 1));
        this.templateContract2.setFile(file2);
        this.templateContract2.setId(1L);
        this.templateContract2.setIsActive(true);
    }


    @Test
    void save_CreatesPDFInRepository_WithNewPDFDto() throws UnsupportedEncodingException {

        when(pDFRepository.save(Mockito.<TemplateContract>any())).thenReturn(templateContract2);
        when(pDFRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        TemplateContractDto actualSaveResult = pDFService.save(new TemplateContractDto());
        verify(pDFRepository).findTemplateContractByIsActive();
        verify(pDFRepository, atLeast(1)).save(Mockito.<TemplateContract>any());
        assertEquals("1970-01-01", actualSaveResult.getCreatedDate());
        assertEquals("foo.txt", actualSaveResult.getFileName());
        assertEquals(1L, actualSaveResult.getFileId());
        assertTrue(actualSaveResult.getIsActive());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, Base64.getDecoder().decode(actualSaveResult.getContent()));
    }


    @Test
    void save_ThrowsDatabaseException_OnDataIntegrityViolation() {

        when(pDFRepository.save(Mockito.<TemplateContract>any())).thenThrow(new DataIntegrityViolationException(""));
        when(pDFRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        assertThrows(DatabaseException.class, () -> pDFService.save(new TemplateContractDto()));
        verify(pDFRepository).findTemplateContractByIsActive();
        verify(pDFRepository).save(Mockito.<TemplateContract>any());
    }


    @Test
    void save_ThrowsServiceException_OnPDFActiveStateError() {

        TemplateContract templateContract3 = mock(TemplateContract.class);
        when(templateContract3.getIsActive()).thenThrow(new ServiceException("Erreur lors de la sauvegarde du PDF"));
        when(templateContract3.getCreatedDate()).thenReturn(LocalDate.of(1970, 1, 1));
        doNothing().when(templateContract3).setCreatedDate(Mockito.<LocalDate>any());
        doNothing().when(templateContract3).setFile(Mockito.<File>any());
        doNothing().when(templateContract3).setId(anyLong());
        doNothing().when(templateContract3).setIsActive(Mockito.<Boolean>any());
        templateContract3.setCreatedDate(LocalDate.of(1970, 1, 1));
        templateContract3.setFile(file);
        templateContract3.setId(1L);
        templateContract3.setIsActive(true);
        when(pDFRepository.save(Mockito.<TemplateContract>any())).thenReturn(templateContract3);
        when(pDFRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        assertThrows(ServiceException.class, () -> pDFService.save(new TemplateContractDto()));
        verify(templateContract3).getCreatedDate();
        verify(templateContract3).getIsActive();
        verify(templateContract3).setCreatedDate(Mockito.<LocalDate>any());
        verify(templateContract3).setFile(Mockito.<File>any());
        verify(templateContract3).setId(anyLong());
        verify(templateContract3).setIsActive(Mockito.<Boolean>any());
        verify(pDFRepository).findTemplateContractByIsActive();
        verify(pDFRepository, atLeast(1)).save(Mockito.<TemplateContract>any());
    }


    @Test
    void unsetDefaultPDF_UnsetsActivePDF_WhenCalled() {

        when(pDFRepository.save(Mockito.<TemplateContract>any())).thenReturn(templateContract2);
        when(pDFRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        pDFService.unsetDefaultPDF();
        verify(pDFRepository).findTemplateContractByIsActive();
        verify(pDFRepository).save(Mockito.<TemplateContract>any());
    }


    @Test
    void unsetDefaultPDF_ThrowsDatabaseException_WhenDataIntegrityViolated() {

        when(pDFRepository.save(Mockito.<TemplateContract>any())).thenThrow(new DataIntegrityViolationException(""));
        when(pDFRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        assertThrows(DatabaseException.class, () -> pDFService.unsetDefaultPDF());
        verify(pDFRepository).findTemplateContractByIsActive();
        verify(pDFRepository).save(Mockito.<TemplateContract>any());
    }

    @Test
    void getCurrentPDF_ReturnsActivePDF_WhenPDFIsActive() throws UnsupportedEncodingException {

        when(pDFRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        TemplateContractDto actualCurrentPDF = pDFService.getCurrentPDF();
        verify(pDFRepository).findTemplateContractByIsActive();
        assertEquals("1970-01-01", actualCurrentPDF.getCreatedDate());
        assertEquals("foo.txt", actualCurrentPDF.getFileName());
        assertEquals(1L, actualCurrentPDF.getFileId());
        assertTrue(actualCurrentPDF.getIsActive());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, Base64.getDecoder().decode(actualCurrentPDF.getContent()));
    }

    @Test
    void getCurrentPDF_ReturnsActivePDFWithExpectedAttributes_WhenPDFIsActive() throws UnsupportedEncodingException {

        TemplateContract templateContract = mock(TemplateContract.class);
        when(templateContract.getFile()).thenReturn(file);
        when(templateContract.getIsActive()).thenReturn(true);
        when(templateContract.getCreatedDate()).thenReturn(LocalDate.of(1970, 1, 1));
        doNothing().when(templateContract).setCreatedDate(Mockito.<LocalDate>any());
        doNothing().when(templateContract).setFile(Mockito.<File>any());
        doNothing().when(templateContract).setId(anyLong());
        doNothing().when(templateContract).setIsActive(Mockito.<Boolean>any());
        templateContract.setCreatedDate(LocalDate.of(1970, 1, 1));
        templateContract.setFile(file2);
        templateContract.setId(1L);
        templateContract.setIsActive(true);
        when(pDFRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        TemplateContractDto actualCurrentPDF = pDFService.getCurrentPDF();
        verify(templateContract).getCreatedDate();
        verify(templateContract, atLeast(1)).getFile();
        verify(templateContract).getIsActive();
        verify(templateContract).setCreatedDate(Mockito.<LocalDate>any());
        verify(templateContract).setFile(Mockito.<File>any());
        verify(templateContract).setId(anyLong());
        verify(templateContract).setIsActive(Mockito.<Boolean>any());
        verify(pDFRepository).findTemplateContractByIsActive();
        assertEquals("1970-01-01", actualCurrentPDF.getCreatedDate());
        assertEquals("foo.txt", actualCurrentPDF.getFileName());
        assertEquals(1L, actualCurrentPDF.getFileId());
        assertTrue(actualCurrentPDF.getIsActive());
        byte[] expectedContent = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedContent, Base64.getDecoder().decode(actualCurrentPDF.getContent()));
    }

    @Test
    void getCurrentPDF_ThrowsServiceException_WhenPDFIsActiveIsInvalid() throws UnsupportedEncodingException {

        TemplateContract templateContract = mock(TemplateContract.class);
        when(templateContract.getIsActive()).thenThrow(new ServiceException("Erreur lors de la récupération du PDF par défaut"));
        when(templateContract.getCreatedDate()).thenReturn(LocalDate.of(1970, 1, 1));
        doNothing().when(templateContract).setCreatedDate(Mockito.<LocalDate>any());
        doNothing().when(templateContract).setFile(Mockito.<File>any());
        doNothing().when(templateContract).setId(anyLong());
        doNothing().when(templateContract).setIsActive(Mockito.<Boolean>any());
        templateContract.setCreatedDate(LocalDate.of(1970, 1, 1));
        templateContract.setFile(file2);
        templateContract.setId(1L);
        templateContract.setIsActive(true);
        when(pDFRepository.findTemplateContractByIsActive()).thenReturn(templateContract);
        assertThrows(ServiceException.class, () -> pDFService.getCurrentPDF());
        verify(templateContract).getCreatedDate();
        verify(templateContract).getIsActive();
        verify(templateContract).setCreatedDate(Mockito.<LocalDate>any());
        verify(templateContract).setFile(Mockito.<File>any());
        verify(templateContract).setId(anyLong());
        verify(templateContract).setIsActive(Mockito.<Boolean>any());
        verify(pDFRepository).findTemplateContractByIsActive();
    }
}
