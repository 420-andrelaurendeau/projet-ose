package com.sap.ose.projetose.exception;

public class ProgramNotFoundException extends RuntimeException {
    public ProgramNotFoundException() {
        super("Programme non trouvé");
    }

    public ProgramNotFoundException(String message) {
        super(message);
    }
}
