import React from "react";
import {useTranslation} from "react-i18next";

interface GSReviewOfferFormProps {
    handleApprove: any;
    handleDecline: any;
    internshipOffer: any;
}
const GSReviewOfferForm: React.FC<GSReviewOfferFormProps> = ({handleApprove, handleDecline, internshipOffer}) => {
    const {t} = useTranslation();

    return(
        <>
            {internshipOffer!.state === "PENDING" && (
                <div className="flex space-x-4 pt-2 pb-4 ">
                    <button
                        className="w-full flex-1 bg-blue text-white font-bold p-2 rounded-md dark:bg-orange"
                        type="button"
                        onClick={handleApprove}>
                        {t("formField.GSReviewOfferForm.button.approved")}
                    </button>
                    <button
                        className="w-full flex-1 bg-red  text-white font-bold p-2 rounded-md dark:bg-red"
                        type="button"
                        onClick={handleDecline}>
                        {t("formField.GSReviewOfferForm.button.refused")}
                    </button>

                </div>
            )}
        </>
    )
}

export default GSReviewOfferForm;