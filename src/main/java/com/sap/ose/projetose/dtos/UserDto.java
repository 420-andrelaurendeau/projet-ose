package com.sap.ose.projetose.dtos;

import com.sap.ose.projetose.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class UserDto {
    private long id;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String email;

    public UserDto(User user) {
        setId(user.getId());
        setLastName(user.getLastName());
        setFirstName(user.getFirstName());
        setPhoneNumber(user.getPhoneNumber());
        setEmail(user.getEmail());
    }
}
