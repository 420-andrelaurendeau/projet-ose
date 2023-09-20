import React, { useEffect, useState, useCallback } from "react";
import { useDropzone } from "react-dropzone";

function InscriptionEmployeur(props: any) {
  interface FormData {
    nom: string;
    prenom: string;
    telephone: string;
    email: string;
    password: string;
    nomEntreprise: string;
    programme: string;
  }

  const [formData, setFormData] = useState<FormData>({
    nom: "",
    prenom: "",
    telephone: "",
    email: "",
    password: "",
    nomEntreprise: "",
    programme: "",
  });
  const [submitting, setSubmitting] = useState(false);
  const [binaryData, setBinaryData] = useState<any>(null);
  const [showPassword, setShowPasswprd] = useState(false);

  const tooglePasswordVisibility = () => {
    setShowPasswprd(!showPassword);
  };

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
    setSubmitting(true);
    alert("Bravo!");
  };

  return (
    <div className="container mx-auto">
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
        Inscription Employeur
      </h1>
      <form
        className="grid grid-cols-6 gap-3  md:grid-cols-8 md:col-span-10 p-3"
        onSubmit={handleSubmit}
      >
        <div className="col-span-6 lg:col-start-3 lg:col-span-2 sm:col-span-3">
          <label
            htmlFor="nom"
            className={
              props.darkMode
                ? "block font-bold text-white"
                : "block font-bold text-black"
            }
          >
            Nom :
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
            placeholder="Votre nom .."
          />
        </div>
        <div className="col-span-6 lg:col-start-5 lg:col-span-2 sm:col-span-3">
          <label
            htmlFor="prenom"
            className={
              props.darkMode
                ? "block font-bold text-white"
                : "block font-bold text-black"
            }
          >
            Prénom :
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
            placeholder="Votre prénom .."
          />
        </div>
        <div className="col-span-6 lg:col-start-3 lg:col-span-4">
          <label
            htmlFor="nomEntreprise"
            className={
              props.darkMode
                ? "block font-bold text-white"
                : "block font-bold text-black"
            }
          >
            Nom de l'entreprise :
          </label>
          <input
            name={"nomEntreprise"}
            value={formData.nomEntreprise}
            onChange={handleChange}
            required={true}
            type="text"
            id="nomEntreprise"
            className={
              props.darkMode
                ? "w-full border border-gray-300 rounded p-1 text-orange"
                : "w-full border border-gray-300 rounded p-1 text-blue"
            }
            placeholder="Nom de l'entreprise .."
          />
        </div>
        <div className="col-span-6 lg:col-start-3 lg:col-span-4">
          <label
            htmlFor="email"
            className={
              props.darkMode
                ? "block font-bold text-white"
                : "block font-bold text-black"
            }
          >
            Email :
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
            placeholder="Email .."
          />
        </div>
        <div className="col-span-6 lg:col-start-3 lg:col-span-4">
          <label
            htmlFor="telephone"
            className={
              props.darkMode
                ? "block font-bold text-white"
                : "block font-bold text-black"
            }
          >
            Téléphone :
          </label>
          <input
            name={"telephone"}
            value={formData.telephone}
            onChange={handleChange}
            required={true}
            pattern={"[0-9]{3}-[0-9]{3}-[0-9]{4}"}
            title={"Exemple: 450-450-4500"}
            placeholder={"ex : 450-450-4500"}
            id="telephone"
            type="tel"
            className={
              props.darkMode
                ? "w-full border rounded p-1 text-orange"
                : "w-full border border-gray-300 rounded p-1 text-blue"
            }
          />
        </div>
        <div className="col-span-6 lg:col-start-3 lg:col-span-4">
          <label
            htmlFor="password"
            className={
              props.darkMode
                ? "block font-bold text-white"
                : "block font-bold text-black"
            }
          >
            Mot de passe :
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
              placeholder="Mot de passe ..."
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
              {showPassword ? "Hide" : "Show"}
            </button>
          </div>
        </div>
        <div className="col-span-6 lg:col-start-3 lg:col-span-4">
          <label
            htmlFor="programme"
            className={
              props.darkMode
                ? "block font-bold text-white"
                : "block font-bold text-black"
            }
          >
            Selectectionner une option
          </label>
          <select
            value={formData.programme}
            onChange={handleChange}
            name={"programme"}
            id="programme"
            required={true}
            className={
              props.darkMode
                ? "border border-orange text-black text-sm rounded-lg focus:ring-blue-500 block w-full p-2.5 "
                : "border border-blue text-black text-sm rounded-lg focus:ring-blue-500 block w-full p-2.5 "
            }
          >
            <option selected value="">
              Choisir un programme
            </option>
            <option value="informatique">Technique informatique</option>
            <option value="soins">Soins infirmier</option>
            <option value="electrique">Technique electrique</option>
            <option value="construction">Genie Civile</option>
          </select>
        </div>
        {/* <div className="col-span-6 lg:col-start-3 lg:col-span-4">
          <label
            htmlFor="televersement"
            className={
              props.darkMode
                ? "block font-bold text-white"
                : "block font-bold text-black"
            }
          >
            Televerser un fichier :
          </label>
          <input
            className={
              props.darkMode
                ? "w-full border-dashed border-2 text-center file:bg-orange file:rounded file:border-solid file:border-transparent file:text-white cursor-pointer rounded p-1 text-orange font-bold"
                : "w-full border-dashed border-2 text-center file:bg-blue file:rounded file:border-solid file:border-transparent file:text-white cursor-pointer rounded p-1 text-black font-bold"
            }
            required={true}
            accept=".pdf"
            name="fileNumber"
            id="televersement"
            type="file"
            onChange={handleChange}
          />
        </div> */}
        <button
          className="col-span-6 lg:col-start-3 lg:col-span-4 bg-blue rounded text-white"
          type="submit"
        >
          Soumettre
        </button>
      </form>
    </div>
  );
}

export default InscriptionEmployeur;
