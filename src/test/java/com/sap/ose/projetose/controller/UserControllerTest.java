package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.EmployerDto;
import com.sap.ose.projetose.dto.StudentDto;
import com.sap.ose.projetose.dto.UserDto;
import com.sap.ose.projetose.service.UserService;
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

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private UserController userController;

    /**
     * Method under test: {@link UserController#getAllUsers()}
     */
    @Test
    void testGetAllUsers() throws Exception {
        StudentDto etudiant = new StudentDto("Jean", "Dupont", "4387996589", "dupont@gmail.com", "2045878", 1L, null, null);
        EmployerDto employeur = new EmployerDto("Patrique", "Lemieux", "4383006589", "lemieux@gmail.com", "SAP");
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(etudiant);
        userDtoList.add(employeur);
        when(userService.getAllUsers()).thenReturn(userDtoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/utilisateur/utilisateurs");
        MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string("[{\"id\":0,\"lastName\":\"Jean\",\"firstName\":\"Dupont\",\"phoneNumber\":\"4387996589\",\"email\":\"dupont@gmail.com\",\"matricule\":\"2045878\",\"programId\":1,\"cvIds\":null,\"internshipIds\":null},{\"id\":0,\"lastName\":\"Patrique\",\"firstName\":\"Lemieux\",\"phoneNumber\":\"4383006589\",\"email\":\"lemieux@gmail.com\",\"entreprise\":\"SAP\",\"programId\":0}]"));
    }

    /**
     * Method under test: {@link UserController#getAllUsers()}
     */
    @Test
    void testGetAllUsers2() throws Exception {
        when(userService.getAllUsers()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

