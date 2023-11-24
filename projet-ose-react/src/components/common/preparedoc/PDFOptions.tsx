import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faArrowPointer, faDownload, faFileSignature, faPenNib, faPrint} from "@fortawesome/free-solid-svg-icons";
import {useAuth} from "../../../authentication/AuthContext";
import React, {useEffect} from "react";
import {useLocation, useNavigate} from "react-router-dom";
import {useTranslation} from "react-i18next";

function PDFOptions(props: any) {
    const navigate = useNavigate();
    const {userRole } = useAuth();
    const {t} = useTranslation();

    return <div className="flex items-center justify-between top-20 left-0 right-0 z-[100] w-full">



        <div
            className="bg-white dark:bg-dark flex items-center p-2 rounded shadow-lg space-x-2 divide-x divide-neutral-200 dark:divide-darkgray">
            <div className="flex space-x-2">
                <div
                    className={`${props.optionsClassname} ${props.selectedOption === "none" ? "bg-blue dark:bg-orange text-white" : "hover:bg-neutral-200 hover:text-white dark:hover:text-white dark:hover:bg-darkgray"}`}
                    onClick={props.onClick}>
                    <FontAwesomeIcon icon={faArrowPointer} size={"lg"}/>
                </div>
                {
                    userRole === "internshipmanager" &&
                    <div
                        className={`${props.optionsClassname} ${props.selectedOption === "write" ? "bg-blue dark:bg-orange text-white" : "hover:bg-neutral-100 dark:hover:text-white dark:hover:bg-darkgray"}`}
                        onClick={props.onClick1}>
                        <FontAwesomeIcon icon={faPenNib} size={"lg"}/>
                    </div>
                }

                <div
                    className={`${props.optionsClassname} ${props.selectedOption === "sign" ? "bg-blue dark:bg-orange text-white" : "hover:bg-neutral-100 dark:hover:text-white dark:hover:bg-darkgray"}`}
                    onClick={props.onClick2}>
                    <FontAwesomeIcon icon={faFileSignature} size={"lg"}/>
                </div>
            </div>
            <div className="flex space-x-2">
                <div
                    className={`${props.optionsClassname} ${props.selectedOption === "print" ? "bg-blue dark:bg-orange text-white" : "hover:bg-neutral-100 dark:hover:text-white dark:hover:bg-darkgray"}`}
                    onClick={props.onClick3}
                >
                    <FontAwesomeIcon icon={faPrint} size={"lg"}/>
                </div>
                <div className={`${props.optionsClassname} hover:bg-neutral-100 dark:hover:text-white dark:hover:bg-darkgray`}
                     onClick={props.onClick4}
                >
                    <FontAwesomeIcon icon={faDownload} size={"lg"}/>
                </div>
            </div>
        </div>
        <div className="flex items-center">
            <button
                type="button"
                className="inline-flex px-4 py-2 border border-transparent hover:border-black dark:border-white shadow-sm text-sm font-medium rounded-md text-neutral-900 bg-white hover:bg-neutral-50 dark:bg-dark dark:hover:bg-black dark:text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                onClick={() => navigate(`/${userRole}/home/internshipagreement/` + props.contractId)}
            >
                {t("Shared.ReturnButton.text")}
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

    </div>;
}

export default PDFOptions;