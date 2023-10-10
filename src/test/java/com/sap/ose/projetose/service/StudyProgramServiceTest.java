package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.StudyProgramDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.ProgramNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.models.Program;
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

@ContextConfiguration(classes = {StudyProgramService.class})
@ExtendWith(SpringExtension.class)
class StudyProgramServiceTest {

    private final StudyProgramDto studyProgramDto = new StudyProgramDto();
    @Autowired
    private StudyProgramService studyProgramService;
    @MockBean
    private StudyProgramRepository studyProgramRepository;

    @BeforeEach
    public void setUp() {
        this.studyProgramDto.setId(1L);
        this.studyProgramDto.setName("Nom");
        this.studyProgramDto.setDescription("Description");
    }

    @Test
    public void findById_Success() {
        Program mockprog = studyProgramDto.toNewProgram();
        when(studyProgramRepository.findById(anyLong())).thenReturn(Optional.of(mockprog));

        Program result = studyProgramService.findById(anyLong());

        Assertions.assertEquals(mockprog, result);
    }

    @Test
    public void findById_NotFound() {
        when(studyProgramRepository.findById(anyLong())).thenReturn(Optional.empty());

        ProgramNotFoundException result = assertThrows(ProgramNotFoundException.class, () -> studyProgramService.findById(anyLong()));
        assertEquals("Programme non trouvé", result.getMessage());
    }

    @Test
    public void findById_DataAccessError() {
        when(studyProgramRepository.findById(anyLong())).thenThrow(new DataAccessException("") {
        });

        DatabaseException result = assertThrows(DatabaseException.class, () -> studyProgramService.findById(anyLong()));
        assertEquals("Erreur lors de la récupération du programme", result.getMessage());
    }

    @Test
    public void findById_UnknownError() {
        when(studyProgramRepository.findById(anyLong())).thenThrow(new RuntimeException("Test exception"));

        ServiceException result = assertThrows(ServiceException.class, () -> studyProgramService.findById(anyLong()));
        assertEquals("Erreur lors de la récupération du programme", result.getMessage());
    }

}
