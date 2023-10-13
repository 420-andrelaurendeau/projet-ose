import React from "react";
import {useParams} from "react-router-dom";
import InternshipOfferForm from "../components/common/InternshipOfferForm";
import EmployeurOffer from "../components/common/EmployeurOffer";
import EtudiantStage from "../components/common/EtudiantStage";
import StudentAppliedOffers from "../components/common/StudentAppliedOffers";
import GSOffers from "../components/common/GSOffers";
import GSOffersPage from "./GSOffersPage";


function SidebarOptionSwitcher(props: any) {
    let {option} = useParams()
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
                    :
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
                                    <p>Candidature</p>
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