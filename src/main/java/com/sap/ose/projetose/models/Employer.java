package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dto.EmployerDto;
import com.sap.ose.projetose.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employer extends User {
    private String enterprise;

    @Override
    public UserDto toUserDto() {
        return new EmployerDto(this);
    }
}
