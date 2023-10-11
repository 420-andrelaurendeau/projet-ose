import {Dialog, Menu, Transition} from "@headlessui/react";
import imgDark from "././assets/images/Cegep-Andre-Laurendeau.png";
import img from "././assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faBars,
    faCircleUser,
    faEnvelope,
    faGraduationCap, faXmark, faPlug, faGreaterThan
} from "@fortawesome/free-solid-svg-icons";
import SidebarEmployeurHome from "./components/common/SidebarEmployeurHome";
import {useTranslation} from "react-i18next";
import {NavLink} from "react-router-dom";
import React, {Fragment, useEffect, useState} from "react";
import Switcher from "./utils/switcher";
import SidebarEtudiant from "./SidebarEtudiant";
import axios from "axios";
import useDarkSide from "./hooks/useDarkSide";
import {LanguageIconEn, LanguageIconFr} from "./components/common/LanguageIcons";


const Header = (props:any) => {
    const {i18n} = useTranslation();
    const [language, setLanguage] = useState(i18n.language.slice(0, 2));
    const fields = i18n.getResource(language,"translation","formField");
    const [isOpen, setIsOpen] = useState(false);
    const [isSwitchStatus, setIsSwitchStatus] = useState(false);
    const [isSwitchLanguage, setIsSwitchLanguage] = useState(false);
    const [isActive, setIsActive] = useState(true);
    let [isOpenProfil, setIsOpenProfil] = useState(false)
    const [colorTheme, setTheme] = useDarkSide();
    const [darkSide, setDarkSide] = useState(
        colorTheme !== "light"
    );

    function closeModal() {
        setIsOpenProfil(false)
    }

    function openModal() {
        setIsOpenProfil(true)
    }
    function switchStatus() {
        setTimeout(() => {
            setIsSwitchStatus(false)
        }, 1000);
        setIsSwitchStatus(true);
        setIsActive(!isActive);
    }

    function toggleDarkMode() {
        setDarkSide(!darkSide);
        // @ts-ignore
        setTheme(darkSide ? "dark" : "light");
    }

    function changeLanguage() {
        setTimeout(() => {
            setIsSwitchLanguage(false)
        }, 1000);
        setIsSwitchLanguage(true);
        if (language === "fr") {
            i18n.changeLanguage("en-US").then(r => console.log(r)).catch(e => console.log(e))
        } else {
            i18n.changeLanguage("fr-FR").then(r => console.log(r)).catch(e => console.log(e))
        }
    }

    useEffect(() => {
        if (language !== i18n.language.slice(0, 2)) {
            setLanguage(i18n.language.slice(0, 2))
        }
    } , [i18n.language])

    const getProgrammeName = (): string => {
         let prog:string = "";
         Object.keys(fields.programs).forEach((key) => {
            if (fields.programs[key].id === props.user.programme_id) {
                prog = fields.programs[key].text;
            }
        });
        return prog;
    };


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
                            {props.user.matricule && <div className="hidden md:flex">
                                <NavLink to={"/home/offer"} state={props.user}
                                         className="ml-10 flex items-baseline space-x-4">
                                    <p className="text-blue dark:text-orange">{fields.Header.stage.text}</p>
                                </NavLink>
                                <NavLink to={"/home/appliedOffers"} state={props.user}
                                         className="ml-10 flex items-baseline space-x-4">
                                    <p className="text-blue dark:text-orange">{fields.Header.sidebar.offre_applique.text}</p>
                                </NavLink>
                            </div>}
                            <button className="hidden md:block" onClick={openModal}>
                                <FontAwesomeIcon icon={faCircleUser} className="text-blue dark:text-orange" size="xl"/>
                            </button>
                            <Transition appear show={isOpenProfil} as={Fragment}>
                                <Dialog as="div" className="relative" onClose={closeModal}>
                                    <Transition.Child
                                        as={Fragment}
                                        enter="ease-out duration-300"
                                        enterFrom="opacity-0"
                                        enterTo="opacity-100"
                                        leave="ease-in duration-200"
                                        leaveFrom="opacity-100"
                                        leaveTo="opacity-0"
                                    >
                                        <div className="fixed inset-0 bg-transparent"/>
                                    </Transition.Child>

                                    <div className="fixed inset-0 px-8 max-w-7xl mx-auto">
                                        <div className="hidden md:flex justify-end">
                                            <Transition.Child
                                                as={Fragment}
                                                enter="ease-out duration-300"
                                                enterFrom="opacity-0 scale-95"
                                                enterTo="opacity-100 scale-100"
                                                leave="ease-in duration-200"
                                                leaveFrom="opacity-100 scale-100"
                                                leaveTo="opacity-0 scale-95"
                                            >
                                                <Dialog.Panel
                                                    className=" w-80 mt-14 end-0 items-center rounded-2xl px-6 bg-lightgray dark:bg-darkergray py-6 shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none">
                                                    <div className="flex  space-x-2">
                                                        <FontAwesomeIcon icon={faCircleUser}
                                                                         className="text-blue dark:text-orange h-12"
                                                                         size="xl"/>
                                                        <div className="mt-4">
                                                            <h2 className="text-black dark:text-white tracking-wide"> {props.user.prenom + ' ' + props.user.nom}</h2>
                                                            <div className="flex justify-between ">
                                                                <div className="text-black dark:text-gray text-xs">
                                                                    <FontAwesomeIcon icon={faEnvelope}/>
                                                                    <FontAwesomeIcon icon={faGraduationCap}/>
                                                                </div>
                                                                <div
                                                                    className="text-black dark:text-gray text-xs w-full">
                                                                    <p className="">
                                                                        {props.user.email}
                                                                    </p>
                                                                    <p className="">
                                                                        {getProgrammeName()}
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div className="mt-4">
                                                        <div className="block justify-between">
                                                            <button
                                                                className="pl-14 flex justify-between mt-2.5 w-full hover:bg-white dark:hover:bg-darkgray"
                                                                onClick={() => switchStatus()}
                                                            >
                                                                <div className="flex w-full justify-between ">
                                                                    <FontAwesomeIcon icon={faPlug}
                                                                                     bounce={isSwitchStatus}
                                                                                     className={isActive ? "text-green mt-1.5" : "text-red mt-1.5"}/>
                                                                    <p className="text-black dark:text-gray w-10/12 text-start">
                                                                        {isActive ? fields.Header.profilMenu.active.text : fields.Header.profilMenu.inactive.text}
                                                                    </p>
                                                                </div>
                                                                <FontAwesomeIcon icon={faGreaterThan}
                                                                                 className="mt-1.5 mr-1 text-black dark:text-gray"/>
                                                            </button>
                                                            <button
                                                                className="pl-14 flex justify-between mt-2.5 w-full hover:bg-white dark:hover:bg-darkgray"
                                                                onClick={() => toggleDarkMode()}
                                                            >
                                                                <div className="flex w-full justify-between ">
                                                                    <div className="h-2 mt-1">
                                                                        <Switcher
                                                                            darkSide={darkSide}
                                                                        />
                                                                    </div>
                                                                    <p className="text-black dark:text-gray w-10/12 text-start">
                                                                        {fields.Header.profilMenu.changeTheme.text}
                                                                    </p>
                                                                </div>
                                                                <FontAwesomeIcon icon={faGreaterThan}
                                                                                 className="mt-1.5 mr-1 text-black dark:text-gray"/>
                                                            </button>
                                                            <button
                                                                className="pl-14 flex justify-between mt-2.5 w-full hover:bg-white dark:hover:bg-darkgray"
                                                                onClick={() => changeLanguage()}
                                                            >
                                                                <div className="flex w-full justify-between ">
                                                                    {language === "en" ?
                                                                        <LanguageIconFr bounce={isSwitchLanguage}/>
                                                                        : <LanguageIconEn bounce={isSwitchLanguage}/>
                                                                    }

                                                                    <p className="text-black dark:text-gray w-10/12 text-start">
                                                                        {fields.Header.profilMenu.changeLanguage.text}
                                                                    </p>
                                                                </div>
                                                                <FontAwesomeIcon icon={faGreaterThan}
                                                                                 className="mt-1.5 mr-1 text-black dark:text-gray"/>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </Dialog.Panel>
                                            </Transition.Child>
                                        </div>
                                    </div>
                                </Dialog>
                            </Transition>
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
                                props.user.matricule ?
                                    <SidebarEtudiant
                                        user={props.user}
                                        setIsOpen={setIsOpen}
                                    /> :
                                    <SidebarEmployeurHome
                                        user={props.user}
                                        setIsOpen={setIsOpen}
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