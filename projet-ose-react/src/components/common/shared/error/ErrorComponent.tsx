import {useTranslation} from "react-i18next";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTriangleExclamation} from "@fortawesome/free-solid-svg-icons";
import img from '../../../../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png';
import imgDark from '../../../../assets/images/Cegep-Andre-Laurendeau.png';
import {Link} from "react-router-dom";
import React from "react";

export default function ErrorComponent(props:any) {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","ErrorPage");
    return (
        <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
            <div className="flex items-center justify-center">
                <img
                    className="mx-auto h-20 w-auto visible dark:hidden"
                    src={img}
                    alt="Your Company"
                />
                <img
                    className="mx-auto h-20 w-auto hidden dark:flex"
                    src={imgDark}
                    alt="Your Company"
                />
            </div>
            <div className=" sm:mx-auto sm:w-full sm:max-w-md">
                <ul role="list" className="divide-y divide-gray-100">
                    <li className="flex justify-between gap-x-6 py-5">
                        <div className="flex min-w-0 gap-x-4">
                            <FontAwesomeIcon icon={faTriangleExclamation} className="h-12 w-12 flex-none rounded-full bg-gray-50 text-red-500" />
                            <div className="min-w-0 flex-auto">
                                <p className= {props.darkMode ?
                                    "mt-1 truncate text-xs leading-5 text-gray-500 text-white"
                                    : "mt-1 truncate text-xs leading-5 text-gray-500 text-black"}>Erreur 404</p>
                                <p className={props.darkMode ?
                                    "mt-1 truncate text-xs leading-5 text-gray-500 text-white"
                                    : "mt-1 truncate text-xs leading-5 text-gray-500 text-black"}>
                                    {fields.errorMessage.text}
                                </p>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div className="flex justify-center">
                <Link to={"/"}><button
                    type="button"
                    className="bg-blue hover:bg-cyan-900 text-white font-bold py-2 px-4 border border-blue rounded">
                    {fields.ReturnButton.text}
                </button></Link>
            </div>
        </div>
    );
}