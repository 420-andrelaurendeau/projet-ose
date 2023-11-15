import {useTranslation} from "react-i18next";
import {NavLink, Outlet, useNavigate, useOutletContext, useParams} from "react-router-dom";
import React, {useEffect, useRef, useState} from "react";
import {getContractById, signDocument} from "../../api/InternshipManagerAPI";
import {ReactComponent as Icon} from '../../assets/icons/back_icon.svg';
import {useToast} from "../../hooks/state/useToast";
import {pdfjs} from "react-pdf";
pdfjs.GlobalWorkerOptions.workerSrc = `//cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.js`;

interface InternshipAgreementPageProps {
    id: string,
    idEmployer: string,
    idStudent: string,
    idInternOffer: string,
    signatureInternShipManager: boolean,
    signatureEmployer: boolean,
    signatureStudent: boolean,
    contract: string

}

const InternshipAgreementPage: React.FC<any> = () => {
    const {id} = useParams();
    const [intershipAggreement, setintershipAggreement] = useState<any>();

    const navigate = useNavigate();
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.InternshipOfferList");

    const fetchedintershipAggreementRef = useRef(false);
    const toasts = useToast();

    useEffect(() => {
        const fetchintershipAggreement = async () => {
            try {
                fetchedintershipAggreementRef.current = true;
                await getContractById(id!).then(
                    (response) => {
                        console.log(response);
                        context.file.content = response.content;
                        setintershipAggreement(response);
                        console.log(context)
                    }
                );
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

    const context = {
        file: {
            content: intershipAggreement?.content!,
        },
        size: "0",
    }

    async function signContract(pdf: any) {
        let form = {
            id: id,
            idEmployer: intershipAggreement.employeur.id,
            idStudent: intershipAggreement.etudiantDto.id,
            idInternOffer: intershipAggreement.internOfferDto.id,
            signatureInternShipManager: true,
            signatureEmployer: false,
            signatureStudent: false,
            contract: pdf
        }

        try {
            await signDocument(form).then(
                () => {
                    toasts.success("Le contrat a été signé avec succès");
                    fetchedintershipAggreementRef.current = false;
                }
            );
        } catch (error) {
            toasts.error("Une erreur est survenue lors de la signature du contrat");
        }
    }


    return (<>
        {intershipAggreement && (
            <div className="h-max sm:pt-5 xxxs:pt-20">
                <button
                    className="fixed z-10 top-20 left-4 p-2 bg-blue dark:bg-orange rounded-full shadow-lg text-offwhite hover:font-bold"
                    onClick={() => navigate("/internshipmanager/home/internshipsagreement")}
                >
                    <Icon className="w-5 h-5 fill-current hover:font-bold"/>
                </button>

                <h1 className="text-center text-3xl font-bold">{intershipAggreement.internOfferDto.title}</h1>

                <div
                    className="block sm:flex mt-5 sm:justify-between sm:items-start sm:w-3/4 sm:mx-auto dark:text-offwhite">

                    <div className='block items-center min-h-50'>


                        <div className="flex pb-4">
                            <h2 className="font-bold text-2xl">Étudiant</h2>
                        </div>

                        <div className="ml-8 p-1">

                            <p className="p-1"> {intershipAggreement.etudiantDto.nom + " " + intershipAggreement.etudiantDto.prenom}</p>


                            <p className="p-1">{intershipAggreement.etudiantDto.email}</p>

                            <p className="p-1"> {intershipAggreement.signatureStudent == true ? "Signé" : "Non signé"} </p>
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

                            <p className="p-1"> {intershipAggreement.signatureEmployer == true ? "Signé" : "Non signé"} </p>
                        </div>
                    </div>
                </div>

                <div
                    className="block pt-4 mt-5 sm:justify-between sm:items-start sm:w-3/4 sm:mx-auto dark:text-offwhite">
                    <h2 className="pb-4 font-bold text-2xl">Description</h2>
                    {intershipAggreement.internOfferDto.description}
                </div>

                {
                    //TODO : Add the pdf button
                    // TODO : Add the signature button
                }

                <NavLink to={intershipAggreement?.fileName!}
                         className="font-medium text-blue hover:text-cyan-900 dark:text-orange dark:hover:text-amber-800">
                    {intershipAggreement?.fileName!}
                </NavLink>
                {/**
                 <div className="px-20 mx-auto">
                 <SignContract pdfBase64={""} signContract={signContract}/>
                 </div>
                 **/}

                <Outlet
                    context={context}
                />
            </div>

        )}
    </>);
}

interface Props {
    file: any;
    size: string;
}

export function useProps() {
    return useOutletContext<Props>();
}

export default InternshipAgreementPage


