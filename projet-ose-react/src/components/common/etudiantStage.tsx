import React from "react";
import img from "../../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png";
import imgDark from "../../assets/images/Cegep-Andre-Laurendeau.png";
import {useTranslation} from "react-i18next";
import Header from "../../Header";
import {useLocation} from "react-router-dom";

function etudiantStage(props: any) {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.EtudiantStage");
    const location = useLocation();
    const {user} = location.state;

    return (
        <div>
            <Header/>
            <div className={"flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8"}>
                <div className="sm:mx-auto sm:w-full sm:max-w-sm">
                    <img
                        className={props.darkMode ? "mx-auto h-16 w-auto" : "mx-auto h-16 w-auto"}
                        src={props.darkMode ? imgDark : img}
                        alt="Cegep Andre Laurendeau"
                    />
                    <h1 className=
                            {props.darkMode ?
                                "mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-white"
                                : "mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-black"}>
                        {fields.titre.text}
                    </h1>
                    <p className="mt-2 text-center text-sm leading-5 text-gray-600 max-w">{user.prenom}</p>
                </div>
            </div>
        </div>

    )
}

export default etudiantStage;