import React, { useState } from "react";
import axios from "axios";
import {useTranslation} from "react-i18next";
// @ts-ignore
import img from '../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png';
// @ts-ignore
import imgDark from '../assets/images/Cegep-Andre-Laurendeau.png';

function EtudiantInscription(props: any) {
    const {i18n} = useTranslation();
    //const fields = i18n.getResource(i18n.language.slice(0,2), "translation", "formFields.ConnectForm");
    const [formData, setFormData] = useState({
        nom: "",
        prenom: "",
        courriel: "",
        motDePasse: "",
        telephonne: "",
    });

    const handleChange = (event:any) => {
        const { name, value } = event.target;
        console.log(name + " " + value);
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = (event:any) => {
        event.preventDefault();
        const { motDePasse, nom, prenom, courriel, telephonne } = formData;
        axios
            .post("http://localhost:8080/api/etudiant/ajouter", {
                nom: nom,
                prenom: prenom,
                courriel: courriel,
                motDePasse: motDePasse,
                telephonne: telephonne,
            })
            .then((response) => {
                console.log(response);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <div className={"flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8"}>
            <div className="sm:mx-auto sm:w-full sm:max-w-sm">
                <img
                    className={props.darkMode ? "mx-auto h-16 w-auto" : "mx-auto h-16 w-auto"}
                    src={props.darkMode ? imgDark : img}
                    alt="Cegep Andre Laurendeau"
                />
                <h2 className=
                        {props.darkMode ?
                            "mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-white"
                            : "mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-black"}>
                    Inscription de l'etudiant
                </h2>
            </div>
            <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                <form className={"space-y-6"} onSubmit={handleSubmit}>
                    <div>
                        <label className={props.darkMode ?
                            "block text-sm font-medium leading-6 text-white"
                            : "block text-sm font-medium leading-6 text-black"}>Nom:
                        </label>
                        <div className="mt-2">
                            <input
                                required={true}
                                placeholder={"Jean"}
                                className={props.darkMode ?
                                    "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                    : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                                }
                                type="text"
                                name={"nom"}
                                value={formData.nom}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                    <div>
                        <label className={"block text-gray-700 text-sm font-bold mb-2"}>Prenom:</label>
                        <div className="mt-2">
                            <input
                                required={true}
                                placeholder={"Pierre"}
                                className={props.darkMode ?
                                    "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                    : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                                }
                                type="text"
                                name={"prenom"}
                                value={formData.prenom}
                                onChange={handleChange}
                            />
                        </div>
                   </div>
                    <div>
                        <label className={"block text-gray-700 text-sm font-bold mb-2"}>Courriel:</label>
                        <div className="mt-2">
                            <input
                                required={true}
                                placeholder={"email@email.com"}
                                title={"Exemple: email@email.com"}
                                className={props.darkMode ?
                                    "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                    : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                                }
                                type="email"
                                name={"courriel"}
                                value={formData.courriel}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                    <div>
                        <label className={"block text-gray-700 text-sm font-bold mb-2"}>Mot de passe:</label>
                        <div className="mt-2">
                            <input
                                required={true}
                                placeholder={"abc123"}
                                pattern={"^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"}
                                minLength={8}
                                title={"8 caractères minimum, au moins une lettre et un chiffre"}
                                className={props.darkMode ?
                                    "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                    : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                                }
                                type="text"
                                name={"motDePasse"}
                                value={formData.motDePasse}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                    <div>
                        <label className={"block text-gray-700 text-sm font-bold mb-2"}>Telephonne:</label>
                        <div className="mt-2">
                            <input
                                required={true}
                                pattern={"[0-9]{3}-[0-9]{3}-[0-9]{4}"}
                                title={"Exemple: 450-450-4500"}
                                placeholder={"450-450-4500"}
                                className={props.darkMode ?
                                    "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                    : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                                }
                                type="text"
                                name={"telephonne"}
                                value={formData.telephonne}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                    <div>
                        <button
                            type="submit"
                            className=
                                {props.darkMode ?
                                    "flex w-full justify-center rounded-md bg-orange px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-orange-100 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-orange"
                                    :"flex w-full justify-center rounded-md bg-blue px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-blue"
                                }>
                            Sign in
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default EtudiantInscription;