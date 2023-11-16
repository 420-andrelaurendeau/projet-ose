package com.sap.ose.projetose.controller;

import static org.mockito.Mockito.when;

import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.service.EmployeurService;
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

@ContextConfiguration(classes = {EmployeurController.class})
@ExtendWith(SpringExtension.class)
class EmployeurControllerDiffblueTest {
    @Autowired
    private EmployeurController employeurController;

    @MockBean
    private EmployeurService employeurService;

    /**
     * Method under test: {@link EmployeurController#getEmployeur(Long)}
     */
    @Test
    void testGetEmployeur() throws Exception {
        when(employeurService.getEmployeurById(Mockito.<Long>any())).thenReturn(new EmployeurDto("Entreprise", 1L));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employeur/{id}", 1L);
        MockMvcBuilders.standaloneSetup(employeurController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"nom\":null,\"prenom\":null,\"phone\":null,\"email\":null,\"entreprise\":\"Entreprise\",\"programme"
                                        + "_id\":1}"));
    }

    /**
     * Method under test: {@link EmployeurController#getEmployeur(Long)}
     */
    @Test
    void testGetEmployeur2() throws Exception {
        when(employeurService.getEmployeurById(Mockito.<Long>any())).thenReturn(new EmployeurDto("Entreprise", 1L));
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeurController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
