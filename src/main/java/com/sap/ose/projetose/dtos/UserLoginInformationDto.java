package com.sap.ose.projetose.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginInformationDto {
    @NotBlank(message = "{user.email.notBlank}")
    @Email(message = "{user.email.malformed}")
    private String email;
    @NotBlank(message = "{user.password.notBlank}")
    private String password;
}
