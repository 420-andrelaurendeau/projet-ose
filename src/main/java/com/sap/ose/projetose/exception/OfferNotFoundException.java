package com.sap.ose.projetose.exception;

public class OfferNotFoundException extends RuntimeException {
    public OfferNotFoundException(String s) {
        super(s);
    }

    public OfferNotFoundException() {
        super("Offre d'emploi non trouvée.");
    }
}
