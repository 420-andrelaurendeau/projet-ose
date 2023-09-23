package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.InternshipCandidatesDto;
import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.InternOfferRepository;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {InternOfferService.class})
@ExtendWith(SpringExtension.class)
class InternOfferServiceTest {
    @MockBean
    private InternOfferRepository internOfferRepository;

    @Autowired
    private InternOfferService internOfferService;

    private File file;
    private Programme programme;
    private InternOffer internOffer;

    @BeforeEach
    void setUp() throws UnsupportedEncodingException {
        // Initialisation de File
        file = new File();
        file.setAccepted(true);
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setFileName("foo.txt");
        file.setId(1L);

        // Initialisation de Programme
        programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1);
        programme.setNom("Nom");

        // Initialisation de InterOfferJob
        internOffer = new InternOffer();
        internOffer.setDescription("The characteristics of someone or something");
        internOffer.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer.setFile(file);
        internOffer.setId(1L);
        internOffer.setInternshipCandidates(new ArrayList<>());
        internOffer.setLocation("Location");
        internOffer.setProgramme(programme);
        internOffer.setSalaryByHour(10.0d);
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setTitle("Dr");
    }

    @Test
    void testSaveInterOfferJob_Null() {
        assertThrows(RuntimeException.class, () -> internOfferService.saveInterOfferJob(new InternOfferDto()));
        assertThrows(RuntimeException.class, () -> internOfferService.saveInterOfferJob(null));
    }

    @Test
    void testSaveInterOfferJob_Success() throws UnsupportedEncodingException {
        when(internOfferRepository.save(Mockito.<InternOffer>any())).thenReturn(internOffer);
        ArrayList<InternshipCandidatesDto> internshipCandidates = new ArrayList<>();
        ProgrammeDto programme2 = new ProgrammeDto(1, "Erreur inconnue lors de la sauvegarde de l'offre d'emploi.",
                "The characteristics of someone or something");

        assertSame(internOffer, internOfferService.saveInterOfferJob(new InternOfferDto(1L, "Dr",
                "Erreur inconnue lors de la sauvegarde de l'offre d'emploi.", "The characteristics of someone or something",
                10.0d, "2020-03-01", "2020-03-01", internshipCandidates, programme2, new FileDto())));
        verify(internOfferRepository).save(Mockito.<InternOffer>any());
    }

    @Test
    void testSaveInterOfferJob_ProgramNull_RuntimeException() {
        ArrayList<InternshipCandidatesDto> internshipCandidates = new ArrayList<>();
        assertThrows(RuntimeException.class, () -> internOfferService.saveInterOfferJob(new InternOfferDto(1L, "Dr",
                "Erreur inconnue lors de la sauvegarde de l'offre d'emploi.", "The characteristics of someone or something",
                10.0d, "2020-03-01", "2020-03-01", internshipCandidates, null, new FileDto())));
    }

    @Test
    void testSaveInterOfferJob_DataIntegrityViolation() throws UnsupportedEncodingException {

        when(internOfferRepository.save(Mockito.any(InternOffer.class)))
                .thenThrow(new DataIntegrityViolationException("Violation d'intégrité des données"));

        assertThrows(DataIntegrityViolationException.class, () ->
                internOfferService.saveInterOfferJob(new InternOfferDto(internOffer)));
    }

    @Test
    void testSaveInterOfferJob_DataAccessError() {
        when(internOfferRepository.save(Mockito.any(InternOffer.class)))
                .thenThrow(new DataAccessException("Erreur d'accès aux données") {});

        assertThrows(DataAccessException.class, () ->
                internOfferService.saveInterOfferJob(new InternOfferDto(internOffer)));
    }
}

