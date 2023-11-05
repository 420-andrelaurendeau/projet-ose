package com.sap.ose.projetose.service;

import com.sap.ose.projetose.repository.StageRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StageService.class})
@ExtendWith(SpringExtension.class)
public class StageServiceTest {

    @Autowired
    private StageService stageService;

    @MockBean
    private StageRepository stageRepository;


}
