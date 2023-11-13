import React, {ReactElement, useEffect, useRef, useState} from "react";
import {NavLink, Outlet, useLocation, useNavigate, useOutletContext} from "react-router-dom";
import {useTranslation} from "react-i18next";
import {useProps} from "../../../pages/employer/EmployeurHomePage";
import ListItemCountSelector from "../shared/paginationList/ListItemCountSelector";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faArrowDown19,
    faArrowDown91,
    faArrowDownAZ,
    faArrowUpZA,
    faEye, faPenNib
} from "@fortawesome/free-solid-svg-icons";
import ListItemPageSelector from "../shared/paginationList/ListItemPageSelector";
import {employeurGetContractById} from "../../../api/ContractAPI";
import ViewPDFModal from "./offer/ViewPDFModal";


export default function EmployerContractPage() {

    const navigate = useNavigate();
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.contractPage." + i18n.language.slice(0, 2));
    const {stageAgreement, pageAgreement, totalPageAgreement, onPageChangeAgreement, numberElementAgreementByPage, handleChangeNumberElementAgreement, sortAgreementDirection, sortAgreementField, setAgreementSortField, setAgreementSortDirection,} = useProps();
    const [file, setFile] = useState<any>({
        content: "",
    });
    const [files, setFiles] = useState<any>([]);
    const [isModalOpen, setIsModalOpen] = useState(false);

    useEffect(() => {
        stageAgreement.map(async (stage: any) => {
            if (stage.contractId === null) return;
            await employeurGetContractById(stage.contractId).then(r => {
                console.log(r)
                setFiles([...files, r])
            })
        })
    }, []);

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
                                <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider">
                                    <span >Option</span>
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
                                                (stage.stateEmployeur == "PENDING" || stage.stateStudent == "PENDING") && (stage.stateEmployeur != "DECLINED" || stage.stateStudent != "DECLINED") ?
                                                    "px-2 inline-flex text-xs leading-5 justify-center font-semibold rounded-full w-3/4 bg-orange text-white dark:text-offwhite"
                                                    : stage.stateEmployeur == "DECLINED" || stage.stateStudent == "DECLINED" ?
                                                        "px-2 inline-flex text-xs leading-5 font-semibold justify-center rounded-full w-3/4 bg-red text-white dark:text-offwhite"
                                                        : "px-2 inline-flex text-xs leading-5 font-semibold rounded-full w-3/4 justify-center bg-green text-white dark:text-offwhite"}
                                        >
                                            {fields.AgreementTable[
                                                (stage.stateEmployeur == "PENDING" || stage.stateStudent == "PENDING") && (stage.stateEmployeur != "DECLINED" || stage.stateStudent != "DECLINED") ?
                                                    "PENDING"
                                                    : stage.stateEmployeur == "DECLINED" || stage.stateStudent == "DECLINED" ?
                                                        "DECLINED"
                                                        : "ACCEPTED"
                                                ].text}
                                        </span>
                                    </td>
                                    <td className="flex space-x-5 items-center justify-between px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                                        {
                                            stage.contractId === null ?
                                            (
                                            !files.isEmpty &&
                                                <div>
                                                    <FontAwesomeIcon icon={faEye}
                                                                     className="text-blue  hover:text-indigo-900 dark:text-orange cursor-pointer"
                                                                     onClick={() => {
                                                                         files.map((file: any) => {
                                                                                 console.log(file)
                                                                                 console.log(stage)
                                                                                 if (file.id === stage.contractId) {
                                                                                     setFile({
                                                                                         content: file.contractContent
                                                                                     });
                                                                                     console.log(file)
                                                                                 }
                                                                             }
                                                                         )
                                                                         setIsModalOpen(true);
                                                                     }}
                                                    />
                                                    <NavLink
                                                        to="#"
                                                        className="flex items-center text-green space-x-1"
                                                        onClick={() => {

                                                        }}
                                                    >
                                                        <p>Sign</p>
                                                        <FontAwesomeIcon icon={faPenNib}/>
                                                    </NavLink>
                                                </div>

                                            ) : (
                                                <div className="flex justify-between">
                                                    <button
                                                        className="flex items-center text-green space-x-1 bg-green"
                                                        onClick={() => {
                                                            handleOfferClick(stage.id);
                                                        }}
                                                    >
                                                        <p>Sign</p>
                                                        <FontAwesomeIcon icon={faPenNib}/>
                                                    </button>
                                                    <button
                                                        className="flex items-center text-green space-x-1 bg-red"
                                                        onClick={() => {
                                                            handleOfferClick(stage.id);
                                                        }}
                                                    >
                                                        <p>Sign</p>
                                                        <FontAwesomeIcon icon={faPenNib}/>
                                                    </button>

                                                </div>
                                            )
                                    }
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
            {
                file.content !== "" && isModalOpen &&
                <ViewPDFModal ismodal={true} file={file} setIsModalOpen={setIsModalOpen} />
            }

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


