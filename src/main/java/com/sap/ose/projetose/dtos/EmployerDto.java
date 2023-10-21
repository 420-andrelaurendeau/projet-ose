package com.sap.ose.projetose.dtos;

import com.sap.ose.projetose.models.Employer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployerDto extends UserDto {
    private String enterprise;

    public EmployerDto(Employer employer) {
        super(employer);
        this.enterprise = employer.getEnterprise();
    }
}
