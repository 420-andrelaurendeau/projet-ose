import React, {useEffect, useState} from "react";
import axios from "axios";
import { useTranslation } from "react-i18next";
import {saveStudent} from "../../../../api/StudentApi";
// @ts-ignore
import img from '../../../../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png';
// @ts-ignore
import imgDark from '../../../../assets/images/Cegep-Andre-Laurendeau.png';
import {Link, useNavigate} from "react-router-dom";
import {useToast} from "../../../../hooks/state/useToast";

//TODO change i18n is not using fields and instead t

function StudentInscriptionForm(props: any) {
    const toast = useToast();
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.InscriptionFormEtudiant");
    const navigate = useNavigate();


    const [formData, setFormData] = useState({
        nom: "",
        prenom: "",
        email: "",
        password: "",
        phone: "",
        matricule: "",
        programme_id: 0,
        cv: null,
    });


    const [programmes, setProgrammes] = useState([]);
    const [reussite, setReussite] = useState(false);
    const [error, setError] = useState(false);

    const handleChange = (event:any) => {
        const { name, value } = event.target;
        console.log(name + " " + value);
        console.log(event.target.value)
        setFormData({
            ...formData,
            [name]: value,
        });
        console.log(formData)
    };



    const handleSubmit = (event:any) => {
        event.preventDefault();
        const { password, nom, prenom, email, phone, matricule, cv, programme_id } = formData;
        if (programme_id == 0) {
            alert(fields.programme.validation.required);
            return;
        }
        saveStudent({
            nom: nom,
            prenom: prenom,
            email: email,
            password: password,
            phone: phone,
            matricule: matricule,
            programme_id: programme_id,
            cv: cv,
            activeCv: null,
            internshipsCandidate: null
        })
            .then((response) => {
                console.log(response)
                toast.success("Inscription réussie");
                navigate("/")
            })
            .catch((error) => {
                console.log(error)
                toast.error("Erreur lors de l'inscription");
            })
        event.target.reset();
        setFormData({
            nom: "",
            prenom: "",
            email: "",
            password: "",
            phone: "",
            matricule: "",
            programme_id: 0,
            cv: null,
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
    }, []);

    useEffect(() => {
        setFormData(formData);
    },[formData]);


    return (
        <div className={"flex min-h-full flex-1 flex-col justify-center px-6 py-6 lg:px-8 dark:text-white text-black"}>
            <div className="sm:mx-auto sm:w-full sm:max-w-sm">
                <img
                    className={"mx-auto h-16 w-auto dark:hidden"}
                    src={img}
                    alt="Cegep Andre Laurendeau"
                />
                <img
                    className={"mx-auto h-16 w-auto hidden dark:flex"}
                    src={imgDark}
                    alt="Cegep Andre Laurendeau"
                />
                <h2 className=
                        {"mt-10 text-center text-2xl font-bold leading-9 tracking-tight"}>
                    {fields.title.text}
                </h2>
            </div>
            <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                <form className={"space-y-6"} onSubmit={handleSubmit}>
                    <div>
                        <label className={
                            "block text-sm font-medium leading-6"}> {fields.lastName.text}
                        </label>
                        <div className="mt-2">
                            <input
                                required={true}
                                placeholder={fields.lastName.placeholder}
                                className={"block w-full rounded-md py-2 shadow-sm sm:text-sm sm:leading-6 pl-2 dark:text-black"
                                }
                                type="text"
                                name={"nom"}
                                value={formData.nom}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                    <div>
                        <label className={"block text-sm font-medium leading-6"
                           }>{fields.firstName.text}</label>
                        <div className="mt-2">
                            <input
                                required={true}
                                placeholder={fields.firstName.placeholder}
                                className={"block w-full rounded-md py-2 shadow-sm sm:text-sm sm:leading-6 pl-2 dark:text-black"}
                                type="text"
                                name={"prenom"}
                                value={formData.prenom}
                                onChange={handleChange}
                            />
                        </div>
                   </div>
                    <div>
                        <label className={"block text-sm font-medium leading-6"}>{fields.email.text}</label>
                        <div className="mt-2">
                            <input
                                required={true}
                                placeholder={fields.email.placeholder}
                                title={"Exemple: email@email.com"}
                                className={"block w-full rounded-md py-2 shadow-sm sm:text-sm sm:leading-6 pl-2 dark:text-black"
                                }
                                type="email"
                                name={"email"}
                                value={formData.email}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                    <div>
                        <label className={"block text-sm font-medium leading-6"}>{fields.password.text}</label>
                        <div className="mt-2">
                            <input
                                required={true}
                                placeholder={fields.password.placeholder}
                                pattern={"^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"}
                                minLength={8}
                                title={"8 caractères minimum, au moins une lettre et un chiffre"}
                                className={"block w-full rounded-md py-2 shadow-sm sm:text-sm sm:leading-6 pl-2 dark:text-black"}
                                type="text"
                                name={"password"}
                                value={formData.password}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                    <div>
                        <label className={"block text-sm font-medium leading-6"}>{fields.phone.text}</label>
                        <div className="mt-2">
                            <input
                                required={true}
                                pattern={"[0-9]{3}-[0-9]{3}-[0-9]{4}"}
                                title={fields.phone.validation.pattern}
                                placeholder={fields.phone.placeholder}
                                className={"block w-full rounded-md py-2 shadow-sm sm:text-sm sm:leading-6 pl-2 dark:text-black"}
                                type="text"
                                name={"phone"}
                                value={formData.phone}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                    <div>
                        <label className={"block text-sm font-medium leading-6"}>{fields.matricule.text}</label>
                        <div className="mt-2">
                            <input
                                required={true}
                                pattern={"[0-9]{7}"}
                                title={fields.matricule.validation.pattern}
                                placeholder={fields.matricule.placeholder}
                                className={"block w-full rounded-md py-2 shadow-sm sm:text-sm sm:leading-6 pl-2 dark:text-black"}
                                type="text"
                                name={"matricule"}
                                value={formData.matricule}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                    <div>
                        <label className={"block text-sm font-medium leading-6"}>{fields.programme.text}</label>
                        <div className="mt-2">
                            <select
                                required={true}
                                className={"block w-full rounded-md py-2 shadow-sm sm:text-sm sm:leading-6 pl-2 text-black"}
                                defaultValue={"DEFAULT"}
                                name={"programme_id"}
                                onChange={handleChange}
                            >
                                <option value={"DEFAULT"} disabled>{fields.programme.placeholder}</option>
                                {programmes.map((programme) => (
                                    <option key={programme['id']} value={Number(programme['id'])}>{programme['nom']}</option>
                                ))}
                            </select>
                        </div>
                    </div>
                    <div>
                        <button
                            type="submit"
                            className=
                                {"flex w-full justify-center rounded-md px-3 py-1.5 text-sm font-semibold leading-6 shadow-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 dark:bg-orange dark:hover:bg-orange-400 bg-blue hover:bg-blue-500 text-white"}>
                            {fields.submitButton.text}
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default StudentInscriptionForm;