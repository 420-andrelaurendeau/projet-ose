import React, {useEffect, useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faFileLines, faPencil, faSignature, faUsers} from "@fortawesome/free-solid-svg-icons";
import {NavLink, Outlet, useLocation, useOutletContext} from "react-router-dom";
import {UpdateOffers} from "../api/InterOfferJobAPI";
import {useTranslation} from "react-i18next";
import Header from "../Header";

interface Props {
    isModalOpen: boolean,
    setIsModalOpen: React.Dispatch<React.SetStateAction<boolean>>,
    offers: any[],
    setOffers: React.Dispatch<React.SetStateAction<any[]>>,
    user: any
}

function EmployeurHomePage() {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.homeEmployeur");
    const [offers, setOffers] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(true)
    const [nbCandidature, setNbCandidature] = useState(0)
    const location = useLocation();
    const user = location.state;

    useEffect(() => {
        console.log(user)
        UpdateOffers(user.email,setOffers)
    }, []);

    useEffect(() => {
        let i = 0;
        offers.map((offer:any) => {
            i += offer.internshipCandidates.length;
        })
        setNbCandidature(i);
    }, [offers]);

    const context =  {
        isModalOpen: isModalOpen,
        setIsModalOpen: setIsModalOpen,
        offers: offers,
        setOffers: setOffers,
        user: user,
    }

    console.log(context)

    return (
        <div className="min-h-screen h-full bg-darkwhite dark:bg-softdark">
            <Header/>
            <header className="max-md:hidden pt-14 ">
                <div className="max-w-7xl mx-auto py-6 px-6  lg:px-8">
                    <h1 className="text-3xl dark:text-white font-bold text-gray-900"> {fields.titre.text} </h1>
                </div>
            </header>
            <main>
                <div className="max-w-7xl mx-auto py-6 xxxs:px-6 lg:px-8">
                    <div className="w-full hidden md:block overflow-x-auto">
                            <div className="flex-row flex md:justify-center space-x-4">
                                <NavLink
                                    to="offre/candidature"
                                    className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                    state={user}
                                >
                                    <div className="flex space-x-2 items-center h-16 w-auto">
                                        <div className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                            <FontAwesomeIcon icon={faUsers} color="white" size="lg" />
                                        </div>
                                        <div className="pl-2">
                                            <p className="text-gray truncate "> {fields.totalCondidature.text} </p>
                                            <p className="text-xl dark:text-white font-bold">{nbCandidature}</p>
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
                                    to="offre/nouvelleOffre"
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
                        <Outlet
                            context={context}
                        />
                    </div>
                </div>
            </main>
        </div>
    );
}


export function useProps(){
    return useOutletContext<Props>();
}
export default EmployeurHomePage;