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
    private long employeurId;
    private Date date;
    private String description;

    public InterviewRequestInDto(long studentId, long employeurId, Date date, String description) {
        this.studentId = studentId;
        this.employeurId = employeurId;
        this.date = date;
        this.description = description;
    }

    @Override
    public String toString() {
        return "InterviewRequestInDto{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", employeurId=" + employeurId +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
