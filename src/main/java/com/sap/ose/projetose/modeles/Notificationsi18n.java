package com.sap.ose.projetose.modeles;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Notificationsi18n {
    newOfferAvailable("formField.notifications.newOfferAvailable"),
    contractText("formField.notifications.contractText"),
    stageText("formField.notifications.stageText"),
    cvAccepter("formField.notifications.cvAccepter"),
    cvRefuser("formField.notifications.cvRefuser"),
    studentApplyOnOffer("formField.notifications.studentApplyOnOffer"),
    revueCv("formField.notifications.revueCv"),
    youAreAcceptedForStage("formField.notifications.youAreAcceptedForStage"),
    newOfferSavedByEmployeur("formField.notifications.newOfferSavedByEmployeur"),
    contractSignedByGS("formField.notifications.contractSignedByGS"),
    newContractAsBeenCreated("formField.notifications.newContractAsBeenCreated"),
    contractAsBeenSignedByThreeParties("formField.notifications.contractAsBeenSignedByThreeParties"),
	offerAsBeenAccpeted("formField.notifications.offerAsBeenAccpeted"),
	offerAsBeenDeclined("formField.notifications.offerAsBeenDeclined"),
    newStageAsBeenCreated("formField.notifications.newStageAsBeenCreated");

    @JsonValue
    private final String translationKey;
}
