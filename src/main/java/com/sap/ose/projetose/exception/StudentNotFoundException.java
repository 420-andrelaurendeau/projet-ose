package com.sap.ose.projetose.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("Étudiant non trouvé");
    }
}
