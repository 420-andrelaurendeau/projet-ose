import React, {ReactElement, useEffect, useRef, useState} from "react";
import {Outlet, useLocation, useNavigate, useOutletContext} from "react-router-dom";
import {useTranslation} from "react-i18next";
import {useProps} from "../../../pages/employer/EmployeurHomePage";
import ListItemCountSelector from "../shared/paginationList/ListItemCountSelector";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faArrowDown19,
    faArrowDown91,
    faArrowDownAZ,
    faArrowUpZA,
    faEye
} from "@fortawesome/free-solid-svg-icons";
import ListItemPageSelector from "../shared/paginationList/ListItemPageSelector";
import {employeurGetContractById} from "../../../api/ContractAPI";


export default function EmployerContractPage() {

    const navigate = useNavigate();
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.contractPage." + i18n.language.slice(0, 2));
    const {stageAgreement, pageAgreement, totalPageAgreement, onPageChangeAgreement, numberElementAgreementByPage, handleChangeNumberElementAgreement, sortAgreementDirection, sortAgreementField, setAgreementSortField, setAgreementSortDirection,} = useProps();
    const [file, setFile] = useState<any>({
        content: "uy",
    });

    const getFile = async (id: number) => {
        const response = await employeurGetContractById(id);
        console.log(response)
        if (response) {
            setFile({
                content: response.contract
            });
            console.log(file)
        } else {
            console.log("error");
        }
    }

    const handleSortClick = (newSortField: any) => {
        if (newSortField === sortAgreementField && sortAgreementDirection === "desc") {
            setAgreementSortField("");
            setAgreementSortDirection("");
        } else if (newSortField === sortAgreementField) {
            setAgreementSortDirection((prevDirection: String) => (prevDirection === "asc" ? "desc" : "asc"));
        } else {
            setAgreementSortField(newSortField);
            setAgreementSortDirection("asc");
        }
    };

    const handleOfferClick = (id: number) => {
        console.log(id)
        navigate(`/employer/home/internshipagreement/${id}`);
    };

    const context = {
        file: file,
    }

    return (
        <div className="flex flex-col justify-center max-md:pt-24 pb-14">
            <div className="xs:-mx-1 lg:-mx-2">
                <div className="max-md:pt-2 min-w-full xs:px-6 lg:px-8">
                    <div className="pb-4">
                        <ListItemCountSelector
                            numberElement={numberElementAgreementByPage}
                            handleChangeNumberElement={handleChangeNumberElementAgreement}
                        />
                    </div>
                    <div className="overflow-x-hidden hover:overflow-auto border border-gray dark:border-darkgray xxxs:rounded-lg">
                        <table className="w-full divide-y divide-gray dark:divide-darkgray">
                            <thead className="bg-blue dark:bg-orange ">
                            <tr>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider flex "
                                    onClick={() => handleSortClick("title")}
                                >
                                    {fields.AgreementTable.title.text}
                                    <div
                                        className={sortAgreementField === "title" ? "visible" : "hidden"}>
                                        <FontAwesomeIcon
                                            icon={sortAgreementDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                            color={"White"} className={"ml-2"}/>
                                    </div>
                                </th>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium max-md:hidden text-gray uppercase tracking-wider"
                                    onClick={() => handleSortClick("startDate")}
                                >
                                    <div className="flex">
                                        {fields.AgreementTable.enterprise.text}
                                        <div
                                            className={sortAgreementField === "startDate" ? "visible" : "hidden"}>
                                            <FontAwesomeIcon
                                                icon={sortAgreementDirection === "asc" ? faArrowDown19 : faArrowDown91}
                                                color={"White"} className={"ml-2"}/>
                                        </div>
                                    </div>
                                </th>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider max-md:hidden "
                                    onClick={() => handleSortClick("salaryByHour")}
                                >
                                    <div className="flex">
                                        {fields.AgreementTable.student.text}
                                        <div
                                            className={sortAgreementField === "salaryByHour" ? "visible" : "hidden"}>
                                            <FontAwesomeIcon
                                                icon={sortAgreementDirection === "asc" ? faArrowDown19 : faArrowDown91}
                                                color={"White"} className={"ml-2"}/>
                                        </div>
                                    </div>
                                </th>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    onClick={() => handleSortClick("state")}
                                >
                                    <div className="flex">
                                        {fields.AgreementTable.status.text}
                                        <div
                                            className={sortAgreementField === "state" ? "visible" : "hidden"}>
                                            <FontAwesomeIcon
                                                icon={sortAgreementDirection === "asc" ? faArrowDownAZ : faArrowUpZA}
                                                color={"White"} className={"ml-2"}/>
                                        </div>
                                    </div>
                                </th>
                                <th scope="col" className="relative px-6 py-3">
                                    <span className="sr-only">Option</span>
                                </th>
                            </tr>
                            </thead>
                            <tbody className="bg-white dark:bg-dark divide-y divide-gray dark:divide-darkgray">
                            { stageAgreement.map((stage:any) => (
                                <tr key={stage.id}>
                                    <td className="px-6 py-4 whitespace-nowrap min-w-full max-md:max-w-[10rem] max-w-[15rem]  ">
                                        <div className="flex items-center">
                                            <div className="ml-4 overflow-hidden">
                                                <p className="text-ellipsis overflow-hidden text-sm font-medium dark:text-offwhite">{stage.internOfferDto.title}</p>
                                            </div>
                                        </div>
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap max-md:hidden">
                                        <div className="text-sm dark:text-offwhite">{stage.employeur.entreprise}</div>
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap dark:text-white max-md:hidden">
                                        {stage.etudiantDto.nom + " " + stage.etudiantDto.prenom}
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap text-sm dark:text-offwhite">

                                        <span
                                            className={
                                                stage.internOfferDto.state == "PENDING" ?
                                                    "px-2 inline-flex text-xs leading-5 justify-center font-semibold rounded-full w-3/4 bg-orange text-white dark:text-offwhite"
                                                    : stage.internOfferDto.state === "DECLINED" ?
                                                        "px-2 inline-flex text-xs leading-5 font-semibold justify-center rounded-full w-3/4 bg-red text-white dark:text-offwhite"
                                                        : "px-2 inline-flex text-xs leading-5 font-semibold rounded-full w-3/4 justify-center bg-green text-white dark:text-offwhite"}
                                        >
                                            {fields.AgreementTable[stage.internOfferDto.state].text}
                                        </span>
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                                        <FontAwesomeIcon icon={faEye}
                                                         className="text-blue hover:text-indigo-900 dark:text-orange cursor-pointer"
                                                         onClick={() => {
                                                             getFile(stage.contractId).then(r => console.log(file))
                                                             console.log(file)
                                                             navigate(`/employer/home/contract/${stage.contractId}`);
                                                         }}
                                        />
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                    <div className="pt-4">
                        <ListItemPageSelector page={pageAgreement} totalPages={totalPageAgreement} onPageChange={onPageChangeAgreement}/>
                    </div>
                </div>
            </div>
            <Outlet
                context={context}
            />
        </div>
    );
};

interface Props {
    file: any;
    size: string;
}

export function usePropsContract(){
    return useOutletContext<Props>();
}


