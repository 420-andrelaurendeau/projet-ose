import InternshipOfferForm from "../components/common/InternshipOfferForm";
import useModal from "../hooks/useModal";
import Switcher from "../utils/switcher";
import React, {useEffect, useState} from "react";
import Nav from "../components/common/Nav";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faFileLines, faPencil, faSignature, faSpinner, faUsers} from "@fortawesome/free-solid-svg-icons";
import {NavLink, Outlet, Route, useLocation} from "react-router-dom";
import {getProgrammes} from "../api/ProgrammeAPI";
import {getInterOfferJob} from "../api/InterOfferJobAPI";
import EmployeurOffer from "../components/common/EmployeurOffer";

function EmployeurHomePage() {
    const location = useLocation();
    const user = location.state;
    const [offers, setOffers] = useState([]);


    const user1 = {
        "id": 4,
        "nom": "Doe",
        "prenom": "John",
        "email": "john@gmail.com",
        "telephone": "514-123-4567",
        "entreprise": "Google",
        "programme": "Génie logiciel",
    }

    useEffect(() => {
        const loadOffers = async () => {
            try {
                const data = await getInterOfferJob(4);
                console.log(data);
                setOffers(data);
            } catch (error) {
                console.error('Erreur lors du chargement des programmes:', error);
            }
        };

        loadOffers().then(r => console.log(r));
    }, []);
    const [isModalOpen, setIsModalOpen] = useState(true)

    return (
        <div className="min-h-screen h-full bg-darkwhite">
            <Nav
                user={user1}
            />
            <header className="max-md:hidden ">
                <div className="max-w-7xl mx-auto py-6 px-6  lg:px-8">
                    <h1 className="text-3xl font-bold text-gray-900">Dashboard</h1>
                </div>
            </header>
            <main>
                <div className="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
                    <div className="w-full hidden md:block overflow-x-auto">
                            <div className="flex-row flex md:justify-center space-x-4">
                                <NavLink
                                    to="#"
                                    className="border border-gray bg-white basis-1/4 text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                >
                                    <div className="flex space-x-2 items-center h-16 w-auto">
                                        <div className="bg-blue rounded-full h-12 w-12 flex items-center justify-center">
                                            <FontAwesomeIcon icon={faUsers} color="white" size="lg" />
                                        </div>
                                        <div className="pl-2">
                                            <p className="text-gray truncate ">Total candidature</p>
                                            <p className="text-xl font-bold">150</p>
                                        </div>
                                    </div>
                                </NavLink>

                                <NavLink
                                    to="#"
                                    className="border border-gray bg-white basis-1/4 text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                >
                                    <div className="flex space-x-2 items-center h-16 w-auto">
                                        <div className="bg-blue rounded-full h-12 w-12 flex items-center justify-center">
                                            <FontAwesomeIcon icon={faFileLines} color="white" size="lg" />
                                        </div>
                                        <div className="pl-2">
                                            <p className="text-gray">Total offer</p>
                                            <p className="text-xl font-bold">{offers.length}</p>
                                        </div>
                                    </div>
                                </NavLink>

                                <NavLink
                                    to="/homeEmployeur/newOffer"
                                    className="border border-gray bg-white basis-1/4 text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                    onClick={() => setIsModalOpen(true)}
                                >
                                    <div className="flex space-x-2 items-center h-16 w-auto">
                                        <div className="bg-blue rounded-full h-12 w-12 flex items-center justify-center">
                                            <FontAwesomeIcon icon={faPencil} color="white" size="lg" />
                                        </div>
                                        <div className="pl-2">
                                            <p className="text-gray">New offer</p>

                                        </div>
                                    </div>
                                </NavLink>
                                <NavLink
                                    to="#"
                                    className="border border-gray bg-white basis-1/4 text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                >
                                    <div className="flex space-x-2 items-center h-16 w-auto">
                                        <div className="bg-blue rounded-full h-12 w-12 flex items-center justify-center">
                                            <FontAwesomeIcon icon={faSignature} color="white" size="lg" />
                                        </div>
                                        <div className="pl-2">
                                            <p className="text-gray">Total contract</p>
                                            <p className="text-xl font-bold">150</p>
                                        </div>
                                    </div>
                                </NavLink>
                            </div>
                    </div>
                    {/* <!-- Replace with your content --> */}
                    <div className="w-full">
                        <Outlet
                            context={[isModalOpen, setIsModalOpen,offers]}
                        />
                    </div>
                    {/* <!-- /End replace --> */}
                </div>
            </main>
        </div>
    );
}

export default EmployeurHomePage;