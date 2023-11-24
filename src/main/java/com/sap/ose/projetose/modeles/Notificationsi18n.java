package com.sap.ose.projetose.modeles;

public enum Notificationsi18n {
    newOfferAvaible,
    contractText,
    stageText,
    cvAccepter,
    cvRefuser;

    @Override
    public String toString() {
        switch (this) {
            case offresText -> {
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
