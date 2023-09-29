package com.sap.ose.projetose.controller;


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

@ContextConfiguration(classes = {InternOfferController.class})
@ExtendWith(SpringExtension.class)
class InternOfferControllerTest {
    @Autowired
    private InternOfferController internOfferController;

    @MockBean
    private InternOfferService internOfferService;

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
    void testSaveInterOfferJob_NullPointerException() throws Exception {
        when(internOfferService.saveInterOfferJob(any())).thenThrow(new NullPointerException("Programme non trouvé"));

        String content = (new ObjectMapper()).writeValueAsString(internOfferDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/interOfferJob/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(internOfferController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Programme non trouvé")));
    }

    @Test
    void testSaveInterOfferJob_DataIntegrityViolationException() throws Exception {
        when(internOfferService.saveInterOfferJob(any())).thenThrow(new DataIntegrityViolationException("Erreur d'intégrité des données lors de la sauvegarde de l'offre d'emploi."));


        String content = (new ObjectMapper()).writeValueAsString(internOfferDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/interOfferJob/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(internOfferController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);
        ;

        resultActions.andExpect(status().isBadRequest())
                .andExpect(content().string("Erreur d'intégrité des données lors de la sauvegarde de l'offre d'emploi."));
    }

    @Test
    void testSaveInterOfferJob_DataAccessException() throws Exception {
        when(internOfferService.saveInterOfferJob(any())).thenThrow(new DataAccessException("Erreur d'accès aux données") {
        });


        String content = (new ObjectMapper()).writeValueAsString(internOfferDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/interOfferJob/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(internOfferController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isInternalServerError())
                .andExpect(content().string("Erreur d'accès aux données"));
    }

    @Test
    void testSaveInterOfferJob_RuntimeException() throws Exception {
        when(internOfferService.saveInterOfferJob(any())).thenThrow(new RuntimeException("Erreur inconnue"));


        String content = (new ObjectMapper()).writeValueAsString(internOfferDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/interOfferJob/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(internOfferController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isInternalServerError())
                .andExpect(content().string("Erreur inconnue"));
    }

}

