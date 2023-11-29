import {Dialog, Transition} from "@headlessui/react";
import React, {Fragment, useEffect, useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCircleUser, faEnvelope, faGraduationCap, faGreaterThan, faPlug} from "@fortawesome/free-solid-svg-icons";
import Switcher from "../../../../utils/switcher";
import {LanguageIconEn, LanguageIconFr} from "../../../../utils/language/LanguageIcons";
import useDarkSide from "../../../../hooks/useDarkSide";
import {useTranslation} from "react-i18next";

function ProfilMenu(props: { show: boolean, onClose: () => void, user: any,language: string, sidebarIsOpen: boolean, onLogout: () => void }) {
    const {i18n} = useTranslation();
    const [language, setLanguage] = useState(i18n.language.slice(0, 2));
    const fields = i18n.getResource(language,"translation","formField");
    const [colorTheme, setTheme] = useDarkSide();
    const [darkSide, setDarkSide] = useState(
        colorTheme !== "light"
    );
    const [isSwitchStatus, setIsSwitchStatus] = useState(false);
    const [isSwitchLanguage, setIsSwitchLanguage] = useState(false);
    const [isActive, setIsActive] = useState(true);
    const [isSmallSize, setIsSmallSize] = useState(false);
    const maxSmSize = 767;

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

    useEffect(() => {
        function handleResize() {
            if (window.innerWidth <= maxSmSize) {
                setIsSmallSize(true);
            } else {
                setIsSmallSize(false);
            }
        }
        window.addEventListener('resize', handleResize)
    }, [])

    const getProgrammeName = (): string => {
        let prog:string = "";
        if (!fields.programs) return prog;
        Object.keys(fields.programs).forEach((key) => {
            if (fields.programs[key].id === props.user.programme_id) {
                prog = fields.programs[key].text;
            }
        });
        return prog;
    };

    return <Transition appear show={props.show} as={Fragment}>
        <Dialog as="div" className={isSmallSize && !props.sidebarIsOpen ? "hidden" : "relative"} onClose={props.onClose}>
            <Transition.Child
                as={Fragment}
                enter="ease-out duration-50"
                enterFrom="opacity-0"
                enterTo="opacity-100"
                leave="ease-in duration-50"
                leaveFrom="opacity-100"
                leaveTo="opacity-0"
            >
                <div className="fixed inset-0 bg-transparent"/>
            </Transition.Child>

            <div className="fixed inset-0 lg:px-8 px-6 max-w-7xl mx-auto z-[101]">
                <div className="md:flex justify-end">
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
                            className=" w-80 mt-14 max-sm:ml-0 max-md:ml-56 end-0 items-center rounded-2xl px-6 bg-lightgray dark:bg-darkergray py-6 shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none" data-testid="background">
                            <div className="flex  space-x-2">
                                <FontAwesomeIcon icon={faCircleUser}
                                                 className="text-blue hidden md:flex dark:text-orange h-12"
                                                 size="xl"/>
                                <div className="mt-4">
                                    <h2 className="text-black dark:text-white hidden md:flex tracking-wide"> {props.user.prenom + " " + props.user.nom}</h2>
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
                                            <p data-testid="programme-text">
                                                {
                                                    getProgrammeName() === "" ?
                                                        fields.Header.profilMenu.gsProfession.text:
                                                        getProgrammeName()
                                                }
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="mt-4">
                                <div className="block justify-between">
                                    <button
                                        className="md:pl-14 flex justify-between mt-2.5 w-full hover:bg-white dark:hover:bg-darkgray"
                                        onClick={toggleDarkMode}
                                        data-testid="change-theme"
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
                                        className="md:pl-14 flex justify-between mt-2.5 w-full hover:bg-white dark:hover:bg-darkgray"
                                        onClick={changeLanguage}
                                        data-testid="change-language"
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
                                    <button
                                        className="md:pl-14 flex justify-center mt-2.5 w-full text-white bg-red hover:bg-rose-800 rounded"
                                        onClick={props.onLogout}
                                    >
                                        <div className="flex w-full justify-between items-center px-4">
                                            <p className="text-white  text-start" data-testid="active-text">
                                                {fields.Header.profilMenu.signOut.text}
                                            </p>
                                            <FontAwesomeIcon icon={faPlug}/>
                                        </div>
                                    </button>
                                </div>
                            </div>
                        </Dialog.Panel>
                    </Transition.Child>
                </div>
            </div>
        </Dialog>
    </Transition>;
}

export default ProfilMenu;