package com.sap.ose.projetose.dto;

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
    public long idStage;
    public long idEmployer;
    public long idStudent;
    public long idInternOffer;
    public boolean signatureInternShipManager;
    public boolean signatureEmployer;
    public boolean signatureStudent;
    @Lob
    public String contract;

}
