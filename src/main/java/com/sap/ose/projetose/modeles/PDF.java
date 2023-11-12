package com.sap.ose.projetose.modeles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PDF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    LocalDate createdDate;
    Boolean isActive;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private File file;

    public PDF(File file) {
        this.file = file;
        this.createdDate = LocalDate.now();
        this.isActive = true;
    }
}
