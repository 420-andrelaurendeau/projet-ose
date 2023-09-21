package com.sap.ose.projetose.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dto.EmployeurDTO;
import com.sap.ose.projetose.service.OseService;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EmployeurController.class})
@ExtendWith(SpringExtension.class)
class EmployeurControllerTest {
    @Autowired
    private EmployeurController employeurController;

    @MockBean
    private OseService oseService;

    /**
     * Method under test: {@link EmployeurController#saveEmployeur(EmployeurDTO)}
     */
    @Test
    void testSaveEmployeur() throws Exception {
        Optional<EmployeurDTO> ofResult = Optional.of(
                new EmployeurDTO(1, "Nom", "Prenom", "jane.doe@example.org", "6625550144", "iloveyou", "Nom Entreprise", 1));
        when(oseService.saveEmployeur(Mockito.<EmployeurDTO>any())).thenReturn(ofResult);

        EmployeurDTO employeurDTO = new EmployeurDTO();
        employeurDTO.setEmail("jane.doe@example.org");
        employeurDTO.setId(1);
        employeurDTO.setNom("Nom");
        employeurDTO.setNomEntreprise("Nom Entreprise");
        employeurDTO.setPassword("iloveyou");
        employeurDTO.setPhone("6625550144");
        employeurDTO.setPrenom("Prenom");
        employeurDTO.setProgramme(1);
        String content = (new ObjectMapper()).writeValueAsString(employeurDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/employeur/ajouter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(employeurController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"email\":\"jane.doe@example.org\",\"phone\":\"6625550144\",\"password\""
                                        + ":\"iloveyou\",\"nomEntreprise\":\"Nom Entreprise\",\"programme\":1}"));
    }
}

