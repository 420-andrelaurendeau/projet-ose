package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternshipmanagerDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.InternshipmanagerNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Internshipmanager;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {InternshipmanagerService.class})
@ExtendWith(SpringExtension.class)
public class InternshipmanagerServiceTest {

    @Autowired
    private InternshipmanagerService internshipmanagerService;

    @MockBean
    private InternshipmanagerRepository internshipmanagerRepository;
    @MockBean
    private ProgrammeService programmeService;
    @MockBean
    private ProgrammeRepository programmeRepository;
    private final InternshipmanagerDto internshipmanagerDto = new InternshipmanagerDto();


    @BeforeEach
    public void setUp() {
        this.internshipmanagerDto.setId(0);
        this.internshipmanagerDto.setProgrammeId(1);
        this.internshipmanagerDto.setNom("Nom");
        this.internshipmanagerDto.setPrenom("Prenom");
        this.internshipmanagerDto.setEmail("Email");
        this.internshipmanagerDto.setPhone("Email");
    }


    @Test
    public void findById_Success() {
        Internshipmanager mockIntern = internshipmanagerDto.fromDto();
        when(internshipmanagerRepository.findById(anyLong())).thenReturn(Optional.of(mockIntern));

        Internshipmanager result = internshipmanagerService.findById(anyLong());

        Assertions.assertEquals(mockIntern, result);
    }

    @Test
    public void findById_NotFound() {
        when(internshipmanagerRepository.findById(anyLong())).thenReturn(Optional.empty());

        InternshipmanagerNotFoundException reslut = assertThrows(InternshipmanagerNotFoundException.class, () -> internshipmanagerService.findById(anyLong()));
        assertEquals("Gestionnaire de stage non trouvé.", reslut.getMessage());
    }

    @Test
    public void findById_DataAccessError() {
        when(internshipmanagerRepository.findById(anyLong())).thenThrow(new DataAccessException("") {
        });

        DatabaseException result = assertThrows(DatabaseException.class, () -> internshipmanagerService.findById(anyLong()));
        assertEquals("Erreur d'accès a la base de  données lors de la récupération du gestionnaire de stage", result.getMessage());
    }

    @Test
    public void findById_UnknownError() {
        when(internshipmanagerRepository.findById(anyLong())).thenThrow(new NullPointerException(""));

        ServiceException result = assertThrows(ServiceException.class, () -> internshipmanagerService.findById(anyLong()));
        assertEquals("Erreur inconnue lors de la récupération du gestionnaire de stage", result.getMessage());
    }

    @Test
    public void getById_Success() {
        Programme mockProgram = new Programme(1L, "ds", "description");
        Internshipmanager mockIntern = internshipmanagerDto.fromDto();
        mockIntern.setProgramme(mockProgram);

        when(internshipmanagerRepository.findById(anyLong())).thenReturn(Optional.of(mockIntern));

        InternshipmanagerDto result = internshipmanagerService.getById(anyLong());
        assertEquals(new InternshipmanagerDto(mockIntern), result);
    }

    @Test
    public void getById_NotFound() {
        when(internshipmanagerRepository.findById(anyLong())).thenReturn(Optional.empty());

        InternshipmanagerNotFoundException reslut = assertThrows(InternshipmanagerNotFoundException.class, () -> internshipmanagerService.getById(anyLong()));
        assertEquals("Gestionnaire de stage non trouvé.", reslut.getMessage());
    }

    @Test
    public void getById_DataAccessError() {
        when(internshipmanagerRepository.findById(anyLong())).thenThrow(new DataAccessException("") {
        });

        DatabaseException result = assertThrows(DatabaseException.class, () -> internshipmanagerService.getById(anyLong()));
        assertEquals("Erreur d'accès a la base de  données lors de la récupération du gestionnaire de stage", result.getMessage());
    }

    @Test
    public void getById_UnknownError() {
        when(internshipmanagerRepository.findById(anyLong())).thenThrow(new NullPointerException(""));

        ServiceException result = assertThrows(ServiceException.class, () -> internshipmanagerService.getById(anyLong()));
        assertEquals("Erreur inconnue lors de la récupération du gestionnaire de stage", result.getMessage());
    }


}
