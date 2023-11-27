package com.sap.ose.projetose.modeles;

public enum Notificationsi18n {
    newOfferAvailable("FormFields.notifications.newOfferAvailable"),
    contractText("FormFields.notifications.contractText"),
    stageText("FormFields.notifications.stageText"),
    cvAccepter("FormFields.notifications.cvAccepter"),
    cvRefuser("FormFields.notifications.cvRefuser"),
    studentApplyOnOffer("FormFields.notifications.studentApplyOnOffer"),
    revueCv("FormFields.notifications.revueCv"),
    youAreAcceptedForStage("FormFields.notifications.youAreAcceptedForStage"),
    newOfferSavedByEmployeur("FormFields.notifications.newOfferSavedByEmployeur"),
    contractSignedByGS("FormFields.notifications.contractSignedByGS"),
    newContractAsBeenCreated("FormFields.notifications.newContractAsBeenCreated"),
    contractAsBeenSignedByThreeParties("FormFields.notifications.contractAsBeenSignedByThreeParties"),
	offerAsBeenAccpeted("FormFields.notifications.offerAsBeenAccpeted"),
	offerAsBeenDeclined("FormFields.notifications.offerAsBeenDeclined"),
    newStageAsBeenCreated("FormFields.notifications.newStageAsBeenCreated");

    private final String translationKey;

    Notificationsi18n(String translationKey) {
        this.translationKey = translationKey;
    }

    @Override
    public String toString() {
        return translationKey;
    }
}
