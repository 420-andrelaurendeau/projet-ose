package com.sap.ose.projetose.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dtos.FileTransferDto;
import com.sap.ose.projetose.dtos.InternshipOfferDto;
import com.sap.ose.projetose.exceptions.DatabaseException;
import com.sap.ose.projetose.exceptions.GlobalExceptionHandler;
import com.sap.ose.projetose.exceptions.ProgramNotFoundException;
import com.sap.ose.projetose.exceptions.ServiceException;
import com.sap.ose.projetose.services.InternshipOfferService;
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
    private InternshipOfferController internshipOfferController;

    @MockBean
    private InternshipOfferService internshipOfferService;

    private InternshipOfferDto internshipOfferDto = new InternshipOfferDto();

    @BeforeEach
    public void setUp() {
        this.internshipOfferDto.setDescription("The characteristics of someone or something");
        this.internshipOfferDto.setEmployerEnterprise("Employeur Entreprise");
        this.internshipOfferDto.setEmployerId(1L);
        this.internshipOfferDto.setEmployerLastName("Employeur Nom");
        this.internshipOfferDto.setEmployerFirstName("Employeur Prenom");
        this.internshipOfferDto.setEndDate("2020-03-01");
        this.internshipOfferDto.setFile(new FileTransferDto());
        this.internshipOfferDto.setId(1L);
        this.internshipOfferDto.setInternshipApplicationIds(new ArrayList<>());
        this.internshipOfferDto.setLocation("Location");
        this.internshipOfferDto.setProgramId(1);
        this.internshipOfferDto.setProgramName("Programme Nom");
        this.internshipOfferDto.setSalaryByHour(10.0d);
        this.internshipOfferDto.setStartDate("2020-03-01");
        this.internshipOfferDto.setTitle("Dr");
    }

    @Test
    void testSaveInterOfferJob_ProgramNotFoundException() throws Exception {
        when(internshipOfferService.createOrUpdateInternshipOffer(any())).thenThrow(new ProgramNotFoundException());

        String content = (new ObjectMapper()).writeValueAsString(internshipOfferDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/internshipOffer/newOffer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(internshipOfferController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Programme non trouvé")));
    }
    @Test
    void testSaveInterOfferJob_DatabaseException() throws Exception {
        when(internshipOfferService.createOrUpdateInternshipOffer(any())).thenThrow(new DatabaseException());


        String content = (new ObjectMapper()).writeValueAsString(internshipOfferDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/internshipOffer/newOffer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(internshipOfferController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isInternalServerError())
                .andExpect(content().string("Erreur d'accès a la base de données"));
    }

    @Test
    void testSaveInterOfferJob_ServiceException() throws Exception {
        when(internshipOfferService.createOrUpdateInternshipOffer(any())).thenThrow(new ServiceException());


        String content = (new ObjectMapper()).writeValueAsString(internshipOfferDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/internshipOffer/newOffer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(internshipOfferController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isInternalServerError())
                .andExpect(content().string("Erreur au niveau du service"));
    }

}

