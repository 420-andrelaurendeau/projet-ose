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
        getStudentPendingCv().then(r => setFiles(r)).then(getStudentPendingCv).then(r => setFiles(r));
        console.log(files);
    }, []);

    return (
        <>
            <div className="md:p-16 p-4">
                <h1 className="text-center font-extrabold text-2xl">{fields.title}</h1>
                {files.map((file) =>
                    <>
                        <div
                             className="mx-12 my-16 px-7 py-3 bg-slate-50 hover:bg-slate-100 rounded-3xl flex flex-wrap justify-between">

                            <div className="flex flex-col md:flex-row md:flex-wrap">
                                <p className="basis-full flex-grow pb-2 md:pb-0">{file.etudiant?.nom}, {file.etudiant?.prenom}</p>
                                <p className="basis-full flex-grow">{file.etudiant?.matricule}</p>
                                <p className="basis-full flex-grow">{file.etudiant?.email}</p>
                                <p className="basis-full flex-grow">{file.fileName}</p>
                            </div>
                            <div className="flex flex-col md:flex-row md:flex-wrap">
                                <button
                                    className="text-blue-500 hover:text-blue-700"
                                    onClick={() => handleDownloadFile(file)}
                                >
                                    <FontAwesomeIcon icon={faDownload} className="scale-150" />
                                </button>
                            </div>
                            <div className="flex flex-col md:flex-row md:flex-wrap">
                                <button className="bg-green hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
                                        onClick={_ => ApproveFile(file)}>
                                    {fields.button.accept}
                                </button>
                                <button className="bg-red hover:bg-green-700 text-white font-bold py-2 px-6 rounded"
                                        onClick={_ => DeclineFile(file)}>
                                    {fields.button.decline}
                                </button>
                            </div>

                        </div>
                    </>
                )}
            </div>
        </>
    );
}


export default EvaluerCV;