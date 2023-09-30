package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternshipmanagerDto;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {InternshipmanagerService.class})
@ExtendWith(SpringExtension.class)
public class InternshipmanagerServiceTest {

    @Autowired
    private InternshipmanagerService internshipmanagerService;

    @MockBean
    private InternshipmanagerRepository internshipmanagerRepository;
    @MockBean
    private ProgrammeService programmeService;
    @MockBean
    private ProgrammeRepository programmeRepository;
    private InternshipmanagerDto internshipmanagerDto = new InternshipmanagerDto();

    private final long INVALID_ID = 99L;
    private final long VALID_ID = 1L;

    @BeforeEach
    public void setUp() {
        this.internshipmanagerDto.setId(0);
        this.internshipmanagerDto.setProgrammeId(1);
        this.internshipmanagerDto.setNom("Nom");
        this.internshipmanagerDto.setPrenom("Prenom");
        this.internshipmanagerDto.setEmail("Email");
        this.internshipmanagerDto.setPhone("Email");
    }


    @Test
    public void findById_NotFound() {
        when(internshipmanagerRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        EmptyResultDataAccessException reslut = assertThrows(EmptyResultDataAccessException.class, () -> internshipmanagerService.findById(INVALID_ID));
        assertEquals(0, reslut.getActualSize());
    }

    @Test
    public void findById_DataIntegrityViolation() {
        when(internshipmanagerRepository.findById(anyLong())).thenThrow(new DataIntegrityViolationException(""));

        DataIntegrityViolationException result = assertThrows(DataIntegrityViolationException.class, () -> internshipmanagerService.findById(VALID_ID));
        assertEquals("Erreur d'intégrité des données lors de la sauvegarde de l'offre d'emploi.", result.getMessage());
    }

    @Test
    public void findById_DataAccessError() {
        when(internshipmanagerRepository.findById(anyLong())).thenThrow(new DataAccessException("") {
        });

        DataAccessException result =  assertThrows(DataAccessException.class, () -> internshipmanagerService.findById(VALID_ID));
        assertEquals("Erreur d'accès aux données lors de la sauvegarde de l'offre d'emploi.", result.getMessage());
    }

    @Test
    public void findById_UnknownError() {
        when(internshipmanagerRepository.findById(anyLong())).thenThrow(new NullPointerException(""));

        RuntimeException result = assertThrows(RuntimeException.class, () -> internshipmanagerService.findById(VALID_ID));
        assertEquals("Erreur inconnue lors de la sauvegarde de l'offre d'emploi.", result.getMessage());
    }

    @Test
    public void getById_NotFound() {
        when(internshipmanagerRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        EmptyResultDataAccessException reslut = assertThrows(EmptyResultDataAccessException.class, () -> internshipmanagerService.getById(INVALID_ID));
        assertEquals(0, reslut.getActualSize());
    }

    @Test
    public void getById_DataIntegrityViolation() {
        when(internshipmanagerRepository.findById(anyLong())).thenThrow(new DataIntegrityViolationException(""));

        DataIntegrityViolationException result = assertThrows(DataIntegrityViolationException.class, () -> internshipmanagerService.getById(VALID_ID));
        assertEquals("Erreur d'intégrité des données lors de la sauvegarde de l'offre d'emploi.", result.getMessage());
    }

    @Test
    public void getById_DataAccessError() {
        when(internshipmanagerRepository.findById(anyLong())).thenThrow(new DataAccessException("") {
        });

        DataAccessException result =  assertThrows(DataAccessException.class, () -> internshipmanagerService.getById(VALID_ID));
        assertEquals("Erreur d'accès aux données lors de la sauvegarde de l'offre d'emploi.", result.getMessage());
    }

    @Test
    public void getById_UnknownError() {
        when(internshipmanagerRepository.findById(anyLong())).thenThrow(new NullPointerException(""));

        RuntimeException result = assertThrows(RuntimeException.class, () -> internshipmanagerService.getById(VALID_ID));
        assertEquals("Erreur inconnue lors de la sauvegarde de l'offre d'emploi.", result.getMessage());
    }


}
