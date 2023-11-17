import React, {useEffect, useState} from "react";
import axios from "axios";
import {useTranslation} from "react-i18next";
import img from "../../../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png"
import imgDark from "../../../assets/images/Cegep-Andre-Laurendeau.png";
import {Program} from "../../../model/Program";
import {useNavigate} from "react-router-dom";
import {useToast} from "../../../hooks/state/useToast";
import {saveEmployer} from "../../../api/EmployerAPI";

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
    const [programmes, setProgrammes] = useState<Program[]>([]);

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
        navigate("/");
    };

    const handleSubmit = (event: any) => {
        event.preventDefault();

        const programme = formData.programme_id;

        if (programme == 0) {
            toast.error("Veuillez choisir un programme");
            return;
        }

        saveEmployer(JSON.stringify(formData))
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
            <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8 dark:text-white text-black">
                <div className="sm:mx-auto sm:w-full sm:max-w-sm">
                    <img
                        className="mx-auto h-16 w-auto dark:hidden"
                        src={img}
                        alt="Your Company"
                    />
                    <img
                        className="mx-auto h-16 w-auto hidden dark:flex"
                        src={imgDark}
                        alt="Your Company"
                    />
                    <h2 className=
                            {"mt-10 text-center text-2xl font-bold leading-9 tracking-tight"}>
                        {fields.titre.text}
                    </h2>
                </div>
                <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                    <form className="space-y-6" action="#" method="POST" onSubmit={handleSubmit}>
                        <div className="flex justify-between ">
                            <div>
                                <label htmlFor="prenom" className=
                                    {"block text-sm font-medium leading-6"}>
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
                                            {"block w-full rounded-md py-2 shadow-sm sm:text-sm sm:leading-6 pl-2 dark:text-black"}
                                        value={formData.prenom}
                                        onChange={handleChange}
                                    />
                                </div>
                            </div>

                            <div>
                                <label htmlFor="nom" className=
                                    {"block text-sm font-medium leading-6"}>
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
                                            {"block w-full rounded-md py-2 shadow-sm sm:text-sm sm:leading-6 pl-2 dark:text-black"}
                                        value={formData.nom}
                                        onChange={handleChange}
                                    />
                                </div>
                            </div>
                        </div>

                        <div>
                            <label htmlFor="entreprise" className=
                                {"block text-sm font-medium leading-6"}>
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
                                    className={"block w-full rounded-md py-2 shadow-sm sm:text-sm sm:leading-6 pl-2 dark:text-black"}
                                    value={formData.entreprise}
                                    onChange={handleChange}
                                />
                            </div>
                        </div>

                        <div>
                            <label htmlFor="email" className=
                                {"block text-sm font-medium leading-6"}>
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
                                    className={"block w-full rounded-md py-2 shadow-sm sm:text-sm sm:leading-6 pl-2 dark:text-black"}
                                    value={formData.email}
                                    onChange={handleChange}
                                />
                            </div>
                        </div>

                        <div>
                            <label htmlFor="phone" className=
                                {"block text-sm font-medium leading-6"}>
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
                                    className={"block w-full rounded-md py-2 shadow-sm sm:text-sm sm:leading-6 pl-2 dark:text-black"}
                                    value={formData.phone}
                                    onChange={handleChange}
                                />
                            </div>
                        </div>
                        <div>
                            <div className="flex justify-between ">
                                <label htmlFor="password" className=
                                    {"block text-sm font-medium leading-6"}>
                                    {fields.password.text}
                                </label>
                                <div className="justify-center">
                                    <button
                                        type="button"
                                        onClick={tooglePasswordVisibility}
                                        className={"basis-1/4 mx-auto border rounded p-1 text-white dark:bg-orange dark:hover:bg-orange-500 bg-blue hover:bg-blue-500"}
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
                                    className={"block w-full rounded-md py-2 shadow-sm sm:text-sm sm:leading-6 pl-2 dark:text-black"
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
                                    "block text-sm font-medium leading-6"
                                }
                            >
                                {fields.programme.text}
                            </label>
                            <select
                                onChange={handleChange}
                                name={"programme_id"}
                                defaultValue={"DEFAULT"}
                                required={true}
                                className={
                                   "block w-full bg-white rounded-md py-2 shadow-sm sm:text-sm sm:leading-6 pl-2 dark:text-black"
                                }
                            >
                                <option value={"DEFAULT"} disabled>{fields.programme.text}</option>
                                {programmes.map((programme) => (
                                    <option key={programme['id']}
                                            value={Number(programme['id'])}>{programme['nom']}</option>
                                ))}
                            </select>
                        </div>

                        <button
                            type="submit"
                            className=
                                {"text-white flex w-full justify-center rounded-md px-3 py-1.5 text-sm font-semibold leading-6 shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 dark:bg-orange dark:hover:bg-orange-500 bg-blue hover:bg-blue"}>
                            {fields.soumettre.text}
                        </button>
                    </form>

                </div>
            </div>
        </div>
    );
}

export default InscriptionEmployeur;
