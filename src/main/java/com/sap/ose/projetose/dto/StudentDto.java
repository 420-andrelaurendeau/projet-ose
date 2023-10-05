package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto extends UserDto {
    private String matricule;
    private long programme_id;
    private List<File> cv;
    private List<Long> internships_id;

    public StudentDto(String nom, String prenom, String phone, String email, String matricule, long programme, List<File> cv, List<Long> internships_id) {
        super(nom, prenom, phone, email);
        this.matricule = matricule;
        this.programme_id = programme;
        this.cv = cv;
        this.internships_id = internships_id;
    }
}
