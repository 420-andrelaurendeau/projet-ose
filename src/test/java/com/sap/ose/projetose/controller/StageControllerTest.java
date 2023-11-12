package com.sap.ose.projetose.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.exception.GlobalExceptionHandler;
import com.sap.ose.projetose.service.EtudiantService;
import com.sap.ose.projetose.service.StageService;
import org.apache.catalina.User;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.apache.catalina.users.MemoryUserDatabase;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {StageController.class})
@ExtendWith(SpringExtension.class)
public class StageControllerTest {

    @Autowired
    private StageController stageController;

    @MockBean
    private StageService stageService;


    @Test
    void testSaveEmployerOpinion() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/stage/saveEmployerOpinion")
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder contentResult = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString(1L));
        MockHttpServletRequestBuilder requestBuilder = contentResult
                .content((new ObjectMapper()).writeValueAsString("foo"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(stageController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }


}


