package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Contract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {

    public long id;
    public EmployeurDto employeur;
    public EtudiantDto etudiantDto;
    public InternOfferDto internOfferDto;
    public boolean signatureInternShipManager;
    public boolean signatureEmployer;
    public boolean signatureStudent;
    public long fileId;
    public String content;
    public String fileName;


    public ContractDto(Contract contract) {
        this.id = contract.getId();
        this.employeur = new EmployeurDto(contract.getEmployeur());
        this.etudiantDto = new EtudiantDto(contract.getStudent());
        this.internOfferDto = new InternOfferDto(contract.getInternOffer());
        this.signatureInternShipManager = contract.isSignatureInternShipManager();
        this.signatureEmployer = contract.isSignatureEmployer();
        this.signatureStudent = contract.isSignatureStudent();
        this.content = Base64.getEncoder().encodeToString(contract.getFile().getContent());
        this.fileName = contract.getFile().getFileName();
    }
}
