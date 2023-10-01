import {useTranslation} from "react-i18next";
import React, {useEffect, useState} from "react";
import {saveOfferReviewRequest} from "../../api/InterOfferJobAPI";
import {validateDescription} from "../../utils/validation/validationInteOfferForm";
import {InterOfferJob} from "../../model/IntershipOffer";


const InternshipOfferModal: React.FC<any> = ({internshipOffer, isModalOpen, handleCloseModal, onUpdateInternshipOffer}) => {
    const {t} = useTranslation();
    const [formStateOffer, setFormState] = React.useState({
        comment: "", state: "", internOfferId: 0, internshipmanagerId: 1,
    });


    const [theme, setTheme] = useState("light");
    const [errors, setErrors] = useState<{
        description?: string,
    }>({});
    function handleFormChange(e: React.ChangeEvent<HTMLTextAreaElement>) {
        const {name, value} = e.target;
        setFormState(prevState => ({
            ...prevState, [name]: value
        }));
    }

    useEffect(() => {
        localStorage.getItem('theme') === 'dark' ? setTheme('dark') : setTheme('light');
    }, [localStorage.getItem('theme')]);

    useEffect(() => {
        if (internshipOffer && internshipOffer.id) {
            setFormState(prevState => ({
                ...prevState, internOfferId: internshipOffer.id
            }));
        }
            /**
             //TODO : Mettre à jour les valeurs de formState ici avec le bonne Id
             if (internshipOffer && internshipOffer.employeurId) {
             setFormState(prevState => ({
             ...prevState, internshipmanagerId: internshipOffer.employeurId
             }));
             }
             }
         */

    }, [internshipOffer]);

    async function handleApprove() {
        const updatedFormState = {
            ...formStateOffer,
            state: "ACCEPTED"
        };
        setFormState(updatedFormState);

        try {
            const response = await saveOfferReviewRequest(updatedFormState);
            console.log(response)
            onUpdateInternshipOffer(response);
            handleCloseModal();
        } catch (error) {
            console.error('Erreur lors de l\'approbation de l\'offre:', error);
        }
    }

    async function handleDecline() {
        // Validation pour le champ de commentaire
        if (!formStateOffer.comment.trim()) {
            setErrors(prevErrors => ({
                ...prevErrors, description: 'Veuillez fournir un commentaire pour refuser l\'offre.'
            }));
            return;
        }

        // Validation de la description (commentaire)
        const descriptionError = validateDescription(formStateOffer.comment, t);
        if (descriptionError) {
            setErrors(prevErrors => ({
                ...prevErrors, description: descriptionError
            }));
            return;
        } else {
            // Réinitialisez l'erreur de description si la validation est passée
            setErrors(prevErrors => ({
                ...prevErrors, description: undefined
            }));
        }

        const updatedFormState = {
            ...formStateOffer,
            state: "DECLINED"
        };
        setFormState(updatedFormState);

        try {
            const response = await saveOfferReviewRequest(updatedFormState);
            onUpdateInternshipOffer(response);
            handleCloseModal();

        } catch (error) {
            console.error('Erreur lors du refus de l\'offre:', error);
        }
    }

    const renderError = (errorMsg: string | undefined) => (
        errorMsg ? (
            <p className="text-red text-xs mt-1 error-message" style={{minHeight: '30px'}}>
                {errorMsg}
            </p>
        ) : null
    );



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
                                    {new Date(internshipOffer.startDate).toLocaleDateString('fr-FR', {
                                        day: '2-digit', month: '2-digit', year: 'numeric',
                                    })}
                                </p>

                                {/* End date field */}
                                <p className="p-1 dark:text-offwhite">
                                    {new Date(internshipOffer.endDate).toLocaleDateString('fr-FR', {
                                        day: '2-digit', month: '2-digit', year: 'numeric',
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
                        <svg xmlns="http://www.w3.org/2000/svg" fill={theme === `light` ? `#306bac` : `#F57A00`}
                             height="50" viewBox="0 -960 960 960" width="24">
                            <path
                                d="M320-240h320v-80H320v80Zm0-160h320v-80H320v80ZM240-80q-33 0-56.5-23.5T160-160v-640q0-33 23.5-56.5T240-880h320l240 240v480q0 33-23.5 56.5T720-80H240Zm280-520h200L520-800v200Z"/>
                        </svg>
                        <p className="mt-1 p-2 w-full dark:text-offwhite">{internshipOffer.file.fileName}</p>
                    </div>


                    {/* Commentaire field */}
                    <textarea name='comment'
                              className="mt-1 p-2 w-full border border-gray rounded-md placeholder:text-xs dark:bg-softdark dark:text-offwhite dark:border-0"
                              id="commentary_placeholder"
                              onChange={(e) => handleFormChange(e)}
                              placeholder={t("formField.InternshipOfferModal.placeholder")}></textarea>

                    {renderError(errors.description)}
                    {/* Buttons */}
                    <div className="block space-y-4 sm:space-y-0 sm:flex sm:space-x-4 pt-5 ">
                        <button
                            className="w-full flex-1 text-white font-bold p-2 rounded-md bg-blue dark:bg-orange"
                            type="button"
                            onClick={handleApprove}>
                            {t("formField.InternshipOfferModal.button.approved")}
                        </button>
                        <button
                            className="w-full flex-1 bg-red  text-white font-bold p-2 rounded-md dark:bg-red"
                            type="button"
                            onClick={handleDecline}>
                            {t("formField.InternshipOfferModal.button.refused")}
                        </button>

                    </div>
                </div>
            </div>
        </div>)}
    </>);
}

export default InternshipOfferModal;