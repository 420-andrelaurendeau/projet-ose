import React, {useState} from "react";
import {faCircleUser, faFileLines, faPencil, faSignature, faSpinner, faUsers} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {NavLink, useLocation, useParams} from "react-router-dom";

export default function SidebarEmployeurHome(props: any) {
    let { option } = useParams()
    const location = useLocation();
    return (
        <div className="fixed shadow h-full min-h-screen">
            <div className="flex flex-col w-60 h-full  p-3 bg-white dark:bg-dark">
                <div className="space-y-3">
                    <div className="flex-1">
                        <ul className="pt-2 pb-4 space-y-3 text-sm">
                            <li className=" space-y-3 ">
                                <p className="text-xl font-bold flex justify-center">
                                    <FontAwesomeIcon icon={faCircleUser} className="w-20 h-auto text-blue dark:text-orange flex justify-center" />
                                </p>
                                <p className="text-xl dark:text-white font-bold flex justify-center">
                                    {props.user.prenom + ' ' + props.user.nom}
                                </p>
                                <div className="shadow h-1 flex justify-center"/>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "offer" ? "w-2 bg-blue dark:bg-orange rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to="/home/offer"
                                    className="flex items-center p-2 space-x-3 rounded-md w-full"
                                    state={props.user}
                                    onClick={() => props.setIsOpen(false)}
                                >
                                    <FontAwesomeIcon icon={faFileLines} className={option == "offer" ? "text-black dark:text-white" : "text-gray"} size="lg"/>
                                    <p className={option == "offer" ? "text-black dark:text-white" : "text-gray"}>Offer</p>
                                </NavLink>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "candidature" ? "w-2 bg-blue dark:bg-orange rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to="/home/candidature"
                                    className="flex items-center p-2 space-x-3 rounded-md"
                                    state={props.user}
                                    onClick={() => props.setIsOpen(false)}
                                >
                                    <FontAwesomeIcon icon={faUsers} className={option == "candidature" ? "text-black dark:text-white" : "text-gray"} size="lg" />
                                    <p className={option == "candidature" ? "text-black dark:text-white" : "text-gray"}>Candidature</p>
                                </NavLink>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "contract" ? "w-2 bg-blue dark:bg-orange rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to="/home/contract"
                                    className="flex items-center p-2 space-x-3 rounded-md"
                                    state={props.user}
                                    onClick={() => props.setIsOpen(false)}
                                >
                                    <FontAwesomeIcon icon={faSignature} className={option == "contract" ? "text-black dark:text-white" : "text-gray"} size="lg" />
                                    <p className={option == "contract" ? "text-black dark:text-white" : "text-gray"}>Contract</p>
                                </NavLink>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "pendingOffer" ? "w-2 bg-blue dark:bg-orange rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to="/home/pendingOffer"
                                    className="flex items-center p-2 space-x-3 rounded-md"
                                    state={props.user}
                                    onClick={() => props.setIsOpen(false)}
                                >
                                    <FontAwesomeIcon icon={faSpinner} className={option == "pendingOffer" ? "text-black dark:text-white" : "text-gray"} size="lg" />
                                    <p className={option == "pendingOffer" ? "text-black dark:text-white" : "text-gray"}>Pending offer</p>
                                </NavLink>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "newOffer" ? "w-2 bg-blue dark:bg-orange rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to="/home/newOffer"
                                    className="flex items-center p-2 space-x-3 rounded-md"
                                    state={props.user}
                                    onClick={() => props.setIsOpen(false)}
                                >
                                    <FontAwesomeIcon icon={faPencil} className={option == "newOffer" ? "text-black dark:text-white" : "text-gray"} size="lg" />
                                    <p className={option == "newOffer" ? "text-black dark:text-white" : "text-gray"}>New offer</p>
                                </NavLink>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    );
}