package com.sap.ose.projetose.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dto.EtudiantDTO;
import com.sap.ose.projetose.service.OseService;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
     * Method under test: {@link EtudiantController#saveEtudiant(EtudiantDTO)}
     */
    @Test
    void testSaveEtudiant() throws Exception {
        Optional<EtudiantDTO> ofResult = Optional
                .of(new EtudiantDTO(1, "Nom", "Prenom", "jane.doe@example.org", "6625550144", "iloveyou", 1, 1, "Cv"));
        when(oseService.saveEtudiant(Mockito.<EtudiantDTO>any())).thenReturn(ofResult);

        EtudiantDTO etudiantDTO = new EtudiantDTO();
        etudiantDTO.setCv("Cv");
        etudiantDTO.setEmail("jane.doe@example.org");
        etudiantDTO.setId(1);
        etudiantDTO.setMatricule(1);
        etudiantDTO.setNom("Nom");
        etudiantDTO.setPassword("iloveyou");
        etudiantDTO.setPhone("6625550144");
        etudiantDTO.setPrenom("Prenom");
        etudiantDTO.setProgramme(1);
        String content = (new ObjectMapper()).writeValueAsString(etudiantDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/etudiant/ajouter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(etudiantController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"email\":\"jane.doe@example.org\",\"phone\":\"6625550144\",\"password\""
                                        + ":\"iloveyou\",\"matricule\":1,\"programme\":1,\"cv\":\"Cv\"}"));
    }

    /**
     * Method under test: {@link EtudiantController#getEtudiant(int)}
     */
    @Test
    void testGetEtudiant() throws Exception {
        when(oseService.getEtudiantById(anyInt()))
                .thenReturn(new EtudiantDTO(1, "Nom", "Prenom", "jane.doe@example.org", "6625550144", "iloveyou", 1, 1, "Cv"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/etudiant/{id}", 1);
        MockMvcBuilders.standaloneSetup(etudiantController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"email\":\"jane.doe@example.org\",\"phone\":\"6625550144\",\"password\""
                                        + ":\"iloveyou\",\"matricule\":1,\"programme\":1,\"cv\":\"Cv\"}"));
    }




    /**
     * Method under test: {@link EtudiantController#getEtudiants()}
     */
    @Test
    void testGetEtudiants() throws Exception {

        when(oseService.getEtudiants()).thenReturn(new ArrayList<>());

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

                .andExpect(MockMvcResultMatchers.content().string("[]"));

                .andExpect(MockMvcResultMatchers.content().string("[{\"nom\":\"Jean\",\"prenom\":\"Dupont\",\"phone\":\"4387996589\",\"email\":\"dupont@gmail.com\",\"matricule\":\"2045878\",\"programme\":\"Informatique\",\"cv\":\"\"}]"));

    }

    /**
     * Method under test: {@link EtudiantController#getEtudiants()}
     */
    @Test
    void testGetEtudiants2() throws Exception {

        ArrayList<EtudiantDTO> etudiantDTOList = new ArrayList<>();
        etudiantDTOList
                .add(new EtudiantDTO(1, "Nom", "Prenom", "jane.doe@example.org", "6625550144", "iloveyou", 1, 1, "Cv"));
        when(oseService.getEtudiants()).thenReturn(etudiantDTOList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/etudiant/etudiants");
        MockMvcBuilders.standaloneSetup(etudiantController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"email\":\"jane.doe@example.org\",\"phone\":\"6625550144\",\"password"
                                        + "\":\"iloveyou\",\"matricule\":1,\"programme\":1,\"cv\":\"Cv\"}]"));

        when(oseService.getAllEtudiants()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(etudiantController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());

    }
}

