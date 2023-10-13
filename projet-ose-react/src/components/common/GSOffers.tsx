import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faFileLines, faEye, faSignature, faUsers, faPencil} from "@fortawesome/free-solid-svg-icons";
import {NavLink} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {InterOfferJob} from "../../model/IntershipOffer";
import InternshipOfferModal from "./InternshipOfferModal";
import useModal from "../../hooks/useModal";


export default function GSOffers(props:any) {
    const {isModalOpen, handleOpenModal, handleCloseModal} = useModal();
    const [offer, setOffer] = useState<InterOfferJob>();

    const handleClick = (id: Number) => {
        setOffer(props.offers.filter((offer: any) => offer.id == id)[0]);
        handleOpenModal();
    }


    return (
        <div className="flex flex-col mt-14">
            <div className=" xs:-mx-6 lg:-mx-8">
                <div className="max-md:pt-2 min-w-full xs:px-6 lg:px-8">
                    <div className="border border-gray dark:border-darkgray xxxs:rounded-lg">
                        <table className="w-full divide-y divide-gray dark:divide-darkgray">
                            <thead className="bg-blue dark:bg-orange">
                            <tr>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                >
                                    Title
                                </th>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                >
                                    Entreprise
                                </th>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                >
                                    Location
                                </th>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                >
                                    Status
                                </th>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                >
                                    Start date
                                </th>
                                <th scope="col" className="relative px-6 py-3">
                                    <span className="sr-only">Edit</span>
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
                        onUpdateInternshipOffer={props.onUpdateInternshipOffer}
                    />
                )
            }
        </div>
    );
}