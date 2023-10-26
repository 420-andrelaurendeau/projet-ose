package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Interview;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InterviewDTO {
    private long id;
    private EtudiantDto student;
    private InternOfferDto internOffer;
    private Date date;
    private String description;

    //TODO changer le code pour qu'on ne use pas des Modeles dans les DTOs

    public InterviewDTO(EtudiantDto student, InternOfferDto internOffer, Date date, String description) {
        this.student = student;
        this.internOffer = internOffer;
        this.date = date;
        this.description = description;
    }

    public Interview fromDto(){
        return new Interview(id, student.fromDto(), internOffer.fromDto(), date, description);
    }

    @Override
    public String toString() {
        return "InterviewDTO{" +
                "id=" + id +
                ", student=" + student +
                ", employeur=" + internOffer +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
