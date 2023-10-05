import React, {useEffect, useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faFileLines, faPencil, faSignature, faUsers} from "@fortawesome/free-solid-svg-icons";
import {NavLink, useLocation} from "react-router-dom";
import {UpdateOffers} from "../api/InterOfferJobAPI";
import SidebarOptionSwitcher from "./SidebarOptionSwitcher";

function EmployeurHomePage() {
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
                    <h1 className="text-3xl font-bold text-gray-900">Dashboard</h1>
                </div>
            </header>
            <main>
                <div className="max-w-7xl mx-auto py-6 xxxs:px-6 lg:px-8">
                    <div className="w-full hidden md:block overflow-x-auto">
                            <div className="flex-row flex md:justify-center space-x-4">
                                <div

                                    className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                >
                                    <div className="flex space-x-2 items-center h-16 w-auto">
                                        <div className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                            <FontAwesomeIcon icon={faUsers} color="white" size="lg" />
                                        </div>
                                        <div className="pl-2">
                                            <p className="text-gray truncate ">Total candidature</p>
                                            <p className="text-xl dark:text-white font-bold">150</p>
                                        </div>
                                    </div>
                                </div>

                                <div
                                    className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                >
                                    <div className="flex space-x-2 items-center h-16 w-auto">
                                        <div className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                            <FontAwesomeIcon icon={faFileLines} color="white" size="lg" />
                                        </div>
                                        <div className="pl-2">
                                            <p className="text-gray">Total offer</p>
                                            <p className="text-xl dark:text-white font-bold">{offers.length}</p>
                                        </div>
                                    </div>
                                </div>

                                <NavLink
                                    to="/home/newOffer"
                                    className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                    onClick={() => setIsModalOpen(true)}
                                    state={user}
                                >
                                    <div className="flex space-x-2 items-center h-16 w-auto">
                                        <div className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                            <FontAwesomeIcon icon={faPencil} color="white" size="lg" />
                                        </div>
                                        <div className="pl-2">
                                            <p className="text-gray">New offer</p>

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
                                            <p className="text-gray">Total contract</p>
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
                    {/* <!-- /End replace --> */}
                </div>
            </main>
        </div>
    );
}

export default EmployeurHomePage;