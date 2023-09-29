package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.InternOfferRepository;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {InternOfferService.class})
@ExtendWith(SpringExtension.class)
class InternOfferServiceTest {

    private static final long VALID_ID = 1L;
    private static final long INVALID_ID = 99L;
    @Autowired
    private InternOfferService internOfferService;
    @MockBean
    private ProgrammeService programmeService;
    @MockBean
    private ProgrammeRepository programmeRepository;
    @MockBean
    private EmployeurService employeurService;
    @MockBean
    private EmployeurRepository employeurRepository;
    @MockBean
    private InternOfferRepository internOfferRepository;
    private final InternOfferDto internOfferDto = new InternOfferDto();

    @BeforeEach
    public void setUp() {
        this.internOfferDto.setId(0);
        this.internOfferDto.setDescription("The characteristics of someone or something");
        this.internOfferDto.setEmployeurEntreprise("Employeur Entreprise");
        this.internOfferDto.setEmployeurId(1L);
        this.internOfferDto.setEmployeurNom("Employeur Nom");
        this.internOfferDto.setEmployeurPrenom("Employeur Prenom");
        this.internOfferDto.setEndDate("2020-03-01");
        this.internOfferDto.setFile(new FileDto());
        this.internOfferDto.setId(1L);
        this.internOfferDto.setInternshipCandidates(new ArrayList<>());
        this.internOfferDto.setLocation("Location");
        this.internOfferDto.setProgrammeId(1);
        this.internOfferDto.setProgrammeNom("Programme Nom");
        this.internOfferDto.setSalaryByHour(10.0d);
        this.internOfferDto.setStartDate("2020-03-01");
        this.internOfferDto.setTitle("Dr");
    }

    @Test
    public void saveInterOfferJob_ProgrammeNotFound() {
        when(programmeService.findById(anyLong())).thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(EmptyResultDataAccessException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
    }

    @Test
    public void saveInterOfferJob_EmployeurNotFound() {
        when(employeurService.findById(anyLong())).thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(EmptyResultDataAccessException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
    }

    @Test
    public void saveInterOfferJob_DataIntegrityError() {
        when(internOfferRepository.save(any())).thenThrow(new DataIntegrityViolationException("Test case") {
        });

        assertThrows(DataIntegrityViolationException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
    }

    @Test
    public void saveInterOfferJob_DataAccessError() {
        when(internOfferRepository.save(any())).thenThrow(new DataAccessException("Test case") {
        });

        assertThrows(DataAccessException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
    }

    @Test
    public void saveInterOfferJob_UnknownError() {
        when(internOfferRepository.save(any())).thenThrow(new IllegalArgumentException("Test case"));

        assertThrows(RuntimeException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
    }


    @Test
    public void getById_Success() {
        InternOffer mockOffer = internOfferDto.fromDto();
        when(internOfferRepository.findById(VALID_ID)).thenReturn(Optional.of(mockOffer));

        InternOffer result = internOfferService.findById(VALID_ID);

        Assertions.assertEquals(mockOffer, result);
    }

    @Test
    public void getById_NotFound() {
        when(internOfferRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        assertThrows(EmptyResultDataAccessException.class, () -> internOfferService.findById(INVALID_ID));
    }

    @Test
    public void getById_DataIntegrityViolation() {
        when(internOfferRepository.findById(anyLong())).thenThrow(new DataIntegrityViolationException("Test exception"));

        assertThrows(DataIntegrityViolationException.class, () -> internOfferService.findById(VALID_ID));
    }

    @Test
    public void getById_DataAccessError() {
        when(internOfferRepository.findById(anyLong())).thenThrow(new DataAccessException("Test exception") {
        });

        assertThrows(DataAccessException.class, () -> internOfferService.findById(VALID_ID));
    }

    @Test
    public void getById_UnknownError() {
        when(internOfferRepository.findById(anyLong())).thenThrow(new RuntimeException("Test exception"));

        assertThrows(RuntimeException.class, () -> internOfferService.findById(VALID_ID));
    }
}

