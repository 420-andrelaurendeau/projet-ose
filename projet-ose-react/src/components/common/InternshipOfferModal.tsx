import {useTranslation} from "react-i18next";
import React, {useEffect, useState} from "react";
import fileIconLight from "../../assets/icons/file_icon.svg";
import fileIconDark from "../../assets/icons/file_icon_solid.svg"
import useDarkSide from "../../hooks/useDarkSide";
import theme from "tailwindcss/defaultTheme";

const InternshipOfferModal: React.FC<any> = ({internshipOffer, isModalOpen, handleCloseModal}) => {
    const {t} = useTranslation();
    const [formState, setFormState] = React.useState({
        commentary: "",
    });

    const [theme, setTheme] = useState("light");
    function handleFormChange(e: React.ChangeEvent<HTMLTextAreaElement>) {
        const {name, value} = e.target;
        setFormState(prevState => ({
            ...prevState, [name]: value
        }));
    }

    useEffect(() => {
        localStorage.getItem('theme') === 'dark' ? setTheme('dark') : setTheme('light');
    }, [localStorage.getItem('theme')]);

    return (<>
        {isModalOpen && (<div className='flex justify-center items-center min-h-screen'>

            <div
                className="fixed z-50 top-0 left-0 w-full h-full bg-black bg-opacity-50 flex justify-center items-start p-3 overflow-y-auto">
                <div className="bg-white rounded-lg p-6 w-full max-w-xl dark:bg-dark">
                    <h1 className='font-bold text-center text-dark text-xl dark:text-offwhite'>{internshipOffer.title}</h1>
                    <div className="block sm:flex mt-5 sm:justify-between sm:items-start">
                        <div className='block items-center min-h-50'>
                            {/* Employeur field */}
                            <div className={"flex"}>
                                <p className={"p-1 dark:text-offwhite"}>
                                    {internshipOffer.employeurNom!}
                                </p>
                                <p className={"p-1 dark:text-offwhite"}>
                                    {internshipOffer.employeurPrenom!}
                                </p>
                            </div>
                            {/* Location field */}
                            <p className="p-1 dark:text-offwhite">
                                {internshipOffer.location}
                            </p>

                            {/* Company field */}
                            <p className="p-1 dark:text-offwhite">
                                {internshipOffer.employeurEntreprise}
                            </p>
                        </div>
                        <div className="block sm:flex flex-col sm:justify-end sm:items-end h-full">
                            <div className="flex">
                                {/* Start date field */}
                                <p className="p-1 dark:text-offwhite">
                                    {internshipOffer.startDate.toLocaleDateString('fr-FR', {
                                        day: '2-digit',
                                        month: '2-digit',
                                        year: 'numeric',
                                    })}
                                </p>

                                {/* End date field */}
                                <p className="p-1 dark:text-offwhite">
                                    {internshipOffer.endDate.toLocaleDateString('fr-FR', {
                                        day: '2-digit',
                                        month: '2-digit',
                                        year: 'numeric',
                                    })}
                                </p>
                            </div>
                            {/* Programme field */}
                            <div className="flex">
                                <p className="p-1 w-full dark:text-offwhite">
                                    {internshipOffer.programmeNom}
                                </p>
                            </div>
                            {/* Salary field */}
                            <div className="flex">
                                <p className="p-1 w-full dark:text-offwhite">
                                    {internshipOffer.salaryByHour}&nbsp;$/h
                                </p>
                            </div>
                        </div>
                    </div>

                    {/* Description field */}
                    <div className="mb-5 justify-center items-center h-full">
                        <p className="mt-1 p-2 w-full dark:text-offwhite ">
                            {internshipOffer.description}
                        </p>

                    </div>

                    {/* File field */}
                    <div className="flex mb-5">
                        <svg xmlns="http://www.w3.org/2000/svg" fill={theme === `light` ? `#306bac` : `#F57A00` } height="50" viewBox="0 -960 960 960" width="24"><path d="M320-240h320v-80H320v80Zm0-160h320v-80H320v80ZM240-80q-33 0-56.5-23.5T160-160v-640q0-33 23.5-56.5T240-880h320l240 240v480q0 33-23.5 56.5T720-80H240Zm280-520h200L520-800v200Z" /></svg>
                        <p className="mt-1 p-2 w-full dark:text-offwhite">{internshipOffer.file.fileName}</p>
                    </div>


                    {/* Commentaire field */}
                    <div>
                        <label className="block text-xs font-bold dark:text-offwhite"
                               htmlFor="description_placeholder">{t('formField.InternshipOfferModal.commentary')}</label>
                        <textarea name='commentary'
                                  className="mt-1 p-2 w-full border border-gray rounded-md placeholder:text-xs dark:bg-softdark dark:text-offwhite dark:border-0"
                                  id="commentary_placeholder"
                                  onChange={(e) => handleFormChange(e)} value={formState.commentary}
                                  placeholder={t("formField.InternshipOfferModal.placeholder")}></textarea>
                    </div>

                    {/* Buttons */}
                    <div className="block space-y-4 sm:space-y-0 sm:flex sm:space-x-4 pt-5 ">
                        <button
                            className="w-full flex-1 text-white font-bold p-2 rounded-md bg-blue dark:bg-orange"
                            type="submit">{t("formField.InternshipOfferModal.button.approved")}
                        </button>
                        <button
                            className="w-full flex-1 bg-red  text-white font-bold p-2 rounded-md dark:bg-red"
                            type="button" onClick={handleCloseModal}>{t("formField.InternshipOfferModal.button.refused")}
                        </button>
                    </div>
                </div>
            </div>
        </div>)}
    </>);
}

export default InternshipOfferModal;