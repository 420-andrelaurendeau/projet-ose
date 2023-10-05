import { Transition } from "@headlessui/react";
import imgDark from "././assets/images/Cegep-Andre-Laurendeau.png";
import img from "././assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faBars, faCircleUser, faXmark} from "@fortawesome/free-solid-svg-icons";
import SidebarEmployeurHome from "./components/common/SidebarEmployeurHome";
import {useTranslation} from "react-i18next";
import {NavLink} from "react-router-dom";
import React, {useState} from "react";
import Switcher from "./utils/switcher";
import SidebarEtudiant from "./SidebarEtudiant";

const Header = (props:any) => {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.Header");
    const [isOpen, setIsOpen] = useState(false);
    return (
        <div>
            {
                isOpen ?
                    <div className="fixed w-screen h-screen backdrop-blur-sm md:hidden"/>
                    : null
            }
            <div className="fixed w-full">
                <nav className="bg-white dark:bg-dark shadow ">
                    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                        <div className="flex items-center justify-between h-16">
                            <div className="flex items-center">
                                <div className="flex-shrink-0">
                                    <img
                                        className="mx-auto h-12 w-auto visible dark:hidden"
                                        src={img}
                                        alt="Your Company"
                                    />
                                    <img
                                        className="mx-auto h-12 w-auto hidden dark:flex"
                                        src={imgDark}
                                        alt="Your Company"
                                    />
                                </div>
                            </div>
                            {props.user.matricule && <div className="hidden md:block">
                                <NavLink to={"/home/offer"} state={props.user} className="ml-10 flex items-baseline space-x-4">
                                    <p className="text-blue dark:text-orange">{fields.stage.text}</p>
                                </NavLink>
                            </div>}
                            <Switcher/>
                            <div className="hidden md:block">
                                <FontAwesomeIcon icon={faCircleUser} className="text-blue dark:text-orange" size="xl" />
                            </div>
                            <div className="-mr-2 flex md:hidden">
                                <button
                                    onClick={() => setIsOpen(!isOpen)}
                                    type="button"
                                    className="inline-flex items-center justify-center p-2 rounded-md text-blue hover:text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-white focus:ring-white dark:focus:ring-offset-dark dark:focus:ring-dark"
                                    aria-controls="mobile-menu"
                                    aria-expanded="false"
                                >
                                    <span className="sr-only">Open main menu</span>
                                    {!isOpen ?
                                        <FontAwesomeIcon icon={faBars} color="black" className="block h-6 w-6 text-blue dark:text-orange" />
                                        : <FontAwesomeIcon icon={faXmark} color="black" className="block h-6 w-6 text-blue dark:text-orange" />
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
                            {
                                props.user.matricule ?
                                 <SidebarEtudiant
                                        user={props.user}
                                 />:
                                    <SidebarEmployeurHome
                                        user={props.user}
                                    />
                            }
                        </div>
                    </Transition>
                </nav>
            </div>

        </div>
    );
}

export default Header;