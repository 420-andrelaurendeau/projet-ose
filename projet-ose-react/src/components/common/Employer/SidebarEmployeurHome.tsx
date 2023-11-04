import React, {useState} from "react";
import {faCircleUser, faFileLines, faPencil, faSignature, faSpinner, faUsers} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {NavLink, useLocation, useParams} from "react-router-dom";
import {useTranslation} from "react-i18next";
import {useAuth} from "../../../authentication/AuthContext";

export default function SidebarEmployeurHome(props: any) {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.Header.sidebarEmployeur");
    const option = window.location.pathname.split("/").pop();
    const { userRole, logoutUser } = useAuth();

    console.log(option);
    return (
        <div className="fixed shadow h-full min-h-screen overflow-y-hidden hover:overflow-auto ">
            <div className="flex flex-col w-60 h-full  p-3 bg-white dark:bg-dark">
                <div className="space-y-3">
                    <div className="flex-1">
                        <ul className="pt-2 pb-4 space-y-3 text-sm">
                            <li className=" space-y-3 ">
                                <button onClick={props.onOpenProfil} className=" w-full">
                                    <p className="text-xl font-bold flex justify-center">
                                        <FontAwesomeIcon icon={faCircleUser} className="w-20 h-auto text-blue dark:text-orange flex justify-center" />
                                    </p>
                                    <p className="text-xl dark:text-white font-bold flex justify-center">
                                        {props.user.prenom + ' ' + props.user.nom}
                                    </p>
                                </button>
                                <div className="shadow h-1 flex justify-center"/>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "offers" ? "w-2 bg-blue dark:bg-orange rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to={"/" + userRole +"/home/offers"}
                                    className={"flex items-center p-2 space-x-3 rounded-md w-full" + (option === "offers" ? "" : " hover:bg-blue group dark:hover:bg-orange")}
                                    state={props.user}
                                    onClick={() => props.setIsOpen(false)}
                                >
                                    <FontAwesomeIcon icon={faFileLines} className="group-hover:text-white dark:text-white" size="lg"/>
                                    <p className="text-black group-hover:text-white dark:text-white">{fields.offre.text}</p>
                                </NavLink>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "application" ? "w-2 bg-blue dark:bg-orange rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to={"/" + userRole +"/home/application"}
                                    className={"flex items-center p-2 space-x-3 rounded-md w-full" + (option === "application" ? "" : " hover:bg-blue group dark:hover:bg-orange")}
                                    state={props.user}
                                    onClick={() => props.setIsOpen(false)}
                                >
                                    <FontAwesomeIcon icon={faUsers} className="group-hover:text-white dark:text-white" size="lg" />
                                    <p className="text-black group-hover:text-white dark:text-white">{fields.candidature.text}</p>
                                </NavLink>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "contract" ? "w-2 bg-blue dark:bg-orange rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to={"/" + userRole +"/home/contract"}
                                    className={"flex items-center p-2 space-x-3 rounded-md w-full" + (option === "contract" ? "" : " hover:bg-blue group dark:hover:bg-orange")}
                                    state={props.user}
                                    onClick={() => props.setIsOpen(false)}
                                >
                                    <FontAwesomeIcon icon={faSignature} className="group-hover:text-white dark:text-white" size="lg" />
                                    <p className="text-black group-hover:text-white dark:text-white">{fields.contract.text}</p>
                                </NavLink>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "pendingOffer" ? "w-2 bg-blue dark:bg-orange rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to={"/" + userRole +"/home/pendingOffer"}
                                    className={"flex items-center p-2 space-x-3 rounded-md w-full" + (option === "pendingOffer" ? "" : " hover:bg-blue group dark:hover:bg-orange")}
                                    state={props.user}
                                    onClick={() => props.setIsOpen(false)}
                                >
                                    <FontAwesomeIcon icon={faSpinner} className="group-hover:text-white dark:text-white" size="lg" />
                                    <p className="text-black group-hover:text-white dark:text-white">{fields.pendingOffer.text}</p>
                                </NavLink>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "newOffer" ? "w-2 bg-blue dark:bg-orange rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to={"/" + userRole +"/home/newOffer"}
                                    className={"flex items-center p-2 space-x-3 rounded-md w-full" + (option === "newOffer" ? "" : " hover:bg-blue group dark:hover:bg-orange")}
                                    state={props.user}
                                    onClick={() => props.setIsOpen(false)}
                                >
                                    <FontAwesomeIcon icon={faPencil} className="group-hover:text-white dark:text-white" size="lg" />
                                    <p className="text-black group-hover:text-white dark:text-white">{fields.newOffre.text}</p>
                                </NavLink>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    );
}