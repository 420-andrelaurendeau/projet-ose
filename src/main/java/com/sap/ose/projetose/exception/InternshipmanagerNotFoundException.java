package com.sap.ose.projetose.exception;

public class InternshipmanagerNotFoundException extends RuntimeException {
    public InternshipmanagerNotFoundException(String message) {
        super(message);
    }

    public InternshipmanagerNotFoundException() {
        super("Gestionnaire de stage non trouvé.");
    }
}
