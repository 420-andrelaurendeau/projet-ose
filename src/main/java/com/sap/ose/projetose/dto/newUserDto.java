package com.sap.ose.projetose.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@Validated
public abstract class newUserDto {
    @NotBlank(message = "Le nom ne peut pas être vide.")
    @Pattern(regexp = "[a-zA-Z]+")
    private String lastName;
    @NotBlank(message = "Le prénom ne peut pas être vide.")
    @Pattern(regexp = "[a-zA-Z]+")
    private String firstName;
    @NotBlank(message = "Le numéro de téléphone ne peut pas être vide.")
    @Pattern(regexp = "[0-9]{3}-[0-9]{3}-[0-9]{4}")
    private String phoneNumber;
    @NotBlank(message = "L'adresse email ne peut pas être vide.")
    @Email
    private String email;
    @NotBlank(message = "Le mot de passe ne peut pas être vide.")
    @Min(5)
    private String password;
}
