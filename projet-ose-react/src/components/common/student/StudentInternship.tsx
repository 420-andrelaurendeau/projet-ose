import {useTranslation} from "react-i18next";
import {Outlet, useNavigate} from "react-router-dom";
import {faBriefcase} from "@fortawesome/free-solid-svg-icons";
import {faArrowDownAZ, faArrowUpZA, faEye} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {useProps} from "../../../pages/student/StudentInternshipPage";
import {AppliedOffers} from "../../../model/AppliedOffers";
import React, {useEffect, useRef, useState} from "react";
import {useAuth} from "../../../authentication/AuthContext";
import {getUser} from "../../../api/UtilisateurAPI";
import {
    offresEtudiant,
    getStudentAppliedOffers,
    getOfferApprovedSeasons,
    getAllSeasons
} from "../../../api/InterOfferJobAPI";

import {saveStudentInternshipOffer} from "../../../api/intershipCandidatesAPI";
import {FileEntity} from "../../../model/FileEntity";
import {useToast} from "../../../hooks/state/useToast";
import {fetchDefaultCvByStudentId} from "../../../api/StudentApi";
import ListItemCountSelector from "../shared/paginationList/ListItemCountSelector";
import ListItemPageSelector from "../shared/paginationList/ListItemPageSelector";
import i18n from "i18next";
import {log} from "util";

interface Props {
    user: any;
    appliedOffers: AppliedOffers[];
}

function StudentInternship() {
    const {t} = useTranslation();
    const {
        offers,
        setOffers,
        setAppliedOffers,
        appliedOffers,
        page,
        setSortField,
        onPageChange,
        setCurrentPage,
        numberElementByPage,
        setSortDirection,
        sortDirection,
        sortField,
        totalPages,
        handleChangeNumberElement,
        seasons,
        selectedOption,
        handleChangeOption,
        user
    } = useProps();
    // const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.EtudiantStage");
    //const [offers, setOffers] = useState<any[]>([])
    const [cv, setCv] = useState<FileEntity>()
    //const [seasons, setSeasons] = useState([])
    //const [selectedOption, setSelectedOption] = useState('');
    const auth = useAuth();
    //const token = localStorage.getItem('token');
    const isloading = useRef(false);
    const [loadingCV, setLoadingCV] = useState<boolean>(false);
    const toast = useToast();
    const navigate = useNavigate();


    //TODO ajouter message errure pour CV pas trouver
    //TODO NO cv NO postulation

    const fecthData = async () => {
        setLoadingCV(true);
        isloading.current = true;
        try {
            const cvRes = await fetchDefaultCvByStudentId(user.id);
            setCv(cvRes);
            setLoadingCV(false);
        } catch (error) {
            console.log("Error fetching user data:", error);
            toast.error(t("formField.EtudiantStage.toast.ErrorNoCv"))
        } finally {
        }
    }

    useEffect(() => {
        if (!isloading.current) {
            fecthData().then(r => console.log(appliedOffers));
            isloading.current = false;
        }
    }, [user]);


    const applyOffer = (offer: any, student: any, cv: any) => {

        if (cv == null) {
            toast.error(t("formField.EtudiantStage.toast.ErrorNoCv"))
        } else {
            saveStudentInternshipOffer(offer, student, cv).then(
                async res => {
                    await getStudentAppliedOffers(user.id).then((res) => {
                        setAppliedOffers(res)
                    })
                    toast.success(t("formField.EtudiantStage.toast.SuccessOfferApplication") + " " + offer.title)

                }
            ).catch(
                err => {
                    console.log(err);
                    toast.error(t("formField.EtudiantStage.toast.ErrorOfferApplication"))
                }
            )
        }
    }

    const handleSortClick = (newSortField: any) => {
        if (newSortField === sortField && sortDirection === "desc") {
            setSortField("");
            setSortDirection("");
        } else if (newSortField === sortField) {
            setSortDirection((prevDirection: String) => (prevDirection === "asc" ? "desc" : "asc"));
        } else {
            setSortField(newSortField);
            setSortDirection("asc");
        }
    };

    const handleOfferClick = (id: number) => {
        navigate(`/student/home/offers/${id}`, {state: context});
    };


    const context: Props = {
        user: user,
        appliedOffers: appliedOffers,
    }

    const shouldButtonBeDisabled = (id:number) => {
        let returnValue = appliedOffers.find((appliedOffer: AppliedOffers) => appliedOffer.appliedOffer.id === id) != null || loadingCV
        return appliedOffers.find((appliedOffer: AppliedOffers) => appliedOffer.appliedOffer.id === id) != null || loadingCV

    }

    return (
        <div className="flex flex-col mt-14">
            <div
                className={window.location.pathname != "/etudiant/home/offre" && window.location.pathname != "/etudiant/home/offre/" ? "max-md:hidden" : ""}>
                <div className="max-md:pt-2 min-w-full xs:px-6 lg:px-8 ">
                    <div className="overflow-x-hidden xxxs:rounded-lg space-y-6">
                        <div className="flex items-center justify-center">
                            <FontAwesomeIcon icon={faBriefcase} className="text-blue dark:text-orange h-16"/>
                        </div>
                        <h1 aria-label={"component-title"}
                            className="mt-7 text-center text-2xl font-bold leading-9 tracking-tight text-black dark:text-white">
                            {t("formField.EtudiantStage.titre.text")}
                        </h1>
                        {
                            loadingCV ?
                                <span
                                    className="flex justify-center dark:text-white">{t("formField.EtudiantStage.cv")}</span>
                                :
                                <span></span>
                        }
                        <div className="pb-4">
                            <ListItemCountSelector
                                numberElement={numberElementByPage}
                                handleChangeNumberElement={handleChangeNumberElement}
                            />
                        </div>
                        <div>
                            <label htmlFor="options" className="text-bold dark:text-white">Filtre par saison: </label>
                            <select id="options" value={selectedOption} onChange={handleChangeOption}>
                                <option value="">Tout</option>
                                {seasons.map((season: string, index: number) => (
                                    <option key={index} value={season}>
                                        {season}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <div
                            className="overflow-x-hidden hover:overflow-auto border border-gray dark:border-darkgray xxxs:rounded-lg">
                            <table className="w-full divide-y divide-gray dark:divide-darkgray">
                                <thead className="bg-blue dark:bg-orange ">
                                <tr>
                                    <th
                                        aria-label={"sort-by-title-button"}
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider flex "
                                        onClick={() => handleSortClick("title")}
                                    >
                                        {t("formField.EtudiantStage.titre.text")}
                                        <div
                                            className={sortField === "title" ? "visible" : "hidden"}>
                                            <FontAwesomeIcon
                                                icon={sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                                color={"White"} className={"ml-2"}/>
                                        </div>
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {t("formField.EtudiantStage.stage.location.text")}
                                    </th>
                                    <th
                                        aria-label={"sort-by-salary-button"}
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                        onClick={() => handleSortClick("salaryByHour")}
                                    >
                                        {t("formField.EtudiantStage.stage.salary.text")}
                                    </th>
                                    <th
                                        aria-label={"sort-by-start-date-button"}
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                        onClick={() => handleSortClick("startDate")}
                                    >
                                        {t("formField.EtudiantStage.stage.startDate.text")}
                                    </th>
                                    <th
                                        aria-label={"sort-by-end-date-button"}
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                        onClick={() => handleSortClick("endDate")}
                                    >
                                        {t("formField.EtudiantStage.stage.endDate.text")}
                                    </th>
                                    {/*TODO add i18n below*/}
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        Entreprise
                                    </th>
                                    <th scope="col" className="relative px-6 py-3">
                                        <span className="sr-only">{t("formField.EtudiantStage.stage.apply.text")}</span>
                                    </th>
                                </tr>
                                </thead>
                                <tbody className="bg-white dark:bg-dark divide-y divide-gray dark:divide-darkgray">
                                {offers.length === 0 ?
                                    <tr>
                                        <td colSpan={7}
                                            className="text-center dark:text-white bg-red">{t("formField.EtudiantStage.noOffers.text")}</td>
                                    </tr>
                                    :
                                    <></>
                                }
                                {offers.map((offer: any) => (
                                    <tr key={offer.id} aria-label={"internship-row"}>
                                        <td className="px-6 py-4 whitespace-nowrap">
                                            <div className="flex items-center">
                                                <div className="ml-4">
                                                    <div
                                                        className="text-sm font-medium dark:text-offwhite">{offer.title}</div>
                                                </div>
                                            </div>
                                        </td>
                                        <td className="px-6 py-4 whitespace-nowrap">
                                            <div className="text-sm dark:text-offwhite">{offer.location}</div>
                                        </td>
                                        <td className="px-6 py-4 whitespace-nowrap">
                                            <div className="text-sm dark:text-offwhite">{offer.salaryByHour}</div>
                                        </td>
                                        <td className="px-6 py-4 whitespace-nowrap">
                                            <div className="text-sm dark:text-offwhite">{offer.startDate}</div>
                                        </td>
                                        <td className="px-6 py-4 whitespace-nowrap">
                                            <div className="text-sm dark:text-offwhite">{offer.endDate}</div>
                                        </td>
                                        <td className="px-6 py-4 break-all">
                                            <div
                                                className="text-sm dark:text-offwhite">{offer.employeurEntreprise}</div>
                                        </td>
                                        <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium flex space-x-3">
                                            <div className="flex space-x-1 items-center">
                                                <p className="text-black dark:text-white">{t("formField.EtudiantStage.stage.view.text")}</p>
                                                <FontAwesomeIcon icon={faEye}
                                                                 className="text-blue hover:text-indigo-900 dark:text-orange cursor-pointer"
                                                                 onClick={() => handleOfferClick(offer.id!)}
                                                />
                                            </div>
                                            <button
                                                aria-label={"apply-button"}
                                                onClick={() => applyOffer(offer, user, cv)}
                                                type="submit"
                                                disabled={
                                                    shouldButtonBeDisabled(offer.id)
                                                }
                                                className="w-full flex justify-center py-2 px-4 border border-gray dark:border-darkgray text-sm font-medium rounded-md text-white disabled:bg-gray bg-blue dark:disabled:bg-gray dark:bg-orange disabled:hover:bg-gray dark:disabled:hover:bg-gray hover:bg-cyan-300 dark:hover:bg-amber-400 focus:outline-none focus:shadow-outline-blue active:bg-blue transition duration-150 ease-in-out"
                                            >
                                                {t("formField.EtudiantStage.stage.apply.text")}
                                            </button>
                                        </td>

                                    </tr>
                                ))}
                                </tbody>
                            </table>
                        </div>
                        <div className="pt-4">
                            <ListItemPageSelector page={page} totalPages={totalPages} onPageChange={onPageChange}/>
                        </div>
                    </div>
                </div>
            </div>
            <Outlet
                context={useProps()}
            />
        </div>

    )
}

export default StudentInternship;


/**
 <div>
 <label htmlFor="options" className="text-bold">Filtre par saison: </label>
 <select id="options" value={selectedOption} onChange={handleChangeOption}>
 <option value="">Tout</option>
 {seasons.map((season: string, index: number) => (
 <option key={index} value={season}>
 {season}
 </option>
 ))}
 </select>
 </div>
 */