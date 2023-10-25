import React, {useEffect, useState} from "react";
import {getStudentAppliedOffers} from "../../api/InterOfferJobAPI";
import {AppliedOffers} from "../../model/AppliedOffers";
import {FileEntity} from "../../model/FileEntity";
import {NavLink, useLocation} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faFilePdf} from "@fortawesome/free-solid-svg-icons";
import {useTranslation} from "react-i18next";

export default function StudentAppliedOffers() {
    const {t} = useTranslation();
    const [appliedOffers, setAppliedOffers] = useState<AppliedOffers[]>([])
    let user = useLocation().state;

    const fetchData = async () => {
        try {
            return await getStudentAppliedOffers(user.id)

        } catch (error) {
            console.error("Erreur lors de la récupération des offres:", error);
            return []
        }
    };

    useEffect(() => {
        fetchData().then((data) => {
            setAppliedOffers(data!);
        });
    }, [])


    return (
        <div>
            <div className="flex flex-col items-center">
                <div className=" lg:-mx-8 mt-28 w-11/12 ">
                    <div
                        className=" md:z-50 md:top-0 md:left-0 justify-center md:w-full md:h-full md:flex md:p-3 max-md:w-full ">
                        <div className=" w-full">
                            <table className=" w-full divide-y divide-gray dark:divide-darkgray">
                                <thead className="bg-blue dark:bg-orange ">
                                <tr>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {t('formField.homeEmployeur.offerTable.titre.text')}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {t('formField.homeEmployeur.offerTable.location.text')}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {t('formField.homeEmployeur.offerTable.startDate.text')}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {t('formField.InternshipOfferList.table.enterprise')}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {t('formField.InternshipOfferForm.file.name')}

                                    </th>
                                </tr>
                                </thead>
                                <tbody className="bg-white dark:bg-dark divide-y divide-gray dark:divide-darkgray">
                                {appliedOffers.map((appliedOffer: AppliedOffers) => (
                                    <tr key={appliedOffer.appliedOffer.id}>
                                        <td className="px-6 py-4 whitespace-nowrap">
                                            <div className="flex items-center">
                                                <div className="ml-4">
                                                    <div
                                                        className="text-sm font-medium dark:text-offwhite">{appliedOffer.appliedOffer.title}</div>
                                                </div>
                                            </div>
                                        </td>
                                        <td className="px-6 py-4 whitespace-nowrap">
                                            <div
                                                className="text-sm dark:text-offwhite">{appliedOffer.appliedOffer.location}</div>
                                        </td>
                                        <td className="px-6 py-4 whitespace-nowrap">
                                            <div
                                                className="text-sm dark:text-offwhite">{appliedOffer.appliedOffer.startDate?.toString()}</div>
                                        </td>
                                        <td className="px-6 py-4 whitespace-nowrap">
                                            <div
                                                className="text-sm dark:text-offwhite">{appliedOffer.appliedOffer.employeurEntreprise}</div>
                                        </td>
                                        <td className="px-6 py-4 whitespace-nowrap">
                                            <div className="text-sm dark:text-offwhite">
                                                {appliedOffer.appliedFiles.map((file: FileEntity) => (
                                                    <div key={file.id} className="flex space-y-2">
                                                        <FontAwesomeIcon icon={faFilePdf}/>
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
    )
        ;
}