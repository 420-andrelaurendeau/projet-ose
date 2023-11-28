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

interface Props {
    user: any;
    appliedOffers: AppliedOffers[];
}

function StudentInternship() {
    const {t} = useTranslation();
    const {offers,setOffers, setAppliedOffers, appliedOffers, page, setSortField, onPageChange, setCurrentPage, numberElementByPage, setSortDirection, sortDirection, sortField, totalPages, handleChangeNumberElement, seasons, selectedOption, handleChangeOption} = useProps();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.EtudiantStage");
    //const [offers, setOffers] = useState<any[]>([])
    const [cv, setCv] = useState<FileEntity>()
    //const [seasons, setSeasons] = useState([])
    //const [selectedOption, setSelectedOption] = useState('');
    const [user, setUser] = useState<any>(null)
    const auth = useAuth();
    //const token = localStorage.getItem('token');
    const isloading = useRef(false);
    const [loadingCV, setLoadingCV] = useState<boolean>(false);
    const toast = useToast();
    const navigate = useNavigate();

    useEffect(() => {
        const fecth = async () => {
            setLoadingCV(true);
            isloading.current = true;
            try {
                const userRes = await getUser(auth.userEmail!);
                setUser(userRes);

                const offersRes = await getStudentAppliedOffers(auth.userId!);
                // Met à jour l'état et attend que React l'applique
                setAppliedOffers(offersRes);

                const cvRes = await fetchDefaultCvByStudentId(userRes.id);
                setCv(cvRes);
                setLoadingCV(false);
            } catch (error) {
                console.log("Error fetching user data:", error);
            } finally {
            }
        }

        if (!isloading.current) {
            fecth().then(r => console.log(appliedOffers));
            isloading.current = false;
        }
    }, []);



    const applyOffer = (offer: any, student: any, cv: any) => {
        if (cv == null || cv == "" ) {
            toast.error(t("formField.EtudiantStage.toast.ErrorNoCv"))
        } else {
            saveStudentInternshipOffer(offer, student, cv).then(
                async res => {
                    console.log(appliedOffers)
                    let appliedOffer: AppliedOffers = {
                        appliedOffer: res.internOfferJob,
                        appliedFiles: res.files
                    };

                    await getStudentAppliedOffers(auth.userId!).then((res) => {
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
        console.log(sortField === "employeurEntreprise" ? "visible" : "hidden")
    };

    const handleOfferClick = (id: number) => {
        navigate(`/student/home/offers/${id}`, {state: context});
    };


    const context:Props = {
        user: user,
        appliedOffers: appliedOffers,
    }

    return (
        <div className="flex flex-col ">
            <div >
                <div className="max-md:mt-16">
                    <div className="overflow-x-hidden xxxs:rounded-lg">
                        <h1 className="mt-7 text-start text-3xl font-bold leading-9 tracking-tight text-black dark:text-white">
                            {fields.titre.text}
                        </h1>
                        <div className="flex justify-between py-4">
                            <div>
                                <label htmlFor="options" className="text-bold dark:text-white">Filtre par saison: </label>
                                <select className="rounded border border-black dark:border-white dark:bg-dark dark:text-white" id="options" value={selectedOption} onChange={handleChangeOption}>
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
                                    handleChangeNumberElement={handleChangeNumberElement}
                                />
                            </div>
                        </div>
                            <div className="overflow-x-hidden hover:overflow-auto border border-gray dark:border-darkgray xxxs:rounded-lg ">
                                <table className="w-full divide-y divide-gray dark:divide-darkgray">
                                    <thead className="bg-blue dark:bg-orange ">
                                    <tr>
                                        <th
                                            scope="col"
                                            className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider flex "
                                            onClick={() => handleSortClick("title")}
                                        >
                                            {fields.titre.text}
                                            <div
                                                className={sortField === "title" ? "visible" : "hidden"}>
                                                <FontAwesomeIcon
                                                    icon={sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                                    color={"White"} className={"ml-2"}/>
                                            </div>
                                        </th>
                                        <th
                                            scope="col"
                                            className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider max-lg:hidden"
                                        >
                                            {fields.stage.location.text}
                                        </th>
                                        <th
                                            scope="col"
                                            className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider max-lg:hidden"
                                            onClick={() => handleSortClick("salaryByHour")}
                                        >
                                            {fields.stage.salary.text}
                                        </th>
                                        <th
                                            scope="col"
                                            className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                                            onClick={() => handleSortClick("startDate")}
                                        >
                                            {fields.stage.startDate.text}
                                        </th>
                                        <th
                                            scope="col"
                                            className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider max-md:hidden"
                                            onClick={() => handleSortClick("endDate")}
                                        >
                                            {fields.stage.endDate.text}
                                        </th>
                                        <th
                                            scope="col"
                                            className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                                        >
                                            Entreprise
                                        </th>
                                        <th scope="col" className="relative px-6 py-3">
                                            <span className="sr-only">{fields.stage.apply.text}</span>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody className="bg-white dark:bg-dark divide-y divide-gray dark:divide-darkgray">
                                    {offers.length === 0 ?
                                        <tr>
                                            <td colSpan={7} className="text-center text-white bg-red">{t("formField.EtudiantStage.noOffers.text")}</td>
                                        </tr>
                                        :
                                        <></>
                                    }
                                    {offers.map((offer: any) => (
                                        <tr key={offer.id}>
                                            <td className="px-6 py-4 whitespace-nowrap">
                                                <div className="flex items-center">
                                                    <div className="ml-4">
                                                        <div className="text-sm font-medium dark:text-offwhite">{offer.title}</div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap max-lg:hidden">
                                                <div className="text-sm dark:text-offwhite">{offer.location}</div>
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap max-lg:hidden">
                                                <div className="text-sm dark:text-offwhite">{offer.salaryByHour}</div>
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap">
                                                <div className="text-sm dark:text-offwhite">{offer.startDate}</div>
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap max-md:hidden">
                                                <div className="text-sm dark:text-offwhite">{offer.endDate}</div>
                                            </td>
                                            <td className="px-6 py-4 break-all">
                                                <div className="text-sm dark:text-offwhite">{offer.employeurEntreprise}</div>
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium flex space-x-3">
                                                <div className="flex space-x-1 items-center">
                                                    <p className="text-black dark:text-white">{fields.stage.view.text}</p>
                                                    <FontAwesomeIcon icon={faEye}
                                                                     className="text-blue hover:text-indigo-900 dark:text-orange cursor-pointer"
                                                                     onClick={() => handleOfferClick(offer.id!)}
                                                    />
                                                </div>
                                                <button
                                                    onClick={() => applyOffer(offer, user, cv)}
                                                    type="submit"
                                                    disabled={
                                                        appliedOffers.find((appliedOffer: AppliedOffers) => appliedOffer.appliedOffer.id === offer.id) != null || loadingCV
                                                    }
                                                    className="w-full flex justify-center py-2 px-4 border border-gray dark:border-darkgray text-sm font-medium rounded-md text-white disabled:bg-gray bg-blue dark:disabled:bg-gray dark:bg-orange disabled:hover:bg-gray dark:disabled:hover:bg-gray hover:bg-cyan-300 dark:hover:bg-amber-400 focus:outline-none focus:shadow-outline-blue active:bg-blue transition duration-150 ease-in-out"
                                                >
                                                    {fields.stage.apply.text}
                                                </button>
                                            </td>

                                        </tr>
                                    ))}
                                    </tbody>
                                </table>
                            </div>
                        <div className="">
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