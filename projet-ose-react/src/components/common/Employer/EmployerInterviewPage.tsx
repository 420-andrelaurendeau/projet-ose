import i18n from "i18next";
import React, {useEffect, useRef, useState} from "react";
import {Interview} from "../../../model/Interview";
import {useAuth} from "../../../authentication/AuthContext";
import {getUser} from "../../../api/UtilisateurAPI";
import {acceptInterview, declineInterview, saveStageStudent} from "../../../api/StudentApi";
import PaginatedList from "../shared/paginationList/PaginatedList";
import {fetchInterviewsEmployer} from "../../../api/InterviewApi";
import {getAllSeasons} from "../../../api/InterOfferJobAPI";

export const EmployerInterviewPage = () => {
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "StudentInterview");
    const [user, setUser] = useState<any>(null);
    const [interviews, setInterviews] = React.useState<Interview[]>([]);
    const isLoading = useRef(false);
    const auth = useAuth();
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [numberElementByPage, setNumberElementByPage] = useState<number>(5);
    const [seasons,setSeasons] = useState([])
    const [selectedOption, setSelectedOption] = useState('');

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
                        session: selectedOption
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
    }, [totalPages, currentPage, numberElementByPage]);

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

    const renderInterviews = (
        <main className={"py-4"}>
            <div className="overflow-x-hidden hover:overflow-auto border border-gray dark:border-darkgray xxxs:rounded-lg">
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

                            {fields.table.date}
                        </th>
                        <th
                            scope="col"
                            className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                        >

                            {fields.table.company}
                        </th>
                        <th
                            scope="col"
                            className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider">
                            {fields.table.status_text.text}
                        </th>
                    </tr>
                    </thead>
                    <tbody className="bg-white text-black divide-y divide-gray dark:bg-darkgray dark:divide-darkgray">
                    {interviews.map((interview) => (
                        <tr key={interview.id}>
                            <td className="px-6 py-4 whitespace-nowrap
                                            text-center text-sm font-medium">
                                {interview.internOffer.title}
                            </td>
                            <td className="px-6 py-4 whitespace-nowrap
                                            text-center text-sm font-medium">
                                {interview.internOffer.location}
                            </td>
                            <td className="px-6 py-4 whitespace-nowrap
                                            text-center text-sm font-medium">
                                {new Date(Date.parse(interview.date)).toISOString().split('T')[0]}
                            </td>
                            <td className="px-6 py-4 whitespace-nowrap
                                            text-center text-sm font-medium">
                                {interview.internOffer.employeurEntreprise}
                            </td>
                            <td>
                                                                        <span
                                                                            className={
                                                                                interview.state == "PENDING" ?
                                                                                    "px-2 inline-flex text-xs leading-5 justify-center font-semibold rounded-full w-3/4 bg-orange text-white dark:text-offwhite"
                                                                                    : interview.state === "DECLINED" ?
                                                                                        "px-2 inline-flex text-xs leading-5 font-semibold justify-center rounded-full w-3/4 bg-red text-white dark:text-offwhite"
                                                                                        : "px-2 inline-flex text-xs leading-5 font-semibold rounded-full w-3/4 justify-center bg-green text-white dark:text-offwhite"}
                                                                        >
                                                {fields[interview.state].text}
                                            </span>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </main>

    )

    return (<div className="dark:bg-black">
        <div className="flex flex-col items-center">
            <div className=" lg:-mx-8 w-11/12 ">
                <div
                    className=" md:z-50 md:top-0 md:left-0 justify-center md:w-full md:h-full md:flex md:p-3 max-md:w-full ">
                    <div className=" w-full">
                        <PaginatedList renderItem={renderInterviews}
                                       page={currentPage}
                                       totalPages={totalPages}
                                       onPageChange={handlePageChange}
                                       numberElement={numberElementByPage}
                                       handleChangeNumberElement={handleChangeNbElement}
                                       selectedOption=""
                                       handleOptionChange={() => {}}
                                       seasons={["",""]}
                        />

                    </div>
                </div>
            </div>
        </div>
    </div>)
}