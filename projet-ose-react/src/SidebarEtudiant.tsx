import React, {useState} from "react";
import {faCircleUser, faFileLines} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {NavLink, useParams} from "react-router-dom";
import {useTranslation} from "react-i18next";

export default function SidebarEtudiant(props: any) {
    let { option } = useParams()
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.Header");

    return (
        <div className="fixed shadow">
            <div className="flex flex-col w-60 h-screen p-3 bg-white ">
                <div className="space-y-3">
                    <div className="flex-1">
                        <ul className="pt-2 pb-4 space-y-3 text-sm">
                            <li className=" space-y-3 ">
                                <p className="text-xl font-bold flex justify-center">
                                    <FontAwesomeIcon icon={faCircleUser} className="w-20 h-auto text-blue flex justify-center" />
                                </p>
                                <p className="text-xl font-bold flex justify-center">
                                    {props.user.prenom + ' ' + props.user.nom}
                                </p>
                                <div className="shadow h-1 flex justify-center"/>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "offer" ? "w-2 bg-blue rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to="/etudiantStage"
                                    state={props.user}
                                    className="flex items-center p-2 space-x-3 rounded-md w-full"
                                >
                                    <FontAwesomeIcon icon={faFileLines} color={option === "offer" ? "black" : "grey"} size="lg"/>
                                    <p className={option == "offer" ? "text-black" : "text-gray"}>{fields.sidebar.stage.text}</p>
                                </NavLink>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    );
}