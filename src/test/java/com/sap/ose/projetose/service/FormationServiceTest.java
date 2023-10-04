package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.FormationDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.ProgramNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.models.Formation;
import com.sap.ose.projetose.repository.FormationRepository;
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

@ContextConfiguration(classes = {FormationService.class})
@ExtendWith(SpringExtension.class)
class FormationServiceTest {

    private final FormationDto formationDto = new FormationDto();
    @Autowired
    private FormationService formationService;
    @MockBean
    private FormationRepository formationRepository;

    @BeforeEach
    public void setUp() {
        this.formationDto.setId(1L);
        this.formationDto.setNom("Nom");
        this.formationDto.setDescription("Description");
    }

    @Test
    public void findById_Success() {
        Formation mockprog = formationDto.fromDto();
        when(formationRepository.findById(anyLong())).thenReturn(Optional.of(mockprog));

        Formation result = formationService.findById(anyLong());

        Assertions.assertEquals(mockprog, result);
    }

    @Test
    public void findById_NotFound() {
        when(formationRepository.findById(anyLong())).thenReturn(Optional.empty());

        ProgramNotFoundException result = assertThrows(ProgramNotFoundException.class, () -> formationService.findById(anyLong()));
        assertEquals("Formation non trouvé", result.getMessage());
    }

    @Test
    public void findById_DataAccessError() {
        when(formationRepository.findById(anyLong())).thenThrow(new DataAccessException("") {
        });

        DatabaseException result = assertThrows(DatabaseException.class, () -> formationService.findById(anyLong()));
        assertEquals("Erreur lors de la récupération du programme", result.getMessage());
    }

    @Test
    public void findById_UnknownError() {
        when(formationRepository.findById(anyLong())).thenThrow(new RuntimeException("Test exception"));

        ServiceException result = assertThrows(ServiceException.class, () -> formationService.findById(anyLong()));
        assertEquals("Erreur lors de la récupération du programme", result.getMessage());
    }

}
