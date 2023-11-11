package com.sap.ose.projetose.service;


import com.sap.ose.projetose.repository.PDFRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PDFService.class})
@ExtendWith(SpringExtension.class)
public class PDFServiceTest {

    private PDFService PDFServiceTest;

    @Autowired
    private PDFService pdfService;

    @MockBean
    private PDFRepository pdfRepository;





}
