package com.sap.ose.projetose.modeles;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Notificationsi18n {
    newOfferAvailable("formFields.notifications.newOfferAvailable"),
    cvAccepter("formFields.notifications.cvAccepter"),
    cvRefuser("formFields.notifications.cvRefuser"),
    studentApplyOnOffer("formFields.notifications.studentApplyOnOffer"),
    revueCv("formFields.notifications.revueCv"),
    youAreAcceptedForStage("formFields.notifications.youAreAcceptedForStage"),
    newOfferSavedByEmployeur("formFields.notifications.newOfferSavedByEmployeur"),
    contractSignedByGSForEmployer("formFields.notifications.contractSignedByGSForEmployer"),
    contractSignedByGSForStudent("formFields.notifications.contractSignedByGSForStudent"),
    contractSignedByStudentForEmployer("formFields.notifications.contractSignedByStudentForEmployer"),
    contractSignedByStudentForGS("formFields.notifications.contractSignedByStudentForGS"),
    contractSignedByEmployerForStudent("formFields.notifications.contractSignedByEmployerForStudent"),
    contractSignedByEmployerForGS("formFields.notifications.contractSignedByEmployerForGS"),
    newContractAsBeenCreated("formFields.notifications.newContractAsBeenCreated"),
    contractAsBeenSignedByThreeParties("formFields.notifications.contractAsBeenSignedByThreeParties"),
	offerAsBeenAccpeted("formFields.notifications.offerAsBeenAccpeted"),
	offerAsBeenDeclined("formFields.notifications.offerAsBeenDeclined"),
    newStageAsBeenCreated("formFields.notifications.newStageAsBeenCreated");

    @JsonValue
    private final String translationKey;
}
