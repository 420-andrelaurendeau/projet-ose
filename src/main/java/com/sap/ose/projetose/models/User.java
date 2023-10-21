package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dtos.UserDto;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User extends BaseModel {
    private String lastName;
    private String firstName;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @ToString.Exclude
    private String password;
    @ManyToOne
    @JoinColumn
    private StudyProgram studyProgram;

    public abstract UserDto toDto();
}