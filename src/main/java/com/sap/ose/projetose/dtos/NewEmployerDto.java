package com.sap.ose.projetose.dtos;


import com.sap.ose.projetose.annotations.StudyProgramExists;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NewEmployerDto extends NewUserDto {
    @NotBlank(message = "{employer.enterprise.notBlank}")
    private String enterprise;
    @StudyProgramExists
    private long studyProgramId;
}
