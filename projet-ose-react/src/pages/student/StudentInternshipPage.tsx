import React, { useEffect, useRef, useState } from "react";
import { NavLink, Outlet, useLocation, useOutletContext } from "react-router-dom";
import { AppliedOffers } from "../../model/AppliedOffers";
import {getAllSeasons, getStudentAppliedOffers, offresEtudiant} from "../../api/InterOfferJobAPI";
import axios from "axios";
import Header from "../../components/common/shared/header/Header";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faFileLines,
    faPencil,
    faSignature,
    faUsers,
    faMicrophone, faPersonDigging,
} from "@fortawesome/free-solid-svg-icons";
import { useTranslation } from "react-i18next";
import { useAuth } from "../../authentication/AuthContext";
import { getUser } from "../../api/UtilisateurAPI";
import {fetchInterviews, fetchInterviewsCountForStudent} from "../../api/StudentApi";
import { Interview } from "../../model/Interview";

interface Props {
    user: any;
    appliedOffers: AppliedOffers[];
    setAppliedOffers: React.Dispatch<React.SetStateAction<AppliedOffers[]>>;
    offers: any[];
    setOffers: React.Dispatch<React.SetStateAction<any[]>>;
    setSortField: React.Dispatch<React.SetStateAction<string>>;
    setSortDirection: React.Dispatch<React.SetStateAction<string>>;
    sortField: string;
    sortDirection: string;
    totalPages: number;
    setCurrentPage: React.Dispatch<React.SetStateAction<number>>;
    handleChangeNumberElement: (event: React.ChangeEvent<HTMLSelectElement>) => void;
    onPageChange: (newPage: number) => void;
    numberElementByPage: number;
    page: number;
    seasons: any[];
    selectedOption: string;
    handleChangeOption: (event: React.ChangeEvent<HTMLSelectElement>) => void;

}

const getActualSeason = () => {
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    const currentMonth = currentDate.getMonth();
    let session = '';

    if (currentMonth >= 5 && currentMonth <= 8) {
        session = 'Été';
    } else if (currentMonth >= 9 || currentMonth <= 1) {
        session = 'Automne';
    } else {
        session = 'Hiver';
    }

    if (session === 'Été' || session === 'Automne') {
        return `Hiver${currentYear + 1}`;
    } else {
        return `Été${currentYear}`;
    }
}

function StudentInternshipPage() {
    const { i18n ,t} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "StudentInternshipPage");
    const [user, setUser] = useState<any>(null);
    const [listStudentAppliedOffers, setListStudentAppliedOffers] = React.useState<AppliedOffers[]>([]);
    const [offers, setOffers] = useState([]);
    const [interviewsNb, setInterviewsNb] = React.useState<number>(0);
    const {userId, userEmail, userRole} = useAuth();
    const [numberElementByPage, setNumberElementByPage] = useState<number>(100)
    const [sortField, setSortField] = useState("id");
    const [sortDirection, setSortDirection] = useState("asc");
    const [totalPages, setTotalPages] = useState(0);
    const [currentPage, setCurrentPage] = useState(0);
    const [seasons,setSeasons] = useState([])
    const [selectedOption, setSelectedOption] = useState(getActualSeason());

    const isLoading = useRef(false);

    //TODO cchanger que ca utilise le user passer en props
    useEffect(() => {
        const fetchUser = async () => {
            isLoading.current = true;

            getUser(userEmail!)
                .then((resUser) => {
                    setUser(resUser);
                    getStudentAppliedOffers(userId!).then((res) => {
                        setListStudentAppliedOffers(res);
                    });
                    fetchInterviewsCountForStudent(resUser.id).then((res) => {
                        setInterviewsNb(res);
                    });
                })
                .catch((err) => {
                    console.log(err);
                })
                .finally(() => (isLoading.current = false));
        };
        if (!isLoading.current) fetchUser();
    }, [selectedOption]);


    useEffect(() => {
        const fetchOffers = async () => {
            isLoading.current = true;
            offresEtudiant(setOffers, setTotalPages, {
                    page: currentPage,
                    size: numberElementByPage,
                    sortField,
                    sortDirection,
                    session: selectedOption
                }
            );
            let season = await  getAllSeasons();
            setSeasons(season);
            isLoading.current = false;
        };
        fetchOffers();

    }, [currentPage, selectedOption,numberElementByPage, sortField, sortDirection]);

    const handleChangePage = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setCurrentPage(0);
        setNumberElementByPage(Number(event.target.value));
    };

    const handlePageChange = (newPage: number) => {
        setCurrentPage(newPage);
    };

    const handleOptionChange = async (event: any) => {
        const selected = event.target.value;
        setSelectedOption(selected);
    };

    const context = {
        user: user,
        appliedOffers: listStudentAppliedOffers,
        setAppliedOffers: setListStudentAppliedOffers,
        offers: offers,
        setOffers: setOffers,
        setSortField: setSortField,
        setSortDirection: setSortDirection,
        sortField: sortField,
        sortDirection: sortDirection,
        totalPages: totalPages,
        setCurrentPage: setCurrentPage,
        handleChangeNumberElement: handleChangePage,
        onPageChange: handlePageChange,
        numberElementByPage: numberElementByPage,
        page: currentPage,
        seasons: seasons,
        selectedOption: selectedOption,
        handleChangeOption: handleOptionChange,
    };

    return (
        <div className="min-h-screen h-full">
            <header className="max-md:hidden pt-24 ">
                <div className="max-w-7xl mx-auto  px-6  lg:px-8">
                    <h1 className="text-3xl dark:text-white font-bold text-gray-900"> {t("StudentInternshipPage.titre.text")}  </h1>
                </div>
            </header>
            <main>
                <div className="max-w-7xl mx-auto xxxs:px-6 lg:px-8">
                    <div
                        className="w-full border-b border-gray dark:border-darkgray mt-6 hidden md:block overflow-x-auto">
                        <div className="flex-row flex md:justify-start">
                            <NavLink to="offers"
                                     className={"flex space-x-2 justify-center border-blue dark:border-orange px-5 items-center h-14" +
                                         (location.pathname === `/${userRole}/home/offers` || location.pathname === `/${userRole}/home/offers/` ? " border-b-2" : "")
                                     }
                                     state={user}
                            >
                                <FontAwesomeIcon icon={faFileLines} className="dark:text-white" size="sm"/>
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">{t("StudentInternshipPage.stages.text")}</p>
                                </div>
                            </NavLink>

                            <NavLink
                                to="appliedOffers"
                                className={"flex space-x-2 items-center border-blue dark:border-orange h-14 px-5 justify-center"
                                    + (location.pathname === `/${userRole}/home/appliedOffers` || location.pathname === `/${userRole}/home/appliedOffers/` ? " border-b-2" : "")
                                }
                                state={user}
                            >
                                <FontAwesomeIcon icon={faPencil} className="dark:text-white" size="sm"/>
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">{t("StudentInternshipPage.offre_applique.text")}</p>
                                </div>
                            </NavLink>

                            <NavLink
                                to="cv"
                                className={"flex space-x-2 items-center border-blue dark:border-orange h-14 px-5 justify-center"
                                    + (location.pathname === `/${userRole}/home/cv` || location.pathname === `/${userRole}/home/cv/` ? " border-b-2" : "")
                                }
                                state={user}
                            >
                                <FontAwesomeIcon icon={faPencil} className="dark:text-white" size="sm"/>
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">{t("StudentInternshipPage.cv.text")}</p>
                                </div>
                            </NavLink>
                            <NavLink
                                to="interview"
                                className={"flex space-x-2 items-center border-blue dark:border-orange h-14 px-5 justify-center"
                                    + (location.pathname === `/${userRole}/home/interview` || location.pathname === `/${userRole}/home/interview/` ? " border-b-2" : "")
                                }
                                state={user}
                            >
                                <FontAwesomeIcon icon={faMicrophone} className="dark:text-white" size="sm"/>
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">{t("StudentInternshipPage.interview.text")}</p>
                                </div>
                            </NavLink>
                            <NavLink
                                to="stage"
                                className={"flex space-x-2 items-center border-blue dark:border-orange h-14 px-5 justify-center"
                                    + (location.pathname === `/${userRole}/home/stage` || location.pathname === `/${userRole}/home/stage/` ? " border-b-2" : "")
                                }
                                state={user}
                            >
                                <FontAwesomeIcon icon={faPersonDigging} className="dark:text-white" size="sm"/>
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">{t("StudentInternshipPage.stage.text")}</p>
                                </div>
                            </NavLink>
                            <NavLink
                                to="internshipagreement"
                                className={"flex space-x-2 items-center border-blue dark:border-orange h-14 px-5 justify-center"
                                    + (location.pathname === `/${userRole}/home/internshipagreement` || location.pathname === `/${userRole}/home/internshipagreement/` ? " border-b-2" : "")
                                }
                                state={user}
                            >
                                <FontAwesomeIcon icon={faPersonDigging} className="dark:text-white" size="sm"/>
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">{t("StudentInternshipPage.contract.text")}</p>
                                </div>
                            </NavLink>
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

export function useProps() {
    return useOutletContext<Props>();
}

export default StudentInternshipPage;
