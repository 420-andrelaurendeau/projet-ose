package com.sap.ose.projetose.dto;

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

    public InterviewRequestInDto(long studentId, long internOfferId, Date date, String description) {
        this.studentId = studentId;
        this.internOfferId = internOfferId;
        this.date = date;
        this.description = description;
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
