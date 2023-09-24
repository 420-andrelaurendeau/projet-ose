import React from "react";
import {InterOfferJob} from "../../model/IntershipOffer";
import useModal from "../../hooks/useModal";
import InternshipOfferModal from "./InternshipOfferModal";
import Switcher from "../../utils/switcher";

function GSInternOfferList() {


    const listInternOffer: InterOfferJob[] = [
        {
            id: 1,
            title: "Développeur Frontend",
            location: "Paris",
            description: "Concevoir des interfaces utilisateur intuitives et esthétiques. hoijiojvb iusd  i qiuhoiyu y ytf giu h iuh uh iu hiuh iuh ihiuh ihiuhihi hiuhihih iuhih ih hihihih ihihihi hihi hihihiuuig gyuguyguyg ytfyfytf yfytfytfy tfytfyt fyfyfy fytfytfyiuhoij hiuhih hihi hihih ihihi hih",
            salaryByHour: 25,
            startDate: new Date('2023-10-01'),
            endDate: new Date('2024-10-01'),
            internshipCandidates: [],
            programmeId: 1,
            programmeNom: "Génie logiciel",
            employeurId: 1,
            employeurNom: "Jean",
            employeurPrenom: "Dupont",
            employeurEntreprise: "Google",
            file: {
                id: 1,
                fileName: "offre1.pdf",
                content: "Contenu du fichier pour l'offre 1",
                isAccepted: true
            }
        },
        {
            id: 2,
            title: "Designer UI/UX",
            location: "Lyon",
            description: "Concevoir des interfaces utilisateur intuitives et esthétiques. hoijiojvb iusd  i qiuhoiyu y ytf giu h iuh uh iu hiuh iuh ihiuh ihiuhihi hiuhihih iuhih ih hihihih ihihihi hihi hihihiuuig gyuguyguyg ytfyfytf yfytfytfy tfytfyt fyfyfy fytfytfyiuhoij hiuhih hihi hihih ihihi hih",
            salaryByHour: 30,
            startDate: new Date('2023-11-15'),
            endDate: new Date('2024-11-15'),
            programmeId: 1,
            programmeNom: "Génie logiciel",
            employeurId: 1,
            employeurNom: "Jean",
            employeurPrenom: "Dupont",
            employeurEntreprise: "Google",
            file: {
                id: 2,
                fileName: "offre2.pdf",
                content: "Contenu du fichier pour l'offre 2",
                isAccepted: false
            }
        },
    ];

    const {isModalOpen, handleOpenModal, handleCloseModal} = useModal();
    const [idInternOffer, setIdInternOffer] = React.useState<Number>(0);
    const handleClick = (id: Number) => {
        setIdInternOffer(id);

        console.log()
        handleOpenModal();

    }

    return (
        <div className="items-center">
            {
                listInternOffer.map((item: InterOfferJob) => {
                    return (
                        <>
                            <Switcher/>
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
                                <div className="flex">
                                    <p className="font-bold dark:text-offwhite"> {item.startDate!.toLocaleDateString()} </p>
                                    <p className="font-bold dark:text-offwhite mx-2">-</p>
                                    <p className="font-bold dark:text-offwhite"> {item.endDate!.toLocaleDateString()} </p>
                                </div>
                            </div>
                            <div className="flex-1 pl-2">
                                <p className="font-bold dark:text-offwhite"> {item.description} </p>
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
            />
        </div>
    )
}

export default GSInternOfferList;