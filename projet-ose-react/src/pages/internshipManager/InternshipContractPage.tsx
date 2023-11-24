import {useTranslation} from "react-i18next";
import {NavLink, useLocation, useNavigate, useParams} from "react-router-dom";
import React, {useEffect, useRef, useState} from "react";
import {getContractById, signDocument} from "../../api/InternshipManagerAPI";
import {ReactComponent as Icon} from '../../assets/icons/back_icon.svg';
import {useToast} from "../../hooks/state/useToast";
import {pdfjs} from "react-pdf";
import {faMagnifyingGlass, faPenNib} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import ViewPDFModal from "../../components/common/Employer/offer/ViewPDFModal";
import {useAuth} from "../../authentication/AuthContext";

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

const InternshipContractPage: React.FC<any> = () => {
    const {id} = useParams();
    const [intershipAggreement, setintershipAggreement] = useState<any>();

    const location = useLocation();
    const navigate = useNavigate();
    const {i18n} = useTranslation();
    const {t} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.internshipAgreement." + i18n.language.slice(0, 2) + ".agreement");
    const {userRole} = useAuth();
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
            } finally {
                fetchedintershipAggreementRef.current = false;
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
    const handleNavigation = () => {
        navigate(`/${userRole}/home/internshipagreement/${id}/contract`, { state: {contractId: id} });
    };


    return (<>
        {intershipAggreement && (
            <div className="h-max sm:pt-5 xxxs:pt-20 pb-6">
                <div className="flex pb-6 justify-end">
                    <button
                        type="button"
                        className="inline-flex px-4 py-2 border border-transparent hover:border-black dark:border-white shadow-sm text-sm font-medium rounded-md text-neutral-900 bg-white hover:bg-neutral-50 dark:bg-dark dark:hover:bg-black dark:text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                        onClick={() => navigate(`/${userRole}/home/internshipagreement`)}
                    >
                        {t("Shared.ReturnButton.text")} <Icon className="w-5 h-5 fill-current hover:font-bold"/>
                    </button>
                </div>


                <div className="bg-white dark:bg-dark py-6 rounded border border-gray dark:border-darkgray">
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

                    <div className="block sm:flex mx-auto w-fit pt-10 gap-x-4">
                        <button
                            className="inline-flex items-center px-4 py-2 border hover:border-black border-transparent dark:border-white shadow-sm text-sm font-medium rounded-md text-white bg-dark hover:bg-neutral-500 dark:bg-white dark:hover:bg-neutral-50 dark:text-neutral-900 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                            onClick={() => setIsModalOpen(true)}>
                            <p className="text-xl">{fields.viewPDF}</p>
                            <FontAwesomeIcon icon={faMagnifyingGlass} className="ml-2" size="xl"/>
                        </button>
                        {
                            userRole === "internshipmanager" && !intershipAggreement.signatureInternShipManager && (
                                <button
                                    className="inline-flex items-center px-4 py-2 border hover:border-black border-transparent dark:border-white shadow-sm text-sm font-medium rounded-md text-white bg-dark hover:bg-neutral-500 dark:bg-white dark:hover:bg-neutral-50 dark:text-neutral-900 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                                    onClick={() => handleNavigation()}>
                                    <p className="text-xl">{fields.signPDF}</p>
                                    <FontAwesomeIcon icon={faPenNib} className="ml-2" size="xl"/>
                                </button>
                            )
                        }
                        {
                            userRole === "employer" && !intershipAggreement.signatureEmployer && (
                                <button
                                    className="inline-flex items-center px-4 py-2 border hover:border-black border-transparent dark:border-white shadow-sm text-sm font-medium rounded-md text-white bg-dark hover:bg-neutral-500 dark:bg-white dark:hover:bg-neutral-50 dark:text-neutral-900 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                                    onClick={() => handleNavigation()}>
                                    <p className="text-xl">{fields.signPDF}</p>
                                    <FontAwesomeIcon icon={faPenNib} className="ml-2" size="xl"/>
                                </button>
                            )
                        }
                        {
                            userRole === "student" && !intershipAggreement.signatureStudent && (
                                <button
                                    className="inline-flex items-center px-4 py-2 border hover:border-black border-transparent dark:border-white shadow-sm text-sm font-medium rounded-md text-neutral-900 bg-white hover:bg-neutral-50 dark:bg-dark dark:hover:bg-black dark:text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                                    onClick={() => handleNavigation()}>
                                    <p className="text-xl">{fields.signPDF}</p>
                                    <FontAwesomeIcon icon={faPenNib} className="ml-2" size="xl"/>
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

            </div>

        )}
    </>);
}


export default InternshipContractPage


