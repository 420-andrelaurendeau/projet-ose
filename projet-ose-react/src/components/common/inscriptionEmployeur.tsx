import React, {useEffect, useState} from "react";
import axios from "axios";
import {useTranslation} from "react-i18next";
import img from "../../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png"
import imgDark from "../../assets/images/Cegep-Andre-Laurendeau.png";
import {Programme} from "../../model/Programme";
import {useNavigate} from "react-router-dom";
import {useToast} from "../../hooks/state/useToast";

function InscriptionEmployeur(props: any) {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.InscriptionEmployeur");

    const toast = useToast();
    const navigate = useNavigate();



    const [formData, setFormData] = useState({
        nom: "",
        prenom: "",
        phone: "",
        email: "",
        password: "",
        entreprise: "",
        programme_id: 0,
    });

    const [showPassword, setShowPasswprd] = useState(false);
    const [programmes, setProgrammes] = useState<Programme[]>([]);

    const tooglePasswordVisibility = () => {
        setShowPasswprd(!showPassword);
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

    useEffect(() => {
        fetchProgrammes();
    }, []);


    useEffect(() => {
        setFormData(formData);
    }, [formData]);



    function handleChange(event: any) {
        const {name, value} = event.target;

        setFormData({
            ...formData,
            [name]: value,
        });
        console.log(name + "= " + value);
    }


    const handleRedirect = async () => {
        toast.success("Inscription réussie");
        navigate("/signIn");
    };

    const handleSubmit = (event: any) => {
        event.preventDefault();

        const programme = formData.programme_id;

        if (programme == null) {
            alert(fields.programme.validation.required);
            return;
        }

        axios
            .post("http://localhost:8080/api/employeur/ajouter", JSON.stringify(formData), {
                headers: {
                    'Content-Type': 'application/json'
                }

            })
            .then((response) => {
                console.log(JSON.stringify(formData));
                console.log(formData);
                console.log(response);
                handleRedirect();
            })
            .catch((error) => {
                console.log(error);
                console.log(JSON.stringify(formData));
                console.log(formData);
            })
            .then(() => {
                setFormData({
                    nom: "",
                    prenom: "",
                    phone: "",
                    email: "",
                    password: "",
                    entreprise: "",
                    programme_id: 0,
                });
                event.target.reset();
            });
    };


    return (
        <div>
            <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
                <div className="sm:mx-auto sm:w-full sm:max-w-sm">
                    <img
                        className="mx-auto h-16 w-auto"
                        src={props.darkMode ? imgDark : img}
                        alt="Your Company"
                    />
                    <h2 className=
                            {props.darkMode ?
                                "mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-white"
                                : "mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-black"}>
                        {fields.titre.text}
                    </h2>
                </div>
                <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                    <form className="space-y-6" action="#" method="POST" onSubmit={handleSubmit}>
                        <div className="flex justify-between ">
                            <div>
                                <label htmlFor="prenom" className=
                                    {props.darkMode ?
                                        "block text-sm font-medium leading-6 text-white"
                                        : "block text-sm font-medium leading-6 text-black"}>
                                    {fields.prenom.text}
                                </label>
                                <div className="mt-2">
                                    <input
                                        id="prenom"
                                        name="prenom"
                                        type="text"
                                        autoComplete="text"
                                        placeholder={fields.prenom.placeholder}
                                        required
                                        className=
                                            {props.darkMode ?
                                                "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                                : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                                            }
                                        value={formData.prenom}
                                        onChange={handleChange}
                                    />
                                </div>
                            </div>

                            <div>
                                <label htmlFor="nom" className=
                                    {props.darkMode ?
                                        "block text-sm font-medium leading-6 text-white"
                                        : "block text-sm font-medium leading-6 text-black"}>
                                    {fields.nom.text}
                                </label>
                                <div className="mt-2">
                                    <input
                                        id="nom"
                                        name="nom"
                                        type="text"
                                        autoComplete="text"
                                        placeholder={fields.nom.placeholder}
                                        required
                                        className=
                                            {props.darkMode ?
                                                "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                                : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                                            }
                                        value={formData.nom}
                                        onChange={handleChange}
                                    />
                                </div>
                            </div>
                        </div>

                        <div>
                            <label htmlFor="entreprise" className=
                                {props.darkMode ?
                                    "block text-sm font-medium leading-6 text-white"
                                    : "block text-sm font-medium leading-6 text-black"}>
                                {fields.entreprise.text}
                            </label>
                            <div className="mt-2">
                                <input
                                    id="entreprise"
                                    name="entreprise"
                                    type="text"
                                    autoComplete="text"
                                    placeholder={fields.entreprise.placeholder}
                                    required
                                    className=
                                        {props.darkMode ?
                                            "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                            : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                                        }
                                    value={formData.entreprise}
                                    onChange={handleChange}
                                />
                            </div>
                        </div>

                        <div>
                            <label htmlFor="email" className=
                                {props.darkMode ?
                                    "block text-sm font-medium leading-6 text-white"
                                    : "block text-sm font-medium leading-6 text-black"}>
                                {fields.email.text}
                            </label>
                            <div className="mt-2">
                                <input
                                    id="email"
                                    name="email"
                                    type="email"
                                    autoComplete="email"
                                    placeholder={fields.email.placeholder}
                                    required
                                    className=
                                        {props.darkMode ?
                                            "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                            : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                                        }
                                    value={formData.email}
                                    onChange={handleChange}
                                />
                            </div>
                        </div>

                        <div>
                            <label htmlFor="phone" className=
                                {props.darkMode ?
                                    "block text-sm font-medium leading-6 text-white"
                                    : "block text-sm font-medium leading-6 text-black"}>
                                {fields.telephone.text}
                            </label>
                            <div className="mt-2">
                                <input
                                    id="phone"
                                    name="phone"
                                    type="tel"
                                    autoComplete="email"
                                    placeholder={fields.telephone.placeholder}
                                    required
                                    className=
                                        {props.darkMode ?
                                            "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                            : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                                        }
                                    value={formData.phone}
                                    onChange={handleChange}
                                />
                            </div>
                        </div>
                        <div>
                            <div className="flex justify-between ">
                                <label htmlFor="password" className=
                                    {props.darkMode ?
                                        "block text-sm font-medium leading-6 text-white"
                                        : "block text-sm font-medium leading-6 text-black"}>
                                    {fields.password.text}
                                </label>
                                <div className="justify-center">
                                    <button
                                        type="button"
                                        onClick={tooglePasswordVisibility}
                                        className={
                                            props.darkMode
                                                ? "basis-1/4 mx-auto border rounded p-1 text-white bg-orange"
                                                : "basis-1/4 mx-auto border rounded p-1 text-white bg-blue"
                                        }
                                    >
                                        {showPassword ? fields.passWordNotShown.text : fields.passWordShown.text}
                                    </button>
                                </div>
                            </div>
                            <div className="mt-2">
                                <input
                                    name="password"
                                    required
                                    minLength={5}
                                    id="password"
                                    type={showPassword ? "text" : "password"}
                                    placeholder={fields.password.placeholder}
                                    className=
                                        {props.darkMode ?
                                            "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                            : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                                        }
                                    value={formData.password}
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
                                id="programme"
                                required={true}
                                className={
                                    props.darkMode ?
                                        "block w-full bg-softdark rounded-md py-2 text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                        : "block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                                }
                            >
                                <option value={"DEFAULT"} disabled>{fields.programme.placeholder}</option>
                                {programmes.map((programme) => (
                                    <option key={programme['id']}
                                            value={Number(programme['id'])}>{programme['nom']}</option>
                                ))}
                            </select>
                        </div>

                        <button
                            type="submit"
                            className=
                                {props.darkMode ?
                                    "flex w-full justify-center rounded-md bg-orange px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-orange-100 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-orange"
                                    : "flex w-full justify-center rounded-md bg-blue px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-blue"
                                }>
                            {fields.soumettre.text}
                        </button>
                    </form>

                </div>
            </div>
        </div>
    );
}

export default InscriptionEmployeur;
