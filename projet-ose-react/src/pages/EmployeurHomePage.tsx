import InternshipOfferForm from "../components/common/InternshipOfferForm";
import useModal from "../hooks/useModal";
import Switcher from "../utils/switcher";
import React from "react";
import Nav from "../components/common/Nav";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faFileLines, faPencil, faSignature, faSpinner, faUsers} from "@fortawesome/free-solid-svg-icons";

function EmployeurHomePage() {

    const {isModalOpen, handleOpenModal, handleCloseModal} = useModal();

    return (
        <div className="h-screen bg-darkwhite">
            <Nav/>
            <header className="">
                <div className="max-w-7xl mx-auto py-6 px-4 sm:px-6 lg:px-8">
                    <h1 className="text-3xl font-bold text-gray-900">Dashboard</h1>
                </div>
            </header>
            <main>
                <div className="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
                    <div className="hidden md:block">
                        <div className="w-full justify-center lg:flex lg:space-x-4 md:space-y-4 lg:space-y-0">
                            <div className="md:justify-center  flex space-x-4">
                                <a
                                    href="#"
                                    className="bg-white text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                >
                                    <div className="flex space-x-2 items-center h-16 w-44">
                                        <div className="bg-blue rounded-full h-12 w-12 flex items-center justify-center">
                                            <FontAwesomeIcon icon={faUsers} color="white" size="lg" />
                                        </div>
                                        <div className="pl-2">
                                            <p className="text-gray">Total candidature</p>
                                            <p className="text-xl font-bold">150</p>
                                        </div>
                                    </div>
                                </a>

                                <a
                                    href="#"
                                    className="bg-white text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                >
                                    <div className="flex space-x-2 items-center h-16 w-44">
                                        <div className="bg-blue rounded-full h-12 w-12 flex items-center justify-center">
                                            <FontAwesomeIcon icon={faFileLines} color="white" size="lg" />
                                        </div>
                                        <div className="pl-2">
                                            <p className="text-gray">Total offer</p>
                                            <p className="text-xl font-bold">150</p>
                                        </div>
                                    </div>
                                </a>

                                <a
                                    href="#"
                                    className="bg-white text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                >
                                    <div className="flex space-x-2 items-center h-16 w-44">
                                        <div className="bg-blue rounded-full h-12 w-12 flex items-center justify-center">
                                            <FontAwesomeIcon icon={faPencil} color="white" size="lg" />
                                        </div>
                                        <div className="pl-2">
                                            <p className="text-gray">New offer</p>
                                            <p className="text-xl font-bold">None</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div className="md:justify-center flex space-x-4">
                                <a
                                    href="#"
                                    className="bg-white text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                >
                                    <div className="flex space-x-2 items-center h-16 w-44">
                                        <div className="bg-blue rounded-full h-12 w-12 flex items-center justify-center">
                                            <FontAwesomeIcon icon={faSpinner} color="white" size="lg" />
                                        </div>
                                        <div className="pl-2">
                                            <p className="text-gray">Pending offer</p>
                                            <p className="text-xl font-bold">150</p>
                                        </div>
                                    </div>
                                </a>

                                <a
                                    href="#"
                                    className="bg-white text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                >
                                    <div className="flex space-x-2 items-center h-16 w-44">
                                        <div className="bg-blue rounded-full h-12 w-12 flex items-center justify-center">
                                            <FontAwesomeIcon icon={faSignature} color="white" size="lg" />
                                        </div>
                                        <div className="pl-2">
                                            <p className="text-gray">Total contract</p>
                                            <p className="text-xl font-bold">150</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                    {/* <!-- Replace with your content --> */}
                    <div className="px-4 py-6 sm:px-0">
                        <div className="border-4 border-dashed border-gray-200 rounded-lg h-96"></div>
                    </div>
                    {/* <!-- /End replace --> */}
                </div>
            </main>
        </div>
    );

    /*return (
        <html lang="en">
        <head>
            <title>Offre d'emploie</title>
        </head>
        <body>
        <div className="items-center">
            <Switcher/>

            <button onClick={handleOpenModal} className="w-full bg-bleu text-white font-bold p-2 rounded-md">
                Open Form
            </button>


            <InternshipOfferForm
                isModalOpen={isModalOpen}
                handleCloseModal={handleCloseModal}
                handleOpenModal={handleOpenModal}
            />
        </div>
        </body>
        </html>
    )*/
}

export default EmployeurHomePage;