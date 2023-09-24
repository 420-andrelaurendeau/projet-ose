package com.sap.ose.projetose.service;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.controller.InternOfferController;
import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.InternshipCandidatesDto;
import com.sap.ose.projetose.exception.GlobalExceptionHandler;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.InternshipCandidates;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.InternOfferRepository;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.exception.GlobalExceptionHandler;
import com.sap.ose.projetose.service.InternOfferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {InternOfferService.class})
@ExtendWith(SpringExtension.class)
class InternOfferServiceTest {
    @MockBean
    private EmployeurRepository employeurRepository;

    @MockBean
    private InternOfferRepository internOfferRepository;

    @Autowired
    private InternOfferService internOfferService;

    @MockBean
    private ProgrammeService programmeService;


    private InternOfferDto internOfferDto = new InternOfferDto();

    @BeforeEach
    public void setUp() {
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
        when(programmeService.getProgrammeById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
    }

    @Test
    public void saveInterOfferJob_EmployeurNotFound() {
        when(programmeService.getProgrammeById(anyInt())).thenReturn(Optional.of(new Programme()));
        when(employeurRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
    }

    @Test
    public void saveInterOfferJob_DataIntegrityError() {
        when(programmeService.getProgrammeById(anyInt())).thenReturn(Optional.of(new Programme()));
        when(employeurRepository.findById(anyLong())).thenReturn(Optional.of(new Employeur()));

        when(internOfferRepository.save(any())).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
    }

    @Test
    public void saveInterOfferJob_DataAccessError() {
        when(programmeService.getProgrammeById(anyInt())).thenReturn(Optional.of(new Programme()));
        when(employeurRepository.findById(anyLong())).thenReturn(Optional.of(new Employeur()));

        when(internOfferRepository.save(any())).thenThrow(EmptyResultDataAccessException.class);


        assertThrows(DataAccessException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
    }

    @Test
    public void saveInterOfferJob_UnknownError() {
        when(programmeService.getProgrammeById(anyInt())).thenReturn(Optional.of(new Programme()));
        when(employeurRepository.findById(anyLong())).thenReturn(Optional.of(new Employeur()));

        when(internOfferRepository.save(any())).thenThrow(IllegalArgumentException.class);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> internOfferService.saveInterOfferJob(internOfferDto));
    }
}

