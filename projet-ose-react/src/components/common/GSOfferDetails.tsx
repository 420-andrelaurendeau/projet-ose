import React, {useState} from "react";
import {ReactComponent as Icon} from '../../assets/icons/back_icon.svg';
import {useNavigate} from "react-router-dom";
import {useTranslation} from "react-i18next";
import {InterOfferJob} from "../../model/IntershipOffer";

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

    return (
        <>
            <button className="dark:text-offwhite hover:font-bold" onClick={() => navigate("/gs/offers")}>
                <Icon className="w-9 h-9 mr-2 fill-current hover:font-bold"/>
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


            {/* Commentaire field */}
            <textarea name='comment'
                      className="mt-1 p-2 w-full border border-gray rounded-md placeholder:text-xs dark:bg-softdark dark:text-offwhite dark:border-0"
                      id="commentary_placeholder"
                      onChange={(e) => handleFormChange(e)}
                      placeholder={t("formField.GSOfferPage.placeholder")}></textarea>

            {renderError()}
        </>
    )

}

export default GSOfferDetails;