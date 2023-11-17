import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faArrowDown19, faArrowDown91, faArrowDownAZ, faArrowUpZA, faEye} from "@fortawesome/free-solid-svg-icons";
import React, {useEffect} from "react";
import {useTranslation} from "react-i18next";
import {useNavigate} from "react-router-dom";
import {saveEmployerOpinion} from "../../../../api/StageAPI";

export default function InternshipManagerInternshipsAgreement(props: any) {

    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.InternshipsAgreementPage.internshipsAgreement");

    const navigate = useNavigate();

    useEffect(() => {
        console.log(props.offers)
    }, [props.offers]);

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
        <div className="overflow-x-hidden hover:overflow-auto border border-gray dark:border-darkgray xxxs:rounded-lg">
            <table className="w-full divide-y divide-gray dark:divide-darkgray">
                <thead className="bg-blue dark:bg-orange ">
                    <tr>
                        <th
                            scope="col"
                            className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider flex "
                            onClick={() => handleSortClick("offer.title")}
                        >
                            {fields.title}
                            <div
                                className={props.sortField === "title" ? "visible" : "hidden"}>
                                <FontAwesomeIcon
                                    icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                    color={"White"} className={"ml-2"}/>
                            </div>
                        </th>
                        <th
                            scope="col"
                            className="px-6 py-3 text-left text-xs font-medium max-md:hidden text-gray uppercase tracking-wider"
                            onClick={() => handleSortClick("employeur.entreprise")}
                        >
                            {fields.enterprise}
                            <div
                                className={props.sortField === "employeur.entreprise" ? "visible" : "hidden"}>
                                <FontAwesomeIcon
                                    icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                    color={"White"} className={"ml-2"}/>
                            </div>
                        </th>
                        <th
                            scope="col"
                            className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider max-md:hidden "
                            onClick={() => handleSortClick("student.nom")}
                        >
                            {fields.student}
                            <div
                                className={props.sortField === "student.nom" ? "visible" : "hidden"}>
                                <FontAwesomeIcon
                                    icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                    color={"White"} className={"ml-2"}/>
                            </div>
                        </th>
                        <th
                            scope="col"
                            className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                            onClick={() => handleSortClick("state")}
                        >
                            {fields.statut}
                            <div
                                className={props.sortField === "stateEmployeur" ? "visible" : "hidden"}>
                                <FontAwesomeIcon
                                    icon={props.sortDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                    color={"White"} className={"ml-2"}/>
                            </div>
                        </th>
                        <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider">
                            <span >Option</span>
                        </th>
                    </tr>
                </thead>
                <tbody className="bg-white dark:bg-dark divide-y divide-gray dark:divide-darkgray">
                    {props.offers.length === 0 && (
                        <tr>
                            <td colSpan={5} className="text-center bg-red text-white">
                                {fields.empty}
                            </td>
                        </tr>
                    )}
                    {props.offers.map((offer: any) => (
                        <tr key={offer.id}>
                            <td className="px-6 py-4 whitespace-nowrap min-w-full max-md:max-w-[10rem] max-w-[15rem]  ">
                                <div className="flex items-center">
                                    <div className="ml-4 overflow-hidden">
                                        <p className="text-ellipsis overflow-hidden text-sm font-medium dark:text-offwhite">{offer.internOfferDto.title}</p>
                                    </div>
                                </div>
                            </td>
                            <td className="px-6 py-4 whitespace-nowrap max-md:hidden">
                                <div className="text-sm dark:text-offwhite">{offer.employeur.entreprise}</div>
                            </td>
                            <td className="px-6 py-4 whitespace-nowrap dark:text-white max-md:hidden">
                                {offer.etudiantDto.nom + " " + offer.etudiantDto.prenom}
                            </td>

                            <td className="px-6 py-4 whitespace-nowrap text-sm dark:text-offwhite">
                                <span
                                    className={
                                        (offer.stateEmployeur == "DECLINED" || offer.stateStudent == "DECLINED" ?
                                            "px-2 xxxs:text-xs sm:text-sm inline-flex leading-5 font-semibold justify-center rounded-full w-3/4 bg-red text-white dark:text-offwhite " :
                                            offer.stateEmployeur == "PENDING" || offer.stateStudent == "PENDING" ?
                                                "px-2  xxxs:text-xs sm:text-sm inline-flex leading-5 justify-center font-semibold rounded-full w-3/4 bg-orange text-white dark:text-offwhite"
                                                : "px-2 xxxs:text-xs sm:text-sm inline-flex leading-5 font-semibold rounded-full w-3/4 justify-center bg-green text-white dark:text-offwhite ")}
                                >
                                    {offer.stateEmployeur == "DECLINED" || offer.stateStudent == "DECLINED" ?
                                        fields.status.declined
                                        : offer.stateEmployeur == "PENDING" || offer.stateStudent == "PENDING" ?
                                            fields.status.pending
                                            : fields.status.accepted
                                    }
                                </span>
                            </td>
                            <td className=" px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                                {
                                    offer.stateEmployeur == "ACCEPTED" && offer.stateStudent == "ACCEPTED" ?
                                        <div role="cell"
                                            className="md:w-10 w-6 px-2 py-2 text-center whitespace-nowrap  font-medium hover:cursor-pointer">
                                            <FontAwesomeIcon icon={faEye}
                                                             className="text-indigo-600 hover:text-indigo-900 dark:text-orange"
                                                             onClick={() => props.handleOfferClick(offer.contractId!)}/>
                                        </div> : (offer.stateEmployeur == "DECLINED") ?
                                            <div>
                                                <p>{fields.sign.employer}</p>
                                            </div> : ( offer.stateStudent == "DECLINED") ?
                                                <div>
                                                    <p>{fields.sign.student}</p>
                                                </div> :
                                                <></>

                                }
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
