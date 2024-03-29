import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faArrowPointer, faDownload, faFileSignature, faPenNib, faPrint} from "@fortawesome/free-solid-svg-icons";
import {useAuth} from "../../../authentication/AuthContext";
import {ReactComponent as Icon} from '../../../assets/icons/back_icon.svg';
import React, {useEffect} from "react";
import {useLocation} from "react-router-dom";
import {useTranslation} from "react-i18next";
import {useNavigate, useParams} from "react-router-dom";

function PDFOptions(props: any) {
    const navigate = useNavigate();
    const {id} = useParams()
    const {userRole } = useAuth();
    const {t} = useTranslation();
    return (
        <div>
            <div className="flex items-center justify-between w-full pb-3">

                <button
                    type="button"
                    className="inline-flex items-center px-4 py-2 border border-transparent hover:border-black shadow-sm text-sm font-medium rounded-md text-white bg-red hover:bg-rose-900 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                    onClick={() => navigate(`/${userRole}/home/internshipagreement/` + props.contractId)}
                >
                    {t("Shared.ReturnButton.text")} <Icon className="w-5 h-5 fill-current hover:font-bold"/>
                </button>
                <button
                    type="button"
                    className="inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-green hover:bg-emerald-900 disabled:bg-gray hover:disabled:border-gray focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                    disabled={props.newContent===false}
                    onClick={props.submitContract}
                >
                    Enregistrer
                </button>
            </div>
            <div className="flex items-center justify-center w-full pb-3">
                <div
                    className="bg-white dark:bg-dark flex items-center p-2 rounded shadow-lg space-x-6 divide-x divide-neutral-200 dark:divide-darkgray">
                    <div className="flex space-x-4">
                        <div
                            className={`${props.optionsClassname} ${props.selectedOption === "none" ? " bg-blue dark:bg-orange text-white flex-col" : "hover:bg-neutral-200 hover:text-white dark:hover:text-white dark:hover:bg-darkgray flex-col"}`}
                            onClick={props.onClick}>
                            <FontAwesomeIcon icon={faArrowPointer} size={"lg"}/>
                            <p>{t("PDFoptions.cursor")}</p>
                        </div>
                        {
                            userRole === "internshipmanager" &&
                            <div
                                className={`${props.optionsClassname} ${props.selectedOption === "write" ? "bg-blue dark:bg-orange text-white flex-col" : "hover:bg-neutral-100 dark:hover:text-white dark:hover:bg-darkgray flex-col"}`}
                                onClick={props.onClick1}>
                                <FontAwesomeIcon icon={faPenNib} size={"lg"}/>
                                <p>{t("PDFoptions.write")}</p>
                            </div>
                        }

                        <div
                            className={`${props.optionsClassname} ${props.selectedOption === "sign" ? " bg-blue dark:bg-orange text-white flex-col" : "hover:bg-neutral-100 dark:hover:text-white dark:hover:bg-darkgray flex-col"}`}
                            onClick={props.onClick2}>
                            <FontAwesomeIcon icon={faFileSignature} size={"lg"}/>
                            <p>{t("PDFoptions.sign")}</p>
                        </div>
                    </div>
                    <div className="flex space-x-2 pl-5">
                        <div className={`${props.optionsClassname}  hover:bg-neutral-100 dark:hover:text-white dark:hover:bg-darkgray flex-col`}
                             onClick={props.onClick4}
                        >
                            <FontAwesomeIcon icon={faDownload} size={"lg"}/>
                            <p>{t("PDFoptions.download")}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        );
}

export default PDFOptions;