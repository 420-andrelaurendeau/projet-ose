package com.sap.ose.projetose.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.service.OseService;
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

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ProgrammeController.class})
@ExtendWith(SpringExtension.class)
class ProgrammeControllerTest {
    @MockBean
    private OseService oseService;

    @Autowired
    private ProgrammeController programmeController;

    /**
     * Method under test: {@link ProgrammeController#saveProgramme(ProgrammeDto)}
     */
    @Test
    void testSaveProgramme() throws Exception {
        Optional<ProgrammeDto> ofResult = Optional.of(new ProgrammeDto(1, "Nom", "The characteristics of someone or something"));
        when(oseService.saveProgramme(Mockito.any())).thenReturn(ofResult);

        ProgrammeDto programmeDTO = new ProgrammeDto();
        programmeDTO.setDescription("The characteristics of someone or something");
        programmeDTO.setId(1);
        programmeDTO.setNom("Nom");
        String content = (new ObjectMapper()).writeValueAsString(programmeDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/programme/ajouter").contentType(MediaType.APPLICATION_JSON).content(content);
        MockMvcBuilders.standaloneSetup(programmeController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"nom\":\"Nom\",\"description\":\"The characteristics of someone or something\"}"));
    }

    /**
     * Method under test: {@link ProgrammeController#getProgrammes()}
     */
    @Test
    void testGetProgrammes() throws Exception {
        when(oseService.getProgrammes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/programme/programmes");
        MockMvcBuilders.standaloneSetup(programmeController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ProgrammeController#getProgrammes()}
     */
    @Test
    void testGetProgrammes2() throws Exception {
        ArrayList<ProgrammeDto> programmeDTOList = new ArrayList<>();
        programmeDTOList.add(new ProgrammeDto(1, "Nom", "The characteristics of someone or something"));
        when(oseService.getProgrammes()).thenReturn(programmeDTOList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/programme/programmes");
        MockMvcBuilders.standaloneSetup(programmeController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"nom\":\"Nom\",\"description\":\"The characteristics of someone or something\"}]"));
    }
}

