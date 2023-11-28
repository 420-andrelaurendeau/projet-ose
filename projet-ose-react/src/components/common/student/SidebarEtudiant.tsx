import React, {useState} from "react";
import {faCircleUser, faFileLines, faPencil, faSignature, faSpinner, faUsers} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {NavLink, useLocation, useParams} from "react-router-dom";
import {useTranslation} from "react-i18next";
import {useAuth} from "../../../authentication/AuthContext";

export default function SidebarEtudiant(props: any) {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.Header.sidebarEtudiant");
    const option = window.location.pathname.split("/").pop();
    const { userRole, logoutUser } = useAuth();

    console.log(option);
    return (
        <div className="fixed shadow h-screen  bg-white dark:bg-dark">
            <div className="flex flex-col w-60 h-full overflow-auto p-3 ">
                <div className="space-y-3 pb-12">
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
                                <div className={option === "appliedOffers" ? "w-2 bg-blue dark:bg-orange rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to={"/" + userRole +"/home/appliedOffers"}
                                    className={"flex items-center p-2 space-x-3 rounded-md w-full" + (option === "appliedOffers" ? "" : " hover:bg-blue group dark:hover:bg-orange")}
                                    state={props.user}
                                    onClick={() => props.setIsOpen(false)}
                                >
                                    <FontAwesomeIcon icon={faPencil} className="group-hover:text-white dark:text-white" size="lg" />
                                    <p className="text-black group-hover:text-white dark:text-white">{fields.appliedOffer.text}</p>
                                </NavLink>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "cv" ? "w-2 bg-blue dark:bg-orange rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to={"/" + userRole +"/home/cv"}
                                    className={"flex items-center p-2 space-x-3 rounded-md w-full" + (option === "cv" ? "" : " hover:bg-blue group dark:hover:bg-orange")}
                                    state={props.user}
                                    onClick={() => props.setIsOpen(false)}
                                >
                                    <FontAwesomeIcon icon={faUsers} className="group-hover:text-white dark:text-white" size="lg" />
                                    <p className="text-black group-hover:text-white dark:text-white">{fields.cv.text}</p>
                                </NavLink>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "interview" ? "w-2 bg-blue dark:bg-orange rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to={"/" + userRole +"/home/interview"}
                                    className={"flex items-center p-2 space-x-3 rounded-md w-full" + (option === "interview" ? "" : " hover:bg-blue group dark:hover:bg-orange")}
                                    state={props.user}
                                    onClick={() => props.setIsOpen(false)}
                                >
                                    <FontAwesomeIcon icon={faSignature} className="group-hover:text-white dark:text-white" size="lg" />
                                    <p className="text-black group-hover:text-white dark:text-white">{fields.interview.text}</p>
                                </NavLink>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "stage" ? "w-2 bg-blue dark:bg-orange rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to={"/" + userRole +"/home/stage"}
                                    className={"flex items-center p-2 space-x-3 rounded-md w-full" + (option === "stage" ? "" : " hover:bg-blue group dark:hover:bg-orange")}
                                    state={props.user}
                                    onClick={() => props.setIsOpen(false)}
                                >
                                    <FontAwesomeIcon icon={faSpinner} className="group-hover:text-white dark:text-white" size="lg" />
                                    <p className="text-black group-hover:text-white dark:text-white">{fields.stage.text}</p>
                                </NavLink>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "internshipagreement" ? "w-2 bg-blue dark:bg-orange rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to={"/" + userRole +"/home/internshipagreement"}
                                    className={"flex items-center p-2 space-x-3 rounded-md w-full" + (option === "internshipagreement" ? "" : " hover:bg-blue group dark:hover:bg-orange")}
                                    state={props.user}
                                    onClick={() => props.setIsOpen(false)}
                                >
                                    <FontAwesomeIcon icon={faSpinner} className="group-hover:text-white dark:text-white" size="lg" />
                                    <p className="text-black group-hover:text-white dark:text-white">{fields.contract.text}</p>
                                </NavLink>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    );
}