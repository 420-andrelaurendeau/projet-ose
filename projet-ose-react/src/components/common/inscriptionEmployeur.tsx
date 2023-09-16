import React, { useEffect, useState } from "react";
import { Trans, useTranslation } from "react-i18next";

function InscriptionEmployeur(props: any) {
  interface FormData {
    nom: string;
    prenom: string;
    nomEntreprise: string;
    email: string;
    telephone: string;
    fileNumber: string;
  }

  const [formData, setFormData] = useState<FormData>({
    nom: "",
    prenom: "",
    nomEntreprise: "",
    email: "",
    telephone: "",
    fileNumber: "",
  });
  const [submitting, setSubmitting] = useState(false);

  function handleChange(event: any) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    console.log(name + "= " + value);
    setFormData({
      ...formData,
      [name]: value,
    });
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
                ? "w-full border border-gray-300 rounded p-1 text-orange"
                : "w-full border border-gray-300 rounded p-1 text-blue"
            }
          />
        </div>
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
