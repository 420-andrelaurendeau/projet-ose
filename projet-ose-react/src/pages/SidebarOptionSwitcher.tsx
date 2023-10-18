import React, {Dispatch, SetStateAction} from "react";
import {useOutletContext, useParams} from "react-router-dom";
import InternshipOfferForm from "../components/common/InternshipOfferForm";
import EmployeurOffer from "../components/common/EmployeurOffer";
import EtudiantStage from "../components/common/EtudiantStage";
import StudentAppliedOffers from "../components/common/StudentAppliedOffers";
import CandidatureOffer from "../components/common/CandidatureOffer";


function SidebarOptionSwitcher(props: any) {
    let {option} = useParams()
    console.log(props.user.matricule ? "Etudiant" : "Employeur")
    return (

        <div className="bg-darkwhite dark:bg-softdark w-full">

            {
                props.user.matricule ?
                    <div className={option != "offer" ? "max-md:hidden" : "hidden"}>
                        <EtudiantStage
                            appliedOffers={props.appliedOffers}
                            setAppliedOffers={props.setAppliedOffers}
                            offers={props.offers}
                        />
                    </div>
                    :
                    <div className={option != "offer" ? "max-md:hidden" : "hidden"}>
                        <EmployeurOffer
                            offers={props.offers}
                        />
                    </div>
            }

            {
                props.user.matricule ?
                    (
                        option === "offer" ?
                            <EtudiantStage
                                appliedOffers={props.appliedOffers}
                                setAppliedOffers={props.setAppliedOffers}
                                offers={props.offers}
                            />
                            :
                            option === "appliedOffers" ?
                                <StudentAppliedOffers
                                    appliedOffers={props.appliedOffers}
                                    user={props.user}
                                />
                                :
                                <p>Home</p>
                    )
                    :
                    (
                        option === "offer" ?
                            <EmployeurOffer
                                offers={props.offers}
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
                                                isModalOpen={props.isModalOpen}
                                                setIsModalOpen={props.setIsModalOpen}
                                                setOffers={props.setOffers}
                                                userId={props.userEmail}
                                                user={props.user}
                                            />
                                            :
                                            <p>Home</p>
                    )

            }
        </div>
    );
}

export default SidebarOptionSwitcher;