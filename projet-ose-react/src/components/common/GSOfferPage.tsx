import {useTranslation} from "react-i18next";
import {useLocation, useNavigate, useNavigation, useParams} from "react-router-dom";
import React, {useEffect, useRef, useState} from "react";
import {getOfferById, saveOfferReviewRequest} from "../../api/InterOfferJobAPI";
import {InterOfferJob} from "../../model/IntershipOffer";
import {ReactComponent as Icon} from '../../assets/icons/back_icon.svg';
import GSOfferDetails from "./GSOfferDetails";
import GSReviewOfferForm from "./GSReviewOfferForm";
import {Simulate} from "react-dom/test-utils";
import error = Simulate.error; // Adjust the import path to where your SVG is located

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


const GSOfferPage: React.FC<any> = () => {
    const {t} = useTranslation();
    const {id} = useParams();
    const [internshipOffer, setinternshipOffer] = useState<InterOfferJob>();
    const [formStateOffer, setFormState] = React.useState({
        comment: "", state: "", internOfferId: 0, internshipmanagerId: 6,
    });

    const [errorMessage, setErrorMessage] = useState<string | null>(null);
    const navigate = useNavigate();

    const fetchedOfferRef = useRef(false);

    const [errors, setErrors] = useState<{
        comment?: string,
    }>({});

    useEffect(() => {

        if (internshipOffer && internshipOffer.id) {
            setFormState(prevState => ({
                ...prevState, internOfferId: internshipOffer!.id!
            }));
        }

        //TODO remplacer par id
        if (internshipOffer && internshipOffer.employeurId) {
            setFormState(prevState => ({
                ...prevState, internshipmanagerId: 6
            }));
        }

    }, [internshipOffer]);


    useEffect(() => {
        const loadOffer = async () => {
            try {
                fetchedOfferRef.current = true

                const response = await getOfferById(parseInt(id!));
                //console.log(response)
                setinternshipOffer(response);

            } catch (error) {
                console.error('Error fetching offers:', error);
            }
            fetchedOfferRef.current = false;
        };
        if (!fetchedOfferRef.current) loadOffer();

    }, []);

    async function saveOfferReview(updatedFormState: any) {
        try {
            await saveOfferReviewRequest(updatedFormState);
            setFormState(updatedFormState);
        } catch (error: any) {
            console.error('Erreur lors de la sauvegarde de la revue de l\'offre:', error);
            setErrorMessage(error.response.data);
        }
    }

    function handleFormChange(e: React.ChangeEvent<HTMLTextAreaElement>) {
        const {name, value} = e.target;
        setFormState(prevState => ({
            ...prevState, [name]: value
        }));
    }

    async function handleApprove() {
        const updatedFormState = {
            ...formStateOffer,
            state: "ACCEPTED"
        };

        await saveOfferReview(updatedFormState);
    }

    async function handleDecline() {

        if (!formStateOffer.comment.trim()) {
            setErrors(prevErrors => ({
                ...prevErrors, comment: t("formField.GSOfferPage.validation.required")
            }));
            return;
        }

        if (formStateOffer.comment.length < 10) {
            setErrors(prevErrors => ({
                ...prevErrors, comment: t("formField.GSOfferPage.validation.minLenght")
            }));
            return;
        } else if (formStateOffer.comment.length > 1000) {
            setErrors(prevErrors => ({
                ...prevErrors, comment: t("formField.GSOfferPage.validation.maxLenght")
            }));
            return;
        } else if (/<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/.test(formStateOffer.comment.toLowerCase())) {
            setErrors(prevErrors => ({
                ...prevErrors, comment: t("formField.GSOfferPage.validation.scriptDetected")
            }));
            return;
        }

        const updatedFormState = {
            ...formStateOffer,
            state: "DECLINED"
        };

        await saveOfferReview(updatedFormState);
    }

    const renderError = () => (
        errors.comment ? (
            <p className="text-red text-xs mt-1 error-message" style={{minHeight: '30px'}}>
                {errors.comment}
            </p>
        ) : null
    );


    return (<>
        {errorMessage && <ErrorModal errorMessage={errorMessage} onClose={() => setErrorMessage(null)}/>}
        {internshipOffer && (
            <div className=''>
                <GSOfferDetails
                    handleFormChange={handleFormChange}
                    internshipOffer={internshipOffer}
                    renderError={renderError}
                />
                <GSReviewOfferForm handleApprove={handleApprove} handleDecline={handleDecline} internshipOffer={internshipOffer}/>
            </div>
        )}
    </>);
}

export default GSOfferPage;