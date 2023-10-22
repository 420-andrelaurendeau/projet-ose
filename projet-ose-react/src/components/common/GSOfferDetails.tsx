import React, {useEffect, useRef, useState} from "react";
import {ReactComponent as Icon} from '../../assets/icons/back_icon.svg';
import {useNavigate} from "react-router-dom";
import {useTranslation} from "react-i18next";
import {InterOfferJob} from "../../model/IntershipOffer";
import {getOfferReviewById} from "../../api/GSManagerAPI";
import {getOfferReviewRequestById} from "../../api/InterOfferJobAPI";
import {comment} from "postcss";
import {useToast} from "../../hooks/state/useToast";

interface GSOfferDetailsProps {
    handleFormChange: any;
    internshipOffer: InterOfferJob;
    renderError: any;
}

const GSOfferDetails: React.FC<GSOfferDetailsProps> = ({
                                                           handleFormChange,
                                                           internshipOffer,
                                                           renderError
                                                       }) => {

    const navigate = useNavigate();
    const {t} = useTranslation();
    const [theme, setTheme] = useState("light");
    const [isAlreadyReviewed, setIsAlreadyReviewed] = useState(false);
    const loadOfferReviewRef = useRef(false);
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.InternshipOfferList");
    const [formStateReview, setFormStateReview] = React.useState({
       comment: "", state: ""
    });
    const toast = useToast();


    useEffect(() => {
            const loadOfferReview = async () => {
                try {
                    loadOfferReviewRef.current = true;

                    const response = await getOfferReviewRequestById(internshipOffer.offerReviewRequestId!);
                    setFormStateReview(prevState => ({
                        ...prevState, comment: response.comment!,
                    }));
                    return
                } catch (error) {
                    toast.error("Une erreur est survenue lors du chargement de l'offre")
                } finally {
                    loadOfferReviewRef.current = false;
                }
            }

            if (internshipOffer.state !== "PENDING" && !loadOfferReviewRef.current) {
                setIsAlreadyReviewed(true);
                setFormStateReview(prevState => ({
                    ...prevState, state: internshipOffer.state!,
                }));
                loadOfferReview()
            }
        }, []
    )


    return (
        <>
            <button
                className="fixed z-10 top-20 left-4 p-2 bg-blue dark:bg-orange rounded-full shadow-lg text-offwhite hover:font-bold"
                onClick={() => navigate("/gs/offers")}
            >
                <Icon className="w-5 h-5 fill-current hover:font-bold"/>
            </button>

            <h1 className='font-bold text-center text-dark text-2xl dark:text-offwhite'>{internshipOffer!.title}</h1>


            <div className="block sm:flex mt-5 sm:justify-between sm:items-start">
                <div className='block items-center min-h-50'>

                    {/* Employeur field */}
                    <div className={"flex"}>
                        <p className={"p-1 text-lg dark:text-offwhite"}>
                            {internshipOffer!.employeurNom!}
                        </p>
                        <p className={"p-1 text-lg dark:text-offwhite"}>
                            {internshipOffer!.employeurPrenom!}
                        </p>
                    </div>

                    {/* Location field */}
                    <p className="p-1 dark:text-offwhite">
                        {internshipOffer!.location}
                    </p>

                    {/* Company field */}
                    <p className="p-1 text-lg dark:text-offwhite">
                        {internshipOffer!.employeurEntreprise}
                    </p>
                </div>
                <div className="block sm:flex flex-col sm:justify-end sm:items-end h-full">
                    {/* Start date field */}
                    <div className="flex">
                        <p className="p-1 dark:text-offwhite">
                            {new Date(internshipOffer!.startDate!).toLocaleDateString('fr-FR', {
                                day: '2-digit', month: '2-digit', year: 'numeric',
                            })}
                        </p>


                        {/* End date field */}
                        <p className="p-1 dark:text-offwhite">
                            {new Date(internshipOffer!.endDate!).toLocaleDateString('fr-FR', {
                                day: '2-digit', month: '2-digit', year: 'numeric',
                            })}
                        </p>
                    </div>


                    {/* Programme field */}
                    <p className="p-1 text-lg w-full dark:text-offwhite">
                        {internshipOffer!.programmeNom}
                    </p>

                    {/* Salary field */}
                    <div className="flex">
                        <p className="p-1 text-lg w-full dark:text-offwhite">
                            {internshipOffer!.salaryByHour}&nbsp;$/h
                        </p>
                    </div>
                </div>
            </div>

            {/* Description field */}
            <div className="mb-5 justify-center items-center h-full">
                <p className="mt-1 p-2 w-full dark:text-offwhite ">
                    {internshipOffer!.description}
                </p>

            </div>

            {/* File field */}
            <div className="flex mb-5">
                <svg xmlns="http://www.w3.org/2000/svg" fill={theme === `light` ? `#306bac` : `#F57A00`}
                     height="50" viewBox="0 -960 960 960" width="24">
                    <path
                        d="M320-240h320v-80H320v80Zm0-160h320v-80H320v80ZM240-80q-33 0-56.5-23.5T160-160v-640q0-33 23.5-56.5T240-880h320l240 240v480q0 33-23.5 56.5T720-80H240Zm280-520h200L520-800v200Z"/>
                </svg>
                <p className="mt-1 p-2 w-full dark:text-offwhite">{internshipOffer!.file!.fileName}</p>
            </div>

            {!isAlreadyReviewed && (
                <>
                    <textarea
                        name='comment'
                        className="mt-1 p-2 w-full border border-gray rounded-md placeholder:text-xs dark:bg-softdark dark:text-offwhite dark:border-0"
                        id="commentary_placeholder"
                        onChange={(e) => handleFormChange(e)}
                        placeholder={t("formField.GSOfferPage.placeholder")}>

                    </textarea>
                    {renderError()}
                </>
            )}

            {isAlreadyReviewed && (
                <>
                    <textarea
                        name='comment'
                        className="mt-1 p-2 w-full border border-gray rounded-md placeholder:text-xs dark:bg-softdark dark:text-offwhite dark:border-0"
                        id="commentary_placeholder"
                        placeholder={formStateReview.comment}
                        disabled={true}
                        >

                    </textarea>
                    <div role="cell" className="md:w-1/5 w-1/3 px-2 py-2 whitespace-nowrap truncate">
                                            <span
                                                className={
                                                    formStateReview.state! == "PENDING" ?
                                                        "px-2  xxxs:text-xs sm:text-sm inline-flex leading-5 justify-center font-semibold rounded-full w-3/4 bg-orange text-white dark:text-offwhite"
                                                        : formStateReview.state! === "DECLINED" ?
                                                            "px-2 xxxs:text-xs sm:text-sm inline-flex leading-5 font-semibold justify-center rounded-full w-3/4 bg-red text-white dark:text-offwhite "
                                                            : "px-2 xxxs:text-xs sm:text-sm inline-flex leading-5 font-semibold rounded-full w-3/4 justify-center bg-green text-white dark:text-offwhite "}
                                            >
                                                {fields.table[formStateReview.state!]}
                                            </span>
                    </div>
                </>
            )}
        </>
    )

}

export default GSOfferDetails;