package com.sap.ose.projetose.exception;

import jakarta.validation.constraints.Null;
import org.springframework.data.mapping.PropertyReferenceException;

public class BadSortingFieldException extends NullPointerException {
public BadSortingFieldException(String propertyName) {
        super("Le champ " + propertyName + " n'existe pas");
    }

}
