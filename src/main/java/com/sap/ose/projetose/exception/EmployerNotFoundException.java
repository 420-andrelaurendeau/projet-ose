package com.sap.ose.projetose.exception;

public class EmployerNotFoundException extends RuntimeException {
    public EmployerNotFoundException() {
        super("Employer non trouvé");
    }
}
