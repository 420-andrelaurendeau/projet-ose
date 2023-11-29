import React, {useEffect, useState} from "react";
import {getStudentPendingCv, acceptStudentCv, declineStudentCv} from "../../../api/InternshipManagerAPI";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faDownload } from "@fortawesome/free-solid-svg-icons";
import {ReviewFile} from "../../../model/ReviewFile";
import {useTranslation} from "react-i18next";
import {useToast} from "../../../hooks/state/useToast";
import ViewPDFModal from "../../../components/common/Employer/offer/ViewPDFModal"
function EvaluerCV() {
    const {t} = useTranslation();
    const [files, setFiles] = useState([] as Array<ReviewFile>);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [viewedPdf, setViewedPdf] = useState({} as ReviewFile);
    const toast = useToast();


    async function ApproveFile(file: ReviewFile) {
        acceptStudentCv(file.id).then(r => {
            console.log(r);
            toast.success(t("StudentCvEvaluation.toast.acceptSuccess"))
        }).catch((error) => {
            console.log(error);
            toast.error(t("StudentCvEvaluation.toast.acceptError"))
        }).then(getStudentPendingCv).then(r => setFiles(r));
    }

    async function DeclineFile(file: ReviewFile) {
        declineStudentCv(file.id).then(r => {
            console.log(r);
            toast.success(t("StudentCvEvaluation.toast.declineSuccess"))
        }).catch((error) => {
            console.log(error);
            toast.error(t("StudentCvEvaluation.toast.declineError"))
        }).then(getStudentPendingCv).then(r => setFiles(r));
    }

    const handleDownloadFile = (file: ReviewFile) => {
        // Create a Blob from the base64 content
        const byteCharacters = atob(file.content);
        const byteNumbers = new Array(byteCharacters.length);
        for (let i = 0; i < byteCharacters.length; i++) {
            byteNumbers[i] = byteCharacters.charCodeAt(i);
        }
        const byteArray = new Uint8Array(byteNumbers);
        const blob = new Blob([byteArray], { type: 'application/pdf' });

        // Create a URL for the blob and trigger the download
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = file.fileName;
        a.click();

        // Clean up the URL
        window.URL.revokeObjectURL(url);
    };


    useEffect(() => {
        getStudentPendingCv().then(r => setFiles(r));
        console.log(files);
    }, []);

    return (
        <>
            <div className="max-md:pt-24">
                <h1 className="text-start xxxs:text-2xl sm:text-3xl font-bold dark:text-gray">{t("StudentCvEvaluation.title")}</h1>
                {files.length === 0 && <p className="w-full h-min bg-red text-center text-white">{t("StudentCvEvaluation.noCv")}</p>}
                {files.map((file) =>
                    <>
                        <div key={file.id} className="flex mx-12 my-16 px-7 py-3 bg-slate-50 hover:bg-slate-100 rounded-3xl  flex-col md:flex-row flex-wrap dark:bg-dark">
                            <div className="flex-item lg:flex-row lg:flex-wrap md:flex-grow overflow-ellipsis pb-2 w-1/4">
                                <p className="basis-full flex-grow pb-2 lg:pb-0 dark:text-white">{file.etudiant?.nom}, {file.etudiant?.prenom}</p>
                                <p className="basis-full flex-grow dark:text-white">{file.etudiant?.matricule}</p>
                                <p className="basis-full flex-grow dark:text-white">{file.etudiant?.email}</p>
                                <p className="basis-full flex-grow dark:text-white">{file.fileName}</p>
                            </div>
                            <div className="md:mx-3 my-4 lg:my-0 text-center lg:flex-grow-0 pb-2 flex xxxs:flex-col md:flex-row ">
                                <button
                                    className="dark:text-orange text-blue rounded py-2 sm:px-4 lg:px-10 hover:text-blue-700 text-center align-middle h-full w-full"
                                    onClick={() => handleDownloadFile(file)}
                                >
                                    <p className="">{t("StudentCvEvaluation.button.download")}</p>
                                    <FontAwesomeIcon icon={faDownload} className="scale-150" />
                                </button>
                                <button className="font-medium text-blue hover:text-cyan-900 dark:text-orange dark:hover:text-amber-800"
                                        onClick={() => {
                                            setViewedPdf(file)
                                            setIsModalOpen(true)
                                        }}
                                >
                                    {t("StudentCvEvaluation.view")}
                                </button>
                            </div>
                            <div className="md:flex-grow my-4 lg:my-0 pb-2">
                                <div className="flex flex-col md:flex-row md:justify-end h-full w-full flex-wrap">

                                    <button className="bg-green hover:bg-green-700 text-white font-bold py-2 rounded mb-3 md:mb-0 md:mr-1 md:px-2 md:aspect-square lg:h-full lg:mr-4"
                                            onClick={_ => ApproveFile(file)}>
                                        {t('StudentCvEvaluation.button.accept')}
                                    </button>
                                    <button className="bg-red hover:bg-red-700 text-white font-bold py-2 rounded md:px-2 md:aspect-square lg:h-full"
                                            onClick={_ => DeclineFile(file)}>
                                        {t("StudentCvEvaluation.button.decline")}
                                    </button>
                                </div>
                            </div>
                        </div>
                        {
                            file && isModalOpen &&
                            <ViewPDFModal ismodal={true} file={viewedPdf} setIsModalOpen={setIsModalOpen} />
                        }
                    </>
                )}
            </div>
        </>
    );
}


export default EvaluerCV;