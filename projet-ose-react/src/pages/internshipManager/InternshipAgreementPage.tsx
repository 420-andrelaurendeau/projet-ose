import {useTranslation} from "react-i18next";
import {useNavigate, useParams} from "react-router-dom";
import React, {JSX, useEffect, useRef, useState} from "react";
import {getContractById, signDocument} from "../../api/InternshipManagerAPI";
import {ReactPainter} from "react-painter";
import {ReactComponent as Icon} from '../../assets/icons/back_icon.svg';
import {useToast} from "../../hooks/state/useToast";
import {Document, Page, pdfjs} from "react-pdf";
// @ts-ignore
import sodapdf from '../../assets/images/sodapdf.pdf';
import useModal from "../../hooks/useModal";
import Modal from "react-modal";
import SignContract from "../../components/common/preparedoc/SignContract";

const InternshipAgreementPage: React.FC<any> = () => {
    const {id} = useParams();
    const [intershipAggreement, setintershipAggreement] = useState<any>();

    const [errorMessage, setErrorMessage] = useState<string | null>(null);
    const navigate = useNavigate();
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.InternshipOfferList");


    const fetchedintershipAggreementRef = useRef(false);

    const toasts = useToast();

    useEffect(() => {
        const fetchintershipAggreement = async () => {
            try {
                fetchedintershipAggreementRef.current = true;
                const response = await getContractById(id!);
                console.log(response)
                setintershipAggreement(response);
            } catch (error) {
                toasts.error("Une erreur est survenue lors du chargement de l'offre");
            }
        }

        if (!fetchedintershipAggreementRef.current)
            fetchintershipAggreement();


    }, []);

    useEffect(() => {
        document.title = fields.title;
    }, []);


    async function signContract(pdf: any) {
        let form = {
            id: 0,
            idStage: id,
            idEmployer: intershipAggreement.employeur.id,
            idStudent: intershipAggreement.etudiantDto.id,
            idInternOffer: intershipAggreement.internOfferDto.id,
            signatureInternShipManager: true,
            signatureEmployer: false,
            signatureStudent: false,
            contract: pdf
        }
        await signDocument(form)

        console.log(pdf)
    }


    return (<>
        {intershipAggreement && (
            <div className="h-max pt-20">
                <button
                    className="fixed z-10 top-20 left-4 p-2 bg-blue dark:bg-orange rounded-full shadow-lg text-offwhite hover:font-bold"
                    onClick={() => navigate("/internshipmanager/home/offers")}
                >
                    <Icon className="w-5 h-5 fill-current hover:font-bold"/>
                </button>

                <h1 className="text-center text-3xl font-bold"> Titre de l'offre de stage </h1>

                <div
                    className="block sm:flex mt-5 sm:justify-between sm:items-start sm:w-3/4 sm:mx-auto dark:text-offwhite">

                    <div className='block items-center min-h-50'>


                        <div className="flex pb-4">
                            <h2 className="font-bold text-2xl">Étudiant</h2>
                        </div>

                        <div className="ml-8 p-1">

                            <p className="p-1"> {intershipAggreement.etudiantDto.nom + " " + intershipAggreement.etudiantDto.prenom}</p>


                            <p className="p-1">{intershipAggreement.etudiantDto.email}</p>

                            <p className="p-1"> {intershipAggreement.stateStudent} </p>
                        </div>

                    </div>
                    <div className="block sm:flex flex-col sm:justify-end sm:items-end h-full sm:text-end">
                        <div className="flex pb-4">
                            <h2 className="font-bold text-2xl">Employeur</h2>
                        </div>

                        <div className="sm:mr-8 ml-8 p-1">

                            {/* Company field */}
                            <p className="p-1"> {intershipAggreement.employeur.nom + " " + intershipAggreement.employeur.prenom}</p>

                            <p className="p-1"> {intershipAggreement.employeur.email}</p>

                            <p className="p-1"> {intershipAggreement.stateEmployeur} </p>
                        </div>
                    </div>
                </div>

                <div className="px-20 mx-auto">
                    <SignContract pdfBase64={""} signContract={signContract}/>
                </div>


            </div>
        )}
    </>);
}

export default InternshipAgreementPage


