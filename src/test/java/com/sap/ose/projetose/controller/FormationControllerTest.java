package com.sap.ose.projetose.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dto.FormationDto;
import com.sap.ose.projetose.repository.FormationRepository;
import com.sap.ose.projetose.service.OseService;
import com.sap.ose.projetose.service.FormationService;
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

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {FormationController.class})
@ExtendWith(SpringExtension.class)
class FormationControllerTest {
    @Autowired
    private FormationController formationController;

    @MockBean
    private FormationService formationService;

    @MockBean
    private FormationRepository formationRepository;

    /**
     * Method under test: {@link FormationController#saveProgramme(FormationDto)}
     */
    @Test
    void testSaveProgramme() throws Exception {

        FormationDto formationDTO = new FormationDto();
        formationDTO.setDescription("The characteristics of someone or something");
        formationDTO.setId(1);
        formationDTO.setNom("Nom");


        when(formationService.saveProgramme(any())).thenReturn(Optional.of(formationDTO));

        String content = (new ObjectMapper()).writeValueAsString(formationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/programme/ajouter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(formationController).build().perform(requestBuilder);


        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"nom\":\"Nom\",\"description\":\"The characteristics of someone or something\"}"));
    }

    /**
     * Method under test: {@link FormationController#getProgrammes()}
     */
    @Test
    void testGetProgrammes() throws Exception {
        when(formationController.getProgrammes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/programme/programmes");
        MockMvcBuilders.standaloneSetup(formationController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FormationController#getProgrammes()}
     */
    @Test
    void testGetProgrammes2() throws Exception {
        ArrayList<FormationDto> formationDTOList = new ArrayList<>();
        formationDTOList.add(new FormationDto(1, "Nom", "The characteristics of someone or something"));
        when(formationController.getProgrammes()).thenReturn(formationDTOList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/programme/programmes");
        MockMvcBuilders.standaloneSetup(formationController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"nom\":\"Nom\",\"description\":\"The characteristics of someone or something\"}]"));
    }
}

