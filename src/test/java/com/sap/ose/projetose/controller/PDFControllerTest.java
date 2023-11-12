package com.sap.ose.projetose.controller;

import static org.mockito.Mockito.when;

import com.sap.ose.projetose.dto.PDFDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.GlobalExceptionHandler;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.service.PDFService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PDFController.class})
@ExtendWith(SpringExtension.class)
class PDFControllerTest {
    @Autowired
    private PDFController pDFController;

    @MockBean
    private PDFService pDFService;

    @Test
    void getCurrentPDF_ReturnsPDFDto_WhenInvoked() throws Exception {
        when(pDFService.getCurrentPDF()).thenReturn(new PDFDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/pdf/getCurrentPDF");
        MockMvcBuilders.standaloneSetup(pDFController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"createdDate\":null,\"isActive\":null,\"fileId\":0,\"fileName\":null,\"content\":null}"));
    }

    @Test
    void getCurrentPDF_ReturnsErrorResponse_OnServiceException() throws Exception {
        when(pDFService.getCurrentPDF()).thenThrow(new ServiceException("Service error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/pdf/getCurrentPDF");
        MockMvcBuilders.standaloneSetup(pDFController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Service error occurred"));
    }

    @Test
    void getCurrentPDF_ReturnsErrorResponse_OnDatabaseException() throws Exception {
        when(pDFService.getCurrentPDF()).thenThrow(new DatabaseException("Database error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/pdf/getCurrentPDF");
        MockMvcBuilders.standaloneSetup(pDFController)
                .setControllerAdvice(new GlobalExceptionHandler()) // Remplacez par votre gestionnaire d'exceptions
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isInternalServerError()) // Ou le statut que vous renvoyez
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Database error occurred")); // En supposant que votre gestionnaire définit un message
    }




    @Test
    void getCurrentPDF2_ReturnsNotFoundStatus_WhenFormLoginRequested() throws Exception {
        when(pDFService.getCurrentPDF()).thenReturn(new PDFDto());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(pDFController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void savePDF_ReturnsSuccessAndEmptyPDFDto_OnPostRequest() throws Exception {
        when(pDFService.save(Mockito.<PDFDto>any())).thenReturn(new PDFDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/pdf/save");
        MockMvcBuilders.standaloneSetup(pDFController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"createdDate\":null,\"isActive\":null,\"fileId\":0,\"fileName\":null,\"content\":null}"));
    }

    @Test
    void savePDF_ReturnsErrorResponse_OnServiceException() throws Exception {
        when(pDFService.save(Mockito.<PDFDto>any())).thenThrow(new ServiceException("Service error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/pdf/save");
        MockMvcBuilders.standaloneSetup(pDFController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Service error occurred"));
    }

    @Test
    void savePDF_ReturnsErrorResponse_OnDatabaseException() throws Exception {
        when(pDFService.save(Mockito.<PDFDto>any())).thenThrow(new DatabaseException("Database error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/pdf/save");
        MockMvcBuilders.standaloneSetup(pDFController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Database error occurred"));
    }


}
