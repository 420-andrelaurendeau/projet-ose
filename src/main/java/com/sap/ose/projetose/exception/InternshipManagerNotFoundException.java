package com.sap.ose.projetose.exception;

public class InternshipManagerNotFoundException extends RuntimeException {
    public InternshipManagerNotFoundException(String message) {
        super(message);
    }

    public InternshipManagerNotFoundException() {
        super("Gestionnaire de stage non trouvé.");
    }
}
