package com.sap.ose.projetose.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String s) {
        super(s);
    }

    public DatabaseException() {
        super("Erreur d'accès a la base de données");
    }
}
