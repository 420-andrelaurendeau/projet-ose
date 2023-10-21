package com.sap.ose.projetose.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class NewUserDto {
    @NotBlank(message = "{user.lastName.notBlank}")
    @Pattern(regexp = "[a-zA-Z]+")
    private String lastName;
    @NotBlank(message = "{user.firstName.notBlank}")
    @Pattern(regexp = "[a-zA-Z]+")
    private String firstName;
    @NotBlank(message = "{user.phoneNumber.notBlank}")
    @Pattern(regexp = "[0-9]{3}-[0-9]{3}-[0-9]{4}")
    private String phoneNumber;
    @NotNull(message = "{user.email.notBlank}")
    @Email(message = "{user.email.malformed}")
    private String email;
    @NotBlank(message = "{user.password.notBlank}")
    @Min(5)
    private String password;
}
