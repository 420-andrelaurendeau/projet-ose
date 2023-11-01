package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.modeles.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InterviewRequestInDto {
    private long id;
    private long studentId;
    private long internOfferId;
    private Date date;
    private String description;
    private State state;

    public InterviewRequestInDto(long studentId, long internOfferId, Date date, String description, State state) {
        this.studentId = studentId;
        this.internOfferId = internOfferId;
        this.date = date;
        this.description = description;
        this.state = state;
    }

    @Override
    public String toString() {
        return "InterviewRequestInDto{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", employeurId=" + internOfferId +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
