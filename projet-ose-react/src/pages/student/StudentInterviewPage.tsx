import React, {useContext, useEffect, useRef, useState} from "react";
import {fetchInterviews, fetchInterviewsCountForStudent, acceptInterview, declineInterview} from "../../api/StudentApi";
import {getUser} from "../../api/UtilisateurAPI";
import {getStudentAppliedOffers, offresEtudiant} from "../../api/InterOfferJobAPI";
import {useAuth} from "../../authentication/AuthContext";
import {Interview} from "../../model/Interview";
import {AppliedOffers} from "../../model/AppliedOffers";
import {useProps} from "./StudentInternshipPage";


export default function StudentInterviewPage() {
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
                        interview.state = 0;
                    }
                    return interview;
                }));
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
                        interview.state = 2;
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
                                        Titre
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        Lieu de l'entretien
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        Date de l'entretien
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        Compagnie
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        Actions
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
                                                    disabled={interview.state != 1}
                                                    onClick={() => handleOnAccept(interview.id)}
                                                    className="text-white bg-green hover:bg-green-700 px-3 py-2 rounded-md text-sm font-medium"
                                                >
                                                    Accepter
                                                </button>
                                                <button
                                                    disabled={interview.state != 1}
                                                    onClick={() => handleOnDecline(interview.id)}
                                                    className="text-white bg-red hover:bg-red-700 px-3 py-2 rounded-md text-sm font-medium"
                                                >
                                                    Refuser
                                                </button>
                                                {interview.state == 0 && <p>Accepted</p>}
                                                {interview.state == 2 && <p>Declined</p>}
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