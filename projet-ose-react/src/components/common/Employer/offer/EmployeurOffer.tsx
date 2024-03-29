import {useTranslation} from "react-i18next";
import {useProps} from "../../../../pages/employer/EmployeurHomePage";
import {Outlet, useNavigate} from "react-router-dom";
import ListItemPageSelector from "../../shared/paginationList/ListItemPageSelector";
import React, {useEffect, useState} from "react";
import ListItemCountSelector from "../../shared/paginationList/ListItemCountSelector";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faArrowDown19,
    faArrowDown91,
    faArrowDownAZ,
    faArrowUpZA, faCircleUser,
    faEye,
} from "@fortawesome/free-solid-svg-icons";
import {
    allEmployeurInternshipOffersBySeason,
    getEmployeurOffers, getEmployeurSeason
} from "../../../../api/InterOfferJobAPI";

export default function EmployeurOffer() {
    const {i18n} = useTranslation();
    const {t} = useTranslation();
    const {offers, setOffers, handleOptionChange, selectedOption, seasons, user, page, totalPages, onPageChange, setSortField, setSortDirection, sortField, sortDirection, numberElementByPage, handleChangeNumberElement} = useProps();
    const navigate = useNavigate();

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
        navigate(`/employer/home/offers/${id}`);
    };
    const handleApplicationClick = (id: number) => {
        navigate(`/employer/home/offers/${id}/application`);
    }

    useEffect(() => {
        console.log("saison:" + seasons)
    }, [seasons]);

    return (
        <div className="flex flex-col justify-center max-md:pt-24 pb-14">
            <header className=" pb-4">
                <h1 className="xxxs:text-2xl sm:text-3xl font-bold text-gray-900 dark:text-offwhite">
                    {t("formField.homeEmployeur.title.text")}

                </h1>
            </header>
            <div className="">
                <div className="max-md:pt-2 min-w-full">
                    <div className="flex justify-between">
                        <div>
                            <label htmlFor="options" className="text-bold dark:text-white">
                                {
                                    t("formField.homeEmployeur.filter.title")
                                }
                            </label>
                            <select className="rounded border border-black dark:border-white dark:bg-dark dark:text-white" id="options" value={selectedOption} onChange={handleOptionChange}>
                                <option value="">{t("formField.homeEmployeur.filter.All")}</option>
                                {seasons.map((season: string, index: number) => (
                                    <option key={index} value={season}>
                                        {t("formField.homeEmployeur.filter."+ season.slice(0, -4)) + " " + season.slice(-4)}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <div className="pb-4">
                            <ListItemCountSelector
                                numberElement={numberElementByPage}
                                handleChangeNumberElement={handleChangeNumberElement}
                            />
                        </div>
                    </div>
                    <div
                        className="overflow-x-hidden hover:overflow-auto border border-gray dark:border-darkgray xxxs:rounded-lg">
                        <table className="w-full divide-y divide-gray dark:divide-darkgray">
                            <thead className="bg-blue dark:bg-orange ">
                            <tr>
                                <th
                                    scope="col"
                                    className="xxxs:px-2 sm:px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider flex "
                                    onClick={() => handleSortClick("title")}
                                >
                                    {t("formField.homeEmployeur.offerTable.titre.text")}
                                    <div
                                        className={sortField === "title" ? "visible" : "hidden"}>
                                        <FontAwesomeIcon
                                            icon={sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                            color={"White"} className={"ml-2"}/>
                                    </div>
                                </th>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium max-md:hidden text-white uppercase tracking-wider"
                                    onClick={() => handleSortClick("startDate")}
                                >
                                    <div className="flex">
                                        {t("formField.homeEmployeur.offerTable.startDate.text")}
                                        <div
                                            className={sortField === "startDate" ? "visible" : "hidden"}>
                                            <FontAwesomeIcon
                                                icon={sortDirection === "asc" ? faArrowDown19 : faArrowDown91}
                                                color={"White"} className={"ml-2"}/>
                                        </div>
                                    </div>
                                </th>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider max-md:hidden "
                                    onClick={() => handleSortClick("salaryByHour")}
                                >
                                    <div className="flex">
                                        {t("formField.homeEmployeur.offerTable.salary.text")}
                                        <div
                                            className={sortField === "salaryByHour" ? "visible" : "hidden"}>
                                            <FontAwesomeIcon
                                                icon={sortDirection === "asc" ? faArrowDown19 : faArrowDown91}
                                                color={"White"} className={"ml-2"}/>
                                        </div>
                                    </div>
                                </th>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-white max-sm:hidden uppercase tracking-wider"
                                    onClick={() => handleSortClick("state")}
                                >
                                    <div className="flex">
                                        {t("formField.homeEmployeur.offerTable.status.text")}
                                        <div
                                            className={sortField === "state" ? "visible" : "hidden"}>
                                            <FontAwesomeIcon
                                                icon={sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                                color={"White"} className={"ml-2"}/>
                                        </div>
                                    </div>
                                </th>
                                <th scope="col"
                                    className="xxxs:px-2 sm:px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider">
                                    {t("formField.homeEmployeur.candidature.text")}
                                </th>
                                <th scope="col"
                                    className="xxxs:px-2 sm:px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider">
                                    <span className="">Option</span>
                                </th>
                            </tr>
                            </thead>
                            <tbody className="bg-white dark:bg-dark divide-y divide-gray dark:divide-darkgray">
                            {offers.map((offer: any) => (
                                <tr key={offer.id}>
                                    <td className="xxxs:px-2 sm:px-6 py-4 whitespace-nowrap min-w-full max-md:max-w-[10rem] max-w-[15rem]  ">
                                        <div className="flex items-center">
                                            <div className="ml-4 overflow-hidden">
                                                <p className="text-ellipsis overflow-hidden text-sm font-medium dark:text-offwhite">{offer.title}</p>
                                            </div>
                                        </div>
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap max-md:hidden">
                                        <div className="text-sm dark:text-offwhite">{offer.startDate!.toString()}</div>
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap dark:text-white max-md:hidden">
                                        {offer.salaryByHour} $
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap text-sm max-sm:hidden dark:text-offwhite">

                                        <span
                                            className={
                                                offer.state == "PENDING" ?
                                                    "px-2 inline-flex text-xs leading-5 justify-center font-semibold rounded-full w-3/4 bg-orange text-white dark:text-offwhite"
                                                    : offer.state === "DECLINED" ?
                                                        "px-2 inline-flex text-xs leading-5 font-semibold justify-center rounded-full w-3/4 bg-red text-white dark:text-offwhite"
                                                        : "px-2 inline-flex text-xs leading-5 font-semibold rounded-full w-3/4 justify-center bg-green text-white dark:text-offwhite"}
                                        >
                                            {t(`formField.homeEmployeur.offerTable.${offer.state}.text`)}
                                        </span>
                                    </td>
                                    <td className="xxxs:px-2 sm:px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                                        <div className="flex items-center xxs:justify-center xs:justify-start ">
                                            {
                                                offer.internshipCandidates!.length > 0 ? (
                                                    Array.from(Array(offer.internshipCandidates!.length), (e, i) => {
                                                        if (i <= 2) {
                                                            return (
                                                                <FontAwesomeIcon key={i} icon={faCircleUser}
                                                                                 className="text-blue dark:text-orange inline-block h-6 w-6 rounded-full ring-2 ring-white dark:ring-dark cursor-pointer"
                                                                                 onClick={() => handleApplicationClick(offer.id!)}
                                                                                 size="xl"/>
                                                            )
                                                        } else if (i > 2) {
                                                            return (
                                                                <div key={i}
                                                                     className="flex items-center bg-blue dark:bg-orange justify-center  text-white h-6 w-6 rounded-full ring-2 ring-white dark:ring-dark  cursor-pointer"
                                                                     onClick={() => handleApplicationClick(offer.id!)}
                                                                >
                                                                    +{offer.internshipCandidates!.length - 1}
                                                                </div>
                                                            )
                                                        }
                                                    })) : (
                                                    <div
                                                        className="flex items-center bg-blue dark:bg-orange justify-center text-white h-6 w-6 rounded-full ring-2 ring-white dark:ring-dark text-xs">
                                                        0
                                                    </div>
                                                )
                                            }
                                        </div>
                                    </td>
                                    <td className="flex space-x-2 xxxs:px-2 sm:px-6 py-4 whitespace-nowrap text-right text-sm font-medium items-center dark:text-white">
                                        <p>
                                            {t("formField.homeEmployeur.view.text")}
                                        </p>
                                        <FontAwesomeIcon
                                            aria-label={"nav-button"}
                                            icon={faEye}
                                            className="text-blue hover:text-indigo-900 dark:text-orange cursor-pointer"
                                            onClick={() => handleOfferClick(offer.id!)}
                                        />
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                    <div>
                        <ListItemPageSelector page={page} totalPages={totalPages} onPageChange={onPageChange}/>
                    </div>
                </div>
            </div>

        </div>
    );
}