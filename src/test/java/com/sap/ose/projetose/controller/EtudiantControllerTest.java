package com.sap.ose.projetose.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.service.EtudiantService;
import com.sap.ose.projetose.service.OseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {EtudiantController.class})
@ExtendWith(SpringExtension.class)
class EtudiantControllerTest {
    @Autowired
    private EtudiantController etudiantController;
    @MockBean
    private EtudiantService oseService;

    /**
     * Method under test: {@link EtudiantController#getEtudiant(Long)}
     */
    @Test
    void testGetEtudiant() throws Exception {
        when(oseService.getEtudiantById(Mockito.<Long>any())).thenReturn(new EtudiantDto("Matricule", 1, "Cv", null));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/etudiant/{id}", 1L);
        MockMvcBuilders.standaloneSetup(etudiantController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("{\"nom\":null,\"prenom\":null,\"phone\":null,\"email\":null,\"matricule\":\"Matricule\",\"programme_id\":1," + "\"cv\":\"Cv\",\"internships_id\":null}"));
    }

    /**
     * Method under test: {@link EtudiantController#saveEtudiant(Etudiant)}
     */
    @Test
    void testSaveEtudiant() throws Exception {
        Etudiant etudiant = new Etudiant();
        etudiant.setCv("Cv");
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1);
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme(new Programme());
        Optional<Etudiant> ofResult = Optional.of(etudiant);
        when(oseService.saveEtudiant(Mockito.any())).thenReturn(ofResult);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv("Cv");
        etudiant2.setEmail("jane.doe@example.org");
        etudiant2.setId(1);
        etudiant2.setMatricule("Matricule");
        etudiant2.setNom("Nom");
        etudiant2.setPassword("iloveyou");
        etudiant2.setPhone("6625550144");
        etudiant2.setPrenom("Prenom");
        etudiant2.setProgramme(new Programme());
        String content = (new ObjectMapper()).writeValueAsString(etudiant2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/etudiant/ajouter").contentType(MediaType.APPLICATION_JSON).content(content);
        MockMvcBuilders.standaloneSetup(etudiantController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"phone\":\"6625550144\",\"email\":\"jane.doe@example.org\",\"password\"" + ":\"iloveyou\",\"matricule\":\"Matricule\",\"programme\":{\"id\":0,\"nom\":null,\"description\":null},\"cv\":\"Cv\",\"internships\":null}"));
    }

    /**
     * Method under test: {@link EtudiantController#getEtudiant(Long)}
     */
    @Test
    void testGetEtudiant2() throws Exception {
        when(oseService.getEtudiantById(Mockito.<Long>any())).thenReturn(new EtudiantDto("Matricule", 1L, "Cv", null));
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(etudiantController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link EtudiantController#getEtudiants()}
     */
    @Test
    void testGetEtudiants() throws Exception {
        when(oseService.getEtudiants()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/etudiant/etudiants");
        MockMvcBuilders.standaloneSetup(etudiantController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}


