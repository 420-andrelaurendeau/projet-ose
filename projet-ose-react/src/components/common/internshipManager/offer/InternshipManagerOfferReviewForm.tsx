import React from "react";
import {useTranslation} from "react-i18next";
import i18n from "i18next";

interface GSReviewOfferFormProps {
    handleApprove: any;
    handleDecline: any;
    internshipOffer: any;
}
const InternshipManagerOfferReviewForm: React.FC<GSReviewOfferFormProps> = ({handleApprove, handleDecline, internshipOffer}) => {
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.InternshipOfferModal.button");

    return(
        <>
            {internshipOffer!.state === "PENDING" && (
                <div className="flex space-x-4 pt-2 pb-4 ">
                    <button
                        className="w-full flex-1 bg-blue text-white font-bold p-2 rounded-md dark:bg-orange"
                        type="button"
                        onClick={handleApprove}>
                        {fields.approved}
                    </button>
                    <button
                        className="w-full flex-1 bg-red  text-white font-bold p-2 rounded-md dark:bg-red"
                        type="button"
                        onClick={handleDecline}>
                        {fields.refused}
                    </button>

                </div>
            )}
        </>
    )
}

export default InternshipManagerOfferReviewForm;