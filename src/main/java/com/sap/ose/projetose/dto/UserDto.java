package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class UserDto {
    private long id;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String email;

    public UserDto(int id, String lastName, String firstName, String phone, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phone;
        this.email = email;
        this.id = id;
    }

    public UserDto(String firstName, String lastName, String phone, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phone;
        this.email = email;
    }

    public abstract User toUser();
}
