package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.service.EtudiantService;
import com.sap.ose.projetose.service.StageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StageController.class})
@ExtendWith(SpringExtension.class)
public class StageControllerTest {

    @Autowired
    private StageController stageController;

    @MockBean
    private StageService stageService;

}


