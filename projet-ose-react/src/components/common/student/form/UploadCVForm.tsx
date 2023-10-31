import React, {ReactElement, useEffect, useState} from "react";
import {useTranslation} from "react-i18next";
import {validateFile} from "../../../../utils/validation/validationInteOfferForm";
import {FileEntity} from "../../../../model/FileEntity";
import axios from "axios";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCheck, faSpinner, faX} from "@fortawesome/free-solid-svg-icons";
import {useLocation} from "react-router-dom";
import {useAuth} from "../../../../authentication/AuthContext";
import {getUser} from "../../../../api/UtilisateurAPI";
import {saveCvStudent} from "../../../../api/StudentApi";
import {useToast} from "../../../../hooks/state/useToast";


function UploadCVForm(): ReactElement {

    let toast = useToast();

    let location = useLocation();

    const [files, setFiles] = useState<any[]>([])
    const [utilisateurs, setUtilisateurs] = useState([])
    const [user, setUser] = useState<any>(null)
    const [uploadState, setUploadState] = useState({status: "None"})
    const auth = useAuth();

    const [errors, setErrors] = useState<{
        file?: string
    }>({});

    const {t} = useTranslation();

    useEffect(() => {
        getUser(auth.userEmail!).then((res) => {
            setUser(res);
        })
    }, [])

    function handleFileChange(event: any) {
        let currFile: FileEntity = {fileName: event.target.files[0].name, content: "", isAccepted: false}
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
                setFiles(files => [...newFile])
            }
        }
        reader.readAsDataURL(file)
    }

    const renderError = (errorMsg: string | undefined) => (
        <p className="text-red text-xs mt-1 error-message" style={{minHeight: '30px'}}>
            {errorMsg || ""}
        </p>);

    const handleValidation = (fieldName: string) => {
        let fieldError = "";

        switch (fieldName) {
            case "file":
                fieldError = validateFile(files[0], t);
                break;
        }

        setErrors(prevErrors => ({
            ...prevErrors, [fieldName]: fieldError
        }));

    }

    const handleSubmit = async () => {
        console.log(files)
        setUploadState({status: "Uploading"})
        saveCvStudent(user!.matricule, files[0]).then(res => {
            console.log(res)
            setUploadState({status: "Done"})
            toast.success(t('cv.success'))
            setFiles([])
        }).catch(err => {
            console.log(err)
            toast.error(t('cv.error'))
            setUploadState({status: "Error"})
        })
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
                            name='file'
                            type="file"
                            className="absolute inset-0 z-50 m-0 p-0 w-full h-full outline-none opacity-0 cursor-pointer"
                            onChange={(e) => {
                                handleFileChange(e);
                            }}
                            onLoad={() => handleValidation("file")}
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
                    <div className={"bg-blue text-white p-1 w-2/4 text-center cursor-pointer"} onClick={handleSubmit}>
                        {t('cv.upload_button')} {renderUploadStatus()}
                    </div>


                </form>
            </div>
        </div>
    )
}

export default UploadCVForm