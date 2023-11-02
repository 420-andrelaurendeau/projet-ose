import {useTranslation} from "react-i18next";
import {useNavigate, useParams} from "react-router-dom";
import React, {JSX, useEffect, useRef, useState} from "react";
import {getStageById, signDocument} from "../../api/InternshipManagerAPI";
import {ReactPainter} from "react-painter";
import {ReactComponent as Icon} from '../../assets/icons/back_icon.svg';
import {useToast} from "../../hooks/state/useToast";
import {Document, Page, pdfjs} from "react-pdf";
// @ts-ignore
import sodapdf from '../../assets/images/sodapdf.pdf';
import useModal from "../../hooks/useModal";
pdfjs.GlobalWorkerOptions.workerSrc = `//unpkg.com/pdfjs-dist@${pdfjs.version}/legacy/build/pdf.worker.min.js`;


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

const ModalComponent: React.FC<{ children: any, onClose:any }> = ({children, onClose}) => {
    return (
        <div className="modal">
            <div className="modal-content">
                <button onClick={onClose} className="modal-close-button">Fermer</button>
                {children}
            </div>
        </div>
    );
};


const InternshipAgreementPage: React.FC<any> = () => {
    const {id} = useParams();
    const [intershipAggreement, setintershipAggreement] = useState<any>();
    const [isUpdate, setIsUpdate] = useState(false);
    const [canvasReset, setCanvasReset] = useState(0);

    const [errorMessage, setErrorMessage] = useState<string | null>(null);
    const navigate = useNavigate();
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.InternshipOfferList");

    const [signature, setSignature] = useState(null);

    const fetchedintershipAggreementRef = useRef(false);

    const toasts = useToast();

    const [modalOpen, setModalOpen] = useState(false);
    const [pageNumber, setPageNumber] = useState(1);



    function blobToBase64(blob: Blob, callback: (arg0: string | ArrayBuffer | null) => void) {
        const reader = new FileReader();

        reader.onload = () => {
            const base64String = reader.result;
            callback(base64String);
        };

        reader.readAsDataURL(blob);
    }

    const [signatureBase64, setSignatureBase64] = useState<string | ArrayBuffer | null>("");

    async function sendsignature(blop: Blob) {
        let signatureBase64: string | ArrayBuffer = "";
        let form;

        const reader = new FileReader();

        reader.onload = async () => {
            const base64String = reader.result;
            form = {
                id: 0,
                idStage: intershipAggreement.id,
                idEmployer: intershipAggreement.employeur.id,
                idStudent: intershipAggreement.etudiantDto.id,
                idInternOffer: intershipAggreement.internOfferDto.id,
                signatureInternShipManager: base64String,
                contract: ""
            }
            console.log(form);

            try {
                const response = await signDocument(form);
                toasts.success('Le document a été signé avec succès');

                console.log(response);
                const blob = base64toBlob("data:application/pdf;base64, "+response.contract);
                setSignatureBase64(URL.createObjectURL(blob));
            } catch (error) {
                toasts.error("Une erreur est survenue lors de la signature du document");
            }
        };

        reader.readAsDataURL(blop);

        openModal();
    }

    const openModal = () => {
        setModalOpen(true);
    };

    // Fonction pour gérer la fermeture de la modal
    const closeModal = () => {
        setModalOpen(false);
    };

    // Fonction pour gérer le changement de page
    const handlePageChange = (newPage: number) => {
        setPageNumber(newPage);
    };

    const base64toBlob = (data: string) => {
        // Cut the prefix `data:application/pdf;base64` from the raw base 64
        const base64WithoutPrefix = data.substr('data:application/pdf;base64,'.length);

        const bytes = atob(base64WithoutPrefix);
        let length = bytes.length;
        let out = new Uint8Array(length);

        while (length--) {
            out[length] = bytes.charCodeAt(length);
        }

        return new Blob([out], {type: 'application/pdf'});
    };


    const [errors, setErrors] = useState<{
        comment?: string,
    }>({});

    useEffect(() => {
        const fetchintershipAggreement = async () => {
            try {
                fetchedintershipAggreementRef.current = true;
                const response = await getStageById(id!);
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

    const clearCanvas = () => {
        setCanvasReset(canvasReset + 1);
    }


    const renderError = () => (
        errors.comment ? (
            <p className="text-red text-xs mt-1 error-message" style={{minHeight: '30px'}}>
                {errors.comment}
            </p>
        ) : null
    );
    const [numPages, setNumPages] = useState<number>();


    function onDocumentLoadSuccess({ numPages }: { numPages: number }): void {
        setNumPages(numPages);
    }


    return (<>
        {errorMessage && <ErrorModal errorMessage={errorMessage} onClose={() => setErrorMessage(null)}/>}
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


                <ReactPainter
                    key={canvasReset} // Cela forcera le composant à se rendre à nouveau lorsque canvasReset change
                    width={300}
                    height={300}
                    onSave={blob => sendsignature(blob)}
                    lineCap={"round"}
                    render={({triggerSave, canvas}) => (
                        <div className="text-center">
                            <div className="border border-1 border-black"
                                 style={{height: 300 + "px", width: 300 + "px"}}>
                                {canvas}
                            </div>
                            <button
                                className={`flex-1 text-white font-bold p-2 rounded-md bg-blue dark:bg-orange w-36 mx-8`}
                                onClick={triggerSave}>Signer le contrat
                            </button>
                            <button className={`flex-1 text-white font-bold p-2 rounded-md bg-blue dark:bg-orange w-36`}
                                    onClick={clearCanvas}>Clear
                            </button>
                        </div>
                    )}
                />
                {modalOpen && (
                    <ModalComponent onClose={closeModal}>
                        <Document file={signatureBase64} onLoadSuccess={onDocumentLoadSuccess}>
                            <Page pageNumber={pageNumber} />
                        </Document>
                        <div>
                            <p>
                                Page {pageNumber} of {numPages}
                            </p>
                            <button onClick={() => handlePageChange(pageNumber - 1)}>Page précédente</button>
                            <button onClick={() => handlePageChange(pageNumber + 1)}>Page suivante</button>
                        </div>
                    </ModalComponent>
                )}
            </div>


        )}
    </>);
}

export default InternshipAgreementPage


