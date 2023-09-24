package com.sap.ose.projetose.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.service.InternOfferService;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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

@ContextConfiguration(classes = {InternOfferController.class})
@ExtendWith(SpringExtension.class)
class InternOfferControllerTest {
    @Autowired
    private InternOfferController internOfferController;

    @MockBean
    private InternOfferService internOfferService;

    private File file;
    private Programme programme;
    private InternOffer internOffer;

    @BeforeEach
    void setUp() throws UnsupportedEncodingException {
        // Initialisation de File
        file = new File();
        file.setAccepted(true);
        file.setContent("AXAXAXAX".getBytes("UTF-8"));
        file.setFileName("foo.txt");
        file.setId(1L);

        // Initialisation de Programme
        programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1);
        programme.setNom("Nom");

        // Initialisation de InterOfferJob
        internOffer = new InternOffer();
        internOffer.setDescription("The characteristics of someone or something");
        internOffer.setEndDate(LocalDate.of(1970, 1, 1));
        internOffer.setFile(file);
        internOffer.setId(1L);
        internOffer.setInternshipCandidates(new ArrayList<>());
        internOffer.setLocation("Location");
        internOffer.setProgramme(programme);
        internOffer.setSalaryByHour(10.0d);
        internOffer.setStartDate(LocalDate.of(1970, 1, 1));
        internOffer.setTitle("Dr");
    }

    @Test
    void testSaveInterOfferJob() throws Exception {

        when(internOfferService.saveInterOfferJob(Mockito.<InternOfferDto>any())).thenReturn(internOffer);

        InternOfferDto internOfferDto = new InternOfferDto();
        internOfferDto.setDescription("The characteristics of someone or something");
        internOfferDto.setEndDate("2020-03-01");
        internOfferDto.setFile(new FileDto());
        internOfferDto.setId(1L);
        internOfferDto.setInternshipCandidates(new ArrayList<>());
        internOfferDto.setLocation("Location");
        internOfferDto.setProgramme(new ProgrammeDto(1, "Nom", "The characteristics of someone or something"));
        internOfferDto.setSalaryByHour(10.0d);
        internOfferDto.setStartDate("2020-03-01");
        internOfferDto.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(internOfferDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/interOfferJob/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"title\":\"Dr\",\"location\":\"Location\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"salaryByHour\":0.0,\"startDate\":\"1970-01-01\",\"endDate\":\"1970-01-01\",\"internshipCandidates"
                                        + "\":[],\"programme\":{\"id\":1,\"nom\":\"Nom\",\"description\":\"The characteristics of someone or something\"},"
                                        + "\"file\":{\"id\":1,\"content\":\"QVhBWEFYQVg=\",\"fileName\":\"foo.txt\",\"accepted\":true}}"));
    }

    @Test
    void testSaveInterOfferJob2() throws Exception {

        when(internOfferService.saveInterOfferJob(Mockito.<InternOfferDto>any())).thenReturn(internOffer);

        InternOfferDto internOfferDto = new InternOfferDto();
        internOfferDto.setDescription("The characteristics of someone or something");
        internOfferDto.setEndDate("2020-03-01");
        internOfferDto.setFile(new FileDto());
        internOfferDto.setId(1L);
        internOfferDto.setInternshipCandidates(new ArrayList<>());
        internOfferDto.setLocation("Location");
        internOfferDto.setProgramme(null);
        internOfferDto.setSalaryByHour(10.0d);
        internOfferDto.setStartDate("2020-03-01");
        internOfferDto.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(internOfferDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/interOfferJob/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(internOfferController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"title\":\"Dr\",\"location\":\"Location\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"salaryByHour\":0.0,\"startDate\":\"1970-01-01\",\"endDate\":\"1970-01-01\",\"internshipCandidates"
                                        + "\":[],\"programme\":{\"id\":1,\"nom\":\"Nom\",\"description\":\"The characteristics of someone or something\"},"
                                        + "\"file\":{\"id\":1,\"content\":\"QVhBWEFYQVg=\",\"fileName\":\"foo.txt\",\"accepted\":true}}"));
    }
}

