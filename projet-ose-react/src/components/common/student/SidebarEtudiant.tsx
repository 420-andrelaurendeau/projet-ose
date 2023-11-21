import React, { useState } from "react";
import { faCircleUser, faFileArrowDown, faFileLines } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { NavLink, useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";

export default function SidebarEtudiant(props: any) {
    const option = window.location.pathname.split("/").pop();
    const { t } = useTranslation();

    return (
        <div className="fixed shadow">
            <div className="flex flex-col w-60 h-screen p-3 bg-white dark:bg-dark">
                <div className="space-y-3">
                    <div className="flex-1">
                        <ul className="pt-2 pb-4 space-y-3 text-sm">
                            <li className=" space-y-3">
                                <p className="text-xl font-bold flex justify-center">
                                    <FontAwesomeIcon icon={faCircleUser} className="w-20 h-auto text-blue dark:text-orange flex justify-center" />
                                </p>
                                <p className="text-xl dark:text-white font-bold flex justify-center">
                                    {props.user.prenom + ' ' + props.user.nom}
                                </p>
                                <div className="shadow h-1 flex justify-center" />
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "offre" ? "w-2 bg-blue dark:bg-orange rounded-lg" : "hidden"}>
                                </div>
                                <NavLink
                                    to="/etudiant/home/offre"
                                    state={props.user}
                                    className={"flex items-center p-2 space-x-3 rounded-md w-full" + (option === "offre" ? "" : " hover:bg-blue group dark:hover:bg-orange")}
                                    onClick={() => props.setIsOpen(false)}
                                >
                                    <FontAwesomeIcon icon={faFileLines} className="group-hover:text-white dark:text-white" size="lg" />
                                    <p aria-label={'internship-option'} className="text-black group-hover:text-white dark:text-white">{t('SideBarEtudiant.stage.text')}</p>
                                </NavLink>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "offreApplique" ? "w-2 bg-blue dark:bg-orange rounded-lg" : "hidden"}>
                                </div>
                                <NavLink
                                    to="/etudiant/home/offre/offreApplique"
                                    state={props.user}
                                    className={"flex items-center p-2 space-x-3 rounded-md w-full" + (option === "offreApplique" ? "" : " hover:bg-blue group dark:hover:bg-orange")}
                                    onClick={() => props.setIsOpen(false)}
                                >
                                    <FontAwesomeIcon icon={faFileArrowDown} className="group-hover:text-white dark:text-white" size="lg" />
                                    <p aria-label={"applied-internship-option"} className="text-black group-hover:text-white dark:text-white">{t('SideBarEtudiant.offre_applique.text')}</p>
                                </NavLink>

                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    );
}
