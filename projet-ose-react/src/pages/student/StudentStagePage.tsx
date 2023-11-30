import React, {useEffect, useRef, useState} from "react";
import i18n from "i18next";
import {useAuth} from "../../authentication/AuthContext";
import {acceptStage, declineStage, fetchStagePending} from "../../api/StudentApi";
import {Stage} from "../../model/Stage";
import {useTranslation} from "react-i18next";
import {getActualSeason} from "../../utils/Utils";
import ListItemCountSelector from "../../components/common/shared/paginationList/ListItemCountSelector";
import ListItemPageSelector from "../../components/common/shared/paginationList/ListItemPageSelector";
import {getAllSeasons} from "../../api/InterOfferJobAPI";


function StudentStagePage() {
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "StudentInterview");
    const {t} = useTranslation()
    const [stages, setStages] = React.useState<Stage[]>([])
    const [user, setUser] = useState<any>(null);
    const isLoading = useRef(false);
    const auth = useAuth();


    const [numberElementByPage, setNumberElementByPage] = useState<number>(100)
    const [sortField, setSortField] = useState("id");
    const [sortDirection, setSortDirection] = useState("asc");
    const [totalPages, setTotalPages] = useState(0);
    const [currentPage, setCurrentPage] = useState(0);
    const [seasons, setSeasons] = useState([])
    const [selectedOption, setSelectedOption] = useState(getActualSeason());

    const handleOnAccept = (stage: any) => {
        acceptStage(stage).then((res) => {
            console.log(res);
            if (res === true) {
                setStages(stages.map((element) => {
                    if (element.id == stage.id) {
                        element.stateStudent = "ACCEPTED";
                    }
                    return element;
                }));
            }
        });
    }

    const handleOnDecline = (stage: any) => {
        declineStage(stage).then((res) => {
            console.log(res);
            if (res === true) {
                setStages(stages.map((element) => {
                    if (element.id === stage.id) {
                        element.stateStudent = "DECLINED";
                    }
                    return element;
                }));
            }
        });
    }

    useEffect(() => {
        const fetchStage = async () => {
            isLoading.current = true;

            await fetchStagePending(auth.userId, currentPage, numberElementByPage, selectedOption).then((res) => {
                setStages(res);
            });
            isLoading.current = false;
        };

        if (!isLoading.current) {
            fetchStage();
        }
    }, [selectedOption, numberElementByPage, currentPage]);

    useEffect(() => {
        const fetchStage = async () => {
            let season = await  getAllSeasons();
            console.log(season);
            setSeasons(season);
        };
        fetchStage()
    }, []);


    const handleChangeNumberElement = (event: React.ChangeEvent<HTMLSelectElement>) => {
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
        <>
            <div className=" flex flex-col dark:bg-softdark">
                <div className="max-md:mt-16">
                    <div className="overflow-x-hidden xxxs:rounded-lg">


                        <h1 className="mt-7 text-start xxxs:text-2xl sm:text-3xl font-bold leading-9 tracking-tight text-black dark:text-white">
                            {t("formField.EtudiantStage.titre.text")}
                        </h1>

                        <div className="flex justify-between py-4">
                            <div>
                                <label htmlFor="options" className="text-bold dark:text-white">
                                    {t("formField.EtudiantStage.filter.title")}
                                </label>
                                <select
                                    className="rounded border border-black dark:border-white dark:bg-dark dark:text-white"
                                    id="options" value={selectedOption} onChange={handleOptionChange}>
                                    <option value="">{t("formField.EtudiantStage.filter.All")}</option>
                                    {seasons.map((season: string, index: number) => (
                                        <option key={index} value={season}>
                                            {t("formField.EtudiantStage.filter."+season.slice(0,-4)) + " " + season.slice(-4)}
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
                        <div
                            className="overflow-x-hidden hover:overflow-auto border border-gray dark:border-darkgray xxxs:rounded-lg">
                            <table className=" w-full divide-y divide-gray dark:divide-darkgray">
                                <thead className="bg-blue dark:bg-orange ">
                                <tr>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                                    >
                                        {fields.table.title}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                                    >
                                        {fields.table.location}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                                    >

                                        {fields.table.company}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                                    >
                                        {fields.table.action.text}
                                    </th>
                                </tr>
                                </thead>
                                <tbody
                                    className="bg-white text-black divide-y divide-gray dark:bg-dark dark:divide-darkgray">
                                {stages && stages.length > 0 ? (
                                    stages.map((stage) => (
                                        <tr key={stage.id}>
                                            <td className="px-6 py-4 whitespace-nowrap text-sm font-medium dark:text-white">
                                                {stage.offer.title}
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap text-sm font-medium  dark:text-white">
                                                {stage.offer.location}
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap text-sm font-medium  dark:text-white">
                                                {stage.offer.employeurEntreprise}
                                            </td>
                                            <td className="px-6 py-4 whitespace-nowrap">
                                                <div className="flex justify-start space-x-2">
                                                    <button
                                                        disabled={stage.stateStudent !== "PENDING"}
                                                        onClick={() => handleOnAccept(stage)}
                                                        className="disabled:bg-gray text-white bg-green hover:bg-green-700 px-3 py-2 rounded-md text-sm font-medium"
                                                    >
                                                        {fields.table.action.button.accept}
                                                    </button>
                                                    <button
                                                        disabled={stage.stateStudent !== "PENDING"}
                                                        onClick={() => handleOnDecline(stage)}
                                                        className="disabled:bg-gray text-white bg-red hover:bg-red-700 px-3 py-2 rounded-md text-sm font-medium"
                                                    >
                                                        {fields.table.action.button.decline}
                                                    </button>
                                                    {stage.stateStudent === "ACCEPTED" &&
                                                        <p className="text-white bg-blue hover:bg-red-700 px-3 py-2 rounded-md text-sm font-bold">{fields.table.action.status.accepted}</p>}
                                                    {stage.stateStudent === "DECLINED" &&
                                                        <p className="text-white bg-red hover:bg-red-700 px-3 py-2 rounded-md text-sm font-bold">{fields.table.action.status.declined}</p>}
                                                </div>
                                            </td>
                                        </tr>
                                    ))
                                ) : (
                                    <tr>
                                        <td colSpan={5}>
                                            <div className="w-full text-center bg-red text-white">
                                                {t("StudentInterview.table.noStage")}
                                            </div>
                                        </td>
                                    </tr>
                                )}
                                </tbody>
                            </table>
                        </div>
                        <div className="">
                            <ListItemPageSelector page={currentPage} totalPages={totalPages}
                                                  onPageChange={handlePageChange}/>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default StudentStagePage;