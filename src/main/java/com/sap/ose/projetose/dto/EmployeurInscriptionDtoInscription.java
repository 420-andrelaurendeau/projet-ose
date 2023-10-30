package com.sap.ose.projetose.dto;


import com.sap.ose.projetose.annotations.StudyProgramExists;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeurInscriptionDtoInscription extends UserInscriptionDto {
    @NotBlank(message = "{employer.enterprise.notBlank}")
    private String enterprise;
    @StudyProgramExists
    private Set<Long> studyProgramId;
}
