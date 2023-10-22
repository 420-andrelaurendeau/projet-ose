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
                <div className="block space-y-4 sm:space-y-0 sm:flex sm:space-x-4 pt-5 ">
                    <button
                        className="w-full flex-1 text-white font-bold p-2 rounded-md bg-blue dark:bg-orange"
                        type="button"
                        onClick={handleApprove}>
                        {t("formField.GSOfferPage.button.approved")}
                    </button>
                    <button
                        className="w-full flex-1 bg-red  text-white font-bold p-2 rounded-md dark:bg-red"
                        type="button"
                        onClick={handleDecline}>
                        {t("formField.GSOfferPage.button.refused")}
                    </button>

                </div>
            )}
        </>
    )
}

export default GSReviewOfferForm;