import {Transition} from "@headlessui/react";
import imgDark from "../../../../assets/images/Cegep-Andre-Laurendeau.png";
import img from "../../../../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faBars,
    faCircleUser,
    faXmark,
    faFileLines, faFile, faInbox
} from "@fortawesome/free-solid-svg-icons";
import SidebarEmployeurHome from "../../Employer/SidebarEmployeurHome";
import {useTranslation} from "react-i18next";
import {NavLink, useNavigate} from "react-router-dom";
import React, {useEffect, useRef, useState} from "react";
import SidebarEtudiant from "../../student/SidebarEtudiant";
import ProfilMenu from "./ProfilMenu";
import {useAuth} from "../../../../authentication/AuthContext";
import {User} from "../../../../model/User";
import {getUser} from "../../../../api/UtilisateurAPI";
import {Message} from "../../../../model/Message";
import MessageBox from "../messaging/MessageBox";
import {fetchUserNotifications} from "../../../../api/NotificationAPI";

const Header = (userd: any) => {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.Header");
    const [language, setLanguage] = useState(i18n.language.slice(0, 2));
    const [isUserMenuOpen, setIsUserMenuOpen] = useState(false);
    const [isMessageBoxOpen, setIsMessageBoxOpen] = useState(false);
    let [isOpenProfil, setIsOpenProfil] = useState(false)
    const { userEmail , userRole, logoutUser } = useAuth();
    const navigate = useNavigate();
    const [messages, setMessages] = useState([] as Message[]);
    const [user, setUser] = useState<User>({
        id: 0,
        nom: "",
        prenom: "",
        email: "",
        phone: "",
        entreprise: "",
        programme: "",
        matricule: "",
    });

    function closeModal() {
        setIsOpenProfil(false)
    }

    function openModal() {
        setIsOpenProfil(true)
    }

    const isloading = useRef(false);

    useEffect(() => {
        const getUtilisateur = () => {
            isloading.current = true;
            if (userEmail)
                getUser(userEmail).then(user => {
                    fetchUserNotifications(user.id).then(messages => {
                        setMessages(messages);
                        console.log(messages);
                    });
                    setUser(user);
                    isloading.current = false;
                })
        }

        if (!isloading.current)
            getUtilisateur()

    }, [])

    return (
        <>
            {
                isUserMenuOpen ?
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
                                            alt="CÉGEP André-Laurendeau"
                                        />
                                        <img
                                            className="mx-auto h-12 w-auto hidden dark:flex"
                                            src={imgDark}
                                            alt="CÉGEP André-Laurendeau"
                                        />
                                    </div>
                                </NavLink>
                            </div>
                            <div className="relative my-auto">
                                <button className="relative" onClick={() => {
                                    setIsMessageBoxOpen(!isMessageBoxOpen)
                                    setIsUserMenuOpen(false)
                                }}>
                                    <FontAwesomeIcon icon={faInbox} className="text-blue dark:text-orange" size="xl"/>
                                    {messages.filter((value) => !value.read).length > 0
                                        ? <div className="w-2 h-2 bg-red rounded-full absolute top-0 right-0"></div>
                                        : <></>}
                                </button>
                                {
                                    isMessageBoxOpen
                                        ? <MessageBox messages={messages}></MessageBox>
                                        : <></>
                                }
                                <button className="md:inline-block hidden ms-8" onClick={openModal} data-testid="profil-button">
                                    <FontAwesomeIcon icon={faCircleUser} className="text-blue dark:text-orange" size="xl"/>
                                </button>
                            </div>
                            <ProfilMenu show={isOpenProfil} onClose={closeModal} user={user}
                                        language={language} sidebarIsOpen={isUserMenuOpen}
                                        onLogout={logoutUser}
                            />
                            <div className="-mr-2 flex md:hidden">
                                <button
                                    onClick={() => {
                                        setIsUserMenuOpen(!isUserMenuOpen)
                                        setIsMessageBoxOpen(false)
                                    }}
                                    type="button"
                                    className="inline-flex items-center justify-center p-2 rounded-md text-blue hover:text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-white focus:ring-white dark:focus:ring-offset-dark dark:focus:ring-dark"
                                    aria-controls="mobile-menu"
                                    aria-expanded="false"
                                >
                                    <span className="sr-only">Open main menu</span>
                                    {!isUserMenuOpen ?
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
                        show={isUserMenuOpen}
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
                                        setIsOpen={setIsUserMenuOpen}
                                    /> :
                                    <SidebarEmployeurHome
                                        user={user}
                                        setIsOpen={setIsUserMenuOpen}
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