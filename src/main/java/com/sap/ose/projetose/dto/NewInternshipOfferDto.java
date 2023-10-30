package com.sap.ose.projetose.dto;

import com.sap.ose.projetose.annotations.FileExists;
import com.sap.ose.projetose.annotations.StudyProgramExists;
import com.sap.ose.projetose.annotations.UserExists;
import com.sap.ose.projetose.modeles.Etats;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class NewInternshipOfferDto {
    @NotBlank(message = "{internshipOffer.title.notBlank}")
    private String title;
    @NotBlank(message = "{internshipOffer.location.notBlank}")
    private String location;
    @NotBlank(message = "{internshipOffer.description.notBlank}")
    private String description;
    private Double salaryByHour;
    @NotBlank(message = "{internshipOffer.startDate.notBlank}")
    @Future(message = "{internshipOffer.startDate.future}")
    private LocalDate startDate;
    @NotBlank(message = "{internshipOffer.endDate.notBlank}")
    @Future(message = "{internshipOffer.endDate.future}")
    private LocalDate endDate;
    @StudyProgramExists
    private Long programId;
    @UserExists
    private Long employerId;
    @FileExists
    private Long offerFile;
    private Etats state;

    @AssertTrue(message = "{internshipOffer.endDate.beforeStartDate}")
    private boolean isStartDateBeforeEndDate() {
        return startDate.isBefore(endDate);
    }
}
