import React from "react";
import {NavLink, Outlet, useLocation} from "react-router-dom";
import Layout from "../../components/layout/Layout";
import {useAuth} from "../../authentication/AuthContext";
import {useTranslation} from "react-i18next";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faFileLines, faPencil} from "@fortawesome/free-solid-svg-icons";

function HomePage() {
    const location = useLocation();
    const user = location.state;
    const { userEmail, userRole, logoutUser } = useAuth();

    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation",'formField.header.' + i18n.language.slice(0, 2) + ".internshipmanager");


    return (
        <div className="">
            <header className="max-md:hidden pt-24 ">
                <div className="max-w-7xl mx-auto  px-6  lg:px-2">
                    <h1 className="text-3xl dark:text-white font-bold text-gray-900"> {fields.title} </h1>
                </div>
            </header>
            <main>
                <div className="max-w-9xl mx-auto xxxs:px-6 lg:px-2">
                    <div className=" max-w-7xl mx-auto border-b border-gray dark:border-darkgray mt-6 mb-10 hidden md:block ">
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
                                    + (location.pathname === `/${userRole}/home/newOffer` || location.pathname === `/${userRole}/home/newOffer/` ? " border-b-2" : "")
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
                                    + (location.pathname === `/${userRole}/home/contract` || location.pathname === `/${userRole}/home/contract/` ? " border-b-2" : "")
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
                    <div className="w-full">
                        <Outlet
                        />
                    </div>
                </div>
            </main>
        </div>
    );
}

export default HomePage;