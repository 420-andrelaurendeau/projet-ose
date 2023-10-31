import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faArrowDown19, faArrowDown91, faArrowDownAZ, faArrowUpZA, faEye} from "@fortawesome/free-solid-svg-icons";
import React, {useRef, useState} from "react";
import {InterOfferJob} from "../../../../model/IntershipOffer";
import InternshipManagerOfferPage from "../../../../pages/internshipManager/InternshipManagerOfferPage";
import useModal from "../../../../hooks/useModal";
import {useTranslation} from "react-i18next";
import {useNavigate} from "react-router-dom";
// todo : change the sort field with correct name of the api

export default function InternshipManagerInternshipsAgreement(props: any) {

    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.InternshipsAgreementPage.internshipsAgreement");

    const navigate = useNavigate();

    const handleOfferClick = (id: number) => {
        navigate(`/internshipmanager/home/offer/${id}`);
    };

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


    return (
        <div className="pt-4 pb-4">
            <div className="">
                <div className="bg-blue rounded dark:bg-orange">
                    <div className="flex p-2 items-center">

                        <div
                            role="columnheader"
                            className="hidden sm:visible xxxs:text-xs sm:text-sm w-1/5 px-2 font-bold text-offwhite uppercase tracking-wider cursor-pointer overflow-hidden truncate sm:flex"
                            onClick={() => handleSortClick("title")}
                        >
                            {fields.title}
                            <div
                                className={props.sortField === "title" ? "visible" : "hidden"}>
                                <FontAwesomeIcon
                                    icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                    color={"White"} className={"ml-2"}/>
                            </div>
                        </div>
                        <div
                            role="columnheader"
                            className="xxxs:text-xs sm:text-sm w-1/3 md:w-1/5  px-2 font-bold text-offwhite uppercase tracking-wider cursor-pointer overflow-hidden truncate flex"
                            onClick={() => handleSortClick("employeurEntreprise")}
                        >
                            {fields.enterprise}
                            <div
                                className={props.sortField === "employeurEntreprise" ? "visible" : "hidden"}>
                                <FontAwesomeIcon
                                    icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                    color={"White"} className={"ml-2"}/>
                            </div>
                        </div>
                        <div
                            role="columnheader"
                            className="xxxs:text-xs sm:text-sm w-1/3 md:w-1/5 px-2 font-bold text-offwhite uppercase tracking-wider cursor-pointer overflow-hidden truncate flex"
                            onClick={() => handleSortClick("location")}
                        >
                            {fields.student}
                            <div
                                className={props.sortField === "location" ? "visible" : "hidden"}>
                                <FontAwesomeIcon
                                    icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                    color={"White"} className={"ml-2"}/>
                            </div>
                        </div>
                        <div
                            role="columnheader"
                            className="xxxs:text-xs sm:text-sm w-1/4 md:w-1/5 px-2 font-bold text-offwhite uppercase tracking-wider cursor-pointer overflow-hidden truncate flex"
                            onClick={() => handleSortClick("state")}
                        >
                            <p className="text-xs">{fields.status}</p>
                            <div
                                className={props.sortField === "state" ? "visible" : "hidden"}>
                                <FontAwesomeIcon
                                    icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                    color={"White"} className={"ml-2"}/>
                            </div>
                        </div>
                        <div role="columnheader" className="md:w-2 w-1/12  relative ">
                            <span className="sr-only">View</span>
                        </div>
                    </div>
                </div>
                <div className="bg-white dark:bg-dark rounded border-b-2 border-x-2 border-gray rounded">

                </div>
            </div>
        </div>
    );
}

