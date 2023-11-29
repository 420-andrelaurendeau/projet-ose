import {useTranslation} from "react-i18next";
import {useParams} from "react-router-dom";
import React, {useContext, useEffect, useRef, useState} from "react";
import {getOfferById, saveOfferReviewRequest, updateOfferReviewApi} from "../../api/InterOfferJobAPI";
import {InternshipOffer} from "../../model/IntershipOffer";
import InternshipManagerOfferDetails from "../../components/common/internshipManager/offer/InternshipManagerOfferDetails";
import InternshipManagerOfferReviewForm from "../../components/common/internshipManager/offer/InternshipManagerOfferReviewForm";
import {ToastContext} from "../../hooks/context/ToastContext";
import {useAuth} from "../../authentication/AuthContext"; // Adjust the import path to where your SVG is located

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


const InternshipManagerOfferPage: React.FC<any> = () => {
    const {id} = useParams();
    const [internshipOffer, setinternshipOffer] = useState<InternshipOffer>();
    const [isUpdate, setIsUpdate] = useState(false);

    
    const [formStateOffer, setFormState] = React.useState({
        comment: "", state: "", internOfferId: 0, internshipmanagerId: 6,
    });

    const [errorMessage, setErrorMessage] = useState<string | null>(null);
    const toast = useContext(ToastContext);

    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.InternshipOfferList");


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
                ...prevState, internshipmanagerId: 5
            }));
        }

    }, [internshipOffer]);

    useEffect(() => {
        document.title = fields.title;
    }, []);


    useEffect(() => {
        const loadOffer = async () => {
            try {

                fetchedOfferRef.current = true
                const response = await getOfferById(parseInt(id!));
                setinternshipOffer(response);

            } catch (error) {
                toast.error(fields.errorFetchOffer);
            } finally {
                fetchedOfferRef.current = false;
            }
        };
        if (!fetchedOfferRef.current) loadOffer();

    }, [isUpdate]);

    async function saveOfferReview(updatedFormState: any) {
        try {
            await saveOfferReviewRequest(updatedFormState);
            setIsUpdate(true);
            setFormState(updatedFormState);
        } catch (error: any) {
            toast.error(fields.errorSaveOfferReview);
        }
    }

    async function updateOfferReview(updatedFormState : any) {
        try {
            await updateOfferReviewApi(updatedFormState);
            setIsUpdate(true);
            setFormState(updatedFormState);
        } catch (error: any) {
            toast.error(fields.errorSaveOfferReview);
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

        await updateOfferReview(updatedFormState)

        toast.success(fields.succesSaveOfferReview, 1);
    }

    async function handleDecline() {

        if (!formStateOffer.comment.trim()) {
            setErrors(prevErrors => ({
                ...prevErrors, comment: fields.validation.required
            }));
            return;
        }

        if (formStateOffer.comment.length < 10) {
            setErrors(prevErrors => ({
                ...prevErrors, comment: fields.validation.minLenght
            }));
            return;
        } else if (formStateOffer.comment.length > 1000) {
            setErrors(prevErrors => ({
                ...prevErrors, comment: fields.validation.maxLenght
            }));
            return;
        } else if (/<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/.test(formStateOffer.comment.toLowerCase())) {
            setErrors(prevErrors => ({
                ...prevErrors, comment: fields.validation.scriptDetected
            }));
            return;
        }

        const updatedFormState = {
            ...formStateOffer,
            state: "DECLINED"
        };

        await saveOfferReview(updatedFormState);
        toast.success(fields.succesSaveOfferReview, 1);
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
            <div className={"max-md:pt-24 "}>
                <InternshipManagerOfferDetails
                    handleFormChange={handleFormChange}
                    internshipOffer={internshipOffer}
                    renderError={renderError}
                />
                <InternshipManagerOfferReviewForm handleApprove={handleApprove}
                                                  handleDecline={handleDecline}
                                                  internshipOffer={internshipOffer}
                />
            </div>
        )}
    </>);
}

export default InternshipManagerOfferPage;