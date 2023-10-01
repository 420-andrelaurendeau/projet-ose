package com.sap.ose.projetose.exception;

public class OfferAlreadyReviewException extends RuntimeException {
    public OfferAlreadyReviewException(String message) {
        super(message);
    }

    public OfferAlreadyReviewException() {
        super("L'offre déjà review.");
    }
}
