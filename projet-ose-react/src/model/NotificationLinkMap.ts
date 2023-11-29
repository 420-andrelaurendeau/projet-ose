export default class NotificationLinkMap {
    static array: Record<string, string> = {
        "formField.notifications.newOfferAvailable": "/student/home/offers",
        "formField.notifications.cvAccepter": "/student/home/cv",
        "formField.notifications.cvRefuser": "/student/home/cv",
        "formField.notifications.studentApplyOnOffer": "/employer/home/offers",
        "formField.notifications.revueCv": "/internshipmanager/home/studentCvReview",
        "formField.notifications.youAreAcceptedForStage": "/student/home/stage",
        "formField.notifications.newOfferSavedByEmployeur": "/internshipmanager/home/offers",
        "formFields.notifications.contractSignedByGSForEmployer": "/employer/home/internshipagreement",
        "formFields.notifications.contractSignedByGSForStudent": "/student/home/internshipagreement",
        "formFields.notifications.contractSignedByStudentForEmployer": "/employer/home/internshipagreement",
        "formFields.notifications.contractSignedByStudentForGS": "/internshipmanager/home/internshipagreement",
        "formFields.notifications.contractSignedByEmployerForStudent": "/student/home/internshipagreement",
        "formFields.notifications.contractSignedByEmployerForGS": "/internshipmanager/home/internshipagreement",
        "formField.notifications.newContractAsBeenCreated": "/internshipmanager/home/internshipagreement",
        "formField.notifications.contractAsBeenSignedByThreeParties": "/internshipmanager/home/internshipagreement",
        "formField.notifications.offerAsBeenAccpeted": "/employer/home/offers",
        "formField.notifications.offerAsBeenDeclined": "/employer/home/offers",
        "formField.notifications.newStageAsBeenCreated": "/internshipmanager/home"
    }
}