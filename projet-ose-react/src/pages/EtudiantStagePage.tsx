import React, {useEffect, useState} from "react";
import EtudiantStage from "../components/common/EtudiantStage";
import SidebarOptionSwitcher from "./SidebarOptionSwitcher";
import {useLocation} from "react-router-dom";
import {AppliedOffers} from "../model/AppliedOffers";
import {getStudentAppliedOffers} from "../api/InterOfferJobAPI";
import axios from "axios";

function EtudiantStagePage() {
    const [darkMode, setDarkMode] = useState(false);
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

        fetchData();
        fetchOffers();
    }, []);

    return (
            <div className="items-center">
                <div className="w-full">
                    <SidebarOptionSwitcher
                        user={user}
                        appliedOffers={listStudentAppliedOffers}
                        offers={offers}
                    />
                </div>
            </div>
    );
}
export default EtudiantStagePage;