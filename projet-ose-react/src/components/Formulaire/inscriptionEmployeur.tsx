import React, {useEffect, useState } from "react";
import {Trans} from "react-i18next";


function InscriptionEmployeur() {

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
  const [errors, setErrors] = useState<Partial<FormData>>({});
  const [submitting, setSubmitting] = useState(false);

  function handleChange(event: any) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    console.log(name + "= " + value)
    setFormData({
      ...formData,
      [name]: value,
    });
  }

  const handleSubmit = (event: any) => {
    event.preventDefault();
    setErrors(validateValues(formData));
    setSubmitting(true);
  };

  const validateValues = (inputValues: FormData) => {
    let erreurs: Partial<FormData> = {};
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if(inputValues.email.length == 0){
      erreurs.email = "L'adresse email ne peux pas etre vide"
    }
    else if (!emailPattern.test(inputValues.email)) {
      erreurs.email = "L'adresse email est invalide";
    }

    if(inputValues.nom.length == 0){
      erreurs.nom = "Le nom ne peux pas etre vide"
    }
    else if(inputValues.nom.length < 5) {
      erreurs.nom = "Le nom est incorrect";
    }

    if(inputValues.prenom.length == 0){
      erreurs.prenom = "Le prenom ne peux pas etre vide"
    }
    else if (inputValues.prenom.length < 5) {
      erreurs.prenom = "le prenom est incorrect";
    }

    if(inputValues.telephone.length == 0){
      erreurs.email = "Le telephone ne peux pas etre vide"
    }
    else if(inputValues.telephone.length != 10 ){

      erreurs.telephone = "le telephone doit avoir 10 caractere"
    }

    console.log(erreurs)

    return erreurs;
  };

  const finishSubmit = () => {
    console.log(formData);
  };
  useEffect(() => {
    if (Object.keys(errors).length === 0 && submitting) {
      finishSubmit();
    }
  }, [errors]);

    return(
        <div className="flex flex-col h-screen items-center">
      {Object.keys(errors).length === 0 && submitting ? (
          <span className="success">Successfully submitted ✓</span>
      ) : null}
          <div>
            <h1 className="mb-3 font-bold">Inscription d'un Employeur</h1>
          </div>
          <div>

            <form className="p-5 m-4" onSubmit={handleSubmit} >


                <label className="col-span-2 font-medium" htmlFor="prenom">
                  Prénom :
                </label>
                <label className="col-span-2 font-medium" htmlFor="nom">
                  Nom :
                </label>


                <input
                    className="col-span-2 p-1 rounded shadow-lg"
                    type={"text"}
                    id="prenom"
                    placeholder="Votre Prénom ..."
                    name={"prenom"}
                    value={formData.prenom}
                    onChange={handleChange}
                />
                <input
                    className="col-span-2 p-1 rounded shadow-lg"
                    type="text"
                    id="nom"
                    placeholder="Votre Nom ..."
                    name={"nom"}
                    value={formData.nom}
                    onChange={handleChange}
                />
                {errors.prenom ? (
                    <p className="error col-span-2">
                      {errors.prenom}
                    </p>
                ) : null}
                {errors.nom ? (
                    <p className="error col-span-2">
                      {errors.nom}
                    </p>
                ) : null}








              {/*<label className="col-span-4 font-medium" htmlFor="nomEntreprise">*/}
              {/*  Nom de l'entreprise :*/}
              {/*</label>*/}
              {/*<input*/}
              {/*    className="col-span-4 p-1 rounded shadow-lg"*/}
              {/*    type="text"*/}
              {/*    id="nomEntreprise"*/}
              {/*    placeholder="nom Inc."*/}
              {/*    name={"nomEntreprise"}*/}
              {/*    value={formData.nomEntreprise}*/}
              {/*    onChange={handleChange}*/}
              {/*/>*/}
              {/*/!*{errors.nomEntreprise ? (*!/*/}
              {/*/!*    <p className="error">*!/*/}
              {/*/!*      {errors.nomEntreprise}*!/*/}
              {/*/!*    </p>*!/*/}
              {/*/!*) : null}*!/*/}


              {/*<label className="col-span-4 font-medium" htmlFor="email">*/}
              {/*  Email :*/}
              {/*</label>*/}
              {/*<input*/}
              {/*    className="col-span-4 p-1 rounded shadow-lg"*/}
              {/*    type="email"*/}
              {/*    id="email"*/}
              {/*    placeholder="nom@email.com ..."*/}
              {/*    name={"email"}*/}
              {/*    value={formData.email}*/}
              {/*    onChange={handleChange}*/}
              {/*/>*/}
              {/*/!*{errors.email ? (*!/*/}
              {/*/!*    <p className="error">*!/*/}
              {/*/!*      {errors.email}*!/*/}
              {/*/!*    </p>*!/*/}
              {/*/!*) : null}*!/*/}


              {/*<label className="col-span-4 font-medium" htmlFor="telephone">*/}
              {/*  Téléphone :*/}
              {/*</label>*/}
              {/*<input*/}
              {/*    className="col-span-4 p-1 rounded shadow-lg"*/}
              {/*    type="tel"*/}
              {/*    id="telephone"*/}
              {/*    placeholder="Votre numéro de téléphone ..."*/}
              {/*    name={"telephone"}*/}
              {/*    value={formData.telephone}*/}
              {/*    onChange={handleChange}*/}
              {/*/>*/}
              {/*/!*{errors.telephone ? (*!/*/}
              {/*/!*    <p className="error">*!/*/}
              {/*/!*      {errors.telephone}*!/*/}
              {/*/!*    </p>*!/*/}
              {/*/!*) : null}*!/*/}

              {/*<label className="col-span-4 font-medium" htmlFor="file_input">*/}
              {/*  Televerser un document :*/}
              {/*</label>*/}
              {/*<div className=" col-span-4">*/}
              {/*  <label className="flex justify-center w-full h-32 px-4 transition border-2 border-gray border-dashed rounded-md appearance-none cursor-pointer hover:border-black  ">*/}
              {/*<span className="flex items-center space-x-2">*/}
              {/*  <span className="font-medium text-gray-600">*/}
              {/*    Drop files to Attach, or*/}
              {/*  </span>*/}
              {/*  <span className="font-medium text-blue-600 underline">*/}
              {/*    browse*/}
              {/*  </span>*/}
              {/*</span>*/}
              {/*    <input id="file_input" type="file" name="file_upload" className="hidden"></input>*/}
              {/*  </label>*/}
              {/*</div>*/}
              <button type="submit" className="my-4 p-1 bg-blue text-white rounded col-span-4">
                Soumettre
              </button>
            </form>
          </div>

        </div>)

}

export default InscriptionEmployeur;