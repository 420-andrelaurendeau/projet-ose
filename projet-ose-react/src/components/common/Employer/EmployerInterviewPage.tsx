import i18n from "i18next";
import React, {useEffect, useRef, useState} from "react";
import {Interview} from "../../../model/Interview";
import {useAuth} from "../../../authentication/AuthContext";
import {getUser} from "../../../api/UtilisateurAPI";
import {acceptInterview, declineInterview, saveStageStudent} from "../../../api/StudentApi";
import PaginatedList from "../shared/paginationList/PaginatedList";
import {fetchInterviewsEmployer} from "../../../api/InterviewApi";
import {getAllSeasons} from "../../../api/InterOfferJobAPI";
import {useTranslation} from "react-i18next";
import {useNavigate} from "react-router-dom";
import {useProps} from "../../../pages/student/StudentInternshipPage";


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
export const EmployerInterviewPage = () => {
    const {t} = useTranslation();
    const [user, setUser] = useState<any>(null);
    const [interviews, setInterviews] = React.useState<Interview[]>([]);
    const isLoading = useRef(false);
    const auth = useAuth();
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const navigate = useNavigate();
    const [numberElementByPage, setNumberElementByPage] = useState<number>(100);
    //const [seasons,setSeasons] = useState([])
    //const [selectedOption, setSelectedOption] = useState(getActualSeason());

    const {
        seasons,
        selectedOption,
        setSelectedOption,
        setSeasons
    } = useProps();

    useEffect(() => {
        const fetchUser = async () => {
            isLoading.current = true;

            getUser(auth.userEmail!)
                .then(async (resUser) => {
                    setUser(resUser);
                    console.log(resUser);
                    let res = await getAllSeasons()
                    setSeasons(res)

                    fetchInterviewsEmployer(resUser.id, {
                        page: currentPage,
                        size: numberElementByPage,
                        sortField: "id",
                        sortDirection: "desc",
                        season: selectedOption
                    }).then((res: any) => {
                        setInterviews(res.content);
                        setTotalPages(res.totalPages);
                    });

                })
                .catch((err) => {
                    console.log(err);
                })
                .finally(() => (isLoading.current = false));
        };
        if (!isLoading.current) fetchUser();
    }, [totalPages, currentPage, numberElementByPage, selectedOption]);

    const handlePageChange = (newPage: number) => {
        setCurrentPage(newPage);
    };

    const handleChangeNbElement = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setCurrentPage(0);
        setNumberElementByPage(Number(event.target.value));
    };

    const handleOptionChange = async (event: any) => {
        const selected = event.target.value;
        setSelectedOption(selected);
    };

    const getInterviewFromId = (id: number) => {
        for (let i = 0; i < interviews.length; i++) {
            if (interviews[i].id === id) {
                return interviews[i];
            }
        }
        return null;
    }

    const handleOnAccept = (interviewId: number) => {
        //todo change for employer
        console.log(getInterviewFromId(interviewId)!.student?.id)
        acceptInterview(interviewId, getInterviewFromId(interviewId)!.student?.id).then((res) => {
            console.log(res);
            if (res === true) {
                {/*Change the interview status to accepted*/
                }
                setInterviews(interviews.map((interview) => {
                    console.log(interview)
                    if (interview.id === interviewId) {
                        interview.state = "ACCEPTED";
                        saveStageStudent({
                            id: 0,
                            student_id: getInterviewFromId(interviewId)!.student?.id,
                            offer: interview.internOffer,
                            stateStudent: "PENDING",
                            stateEmployeur: "PENDING"
                        })
                    }
                    return interview;
                }));
                console.log(interviews);
            }
        });
    }

    const handleOnDecline = (interviewId: number) => {
        //todo change for employer
        declineInterview(interviewId, getInterviewFromId(interviewId)!.student?.id).then((res) => {
            console.log(res);
            if (res === true) {
                {/*Change the interview status to accepted*/
                }
                setInterviews(interviews.map((interview) => {
                    if (interview.id === interviewId) {
                        interview.state = "DECLINED";
                    }
                    return interview;
                }));
            }
        });
    }

    const handleReschdule = (id: number) => {
        navigate(`reschedule/${id}`, {state: {interview: getInterviewFromId(id)}})
    }

    const renderInterviews = (
        <main>
            <div
                className="overflow-x-hidden hover:overflow-auto border border-gray dark:border-darkgray xxxs:rounded-lg">
                <table className=" w-full divide-y divide-gray dark:divide-darkgray">
                    <thead className="bg-blue dark:bg-orange ">
                    <tr>
                        <th
                            scope="col"
                            className="xxxs:px-2 sm:px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                        >
                            {t("StudentInterview.table.title")}
                        </th>
                        <th
                            scope="col"
                            className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider max-md:hidden"
                        >
                            {t("StudentInterview.table.location")}
                        </th>
                        <th
                            scope="col"
                            className="xxxs:px-2 sm:px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                        >

                            {t("StudentInterview.table.date")}
                        </th>
                        <th
                            scope="col"
                            className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider max-sm:hidden"
                        >

                            {t("StudentInterview.table.company")}
                        </th>
                        <th
                            scope="col"
                            className="xxxs:px-2 sm:px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider">
                            {t("StudentInterview.table.status_text.text")}
                        </th>
                        {/**TODO FAIR i18n **/}
                        <th
                            scope="col"
                            className="xxxs:px-2 sm:px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider">
                            Actions
                        </th>
                    </tr>
                    </thead>
                    <tbody
                        className="bg-white text-black dark:text-white divide-y divide-gray dark:bg-dark dark:divide-darkgray">
                    {interviews.map((interview) => (
                        <tr key={interview.id}>
                            <td className="xxxs:px-2 sm:px-6 py-4 whitespace-nowrap
                                            min-w-full max-md:max-w-[10rem] max-w-[15rem]">
                                <div className="flex items-center">
                                    <div className="ml-4 overflow-hidden">
                                        <p className="text-ellipsis overflow-hidden text-sm font-medium dark:text-offwhite">{interview.internOffer.title}</p>
                                    </div>
                                </div>
                            </td>
                            <td className="px-6 py-4 whitespace-nowrap
                                            text-left text-sm font-medium max-md:hidden">
                                {interview.internOffer.location}
                            </td>
                            <td className="xxxs:px-2 sm:px-6 py-4 whitespace-nowrap
                                            text-left text-sm font-medium">
                                {new Date(Date.parse(interview.date)).toISOString().split('T')[0]} {new Date(Date.parse(interview.date)).toISOString().split('T')[1].split(':')[0]}:{new Date(Date.parse(interview.date)).toISOString().split('T')[1].split(':')[1]}
                            </td>
                            <td className="px-6 py-4 whitespace-nowrap
                                            text-left text-sm font-medium max-sm:hidden">
                                {interview.internOffer.employeurEntreprise}
                            </td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm dark:text-offwhite">
                                <span
                                    className={
                                        interview.state == "PENDING" ?
                                            "px-1 inline-flex text-xs leading-5 justify-center font-semibold rounded-full bg-orange text-white dark:text-offwhite"
                                            : interview.state === "DECLINED" ?
                                                "px-1 inline-flex text-xs leading-5 font-semibold justify-center rounded-full  bg-red text-white dark:text-offwhite"
                                                : "px-1 inline-flex text-xs leading-5 font-semibold rounded-full justify-center bg-green text-white dark:text-offwhite"}
                                >
                                     {t(`StudentInterview.${interview.state}.text`)}
                                </span>
                            </td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm dark:text-offwhite">
                                {interview.state === "DECLINED" && (
                                    <button onClick={() => handleReschdule(interview.id)}>
                                        {t("StudentInterview.table.reschedule")}
                                    </button>
                                )}
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </main>

    )

    return (<div className="">
        <div className="flex flex-col items-start max-md:pt-24">
            <header className=" pb-4">
                <h1 className="xxxs:text-2xl sm:text-3xl font-bold text-gray-900 dark:text-offwhite">
                    {t("StudentInterview.title.text")}
                </h1>
            </header>
            <div className="w-full">

                <PaginatedList renderItem={renderInterviews}
                               page={currentPage}
                               totalPages={totalPages}
                               onPageChange={handlePageChange}
                               numberElement={numberElementByPage}
                               handleChangeNumberElement={handleChangeNbElement}
                               selectedOption={selectedOption}
                               handleOptionChange={handleOptionChange}
                               seasons={seasons}
                />

            </div>
        </div>
    </div>)
}