package com.sap.ose.projetose.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dto.EtudiantDtoWithId;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.InternshipCandidatesDto;
import com.sap.ose.projetose.modeles.State;
import com.sap.ose.projetose.service.InternshipCandidatesService;

import java.util.ArrayList;

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

@ContextConfiguration(classes = {InternshipCandidatesController.class})
@ExtendWith(SpringExtension.class)
class InternshipCandidatesControllerTest {
    @Autowired
    private InternshipCandidatesController internshipCandidatesController;

    @MockBean
    private InternshipCandidatesService internshipCandidatesService;

    /**
     * Method under test: {@link InternshipCandidatesController#getPendingCandidates()}
     */
    @Test
    void testGetPendingCandidates() throws Exception {
        when(internshipCandidatesService.getPendingCandidates()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/intershipCandidates/getPendingCandidates");
        MockMvcBuilders.standaloneSetup(internshipCandidatesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link InternshipCandidatesController#getPendingCandidates()}
     */
    @Test
    void testGetPendingCandidates2() throws Exception {
        ArrayList<InternshipCandidatesDto> internshipCandidatesDtoList = new ArrayList<>();
        internshipCandidatesDtoList.add(new InternshipCandidatesDto());
        when(internshipCandidatesService.getPendingCandidates()).thenReturn(internshipCandidatesDtoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/intershipCandidates/getPendingCandidates");
        MockMvcBuilders.standaloneSetup(internshipCandidatesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("[{\"id\":0,\"etudiant\":null,\"internOfferJob\":null,\"files\":null,\"state\":null}]"));
    }

    /**
     * Method under test: {@link InternshipCandidatesController#acceptIntershipCandidate(Long)}
     */
    @Test
    void testAcceptIntershipCandidate() throws Exception {
        when(internshipCandidatesService.acceptCandidates(Mockito.<Long>any())).thenReturn(new InternshipCandidatesDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/intershipCandidates/acceptCandidats/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(internshipCandidatesController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":0,\"etudiant\":null,\"internOfferJob\":null,\"files\":null,\"state\":null}"));
    }

    /**
     * Method under test: {@link InternshipCandidatesController#getAcceptedCandidates()}
     */
    @Test
    void testGetAcceptedCandidates() throws Exception {
        when(internshipCandidatesService.getAcceptedCandidates()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/intershipCandidates/getAcceptedCandidates");
        MockMvcBuilders.standaloneSetup(internshipCandidatesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link InternshipCandidatesController#getAcceptedCandidates()}
     */
    @Test
    void testGetAcceptedCandidates2() throws Exception {
        ArrayList<InternshipCandidatesDto> internshipCandidatesDtoList = new ArrayList<>();
        internshipCandidatesDtoList.add(new InternshipCandidatesDto());
        when(internshipCandidatesService.getAcceptedCandidates()).thenReturn(internshipCandidatesDtoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/intershipCandidates/getAcceptedCandidates");
        MockMvcBuilders.standaloneSetup(internshipCandidatesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("[{\"id\":0,\"etudiant\":null,\"internOfferJob\":null,\"files\":null,\"state\":null}]"));
    }

    /**
     * Method under test: {@link InternshipCandidatesController#getDeclinedCandidates()}
     */
    @Test
    void testGetDeclinedCandidates() throws Exception {
        when(internshipCandidatesService.getDeclinedCandidates()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/intershipCandidates/getDeclinedCandidates");
        MockMvcBuilders.standaloneSetup(internshipCandidatesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link InternshipCandidatesController#getDeclinedCandidates()}
     */
    @Test
    void testGetDeclinedCandidates2() throws Exception {
        ArrayList<InternshipCandidatesDto> internshipCandidatesDtoList = new ArrayList<>();
        internshipCandidatesDtoList.add(new InternshipCandidatesDto());
        when(internshipCandidatesService.getDeclinedCandidates()).thenReturn(internshipCandidatesDtoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/intershipCandidates/getDeclinedCandidates");
        MockMvcBuilders.standaloneSetup(internshipCandidatesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("[{\"id\":0,\"etudiant\":null,\"internOfferJob\":null,\"files\":null,\"state\":null}]"));
    }

    /**
     * Method under test: {@link InternshipCandidatesController#acceptIntershipCandidate(Long)}
     */
    @Test
    void testAcceptIntershipCandidate2() throws Exception {
        when(internshipCandidatesService.acceptCandidates(Mockito.<Long>any())).thenReturn(new InternshipCandidatesDto());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(internshipCandidatesController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link InternshipCandidatesController#declineIntershipCandidate(Long)}
     */
    @Test
    void testDeclineIntershipCandidate() throws Exception {
        when(internshipCandidatesService.declineCandidates(Mockito.<Long>any()))
                .thenReturn(new InternshipCandidatesDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/intershipCandidates/declineCandidats/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(internshipCandidatesController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":0,\"etudiant\":null,\"internOfferJob\":null,\"files\":null,\"state\":null}"));
    }

    /**
     * Method under test: {@link InternshipCandidatesController#getInternshipCandidatesByOfferId(String)}
     */
    @Test
    void testGetInternshipCandidatesByOfferId() throws Exception {
        when(internshipCandidatesService.getInternshipCandidatesByIds(Mockito.<String>any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/intershipCandidates/getInternshipCandidatesByIds/{id}", "42");
        MockMvcBuilders.standaloneSetup(internshipCandidatesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link InternshipCandidatesController#getInternshipCandidatesByOfferId(String)}
     */
    @Test
    void testGetInternshipCandidatesByOfferId2() throws Exception {
        ArrayList<InternshipCandidatesDto> internshipCandidatesDtoList = new ArrayList<>();
        internshipCandidatesDtoList.add(new InternshipCandidatesDto());
        when(internshipCandidatesService.getInternshipCandidatesByIds(Mockito.<String>any()))
                .thenReturn(internshipCandidatesDtoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/intershipCandidates/getInternshipCandidatesByIds/{id}", "42");
        MockMvcBuilders.standaloneSetup(internshipCandidatesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("[{\"id\":0,\"etudiant\":null,\"internOfferJob\":null,\"files\":null,\"state\":null}]"));
    }

    /**
     * Method under test: {@link InternshipCandidatesController#getIntershipCandidate()}
     */
    @Test
    void testGetIntershipCandidate() throws Exception {
        when(internshipCandidatesService.getCandidates()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/intershipCandidates/getCandidats");
        MockMvcBuilders.standaloneSetup(internshipCandidatesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link InternshipCandidatesController#getIntershipCandidate()}
     */
    @Test
    void testGetIntershipCandidate2() throws Exception {
        ArrayList<InternshipCandidatesDto> internshipCandidatesDtoList = new ArrayList<>();
        internshipCandidatesDtoList.add(new InternshipCandidatesDto());
        when(internshipCandidatesService.getCandidates()).thenReturn(internshipCandidatesDtoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/intershipCandidates/getCandidats");
        MockMvcBuilders.standaloneSetup(internshipCandidatesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("[{\"id\":0,\"etudiant\":null,\"internOfferJob\":null,\"files\":null,\"state\":null}]"));
    }

    /**
     * Method under test: {@link InternshipCandidatesController#saveIntershipCandidate(InternshipCandidatesDto)}
     */
    @Test
    void testSaveIntershipCandidate() throws Exception {
        when(internshipCandidatesService.saveCandidates(Mockito.<InternshipCandidatesDto>any()))
                .thenReturn(new InternshipCandidatesDto());

        InternshipCandidatesDto internshipCandidatesDto = new InternshipCandidatesDto();
        internshipCandidatesDto.setEtudiant(new EtudiantDtoWithId());
        internshipCandidatesDto.setFiles(new ArrayList<>());
        internshipCandidatesDto.setId(1L);
        internshipCandidatesDto.setInternOfferJob(new InternOfferDto());
        internshipCandidatesDto.setState(State.ACCEPTED);
        String content = (new ObjectMapper()).writeValueAsString(internshipCandidatesDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/intershipCandidates/saveCandidats")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(internshipCandidatesController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":0,\"etudiant\":null,\"internOfferJob\":null,\"files\":null,\"state\":null}"));
    }
}

