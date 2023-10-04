package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.ProgramNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.Programme;
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

@ContextConfiguration(classes = {ProgrammeService.class})
@ExtendWith(SpringExtension.class)
class ProgrammeServiceTest {

    private final ProgrammeDto programmeDto = new ProgrammeDto();
    @Autowired
    private ProgrammeService programmeService;
    @MockBean
    private ProgrammeRepository programmeRepository;

    @BeforeEach
    public void setUp() {
        this.programmeDto.setId(1L);
        this.programmeDto.setNom("Nom");
        this.programmeDto.setDescription("Description");
    }

    @Test
    public void findById_Success() {
        Programme mockprog = programmeDto.fromDto();
        when(programmeRepository.findById(anyLong())).thenReturn(Optional.of(mockprog));

        Programme result = programmeService.findById(anyLong());

        Assertions.assertEquals(mockprog, result);
    }

    @Test
    public void findById_NotFound() {
        when(programmeRepository.findById(anyLong())).thenReturn(Optional.empty());

        ProgramNotFoundException result = assertThrows(ProgramNotFoundException.class, () -> programmeService.findById(anyLong()));
        assertEquals("Programme non trouvé", result.getMessage());
    }

    @Test
    public void findById_DataAccessError() {
        when(programmeRepository.findById(anyLong())).thenThrow(new DataAccessException("") {
        });

        DatabaseException result = assertThrows(DatabaseException.class, () -> programmeService.findById(anyLong()));
        assertEquals("Erreur lors de la récupération du programme", result.getMessage());
    }

    @Test
    public void findById_UnknownError() {
        when(programmeRepository.findById(anyLong())).thenThrow(new RuntimeException("Test exception"));

        ServiceException result = assertThrows(ServiceException.class, () -> programmeService.findById(anyLong()));
        assertEquals("Erreur lors de la récupération du programme", result.getMessage());
    }

}
