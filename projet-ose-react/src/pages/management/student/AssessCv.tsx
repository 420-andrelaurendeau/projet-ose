import {useEffect, useState} from "react";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faFileSignature} from '@fortawesome/free-solid-svg-icons'
import axios from "axios";
import {File} from "../../../model/File";
import config from "tailwindcss/defaultConfig";

function EvaluerCV() {
    const [CvInView, setCvInView] = useState(null as File | null);
    const [displayAssessmentPopup, setDisplayAssessmentPopup] = useState(null as File | null);
    const [files, setFiles] = useState([] as Array<File>);

    async function getPendingFiles() {
        const response = await axios.get('/api/management/pending_cvs');
        console.log(response.data);
        setFiles(response.data as Array<File>);
    }

    async function ApproveFile(file: File) {
        return axios.post('/api/management/approve_cv?id=' + file.id).then((_) => getPendingFiles());
    }

    useEffect(() => {
        getPendingFiles();
    }, []);

    return (
        <>
            <div className="md:p-16 md:m-16 p-4 m-4">
                <h1 className="font-extrabold text-2xl">CVs &aacute; &Eacute;valuer</h1>
                {files.map((file) =>
                    <>
                        <div onClick={() => {
                            setCvInView(file)
                        }}
                             className="mx-12 my-16 px-7 py-3 bg-slate-50 hover:bg-slate-100 rounded-3xl flex flex-wrap">
                            <p className="basis-full flex-grow pb-2 md:pb-0">Nom, Prenom</p>
                            <p className="basis-full md:basis-1/2">Formation</p>
                            <p className="basis-full md:basis-1/2 md:text-end pb-2 md:pb-0">Matricule</p>
                            <p className="basis-full md:basis-1/2 flex-grow">{file.fileName}</p>
                            <p className="basis-full md:basis-1/2 flex-grow md:text-end">Date de Remise</p>
                        </div>
                    </>
                )}
            </div>

            {
                CvInView ?
                    (
                        <>
                            <div className="absolute top-0 bg-dark w-full h-8 z-20 flex justify-end items-center px-3 ">
                                <FontAwesomeIcon icon={faFileSignature} className="text-gray hover:text-white" onClick={_ => {
                                    setCvInView(null);
                                    setDisplayAssessmentPopup(CvInView);
                                }}/>
                            </div>
                            <div className="fixed bottom-0 bg-dark w-full h-full opacity-75 z-0" onClick={_ =>
                                setCvInView(null)
                            }></div>
                            <div className="absolute bottom-0 overflow-scroll no-scrollbar z-10 h-[calc(100vh-2rem)] -translate-x-1/2 left-1/2">
                                <div className="justify-end items-center px-3">
                                    <div
                                        className="my-5 bg-white w-[80vw] aspect-[216/279]">
                                        Je suis un document. Page 1
                                    </div>
                                    <div
                                        className="my-5 bg-white w-[80vw] aspect-[216/279]">
                                        Je suis un document. Page 2
                                    </div>
                                </div>
                            </div>
                        </>
                    ) : null
            }
            {
                displayAssessmentPopup ? (
                    <>
                        <div className="absolute bottom-0 bg-dark w-full h-full opacity-75 z-0" onClick={() =>
                            setDisplayAssessmentPopup(null)
                        }></div>
                        <div className="absolute bg-white rounded-md w-2/3 md:w-1/3 z-10 -translate-x-1/2 -translate-y-1/2 left-1/2 top-1/2">
                            <div className="flex flex-col justify-start h-full">
                                <h1 className="font-bold text-2xl relative px-5 py-3 w-100 text-center">Evaluer le CV: {displayAssessmentPopup.fileName}</h1>
                                <div className="flex flex-col justify-between px-5 py-3 grow-1 basis-full">
                                    <div className="flex flex-col">
                                        <label className="font-bold text-lg">Commentaires:</label>
                                        <textarea className="border-2 border-slate-200 rounded-md resize-none h-52"/>
                                    </div>
                                    <div className="flex flex-col md:flex-row justify-around transition-colors mb-5">
                                        <button className="bg-green-600 hover:bg-green-500 text-white rounded-md px-3 py-1 mt-3"
                                        onClick={
                                            _ =>
                                                ApproveFile(displayAssessmentPopup)
                                                    .then(_ => setDisplayAssessmentPopup(null))
                                        }>Accepter</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </>
                ) : null
            }
        </>
    );
}


export default EvaluerCV;