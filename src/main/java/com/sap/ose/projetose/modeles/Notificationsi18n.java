package com.sap.ose.projetose.modeles;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
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
    contractSignedByGSForEmployer("FormFields.notifications.contractSignedByGSForEmployer"),
    contractSignedByGSForStudent("FormFields.notifications.contractSignedByGSForStudent"),
    contractSignedByStudentForEmployer("FormFields.notifications.contractSignedByStudentForEmployer"),
    contractSignedByStudentForGS("FormFields.notifications.contractSignedByStudentForGS"),
    contractSignedByEmployerForStudent("FormFields.notifications.contractSignedByEmployerForStudent"),
    contractSignedByEmployerForGS("FormFields.notifications.contractSignedByEmployerForGS"),
    newContractAsBeenCreated("FormFields.notifications.newContractAsBeenCreated"),
    contractAsBeenSignedByThreeParties("FormFields.notifications.contractAsBeenSignedByThreeParties"),
	offerAsBeenAccpeted("FormFields.notifications.offerAsBeenAccpeted"),
	offerAsBeenDeclined("FormFields.notifications.offerAsBeenDeclined"),
    newStageAsBeenCreated("FormFields.notifications.newStageAsBeenCreated");

    @JsonValue
    private final String translationKey;
}
