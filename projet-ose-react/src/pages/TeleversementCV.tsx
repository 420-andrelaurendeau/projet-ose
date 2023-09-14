import {ReactElement, useState} from "react";
import {useTranslation} from "react-i18next";


function TeleversementCV(): ReactElement {

    const [files, setFiles] = useState<any[]>([])

    const {t} = useTranslation();

    function handleFileChange(event: any) {
        files.push(event.target.files[0])
        setFiles(files => [...files])
    }

    return (
        <div className={"w-2/4 mt-20 flex flex-col items-center justify-center"}>
            <h1 className={"text-4xl"}>Televerser votre CV</h1>
            <h2></h2>
            <br/>
            <form className={"flex flex-col items-center justify-center"}>
                <div className="border-dashed bg-offwhite border-2 h-32 relative dark:border-gray dark:bg-softdark">
                    <input
                        name={'files'}
                        type={"file"}
                        multiple
                        className={"absolute inset-0 z-50 m-0 p-0 w-full h-full outline-none opacity-0 cursor-pointer"}
                        onChange={handleFileChange}
                    />
                    <div className={"flex flex-col items-center justify-center py-10 text-center"}>
                        <p className={"mb-2 dark:text-gray"}>{t('formField.InternshipOfferForm.file.text')}</p>
                        <p className={"text-xs dark:text-gray"}>{t('formField.InternshipOfferForm.file.smallText')}
                            <span
                                className={"text-blue-600 cursor-pointer dark:text-gray"}>{t('formField.InternshipOfferForm.file.span')}</span>
                        </p>
                    </div>
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
    )
}

export default TeleversementCV