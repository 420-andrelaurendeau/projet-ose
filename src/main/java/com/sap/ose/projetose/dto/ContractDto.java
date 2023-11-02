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
    @Lob
    public String signatureInternShipManager;
    @Lob
    public String signatureEmployer;
    @Lob
    public String signatureStudent;
    public String contract;

}
