package com.sap.ose.projetose.dto;


import com.sap.ose.projetose.validators.NotBlankValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Validated
public class newEmployerDto extends newUserDto {
    @NotBlank(message = "Le nom d'entreprise ne peut pas être vide.")
    private String enterprise;
    private long studyProgramId;
}
