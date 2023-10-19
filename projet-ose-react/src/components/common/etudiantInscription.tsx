import React, {useEffect, useState} from "react";
import axios from "axios";
import { useTranslation } from "react-i18next";
import jwtDecode from 'jwt-decode';
// @ts-ignore
import img from '../../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png';
// @ts-ignore
import imgDark from '../../assets/images/Cegep-Andre-Laurendeau.png';
import {Link, useNavigate} from "react-router-dom";
import {useToast} from "../../hooks/state/useToast";

function EtudiantInscription(props: any) {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.InscriptionFormEtudiant");
    const navigate = useNavigate();
    const toast = useToast();

    interface FormData {
        nom: string;
        prenom: string;
        phone: string;
        email: string;
        password: string;
        matricule: string;
        programme_id: any;
    }

    const [formData, setFormData] = useState<FormData>({
        nom: "",
        prenom: "",
        email: "",
        password: "",
        phone: "",
        matricule: "",
        programme_id: null,
    });

    const [programmes, setProgrammes] = useState([]);
    const [reussite, setReussite] = useState(false);
    const [error, setError] = useState(false);

    useEffect(() => {
        fetchProgrammes();
    }, []);

    useEffect(() => {
        setFormData(formData);
    },[formData]);


    function handleChange(event: any) {
        const {name, value} = event.target;

        setFormData({
            ...formData,
            [name]: value,
        });
        console.log(name + "= " + value);
    }

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

    const handleSubmit = (event:any) => {
        event.preventDefault();

        console.log("form: "+ formData)

        if (formData.programme_id == null) {
            alert(fields.programme.validation.required);
            return;
        }

        axios
            .post("http://localhost:8080/api/auth/register/etudiant", JSON.stringify(formData), {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {
                console.log(response)
                setReussite(true)
                setError(false)
                if (response.data != null){
                    handleRedirect();
                }else{
                    console.log("Donnee erronee")
                }

            })
            .catch((error) => {
                console.log(error)
                alert("Erreur lors de l'inscription")
                setReussite(false)
                setError(true)
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
        });
    };

    const handleRedirect = async () => {
        toast.success("Inscription réussie");
        navigate("/signIn");
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
                    {fields.title.text}
                </h2>
            </div>
            <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                <form className={"space-y-6"} onSubmit={handleSubmit}>
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
                                title={fields.phone.validation.pattern}
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
                            : "block text-sm font-medium leading-6 text-black"}>{fields.matricule.text}</label>
                        <div className="mt-2">
                            <input
                                required={true}
                                pattern={"[0-9]{7}"}
                                title={fields.matricule.validation.pattern}
                                placeholder={fields.matricule.placeholder}
                                className={props.darkMode ?
                                    "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                    : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                                }
                                type="text"
                                name={"matricule"}
                                value={formData.matricule}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                    <div>
                        <label
                            htmlFor="programme"
                            className={
                                props.darkMode ?
                                    "block text-sm font-medium leading-6 text-white"
                                    : "block text-sm font-medium leading-6 text-black"
                            }
                        >
                            {fields.programme.text}
                        </label>
                        <select
                            value={formData.programme_id}
                            onChange={handleChange}
                            name={"programme_id"}
                            defaultValue={"DEFAULT"}
                            id="programme_id"
                            required={true}
                            className={
                                props.darkMode ?
                                    "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                    : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                            }
                        >
                            <option value={"DEFAULT"} disabled>{fields.programme.placeholder}</option>
                            {programmes.map((programme) => (
                                <option key={programme['id']} value={programme['id']}>{programme['nom']}</option>
                            ))}
                        </select>
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
                        {reussite && <p className="text-green-500 scale-150 text-center">{fields.reussite.name}</p>}
                        {reussite && <Link to={"/signIn"}><p className="text-green-500 scale-100 text-center">{fields.reussite.link}</p></Link>}
                        {error && <p className="text-red-500 scale-150 text-center">{fields.error.name}</p>}
                    </div>
                </form>
            </div>
        </div>
    );
}

export default EtudiantInscription;