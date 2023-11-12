package com.sap.ose.projetose.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.StudentAppliedOffersDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.EtudiantNotFoundException;
import com.sap.ose.projetose.exception.GlobalExceptionHandler;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.service.EtudiantService;
import org.junit.jupiter.api.BeforeEach;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.in;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {EtudiantController.class})
@ExtendWith(SpringExtension.class)
class EtudiantControllerTest {
    @Autowired
    private EtudiantController etudiantController;
    @MockBean
    private EtudiantService oseService;


    private Etudiant etudiant;

    @BeforeEach
    public void setup() {

        InternOffer internOffer = new InternOffer();
        internOffer.setDescription("The characteristics of someone or something");
        internOffer.setEmployeur(new Employeur());
        internOffer.setEndDate(LocalDate.now());
        internOffer.setFile(new File());
        internOffer.setState(State.ACCEPTED);
        internOffer.setId(1L);
        internOffer.setInternshipCandidates(null);
        internOffer.setLocation("Location");
        internOffer.setProgramme(null);
        internOffer.setSalaryByHour(10.0d);
        internOffer.setProgramme(new Programme());
        internOffer.setStartDate(LocalDate.now());
        internOffer.setTitle("Dr");


        etudiant = new Etudiant();
        etudiant.setCv(null);
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1);
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme(new Programme());
        etudiant.setInternshipsCandidate(List.of(
                new InternshipCandidates(
                        etudiant,
                        internOffer,
                        List.of(new File())
                )
        ));
    }

    /**
     * Method under test: {@link EtudiantController#getEtudiant(Long)}
     */
    @Test
    void testGetEtudiant() throws Exception {
        when(oseService.getEtudiantById(Mockito.<Long>any())).thenReturn(new EtudiantDto("Matricule", 1, null, null));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student/{id}", 1L);
        MockMvcBuilders.standaloneSetup(etudiantController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("{\"id\":0,\"nom\":null,\"prenom\":null,\"phone\":null,\"email\":null,\"matricule\":\"Matricule\",\"programme_id\":1," + "\"cv\":null,\"internships_id\":null}"));
    }

    /**
     * Method under test: {@link EtudiantController#getEtudiant(Long)}
     */
    @Test
    void testGetEtudiant2() throws Exception {
        when(oseService.getEtudiantById(Mockito.<Long>any())).thenReturn(new EtudiantDto("Matricule", 1L, null, null));
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student/etudiants");
        MockMvcBuilders.standaloneSetup(etudiantController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void getOffersApplied_EtudiantNotFoundException() throws Exception {
        when(etudiantController.getOffersApplied(anyLong())).thenThrow(new EtudiantNotFoundException());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student/1/offersApplied")
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(etudiantController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Étudiant non trouvé")));
    }

    @Test
    void getOffersApplied_DatabaseException() throws Exception {
        when(etudiantController.getOffersApplied(anyLong())).thenThrow(new DatabaseException());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student/1/offersApplied")
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(etudiantController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Erreur d'accès a la base de données")));
    }

    @Test
    void getOffersApplied_ServiceException() throws Exception {
        when(etudiantController.getOffersApplied(anyLong())).thenThrow(new ServiceException());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student/1/offersApplied")
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(etudiantController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Erreur au niveau du service")));
    }


    @Test
    void getOffersApplied_EmptyArray() throws Exception {
        when(oseService.getOffersAppliedByEtudiant(anyLong())).thenReturn(new ArrayList<>());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student/1/offersApplied")
                .contentType(MediaType.APPLICATION_JSON);

        MockMvcBuilders.standaloneSetup(etudiantController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void getOffersApplied_StudentAppliedOffersArray() throws Exception {


        StudentAppliedOffersDto dto = new StudentAppliedOffersDto(new InternOfferDto(etudiant.getInternshipsCandidate().get(0).getInternOffer()), List.of(new FileDto()));
        when(oseService.getOffersAppliedByEtudiant(anyLong())).thenReturn(List.of(dto));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student/1/offersApplied")
                .contentType(MediaType.APPLICATION_JSON);

        String expectedJson = new ObjectMapper().writeValueAsString(List.of(dto));

        MockMvcBuilders.standaloneSetup(etudiantController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(expectedJson));
    }
}


