package com.sap.ose.projetose.exceptions;

public class ServiceException extends RuntimeException {
    public ServiceException(String s) {
        super(s);
    }

    public ServiceException(){
        super("Erreur au niveau du service");
    }
}
