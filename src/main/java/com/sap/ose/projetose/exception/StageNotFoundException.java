package com.sap.ose.projetose.exception;

public class StageNotFoundException extends RuntimeException {
    public StageNotFoundException(String s) {
        super(s);
    }

    public StageNotFoundException() {
        super("Entente de stage non trouvée.");
    }
}
