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
    faMicrophone, faPersonDigging,
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
        <div className="min-h-screen h-full">
            <header className="max-md:hidden pt-24 ">
                <div className="max-w-7xl mx-auto  px-6  lg:px-8">
                    <h1 className="text-3xl dark:text-white font-bold text-gray-900"> {fields.homeEmployeur.titre.text}  </h1>
                </div>
            </header>
            <main>
                <div className="max-w-7xl mx-auto xxxs:px-6 lg:px-8">
                    <div
                        className="w-full border-b border-gray dark:border-darkgray mt-6 mb-10 hidden md:block overflow-x-auto">
                        <div className="flex-row flex md:justify-start">
                            <NavLink to="offers"
                                     className={"flex space-x-2 justify-center border-blue dark:border-orange px-5 items-center h-14" +
                                         (location.pathname === `/${auth.userRole}/home/offers` || location.pathname === `/${auth.userRole}/home/offers/` ? " border-b-2" : "")
                                     }
                                     state={user}
                            >
                                <FontAwesomeIcon icon={faFileLines} className="dark:text-white" size="sm"/>
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">{fields.Header.sidebar.stage.text}</p>
                                </div>
                            </NavLink>

                            <NavLink
                                to="appliedOffers"
                                className={"flex space-x-2 items-center border-blue dark:border-orange h-14 px-5 justify-center"
                                    + (location.pathname === `/${auth.userRole}/home/appliedOffers` || location.pathname === `/${auth.userRole}/home/appliedOffers/` ? " border-b-2" : "")
                                }
                                state={user}
                            >
                                <FontAwesomeIcon icon={faPencil} className="dark:text-white" size="sm"/>
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">{fields.Header.sidebar.offre_applique.text}</p>
                                </div>
                            </NavLink>

                            <NavLink
                                to="cv"
                                className={"flex space-x-2 items-center border-blue dark:border-orange h-14 px-5 justify-center"
                                    + (location.pathname === `/${auth.userRole}/home/cv` || location.pathname === `/${auth.userRole}/home/cv/` ? " border-b-2" : "")
                                }
                                state={user}
                            >
                                <FontAwesomeIcon icon={faPencil} className="dark:text-white" size="sm"/>
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">{fields.Header.cv.text}</p>
                                </div>
                            </NavLink>
                            <NavLink
                                to="interview"
                                className={"flex space-x-2 items-center border-blue dark:border-orange h-14 px-5 justify-center"
                                    + (location.pathname === `/${auth.userRole}/home/interview` || location.pathname === `/${auth.userRole}/home/interview/` ? " border-b-2" : "")
                                }
                                state={user}
                            >
                                <FontAwesomeIcon icon={faMicrophone} className="dark:text-white" size="sm"/>
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">{fields.Header.interview.text}</p>
                                </div>
                            </NavLink>
                            <NavLink
                                to="stage"
                                className={"flex space-x-2 items-center border-blue dark:border-orange h-14 px-5 justify-center"
                                    + (location.pathname === `/${auth.userRole}/home/stage` || location.pathname === `/${auth.userRole}/home/stage/` ? " border-b-2" : "")
                                }
                                state={user}
                            >
                                <FontAwesomeIcon icon={faPersonDigging} className="dark:text-white" size="sm"/>
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">Stage</p>
                                </div>
                            </NavLink>
                            <NavLink
                                to="contract"
                                className={"flex space-x-2 items-center border-blue dark:border-orange h-14 px-5 justify-center"
                                    + (location.pathname === `/${auth.userRole}/home/contract` || location.pathname === `/${auth.userRole}/home/stage/` ? " border-b-2" : "")
                                }
                                state={user}
                            >
                                <FontAwesomeIcon icon={faPersonDigging} className="dark:text-white" size="sm"/>
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">Contract</p>
                                </div>
                            </NavLink>
                        </div>

                    </div>
                    <div className="w-full">
                        <Outlet
                            context={context}
                        />
                    </div>
                </div>
            </main>
        </div>
    );
}

export function useProps() {
    return useOutletContext<Props>();
}

export default StudentInternshipPage;
