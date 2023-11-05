package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.repository.Contract;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    public String contract;

    public ContractDto(Contract contract) {
        this.id = contract.getId();
        this.employeur = new EmployeurDto(contract.getEmployeur());
        this.etudiantDto = new EtudiantDto(contract.getStudent());
        this.internOfferDto = new InternOfferDto(contract.getInternOffer());
        this.signatureInternShipManager = contract.isSignatureInternShipManager();
        this.signatureEmployer = contract.isSignatureEmployer();
        this.signatureStudent = contract.isSignatureStudent();
        this.contract = contract.getContract();
    }
}
