package com.sap.ose.projetose.controller;

import static org.mockito.Mockito.when;

import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.service.OseService;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

@ContextConfiguration(classes = {EtudiantController.class})
@ExtendWith(SpringExtension.class)
class EtudiantControllerTest {
    @Autowired
    private EtudiantController etudiantController;

    @MockBean
    private OseService oseService;



    /**
     * Method under test: {@link EtudiantController#getEtudiants()}
     */
    @Test
    void testGetEtudiants() throws Exception {
        EtudiantDto etudiantDto =
                new EtudiantDto("Jean", "Dupont", "4387996589", "dupont@gmail.com", "2045878", "Informatique", "");
        List<EtudiantDto> etudiantDtoList = new ArrayList<>();
        etudiantDtoList.add(etudiantDto);
        when(oseService.getAllEtudiants()).thenReturn(etudiantDtoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/etudiant/etudiants");
        MockMvcBuilders.standaloneSetup(etudiantController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[{\"nom\":\"Jean\",\"prenom\":\"Dupont\",\"phone\":\"4387996589\",\"email\":\"dupont@gmail.com\",\"matricule\":\"2045878\",\"programme\":\"Informatique\",\"cv\":\"\"}]"));
    }

    /**
     * Method under test: {@link EtudiantController#getEtudiants()}
     */
    @Test
    void testGetEtudiants2() throws Exception {
        when(oseService.getAllEtudiants()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(etudiantController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

