package com.sap.ose.projetose.service;

import com.sap.ose.projetose.exception.BadSortingFieldException;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.InvalidStateException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.repository.StageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


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



}
