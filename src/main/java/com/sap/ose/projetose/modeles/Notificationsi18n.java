package com.sap.ose.projetose.modeles;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Notificationsi18n {
    newOfferAvailable("formField.notifications.newOfferAvailable",
            """
                    Il y a une nouvelle offre.
                    ----
                    There`s a new offer."""),
    cvAccepter("formField.notifications.cvAccepter",
            """
                    Un CV a été approuvé.
                    ----
                    A CV has been approved."""),
    cvRefuser("formField.notifications.cvRefuser",
            """
                    Un CV a été réfusé.
                    ----
                    A CV has been refused."""),
    studentApplyOnOffer("formField.notifications.studentApplyOnOffer",
            """
                    Vous avez un nouveau candidat.
                    ----
                    You have a new candidate."""),
    revueCv("formField.notifications.revueCv",
            """
                    Un CV est en attente d'approbation.
                    ----
                    A CV is available for review."""),
    youAreAcceptedForStage("formField.notifications.youAreAcceptedForStage",
            """
                    Vous êtes accepté pour un stage.
                    ----
                    You have been accepted for an internship."""),
    newOfferSavedByEmployeur("formField.notifications.newOfferSavedByEmployeur",
            """
                    Un offre d'emploi est en attente d'approbation.
                    ----
                    A job offer is available for review."""),
    contractSignedByGSForEmployer("formField.notifications.contractSignedByGSForEmployer",
            """
                    Un contrat a été signé par un gestionnaire des stages.
                    ----
                    A contract has been signed by an internship manager."""),
    contractSignedByGSForStudent("formField.notifications.contractSignedByGSForStudent",
            """
                    Votre gestionnaire des stages a signé un contrat.
                    ----
                    Your internship manager has signed a contract."""),
    contractSignedByStudentForEmployer("formField.notifications.contractSignedByStudentForEmployer",
            """
                    Un candidat a signé un contrat.
                    ----
                    A candidate has signed a contract."""),
    contractSignedByStudentForGS("formField.notifications.contractSignedByStudentForGS",
            """
                    Un CV a été approuvé.
                    ----
                    A CV has been approved."""),
    contractSignedByEmployerForStudent("formField.notifications.contractSignedByEmployerForStudent",
            """
                    Un étudiant a signé un contrat.
                    ----
                    A student has signed a contract."""),
    contractSignedByEmployerForGS("formField.notifications.contractSignedByEmployerForGS",
            """
                    Un employeur a signé un contrat.
                    ----
                    An employer has signed a contract."""),
    newContractAsBeenCreated("formField.notifications.newContractAsBeenCreated",
            """
                    Un nouveau contrat est disponible.
                    ----
                    A new contract is now available."""),
    contractAsBeenSignedByThreeParties("formField.notifications.contractAsBeenSignedByThreeParties",
            """
                    Un contrat est signé par les trois parties prenants.
                    ----
                    A contract has been signed by all parties."""),
	offerAsBeenAccepted("formField.notifications.offerAsBeenAccepted",
            """
                    Un offre de stage a été approuvé.
                    ----
                    A job offer has been approved."""),
	offerAsBeenDeclined("formField.notifications.offerAsBeenDeclined",
            """
                    A job offer has been denied.
                    ----
                    A job offer has been denied."""),
    newStageAsBeenCreated("formField.notifications.newStageAsBeenCreated",
            """
                    Il y a une nouvelle application de stage.
                    ----
                    There`s a new internship application.""");

    @JsonValue
    private final String translationKey;
    @Getter
    private final String bilingualEmail;
}
