import React from "react";
import {faFileLines, faPencil, faSignature, faSpinner, faUsers} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

export default function Sidebar() {
    return (
        <div className="fixed">
            <div className="flex flex-col w-60 h-screen p-3 bg-white shadow ">
                <div className="space-y-3">
                    <div className="flex items-center">
                        <h2 className="text-xl font-bold">Dashboard</h2>
                    </div>
                    <div className="flex-1">
                        <ul className="pt-2 pb-4 space-y-1 text-sm">
                            <li className="rounded-sm flex active:space-x-2">
                                <div className="w-2 bg-blue rounded-lg">
                                </div>
                                <a
                                    href="#"
                                    className="flex items-center p-2 space-x-3 rounded-md"
                                >
                                    <FontAwesomeIcon icon={faFileLines} color="black" size="lg"/>
                                    <span>Offer</span>
                                </a>
                            </li>
                            <li className="rounded-sm">
                                <a
                                    href="#"
                                    className="flex items-center p-2 space-x-3 rounded-md"
                                >
                                    <FontAwesomeIcon icon={faUsers} color="black" size="lg" />
                                    <span>Candidature</span>
                                </a>
                            </li>
                            <li className="rounded-sm">
                                <a
                                    href="#"
                                    className="flex items-center p-2 space-x-3 rounded-md"
                                >
                                    <FontAwesomeIcon icon={faSignature} color="black" size="lg" />
                                    <span>Contract</span>
                                </a>
                            </li>
                            <li className="rounded-sm">
                                <a
                                    href="#"
                                    className="flex items-center p-2 space-x-3 rounded-md"
                                >
                                    <FontAwesomeIcon icon={faSpinner} color="black" size="lg" />
                                    <span>Pending offer</span>
                                </a>
                            </li>
                            <li className="rounded-sm">
                                <a
                                    href="#"
                                    className="flex items-center p-2 space-x-3 rounded-md"
                                >
                                    <FontAwesomeIcon icon={faPencil} color="black" size="lg" />
                                    <span>New offer</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    );
}