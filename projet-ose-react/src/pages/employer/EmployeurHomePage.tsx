import React, {useEffect, useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faFileLines, faPencil, faSignature, faUsers} from "@fortawesome/free-solid-svg-icons";
import {NavLink, Outlet, useLocation, useOutletContext} from "react-router-dom";
import {UpdateOffers} from "../../api/InterOfferJobAPI";
import {useTranslation} from "react-i18next";
import Header from "../../components/common/shared/header/Header";
import {getUser} from "../../api/UtilisateurAPI";
import {useAuth} from "../../authentication/AuthContext";
import {User} from "../../model/User";
import {data} from "autoprefixer";
import {useToast} from "../../hooks/state/useToast";

interface Props {
    isModalOpen: boolean,
    setIsModalOpen: React.Dispatch<React.SetStateAction<boolean>>,
    offers: any[],
    setOffers: React.Dispatch<React.SetStateAction<any[]>>,
    user: any,
    setSortField: React.Dispatch<React.SetStateAction<string>>,
    setSortDirection: React.Dispatch<React.SetStateAction<string>>,
    sortField: string,
    sortDirection: string,
    totalPages: number,
    setCurrentPage: React.Dispatch<React.SetStateAction<number>>,
    handleChangeNumberElement: (event: React.ChangeEvent<HTMLSelectElement>) => void,
    onPageChange: (newPage: number) => void,
    numberElementByPage: number,
    page: number,
}

function EmployeurHomePage() {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.homeEmployeur");
    const [offers, setOffers] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [numberElementByPage, setNumberElementByPage] = useState<number>(5)
    const [sortField, setSortField] = useState("id");
    const [sortDirection, setSortDirection] = useState("asc");
    const [totalPages, setTotalPages] = useState(0);
    const [isModalOpen, setIsModalOpen] = useState(true)
    const [nbCandidature, setNbCandidature] = useState(0)
    const { userEmail, userRole, logoutUser } = useAuth();
    const location = useLocation();
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
    const toast = useToast();

    useEffect(() => {
        const getUtilisateur = async () => {
            let  data = null;
            if (userEmail != null){
                console.log(userEmail)
                data = await getUser(userEmail)
                setUser(data)
            }
        }
        getUtilisateur().then(r => console.log(r))
    }, [localStorage.getItem('token')])

    useEffect(() => {
        console.log(user)
        if (userEmail)
            try {
                UpdateOffers(userEmail,setOffers, setTotalPages,{
                    page: currentPage,
                    size: numberElementByPage,
                    sortField,
                    sortDirection
                })
            } catch (error) {
                console.log(error);
                toast.error(fields.toast.errorFetchOffers)
            }

    }, [currentPage, numberElementByPage, sortField, sortDirection]);

    useEffect(() => {
        let i = 0;
        offers.map((offer:any) => {
            i += offer.internshipCandidates.length;
        })
        setNbCandidature(i);
    }, [offers]);

    const handleChangePage = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setCurrentPage(0);
        setNumberElementByPage(Number(event.target.value));
    };

    const handlePageChange = (newPage: number) => {
        setCurrentPage(newPage);
    };

    const context =  {
        isModalOpen: isModalOpen,
        setIsModalOpen: setIsModalOpen,
        offers: offers,
        setOffers: setOffers,
        user: user,
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
    }

    return (
        <div className="min-h-screen h-full">
            <header className="max-md:hidden pt-24 ">
                <div className="max-w-7xl mx-auto  px-6  lg:px-8">
                    <h1 className="text-3xl dark:text-white font-bold text-gray-900"> {fields.titre.text} </h1>
                </div>
            </header>
            <main>
                <div className="max-w-7xl mx-auto xxxs:px-6 lg:px-8">
                    <div className="w-full border-b border-gray dark:border-darkgray mt-6 mb-10 hidden md:block overflow-x-auto">
                        <div className="flex-row flex md:justify-start">
                            <NavLink to="offers"
                                     className={"flex space-x-2 justify-center border-blue dark:border-orange px-5 items-center h-14" +
                                         (location.pathname ===  `/${userRole}/home/offers` || location.pathname === `/${userRole}/home/offers/` ? " border-b-2" : "")
                                     }
                                     state={user}
                            >
                                <FontAwesomeIcon icon={faFileLines} className="dark:text-white" size="sm" />
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">{fields.offre.text}</p>
                                </div>
                            </NavLink>

                            <NavLink
                                to="newOffer"
                                className={"flex space-x-2 items-center border-blue dark:border-orange h-14 px-5 justify-center"
                                    + (location.pathname === `/${userRole}/home/newOffer` || location.pathname === `/${userRole}/home/newOffer/` ? " border-b-2" : "")
                                }
                                state={user}
                            >
                                <FontAwesomeIcon icon={faPencil} className="dark:text-white" size="sm" />
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">{fields.newOffre.text}</p>
                                </div>
                            </NavLink>

                            <div className="flex space-x-2 items-center h-14 px-5 justify-center">
                                <FontAwesomeIcon icon={faSignature} className="dark:text-white" size="sm" />
                                <div className="pl-2">
                                    <p className="text-black dark:text-white">{fields.contract.text}</p>
                                </div>
                            </div>
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


export function useProps(){
    return useOutletContext<Props>();
}
export default EmployeurHomePage;