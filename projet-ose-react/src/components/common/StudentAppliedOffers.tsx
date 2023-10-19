import React, {useEffect} from "react";
import {getStudentAppliedOffers} from "../../api/InterOfferJobAPI";
import {AppliedOffers} from "../../model/AppliedOffers";
import {InterOfferJob} from "../../model/IntershipOffer";
import {FileEntity} from "../../model/FileEntity";
import {NavLink} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faFilePdf} from "@fortawesome/free-solid-svg-icons";
import {useProps} from "../../pages/EtudiantStagePage";

export default function StudentAppliedOffers() {
    const {appliedOffers, user} = useProps();

    return (
        <div className="flex flex-col">
            <div className=" lg:-mx-8 mt-28">
                <div className=" md:fixed md:z-50 md:top-0 md:left-0 justify-center md:w-full md:h-full md:bg-black md:bg-opacity-50 md:flex md:p-3 max-md:w-full ">
                    <NavLink
                        to="/etudiant/home/offre"
                        className="md:absolute max-md:hidden w-full h-full"
                        state={user}
                    />
                    <div className="md:pt-20 xxxs:px-6 lg:px-8 ">
                        <div className="overflow-auto border border-gray dark:border-darkgray xxxs:rounded-lg">
                            <div className=" w-full">
                                <table className=" w-full divide-y divide-gray dark:divide-darkgray">
                                    <thead className="bg-blue dark:bg-orange ">
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
                                            Location
                                        </th>
                                        <th
                                            scope="col"
                                            className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                        >
                                            Start date
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
                                            Files
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody className="bg-white dark:bg-dark divide-y divide-gray dark:divide-darkgray">
                                    {appliedOffers.map((appliedOffer:AppliedOffers) => (
                                        <tr key={appliedOffer.appliedOffer.id}>
                                            <td className="px-6 py-4 whitespace-nowrap">
                                                <div className="flex items-center">
                                                    <div className="ml-4">
                                                        <div className="text-sm font-medium dark:text-offwhite">{appliedOffer.appliedOffer.title}</div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap">
                                                <div className="text-sm dark:text-offwhite">{appliedOffer.appliedOffer.location}</div>
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap">
                                                <div className="text-sm dark:text-offwhite">{appliedOffer.appliedOffer.startDate?.toString()}</div>
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap">
                                                <div className="text-sm dark:text-offwhite">{appliedOffer.appliedOffer.employeurEntreprise}</div>
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap">
                                                <div className="text-sm dark:text-offwhite">
                                                    {appliedOffer.appliedFiles.map((file:FileEntity) => (
                                                        <div key={file.id} className="flex space-y-2">
                                                            <FontAwesomeIcon icon={faFilePdf} />
                                                            <a href="" download={file.fileName}>{file.fileName}</a>
                                                        </div>
                                                    ))}
                                                </div>
                                            </td>
                                        </tr>
                                    ))}
                                    </tbody>
                                </table>

                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    );
}