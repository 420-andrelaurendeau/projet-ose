import {Transition} from "@headlessui/react";
import imgDark from "../../../../assets/images/Cegep-Andre-Laurendeau.png";
import img from "../../../../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faBars,
    faCircleUser,
    faXmark
} from "@fortawesome/free-solid-svg-icons";
import SidebarEmployeurHome from "../../SidebarEmployeurHome";
import {useTranslation} from "react-i18next";
import {NavLink, useLocation} from "react-router-dom";
import React, {useState} from "react";
import SidebarEtudiant from "../../../../SidebarEtudiant";
import ProfilMenu from "../../ProfilMenu";

const Header = (userd: any) => {
    const {i18n} = useTranslation();
    const [language, setLanguage] = useState(i18n.language.slice(0, 2));
    const fields = i18n.getResource(language, "translation", "formField");
    const [isOpen, setIsOpen] = useState(false);
    let [isOpenProfil, setIsOpenProfil] = useState(false)
    const location = useLocation();
    const user = userd.user;

    function closeModal() {
        setIsOpenProfil(false)
    }

    function openModal() {
        setIsOpenProfil(true)
    }


    return (
        <>
            {
                isOpen ?
                    <div className="fixed w-screen h-screen backdrop-blur-sm md:hidden"/>
                    : null
            }
            <div className="fixed z-40 w-full">
                <nav className="bg-white dark:bg-dark shadow ">
                    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                        <div className="flex items-center justify-between h-16">
                            <div className="flex items-center">
                                <NavLink to={"/"}>
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
                                </NavLink>
                            </div>
                            <button className="hidden md:block" onClick={openModal} data-testid="profil-button">
                                <FontAwesomeIcon icon={faCircleUser} className="text-blue dark:text-orange" size="xl"/>
                            </button>
                            <ProfilMenu show={isOpenProfil} onClose={closeModal} user={user}
                                        language={language} sidebarIsOpen={isOpen}
                            />
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
                                        <FontAwesomeIcon icon={faBars} color="black"
                                                         className="block h-6 w-6 text-blue dark:text-orange"/>
                                        : <FontAwesomeIcon icon={faXmark} color="black"
                                                           className="block h-6 w-6 text-blue dark:text-orange"/>
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
                        <div className="md:hidden">
                            {
                                user.matricule ?
                                    <SidebarEtudiant
                                        user={user}
                                        setIsOpen={setIsOpen}
                                    /> :
                                    <SidebarEmployeurHome
                                        user={user}
                                        setIsOpen={setIsOpen}
                                        onOpenProfil={openModal}
                                    />
                            }
                        </div>
                    </Transition>

                </nav>
            </div>

        </>
    );
}

export default Header;