import React, { useState } from "react";
import axios from "axios";

function EtudiantInscription() {
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
        <div className={"flex flex-col items-center justify-center h-screen bg-white"}>
            <div className={"mb-10"}>
                <h1 className={"scale-150 text-orange"}>Inscription de l'etudiant</h1>
            </div>
            <form className={"bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4"} onSubmit={handleSubmit}>
                <div className={"mb-4"}>
                    <label className={"block text-gray-700 text-sm font-bold mb-2"}>Nom:</label>
                    <input
                        required={true}
                        placeholder={"Jean"}
                        className={"shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"}
                        type="text"
                        name={"nom"}
                        value={formData.nom}
                        onChange={handleChange}
                    />
                </div>
                <div className={"mb-4"}>
                    <label className={"block text-gray-700 text-sm font-bold mb-2"}>Prenom:</label>
                    <input
                        required={true}
                        placeholder={"Pierre"}
                        className={"shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"}
                        type="text"
                        name={"prenom"}
                        value={formData.prenom}
                        onChange={handleChange}
                    />
                </div>
                <div className={"mb-4"}>
                    <label className={"block text-gray-700 text-sm font-bold mb-2"}>Courriel:</label>
                    <input
                        required={true}
                        placeholder={"email@email.com"}
                        title={"Exemple: email@email.com"}
                        className={"shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"}
                        type="email"
                        name={"courriel"}
                        value={formData.courriel}
                        onChange={handleChange}
                    />
                </div>
                <div className={"mb-4"}>
                    <label className={"block text-gray-700 text-sm font-bold mb-2"}>Mot de passe:</label>
                    <input
                        required={true}
                        placeholder={"abc123"}
                        pattern={"^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"}
                        minLength={8}
                        title={"8 caractères minimum, au moins une lettre et un chiffre"}
                        className={"shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"}
                        type="text"
                        name={"motDePasse"}
                        value={formData.motDePasse}
                        onChange={handleChange}
                    />
                </div>
                <div className={"mb-4"}>
                    <label className={"block text-gray-700 text-sm font-bold mb-2"}>Telephonne:</label>
                    <input
                        required={true}
                        pattern={"[0-9]{3}-[0-9]{3}-[0-9]{4}"}
                        title={"Exemple: 450-450-4500"}
                        placeholder={"450-450-4500"}
                        className={"shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"}
                        type="text"
                        name={"telephonne"}
                        value={formData.telephonne}
                        onChange={handleChange}
                    />
                </div>
                <br></br>
                <button className={"text-orange border hover:bg-orange hover:text-white text-black py-2 px-4 rounded"} type="submit">Inscription</button>
            </form>
        </div>
    );
}

export default EtudiantInscription;