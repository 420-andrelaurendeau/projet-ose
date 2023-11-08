import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faArrowDownAZ, faArrowUpZA, faEye} from "@fortawesome/free-solid-svg-icons";
import React, {useEffect} from "react";
import {useTranslation} from "react-i18next";
import {useNavigate} from "react-router-dom";
// todo : change the sort field with correct name of the api

export default function InternshipManagerInternshipsAgreement(props: any) {

    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.InternshipsAgreementPage.internshipsAgreement");

    const navigate = useNavigate();

    useEffect(() => {
        console.log(props.offers)
    }, [props.offers]);

    const handleSortClick = (newSortField: any) => {
        if (newSortField === props.sortField && props.sortDirection === "desc") {
            props.setsortField("");
            props.setSortDirection("");
        } else if (newSortField === props.sortField) {
            props.setSortDirection((prevDirection: String) => (prevDirection === "asc" ? "desc" : "asc"));
        } else {
            props.setsortField(newSortField);
            props.setSortDirection("asc");
        }
        console.log(props.sortField === "employeurEntreprise" ? "visible" : "hidden")
    };

    //todo remove sort by status in tableit is redundant and doesnt really work

    return (
        <div className="pt-4 pb-4">
            <div className="">
                <div className="bg-blue rounded dark:bg-orange">
                    <div className="flex p-2 items-center">

                        <div
                            role="columnheader"
                            className="xxxs:text-xs sm:text-sm sm:w-1/5 w-1/2 px-2 font-bold text-offwhite uppercase tracking-wider cursor-pointer overflow-hidden truncate sm:flex"
                            onClick={() => handleSortClick("offer.title")}
                        >
                            {fields.title}
                            <div
                                className={props.sortField === "offer.title" ? "visible" : "hidden"}>
                                <FontAwesomeIcon
                                    icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                    color={"White"} className={"ml-2"}/>
                            </div>
                        </div>
                        <div
                            role="columnheader"
                            className="hidden sm:visible xxxs:text-xs sm:text-sm w-1/3 md:w-1/5  px-2 font-bold text-offwhite uppercase tracking-wider cursor-pointer overflow-hidden truncate sm:flex"
                            onClick={() => handleSortClick("employeur.entreprise")}
                        >
                            {fields.enterprise}
                            <div
                                className={props.sortField === "employeur.entreprise" ? "visible" : "hidden"}>
                                <FontAwesomeIcon
                                    icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                    color={"White"} className={"ml-2"}/>
                            </div>
                        </div>
                        <div
                            role="columnheader"
                            className="hidden sm:visible xxxs:text-xs sm:text-sm w-1/3 md:w-1/5 px-2 font-bold text-offwhite uppercase tracking-wider cursor-pointer overflow-hidden truncate sm:flex"
                            onClick={() => handleSortClick("student.nom")}
                        >
                            {fields.student}
                            <div
                                className={props.sortField === "student.nom" ? "visible" : "hidden"}>
                                <FontAwesomeIcon
                                    icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                    color={"White"} className={"ml-2"}/>
                            </div>
                        </div>

                        <div
                            role="columnheader"
                            className="xxxs:text-xs sm:text-sm sm:w-1/4 w-1/2 md:w-1/5 px-2 font-bold text-offwhite uppercase tracking-wider cursor-pointer overflow-hidden truncate flex"
                            onClick={() => handleSortClick("stateEmployeur")}
                        >
                            {fields.statut}
                            <div
                                className={props.sortField === "stateEmployeur" ? "visible" : "hidden"}>
                                <FontAwesomeIcon
                                    icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                    color={"White"} className={"ml-2"}/>
                            </div>
                        </div>
                        <div role="columnheader" className=" w-1/12">

                        </div>
                    </div>
                </div>
                <div className="bg-white dark:bg-dark rounded border-b-2 border-x-2 border-gray rounded">
                    {props.offers.map((offer: any) => (
                        <div role="row" className="flex p-3"
                             key={offer.id}>

                            <div role="cell"
                                 className="md:w-1/5 w-2/3 px-2 py-2 whitespace-nowrap truncate">
                                <div
                                    className="font-medium text-gray-900 dark:text-offwhite">{offer.internOfferDto.title}</div>
                            </div>
                            <div role="cell" className="hidden md:block w-1/5 px-2 py-2 whitespace-nowrap truncate ">
                                <div
                                    className="font-medium text-gray-900 dark:text-offwhite">{offer.employeur.entreprise}</div>
                            </div>
                            <div role="cell" className="hidden md:block w-1/5 px-2 py-2 whitespace-nowrap truncate ">
                                <div
                                    className="text-gray-500 dark:text-offwhite">{offer.etudiantDto.nom + " " + offer.etudiantDto.prenom}</div>
                            </div>

                            <div role="cell" className="md:w-1/5 w-1/3 px-2 py-2 whitespace-nowrap truncate">
                                            <span
                                                className={
                                                    (offer.stateEmployeur == "DECLINED" || offer.stateStudent == "DECLINED" ?
                                                        "px-2 xxxs:text-xs sm:text-sm inline-flex leading-5 font-semibold justify-center rounded-full w-3/4 bg-red text-white dark:text-offwhite " :
                                                        offer.stateEmployeur == "PENDING" || offer.stateStudent == "PENDING" ?
                                                            "px-2  xxxs:text-xs sm:text-sm inline-flex leading-5 justify-center font-semibold rounded-full w-3/4 bg-orange text-white dark:text-offwhite"
                                                            : "px-2 xxxs:text-xs sm:text-sm inline-flex leading-5 font-semibold rounded-full w-3/4 justify-center bg-green text-white dark:text-offwhite ")}
                                            >
                                                {offer.stateEmployeur == "DECLINED" || offer.stateStudent == "DECLINED" ?
                                                    fields.status.declined
                                                    : offer.stateEmployeur == "PENDING" || offer.stateStudent == "PENDING" ?
                                                        fields.status.pending
                                                        : fields.status.accepted
                                                }
                                            </span>
                            </div>

                            {
                                offer.stateEmployeur == "ACCEPTED" && offer.stateStudent == "ACCEPTED" ?
                                    <div role="cell"
                                         className="md:w-10 w-6 px-2 py-2 text-center whitespace-nowrap  font-medium hover:cursor-pointer">
                                        <FontAwesomeIcon icon={faEye}
                                                         className="text-indigo-600 hover:text-indigo-900 dark:text-orange"
                                                         onClick={() => props.handleOfferClick(offer.contractId!)}/>
                                    </div>: <></>
                            }
                        </div>
                    ))
                    }
                </div>
            </div>
        </div>
    );
}

/**
 */
