package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@DiscriminatorColumn(name = "UTILISATEUR")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @Transient
    @ToString.Exclude
    private String password;

    public User(String lastName, String firstName, String phoneNumber, String email, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public User(String lastName, String firstName, String phoneNumber, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public abstract UserDto toUserDto();
}