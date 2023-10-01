package com.sap.ose.projetose.exception;

public class OfferAlreadyApprovedException extends RuntimeException {
    public OfferAlreadyApprovedException(String message) {
        super(message);
    }

    public OfferAlreadyApprovedException() {
        super("L'offre déjà approuvée.");
    }
}
