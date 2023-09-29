import React, {useState} from "react";
import {faCircleUser, faFileLines, faPencil, faSignature, faSpinner, faUsers} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {NavLink, useParams} from "react-router-dom";

export default function Sidebar(props: any) {
    let { option } = useParams()

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
                                    to="/homeEmployeur/offer"
                                    className="flex items-center p-2 space-x-3 rounded-md w-full"
                                >
                                    <FontAwesomeIcon icon={faFileLines} color={option === "offer" ? "black" : "grey"} size="lg"/>
                                    <p className={option == "offer" ? "text-black" : "text-gray"}>Offer</p>
                                </NavLink>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "candidature" ? "w-2 bg-blue rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to="/homeEmployeur/candidature"
                                    className="flex items-center p-2 space-x-3 rounded-md"
                                >
                                    <FontAwesomeIcon icon={faUsers} color={option === "candidature" ? "black" : "grey"} size="lg" />
                                    <p className={option == "candidature" ? "text-black" : "text-gray"}>Candidature</p>
                                </NavLink>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "contract" ? "w-2 bg-blue rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to="/homeEmployeur/contract"
                                    className="flex items-center p-2 space-x-3 rounded-md"
                                >
                                    <FontAwesomeIcon icon={faSignature} color={option === "contract" ? "black" : "grey"} size="lg" />
                                    <p className={option == "contract" ? "text-black" : "text-gray"}>Contract</p>
                                </NavLink>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "pendingOffer" ? "w-2 bg-blue rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to="/homeEmployeur/pendingOffer"
                                    className="flex items-center p-2 space-x-3 rounded-md"
                                >
                                    <FontAwesomeIcon icon={faSpinner} color={option === "pendingOffer" ? "black" : "grey"} size="lg" />
                                    <p className={option == "pendingOffer" ? "text-black" : "text-gray"}>Pending offer</p>
                                </NavLink>
                            </li>
                            <li className="rounded-sm flex space-x-2">
                                <div className={option === "newOffer" ? "w-2 bg-blue rounded-lg": "hidden"}>
                                </div>
                                <NavLink
                                    to="/homeEmployeur/newOffer"
                                    className="flex items-center p-2 space-x-3 rounded-md"
                                >
                                    <FontAwesomeIcon icon={faPencil} color={option === "newOffer"? "black" : "grey"} size="lg" />
                                    <p className={option == "newOffer" ? "text-black" : "text-gray"}>New offer</p>
                                </NavLink>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    );
}