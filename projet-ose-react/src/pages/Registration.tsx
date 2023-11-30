import React, {useEffect, useState} from "react";
import {render} from "@testing-library/react";
import EtudiantInscription from "../components/common/student/form/StudentInscriptionForm";
import StudentInscriptionForm from "../components/common/student/form/StudentInscriptionForm";
import InscriptionEmployeur from "../components/common/Employer/inscriptionEmployeur";
import {useTranslation} from "react-i18next";
import {LanguageIconEn, LanguageIconFr} from "../utils/language/LanguageIcons";



function Registration() {
    const [isButtonClicked, setIsButtonClicked] = useState(false);
    const {i18n,t} = useTranslation();
    const [language, setLanguage] = useState(i18n.language.slice(0, 2));
    const [isSwitchLanguage, setIsSwitchLanguage] = useState(false);

    function changeLanguage() {
        setTimeout(() => {
            setIsSwitchLanguage(false)
        }, 1000);
        setIsSwitchLanguage(true);
        if (language === "fr") {
            i18n.changeLanguage("en-US").then(r => console.log(r)).catch(e => console.log(e))
        } else {
            i18n.changeLanguage("fr-FR").then(r => console.log(r)).catch(e => console.log(e))
        }
    }

    useEffect(() => {
        if (language !== i18n.language.slice(0, 2)) {
            setLanguage(i18n.language.slice(0, 2))
        }
    } , [i18n.language])

    const handleButtonClick = (value:boolean) => {
        setIsButtonClicked(value);
        console.log(isButtonClicked)
    };

    return(
        <>
            <div className="dark:bg-softdark">
                <button
                    className="md:pl-14 flex justify-between mt-2.5 w-full hover:bg-white dark:hover:bg-darkgray dark:bg-dark"
                    onClick={changeLanguage}
                    data-testid="change-language"
                >
                    <div className="flex w-full space-x-2">
                        {language === "en" ?
                            <LanguageIconFr bounce={isSwitchLanguage}/>
                            : <LanguageIconEn bounce={isSwitchLanguage}/>
                        }

                        <p className="text-black dark:text-gray w-10/12 text-start">
                            {t("formField.Header.profilMenu.changeLanguage.text")}
                        </p>
                    </div>
                </button>
                <div className="flex justify-center items-start dark:text-white">
                    <button className={
                        !isButtonClicked ?'m-4 p-2' : 'border-b-4 m-4 p-2 cursor-not-allowed'
                    }
                            onClick={() => handleButtonClick(true)}
                            disabled={isButtonClicked}
                    >
                        {t('formField.Header.InscriptionEmployer')}
                    </button>
                    <button className={
                        isButtonClicked ? 'm-4 p-2' : 'border-b-4 m-4 p-2 cursor-not-allowed'
                    }
                            onClick={() => handleButtonClick(false)}
                            disabled={!isButtonClicked}
                    >
                        {t('formField.Header.InscriptionStudent')}
                    </button>
                </div>
                <div className="flex justify-center items-start">
                    {isButtonClicked ? (
                        <InscriptionEmployeur/>
                    ) : (
                        <StudentInscriptionForm/>
                    )}
                </div>
            </div>

        </>
    );
}

export default Registration;