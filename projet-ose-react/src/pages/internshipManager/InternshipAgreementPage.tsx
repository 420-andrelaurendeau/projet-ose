import {useTranslation} from "react-i18next";
import {NavLink, useNavigate, useParams} from "react-router-dom";
import React, {useEffect, useRef, useState} from "react";
import {getContractById, signDocument} from "../../api/InternshipManagerAPI";
import {ReactComponent as Icon} from '../../assets/icons/back_icon.svg';
import {useToast} from "../../hooks/state/useToast";
import {pdfjs} from "react-pdf";
import {faMagnifyingGlass, faPenNib} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import ViewPDFModal from "../../components/common/Employer/offer/ViewPDFModal";

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
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.internshipAgreement." + i18n.language.slice(0, 2) + ".agreement");

    const [isModalOpen, setIsModalOpen] = useState(false);
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
                <div className="flex w-full justify-end sm:w-3/4 sm:mx-auto items-center">
                    <button
                        type="button"
                        className="inline-flex items-center px-4 py-2 border border-transparent hover:border-black dark:border-white shadow-sm text-sm font-medium rounded-md text-neutral-900 bg-white hover:bg-neutral-50 dark:bg-dark dark:hover:bg-black dark:text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                        onClick={() => navigate("/internshipmanager/home/offers")}
                    >
                        Back <Icon className="w-5 h-5 fill-current hover:font-bold"/>
                    </button>
                </div>

                <h1 className="text-center dark:text-white text-3xl font-bold">{intershipAggreement.internOfferDto.title}</h1>

                <div
                    className="block sm:flex mt-5 sm:justify-between sm:items-start sm:w-3/4 sm:mx-auto dark:text-offwhite">

                    <div className='block items-center min-h-50'>


                        <div className="flex pb-4">
                            <h2 className="font-bold text-2xl">{fields.student}</h2>
                        </div>

                        <div className="ml-8 p-1">

                            <p className="p-1"> {intershipAggreement.etudiantDto.nom + " " + intershipAggreement.etudiantDto.prenom}</p>


                            <p className="p-1">{intershipAggreement.etudiantDto.email}</p>

                            <p className={"p-1 mt-3 px-2 xxxs:text-xs sm:text-sm inline-flex leading-5 font-semibold justify-center rounded-full w-3/4 text-white dark:text-offwhite "
                                + (intershipAggreement.signatureStudent ? "bg-green" : "bg-orange")}>
                                {intershipAggreement.signatureStudent ? fields.sign : fields.notsign}
                            </p>
                        </div>

                    </div>
                    <div className="block sm:flex flex-col sm:justify-end sm:items-end h-full sm:text-end">
                        <div className="flex pb-4">
                            <h2 className="font-bold text-2xl">{fields.employer}</h2>
                        </div>

                        <div className="sm:mr-8 ml-8 p-1">

                            {/* Company field */}
                            <p className="p-1"> {intershipAggreement.employeur.nom + " " + intershipAggreement.employeur.prenom}</p>

                            <p className="p-1"> {intershipAggreement.employeur.email}</p>

                            <p className={"p-1 mt-3 px-2 xxxs:text-xs sm:text-sm inline-flex leading-5 font-semibold justify-center rounded-full w-3/4 text-white dark:text-offwhite "
                                        + (intershipAggreement.signatureEmployer ? "bg-green" : "bg-orange")}>
                                {intershipAggreement.signatureEmployer ? fields.sign : fields.notsign}
                            </p>

                        </div>
                    </div>
                </div>

                <div
                    className="block pt-4 mt-5 sm:justify-between sm:items-start sm:w-3/4 sm:mx-auto dark:text-offwhite">
                    <h2 className="pb-4 font-bold text-2xl">Description</h2>
                    {intershipAggreement.internOfferDto.description}
                </div>

                <div className="block sm:flex w-1/2 sm:w-1/5 mx-auto pt-10 gap-x-4">
                    <button
                        className="inline-flex items-center px-4 py-2 border hover:border-black border-transparent dark:border-white shadow-sm text-sm font-medium rounded-md text-neutral-900 bg-white hover:bg-neutral-50 dark:bg-dark dark:hover:bg-black dark:text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                        onClick={() => setIsModalOpen(true)}>
                        <p className="text-xl">{fields.viewPDF}</p>
                        <FontAwesomeIcon icon={faMagnifyingGlass} className="ml-2" size="xl"/>
                    </button>
                    {
                        intershipAggreement.signatureInternShipManager == false && (
                            <button
                                className="inline-flex items-center px-4 py-2 border hover:border-black border-transparent dark:border-white shadow-sm text-sm font-medium rounded-md text-neutral-900 bg-white hover:bg-neutral-50 dark:bg-dark dark:hover:bg-black dark:text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500">
                                <NavLink to={""}
                                         className="flex items-center font-medium dark:text-offwhite dark:hover:text-amber-800">
                                    <p className="text-xl">{fields.signPDF}</p>
                                    <FontAwesomeIcon icon={faPenNib} className="ml-2" size="xl"/>
                                </NavLink>
                            </button>
                        )
                    }
                </div>
                {
                    context.file.content !== "" && isModalOpen &&
                    <div className="">
                        <ViewPDFModal ismodal={true} setIsModalOpen={setIsModalOpen} file={context.file}/>
                    </div>
                }
            </div>

        )}
    </>);
}


export default InternshipAgreementPage


