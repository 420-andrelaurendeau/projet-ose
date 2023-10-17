import React, { useEffect } from "react";
import {InterOfferJob} from "../../model/IntershipOffer";
import useModal from "../../hooks/useModal";
import InternshipOfferModal from "./InternshipOfferModal";
import Switcher from "../../utils/switcher";
import {getAllPendingInterOfferJob} from "../../api/InterOfferJobAPI";


function GSInternOfferList() {

    const [listInternOffer, setListInternOffer] = React.useState<InterOfferJob[]>([]);
    const {isModalOpen, handleOpenModal, handleCloseModal} = useModal();
    const [idInternOffer, setIdInternOffer] = React.useState<Number>(0);
    const [forceUpdate, setForceUpdate] = React.useState(false);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const data = await getAllPendingInterOfferJob();
                console.log(data.map((item: InterOfferJob) => console.log(item.id)));
                setListInternOffer(data);
            } catch (error) {
                console.error("Erreur lors de la récupération des offres:", error);
            }
        };

        fetchData();
    }, []);

    const handleClick = (id: Number) => {
        setIdInternOffer(id);

        console.log()
        handleOpenModal();

    }

    const updateInternshipOffer = (updatedOffer: InterOfferJob) => {
        console.log("dans la fonction update")
        setListInternOffer(prevList => prevList.map(offer =>
            offer.id === updatedOffer.id ? updatedOffer : offer
        ));
        setForceUpdate(!forceUpdate);
    };


    return (
        <div className="items-center">
            {
                listInternOffer.map((item: InterOfferJob) => {
                    return (
                        <>
                        <div className="flex items-start border border-red" onClick={() => handleClick(item.id!)}>
                            <div
                                className="flex flex-col border border-orange w-64 md:w-128 lg:w-192 h-48 justify-between pl-2">
                                <div className="flex mb-2 items-center">
                                    <p className="font-bold dark:text-offwhite"> {item.title} </p>
                                    <p className="font-bold dark:text-offwhite">-</p>
                                    <p className="font-bold dark:text-offwhite"> {item.location} </p>
                                </div>
                                <div className="mb-2">
                                    <p className="font-bold dark:text-offwhite"> {item.programmeNom} </p>
                                </div>
                            </div>
                            <div className="flex-1 pl-2">
                                <p className="font-bold text-black dark:text-offwhite"> {item.state} </p>
                            </div>
                        </div>
                        </>
                    )
                })
            }
            <InternshipOfferModal
                isModalOpen={isModalOpen}
                handleCloseModal={handleCloseModal}
                internshipOffer={listInternOffer.find((item: InterOfferJob) => item.id === idInternOffer)}
                onUpdateInternshipOffer={updateInternshipOffer}
            />

        </div>
    )
}

export default GSInternOfferList;