package com.sap.ose.projetose.exception;

public class ProgramNotFoundException extends RuntimeException {
    public ProgramNotFoundException() {
        super("Formation non trouvé");
    }
}
