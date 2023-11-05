package com.sap.ose.projetose.service;

import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.repository.StageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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


}
