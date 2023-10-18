import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faArrowDown19, faArrowDown91, faArrowDownAZ, faArrowUpZA, faEye} from "@fortawesome/free-solid-svg-icons";
import React, {useState} from "react";
import {InterOfferJob} from "../../model/IntershipOffer";
import InternshipOfferModal from "./InternshipOfferModal";
import useModal from "../../hooks/useModal";


export default function GSOffers(props: any) {
    const {isModalOpen, handleOpenModal, handleCloseModal} = useModal();
    const [offer, setOffer] = useState<InterOfferJob>();

    const handleClick = (id: Number) => {
        setOffer(props.offers.filter((offer: any) => offer.id == id)[0]);
        handleOpenModal();
    }

    const handleSortClick = (newSortField: any) => {
        newSortField = "badSoerd";
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
        <div className="px-2 md:px-0 flex flex-col mt-14">
            <div className="-mx-6 lg:-mx-8">
                <div className="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
                    <div className="shadow overflow-hidden rounded-lg">
                        <div className="min-w-full divide-y divide-gray-200">
                            <div className="bg-blue dark:bg-orange">
                                <div className="flex p-3 items-center">

                                    <div
                                        role="columnheader"
                                        className=" md:w-1/5 w-2/3  px-2 text-xs font-medium text-gray-500 uppercase tracking-wider cursor-pointer overflow-hidden truncate flex "
                                        onClick={() => handleSortClick("title")}
                                    >
                                        Title
                                        <div
                                            className={props.sortField === "title" ? "visible" : "hidden"}>
                                            <FontAwesomeIcon
                                                icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                                color={"White"} className={"ml-2"}/>
                                        </div>
                                    </div>
                                    <div
                                        role="columnheader"
                                        className="hidden md:visible w-1/5 px-2 text-xs font-medium text-gray-500 uppercase tracking-wider cursor-pointer overflow-hidden truncate md:flex border-l border-black"
                                        onClick={() => handleSortClick("employeurEntreprise")}
                                    >
                                        Entreprise
                                        <div
                                            className={props.sortField === "employeurEntreprise" ? "visible" : "hidden"}>
                                            <FontAwesomeIcon
                                                icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                                color={"White"} className={"ml-2"}/>
                                        </div>
                                    </div>
                                    <div
                                        role="columnheader"
                                        className="hidden md:visible w-1/5 px-2 text-xs font-medium text-gray-500 uppercase tracking-wider cursor-pointer overflow-hidden truncate md:flex border-l border-black"
                                        onClick={() => handleSortClick("location")}
                                    >
                                        Location
                                        <div
                                            className={props.sortField === "location" ? "visible" : "hidden"}>
                                            <FontAwesomeIcon
                                                icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                                color={"White"} className={"ml-2"}/>
                                        </div>
                                    </div>
                                    <div
                                        role="columnheader"
                                        className="md:w-1/5 w-1/3 px-2 text-xs font-medium text-gray-500 uppercase tracking-wider cursor-pointer overflow-hidden truncate flex border-l border-black"
                                        onClick={() => handleSortClick("state")}
                                    >
                                        Status
                                        <div
                                            className={props.sortField === "state" ? "visible" : "hidden"}>
                                            <FontAwesomeIcon
                                                icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                                color={"White"} className={"ml-2"}/>
                                        </div>
                                    </div>
                                    <div
                                        role="columnheader"
                                        className="hidden md:visible w-1/5 px-2 text-xs font-medium text-gray-500 uppercase tracking-wider cursor-pointer overflow-hidden truncate md:flex border-l border-black"
                                        onClick={() => handleSortClick("startDate")}
                                    >
                                        Start date
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
                            <div className="bg-white dark:bg-dark ">
                                {props.offers.map((offer: InterOfferJob) => (
                                    <div role="row" className="flex p-3 border-b border-gray"
                                         key={offer.id}>

                                        <div role="cell"
                                             className="md:w-1/5 w-2/3 px-2 py-2 whitespace-nowrap truncate">
                                            <div
                                                className="text-sm font-medium text-gray-900 dark:text-offwhite">{offer.title}</div>
                                        </div>
                                        <div role="cell" className="hidden md:block w-1/5 px-2 py-2 whitespace-nowrap truncate border-l border-gray">
                                            <div
                                                className="text-sm font-medium text-gray-900 dark:text-offwhite">{offer.employeurEntreprise}</div>
                                        </div>
                                        <div role="cell" className="hidden md:block w-1/5 px-2 py-2 whitespace-nowrap truncate border-l border-gray">
                                            <div
                                                className="text-sm text-gray-500 dark:text-offwhite">{offer.location}</div>
                                        </div>
                                        <div role="cell" className="md:w-1/5 w-1/3 px-2 py-2 whitespace-nowrap truncate border-l border-gray">
                                            <span
                                                className={
                                                    offer.state == "PENDING" ?
                                                        "px-2 inline-flex text-xs leading-5 justify-center font-semibold rounded-full w-3/4 bg-orange text-white dark:text-offwhite border-l border-gray"
                                                        : offer.state === "DECLINED" ?
                                                            "px-2 inline-flex text-xs leading-5 font-semibold justify-center rounded-full w-3/4 bg-red text-white dark:text-offwhite border-l border-gray"
                                                            : "px-2 inline-flex text-xs leading-5 font-semibold rounded-full w-3/4 justify-center bg-green text-white dark:text-offwhite border-l border-gray"}
                                            >
                                                {offer.state}
                                            </span>
                                        </div>
                                        <div role="cell"
                                             className=" hidden md:block w-1/5 px-2 py-2 whitespace-nowrap truncate text-sm text-gray-500 dark:text-offwhite border-l border-gray">
                                            {offer.startDate!.toString()}
                                        </div>
                                        <div role="cell"
                                             className="md:w-10 w-6 px-2 py-2 text-center whitespace-nowrap  font-medium border-l border-gray">
                                            <FontAwesomeIcon icon={faEye}
                                                             className="text-indigo-600 hover:text-indigo-900 dark:text-orange"
                                                             onClick={() => handleClick(offer.id!)}/>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            {
                isModalOpen && (
                    <InternshipOfferModal
                        isModalOpen={isModalOpen}
                        handleCloseModal={handleCloseModal}
                        internshipOffer={offer}
                        isUpdate={props.isUpdate}
                    />
                )
            }
        </div>
    );
}


/**
 *
 return (
 <div className="flex flex-col mt-14">
 <div className=" xs:-mx-6 lg:-mx-8">
 <div className="max-md:pt-2 min-w-full xs:px-6 lg:px-8">
 <div className="border border-gray dark:border-darkgray xxxs:rounded-lg">
 <table className="w-full divide-y divide-gray dark:divide-darkgray">
 <thead className="bg-blue dark:bg-orange">
 <tr className="flex-auto">
 <th
 scope="col"
 className="flex-auto px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider cursor-pointer overflow-hidden"
 onClick={() => handleSortClick("title")}
 >
 Title
 </th>
 <th
 scope="col"
 className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider cursor-pointer overflow-hidden flex"
 onClick={() => handleSortClick("employeurEntreprise")}
 >
 Entreprise

 <div className={props.sortField === "employeurEntreprise" ? "visible" : "hidden"}>
 <FontAwesomeIcon icon={props.sortDirection === "asc" ?  faArrowDownAZ : faArrowUpZA } color={"White"} className={"ml-2"}/>
 </div>




 </th>
 <th
 scope="col"
 className="flex-1 px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider cursor-pointer"
 onClick={() => handleSortClick("location")}
 >
 Location
 </th>
 <th
 scope="col"
 className="flex-1 px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider cursor-pointer"
 onClick={() => handleSortClick("state")}
 >
 Status
 </th>
 <th
 scope="col"
 className="flex-1 px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider cursor-pointer"
 onClick={() => handleSortClick("startDate")}
 >
 Start date
 </th>
 <th scope="col" className=" flex-1 relative px-6 py-3">
 <span className="sr-only">View</span>
 </th>
 </tr>
 </thead>
 <tbody className="bg-white dark:bg-dark divide-y divide-gray dark:divide-darkgray">
 {props.offers.map((offer:any) => (
 <tr key={offer.id}>
 <td className="px-6 py-4 whitespace-nowrap">
 <div className="flex items-center">
 <div className="ml-4">
 <div className="text-sm font-medium dark:text-offwhite">{offer.title}</div>
 </div>
 </div>
 </td>
 <td className="px-6 py-4 whitespace-nowrap">
 <div className="flex items-center">
 <div className="ml-4">
 <div className="text-sm font-medium dark:text-offwhite">{offer.employeurEntreprise}</div>
 </div>
 </div>
 </td>
 <td className="px-6 py-4 whitespace-nowrap">
 <div className="text-sm dark:text-offwhite">{offer.location}</div>
 </td>
 <td className="px-6 py-4 whitespace-nowrap">
 <span
 className={
 offer.state == "PENDING" ?
 "px-2 inline-flex text-xs leading-5 justify-center font-semibold rounded-full w-3/4 bg-orange text-white dark:text-offwhite"
 : offer.state === "DECLINED" ?
 "px-2 inline-flex text-xs leading-5 font-semibold justify-center rounded-full w-3/4 bg-red text-white dark:text-offwhite"
 : "px-2 inline-flex text-xs leading-5 font-semibold rounded-full w-3/4 justify-center bg-green text-white dark:text-offwhite"}
 >
 {offer.state}
 </span>
 </td>
 <td className="px-6 py-4 whitespace-nowrap text-sm dark:text-offwhite">
 {offer.startDate}
 </td>

 <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
 <FontAwesomeIcon icon={faEye} className="dark:text-orange text-blue" size="lg" onClick={() => handleClick(offer.id)}/>
 </td>
 </tr>
 ))}
 </tbody>
 </table>
 </div>
 </div>
 </div>
 {
 isModalOpen && (
 <InternshipOfferModal
 isModalOpen={isModalOpen}
 handleCloseModal={handleCloseModal}
 internshipOffer={offer}
 isUpdate={props.isUpdate}
 />
 )
 }
 </div>
 );
 */