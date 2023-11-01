package com.sap.ose.projetose.exception;

public class InvalidStateException extends RuntimeException {
    public InvalidStateException(String state) {
        super("Cette état n'est pas valide : " + state);
    }

    public InvalidStateException(String message, String state) {
        super(message + " " + state);
    }
}

