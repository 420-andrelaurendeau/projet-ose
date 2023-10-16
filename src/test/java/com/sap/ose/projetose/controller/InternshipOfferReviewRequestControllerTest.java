package com.sap.ose.projetose.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dto.OfferReviewRequestDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.service.InternshipOfferReviewRequestService;
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

@ContextConfiguration(classes = {InternshipOfferReviewRequestController.class})
@ExtendWith(SpringExtension.class)
public class InternshipOfferReviewRequestControllerTest {

    private final OfferReviewRequestDto offerReviewRequestDto = new OfferReviewRequestDto();
    @Autowired
    private InternshipOfferReviewRequestController internshipOfferReviewRequestController;
    @MockBean
    private InternshipOfferReviewRequestService internshipOfferReviewRequestService;

    @BeforeEach
    public void setUp() {
        this.offerReviewRequestDto.setId(1L);
        this.offerReviewRequestDto.setComment("Commentaire");
        this.offerReviewRequestDto.setInternshipOfferId(1L);
        this.offerReviewRequestDto.setInternshipManagerId(1L);
    }

    @Test
    void testSaveOfferReviewRequest_OfferAlreadyApprovedException() throws Exception {
        when(internshipOfferReviewRequestService.saveOfferReviewRequest(any())).thenThrow(new OfferAlreadyReviewedException());

        String content = (new ObjectMapper()).writeValueAsString(offerReviewRequestDto);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                                                        .post("/api/offerReview/update")
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(content);

        ResultActions resultActions = MockMvcBuilders
                                        .standaloneSetup(internshipOfferReviewRequestController)
                                        .setControllerAdvice(new GlobalExceptionHandler()).build()
                                        .perform(requestBuilder);

        resultActions
                .andExpect(status().isConflict())
                .andExpect(content().string(containsString("L'offre a déjà été revue")));
    }

    @Test
    void testSaveOfferReviewRequest_OfferNotFoundException() throws Exception {
        when(internshipOfferReviewRequestService.saveOfferReviewRequest(any())).thenThrow(new OfferNotFoundException());

        String content = (new ObjectMapper()).writeValueAsString(offerReviewRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                                                        .post("/api/offerReview/update")
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(content);

        ResultActions resultActions = MockMvcBuilders
                                        .standaloneSetup(internshipOfferReviewRequestController)
                                        .setControllerAdvice(new GlobalExceptionHandler()).build()
                                        .perform(requestBuilder);

        resultActions
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Offre d'emploi non trouvée.")));
    }

    @Test
    void testSaveOfferReviewRequest_InternshipmanagerNotFoundException() throws Exception {
        when(internshipOfferReviewRequestService.saveOfferReviewRequest(any())).thenThrow(new InternshipManagerNotFoundException());

        String content = (new ObjectMapper()).writeValueAsString(offerReviewRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/offerReview/update").contentType(MediaType.APPLICATION_JSON).content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(internshipOfferReviewRequestController).setControllerAdvice(new GlobalExceptionHandler()).build().perform(requestBuilder);

        resultActions.andExpect(status().isNotFound()).andExpect(content().string(containsString("Gestionnaire de stage non trouvé.")));
    }

    @Test
    void testSaveOfferReviewRequest_DatabaseException() throws Exception {
        when(internshipOfferReviewRequestService.saveOfferReviewRequest(any())).thenThrow(new DatabaseException());

        String content = (new ObjectMapper()).writeValueAsString(offerReviewRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/offerReview/update").contentType(MediaType.APPLICATION_JSON).content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(internshipOfferReviewRequestController).setControllerAdvice(new GlobalExceptionHandler()).build().perform(requestBuilder);

        resultActions.andExpect(status().isInternalServerError()).andExpect(content().string(containsString("Erreur d'accès a la base de données")));
    }

    @Test
    void testSaveOfferReviewRequest_ServiceException() throws Exception {
        when(internshipOfferReviewRequestService.saveOfferReviewRequest(any())).thenThrow(new ServiceException());

        String content = (new ObjectMapper()).writeValueAsString(offerReviewRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/offerReview/update").contentType(MediaType.APPLICATION_JSON).content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(internshipOfferReviewRequestController).setControllerAdvice(new GlobalExceptionHandler()).build().perform(requestBuilder);

        resultActions.andExpect(status().isInternalServerError()).andExpect(content().string(containsString("Erreur au niveau du service")));
    }

}
