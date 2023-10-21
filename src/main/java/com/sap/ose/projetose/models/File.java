package com.sap.ose.projetose.models;

import jakarta.persistence.*;
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

    private boolean isAccepted;

    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private User user;
}