import {useEffect, useState} from "react";
import {getStudentPendingCv, acceptStudentCv, declineStudentCv} from "../../../api/InternshipManagerAPI";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faDownload } from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import {ReviewFile} from "../../../model/ReviewFile";
import {useTranslation} from "react-i18next";
function EvaluerCV() {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","StudentCvEvaluation");
    const [files, setFiles] = useState([] as Array<ReviewFile>);


    async function ApproveFile(file: ReviewFile) {
        acceptStudentCv(file.id).then(r => {
            console.log(r);
        }).then(getStudentPendingCv).then(r => setFiles(r));
    }

    async function DeclineFile(file: ReviewFile) {
        declineStudentCv(file.id).then(r => {
            console.log(r);
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
            <div className="md:p-16 p-4">
                <h1 className="text-center font-extrabold text-2xl dark:text-gray">{fields.title}</h1>
                {files.map((file) =>
                    <>
                        <div className="mx-12 my-16 px-7 py-3 bg-slate-50 hover:bg-slate-100 rounded-3xl flex flex-col md:flex-row flex-wrap dark:bg-dark">
                            <div className="flex-item md:flex-row md:flex-wrap md:flex-grow">
                                <p className="basis-full flex-grow pb-2 md:pb-0 dark:text-white">{file.etudiant?.nom}, {file.etudiant?.prenom}</p>
                                <p className="basis-full flex-grow dark:text-white">{file.etudiant?.matricule}</p>
                                <p className="basis-full flex-grow dark:text-white">{file.etudiant?.email}</p>
                                <p className="basis-full flex-grow dark:text-white">{file.fileName}</p>
                            </div>
                            <div className="rounded bg-gray my-4 md:my-0 text-center sm:flex-grow md:flex-grow-0">
                                <button
                                    className="text-blue-500 py-2 sm:px-4 lg:px-10 hover:text-blue-700 text-center text-white align-middle h-full w-full"
                                    onClick={() => handleDownloadFile(file)}
                                >
                                    <p className="dark:text-white">{fields.button.download}</p>
                                    <FontAwesomeIcon icon={faDownload} className="scale-150 dark:text-white" />
                                </button>
                            </div>
                            <div className="flex-grow my-4 md:my-0">
                                <div className="flex flex-col md:flex-row md:justify-end h-full w-full flex-wrap">
                                    <button className="bg-green hover:bg-green-700 text-white font-bold py-2 rounded mb-3 md:px-4 lg:aspect-square md:h-full md:mr-4"
                                            onClick={_ => ApproveFile(file)}>
                                        {fields.button.accept}
                                    </button>
                                    <button className="bg-red hover:bg-red-700 text-white font-bold py-2 rounded md:px-4 lg:aspect-square md:h-full"
                                            onClick={_ => DeclineFile(file)}>
                                        {fields.button.decline}
                                    </button>
                                </div>
                            </div>
                        </div>
                    </>
                )}
            </div>
        </>
    );
}


export default EvaluerCV;