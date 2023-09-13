function InscriptionEmployeur() {
  return (
    <div className="flex flex-col justify-center items-center">
      <h1 className="mb-3 text-bold">Inscription d'un Employeur</h1>
      <form className="grid grid-cols-1 gap-y-2 gap-x-3 bg-slate-100 rounded p-5">
        <label className="font-medium" htmlFor="prenom">
          Prénom :
        </label>
        <input
          className="col-span-3 p-1 rounded"
          type="text"
          id="prenom"
          placeholder="Votre Prénom ..."
        />

        <label className="font-medium" htmlFor="nom">
          Nom :
        </label>
        <input
          className="col-span-3 p-1 rounded"
          type="text"
          id="nom"
          placeholder="Votre Nom ..."
        />

        <label className="font-medium" htmlFor="nomEntreprise">
          Nom de l'entreprise :
        </label>
        <input
          className="col-span-3 p-1 rounded"
          type="text"
          id="nomEntreprise"
          placeholder="Nom de votre entreprise ..."
        />

        <label className="font-medium" htmlFor="email">
          Email :
        </label>
        <input
          className="col-span-3 p-1 rounded"
          type="email"
          id="email"
          placeholder="Votre email ..."
        />

        <label className="font-medium" htmlFor="telephone">
          Téléphone :
        </label>
        <input
          className="col-span-3 p-1 rounded"
          type="tel"
          id="telephone"
          placeholder="Votre numéro de téléphone ..."
        />

        <label className="font-medium" htmlFor="file_input">
          Upload file
        </label>
        <input
          className="col-span-3 border border-black-400 rounded"
          id="file_input"
          type="file"
        />

        <button className="my-4 p-1 bg-blue text-white rounded col-span-3">
          Soumettre
        </button>
      </form>
    </div>
  );
}

export default InscriptionEmployeur;
