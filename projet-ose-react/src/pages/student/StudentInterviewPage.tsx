import React, {useContext, useEffect, useRef, useState} from "react";
import {fetchInterviews, acceptInterview, declineInterview} from "../../api/StudentApi";
import {getUser} from "../../api/UtilisateurAPI";
import {useAuth} from "../../authentication/AuthContext";
import {Interview} from "../../model/Interview";
import i18n from "i18next";


export default function StudentInterviewPage() {
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "StudentInterview");
    const [user, setUser] = useState<any>(null);
    const [interviews, setInterviews] = React.useState<Interview[]>([]);
    const isLoading = useRef(false);
    const auth = useAuth();

    const handleOnAccept = (interviewId: number) => {
        acceptInterview(interviewId, user.id).then((res) => {
            console.log(res);
            if (res === true) {
                {/*Change the interview status to accepted*/}
                setInterviews(interviews.map((interview) => {
                    if (interview.id === interviewId) {
                        interview.state = "ACCEPTED";
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
                {/*Change the interview status to accepted*/}
                setInterviews(interviews.map((interview) => {
                    if (interview.id === interviewId) {
                        interview.state = "DECLINED";
                    }
                    return interview;
                }));
            }
        });
    }

    useEffect(() => {
        const fetchUser = async () => {
            isLoading.current = true;

            getUser(auth.userEmail!)
                .then((resUser) => {
                    setUser(resUser);
                    console.log(resUser);
                    fetchInterviews(resUser.id).then((res) => {
                        setInterviews(res);
                    });
                })
                .catch((err) => {
                    console.log(err);
                })
                .finally(() => (isLoading.current = false));
        };
        if (!isLoading.current) fetchUser();
    }, []);

    useEffect(() => {
        interviews.map((interview) => {
            console.log(interview);
        });
    }, [interviews]);

    return (
        <div className="text-white  dark:bg-black">
            <div className="flex flex-col items-center">
                <div className=" lg:-mx-8 mt-28 w-11/12 ">
                    <div
                        className=" md:z-50 md:top-0 md:left-0 justify-center md:w-full md:h-full md:flex md:p-3 max-md:w-full ">
                        <div className=" w-full">
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
                                            {interview.date}
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
                                                {interview.state === "ACCEPTED" && <p className="text-white bg-blue hover:bg-red-700 px-3 py-2 rounded-md text-sm font-bold">{fields.table.action.status.accepted}</p>}
                                                {interview.state === "DECLINED" && <p className="text-white bg-red hover:bg-red-700 px-3 py-2 rounded-md text-sm font-bold">{fields.table.action.status.declined}</p>}
                                            </div>
                                        </td>
                                    </tr>
                                ))}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}