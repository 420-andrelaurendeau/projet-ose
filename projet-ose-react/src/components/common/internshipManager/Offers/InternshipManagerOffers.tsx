import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faArrowDown19, faArrowDown91, faArrowDownAZ, faArrowUpZA, faEye} from "@fortawesome/free-solid-svg-icons";
import React, {useState} from "react";
import {InterOfferJob} from "../../../../model/IntershipOffer";
import GSOfferPage from "../../../../pages/internshipManager/Offer/GSOfferPage";
import useModal from "../../../../hooks/useModal";
import {useTranslation} from "react-i18next";
import {useNavigate} from "react-router-dom";


export default function InternshipManagerOffers(props: any) {
    const [offer, setOffer] = useState<InterOfferJob>();

    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.InternshipOfferList");

    const navigate = useNavigate();

    const handleOfferClick = (id: number) => {
        navigate(`/gs/offer/${id}`);
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
                    <div className="flex p-3 items-center">

                        <div
                            role="columnheader"
                            className=" md:w-1/5 w-2/3 xxxs:text-sm sm:text-sm px-2 font-bold text-offwhite uppercase tracking-wider cursor-pointer overflow-hidden truncate flex "
                            onClick={() => handleSortClick("title")}
                        >
                            {fields.table.title}
                            <div
                                className={props.sortField === "title" ? "visible" : "hidden"}>
                                <FontAwesomeIcon
                                    icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                    color={"White"} className={"ml-2"}/>
                            </div>
                        </div>
                        <div
                            role="columnheader"
                            className="hidden sm:text-sm md:visible w-1/5 px-2 font-bold text-offwhite uppercase tracking-wider cursor-pointer overflow-hidden truncate md:flex"
                            onClick={() => handleSortClick("employeurEntreprise")}
                        >
                            {fields.table.enterprise}
                            <div
                                className={props.sortField === "employeurEntreprise" ? "visible" : "hidden"}>
                                <FontAwesomeIcon
                                    icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                    color={"White"} className={"ml-2"}/>
                            </div>
                        </div>
                        <div
                            role="columnheader"
                            className="hidden sm:text-sm md:visible w-1/5 px-2 font-bold text-offwhite uppercase tracking-wider cursor-pointer overflow-hidden truncate md:flex"
                            onClick={() => handleSortClick("location")}
                        >
                            {fields.table.location}
                            <div
                                className={props.sortField === "location" ? "visible" : "hidden"}>
                                <FontAwesomeIcon
                                    icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                    color={"White"} className={"ml-2"}/>
                            </div>
                        </div>
                        <div
                            role="columnheader"
                            className="md:w-1/5 xxxs:text-sm sm:text-sm w-1/3 px-2 font-bold text-offwhite uppercase tracking-wider cursor-pointer overflow-hidden truncate flex "
                            onClick={() => handleSortClick("state")}
                        >
                            {fields.table.status}
                            <div
                                className={props.sortField === "state" ? "visible" : "hidden"}>
                                <FontAwesomeIcon
                                    icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                    color={"White"} className={"ml-2"}/>
                            </div>
                        </div>
                        <div
                            role="columnheader"
                            className="hidden  sm:text-sm md:visible w-1/5 px-2 font-bold text-offwhite uppercase tracking-wider cursor-pointer overflow-hidden truncate md:flex "
                            onClick={() => handleSortClick("startDate")}
                        >
                            {fields.table.startDate}
                            <div
                                className={props.sortField === "startDate" ? "visible" : "hidden"}>
                                <FontAwesomeIcon
                                    icon={props.sortDirection === "asc" ? faArrowDown19 : faArrowDown91}
                                    color={"White"} className={"ml-2"}/>
                            </div>
                        </div>
                        <div role="columnheader" className="md:w-10 w-6  relative ">
                            <span className="sr-only">View</span>
                        </div>
                    </div>
                </div>
                <div className="bg-white dark:bg-dark rounded border-b-2 border-x-2 border-gray rounded">
                    {props.offers.map((offer: InterOfferJob) => (
                        <div role="row" className="flex p-3"
                             key={offer.id}>

                            <div role="cell"
                                 className="md:w-1/5 w-2/3 px-2 py-2 whitespace-nowrap truncate">
                                <div
                                    className="font-medium text-gray-900 dark:text-offwhite">{offer.title}</div>
                            </div>
                            <div role="cell" className="hidden md:block w-1/5 px-2 py-2 whitespace-nowrap truncate ">
                                <div
                                    className="font-medium text-gray-900 dark:text-offwhite">{offer.employeurEntreprise}</div>
                            </div>
                            <div role="cell" className="hidden md:block w-1/5 px-2 py-2 whitespace-nowrap truncate ">
                                <div
                                    className="text-gray-500 dark:text-offwhite">{offer.location}</div>
                            </div>
                            <div role="cell" className="md:w-1/5 w-1/3 px-2 py-2 whitespace-nowrap truncate">
                                            <span
                                                className={
                                                    offer.state == "PENDING" ?
                                                        "px-2  xxxs:text-xs sm:text-sm inline-flex leading-5 justify-center font-semibold rounded-full w-3/4 bg-orange text-white dark:text-offwhite"
                                                        : offer.state === "DECLINED" ?
                                                            "px-2 xxxs:text-xs sm:text-sm inline-flex leading-5 font-semibold justify-center rounded-full w-3/4 bg-red text-white dark:text-offwhite "
                                                            : "px-2 xxxs:text-xs sm:text-sm inline-flex leading-5 font-semibold rounded-full w-3/4 justify-center bg-green text-white dark:text-offwhite "}
                                            >
                                                {fields.table[offer.state]}
                                            </span>
                            </div>
                            <div role="cell"
                                 className=" hidden md:block w-1/5 px-2 py-2 whitespace-nowrap truncate text-sm text-gray-500 dark:text-offwhite ">
                                {offer.startDate!.toString()}
                            </div>
                            <div role="cell"
                                 className="md:w-10 w-6 px-2 py-2 text-center whitespace-nowrap  font-medium hover:cursor-pointer">
                                <FontAwesomeIcon icon={faEye}
                                                 className="text-indigo-600 hover:text-indigo-900 dark:text-orange"
                                                 onClick={() => handleOfferClick(offer.id!)}/>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

