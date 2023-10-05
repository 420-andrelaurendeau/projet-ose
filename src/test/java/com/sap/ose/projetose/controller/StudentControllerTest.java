package com.sap.ose.projetose.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.dto.StudentApplicationDto;
import com.sap.ose.projetose.dto.StudentDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.GlobalExceptionHandler;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.exception.StudentNotFoundException;
import com.sap.ose.projetose.models.*;
import com.sap.ose.projetose.service.StudentService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {StudentController.class})
@ExtendWith(SpringExtension.class)
class StudentControllerTest {
    @Autowired
    private StudentController studentController;
    @MockBean
    private StudentService oseService;


    private Student etudiant;

    @BeforeEach
    public void setup() {

        InternshipOffer internshipOffer = new InternshipOffer();
        internshipOffer.setDescription("The characteristics of someone or something");
        internshipOffer.setEmployer(new Employer());
        internshipOffer.setEndDate(LocalDate.now());
        internshipOffer.setFile(new File());
        internshipOffer.setState(AssessmentState.APPROVED);
        internshipOffer.setId(1L);
        internshipOffer.setInternshipCandidates(null);
        internshipOffer.setLocation("Location");
        internshipOffer.setFormation(null);
        internshipOffer.setSalaryByHour(10.0d);
        internshipOffer.setFormation(new Formation());
        internshipOffer.setStartDate(LocalDate.now());
        internshipOffer.setTitle("Dr");


        etudiant = new Student();
        etudiant.setCv(new ArrayList<>());
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1);
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setFormation(new Formation());
        etudiant.setInternshipsCandidate(List.of(
                new InternshipCandidates(
                        etudiant,
                        internshipOffer,
                        List.of(new File())
                )
        ));
    }

    /**
     * Method under test: {@link StudentController#getEtudiant(Long)}
     */
    @Test
    void testGetEtudiant() throws Exception {
        when(oseService.getEtudiantById(Mockito.<Long>any())).thenReturn(new StudentDto("Matricule", 1, new ArrayList<>(), null));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/etudiant/{id}", 1L);
        MockMvcBuilders.standaloneSetup(studentController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("{\"nom\":null,\"prenom\":null,\"phone\":null,\"email\":null,\"matricule\":\"Matricule\",\"programme_id\":1," + "\"cv\":\"Cv\",\"internships_id\":null}"));
    }

    /**
     * Method under test: {@link StudentController#saveEtudiant(Student)}
     */
    @Test
    void testSaveEtudiant() throws Exception {
        Student etudiant = new Student();
        etudiant.setCv(new ArrayList<>());
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1);
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setFormation(new Formation());
        Optional<Student> ofResult = Optional.of(etudiant);
        when(oseService.saveEtudiant(Mockito.any())).thenReturn(ofResult);

        Student etudiant2 = new Student();
        etudiant2.setCv(new ArrayList<>());
        etudiant2.setEmail("jane.doe@example.org");
        etudiant2.setId(1);
        etudiant2.setMatricule("Matricule");
        etudiant2.setNom("Nom");
        etudiant2.setPassword("iloveyou");
        etudiant2.setPhone("6625550144");
        etudiant2.setPrenom("Prenom");
        etudiant2.setFormation(new Formation());
        String content = (new ObjectMapper()).writeValueAsString(etudiant2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/etudiant/ajouter").contentType(MediaType.APPLICATION_JSON).content(content);
        MockMvcBuilders.standaloneSetup(studentController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"phone\":\"6625550144\",\"email\":\"jane.doe@example.org\",\"password\"" + ":\"iloveyou\",\"matricule\":\"Matricule\",\"programme\":{\"id\":0,\"nom\":null,\"description\":null},\"cv\":\"Cv\",\"internshipsCandidate\":null}"));
    }

    /**
     * Method under test: {@link StudentController#getEtudiant(Long)}
     */
    @Test
    void testGetEtudiant2() throws Exception {
        when(oseService.getEtudiantById(Mockito.<Long>any())).thenReturn(new StudentDto("Matricule", 1L, new ArrayList<>(), null));
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(studentController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link StudentController#getEtudiants()}
     */
    @Test
    void testGetEtudiants() throws Exception {
        when(oseService.getEtudiants()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/etudiant/etudiants");
        MockMvcBuilders.standaloneSetup(studentController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void getOffersApplied_EtudiantNotFoundException() throws Exception {
        when(studentController.getOffersApplied(anyLong())).thenThrow(new StudentNotFoundException());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/etudiant/1/offersApplied")
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(studentController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Étudiant non trouvé")));
    }

    @Test
    void getOffersApplied_DatabaseException() throws Exception {
        when(studentController.getOffersApplied(anyLong())).thenThrow(new DatabaseException());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/etudiant/1/offersApplied")
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(studentController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Erreur d'accès a la base de données")));
    }

    @Test
    void getOffersApplied_ServiceException() throws Exception {
        when(studentController.getOffersApplied(anyLong())).thenThrow(new ServiceException());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/etudiant/1/offersApplied")
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions resultActions = MockMvcBuilders.standaloneSetup(studentController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
                .perform(requestBuilder);

        resultActions.andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Erreur au niveau du service")));
    }


    @Test
    void getOffersApplied_EmptyArray() throws Exception {
        when(oseService.getOffersAppliedByEtudiant(anyLong())).thenReturn(new ArrayList<>());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/etudiant/1/offersApplied")
                .contentType(MediaType.APPLICATION_JSON);

        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void getOffersApplied_StudentAppliedOffersArray() throws Exception {


        StudentApplicationDto dto = new StudentApplicationDto(new InternshipOfferDto(etudiant.getInternshipsCandidate().get(0).getInternshipOffer()), List.of(new FileDto()));
        when(oseService.getOffersAppliedByEtudiant(anyLong())).thenReturn(List.of(dto));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/etudiant/1/offersApplied")
                .contentType(MediaType.APPLICATION_JSON);

        String expectedJson = new ObjectMapper().writeValueAsString(List.of(dto));

        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(expectedJson));
    }
}


