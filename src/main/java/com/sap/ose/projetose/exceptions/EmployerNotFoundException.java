package com.sap.ose.projetose.exceptions;

public class EmployerNotFoundException extends RuntimeException {
    public EmployerNotFoundException() {
        super("Employeur non trouvé");
    }

    public EmployerNotFoundException(String message) {
        super(message);
    }
}
