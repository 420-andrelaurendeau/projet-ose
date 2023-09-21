import React, { useEffect, useState, useCallback } from "react";
import { useDropzone } from "react-dropzone";
import axios from "axios";
import {useTranslation} from "react-i18next";

function InscriptionEmployeur(props: any) {
  const {i18n} = useTranslation();
  const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.InscriptionEmployeur");


  interface FormData {
    nom: string;
    prenom: string;
    email: string;
    phone: string;
    password: string;
    nomEntreprise: string;
    programme: any;
  }

  const [formData, setFormData] = useState<FormData>({
    nom: "",
    prenom: "",
    phone: "",
    email: "",
    password: "",
    nomEntreprise: "",
    programme: null,
  });
  const [showPassword, setShowPasswprd] = useState(false);
  const [programmes, setProgrammes] = useState([]);

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

  React.useEffect(() => {
        fetchProgrammes();
      }
      , []);

  function handleChange(event: any) {
    const { name, value } = event.target;

    setFormData({
      ...formData,
      [name]: value,
    });
    console.log(name + "= " + value);
  }

  const handleSubmit = (event: any) => {
    event.preventDefault();
    console.log(formData.email);

    const nom = formData.nom;
    const prenom = formData.prenom;
    const email = formData.phone;
    const phone = formData.email;
    const password = formData.password;
    const nomEntreprise = formData.nomEntreprise;
    const programme = formData.programme;

    if (programme == null) {
      alert(fields.programme.validation.required);
      return;
    }

    axios
      .post("http://localhost:8080/api/employeur/ajouter", {
        nom: nom,
        prenom: prenom,
        email: email,
        phone: phone,
        password: password,
        nomEntreprise: nomEntreprise,
        programme: programme,
      })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
        alert(fields.erreur.text);
      })
      .then(() => {
        alert("Bravo!");
        setFormData({
          nom: "",
          prenom: "",
          email: "",
          phone: "",
          password: "",
          nomEntreprise: "",
          programme: 0,
        });
        event.target.reset();
      });
  };


  return (
    <div className="container mx-auto ">
      <div className=" justify-center items-center">
        <img
          onClick={props.toggleDarkMode}
          className="self-left justify-self-end w-8 h-auto"
          src={props.darkMode ? props.toggleOn : props.toggleOff}
          alt="toggle"
        />
      </div>
      <h1
        className={
          props.darkMode
            ? "text-center text-2xl font-bold mb-4 col-span-12 text-white"
            : "text-center text-2xl font-bold mb-4 col-span-12 text-black"
        }
      >
        {fields.titre.text}
      </h1>
      <form
        className="grid grid-cols-6 gap-3 p-3"
        onSubmit={handleSubmit}
      >
        <div className="col-span-6 lg:col-start-2 lg:col-span-2 sm:col-span-3">
          <label
            htmlFor="nom"
            className={
              props.darkMode
                ? "block font-bold text-white"
                : "block font-bold text-black"
            }
          >
            {fields.nom.text}
          </label>
          <input
            name={"nom"}
            value={formData.nom}
            onChange={handleChange}
            required={true}
            type="text"
            id="nom"
            className={
              props.darkMode
                ? "w-full border border-gray-300 rounded p-1 text-orange"
                : "w-full border border-gray-300 rounded p-1 text-blue"
            }
            placeholder={fields.nom.placeholder}
          />
        </div>
        <div className="col-span-6 lg:col-span-2 sm:col-span-3">
          <label
            htmlFor="prenom"
            className={
              props.darkMode
                ? "block font-bold text-white"
                : "block font-bold text-black"
            }
          >
            {fields.prenom.text}
          </label>
          <input
            name={"prenom"}
            value={formData.prenom}
            onChange={handleChange}
            required={true}
            type="text"
            id="prenom"
            className={
              props.darkMode
                ? "w-full border border-gray-300 rounded p-1 text-orange"
                : "w-full border border-gray-300 rounded p-1 text-blue"
            }
            placeholder= {fields.prenom.placeholder}
          />
        </div>
        <div className="col-span-6 lg:col-start-2 lg:col-span-4">
          <label
            htmlFor={fields.nomEntreprise.name}
            className={
              props.darkMode
                ? "block font-bold text-white"
                : "block font-bold text-black"
            }
          >
            {fields.nomEntreprise.text}
          </label>
          <input
            name={"nomEntreprise"}
            value={formData.nomEntreprise}
            onChange={handleChange}
            required={true}
            type="text"
            id={fields.nomEntreprise.name}
            className={
              props.darkMode
                ? "w-full border border-gray-300 rounded p-1 text-orange"
                : "w-full border border-gray-300 rounded p-1 text-blue"
            }
            placeholder={fields.nomEntreprise.placeholder}
          />
        </div>
        <div className="col-span-6 lg:col-start-2 lg:col-span-4">
          <label
            htmlFor="email"
            className={
              props.darkMode
                ? "block font-bold text-white"
                : "block font-bold text-black"
            }
          >
            {fields.email.text}
          </label>
          <input
            name={"email"}
            value={formData.email}
            onChange={handleChange}
            required={true}
            id="email"
            type="email"
            className={
              props.darkMode
                ? "w-full border border-gray-300 rounded p-1 text-orange"
                : "w-full border border-gray-300 rounded p-1 text-blue"
            }
            placeholder={fields.email.placeholder}
          />
        </div>
        <div className="col-span-6 lg:col-start-2 lg:col-span-4">
          <label
            htmlFor="telephone"
            className={
              props.darkMode
                ? "block font-bold text-white"
                : "block font-bold text-black"
            }
          >
            {fields.telephone.text}
          </label>
          <input
            name={"phone"}
            value={formData.phone}
            onChange={handleChange}
            required={true}
            pattern={"[0-9]{3}-[0-9]{3}-[0-9]{4}"}
            title={"Exemple: 450-450-4500"}
            placeholder={fields.telephone.placeholder}
            id="telephone"
            type="tel"
            className={
              props.darkMode
                ? "w-full border rounded p-1 text-orange"
                : "w-full border border-gray-300 rounded p-1 text-blue"
            }
          />
        </div>
        <div className="col-span-6 lg:col-start-2 lg:col-span-4">
          <label
            htmlFor="password"
            className={
              props.darkMode
                ? "block font-bold text-white"
                : "block font-bold text-black"
            }
          >
            {fields.password.text}
          </label>
          <div className="flex flex-row">
            <input
              name={"password"}
              value={formData.password}
              onChange={handleChange}
              required={true}
              minLength={5}
              id="password"
              type={showPassword ? "text" : "password"}
              placeholder={fields.password.placeholder}
              className={
                props.darkMode
                  ? "basis-3/4 mr-2 border rounded p-1 text-orange"
                  : "basis-3/4 mr-2 border rounded p-1 text-blue"
              }
            />
            <button
              type="button"
              onClick={tooglePasswordVisibility}
              className={
                props.darkMode
                  ? "basis-1/4 mx-auto border rounded p-1 text-white bg-orange"
                  : "basis-1/4 mx-auto border rounded p-1 text-white bg-blue"
              }
            >
              {showPassword ? fields.passWordNotShown.text : fields.passWordShown.text }
            </button>
          </div>
        </div>
        <div className="col-span-6 lg:col-start-2 lg:col-span-4">
          <label
            htmlFor="programme"
            className={
              props.darkMode
                ? "block font-bold text-white"
                : "block font-bold text-black"
            }
          >
            {fields.programme.text}
          </label>
          <select
            value={formData.programme}
            onChange={handleChange}
            name={"programme"}
            defaultValue={"DEFAULT"}
            id="programme"
            required={true}
            className={
              props.darkMode
                ? "border border-orange text-black text-sm rounded-lg focus:ring-blue-500 block w-full p-2.5 "
                : "border border-blue text-black text-sm rounded-lg focus:ring-blue-500 block w-full p-2.5 "
            }
          >
            <option value={"DEFAULT"} disabled>{fields.programme.placeholder}</option>
            {programmes.map((programme) => (
                <option key={programme['id']} value={programme['id']}>{programme['nom']}</option>
            ))}
          </select>
        </div>
        <button
          className="col-span-6 lg:col-start-2 lg:col-span-4 bg-blue rounded text-white"
          type="submit"
        >
          {fields.soumettre.text}
        </button>
      </form>
    </div>
  );
}

export default InscriptionEmployeur;
