package com.sap.ose.projetose.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.GlobalExceptionHandler;
import com.sap.ose.projetose.exception.ProgramNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.service.InternshipOfferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
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

@ContextConfiguration(classes = {InternshipOfferController.class})
@ExtendWith(SpringExtension.class)
class InternshipOfferControllerTest {
    @Autowired
    private InternshipOfferController internOfferController;

    @MockBean
    private InternshipOfferService internshipOfferService;

    private final InternshipOfferDto internshipOfferDto = new InternshipOfferDto();

    @BeforeEach
    public void setUp() {
        this.internshipOfferDto.setDescription("The characteristics of someone or something");
        this.internshipOfferDto.setEmployeurEntreprise("Employer Entreprise");
        this.internshipOfferDto.setEmployeurId(1L);
        this.internshipOfferDto.setEmployeurNom("Employer Nom");
        this.internshipOfferDto.setEmployeurPrenom("Employer Prenom");
        this.internshipOfferDto.setEndDate("2020-03-01");
        this.internshipOfferDto.setFile(new FileDto());
        this.internshipOfferDto.setId(1L);
        this.internshipOfferDto.setInternshipCandidates(new ArrayList<>());
        this.internshipOfferDto.setLocation("Location");
        this.internshipOfferDto.setProgrammeId(1);
        this.internshipOfferDto.setProgrammeNom("Formation Nom");
        this.internshipOfferDto.setSalaryByHour(10.0d);
        this.internshipOfferDto.setStartDate("2020-03-01");
        this.internshipOfferDto.setTitle("Dr");
    }

    @Test
    void testSaveInterOfferJob_ProgramNotFoundException() throws Exception {
        when(internshipOfferService.saveInterOfferJob(any())).thenThrow(new ProgramNotFoundException());

        String content = (new ObjectMapper()).writeValueAsString(internshipOfferDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/interOfferJob/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(internOfferController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Formation non trouvé")));
    }
    @Test
    void testSaveInterOfferJob_DatabaseException() throws Exception {
        when(internshipOfferService.saveInterOfferJob(any())).thenThrow(new DatabaseException());


        String content = (new ObjectMapper()).writeValueAsString(internshipOfferDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/interOfferJob/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(internOfferController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isInternalServerError())
                .andExpect(content().string("Erreur d'accès a la base de données"));
    }

    @Test
    void testSaveInterOfferJob_ServiceException() throws Exception {
        when(internshipOfferService.saveInterOfferJob(any())).thenThrow(new ServiceException());


        String content = (new ObjectMapper()).writeValueAsString(internshipOfferDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/interOfferJob/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(internOfferController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isInternalServerError())
                .andExpect(content().string("Erreur au niveau du service"));
    }

}

