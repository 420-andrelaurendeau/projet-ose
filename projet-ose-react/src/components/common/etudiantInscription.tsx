import React, { useState } from "react";
import axios from "axios";
import { useTranslation } from "react-i18next";
// @ts-ignore
import img from '../../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png';
// @ts-ignore
import imgDark from '../../assets/images/Cegep-Andre-Laurendeau.png';

function EtudiantInscription(props: any) {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.InscriptionFormEtudiant");

    const [formData, setFormData] = useState({
        nom: "",
        prenom: "",
        email: "",
        password: "",
        phone: "",
        programme: null,
        cv: null,
    });

    const [programmes, setProgrammes] = useState([]);

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
        const { password, nom, prenom, email, phone, cv, programme } = formData;
        console.log(formData)
        console.log(password, nom, prenom, email, phone, cv, programme);
        if (programme == null) {
            alert("Veuillez choisir un programme");
            return;
        }
        axios
            .post("http://localhost:8080/api/etudiant/ajouter", {
                nom: nom,
                prenom: prenom,
                email: email,
                password: password,
                phone: phone,
                programme: programme,
                cv: cv,
            })
            .then((response) => {
                console.log(response);
            })
            .catch((error) => {
                console.log(error);
                alert("Erreur lors de l'inscription")
            });
    };

    const fetchProgrammes = () => {
        axios
            .get("http://localhost:8080/api/programme/programmes")
            .then((response) => {
                console.log(response);
                setProgrammes(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    }

    React.useEffect(() => {
        fetchProgrammes();
    }
    , []);

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
                    {fields.title.text}
                </h2>
            </div>
            <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                <form className={"space-y-6"} onSubmit={handleSubmit} action={"#"}>
                    <div>
                        <label className={props.darkMode ?
                            "block text-sm font-medium leading-6 text-white"
                            : "block text-sm font-medium leading-6 text-black"}> {fields.lastName.text}
                        </label>
                        <div className="mt-2">
                            <input
                                required={true}
                                placeholder={fields.lastName.placeholder}
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
                        <label className={props.darkMode ?
                            "block text-sm font-medium leading-6 text-white"
                            : "block text-sm font-medium leading-6 text-black"}>{fields.firstName.text}</label>
                        <div className="mt-2">
                            <input
                                required={true}
                                placeholder={fields.firstName.placeholder}
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
                        <label className={props.darkMode ?
                            "block text-sm font-medium leading-6 text-white"
                            : "block text-sm font-medium leading-6 text-black"}>{fields.email.text}</label>
                        <div className="mt-2">
                            <input
                                required={true}
                                placeholder={fields.email.placeholder}
                                title={"Exemple: email@email.com"}
                                className={props.darkMode ?
                                    "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                    : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                                }
                                type="email"
                                name={"email"}
                                value={formData.email}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                    <div>
                        <label className={props.darkMode ?
                            "block text-sm font-medium leading-6 text-white"
                            : "block text-sm font-medium leading-6 text-black"}>{fields.password.text}</label>
                        <div className="mt-2">
                            <input
                                required={true}
                                placeholder={fields.password.placeholder}
                                pattern={"^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"}
                                minLength={8}
                                title={"8 caractères minimum, au moins une lettre et un chiffre"}
                                className={props.darkMode ?
                                    "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                    : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                                }
                                type="text"
                                name={"password"}
                                value={formData.password}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                    <div>
                        <label className={props.darkMode ?
                            "block text-sm font-medium leading-6 text-white"
                            : "block text-sm font-medium leading-6 text-black"}>{fields.phone.text}</label>
                        <div className="mt-2">
                            <input
                                required={true}
                                pattern={"[0-9]{3}-[0-9]{3}-[0-9]{4}"}
                                title={"Exemple: 450-450-4500"}
                                placeholder={fields.phone.placeholder}
                                className={props.darkMode ?
                                    "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                    : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                                }
                                type="text"
                                name={"phone"}
                                value={formData.phone}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                    <div>
                        <label className={props.darkMode ?
                            "block text-sm font-medium leading-6 text-white"
                            : "block text-sm font-medium leading-6 text-black"}>{fields.programme.text}</label>
                        <div className="mt-2">
                            <select
                                required={true}
                                className={props.darkMode ?
                                    "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                    : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                                }
                                defaultValue={"DEFAULT"}
                                name={"programme"}
                                onChange={handleChange}
                            >
                                <option value={"DEFAULT"} disabled>{fields.programme.placeholder}</option>
                                {programmes.map((programme) => (
                                    <option key={programme['id']} value={programme['id']}>{programme['nom']}</option>
                                ))}
                            </select>
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
                            {fields.submitButton.text}
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default EtudiantInscription;