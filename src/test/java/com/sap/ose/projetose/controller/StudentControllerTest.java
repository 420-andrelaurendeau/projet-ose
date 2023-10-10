package com.sap.ose.projetose.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternshipOfferDto;
import com.sap.ose.projetose.dto.StudentApplicationsDto;
import com.sap.ose.projetose.dto.StudentDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.StudentNotFoundException;
import com.sap.ose.projetose.exception.GlobalExceptionHandler;
import com.sap.ose.projetose.exception.ServiceException;
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


    private Student student;

    @BeforeEach
    public void setup() {

        InternshipOffer internshipOffer = new InternshipOffer();
        internshipOffer.setDescription("The characteristics of someone or something");
        internshipOffer.setEmployer(new Employer());
        internshipOffer.setEndDate(LocalDate.now());
        internshipOffer.setFile(new File());
        internshipOffer.setState(ApprovalStatus.APPROVED);
        internshipOffer.setId(1L);
        internshipOffer.setInternshipCandidates(null);
        internshipOffer.setLocation("Location");
        internshipOffer.setProgram(null);
        internshipOffer.setSalaryByHour(10.0d);
        internshipOffer.setProgram(new Program());
        internshipOffer.setStartDate(LocalDate.now());
        internshipOffer.setTitle("Dr");


        student = new Student();
        student.setCvList(null);
        student.setEmail("jane.doe@example.org");
        student.setId(1);
        student.setMatricule("Matricule");
        student.setLastName("Nom");
        student.setPassword("iloveyou");
        student.setPhoneNumber("6625550144");
        student.setFirstName("Prenom");
        student.setProgram(new Program());
        student.setInternshipApplications(List.of(
                new InternshipApplication(
                        student,
                        internshipOffer,
                        List.of(new File())
                )
        ));
    }

    /**
     * Method under test: {@link StudentController#getStudent(Long)}
     */
    @Test
    void testGetEtudiant() throws Exception {
        when(oseService.getStudentDTOById(Mockito.<Long>any())).thenReturn(new StudentDto("Matricule", 1, null, null));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/etudiant/{id}", 1L);
        MockMvcBuilders.standaloneSetup(studentController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("{\"id\":0,\"lastName\":null,\"firstName\":null,\"phoneNumber\":null,\"email\":null,\"matricule\":\"Matricule\",\"programId\":1," + "\"cvIds\":null,\"applicationIds\":null}"));
    }

    /**
     * Method under test: {@link StudentController#saveStudent(Student)}
     */
    @Test
    void testSaveEtudiant() throws Exception {
        Student student = new Student();
        student.setCvList(null);
        student.setEmail("jane.doe@example.org");
        student.setId(1);
        student.setMatricule("Matricule");
        student.setLastName("Nom");
        student.setPassword("iloveyou");
        student.setPhoneNumber("6625550144");
        student.setFirstName("Prenom");
        student.setProgram(new Program());
        Optional<Student> ofResult = Optional.of(student);
        when(oseService.saveStudent(Mockito.any())).thenReturn(ofResult);

        Student student2 = new Student();
        student2.setCvList(null);
        student2.setEmail("jane.doe@example.org");
        student2.setId(1);
        student2.setMatricule("Matricule");
        student2.setLastName("Nom");
        student2.setPassword("iloveyou");
        student2.setPhoneNumber("6625550144");
        student2.setFirstName("Prenom");
        student2.setProgram(new Program());
        String content = (new ObjectMapper()).writeValueAsString(student2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/etudiant/ajouter").contentType(MediaType.APPLICATION_JSON).content(content);
        MockMvcBuilders.standaloneSetup(studentController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"lastName\":\"Nom\",\"firstName\":\"Prenom\",\"phoneNumber\":\"6625550144\",\"email\":\"jane.doe@example.org\",\"password\"" + ":\"iloveyou\",\"matricule\":\"Matricule\",\"program\":{\"id\":0,\"nom\":null,\"description\":null},\"cvList\":null,\"internshipApplications\":null}"));
    }

    /**
     * Method under test: {@link StudentController#getStudent(Long)}
     */
    @Test
    void testGetEtudiant2() throws Exception {
        when(oseService.getStudentDTOById(Mockito.<Long>any())).thenReturn(new StudentDto("Matricule", 1L, null, null));
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(studentController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link StudentController#getStudents()}
     */
    @Test
    void testGetEtudiants() throws Exception {
        when(oseService.getStudents()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/etudiant/etudiants");
        MockMvcBuilders.standaloneSetup(studentController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void getOffersApplied_EtudiantNotFoundException() throws Exception {
        when(studentController.getApplicationsByStudent(anyLong())).thenThrow(new StudentNotFoundException());

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
        when(studentController.getApplicationsByStudent(anyLong())).thenThrow(new DatabaseException());

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
        when(studentController.getApplicationsByStudent(anyLong())).thenThrow(new ServiceException());

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
        when(oseService.getApplicationsByStudent(anyLong())).thenReturn(new ArrayList<>());

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


        StudentApplicationsDto dto = new StudentApplicationsDto(new InternshipOfferDto(student.getInternshipApplications().get(0).getInternshipOffer()), List.of(new FileDto()));
        when(oseService.getApplicationsByStudent(anyLong())).thenReturn(List.of(dto));

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


