import React, { useEffect, useRef, useState } from "react";
import { NavLink, Outlet, useLocation, useOutletContext } from "react-router-dom";
import { AppliedOffers } from "../../model/AppliedOffers";
import { getStudentAppliedOffers, offresEtudiant } from "../../api/InterOfferJobAPI";
import axios from "axios";
import Header from "../../components/common/shared/header/Header";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faFileLines,
    faPencil,
    faSignature,
    faUsers,
    faMicrophone,
} from "@fortawesome/free-solid-svg-icons";
import { useTranslation } from "react-i18next";
import { useAuth } from "../../authentication/AuthContext";
import { getUser } from "../../api/UtilisateurAPI";
import {fetchInterviews, fetchInterviewsCountForStudent} from "../../api/StudentApi";
import { Interview } from "../../model/Interview";

interface Props {
    user: any;
    appliedOffers: AppliedOffers[];
    setAppliedOffers: React.Dispatch<React.SetStateAction<AppliedOffers[]>>;
    offers: never[];
}

function StudentInternshipPage() {
    const { i18n } = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField");
    const [user, setUser] = useState<any>(null);
    const [listStudentAppliedOffers, setListStudentAppliedOffers] = React.useState<AppliedOffers[]>([]);
    const [offers, setOffers] = useState([]);
    const [interviewsNb, setInterviewsNb] = React.useState<number>(0);
    const auth = useAuth();

    const isLoading = useRef(false);

    useEffect(() => {
        const fetchUser = async () => {
            isLoading.current = true;

            getUser(auth.userEmail!)
                .then((resUser) => {
                    setUser(resUser);
                    console.log(resUser);
                    getStudentAppliedOffers(resUser.id).then((res) => {
                        setListStudentAppliedOffers(res);
                    });
                    offresEtudiant().then((res) => {
                        setOffers(res);
                    });
                    fetchInterviewsCountForStudent(resUser.id).then((res) => {
                        setInterviewsNb(res);
                        console.log(interviewsNb);
                    });
                })
                .catch((err) => {
                    console.log(err);
                })
                .finally(() => (isLoading.current = false));
        };
        if (!isLoading.current) fetchUser();
    }, []);

    const context = {
        user: user,
        appliedOffers: listStudentAppliedOffers,
        setAppliedOffers: setListStudentAppliedOffers,
        offers: offers,
    };

    return (
        <div className="items-center">
            <div className="min-h-screen h-full bg-darkwhite dark:bg-softdark">
                <Header />
                <div className="pt-24 flex-row flex md:justify-center w-full">
                    <NavLink
                        to="offers"
                        className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-white hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                        state={user}
                    >
                        <div className="flex space-x-2 items-center h-16 w-auto">
                            <div className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                <FontAwesomeIcon icon={faFileLines} className="group-hover:text-white dark:text-white" size="lg" />
                            </div>
                            <div className="pl-2">
                                <p className="text-blue dark:text-orange">{fields.Header.sidebar.stage.text}</p>
                            </div>
                        </div>
                    </NavLink>
                    <NavLink
                        to="appliedOffers"
                        className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-white hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                        state={user}
                    >
                        <div className="flex space-x-2 items-center h-16 w-auto">
                            <div className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                <FontAwesomeIcon icon={faFileLines} className="group-hover:text-white dark:text-white" size="lg" />
                            </div>
                            <div className="pl-2">
                                <p className="text-blue dark:text-orange">{fields.Header.sidebar.offre_applique.text}</p>
                            </div>
                        </div>
                    </NavLink>
                    <NavLink
                        to="cv"
                        className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-white hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                        state={user}
                    >
                        <div className="flex space-x-2 items-center h-16 w-auto">
                            <div className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                <FontAwesomeIcon icon={faFileLines} className="group-hover:text-white dark:text-white" size="lg" />
                            </div>
                            <div className="pl-2">
                                <p className="text-blue dark:text-orange">{fields.Header.cv.text}</p>
                            </div>
                        </div>
                    </NavLink>
                    <NavLink
                        to="interview"
                        className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-white hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                    >
                        <div className="flex space-x-2 items-center h-16 w-auto">
                            <div className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                <FontAwesomeIcon icon={faMicrophone} className="group-hover:text-white dark:text-white" size="lg" />
                            </div>
                            <div className="pl-2">
                                <p className="text-blue dark:text-orange">{fields.Header.interview.text}</p>
                            </div>
                            {interviewsNb > 0 ? (
                                <p className="text-black dark:text-white">{interviewsNb}</p>
                            ) : (
                                <p className="text-black dark:text-white">0</p>
                            )}
                        </div>
                    </NavLink>
                </div>
                <Outlet context={context} />
            </div>
        </div>
    );
}

export function useProps() {
    return useOutletContext<Props>();
}

export default StudentInternshipPage;
