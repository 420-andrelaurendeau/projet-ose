package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.InternshipManagerDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.InternshipManagerNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.models.InternshipManager;
import com.sap.ose.projetose.models.Program;
import com.sap.ose.projetose.repository.InternshipmanagerRepository;
import com.sap.ose.projetose.repository.StudyProgramRepository;
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

@ContextConfiguration(classes = {InternshipManagerService.class})
@ExtendWith(SpringExtension.class)
public class InternshipManagerServiceTest {

    private final InternshipManagerDto internshipmanagerDto = new InternshipManagerDto();
    @Autowired
    private InternshipManagerService internshipmanagerService;
    @MockBean
    private InternshipmanagerRepository internshipmanagerRepository;
    @MockBean
    private StudyProgramService studyProgramService;
    @MockBean
    private StudyProgramRepository studyProgramRepository;

    @BeforeEach
    public void setUp() {
        this.internshipmanagerDto.setId(0);
        this.internshipmanagerDto.setProgramId(1);
        this.internshipmanagerDto.setLastName("Nom");
        this.internshipmanagerDto.setFirstName("Prenom");
        this.internshipmanagerDto.setEmail("Email");
        this.internshipmanagerDto.setPhoneNumber("Email");
    }


    @Test
    public void findById_Success() {
        InternshipManager mockIntern = internshipmanagerDto.toInternshipManager();
        when(internshipmanagerRepository.findById(anyLong())).thenReturn(Optional.of(mockIntern));

        InternshipManager result = internshipmanagerService.findInternshipManagerById(anyLong());

        Assertions.assertEquals(mockIntern, result);
    }

    @Test
    public void findById_NotFound() {
        when(internshipmanagerRepository.findById(anyLong())).thenReturn(Optional.empty());

        InternshipManagerNotFoundException reslut = assertThrows(InternshipManagerNotFoundException.class, () -> internshipmanagerService.findInternshipManagerById(anyLong()));
        assertEquals("Gestionnaire de stage non trouvé.", reslut.getMessage());
    }

    @Test
    public void findById_DataAccessError() {
        when(internshipmanagerRepository.findById(anyLong())).thenThrow(new DataAccessException("") {
        });

        DatabaseException result = assertThrows(DatabaseException.class, () -> internshipmanagerService.findInternshipManagerById(anyLong()));
        assertEquals("Erreur d'accès a la base de  données lors de la récupération du gestionnaire de stage", result.getMessage());
    }

    @Test
    public void findById_UnknownError() {
        when(internshipmanagerRepository.findById(anyLong())).thenThrow(new NullPointerException(""));

        ServiceException result = assertThrows(ServiceException.class, () -> internshipmanagerService.findInternshipManagerById(anyLong()));
        assertEquals("Erreur inconnue lors de la récupération du gestionnaire de stage", result.getMessage());
    }

    @Test
    public void getById_Success() {
        Program mockProgram = new Program(1L, "ds", "description");
        InternshipManager mockIntern = internshipmanagerDto.toInternshipManager();
        mockIntern.setProgram(mockProgram);

        when(internshipmanagerRepository.findById(anyLong())).thenReturn(Optional.of(mockIntern));

        InternshipManagerDto result = internshipmanagerService.getInternshipManagerById(anyLong());
        assertEquals(new InternshipManagerDto(mockIntern), result);
    }

    @Test
    public void getById_NotFound() {
        when(internshipmanagerRepository.findById(anyLong())).thenReturn(Optional.empty());

        InternshipManagerNotFoundException reslut = assertThrows(InternshipManagerNotFoundException.class, () -> internshipmanagerService.getInternshipManagerById(anyLong()));
        assertEquals("Gestionnaire de stage non trouvé.", reslut.getMessage());
    }

    @Test
    public void getById_DataAccessError() {
        when(internshipmanagerRepository.findById(anyLong())).thenThrow(new DataAccessException("") {
        });

        DatabaseException result = assertThrows(DatabaseException.class, () -> internshipmanagerService.getInternshipManagerById(anyLong()));
        assertEquals("Erreur d'accès a la base de  données lors de la récupération du gestionnaire de stage", result.getMessage());
    }

    @Test
    public void getById_UnknownError() {
        when(internshipmanagerRepository.findById(anyLong())).thenThrow(new NullPointerException(""));

        ServiceException result = assertThrows(ServiceException.class, () -> internshipmanagerService.getInternshipManagerById(anyLong()));
        assertEquals("Erreur inconnue lors de la récupération du gestionnaire de stage", result.getMessage());
    }


}
