package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.service.InternOfferService;
import com.sap.ose.projetose.service.InternshipmanagerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Page.empty;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {IntershipManagerController.class})
@ExtendWith(SpringExtension.class)
public class IntershipManagerControllerTest {

    @Autowired
    private IntershipManagerController intershipManagerController;

    @MockBean
    private InternshipmanagerService internshipmanagerService;

    @MockBean
    private InternOfferController internOfferController;

    @MockBean
    private InternOfferService internOfferService;


    @Test
    void getOffersApplied_InvalidStateException() throws Exception {

        when(internshipmanagerService.getSortedOffersByPage(0, 10, null, "id", "desc")).thenThrow(new InvalidStateException(""));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/internshipManager/offers").contentType(MediaType.APPLICATION_JSON);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(intershipManagerController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build().perform(requestBuilder);

        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Cette état n'est pas valide : "));

    }

    @Test
    void getOffersApplied_BadSortingFieldException() throws Exception {

        when(internshipmanagerService.getSortedOffersByPage(0, 10, null, "id", "desc")).thenThrow(new BadSortingFieldException(""));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/internshipManager/offers").contentType(MediaType.APPLICATION_JSON);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(intershipManagerController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build().perform(requestBuilder);

        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Le champ  n'existe pas"));

    }

    @Test
    void getOffersApplied_DatabaseException() throws Exception {

        when(internshipmanagerService.getSortedOffersByPage(0, 10, null, "id", "desc")).thenThrow(new DatabaseException());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/internshipManager/offers").contentType(MediaType.APPLICATION_JSON);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(intershipManagerController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build().perform(requestBuilder);

        resultActions.andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Erreur d'accès a la base de données"));

    }

    @Test
    void getOffersApplied_ServiceException() throws Exception {

        when(internshipmanagerService.getSortedOffersByPage(0, 10, null, "id", "desc")).thenThrow(new ServiceException());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/internshipManager/offers").contentType(MediaType.APPLICATION_JSON);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(intershipManagerController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build().perform(requestBuilder);

        resultActions.andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("Erreur au niveau du service"));

    }


    @Test
    void countInterOfferByState_DatabaseException() throws Exception {
        when(internOfferService.getCountByState()).thenThrow(new DatabaseException());


        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/internshipManager/count").contentType(MediaType.APPLICATION_JSON);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(intershipManagerController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isInternalServerError())
                .andExpect(content().string("Erreur d'accès a la base de données"));
    }

    @Test
    void countInterOfferByState_ServiceException() throws Exception {
        when(internOfferService.getCountByState()).thenThrow(new ServiceException());


        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/internshipManager/count").contentType(MediaType.APPLICATION_JSON);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(intershipManagerController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isInternalServerError())
                .andExpect(content().string("Erreur au niveau du service"));
    }

}
