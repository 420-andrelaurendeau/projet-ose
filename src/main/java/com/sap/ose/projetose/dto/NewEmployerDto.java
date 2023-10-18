package com.sap.ose.projetose.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NewEmployerDto extends NewUserDto {
    @NotBlank
    private String enterprise;
    private long studyProgramId;
}
