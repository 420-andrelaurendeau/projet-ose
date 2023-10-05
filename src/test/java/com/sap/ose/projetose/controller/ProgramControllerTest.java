package com.sap.ose.projetose.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dto.ProgramDto;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import com.sap.ose.projetose.service.UserService;
import com.sap.ose.projetose.service.ProgrammeService;
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

@ContextConfiguration(classes = {ProgrammeController.class})
@ExtendWith(SpringExtension.class)
class ProgramControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private ProgrammeController programmeController;

    @MockBean
    private ProgrammeService programmeService;

    @MockBean
    private ProgrammeRepository programmeRepository;

    /**
     * Method under test: {@link ProgrammeController#saveProgramme(ProgramDto)}
     */
    @Test
    void testSaveProgramme() throws Exception {

        ProgramDto programDTO = new ProgramDto();
        programDTO.setDescription("The characteristics of someone or something");
        programDTO.setId(1);
        programDTO.setName("Nom");


        when(programmeService.saveProgramme(any())).thenReturn(Optional.of(programDTO));

        String content = (new ObjectMapper()).writeValueAsString(programDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/programme/ajouter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(programmeController).build().perform(requestBuilder);


        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Nom\",\"description\":\"The characteristics of someone or something\"}"));
    }

    /**
     * Method under test: {@link ProgrammeController#getProgrammes()}
     */
    @Test
    void testGetProgrammes() throws Exception {
        when(programmeController.getProgrammes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/programme/programmes");
        MockMvcBuilders.standaloneSetup(programmeController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ProgrammeController#getProgrammes()}
     */
    @Test
    void testGetProgrammes2() throws Exception {
        ArrayList<ProgramDto> programDTOList = new ArrayList<>();
        programDTOList.add(new ProgramDto(1, "Nom", "The characteristics of someone or something"));
        when(programmeController.getProgrammes()).thenReturn(programDTOList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/programme/programmes");
        MockMvcBuilders.standaloneSetup(programmeController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"name\":\"Nom\",\"description\":\"The characteristics of someone or something\"}]"));
    }
}

