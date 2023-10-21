package com.sap.ose.projetose.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("Étudiant non trouvé");
    }

    public StudentNotFoundException(String message) {
        super(message);
    }
}
