package com.sap.ose.projetose.exception;

public class InternshipManagerNotFoundException extends RuntimeException {
    public InternshipManagerNotFoundException() {
        super("Gestionnaire de stage non trouvé.");
    }
}
