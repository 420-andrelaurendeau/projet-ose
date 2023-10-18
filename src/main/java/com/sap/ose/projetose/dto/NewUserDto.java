package com.sap.ose.projetose.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class NewUserDto {
    @NotBlank
    @Pattern(regexp = "[a-zA-Z]+")
    private String lastName;
    @NotBlank
    @Pattern(regexp = "[a-zA-Z]+")
    private String firstName;
    @NotBlank
    @Pattern(regexp = "[0-9]{3}-[0-9]{3}-[0-9]{4}")
    private String phoneNumber;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Min(5)
    private String password;
}
