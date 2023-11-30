import React, {useEffect, useState} from "react";
import ConnectForm from "../components/common/shared/authentication/ConnectForm";
import {LanguageIconEn, LanguageIconFr} from "../utils/language/LanguageIcons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faGreaterThan} from "@fortawesome/free-solid-svg-icons";
import {useTranslation} from "react-i18next";

function ConnectPage() {
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

    return (

        <div className="min-h-screen h-full w-full bg-white dark:bg-dark">
            <button
                className="md:pl-14 flex justify-between mt-2.5 w-full hover:bg-white dark:hover:bg-darkgray"
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
            <div className="items-center">
                <ConnectForm/>
            </div>
        </div>
    );
}
export default ConnectPage;