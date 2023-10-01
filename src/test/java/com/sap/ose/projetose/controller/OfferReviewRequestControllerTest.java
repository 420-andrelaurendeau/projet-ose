package com.sap.ose.projetose.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dto.OfferReviewRequestDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.service.OfferReviewRequestService;
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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {OfferReviewRequestController.class})
@ExtendWith(SpringExtension.class)
public class OfferReviewRequestControllerTest {

    @Autowired
    private OfferReviewRequestController offerReviewRequestController;

    @MockBean
    private OfferReviewRequestService offerReviewRequestService;

    private OfferReviewRequestDto offerReviewRequestDto = new OfferReviewRequestDto();

    @BeforeEach
    public void setUp() {
        this.offerReviewRequestDto.setId(1L);
        this.offerReviewRequestDto.setComment("Commentaire");
        this.offerReviewRequestDto.setInternOfferId(1L);
        this.offerReviewRequestDto.setInternshipmanagerId(1L);
    }

    @Test
    void testSaveOfferReviewRequest_OfferAlreadyApprovedException() throws Exception {
        when(offerReviewRequestService.saveOfferReviewRequest(any())).thenThrow(new OfferAlreadyReviewException());

        String content = (new ObjectMapper()).writeValueAsString(offerReviewRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/offerReviewRequest/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(offerReviewRequestController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isConflict())
                .andExpect(content().string(containsString("L'offre déjà approuvée.")));
    }

    @Test
    void testSaveOfferReviewRequest_OfferNotFoundException() throws Exception {
        when(offerReviewRequestService.saveOfferReviewRequest(any())).thenThrow(new OfferNotFoundException());

        String content = (new ObjectMapper()).writeValueAsString(offerReviewRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/offerReviewRequest/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(offerReviewRequestController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Offre d'emploi non trouvée.")));
    }

    @Test
    void testSaveOfferReviewRequest_InternshipmanagerNotFoundException() throws Exception {
        when(offerReviewRequestService.saveOfferReviewRequest(any())).thenThrow(new InternshipmanagerNotFoundException());

        String content = (new ObjectMapper()).writeValueAsString(offerReviewRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/offerReviewRequest/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(offerReviewRequestController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Gestionnaire de stage non trouvé.")));
    }
    @Test
    void testSaveOfferReviewRequest_DatabaseException() throws Exception {
        when(offerReviewRequestService.saveOfferReviewRequest(any())).thenThrow(new DatabaseException());

        String content = (new ObjectMapper()).writeValueAsString(offerReviewRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/offerReviewRequest/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(offerReviewRequestController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Erreur d'accès a la base de données")));
    }

    @Test
    void testSaveOfferReviewRequest_ServiceException() throws Exception {
        when(offerReviewRequestService.saveOfferReviewRequest(any())).thenThrow(new ServiceException());

        String content = (new ObjectMapper()).writeValueAsString(offerReviewRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/offerReviewRequest/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(offerReviewRequestController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Erreur au niveau du service")));
    }

}
