export default class NotificationLinkMap {
    static array: Record<string, string> = {
        "formField.notifications.newOfferAvailable": "/student/home/offers",
        "formField.notifications.cvAccepter": "/student/home/cv",
        "formField.notifications.cvRefuser": "/student/home/cv",
        "formField.notifications.studentApplyOnOffer": "/employer/home/offers",
        "formField.notifications.revueCv": "/internshipmanager/home/studentCvReview",
        "formField.notifications.youAreAcceptedForStage": "/student/home/stage",
        "formField.notifications.newOfferSavedByEmployeur": "/internshipmanager/home/offers",
        "formField.notifications.contractSignedByGSForEmployer": "/employer/home/internshipagreement",
        "formField.notifications.contractSignedByGSForStudent": "/student/home/internshipagreement",
        "formField.notifications.contractSignedByStudentForEmployer": "/employer/home/internshipagreement",
        "formField.notifications.contractSignedByStudentForGS": "/internshipmanager/home/internshipagreement",
        "formField.notifications.contractSignedByEmployerForStudent": "/student/home/internshipagreement",
        "formField.notifications.contractSignedByEmployerForGS": "/internshipmanager/home/internshipagreement",
        "formField.notifications.newContractAsBeenCreated": "/internshipmanager/home/internshipagreement",
        "formField.notifications.contractAsBeenSignedByThreeParties": "/internshipmanager/home/internshipagreement",
        "formField.notifications.offerAsBeenAccpeted": "/employer/home/offers",
        "formField.notifications.offerAsBeenDeclined": "/employer/home/offers",
        "formField.notifications.newStageAsBeenCreated": "/internshipmanager/home"
    }
}