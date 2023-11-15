package com.sap.ose.projetose.exception;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException() {
        super("Fichier non trouvé");
    }

    public FileNotFoundException(String message) {
        super(message);
    }
}
