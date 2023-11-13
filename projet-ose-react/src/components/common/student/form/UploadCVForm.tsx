import React, {ReactElement, useEffect, useState} from "react";
import {useTranslation} from "react-i18next";
import {validateFile} from "../../../../utils/validation/ValidateInternshipOfferForm";
import {FileEntity} from "../../../../model/FileEntity";
import axios from "axios";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCheck, faDownload, faSpinner, faX, faCheckSquare, faSquare} from "@fortawesome/free-solid-svg-icons";
import {useLocation} from "react-router-dom";
import {useAuth} from "../../../../authentication/AuthContext";
import {getUser} from "../../../../api/UtilisateurAPI";
import {saveCvStudent, fetchAllStudentCvs, setDefaultCv, fetchDefaultCvByStudentId} from "../../../../api/StudentApi";
import {useToast} from "../../../../hooks/state/useToast";
import {ReviewFile} from "../../../../model/ReviewFile";
import {User} from "../../../../model/User";


function UploadCVForm(): ReactElement {

    const toast = useToast(); // Move the hook call here
    const [files, setFiles] = useState<any[]>([])
    const [user, setUser] = useState<User>({} as User)
    const [uploadState, setUploadState] = useState({status: "None"})
    const [cvs, setCvs] = useState<ReviewFile[]>([]);
    const [cvDefault, setCvDefault] = useState<ReviewFile>({} as ReviewFile);
    const auth = useAuth();

    const [errors, setErrors] = useState<{
        file?: string
    }>({});

    const {t} = useTranslation();

    useEffect(() => {
        getUser(auth.userEmail!).then((res) => {
            setUser(res);
            console.log(user)
            fetchAllStudentCvs(res['id']).then((res) => {
                setCvs(res)
                console.log(cvs)
            }).catch((error) => {
                console.log("Error fetching user data:", error)
            });
            fetchDefaultCvByStudentId(res['id']).then((res) => {
                console.log(res);
                setCvDefault(res);
            }).catch((error) => {
                console.log("Error fetching user data:", error)
            });
        }).catch((error) => {
            console.log("Error fetching user data:", error)
        });

    }, [])

    function handleFileChange(event: any) {
        let currFile: FileEntity = {
            fileName: event.target.files[0].name,
            content: "",
            isAccepted: "PENDING",
            uploaderId: user.id
        }
        const file = event.target.files[0]
        const reader: FileReader = new FileReader();
        reader.onloadend = () => {
            const base64string: string | undefined = reader.result?.toString().split(',')[1]
            currFile.content = base64string || ""

            let fileError: string = validateFile(currFile, t)
            setErrors(prevErrors => ({
                ...prevErrors, ['file']: fileError
            }));
            if (fileError === "") {
                let newFile = [currFile]
                setFiles([...newFile])
            }
        }
        reader.readAsDataURL(file)
    }

    const renderError = (errorMsg: string | undefined) => (
        <p className="text-red text-xs mt-1 error-message" style={{minHeight: '30px'}}>
            {errorMsg || ""}
        </p>);

    const handleSubmit = async () => {
        console.log(files)
        if (files.length !== 0) {
            setUploadState({status: "Uploading"})
            saveCvStudent(parseInt(user!.matricule), files[0]).then(res => {
                console.log(res)
                setUploadState({status: "Done"})
                toast.success(t('cv.success'))
                setFiles([])
                fetchAllStudentCvs(user.id).then((res) => {
                    setCvs(res)
                }).catch((error) => {
                    console.log("Error fetching user data:", error)
                })
            }).catch(err => {
                console.log(err)
                toast.error(t('cv.error'))
                setUploadState({status: "Error"})
            })
        }
    }

    const renderUploadStatus = (): ReactElement | null => {
        switch (uploadState.status) {
            case "Uploading":
                return <FontAwesomeIcon icon={faSpinner} spin/>
            case "Done":
                return <FontAwesomeIcon icon={faCheck}/>
            case "Error":
                return <FontAwesomeIcon icon={faX}/>
            default:
                return null
        }
    }

    const handleDownloadFile = (file: ReviewFile) => {
        // Create a Blob from the base64 content
        const byteCharacters = atob(file.content);
        const byteNumbers = new Array(byteCharacters.length);
        for (let i = 0; i < byteCharacters.length; i++) {
            byteNumbers[i] = byteCharacters.charCodeAt(i);
        }
        const byteArray = new Uint8Array(byteNumbers);
        const blob = new Blob([byteArray], {type: 'application/pdf'});

        // Create a URL for the blob and trigger the download
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = file.fileName;
        a.click();

        // Clean up the URL
        window.URL.revokeObjectURL(url);
    };

    async function setDefaultFile(file: ReviewFile) {
        setDefaultCv(user.id, file.id).then(res => {
            console.log(res);
            toast.success(t('CV par défaut défini avec succès'));
        }).catch(err => {
            console.log(err);
            toast.error(t(err.response.data));
        }).finally(() => {
            fetchAllStudentCvs(user.id).then((res) => {
                setCvs(res);
            }).catch((error) => {
                console.log("Error fetching user data:", error);
            });
            fetchDefaultCvByStudentId(user.id).then((res) => {
                console.log(res);
                setCvDefault(res);
            }).catch((error) => {
                console.log("Error fetching user data:", error);
            });
        });
    }

    return (
        <div className={"flex flex-col items-center justify-center"}>
            <div className={"w-2/4 mt-20 flex flex-col items-center justify-center"}>
                <h1 className={"text-4xl"}>{t('formField.Header.cv.text')}</h1>
                <h2></h2>
                <br/>
                <form className={"flex flex-col items-center justify-center"}>
                    <div
                        className="border-dashed bg-offwhite border-2 h-32 relative dark:border-gray dark:bg-softdark pb-5 px-9">
                        <input
                            aria-label="file"
                            name='file'
                            type="file"
                            className="absolute inset-0 z-50 m-0 p-0 w-full h-full outline-none opacity-0 cursor-pointer"
                            onChange={(e) => {
                                handleFileChange(e);
                            }}
                        />
                        <div className="flex flex-col items-center justify-center py-10 text-center">
                            <p className="mb-2 dark:text-gray">{t('formField.InternshipOfferForm.file.text')}</p>
                            <p className="text-xs dark:text-gray">{t('formField.InternshipOfferForm.file.smallText') + " "}
                                <span
                                    className="text-blue-600 cursor-pointer dark:text-gray">{t('formField.InternshipOfferForm.file.span')}</span>
                            </p>
                        </div>

                        {renderError(errors.file)}
                    </div>

                    <br/>
                    <div className={"flex flex-col items-center justify-center w-full"}>
                        <div className={"flex flex-col items-center justify-around w-full "}>
                            {files.map((file, i) => {
                                return <div key={i}>
                                    {file["fileName"]}
                                </div>
                            })}
                        </div>
                    </div>
                    <br/>
                    <div aria-label="upload_button"
                         className={` text-white p-1 w-2/4 text-center ${files.length == 0 ? "cursor-default bg-gray" : "cursor-pointer bg-blue"}`}
                         onClick={handleSubmit}>
                        {t('cv.upload_button')} {renderUploadStatus()}
                    </div>
                </form>
            </div>
            {cvs.map((file) =>
                <>
                    <div
                        className="w-full my-2 px-4 py-1 bg-slate-50 hover:bg-slate-100 rounded-3xl flex flex-col md:flex-row flex-wrap dark:bg-dark">
                        <div className="flex-item lg:flex-row lg:flex-wrap md:flex-grow overflow-ellipsis pb-2">
                            <p className="basis-full dark:text-white">{file.fileName}</p>
                        </div>
                        <div className="flex-item overflow-ellipsis pb-2">
                            <button
                                className="text-blue-500 rounded bg-gray py-2 sm:px-4 lg:px-10 hover:text-blue-700 text-center text-white align-middle h-full w-full"
                                onClick={() => setDefaultFile(file)}
                            >
                                {file.id == cvDefault.id ? <FontAwesomeIcon icon={faCheckSquare} className="scale-150 dark:text-white"/> : <FontAwesomeIcon icon={faSquare} className="scale-150 dark:text-white"/>}
                            </button>
                        </div>
                        <div className="flex-item md:mx-3 my-4 lg:my-0 text-center lg:flex-grow-0 pb-2">
                            <button
                                className="text-blue-500 rounded bg-gray py-2 sm:px-4 lg:px-10 hover:text-blue-700 text-center text-white align-middle h-full w-full"
                                onClick={() => handleDownloadFile(file)}
                            >
                                <FontAwesomeIcon icon={faDownload} className="scale-150 dark:text-white"/>
                            </button>
                        </div>
                    </div>
                </>

            )}
        </div>
    )
}

export default UploadCVForm