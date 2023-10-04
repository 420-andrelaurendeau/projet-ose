package com.sap.ose.projetose.exception;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String s) {
        super(s);
    }

    public DatabaseException(){
        super("Erreur d'accès a la base de données");
    }
}
