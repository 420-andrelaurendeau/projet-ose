package com.sap.ose.projetose.modeles;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Notificationsi18n {
    newOfferAvailable("formField.notifications.newOfferAvailable"),
    cvAccepter("formField.notifications.cvAccepter"),
    cvRefuser("formField.notifications.cvRefuser"),
    studentApplyOnOffer("formField.notifications.studentApplyOnOffer"),
    revueCv("formField.notifications.revueCv"),
    youAreAcceptedForStage("formField.notifications.youAreAcceptedForStage"),
    newOfferSavedByEmployeur("formField.notifications.newOfferSavedByEmployeur"),
    contractSignedByGSForEmployer("formField.notifications.contractSignedByGSForEmployer"),
    contractSignedByGSForStudent("formField.notifications.contractSignedByGSForStudent"),
    contractSignedByStudentForEmployer("formField.notifications.contractSignedByStudentForEmployer"),
    contractSignedByStudentForGS("formField.notifications.contractSignedByStudentForGS"),
    contractSignedByEmployerForStudent("formField.notifications.contractSignedByEmployerForStudent"),
    contractSignedByEmployerForGS("formField.notifications.contractSignedByEmployerForGS"),
    newContractAsBeenCreated("formField.notifications.newContractAsBeenCreated"),
    contractAsBeenSignedByThreeParties("formField.notifications.contractAsBeenSignedByThreeParties"),
	offerAsBeenAccpeted("formField.notifications.offerAsBeenAccpeted"),
	offerAsBeenDeclined("formField.notifications.offerAsBeenDeclined"),
    newStageAsBeenCreated("formField.notifications.newStageAsBeenCreated");

    @JsonValue
    private final String translationKey;
}
