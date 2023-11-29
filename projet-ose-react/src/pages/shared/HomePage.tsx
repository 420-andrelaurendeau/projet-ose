import React, {useEffect, useRef, useState} from "react";
import {NavLink, Outlet, useLocation} from "react-router-dom";
import Layout from "../../components/layout/Layout";
import {useAuth} from "../../authentication/AuthContext";
import {useTranslation} from "react-i18next";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faFileLines, faPencil} from "@fortawesome/free-solid-svg-icons";
import {getUser} from "../../api/UtilisateurAPI";
import {getStudentAppliedOffers} from "../../api/InterOfferJobAPI";
import {fetchInterviewsCountForStudent} from "../../api/StudentApi";

function HomePage() {
    const location = useLocation();
    const { userEmail, userRole, logoutUser } = useAuth();

    const {i18n} = useTranslation();
    const {t} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation",'formField.header.' + i18n.language.slice(0, 2) + ".internshipmanager");

    const [user, setUser] = useState<any>(null);
    const isLoading = useRef(false);
    useEffect(() => {
        const fetchUser = async () => {
            isLoading.current = true;

            getUser(userEmail!)
                .then( (resUser) => {
                    setUser(resUser);
                })
                .catch((err) => {
                    console.log(err);
                })
                .finally(() => (isLoading.current = false));
        };
        if (!isLoading.current) fetchUser();
    }, []);

    return (
        <div className="min-h-screen h-full dark:bg-softdark">
            <header className="max-md:hidden pt-24 ">
                <div className="max-w-7xl mx-auto  px-6  lg:px-2">
                    <h1 className="text-3xl dark:text-white font-bold text-gray-900"> {fields.title} </h1>
                </div>
            </header>
            <main>
                <div className="max-w-7xl mx-auto xxxs:px-6 lg:px-8">
                    <div className="w-full border-b border-gray dark:border-darkgray mt-6 hidden mb-10 md:block overflow-x-auto">
                        <div className="flex-row flex md:justify-start">
                            <NavLink to="offers"
                                     className={"flex space-x-2 justify-center border-blue dark:border-orange px-5 items-center h-14" +
                                         (location.pathname ===  `/${userRole}/home/offers` || location.pathname === `/${userRole}/home/offers/` ? " border-b-2" : "")
                                     }
                                     state={user}
                            >
                                <FontAwesomeIcon icon={faFileLines} className="dark:text-white" size="sm" />
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">{fields.offers}</p>
                                </div>
                            </NavLink>

                            <NavLink
                                to="studentCvReview"
                                className={"flex space-x-2 items-center border-blue dark:border-orange h-14 px-5 justify-center"
                                    + (location.pathname === `/${userRole}/home/studentCvReview` || location.pathname === `/${userRole}/home/studentCvReview/` ? " border-b-2" : "")
                                }
                                state={user}
                            >
                                <FontAwesomeIcon icon={faPencil} className="dark:text-white" size="sm" />
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">{fields.cv}</p>
                                </div>
                            </NavLink>

                            <NavLink
                                to={ userRole !== "internshipmanager" ? "contract" : "internshipagreement"}
                                className={"flex space-x-2 items-center border-blue dark:border-orange h-14 px-5 justify-center"
                                    + (location.pathname === `/${userRole}/home/internshipagreement` || location.pathname === `/${userRole}/home/internshipagreement/` ? " border-b-2" : "")
                                }
                                state={user}
                            >
                                <FontAwesomeIcon icon={faPencil} className="dark:text-white" size="sm" />
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">{fields.internshipAgreement}</p>
                                </div>
                            </NavLink>
                        </div>
                    </div>
                    {window.location.pathname === `/${userRole}/home/` &&
                        <div className="w-full">
                            <h1 className="text-black dark:text-white text-center text-3xl xxxs:pt-16 md:pt-4">{t("formField.Home.text")}{user?.prenom} {user?.nom}</h1>
                            <p className="text-black dark:text-white text-center text-2xl">{t("formField.Home.text2")}</p>
                        </div>
                    }
                    <div className="w-full h-full">
                        <Outlet
                        />
                    </div>
                </div>
            </main>
        </div>
    );
}

export default HomePage;