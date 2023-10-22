import React, {Dispatch, SetStateAction} from "react";
import {useOutletContext, useParams} from "react-router-dom";
import InternshipOfferForm from "../components/common/InternshipOfferForm";
import EmployeurOffer from "../components/common/EmployeurOffer";
import EtudiantStage from "../components/common/EtudiantStage";
import StudentAppliedOffers from "../components/common/StudentAppliedOffers";
import GSOffers from "../components/common/internshipManager/Offers/GSOffers";
import GSOffersPage from "./internshipManager/Offers/GSOffersPage";
import CandidatureOffer from "../components/common/CandidatureOffer";



function SidebarOptionSwitcher(props:any) {
    let { option } = useParams()
    return (

        <div className="bg-darkwhite dark:bg-softdark w-full">

            {
                props.user.id == 5 ?
                    <div className={option != "offer" ? "max-md:hidden" : "hidden"}>

                        <GSOffersPage/>
                    </div>
                    : props.user.matricule ?
                    <div className={option != "offer" ? "max-md:hidden" : "hidden"}>
                        <EtudiantStage
                        />
                    </div>
                    :
                    <div className={option != "offer" ? "max-md:hidden" : "hidden"}>
                        <EmployeurOffer
                        />
                    </div>
            }

            {
                props.user.id == 5 ?
                    (
                        option === "offer" ?
                            <>
                                {console.log("test")}
                                <GSOffersPage/>
                            </>
                            :
                            <></>
                    )
                    : props.user.matricule ?
                    (
                        option === "offer" ?
                        <EtudiantStage
                        />
                        :
                        option === "appliedOffers" ?
                            <StudentAppliedOffers

                            />
                            :
                            <p>Home</p>
                    )
                     :
                     (
                         option === "offer" ?
                             <EmployeurOffer
                             />
                             :
                             option === "candidature" ?
                                 <CandidatureOffer
                                    user={props.user}
                                    offers={props.offers}
                                 />
                                 :
                                 option === "contract" ?
                                     <p>Contract</p>
                                     :
                                     option === "pendingOffer" ?
                                         <p>Pending offer</p>
                                         :
                                         option === "newOffer" ?
                                             <InternshipOfferForm
                                             />
                                             :
                                             <p>Home</p>
                     )

            }
        </div>
    );
}

export default SidebarOptionSwitcher;