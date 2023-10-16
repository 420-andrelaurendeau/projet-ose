import React, {useEffect, useState} from "react";
import img from "../../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png";
import imgDark from "../../assets/images/Cegep-Andre-Laurendeau.png";
import {useTranslation} from "react-i18next";
import Header from "../../Header";
import {useLocation} from "react-router-dom";
import axios from "axios";
import {faBriefcase} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

function EtudiantStage(props: any) {
    // eslint-disable-next-line react-hooks/rules-of-hooks
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.EtudiantStage");
    // eslint-disable-next-line react-hooks/rules-of-hooks
    const location = useLocation();
    const user = location.state;
    let anError = false;
    // eslint-disable-next-line react-hooks/rules-of-hooks

    const applyOffer = (offer: any, student: any) => {
        console.log(offer);
        console.log(student);
        axios.post(`http://localhost:8080/api/internshipApplications/newApplication`, {
            etudiant: student,
            internOfferJob: offer,
            files: null
        }).then(
            res => {
                console.log(res.data);
                props.setAppliedOffers([...props.appliedOffers, res.data]);
            }
        ).catch(
            err => {
                console.log(err);
                anError = true;
            }
        )
        if (anError) {
            alert("Une erreur est survenue lors de l'application à l'offre de stage");
        }
        else {
            alert("Vous avez appliqué à l'offre de stage : " + offer.id + " qui est : " + offer.title);
        }
    }

    return (
        <div>
            <div className="flex min-h-full flex-1 flex-col justify-center px-6 lg:px-8 ">
                <div className="sm:mx-auto sm:w-full sm:max-w-sm mt-28">
                    <div className="flex items-center justify-center">
                        <FontAwesomeIcon icon={faBriefcase} className="text-blue dark:text-orange h-16" />
                    </div>
                    <h1 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-black dark:text-white">
                        {fields.titre.text}
                    </h1>
                    {props.offers.map((offer: any) => (
                        <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md" key={offer.id}>
                            <div className="bg-white dark:bg-dark py-8 px-4 shadow border border-gray dark:border-darkgray sm:rounded-lg sm:px-10">
                                <div>
                                    <h2 className="mt-6 text-center text-3xl font-extrabold leading-9 dark:text-white">
                                        {offer.title}
                                    </h2>
                                </div>

                                <div className="mt-6">
                                    <div className="w-full">
                                        <div className="flex justify-between">
                                            <div className="text-sm leading-5 dark:text-white">
                                                <p>{fields.stage.description.text}</p>
                                                <p>{fields.stage.location.text}</p>
                                                <p>{fields.stage.salary.text}</p>
                                                <p>{fields.stage.startDate.text}</p>
                                                <p>{fields.stage.endDate.text}</p>
                                            </div>
                                            <div className="text-sm leading-5 dark:text-white">
                                                <p>{offer.description}</p>
                                                <p>{offer.location}</p>
                                                <p>{offer.salaryByHour}</p>
                                                <p>{offer.startDate}</p>
                                                <p>{offer.endDate}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="mt-6">
                                    <div className="w-full">
                                        <div className="flex justify-between">
                                            <div className="text-sm leading-5">
                                                <button
                                                    onClick={() => applyOffer(offer, user)}
                                                    type="submit"
                                                    disabled={props.appliedOffers.some((appliedOffer: any) => appliedOffer.appliedOffer.id === offer.id)}
                                                    className="w-full flex justify-center py-2 px-4 border border-gray dark:border-darkgray text-sm font-medium rounded-md text-white bg-blue dark:bg-orange hover:bg-gray focus:outline-none focus:shadow-outline-blue active:bg-blue transition duration-150 ease-in-out"
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

export default EtudiantStage;