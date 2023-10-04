package com.sap.ose.projetose.exception;

public class EtudiantNotFoundException extends RuntimeException {
    public EtudiantNotFoundException() {
        super("Étudiant non trouvé");
    }

    public EtudiantNotFoundException(String message) {
        super(message);
    }
}
