import React, {Dispatch, SetStateAction} from "react";
import {useOutletContext, useParams} from "react-router-dom";
import InternshipOfferForm from "../components/common/InternshipOfferForm";
import EmployeurOffer from "../components/common/EmployeurOffer";
import EtudiantStage from "../components/common/EtudiantStage";

export interface Props{
    isModalOpen: boolean;
    setIsModalOpen: Dispatch<SetStateAction<boolean>>;
    offers: never[];
    setOffers: Dispatch<SetStateAction<never[]>>;
    userEmail: any; 
    user: any;
}

function StudentsSidebarOptionSwitcher(props:Props) {
    let { option } = useParams()
    return (

        <div className="bg-darkwhite dark:bg-softdark w-full">
            <div className={option != "offer" ? "max-md:hidden":"hidden"}>
                <EtudiantStage/>
            </div>

            {
                option === "offer" ?
                    <EtudiantStage/>
                    :
                    <p>Home</p>
            }
        </div>
    );
}

export default StudentsSidebarOptionSwitcher;