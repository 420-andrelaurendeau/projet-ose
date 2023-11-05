package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.ContractDto;
import com.sap.ose.projetose.modeles.Stage;
import com.sap.ose.projetose.repository.Contract;
import com.sap.ose.projetose.repository.ContractRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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


}
