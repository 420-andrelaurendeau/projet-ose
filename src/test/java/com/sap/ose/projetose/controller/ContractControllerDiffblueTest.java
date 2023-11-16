package com.sap.ose.projetose.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ose.projetose.dto.ContractDto;
import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.service.ContractService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ContractController.class})
@ExtendWith(SpringExtension.class)
class ContractControllerDiffblueTest {
    @Autowired
    private ContractController contractController;

    @MockBean
    private ContractService contractService;

    /**
     * Method under test: {@link ContractController#getContract(long)}
     */
    @Test
    void testGetContract() throws Exception {
        when(contractService.getById(anyLong())).thenReturn(new ContractDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/contract/{id}", 1L);
        MockMvcBuilders.standaloneSetup(contractController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"employeur\":null,\"etudiantDto\":null,\"internOfferDto\":null,\"signatureInternShipManager\":false"
                                        + ",\"signatureEmployer\":false,\"signatureStudent\":false,\"fileId\":0,\"content\":null,\"fileName\":null}"));
    }

    /**
     * Method under test: {@link ContractController#getContract(long)}
     */
    @Test
    void testGetContract2() throws Exception {
        EmployeurDto employeur = new EmployeurDto("Entreprise", 1L);

        EtudiantDto etudiantDto = new EtudiantDto();
        when(contractService.getById(anyLong())).thenReturn(new ContractDto(1L, employeur, etudiantDto,
                new InternOfferDto(), true, true, true, 1L, "Not all who wander are lost", "foo.txt"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/contract/{id}", 1L);
        MockMvcBuilders.standaloneSetup(contractController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"employeur\":{\"id\":0,\"nom\":null,\"prenom\":null,\"phone\":null,\"email\":null,\"entreprise\":\"Entreprise"
                                        + "\",\"programme_id\":1},\"etudiantDto\":{\"id\":0,\"nom\":null,\"prenom\":null,\"phone\":null,\"email\":null,\"matricule"
                                        + "\":null,\"programme_id\":0,\"cv\":null,\"internships_id\":null},\"internOfferDto\":{\"id\":0,\"title\":null,\"location"
                                        + "\":null,\"description\":null,\"salaryByHour\":0.0,\"startDate\":null,\"endDate\":null,\"internshipCandidates\""
                                        + ":null,\"programmeId\":0,\"file\":null,\"employeurId\":0,\"programmeNom\":null,\"employeurPrenom\":null,\"employeurNom"
                                        + "\":null,\"employeurEntreprise\":null,\"offerReviewRequestId\":0,\"state\":null},\"signatureInternShipManager"
                                        + "\":true,\"signatureEmployer\":true,\"signatureStudent\":true,\"fileId\":1,\"content\":\"Not all who wander are"
                                        + " lost\",\"fileName\":\"foo.txt\"}"));
    }

    /**
     * Method under test: {@link ContractController#getContract(long)}
     */
    @Test
    void testGetContract3() throws Exception {
        when(contractService.getById(anyLong())).thenReturn(new ContractDto());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(contractController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link ContractController#getStudentContract(long, int, int, String, String)}
     */
    @Test
    void testGetStudentContract() throws Exception {
        when(contractService.getAllByStudentID(anyLong(), anyInt(), anyInt(), Mockito.<Sort>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/contract/student/{id}/getAll", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(contractController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":[],\"pageable\":\"INSTANCE\",\"totalPages\":1,\"totalElements\":0,\"last\":true,\"numberOfElements"
                                        + "\":0,\"first\":true,\"size\":0,\"number\":0,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"empty"
                                        + "\":true}"));
    }

    /**
     * Method under test: {@link ContractController#getStudentContract(long, int, int, String, String)}
     */
    @Test
    void testGetStudentContract2() throws Exception {
        ArrayList<ContractDto> content = new ArrayList<>();
        content.add(new ContractDto());
        PageImpl<ContractDto> pageImpl = new PageImpl<>(content);
        when(contractService.getAllByStudentID(anyLong(), anyInt(), anyInt(), Mockito.<Sort>any())).thenReturn(pageImpl);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/contract/student/{id}/getAll", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(contractController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":[{\"id\":0,\"employeur\":null,\"etudiantDto\":null,\"internOfferDto\":null,\"signatureInternShipManager"
                                        + "\":false,\"signatureEmployer\":false,\"signatureStudent\":false,\"fileId\":0,\"content\":null,\"fileName\":null"
                                        + "}],\"pageable\":\"INSTANCE\",\"totalPages\":1,\"totalElements\":1,\"last\":true,\"numberOfElements\":1,\"first\""
                                        + ":true,\"size\":1,\"number\":0,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"empty\":false}"));
    }

    /**
     * Method under test: {@link ContractController#getStudentContract(long, int, int, String, String)}
     */
    @Test
    void testGetStudentContract3() throws Exception {
        ArrayList<ContractDto> content = new ArrayList<>();
        EmployeurDto employeur = new EmployeurDto("Entreprise", 1L);

        EtudiantDto etudiantDto = new EtudiantDto();
        content.add(new ContractDto(1L, employeur, etudiantDto, new InternOfferDto(), true, true, true, 1L,
                "Not all who wander are lost", "foo.txt"));
        PageImpl<ContractDto> pageImpl = new PageImpl<>(content);
        when(contractService.getAllByStudentID(anyLong(), anyInt(), anyInt(), Mockito.<Sort>any())).thenReturn(pageImpl);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/contract/student/{id}/getAll", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(contractController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":[{\"id\":1,\"employeur\":{\"id\":0,\"nom\":null,\"prenom\":null,\"phone\":null,\"email\":null,\"entreprise"
                                        + "\":\"Entreprise\",\"programme_id\":1},\"etudiantDto\":{\"id\":0,\"nom\":null,\"prenom\":null,\"phone\":null,\"email\""
                                        + ":null,\"matricule\":null,\"programme_id\":0,\"cv\":null,\"internships_id\":null},\"internOfferDto\":{\"id\":0,"
                                        + "\"title\":null,\"location\":null,\"description\":null,\"salaryByHour\":0.0,\"startDate\":null,\"endDate\":null,"
                                        + "\"internshipCandidates\":null,\"programmeId\":0,\"file\":null,\"employeurId\":0,\"programmeNom\":null,\"employeurPrenom"
                                        + "\":null,\"employeurNom\":null,\"employeurEntreprise\":null,\"offerReviewRequestId\":0,\"state\":null},"
                                        + "\"signatureInternShipManager\":true,\"signatureEmployer\":true,\"signatureStudent\":true,\"fileId\":1,\"content\":\"Not"
                                        + " all who wander are lost\",\"fileName\":\"foo.txt\"}],\"pageable\":\"INSTANCE\",\"totalPages\":1,\"totalElements"
                                        + "\":1,\"last\":true,\"numberOfElements\":1,\"first\":true,\"size\":1,\"number\":0,\"sort\":{\"sorted\":false,\"unsorted"
                                        + "\":true,\"empty\":true},\"empty\":false}"));
    }

    /**
     * Method under test: {@link ContractController#saveContract(ContractDto)}
     */
    @Test
    void testSaveContract() throws Exception {
        when(contractService.saveContractDto(Mockito.<ContractDto>any())).thenReturn(new ContractDto());

        ContractDto contractDto = new ContractDto();
        contractDto.setContent("Not all who wander are lost");
        contractDto.setEmployeur(new EmployeurDto("Entreprise", 1L));
        contractDto.setEtudiantDto(new EtudiantDto());
        contractDto.setFileId(1L);
        contractDto.setFileName("foo.txt");
        contractDto.setId(1L);
        contractDto.setInternOfferDto(new InternOfferDto());
        contractDto.setSignatureEmployer(true);
        contractDto.setSignatureInternShipManager(true);
        contractDto.setSignatureStudent(true);
        String content = (new ObjectMapper()).writeValueAsString(contractDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/contract/internshipmanager/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(contractController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"employeur\":null,\"etudiantDto\":null,\"internOfferDto\":null,\"signatureInternShipManager\":false"
                                        + ",\"signatureEmployer\":false,\"signatureStudent\":false,\"fileId\":0,\"content\":null,\"fileName\":null}"));
    }

    /**
     * Method under test: {@link ContractController#saveContract(ContractDto)}
     */
    @Test
    void testSaveContract2() throws Exception {
        EmployeurDto employeur = new EmployeurDto("Entreprise", 1L);

        EtudiantDto etudiantDto = new EtudiantDto();
        when(contractService.saveContractDto(Mockito.<ContractDto>any())).thenReturn(new ContractDto(1L, employeur,
                etudiantDto, new InternOfferDto(), true, true, true, 1L, "Not all who wander are lost", "foo.txt"));

        ContractDto contractDto = new ContractDto();
        contractDto.setContent("Not all who wander are lost");
        contractDto.setEmployeur(new EmployeurDto("Entreprise", 1L));
        contractDto.setEtudiantDto(new EtudiantDto());
        contractDto.setFileId(1L);
        contractDto.setFileName("foo.txt");
        contractDto.setId(1L);
        contractDto.setInternOfferDto(new InternOfferDto());
        contractDto.setSignatureEmployer(true);
        contractDto.setSignatureInternShipManager(true);
        contractDto.setSignatureStudent(true);
        String content = (new ObjectMapper()).writeValueAsString(contractDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/contract/internshipmanager/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(contractController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"employeur\":{\"id\":0,\"nom\":null,\"prenom\":null,\"phone\":null,\"email\":null,\"entreprise\":\"Entreprise"
                                        + "\",\"programme_id\":1},\"etudiantDto\":{\"id\":0,\"nom\":null,\"prenom\":null,\"phone\":null,\"email\":null,\"matricule"
                                        + "\":null,\"programme_id\":0,\"cv\":null,\"internships_id\":null},\"internOfferDto\":{\"id\":0,\"title\":null,\"location"
                                        + "\":null,\"description\":null,\"salaryByHour\":0.0,\"startDate\":null,\"endDate\":null,\"internshipCandidates\""
                                        + ":null,\"programmeId\":0,\"file\":null,\"employeurId\":0,\"programmeNom\":null,\"employeurPrenom\":null,\"employeurNom"
                                        + "\":null,\"employeurEntreprise\":null,\"offerReviewRequestId\":0,\"state\":null},\"signatureInternShipManager"
                                        + "\":true,\"signatureEmployer\":true,\"signatureStudent\":true,\"fileId\":1,\"content\":\"Not all who wander are"
                                        + " lost\",\"fileName\":\"foo.txt\"}"));
    }

    /**
     * Method under test: {@link ContractController#saveEmployerContract(ContractDto)}
     */
    @Test
    void testSaveEmployerContract() throws Exception {
        when(contractService.saveContractEmployerDto(Mockito.<ContractDto>any())).thenReturn(new ContractDto());

        ContractDto contractDto = new ContractDto();
        contractDto.setContent("Not all who wander are lost");
        contractDto.setEmployeur(new EmployeurDto("Entreprise", 1L));
        contractDto.setEtudiantDto(new EtudiantDto());
        contractDto.setFileId(1L);
        contractDto.setFileName("foo.txt");
        contractDto.setId(1L);
        contractDto.setInternOfferDto(new InternOfferDto());
        contractDto.setSignatureEmployer(true);
        contractDto.setSignatureInternShipManager(true);
        contractDto.setSignatureStudent(true);
        String content = (new ObjectMapper()).writeValueAsString(contractDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/contract/employer/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(contractController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"employeur\":null,\"etudiantDto\":null,\"internOfferDto\":null,\"signatureInternShipManager\":false"
                                        + ",\"signatureEmployer\":false,\"signatureStudent\":false,\"fileId\":0,\"content\":null,\"fileName\":null}"));
    }

    /**
     * Method under test: {@link ContractController#saveEmployerContract(ContractDto)}
     */
    @Test
    void testSaveEmployerContract2() throws Exception {
        EmployeurDto employeur = new EmployeurDto("Entreprise", 1L);

        EtudiantDto etudiantDto = new EtudiantDto();
        when(contractService.saveContractEmployerDto(Mockito.<ContractDto>any())).thenReturn(new ContractDto(1L, employeur,
                etudiantDto, new InternOfferDto(), true, true, true, 1L, "Not all who wander are lost", "foo.txt"));

        ContractDto contractDto = new ContractDto();
        contractDto.setContent("Not all who wander are lost");
        contractDto.setEmployeur(new EmployeurDto("Entreprise", 1L));
        contractDto.setEtudiantDto(new EtudiantDto());
        contractDto.setFileId(1L);
        contractDto.setFileName("foo.txt");
        contractDto.setId(1L);
        contractDto.setInternOfferDto(new InternOfferDto());
        contractDto.setSignatureEmployer(true);
        contractDto.setSignatureInternShipManager(true);
        contractDto.setSignatureStudent(true);
        String content = (new ObjectMapper()).writeValueAsString(contractDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/contract/employer/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(contractController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"employeur\":{\"id\":0,\"nom\":null,\"prenom\":null,\"phone\":null,\"email\":null,\"entreprise\":\"Entreprise"
                                        + "\",\"programme_id\":1},\"etudiantDto\":{\"id\":0,\"nom\":null,\"prenom\":null,\"phone\":null,\"email\":null,\"matricule"
                                        + "\":null,\"programme_id\":0,\"cv\":null,\"internships_id\":null},\"internOfferDto\":{\"id\":0,\"title\":null,\"location"
                                        + "\":null,\"description\":null,\"salaryByHour\":0.0,\"startDate\":null,\"endDate\":null,\"internshipCandidates\""
                                        + ":null,\"programmeId\":0,\"file\":null,\"employeurId\":0,\"programmeNom\":null,\"employeurPrenom\":null,\"employeurNom"
                                        + "\":null,\"employeurEntreprise\":null,\"offerReviewRequestId\":0,\"state\":null},\"signatureInternShipManager"
                                        + "\":true,\"signatureEmployer\":true,\"signatureStudent\":true,\"fileId\":1,\"content\":\"Not all who wander are"
                                        + " lost\",\"fileName\":\"foo.txt\"}"));
    }

    /**
     * Method under test: {@link ContractController#saveStudentContract(ContractDto)}
     */
    @Test
    void testSaveStudentContract() throws Exception {
        when(contractService.saveContractStudentDto(Mockito.<ContractDto>any())).thenReturn(new ContractDto());

        ContractDto contractDto = new ContractDto();
        contractDto.setContent("Not all who wander are lost");
        contractDto.setEmployeur(new EmployeurDto("Entreprise", 1L));
        contractDto.setEtudiantDto(new EtudiantDto());
        contractDto.setFileId(1L);
        contractDto.setFileName("foo.txt");
        contractDto.setId(1L);
        contractDto.setInternOfferDto(new InternOfferDto());
        contractDto.setSignatureEmployer(true);
        contractDto.setSignatureInternShipManager(true);
        contractDto.setSignatureStudent(true);
        String content = (new ObjectMapper()).writeValueAsString(contractDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/contract/student/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(contractController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"employeur\":null,\"etudiantDto\":null,\"internOfferDto\":null,\"signatureInternShipManager\":false"
                                        + ",\"signatureEmployer\":false,\"signatureStudent\":false,\"fileId\":0,\"content\":null,\"fileName\":null}"));
    }

    /**
     * Method under test: {@link ContractController#saveStudentContract(ContractDto)}
     */
    @Test
    void testSaveStudentContract2() throws Exception {
        EmployeurDto employeur = new EmployeurDto("Entreprise", 1L);

        EtudiantDto etudiantDto = new EtudiantDto();
        when(contractService.saveContractStudentDto(Mockito.<ContractDto>any())).thenReturn(new ContractDto(1L, employeur,
                etudiantDto, new InternOfferDto(), true, true, true, 1L, "Not all who wander are lost", "foo.txt"));

        ContractDto contractDto = new ContractDto();
        contractDto.setContent("Not all who wander are lost");
        contractDto.setEmployeur(new EmployeurDto("Entreprise", 1L));
        contractDto.setEtudiantDto(new EtudiantDto());
        contractDto.setFileId(1L);
        contractDto.setFileName("foo.txt");
        contractDto.setId(1L);
        contractDto.setInternOfferDto(new InternOfferDto());
        contractDto.setSignatureEmployer(true);
        contractDto.setSignatureInternShipManager(true);
        contractDto.setSignatureStudent(true);
        String content = (new ObjectMapper()).writeValueAsString(contractDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/contract/student/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(contractController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"employeur\":{\"id\":0,\"nom\":null,\"prenom\":null,\"phone\":null,\"email\":null,\"entreprise\":\"Entreprise"
                                        + "\",\"programme_id\":1},\"etudiantDto\":{\"id\":0,\"nom\":null,\"prenom\":null,\"phone\":null,\"email\":null,\"matricule"
                                        + "\":null,\"programme_id\":0,\"cv\":null,\"internships_id\":null},\"internOfferDto\":{\"id\":0,\"title\":null,\"location"
                                        + "\":null,\"description\":null,\"salaryByHour\":0.0,\"startDate\":null,\"endDate\":null,\"internshipCandidates\""
                                        + ":null,\"programmeId\":0,\"file\":null,\"employeurId\":0,\"programmeNom\":null,\"employeurPrenom\":null,\"employeurNom"
                                        + "\":null,\"employeurEntreprise\":null,\"offerReviewRequestId\":0,\"state\":null},\"signatureInternShipManager"
                                        + "\":true,\"signatureEmployer\":true,\"signatureStudent\":true,\"fileId\":1,\"content\":\"Not all who wander are"
                                        + " lost\",\"fileName\":\"foo.txt\"}"));
    }
}
