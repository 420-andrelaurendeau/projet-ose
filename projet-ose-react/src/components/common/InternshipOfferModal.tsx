import {useTranslation} from "react-i18next";
import {useLocation} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {saveOfferReviewRequest} from "../../api/InterOfferJobAPI";

const ErrorModal: React.FC<{ errorMessage: string; onClose: () => void }> = ({errorMessage, onClose}) => {
    return (
        <div className="fixed z-60 top-0 left-0 w-full h-full bg-black bg-opacity-50 flex justify-center items-center">
            <div className="bg-white rounded-lg p-6 w-full max-w-md dark:bg-dark">
                <h2 className='font-bold text-center text-red-600 text-xl dark:text-offwhite'>Erreur</h2>
                <p className="mt-4">{errorMessage}</p>
                <button onClick={onClose} className="mt-4 p-2 w-full bg-blue-500 text-white rounded-md">Fermer</button>
            </div>
        </div>
    );
}

const InternshipOfferModal: React.FC<any> = ({internshipOffer, isModalOpen, handleCloseModal, onUpdateInternshipOffer}) => {
    const {t} = useTranslation();
    const [formStateOffer, setFormState] = React.useState({
        comment: "", state: "", internOfferId: 0, internshipmanagerId: 6,
    });

    const [errorMessage, setErrorMessage] = useState<string | null>(null);
    const location = useLocation();
    const user = location.state;


    const [theme, setTheme] = useState("light");
    const [errors, setErrors] = useState<{
        comment?: string,
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

        //TODO remplacer par id
        if (internshipOffer && internshipOffer.employeurId) {
            console.log(user.id)
            setFormState(prevState => ({
                ...prevState, internshipmanagerId: 6
            }));
        }

    }, [internshipOffer]);

useEffect(() => {
    function handleKeyUp(event: KeyboardEvent) {
        if (event.key === 'Escape') {
            handleCloseModal();
        }
    }

    window.addEventListener('keyup', handleKeyUp);

    return () => window.removeEventListener('keyup', handleKeyUp);
}, [handleCloseModal]);


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
    } catch (error: any) {
        //console.error('Erreur lors de l\'approbation de l\'offre:', error);
        handleCloseModal();
        //setErrorMessage(error.response.data);
    }

}

async function handleDecline() {

    if (!formStateOffer.comment.trim()) {
        setErrors(prevErrors => ({
            ...prevErrors, comment: t("formField.InternshipOfferModal.validation.required")
        }));
        return;
    }


    if (formStateOffer.comment.length < 10) {
        setErrors(prevErrors => ({
            ...prevErrors, comment: t("formField.InternshipOfferModal.validation.minLenght")
        }));
        return;
    } else if (formStateOffer.comment.length > 1000) {
        setErrors(prevErrors => ({
            ...prevErrors, comment: t("formField.InternshipOfferModal.validation.maxLenght")
        }));
        return;
    } else if (/<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/.test(formStateOffer.comment.toLowerCase())) {
        setErrors(prevErrors => ({
            ...prevErrors, comment: t("formField.InternshipOfferModal.validation.scriptDetected")
        }));
        return;
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

    } catch (error: any) {
        console.error('Erreur lors du refus de l\'offre:', error);
        handleCloseModal();
        setErrorMessage(error.response.data);
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
    {errorMessage && <ErrorModal errorMessage={errorMessage} onClose={() => setErrorMessage(null)}/>}
    {isModalOpen && (<div className='flex justify-center items-center min-h-screen'>

        <div
            className="fixed z-50 top-0 left-0 w-full h-full bg-black bg-opacity-50 flex justify-center items-start p-3 overflow-y-auto">
            <div className="bg-white rounded-lg p-6 w-full max-w-xl dark:bg-dark">
                <div className="bg-white rounded-lg p-6 w-full max-w-xl dark:bg-dark relative">


                    <button
                        onClick={handleCloseModal}
                        className="absolute top-2 right-2 bg-red-600 dark:text-offwhite p-2 rounded-full focus:outline-none z-10"
                    >
                        X
                    </button>

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

                    {renderError(errors.comment)}
                    {/* Buttons */}
                    {internshipOffer.state === "PENDING" && (
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
                    )}

                </div>

            </div>
        </div>
    </div>)}
</>);
}

export default InternshipOfferModal;