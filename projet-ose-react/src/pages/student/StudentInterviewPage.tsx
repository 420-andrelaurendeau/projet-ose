import React, {useContext, useEffect, useRef, useState} from "react";
import {
    fetchInterviews,
    acceptInterview,
    declineInterview,
    saveStageStudent
} from "../../api/StudentApi";
import {getUser} from "../../api/UtilisateurAPI";
import {useAuth} from "../../authentication/AuthContext";
import {Interview} from "../../model/Interview";
import i18n from "i18next";
import PaginatedList from "../../components/common/shared/paginationList/PaginatedList";
import {getAllSeasons} from "../../api/InterOfferJobAPI";


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

export default function StudentInterviewPage() {
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "StudentInterview");
    const [user, setUser] = useState<any>(null);
    const [interviews, setInterviews] = React.useState<Interview[]>([]);
    const isLoading = useRef(false);
    const auth = useAuth();
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [numberElementByPage, setNumberElementByPage] = useState<number>(100);
    const [seasons,setSeasons] = useState([])
    const [selectedOption, setSelectedOption] = useState(getActualSeason());

    const handleOnAccept = (interviewId: number) => {
        acceptInterview(interviewId, user.id).then((res) => {
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
                            student_id: user.id,
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
        declineInterview(interviewId, user.id).then((res) => {
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

    const handleOptionChange = async (event: any) => {
        const selected = event.target.value;
        setSelectedOption(selected);
    };

    useEffect(() => {
        const fetchUser = async () => {
            isLoading.current = true;

            getUser(auth.userEmail!)
                .then((resUser) => {
                    setUser(resUser);
                    console.log(resUser);
                    fetchInterviews(resUser.id, {
                        page: currentPage,
                        size: numberElementByPage,
                        sortField: "id",
                        sortDirection: "desc",
                        season: selectedOption,

                    }).then((res:any) => {
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

    useEffect(() => {
        interviews.map((interview) => {
            console.log(interview);
        });
    }, [interviews]);

    useEffect(() => {
        const fetchSeasons = async () => {
            let season = await  getAllSeasons();
            setSeasons(season);
        }
        fetchSeasons()
    }, []);

    const handlePageChange = (newPage: number) => {
        setCurrentPage(newPage);
    };

    const handleChangeNbElement = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setCurrentPage(0);
        setNumberElementByPage(Number(event.target.value));
    };

    const renderInterviews = (
        <main className={"pb-4"}>
            <table className=" w-full divide-y divide-gray dark:divide-darkgray">
                <thead className="bg-blue dark:bg-orange ">
                <tr>
                    <th
                        scope="col"
                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                    >
                        {fields.table.title}
                    </th>
                    <th
                        scope="col"
                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                    >
                        {fields.table.location}
                    </th>
                    <th
                        scope="col"
                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                    >

                        {fields.table.date}
                    </th>
                    <th
                        scope="col"
                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                    >

                        {fields.table.company}
                    </th>
                    <th
                        scope="col"
                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                    >

                        {fields.table.action.text}
                    </th>
                </tr>
                </thead>
                <tbody className="bg-white text-black divide-y divide-gray dark:bg-darkgray dark:divide-darkgray">
                {interviews.length === 0 && (
                    <tr>
                        <td colSpan={5} className="text-center bg-red text-white">
                            {fields.table.empty}
                        </td>
                    </tr>
                )}
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
                        <td className="px-6 py-4 whitespace-nowrap">
                            <div className="flex justify-center space-x-2">
                                <button
                                    disabled={interview.state != "PENDING"}
                                    onClick={() => handleOnAccept(interview.id)}
                                    className="disabled:bg-gray text-white bg-green hover:bg-green-700 px-3 py-2 rounded-md text-sm font-medium"
                                >
                                    {fields.table.action.button.accept}
                                </button>
                                <button
                                    disabled={interview.state != "PENDING"}
                                    onClick={() => handleOnDecline(interview.id)}
                                    className="disabled:bg-gray text-white bg-red hover:bg-red-700 px-3 py-2 rounded-md text-sm font-medium"
                                >
                                    {fields.table.action.button.decline}
                                </button>
                                {interview.state === "ACCEPTED" &&
                                    <p className="text-white bg-blue hover:bg-red-700 px-3 py-2 rounded-md text-sm font-bold">{fields.table.action.status.accepted}</p>}
                                {interview.state === "DECLINED" &&
                                    <p className="text-white bg-red hover:bg-red-700 px-3 py-2 rounded-md text-sm font-bold">{fields.table.action.status.declined}</p>}
                            </div>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </main>
    )

    return (
        <div className="dark:bg-softdark">
            <div className="flex flex-col items-center">
                <div className=" lg:-mx-8 mt-28 w-11/12 ">
                    <div
                        className=" md:z-50 md:top-0 md:left-0 justify-center md:w-full md:h-full md:flex md:p-3 max-md:w-full dark:text-white ">
                        <div className=" w-full">
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
                </div>
            </div>
        </div>
    );
}