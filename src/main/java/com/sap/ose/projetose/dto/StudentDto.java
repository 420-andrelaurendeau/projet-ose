package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.models.Student;
import com.sap.ose.projetose.models.File;
import com.sap.ose.projetose.models.InternshipCandidates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    public StudentDto(Student etudiant) {
        super(etudiant.getNom(), etudiant.getPrenom(), etudiant.getPhone(), etudiant.getEmail());
        this.matricule = etudiant.getMatricule();
        this.programme_id = etudiant.getFormation().getId();
        this.cv = etudiant.getCv();
        this.internships_id = etudiant.getInternshipsCandidate().stream().map(InternshipCandidates::getId).toList();
    }

    public Student toStudent() {
        return new Student(getNom(), getPrenom(), getPhone(), getEmail(), this.matricule, new ArrayList<File>(),null, null);
    }

}
