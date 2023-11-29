import React, {useEffect, useState} from "react";
import {getStudentAppliedOffers} from "../../../../api/InterOfferJobAPI";
import {AppliedOffers} from "../../../../model/AppliedOffers";
import {FileEntity} from "../../../../model/FileEntity";
import {NavLink, useLocation} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faDownload, faFilePdf} from "@fortawesome/free-solid-svg-icons";
import {useTranslation} from "react-i18next";

export default function StudentAppliedOffers() {
    const {t} = useTranslation();
    const [appliedOffers, setAppliedOffers] = useState<AppliedOffers[]>([])
    let user = useLocation().state;
    const [seasons,setSeasons] = useState([])
    const [selectedOption, setSelectedOption] = useState('');


    const fetchData = async () => {
        try {
            return await getStudentAppliedOffers(user.id)
        } catch (error) {
            console.log("Erreur lors de la récupération des offres:", error);
            return []
        }
    };

    useEffect(() => {
        fetchData().then((data) => {
            setAppliedOffers(data!);
        })
    }, [])

    const handleDownloadFile = (file: FileEntity) => {
        // Create a Blob from the base64 content
        const byteNumbers = atob(file.content)
                                        .split('')
                                        .map((value) => value.charCodeAt(0))

        const byteArray = new Uint8Array(byteNumbers);
        const blob = new Blob([byteArray], { type: 'application/pdf' });

        // Create a URL for the blob and trigger the download
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = file.fileName;
        a.click();

        // Clean up the URL
        window.URL.revokeObjectURL(url);
    };


    return (
        <div>
            <div className="flex flex-col justify-center pt-24">
                <div className="overflow-x-hidden xxxs:rounded-lg">
                    <h1 className="mt-7 text-start xxxs:text-2xl sm:text-3xl font-bold leading-9 tracking-tight text-black dark:text-white">
                        {t("formField.Header.sidebarEtudiant.appliedOffer.text")}
                    </h1>
                    <div className="max-md:pt-2 min-w-full">
                        <div className="overflow-x-hidden hover:overflow-auto border border-gray dark:border-darkgray xxxs:rounded-lg">
                            <table className=" w-full divide-y divide-gray dark:divide-darkgray">
                                <thead className="bg-blue dark:bg-orange ">
                                <tr>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                                    >
                                        {t('formField.homeEmployeur.offerTable.titre.text')}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                                    >
                                        {t('formField.homeEmployeur.offerTable.location.text')}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                                    >
                                        {t('formField.homeEmployeur.offerTable.startDate.text')}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                                    >
                                        {t('formField.InternshipOfferList.table.enterprise')}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                                    >
                                        {t('formField.InternshipOfferForm.file.name')}

                                    </th>
                                </tr>
                                </thead>
                                <tbody className="bg-white dark:bg-dark divide-y divide-gray dark:divide-darkgray">
                                {appliedOffers.length === 0 && (
                                    <tr>
                                        <td colSpan={5} className="text-center bg-red text-white">
                                            {t('formField.EtudiantStage.noOffers.noAppliedOffers')}
                                        </td>
                                    </tr>
                                )}
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
                                                        <a onClick={() => handleDownloadFile(file)} href={''}>{file.fileName} <FontAwesomeIcon icon={faDownload} className="scale-150 dark:text-white"/></a>
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