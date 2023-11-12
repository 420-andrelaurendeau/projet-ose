package com.sap.ose.projetose.service;

import com.sap.ose.projetose.controller.ReactOseController;
import com.sap.ose.projetose.dto.ContractDto;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.ContractRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;


@Service
public class ContractService {


    private final ContractRepository contractRepository;
    private final EmployeurService employeurService;

    private final PDFService pdfService;

    private final FileService fileService;
    private final InternOfferService internOfferService;
    private final EtudiantService studentService;

    Logger logger = LoggerFactory.getLogger(ContractService.class);


    @Autowired
    public ContractService(ContractRepository contractRepository, EmployeurService employeurService, PDFService pdfService, FileService fileService, InternOfferService internOfferService, EtudiantService studentService) {
        this.contractRepository = contractRepository;
        this.employeurService = employeurService;
        this.pdfService = pdfService;
        this.fileService = fileService;
        this.internOfferService = internOfferService;
        this.studentService = studentService;
    }

    @Transactional
    public ContractDto getById(long id) {
        Contract contract = contractRepository.findById(id).orElseThrow(() -> new IllegalStateException("Le contrat n'existe pas"));
        return new ContractDto(contract);
    }


    @Transactional
    public ContractDto saveContractDto(ContractDto contractDto) {
        try {

            File file = fileService.findById(contractDto.getContractId());
            byte[] encodedString = Base64.getEncoder().encode(contractDto.getContractContent().getBytes());
            file.setContent(encodedString);

            Contract contract = findById(contractDto.getId());
            contract.setFile(file);
            contract.setSignatureInternShipManager(true);

            contractRepository.save(contract);

            return new ContractDto(contract);
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de sauvegarder le contrat");
        }
    }

    @Transactional
    public long createContract(Stage stage) {
        try {

            PDF contractPDF = pdfService.findCurrent();

            File file = new File(contractPDF.getFile().getContent(), contractPDF.getFile().getFileName(), State.ACCEPTED, null);
            System.out.println("BONJOURfdfsfdsfdf");
            Contract contract = new Contract(stage.getEmployeur(), stage.getStudent(), stage.getOffer(), false, false, false, file);

            return contractRepository.save(contract).getId();
        } catch (Exception e) {
            logger.error("Erreur lors de la création du contrat", e);
            throw new IllegalStateException("Impossible de sauvegarder le contrat");
        }
    }

    Contract findById(long id) {
        return contractRepository.findById(id).orElseThrow(() -> new IllegalStateException("Le contrat n'existe pas"));
    }


}
