package com.sap.ose.projetose.exceptions;

public class OfferAlreadyReviewedException extends RuntimeException {
    public OfferAlreadyReviewedException(String message) {
        super(message);
    }

    public OfferAlreadyReviewedException() {
        super("L'offre a déjà été revue");
    }
}
