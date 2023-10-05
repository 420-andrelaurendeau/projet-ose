import img from "./assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png";
import imgDark from "./assets/images/Cegep-Andre-Laurendeau.png";
import {Link, NavLink} from "react-router-dom";
import React, {useState} from "react";
import { Transition } from "@headlessui/react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faBars, faCircleUser, faXmark} from "@fortawesome/free-solid-svg-icons";
import Sidebar from "./Sidebar";
import {useTranslation} from "react-i18next";
import SidebarEtudiant from "./SidebarEtudiant";

function Header(props: any) {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.Header");
    const [isOpen, setIsOpen] = useState(false);
    return (
        <div>
            <nav className="bg-white shadow">
                <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                    <div className="flex items-center justify-between h-16">
                        <div className="flex items-center">
                            <div className="flex-shrink-0">
                                <NavLink to={"/home"} state={props.user} className="ml-10 flex items-baseline space-x-4">
                                    <img
                                        className="h-8 w-8"
                                        src={img}
                                        alt="Workflow"
                                    />
                                </NavLink>
                            </div>
                        </div>
                        {props.user.matricule && <div className="hidden md:block">
                            <NavLink to={"/etudiantStage"} state={props.user} className="ml-10 flex items-baseline space-x-4">
                                <p className="text-blue">{fields.stage.text}</p>
                            </NavLink>
                        </div>}
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
                    {(ref) => (
                        <div className="md:hidden" id="mobile-menu">
                            {props.user.matricule && <SidebarEtudiant user={props.user}/>}
                            {!props.user.matricule && <Sidebar user={props.user}/>}
                        </div>
                    )}
                </Transition>
            </nav>
        </div>
    );
}

export default Header;