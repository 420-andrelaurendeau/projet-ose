import React from "react";
import {useLocation, useParams} from "react-router-dom";
import InternshipOfferForm from "../components/common/InternshipOfferForm";
import useModal from "../hooks/useModal";

function SidebarOptionSwitcher() {
    let { option } = useParams()
    const {isModalOpen, handleOpenModal, handleCloseModal} = useModal();

    return (
        <div className="bg-darkwhite md:hidden w-full">
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
                                        isModalOpen={true}
                                        handleCloseModal={handleCloseModal}
                                        handleOpenModal={handleOpenModal}
                                        isModal={false}
                                    />
                                    :
                                    <p>Home</p>
            }
        </div>
    );
}

export default SidebarOptionSwitcher;