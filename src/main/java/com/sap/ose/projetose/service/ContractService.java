package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.ContractDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.ContractRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;


@Service
public class ContractService {


    private final ContractRepository contractRepository;
    private final EmployeurService employeurService;

    private final TemplateContractService templateContractService;

    private final FileService fileService;
    private final InternOfferService internOfferService;
    private final EtudiantService studentService;

    Logger logger = LoggerFactory.getLogger(ContractService.class);


    @Autowired
    public ContractService(ContractRepository contractRepository, EmployeurService employeurService, TemplateContractService templateContractService, FileService fileService, InternOfferService internOfferService, EtudiantService studentService) {
        this.contractRepository = contractRepository;
        this.employeurService = employeurService;
        this.templateContractService = templateContractService;
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
    public ContractDto saveContractStudentDto(ContractDto contractDto) {
        try {

            File file = fileService.findById(contractDto.getContractId());
            byte[] encodedString = Base64.getEncoder().encode(contractDto.getContractContent().getBytes());
            file.setContent(encodedString);

            Contract contract = findById(contractDto.getId());
            contract.setFile(file);
            contract.setSignatureStudent(true);

            contractRepository.save(contract);

            return new ContractDto(contract);
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de sauvegarder le contrat");
        }
    }

    @Transactional
    public ContractDto saveContractEmployerDto(ContractDto contractDto) {
        try {

            File file = fileService.findById(contractDto.getContractId());
            byte[] encodedString = Base64.getEncoder().encode(contractDto.getContractContent().getBytes());
            file.setContent(encodedString);

            Contract contract = findById(contractDto.getId());
            contract.setFile(file);
            contract.setSignatureEmployer(true);

            contractRepository.save(contract);

            return new ContractDto(contract);
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de sauvegarder le contrat");
        }
    }

    @Transactional
    public long createContract(Stage stage) {
        try {

            TemplateContract contractTemplateContract = templateContractService.findCurrent();

            File file = new File(contractTemplateContract.getFile().getContent(), contractTemplateContract.getFile().getFileName(), State.ACCEPTED, null);
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

    public List<ContractDto> getAllByStudentID(long id) {
        try {
            return contractRepository.findAllByStudentId(id).stream().map(ContractDto::new).toList();
        } catch (DataAccessException e) {
            logger.error("Erreur lors de la récupération des contrats", e);
            throw new DatabaseException("");
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des contrats", e);
            throw new ServiceException("");
        }
    }


}
