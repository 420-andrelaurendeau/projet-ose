import React, {useEffect, useState} from "react";
import {getAllSeasons, getPageStudentAppliedOffers, getStudentAppliedOffers} from "../../../../api/InterOfferJobAPI";
import {AppliedOffers} from "../../../../model/AppliedOffers";
import {FileEntity} from "../../../../model/FileEntity";
import {NavLink, useLocation} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faDownload, faFilePdf} from "@fortawesome/free-solid-svg-icons";
import {useTranslation} from "react-i18next";
import {useProps} from "../../../../pages/student/StudentInternshipPage";
import ListItemCountSelector from "../../shared/paginationList/ListItemCountSelector";
import ListItemPageSelector from "../../shared/paginationList/ListItemPageSelector";
import {useAuth} from "../../../../authentication/AuthContext";

const getActualSeason = () => {
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    const currentMonth = currentDate.getMonth();
    let session = '';

    if (currentMonth >= 5 && currentMonth <= 8) {
        session = 'Été';
    } else if (currentMonth >= 9 || currentMonth <= 1) {
        session = 'Automne';
    } else {
        session = 'Hiver';
    }

    if (session === 'Été' || session === 'Automne') {
        return `Hiver${currentYear + 1}`;
    } else {
        return `Été${currentYear}`;
    }
}

export default function StudentAppliedOffers() {
    const {t} = useTranslation();
    const [appliedOffers, setAppliedOffers] = useState<AppliedOffers[]>([])

    const {userId} = useAuth();
    const [numberElementByPage, setNumberElementByPage] = useState<number>(100)
    const [sortField, setSortField] = useState("id");
    const [sortDirection, setSortDirection] = useState("asc");
    const [totalPages, setTotalPages] = useState(0);
    const [currentPage, setCurrentPage] = useState(0);
    const [seasons,setSeasons] = useState([])
    const [selectedOption, setSelectedOption] = useState(getActualSeason());

    const fetchData = async () => {
        try {
            console.log("user.id: ", userId);

            return await getPageStudentAppliedOffers(userId, currentPage, numberElementByPage, sortField, sortDirection, selectedOption);
        } catch (error) {
            console.log("Erreur lors de la récupération des offres:", error);
            return []
        }
    };

    useEffect(() => {
        fetchData().then((data) => {
            setAppliedOffers(data!);
        })
    }, [selectedOption, currentPage, numberElementByPage, sortField, sortDirection]);

    useEffect(() => {
        const fetchSeasons = async () => {
            let season = await  getAllSeasons();
            setSeasons(season);
        }

        fetchSeasons();
    }, []);

    const handleSortClick = (newSortField: any) => {

        console.log(`sortField: ${sortField}, sortDirection: ${sortDirection}`)
        if (newSortField === sortField && sortDirection === "desc") {
            setSortField("");
            setSortDirection("");
        } else if (newSortField === sortField && sortDirection === "asc") {
            setSortDirection("desc");
        } else {
            setSortField(newSortField);
            setSortDirection("asc");
        }
    };

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


    const handleChangePage = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setCurrentPage(0);
        setNumberElementByPage(Number(event.target.value));
    };

    const handlePageChange = (newPage: number) => {
        setCurrentPage(newPage);
    };

    const handleOptionChange = async (event: any) => {
        const selected = event.target.value;
        setSelectedOption(selected);
    };



    return (
        <div>
            <div className="flex flex-col justify-center">
                <div className="overflow-x-hidden xxxs:rounded-lg">
                    <h1 className="mt-7 text-start xxxs:text-2xl sm:text-3xl font-bold leading-9 tracking-tight text-black dark:text-white">
                        {t("formField.Header.sidebarEtudiant.appliedOffer.text")}
                    </h1>

                    <div className="flex justify-between py-4">
                        <div>
                            <label htmlFor="options" className="text-bold dark:text-white">Filtre par saison: </label>
                            <select className="rounded border border-black dark:border-white dark:bg-dark dark:text-white" id="options" value={selectedOption} onChange={handleOptionChange}>
                                <option value="">Tout</option>
                                {seasons.map((season: string, index: number) => (
                                    <option key={index} value={season}>
                                        {season}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <div>
                            <ListItemCountSelector
                                numberElement={numberElementByPage}
                                handleChangeNumberElement={handleChangePage}
                            />
                        </div>
                    </div>
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
                    <div className="">
                        <ListItemPageSelector page={currentPage} totalPages={totalPages} onPageChange={handlePageChange}/>
                    </div>
                </div>
            </div>
        </div>
    )
        ;
}