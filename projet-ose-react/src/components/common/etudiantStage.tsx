import React, {useEffect, useState} from "react";
import img from "../../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png";
import imgDark from "../../assets/images/Cegep-Andre-Laurendeau.png";
import {useTranslation} from "react-i18next";
import Header from "../../Header";
import {useLocation} from "react-router-dom";
import axios from "axios";

function etudiantStage(props: any) {
    // eslint-disable-next-line react-hooks/rules-of-hooks
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.EtudiantStage");
    // eslint-disable-next-line react-hooks/rules-of-hooks
    const location = useLocation();
    const user = location.state;

    // eslint-disable-next-line react-hooks/rules-of-hooks
    const [offers, setOffers] = useState([
    ]);

    const fetchOffers = () => {
        axios.get(`http://localhost:8080/api/interOfferJob/OffersEtudiant`)
            .then(res => {
                setOffers(res.data);
                console.log(res.data);
            })
            .catch(err => {
                console.log(err);
            });
    }
    const applyOffer = (offer: any, student: any) => {
        console.log(offer);
        console.log(student);
        axios.post(`http://localhost:8080/api/intershipCandidates/saveCandidats`, {
            etudiant: student,
            internOfferJob: offer,
            files: null
        }).then(
            res => {
                console.log(res.data);
            }
        ).catch(
            err => {
                console.log(err);
            }
        )
    }
    // eslint-disable-next-line react-hooks/rules-of-hooks
    useEffect(() => {
        fetchOffers();
    }, []);

    return (
        <div>
            <Header user={user}/>
            <div className={"flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8"}>
                <div className="sm:mx-auto sm:w-full sm:max-w-sm">
                    <img
                        className={props.darkMode ? "mx-auto h-16 w-auto" : "mx-auto h-16 w-auto"}
                        src={props.darkMode ? imgDark : img}
                        alt="Cegep Andre Laurendeau"
                    />
                    <h1 className=
                            {props.darkMode ?
                                "mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-white"
                                : "mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-black"}>
                        {fields.titre.text}
                    </h1>
                    {offers.map((offer: any) => (
                        <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
                            <div className="bg-white py-8 px-4 shadow sm:rounded-lg sm:px-10">
                                <div>
                                    <h2 className="mt-6 text-center text-3xl font-extrabold leading-9 text-gray-900">
                                        {offer.title}
                                    </h2>
                                </div>

                                <div className="mt-6">
                                    <div className="w-full">
                                        <div className="flex justify-between">
                                            <div className="text-sm leading-5 text-gray-500">
                                                <p className={"text-gray-900"}>{fields.stage.description.text}</p>
                                                <p className={"text-gray-900"}>{fields.stage.location.text}</p>
                                                <p className={"text-gray-900"}>{fields.stage.salary.text}</p>
                                                <p className={"text-gray-900"}>{fields.stage.startDate.text}</p>
                                                <p className={"text-gray-900"}>{fields.stage.endDate.text}</p>
                                            </div>
                                            <div className="text-sm leading-5 text-gray-500">
                                                <p className={"text-gray-900"}>{offer.description}</p>
                                                <p className={"text-gray-900"}>{offer.location}</p>
                                                <p className={"text-gray-900"}>{offer.salaryByHour}</p>
                                                <p className={"text-gray-900"}>{offer.startDate}</p>
                                                <p className={"text-gray-900"}>{offer.endDate}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="mt-6">
                                    <div className="w-full">
                                        <div className="flex justify-between">
                                            <div className="text-sm leading-5 text-gray-500">
                                                <button
                                                    onClick={() => applyOffer(offer, user)}
                                                    type="submit"
                                                    className="w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-green-600 hover:bg-green-500 focus:outline-none focus:border-green-700 focus:shadow-outline-green active:bg-green-700 transition duration-150 ease-in-out"
                                                >
                                                    {fields.stage.apply.text}
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>

    )
}

export default etudiantStage;