package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.ContractDto;
import com.sap.ose.projetose.modeles.Stage;
import com.sap.ose.projetose.repository.Contract;
import com.sap.ose.projetose.repository.ContractRepository;
import jakarta.transaction.Transactional;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


@Service
public class ContractService {


    private final ContractRepository contractRepository;
    private final EmployeurService employeurService;
    private final InternOfferService internOfferService;
    private final EtudiantService studentService;


    @Autowired
    public ContractService(ContractRepository contractRepository, EmployeurService employeurService, InternOfferService internOfferService, EtudiantService studentService) {
        this.contractRepository = contractRepository;
        this.employeurService = employeurService;
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

            Contract contract = findById(contractDto.getId());
            contract.setContract(contractDto.getContract());
            contract.setSignatureInternShipManager(true);

            contractRepository.save(contract);

            return new ContractDto(contract);
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de sauvegarder le contrat");
        }
    }

    @Transactional
    public long saveContract(Contract contract) {
        try {
            Contract contractSaved = contractRepository.save(contract);
            return contractSaved.id;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de sauvegarder le contrat");
        }
    }


    @Transactional
    public long createContract(Stage stage) {
        try {
            // TODO changement de la valeur contrat
            Contract contract = new Contract(stage.getEmployeur(), stage.getStudent(), stage.getOffer(), false, false, false, "");
            return contractRepository.save(contract).getId();
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de sauvegarder le contrat");
        }
    }

    Contract findById(long id) {
        return contractRepository.findById(id).orElseThrow(() -> new IllegalStateException("Le contrat n'existe pas"));
    }

    public String addImageToExistingPDF(String base64Image, String inputPath) throws IOException {
        try (PDDocument document = PDDocument.load(new File(inputPath))) {
            PDPage page = new PDPage();
            document.addPage(page);

            // Charger l'image depuis la base64
            byte[] imageBytes = decodeBase64(base64Image);
            PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, imageBytes, "image");

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Ajustez les coordonnées et la taille de l'image selon vos besoins
                contentStream.drawImage(pdImage, 10, 10, 100, 100);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);

            byte[] pdfBytes = outputStream.toByteArray();
            String encodedPDF = Base64.encodeBase64String(pdfBytes);

            System.out.println(encodedPDF);

            document.close();
            return encodedPDF;

        }
    }


    public byte[] decodeBase64(String base64Image) throws IOException {
        // Recherchez la virgule pour obtenir la partie réelle des données base64
        int commaIndex = base64Image.indexOf(',');
        if (commaIndex < 0) {
            throw new IllegalArgumentException("La chaîne base64 ne contient pas de virgule.");
        }

        String base64Data = base64Image.substring(commaIndex + 1);

        // Décodez la chaîne base64 en un tableau de bytes
        return Base64.decodeBase64(base64Data);
    }


}
