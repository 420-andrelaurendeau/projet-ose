package com.sap.ose.projetose.models;

import com.sap.ose.projetose.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "UTILISATEUR")
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
    private String nom;
    private String prenom;
    private String phone;
    @Column(unique = true)
    private String email;
    @Getter(AccessLevel.NONE)
    @ToString.Exclude
    private String password;
    @ManyToOne
    @JoinColumn(name = "programme_id")
    private Formation formation;

    public User(String nom, String prenom, String email, String password, String phone, Formation formation) {
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.formation = formation;
    }

    public abstract UserDto toUserDto();
}