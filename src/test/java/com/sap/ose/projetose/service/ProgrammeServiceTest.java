package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.ProgrammeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ProgrammeService.class})
@ExtendWith(SpringExtension.class)
class ProgrammeServiceTest {


    private static final long VALID_ID = 1L;
    private static final long INVALID_ID = 99L;
    @Autowired
    private ProgrammeService programmeService;
    @MockBean
    private ProgrammeRepository programmeRepository;
    private final ProgrammeDto programmeDto = new ProgrammeDto();

    @BeforeEach
    public void setUp() {
        this.programmeDto.setId(VALID_ID);
        this.programmeDto.setNom("Nom");
        this.programmeDto.setDescription("Description");
    }

    @Test
    public void findById_Success() {
        Programme mockprog = programmeDto.fromDto();
        when(programmeRepository.findById(VALID_ID)).thenReturn(Optional.of(mockprog));

        Programme result = programmeService.findById(VALID_ID);

        Assertions.assertEquals(mockprog, result);
    }

    @Test
    public void getById_NotFound() {
        when(programmeRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        assertThrows(EmptyResultDataAccessException.class, () -> programmeService.findById(INVALID_ID));
    }

    @Test
    public void getById_DataIntegrityViolation() {
        when(programmeRepository.findById(anyLong())).thenThrow(new DataIntegrityViolationException("Test exception"));

        assertThrows(DataIntegrityViolationException.class, () -> programmeService.findById(VALID_ID));
    }

    @Test
    public void getById_DataAccessError() {
        when(programmeRepository.findById(anyLong())).thenThrow(new DataAccessException("Test exception") {
        });

        assertThrows(DataAccessException.class, () -> programmeService.findById(VALID_ID));
    }

    @Test
    public void getById_UnknownError() {
        when(programmeRepository.findById(anyLong())).thenThrow(new RuntimeException("Test exception"));

        assertThrows(RuntimeException.class, () -> programmeService.findById(VALID_ID));
    }

}
