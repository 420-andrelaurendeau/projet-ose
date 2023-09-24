import {useTranslation} from "react-i18next";
import React from "react";
import fileIcon from "../../assets/icons/file_icon.svg";

const InternshipOfferModal: React.FC<any> = ({internshipOffer, isModalOpen, handleCloseModal}) => {
    const {t} = useTranslation();
    const [formState, setFormState] = React.useState({
        commentary: "",
    });

    function handleFormChange(e: React.ChangeEvent<HTMLTextAreaElement>) {
        const {name, value} = e.target;
        setFormState(prevState => ({
            ...prevState, [name]: value
        }));

    }

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
                                <p className={"p-1 dark:bg-softdark dark:text-orange"}>
                                    {internshipOffer.employeurNom!}
                                </p>
                                <p className={"p-1 dark:bg-softdark dark:text-orange"}>
                                    {internshipOffer.employeurPrenom!}
                                </p>
                            </div>
                            {/* Location field */}
                            <p className="p-1 dark:bg-softdark dark:text-orange">
                                {internshipOffer.location}
                            </p>

                            {/* Company field */}
                            <p className="p-1 dark:bg-softdark dark:text-orange">
                                {internshipOffer.employeurEntreprise}
                            </p>
                        </div>
                        <div className="block sm:flex flex-col sm:justify-end sm:items-end h-full">
                            <div className="flex">
                                {/* Start date field */}
                                <p className="p-1 dark:bg-softdark dark:text-orange">
                                    {internshipOffer.startDate.toLocaleDateString('fr-FR', {
                                        day: '2-digit',
                                        month: '2-digit',
                                        year: 'numeric',
                                    })}
                                </p>

                                {/* End date field */}
                                <p className="p-1 dark:bg-softdark dark:text-orange">
                                    {internshipOffer.endDate.toLocaleDateString('fr-FR', {
                                        day: '2-digit',
                                        month: '2-digit',
                                        year: 'numeric',
                                    })}
                                </p>
                            </div>
                            {/* Programme field */}
                            <div className="flex">
                                <p className="p-1 w-full dark:bg-softdark">
                                    {internshipOffer.programmeNom}
                                </p>
                            </div>
                            {/* Salary field */}
                            <div className="flex">
                                <p className="p-1 w-full dark:bg-softdark">
                                    {internshipOffer.salaryByHour}&nbsp;$/h
                                </p>
                            </div>
                        </div>
                    </div>

                    {/* Description field */}
                    <div className="mb-5 justify-center items-center h-full">
                        <p className="mt-1 p-2 w-full dark:bg-softdark dark:text-orange ">
                            {internshipOffer.description}
                        </p>

                    </div>

                    {/* File field */}
                    <div className="flex mb-5">
                        <img src={fileIcon} alt="Description de mon icône" />
                        <p>{internshipOffer.file.fileName}</p>
                    </div>


                    {/* Commentaire field */}
                    <div>
                        <label className="block text-xs font-bold dark:text-offwhite"
                               htmlFor="description_placeholder">{t('formField.InternshipOfferModal.commentary')}</label>
                        <textarea name='commentary'
                                  className="mt-1 p-2 w-full border border-gray rounded-md placeholder:text-xs dark:bg-softdark dark:text-orange dark:border-0"
                                  id="commentary_placeholder"
                                  onChange={(e) => handleFormChange(e)} value={formState.commentary}
                                  placeholder={t("formField.InternshipOfferModal.placeholder")}></textarea>
                    </div>

                    {/* Buttons */}
                    <div className="block space-y-4 sm:space-y-0 sm:flex sm:space-x-4 pt-5 ">
                        <button
                            className="w-full flex-1 text-white font-bold p-2 rounded-md bg-blue"
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