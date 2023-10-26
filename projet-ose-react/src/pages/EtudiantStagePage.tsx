import React, {useEffect, useState} from "react";
import EtudiantStage from "../components/common/EtudiantStage";
import SidebarOptionSwitcher from "./SidebarOptionSwitcher";
import {NavLink, Outlet, useLocation, useOutletContext} from "react-router-dom";
import {AppliedOffers} from "../model/AppliedOffers";
import {getStudentAppliedOffers} from "../api/InterOfferJobAPI";
import axios from "axios";
import Header from "../components/common/shared/header/Header";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faFileLines, faPencil, faSignature, faUsers} from "@fortawesome/free-solid-svg-icons";
import {useTranslation} from "react-i18next";


interface Props {
    user: any,
    appliedOffers: AppliedOffers[],
    setAppliedOffers: React.Dispatch<React.SetStateAction<AppliedOffers[]>>,
    offers: never[]
}

function EtudiantStagePage() {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField");
    const location = useLocation();
    const user = location.state;
    const [listStudentAppliedOffers, setListStudentAppliedOffers] = React.useState<AppliedOffers[]>([]);
    const [offers, setOffers] = useState([
    ]);
    const fetchOffers = () => {
        axios.get(`http://localhost:8080/api/interOfferJob/OffersEtudiant`)
            .then(res => {
                setOffers(res.data);
                console.log(res.data);
            })
            .catch(err => {
                console.log(err);
            });
    }
    useEffect(() => {
        const fetchData = async () => {
            try {
                const data:AppliedOffers[] = await getStudentAppliedOffers(user.id);
                console.log(data);
                setListStudentAppliedOffers(data);
            } catch (error) {
                console.error("Erreur lors de la récupération des offres:", error);
            }
        };

        fetchData().then(r => console.log("ok"));
        fetchOffers();
    }, []);


    const context =  {
        user:user,
        appliedOffers:listStudentAppliedOffers,
        setAppliedOffers:setListStudentAppliedOffers,
        offers:offers
    }

    return (
        <div className="items-center">
            <div className="min-h-screen h-full bg-darkwhite dark:bg-softdark">
                <Header/>
                <div className="pt-24 flex-row flex md:justify-center space-x-4">
                    <NavLink
                        to="offers"
                        className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-white hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                        state={user}
                    >
                        <div className="flex space-x-2 items-center h-16 w-auto">
                            <div className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                <FontAwesomeIcon icon={faFileLines} className="group-hover:text-white dark:text-white" size="lg"/>
                            </div>
                            <div className="pl-2">
                                <p className="text-blue dark:text-orange">{fields.Header.sidebar.stage.text}</p>
                            </div>
                        </div>
                    </NavLink>
                    <NavLink
                        to="offreApplique"
                        className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-white hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                        state={user}
                    >
                        <div className="flex space-x-2 items-center h-16 w-auto">
                            <div className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                <FontAwesomeIcon icon={faFileLines} className="group-hover:text-white dark:text-white" size="lg"/>
                            </div>
                            <div className="pl-2">
                                <p className="text-blue dark:text-orange">{fields.Header.sidebar.offre_applique.text}</p>
                            </div>
                        </div>
                    </NavLink>
                    <NavLink
                        to="TeleverserCV"
                        className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-white hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                        state={user}
                    >
                        <div className="flex space-x-2 items-center h-16 w-auto">
                            <div className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                <FontAwesomeIcon icon={faFileLines} className="group-hover:text-white dark:text-white" size="lg"/>
                            </div>
                            <div className="pl-2">
                                <p className="text-blue dark:text-orange">{fields.Header.cv.text}</p>
                            </div>
                        </div>
                    </NavLink>

                </div>
                <Outlet
                    context={context}
                />
            </div>
        </div>
    );
}

export function useProps(){
    return useOutletContext<Props>();
}
export default EtudiantStagePage;