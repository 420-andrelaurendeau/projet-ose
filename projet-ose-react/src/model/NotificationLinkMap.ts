export default class NotificationLinkMap {
    static array: Record<string, string> = {
        "formField.notifications.newOfferAvailable": "/student/home/offers",
        "formField.notifications.cvAccepter": "/student/home/cv",
        "formField.notifications.cvRefuser": "/student/home/cv",
        "formField.notifications.studentApplyOnOffer": "/employer/home/offers",
        "formField.notifications.revueCv": "/internshipmanager/home/studentCvReview",
        "formField.notifications.youAreAcceptedForStage": "/student/home/stage",
        "formField.notifications.newOfferSavedByEmployeur": "/internshipmanager/home/offers",
        "FormFields.notifications.contractSignedByGSForEmployer": "/employer/home/internshipagreement",
        "FormFields.notifications.contractSignedByGSForStudent": "/student/home/internshipagreement",
        "FormFields.notifications.contractSignedByStudentForEmployer": "/employer/home/internshipagreement",
        "FormFields.notifications.contractSignedByStudentForGS": "/internshipmanager/home/internshipagreement",
        "FormFields.notifications.contractSignedByEmployerForStudent": "/student/home/internshipagreement",
        "FormFields.notifications.contractSignedByEmployerForGS": "/internshipmanager/home/internshipagreement",
        "formField.notifications.newContractAsBeenCreated": "/internshipmanager/home/internshipagreement",
        "formField.notifications.contractAsBeenSignedByThreeParties": "/internshipmanager/home/internshipagreement",
        "formField.notifications.offerAsBeenAccpeted": "/employer/home/offers",
        "formField.notifications.offerAsBeenDeclined": "/employer/home/offers",
        "formField.notifications.newStageAsBeenCreated": "/internshipmanager/home"
    }
}