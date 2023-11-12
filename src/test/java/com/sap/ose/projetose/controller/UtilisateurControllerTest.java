package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.UtilisateurDto;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.EtudiantRepository;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import com.sap.ose.projetose.service.OseService;
import com.sap.ose.projetose.service.UtilisateurService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UtilisateurController.class})
@ExtendWith(SpringExtension.class)
class UtilisateurControllerTest {
    @MockBean
    private OseService oseService;

    @MockBean
    private EtudiantRepository etudiantRepository;

    @MockBean
    private EmployeurRepository employeurRepository;

    @MockBean
    private UtilisateurService utilisateurService;

    @MockBean
    private InternshipmanagerRepository internshipmanagerRepository;

    @Autowired
    private UtilisateurController utilisateurController;

    /**
     * Method under test: {@link UtilisateurController#getAllUsers()}
     */
    @Test
    void testGetAllUsers() throws Exception {
        EtudiantDto etudiant = new EtudiantDto("Jean", "Dupont", "4387996589", "dupont@gmail.com", "2045878", 1L, null, null);
        EmployeurDto employeur = new EmployeurDto("Patrique", "Lemieux", "4383006589", "lemieux@gmail.com", "SAP");
        List<UtilisateurDto> utilisateurDtoList = new ArrayList<>();
        utilisateurDtoList.add(etudiant);
        utilisateurDtoList.add(employeur);
        when(oseService.getAllUsers()).thenReturn(utilisateurDtoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/utilisateur/utilisateurs");
        MockMvcBuilders.standaloneSetup(utilisateurController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("[{\"id\":0,\"nom\":\"Jean\",\"prenom\":\"Dupont\",\"phone\":\"4387996589\",\"email\":\"dupont@gmail.com\",\"matricule\":\"2045878\",\"programme_id\":1,\"cv\":null,\"internships_id\":null},{\"id\":0,\"nom\":\"Patrique\",\"prenom\":\"Lemieux\",\"phone\":\"4383006589\",\"email\":\"lemieux@gmail.com\",\"entreprise\":\"SAP\",\"programme_id\":0}]"));
    }

    /**
     * Method under test: {@link UtilisateurController#getAllUsers()}
     */
    @Test
    void testGetAllUsers2() throws Exception {
        when(oseService.getAllUsers()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(utilisateurController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

