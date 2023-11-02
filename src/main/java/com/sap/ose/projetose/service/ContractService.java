package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.ContractDto;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Stage;
import com.sap.ose.projetose.repository.Contract;
import com.sap.ose.projetose.repository.ContractRepository;
import jakarta.transaction.Transactional;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;


@Service
public class ContractService {


    private final ContractRepository contractRepository;
    private final EmployeurService employeurService;
    private final InternOfferService internOfferService;
    private final EtudiantService studentService;

    private final StageService stageService;

    @Autowired
    public ContractService(ContractRepository contractRepository, EmployeurService employeurService, InternOfferService internOfferService, EtudiantService studentService, StageService stageService) {
        this.contractRepository = contractRepository;
        this.employeurService = employeurService;
        this.internOfferService = internOfferService;
        this.studentService = studentService;
        this.stageService = stageService;
    }




    @Transactional
    public ContractDto saveContract(ContractDto contractDto) {
        try {
            Etudiant student = studentService.findEtudiantById(contractDto.getIdStudent());
            Employeur employeur = employeurService.findById(contractDto.getIdEmployer());
            InternOffer internOffer = internOfferService.findById(contractDto.getIdInternOffer());
            Stage stage = stageService.getById(contractDto.getIdStage());

            String base64Contract = addImageToExistingPDF(contractDto.signatureInternShipManager, "src/main/java/com/sap/ose/projetose/sodapdf-converted.pdf");
            System.out.println(base64Contract);

            Contract contract = contractRepository.save(new Contract(stage, employeur, student, internOffer, contractDto.signatureInternShipManager, null, null, base64Contract));
            return new ContractDto(contract.getId(), contract.getStage().getId(), contract.getEmployeur().getId(), contract.getStudent().getId(), contract.getInternOffer().getId(), contract.getSignatureInternShipManager(), contract.getSignatureEmployer(), contract.getSignatureStudent(), contract.getContract());
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de sauvegarder le contrat");
        }
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
