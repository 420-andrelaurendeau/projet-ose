import {ReactElement, useState} from "react";
import {useTranslation} from "react-i18next";
import {validateFile} from "../utils/validation/validationInteOfferForm";
import {FileEntity} from "../model/FileEntity";


function TeleversementCV(): ReactElement {

    const [files, setFiles] = useState<any[]>([])

    const [errors, setErrors] = useState<{
        file?: string
    }>({});

    const {t} = useTranslation();


    function handleFileChange(event: any) {
        let currFile: FileEntity = {fileName: event.target.files[0].name, content: "", isAccepted: true}

        let fileError: string = validateFile(currFile, t)
        setErrors(prevErrors => ({
            ...prevErrors, ['file']: fileError
        }));
        if (fileError === "") {
            files.push(event.target.files[0])
            setFiles(files => [...files])
        }

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

    return (
        <div className={"flex flex-col items-center justify-center"}>
            <div className={"w-2/4 mt-20 flex flex-col items-center justify-center"}>
                <h1 className={"text-4xl"}>Televerser votre CV</h1>
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
                            <p className="text-xs dark:text-gray">{t('formField.InternshipOfferForm.file.smallText')}
                                <span
                                    className="text-blue-600 cursor-pointer dark:text-gray">{t('formField.InternshipOfferForm.file.span')}</span>
                            </p>
                        </div>

                        {renderError(errors.file)}
                    </div>

                    <br/>
                    <div className={"flex flex-col items-center justify-center w-full"}>
                        <div className={"flex flex-col items-center justify-around w-full bg-gray"}>
                            {files.map((file, i) => {
                                return <div key={i}>
                                    {file["name"]}
                                </div>
                            })}
                        </div>
                    </div>
                    <br/>
                    <div className={"bg-blue text-white p-1 w-2/4 text-center"}>
                        Submit
                    </div>
                </form>
            </div>
        </div>
    )
}

export default TeleversementCV