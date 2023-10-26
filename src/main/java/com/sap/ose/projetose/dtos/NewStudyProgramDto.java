package com.sap.ose.projetose.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewStudyProgramDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
