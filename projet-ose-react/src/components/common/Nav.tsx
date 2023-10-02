import React, { useState } from "react";
import { Transition } from "@headlessui/react";
import imgDark from "../../assets/images/Cegep-Andre-Laurendeau.png";
import img from "../../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faBars, faCircleUser, faXmark} from "@fortawesome/free-solid-svg-icons";
import Sidebar from "./Sidebar";

const Nav = (props:any) => {
    const [isOpen, setIsOpen] = useState(false);
    interface FormData {
        nom: string;
        prenom: string;
        email: string;
        telephone: string;
        entreprise: string;
        programme: string;
    }
    return (
        <div>
            {
                isOpen ?
                    <div className="fixed w-screen h-screen backdrop-blur-sm"/>
                    : null
            }
            <div className="fixed w-full">
                <nav className="bg-white shadow ">
                    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                        <div className="flex items-center justify-between h-16">
                            <div className="flex items-center">
                                <div className="flex-shrink-0">
                                    <img
                                        className="h-8 w-8"
                                        src={img}
                                        alt="Workflow"
                                    />
                                </div>
                            </div>
                            <div className="hidden md:block">
                                <FontAwesomeIcon icon={faCircleUser} className="text-blue" size="xl" />
                            </div>
                            <div className="-mr-2 flex md:hidden">
                                <button
                                    onClick={() => setIsOpen(!isOpen)}
                                    type="button"
                                    className="inline-flex items-center justify-center p-2 rounded-md text-blue hover:text-white hover:bg-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-white focus:ring-white"
                                    aria-controls="mobile-menu"
                                    aria-expanded="false"
                                >
                                    <span className="sr-only">Open main menu</span>
                                    {!isOpen ?
                                        <FontAwesomeIcon icon={faBars} color="black" className="block h-6 w-6 text-blue" />
                                        : <FontAwesomeIcon icon={faXmark} color="black" className="block h-6 w-6 text-blue" />
                                    }
                                </button>
                            </div>
                        </div>
                    </div>
                    <Transition
                        show={isOpen}
                        enter="transition ease-out duration-100 transform"
                        enterFrom="opacity-0 scale-95"
                        enterTo="opacity-100 scale-100"
                        leave="transition ease-in duration-75 transform"
                        leaveFrom="opacity-100 scale-100"
                        leaveTo="opacity-0 scale-95"
                    >
                        <div className="md:hidden" >
                            <Sidebar
                                user={props.user}
                            />
                        </div>
                    </Transition>
                </nav>
            </div>

        </div>
    );
}

export default Nav;