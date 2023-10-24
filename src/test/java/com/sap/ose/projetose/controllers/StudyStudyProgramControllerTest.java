package com.sap.ose.projetose.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dtos.StudyProgramDto;
import com.sap.ose.projetose.repositories.StudyProgramRepository;
import com.sap.ose.projetose.services.StudyProgramService;
import com.sap.ose.projetose.services.UserService;
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

@ContextConfiguration(classes = {StudyProgramController.class})
@ExtendWith(SpringExtension.class)
class StudyStudyProgramControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private StudyProgramController studyProgramController;

    @MockBean
    private StudyProgramService studyProgramService;

    @MockBean
    private StudyProgramRepository studyProgramRepository;

    /**
     * Method under test: {@link StudyProgramController#createStudyProgram(StudyProgramDto)}
     */
    @Test
    void testSaveProgramme() throws Exception {

        StudyProgramDto studyProgramDTO = new StudyProgramDto();
        studyProgramDTO.setDescription("The characteristics of someone or something");
        studyProgramDTO.setId(1);
        studyProgramDTO.setName("Nom");


        when(studyProgramService.saveStudyProgram(any())).thenReturn(Optional.of(studyProgramDTO));

        String content = (new ObjectMapper()).writeValueAsString(studyProgramDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/programme/ajouter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(studyProgramController).build().perform(requestBuilder);


        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Nom\",\"description\":\"The characteristics of someone or something\"}"));
    }

    /**
     * Method under test: {@link StudyProgramController#getStudyPrograms()}
     */
    @Test
    void testGetProgrammes() throws Exception {
        when(studyProgramController.getStudyPrograms()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/programme/all");
        MockMvcBuilders.standaloneSetup(studyProgramController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link StudyProgramController#getStudyPrograms()}
     */
    @Test
    void testGetProgrammes2() throws Exception {
        ArrayList<StudyProgramDto> studyProgramDTOList = new ArrayList<>();
        studyProgramDTOList.add(new StudyProgramDto(1, "Nom", "The characteristics of someone or something"));
        when(studyProgramController.getStudyPrograms()).thenReturn(studyProgramDTOList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/programme/all");
        MockMvcBuilders.standaloneSetup(studyProgramController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"name\":\"Nom\",\"description\":\"The characteristics of someone or something\"}]"));
    }
}

