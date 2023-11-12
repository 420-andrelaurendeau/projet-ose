package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.StageDto;
import com.sap.ose.projetose.exception.*;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ContextConfiguration(classes = {StageService.class})
@ExtendWith(SpringExtension.class)
public class StageServiceTest {

    @Autowired
    private StageService stageService;

    @MockBean
    private StageRepository stageRepository;

    @MockBean
    private InternOfferService internOfferService;

    @MockBean
    private EtudiantService etudiantService;

    @MockBean
    private ContractService contractService;

    @Test
    public void getCountByState_DataAccessException() {
        when(stageRepository.getCountByState()).thenThrow(new DataAccessException("") {
        });
        DatabaseException result = assertThrows(DatabaseException.class, () -> stageService.getCountByState());
        assertEquals("Erreur d'accès a la base de données", result.getMessage());
    }

    @Test
    public void getCountByState_ServiceException() {
        when(stageRepository.getCountByState()).thenThrow(new ServiceException(""));
        ServiceException result = assertThrows(ServiceException.class, () -> stageService.getCountByState());
        assertEquals("Erreur lors de la récupération du compte des ententes de stages.", result.getMessage());
    }

    @Test
    public void getSortedByPage_IllegalStateException() {
        InvalidStateException result = assertThrows(InvalidStateException.class, () -> stageService.getSortedByPage(1, 1, Sort.by("title"), "wrongState"));
        assertEquals("Cette état n'est pas valide : wrongState", result.getMessage());
    }


    @Test
    public void getSortedByPage_DatabaseException() {
        Pageable pageable = PageRequest.of(1, 1, Sort.by("title"));
        when(stageRepository.findAll(pageable)).thenThrow(new DataAccessException("") {
        });

        DatabaseException result = assertThrows(DatabaseException.class, () -> stageService.getSortedByPage(1, 1, Sort.by("title"), null));
        assertEquals("Erreur d'accès a la base de données", result.getMessage());
    }

    @Test
    public void getSortedByPage_ServiceException() {
        Pageable pageable = PageRequest.of(1, 1, Sort.by("title"));
        when(stageRepository.findAll(pageable)).thenThrow(RuntimeException.class);

        ServiceException result = assertThrows(ServiceException.class, () -> stageService.getSortedByPage(1, 1, Sort.by("title"), null));
        assertEquals("Erreur lors de la récupération des offres d'emploi.", result.getMessage());
    }

    @Test
    public void getSortedByPage_BadSortedField() {

        when(stageRepository.findAll(any(Pageable.class))).thenThrow(PropertyReferenceException.class);
        BadSortingFieldException result = assertThrows(BadSortingFieldException.class, () -> stageService.getSortedByPage(1, 1, Sort.by("wrongField"), null));
        assertEquals("Le champ wrongField: ASC n'existe pas", result.getMessage());
    }

    @Test
    void isContractAccepted_ReturnsTrue_WhenRepositoryConfirms() {
        when(stageRepository.isContractAccepted(anyLong())).thenReturn(true);
        boolean actualIsContractAcceptedResult = stageService.isContractAccepted(1L);
        verify(stageRepository).isContractAccepted(anyLong());
        assertTrue(actualIsContractAcceptedResult);
    }

    @Test
    void isContractAccepted_ReturnsFalse_WhenRepositoryDenies() {
        when(stageRepository.isContractAccepted(anyLong())).thenReturn(false);
        boolean actualIsContractAcceptedResult = stageService.isContractAccepted(1L);
        verify(stageRepository).isContractAccepted(anyLong());
        assertFalse(actualIsContractAcceptedResult);
    }

    @Test
    void isContractAccepted_ThrowsServiceException_OnIllegalArgumentException() {
        when(stageRepository.isContractAccepted(anyLong())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(ServiceException.class, () -> stageService.isContractAccepted(1L));
        verify(stageRepository).isContractAccepted(anyLong());
    }

    @Test
    void isContractAccepted_ThrowsDatabaseException_OnEmptyResultDataAccessException() {
        when(stageRepository.isContractAccepted(anyLong())).thenThrow(new EmptyResultDataAccessException(3));
        assertThrows(DatabaseException.class, () -> stageService.isContractAccepted(1L));
        verify(stageRepository).isContractAccepted(anyLong());
    }

    @Test
    void saveEmployerOpinion_ThrowsIllegalArgumentException_OnRepositoryFindByIdFailure() {
        when(stageRepository.findById(Mockito.<Long>any()))
                .thenThrow(new IllegalArgumentException("Erreur lors de la récupération des offres d'emploi."));
        assertThrows(IllegalArgumentException.class, () -> stageService.saveEmployerOpinion(anyLong(), "DECLINED"));
        verify(stageRepository).findById(Mockito.<Long>any());
    }

    @Test
    void saveEmployerOpinion_ThrowsIllegalArgumentException_OnRepositoryFindByIdError() {
        when(stageRepository.findById(Mockito.<Long>any()))
                .thenThrow(new IllegalArgumentException("Erreur lors de la récupération des offres d'emploi."));
        StageDto stageDto = mock(StageDto.class);
        when(stageDto.getId()).thenReturn(1L);
        assertThrows(IllegalArgumentException.class, () -> stageService.saveEmployerOpinion(stageDto.getId(), "DECLINED"));
        verify(stageDto).getId();
        verify(stageRepository).findById(Mockito.<Long>any());
    }

    @Test
    void saveEmployerOpinion_ThrowsIllegalArgumentException_OnStageDtoIdError() {
        StageDto stageDto = mock(StageDto.class);
        when(stageDto.getId())
                .thenThrow(new IllegalArgumentException("Erreur d'argument avec l'état de l'employeur sur l'entente de stage"));
        assertThrows(IllegalArgumentException.class, () -> stageService.saveEmployerOpinion(stageDto.getId(), "DECLINED"));
        verify(stageDto).getId();
    }


}
