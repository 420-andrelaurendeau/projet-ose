import InternshipOfferForm from "../components/common/InternshipOfferForm";
import useModal from "../hooks/useModal";
import Switcher from "../utils/switcher";
import React from "react";

function InternOfferPage() {

    const {isModalOpen, handleOpenModal, handleCloseModal} = useModal();

    return (
        <html lang="en">
        <head>
            <title>Offre d'emploie</title>
        </head>
        <body>
        <div className="items-center">
            <Switcher/>

            <button onClick={handleOpenModal} className="w-full bg-bleu text-white font-bold p-2 rounded-md">
                Open Form
            </button>


            <InternshipOfferForm
                isModalOpen={isModalOpen}
                handleCloseModal={handleCloseModal}
                handleOpenModal={handleOpenModal}
            />
        </div>
        </body>
        </html>
    )
}

export default InternOfferPage;