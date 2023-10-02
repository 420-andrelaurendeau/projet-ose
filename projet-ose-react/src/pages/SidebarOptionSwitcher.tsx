import React from "react";
import {useOutletContext, useParams} from "react-router-dom";
import InternshipOfferForm from "../components/common/InternshipOfferForm";
import EmployeurOffer from "../components/common/EmployeurOffer";

function SidebarOptionSwitcher() {
    let { option } = useParams()
    const props:any = useOutletContext()

    return (

        <div className="bg-darkwhite w-full">
            <div className={option != "offer" ? "max-md:hidden":"hidden"}>
                <EmployeurOffer
                    offers={props.offers}
                />
            </div>

            {
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
                                        userId={props.userId}
                                    />
                                    :
                                    <p>Home</p>
            }
        </div>
    );
}

export default SidebarOptionSwitcher;