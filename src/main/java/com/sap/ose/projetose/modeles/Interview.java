package com.sap.ose.projetose.modeles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Etudiant student;

    @ManyToOne()
    @JoinColumn(name = "Internshipoffer_id")
    private InternOffer internshipOffer;

    private Date date;

    private String description;
    @Override
    public String toString() {
        return "Interview{" +
                "id=" + id +
                ", etudiant_id=" + student.getId() +
                ", employeur_id=" + internshipOffer.getId() +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
