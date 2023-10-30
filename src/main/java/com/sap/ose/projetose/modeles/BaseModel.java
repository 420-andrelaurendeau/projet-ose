package com.sap.ose.projetose.modeles;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseModel {
    @Id
    @GeneratedValue
    private long id;
}
