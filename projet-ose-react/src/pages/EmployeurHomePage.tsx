import React, {useEffect, useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faFileLines, faPencil, faSignature, faUsers} from "@fortawesome/free-solid-svg-icons";
import {NavLink, useLocation} from "react-router-dom";
import {UpdateOffers} from "../api/InterOfferJobAPI";
import SidebarOptionSwitcher from "./SidebarOptionSwitcher";
import InternshipOfferModal from "../components/common/InternshipOfferModal";
import InternshipOfferForm from "../components/common/InternshipOfferForm";
import {ftruncate} from "fs";
import {useTranslation} from "react-i18next";

function EmployeurHomePage() {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.homeEmployeur");
    const [offers, setOffers] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(true)
    const location = useLocation();
    const user = location.state;

    useEffect(() => {
        console.log(user)
        UpdateOffers(user.email,setOffers)
    }, []);

    return (
        <div>
            <header className="max-md:hidden ">
                <div className="max-w-7xl mx-auto py-6 px-6  lg:px-8">
                    <h1 className="text-3xl font-bold text-gray-900"> {fields.titre.text} </h1>
                </div>
            </header>
            <main>
                <div className="max-w-7xl mx-auto py-6 xxxs:px-6 lg:px-8">
                    <div className="w-full hidden md:block overflow-x-auto">
                            <div className="flex-row flex md:justify-center space-x-4">
                                <NavLink
                                    to="/home/candidature"
                                    className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                    state={user}
                                >
                                    <div className="flex space-x-2 items-center h-16 w-auto">
                                        <div className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                            <FontAwesomeIcon icon={faUsers} color="white" size="lg" />
                                        </div>
                                        <div className="pl-2">
                                            <p className="text-gray truncate "> {fields.totalCondidature.text} </p>
                                            <p className="text-xl dark:text-white font-bold">150</p>
                                        </div>
                                    </div>
                                </NavLink>

                                <div
                                    className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                >
                                    <div className="flex space-x-2 items-center h-16 w-auto">
                                        <div className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                            <FontAwesomeIcon icon={faFileLines} color="white" size="lg" />
                                        </div>
                                        <div className="pl-2">
                                            <p className="text-gray">{fields.totalOffre.text}</p>
                                            <p className="text-xl dark:text-white font-bold">{offers.length}</p>
                                        </div>
                                    </div>
                                </div>

                                <NavLink
                                    to="/home/newOffer"
                                    className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                    state={user}
                                >
                                    <div className="flex space-x-2 items-center h-16 w-auto">
                                        <div className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                            <FontAwesomeIcon icon={faPencil} color="white" size="lg" />
                                        </div>
                                        <div className="pl-2">
                                            <p className="text-gray">{fields.newOffre.text}</p>

                                        </div>
                                    </div>
                                </NavLink>
                                <div
                                    className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                >
                                    <div className="flex space-x-2 items-center h-16 w-auto">
                                        <div className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                            <FontAwesomeIcon icon={faSignature} color="white" size="lg" />
                                        </div>
                                        <div className="pl-2">
                                            <p className="text-gray">{fields.totalContract.text}</p>
                                            <p className="text-xl dark:text-white font-bold">150</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </div>
                    <div className="w-full">
                        <SidebarOptionSwitcher
                            isModalOpen={isModalOpen}
                            setIsModalOpen={setIsModalOpen}
                            offers={offers}
                            setOffers={setOffers}
                            userEmail={user.email}
                            user={user}
                        />
                    </div>
                </div>
            </main>
        </div>
    );
}

export default EmployeurHomePage;