package com.sap.ose.projetose.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class File extends BaseModel {
    @Lob
    private byte[] content;
    private String fileName;
    private ApprovalStatus approvalStatus;

    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private User user;
}
