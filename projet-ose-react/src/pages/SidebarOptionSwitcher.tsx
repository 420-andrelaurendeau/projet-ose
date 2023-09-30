import React from "react";
import {useLocation, useOutletContext, useParams} from "react-router-dom";
import InternshipOfferForm from "../components/common/InternshipOfferForm";
import useModal from "../hooks/useModal";
import {list} from "postcss";

function SidebarOptionSwitcher() {
    let { option } = useParams()
    const props:any = useOutletContext()

    return (
        <div className="bg-darkwhite w-full">
            {
                option === "offer" ?
                    <p className="text-black">Offer</p>
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
                                        isModalOpen={props[0]}
                                        setIsModalOpen={props[1]}
                                    />
                                    :
                                    <p>Home</p>
            }
        </div>
    );
}

export default SidebarOptionSwitcher;