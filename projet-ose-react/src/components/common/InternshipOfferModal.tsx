import {useTranslation} from "react-i18next";
import React from "react";

const InternshipOfferModal: React.FC<any> = ({internshipOffer, isModalOpen,handleCloseModal}) => {
    const {t} = useTranslation();


    return (
        <>
            {isModalOpen && (
                <div className='flex justify-center items-center min-h-screen'>

                    <div
                        className="fixed z-50 top-0 left-0 w-full h-full bg-black bg-opacity-50 flex justify-center items-start p-3 overflow-y-auto">

                        <div className="bg-white rounded-lg p-6 w-full max-w-xl dark:bg-dark">


                            <h1 className='font-bold text-center text-dark text-xl dark:text-offwhite'>{t('formField.InternshipOfferForm.titleForm')}</h1>

                            <div
                                className='block sm:flex items-center space-x-0 sm:space-x-4 space-y-4 sm:space-y-0 min-h-50'>
                                {/* Title field */}
                                <div className='flex-auto sm:w-1/2'>
                                    <label className="block text-xs font-bold dark:text-offwhite"
                                           htmlFor="title_placeholder">{t('formField.InternshipOfferForm.title.text')}</label>
                                    <p
                                        className={"mt-1 p-2 w-full border border-gray rounded-md placeholder:text-xs placeholder:h-10 dark:bg-softdark dark:text-orange dark:border-0"}
                                        placeholder={t("formField.InternshipOfferForm.title.placeholder")}> {internshipOffer.title}</p>
                                </div>

                                {/* Location field */}
                                <div className='flex-auto sm:w-1/2'>
                                    <label className="block text-xs font-bold dark:text-offwhite"
                                           htmlFor="location_placeholder">{t('formField.InternshipOfferForm.location.text')}</label>
                                    <p
                                        className="mt-1 p-2 w-full border border-gray rounded-md placeholder:text-xs dark:bg-softdark dark:text-orange dark:border-0"
                                        id="location_placeholder"
                                        placeholder={t("formField.InternshipOfferForm.location.placeholder")}>
                                        {internshipOffer.location}
                                    </p>
                                </div>

                            </div>

                            {/* Description field */}
                            <div>
                                <label className="block text-xs font-bold dark:text-offwhite"
                                       htmlFor="description_placeholder">{t('formField.InternshipOfferForm.description.text')}</label>
                                <p
                                    className="mt-1 p-2 w-full border border-gray rounded-md placeholder:text-xs dark:bg-softdark dark:text-orange dark:border-0"
                                    id="description_placeholder"
                                    placeholder={t("formField.InternshipOfferForm.description.placeholder")}>
                                    {internshipOffer.description}
                                </p>

                            </div>

                            <div className='block sm:flex space-x-0 sm:space-x-4 space-y-4 sm:space-y-0'>
                                {/* Programme field */}
                                <div className='sm:w-1/2 sm:w-1/2'>
                                    <label className="block text-xs font-bold dark:text-offwhite"
                                           htmlFor="categories_placeholder">{t('formField.InternshipOfferForm.program.text')}</label>
                                    <p
                                        className="mt-1 p-2 w-full border border-gray rounded-md placeholder:text-xs dark:bg-softdark dark:border-0"
                                        id="categories_placeholder">
                                        {internshipOffer.programmeNom}
                                    </p>

                                </div>

                                {/* Salary field */}
                                <div className='sm:w-1/2 sm:w-1/2'>
                                    <label className="block text-xs font-bold dark:text-offwhite"
                                           htmlFor="salary_placeholder">{t('formField.InternshipOfferForm.salary.text')}</label>
                                    <p
                                        className="mt-1 p-2 w-full border border-gray rounded-md placeholder:text-xs dark:bg-softdark dark:text-orange dark:border-0"
                                        id="salary_placeholder"
                                        placeholder={t("formField.InternshipOfferForm.salary.placeholder") + " $"}>
                                        {internshipOffer.salaryByHour}
                                    </p>
                                </div>
                            </div>

                            <div className='block sm:flex space-x-0 sm:space-x-4 space-y-4 sm:space-y-0'>
                                {/* Start date field */}
                                <div className='flex-auto sm:w-1/2'>
                                    <label className="block text-xs font-bold dark:text-offwhite"
                                           htmlFor="start_date_placeholder">{t('formField.InternshipOfferForm.startDate.text')}</label>
                                    <p
                                        className="mt-1 p-2 w-full border border-gray rounded-md dark:bg-softdark dark:border-0"
                                        id="start_date_placeholder">
                                        {internshipOffer.startDate?.toLocaleDateString()}
                                    </p>
                                </div>

                                {/* End date field */}
                                <div className='flex-auto sm:w-1/2'>
                                    <label className="block text-xs font-bold dark:text-offwhite"
                                           htmlFor="end_date_placeholder">{t('formField.InternshipOfferForm.endDate.text')}</label>
                                    <p
                                        className="mt-1 p-2 w-full border border-gray rounded-md dark:bg-softdark dark:border-0"
                                        id="end_date_placeholder">
                                        {internshipOffer.endDate?.toLocaleDateString()}
                                    </p>
                                </div>
                            </div>

                            {/* File field */}
                            <div
                                className="border-dashed bg-offwhite border-2 h-32 relative dark:border-gray dark:bg-softdark pb-5">
                                <p
                                    className="absolute inset-0 z-50 m-0 p-0 w-full h-full outline-none opacity-0 cursor-pointer">
                                    {t('formField.InternshipOfferForm.file.text')}
                                </p>
                            </div>

                            {/* Submit button */}
                            <div className="block space-y-4 sm:space-y-0 sm:flex sm:space-x-4 pt-5">
                                <button
                                    className={`w-full flex-1 text-white font-bold p-2 rounded-md`}
                                    type="submit">Submit
                                </button>
                                <button
                                    className="w-full flex-1 bg-red  text-white font-bold p-2 rounded-md dark:bg-red"
                                    type="button" onClick={handleCloseModal}>Close
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
}

export default InternshipOfferModal;