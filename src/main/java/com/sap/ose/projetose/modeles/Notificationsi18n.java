package com.sap.ose.projetose.modeles;

public enum Notificationsi18n {
    newOfferAvaible,
    contractText,
    stageText,
    cvAccepter,
    cvRefuser,
    studentApplyOnOffer, revueCv, youAreAcceptedForStage;

    @Override
    public String toString() {
        switch (this) {
            case newOfferAvaible -> {
                return "";
            }
            case contractText -> {
                return "";
            }
            case stageText -> {
                return "";
            }
            case cvAccepter -> {
                return "";
            }
            case cvRefuser -> {
                return "";
            }
        }
        throw new IndexOutOfBoundsException("Could not find: " + super.toString());
    }
}