function InscriptionEmployeur() {
  return (
    <div className="flex flex-col justify-center items-center">
      <h1 className="mb-3 font-bold">Inscription d'un Employeur</h1>
      <form className="grid grid-cols-4 gap-y-3 gap-x-3 rounded p-5 m-4">
        <label className="col-span-2 font-medium" htmlFor="prenom">
          Prénom :
        </label>
        <label className="col-span-2 font-medium" htmlFor="nom">
          Nom :
        </label>
        <input
          className="col-span-2 p-1 rounded shadow-lg"
          type="text"
          id="prenom"
          placeholder="Votre Prénom ..."
        />

        <input
          className="col-span-2 p-1 rounded shadow-lg"
          type="text"
          id="nom"
          placeholder="Votre Nom ..."
        />

        <label className="col-span-4 font-medium" htmlFor="nomEntreprise">
          Nom de l'entreprise :
        </label>
        <input
          className="col-span-4 p-1 rounded shadow-lg"
          type="text"
          id="nomEntreprise"
          placeholder="nom Inc."
        />

        <label className="col-span-4 font-medium" htmlFor="email">
          Email :
        </label>
        <input
          className="col-span-4 p-1 rounded shadow-lg"
          type="email"
          id="email"
          placeholder="nom@email.com ..."
        />

        <label className="col-span-4 font-medium" htmlFor="telephone">
          Téléphone :
        </label>
        <input
          className="col-span-4 p-1 rounded shadow-lg"
          type="tel"
          id="telephone"
          placeholder="Votre numéro de téléphone ..."
        />

        <label className="col-span-4 font-medium" htmlFor="file_input">
          Televerser un document :
        </label>
        <div className=" col-span-4">
          <label className="flex justify-center w-full h-32 px-4 transition bg-white border-2 border-gray-300 border-dashed rounded-md appearance-none cursor-pointer ">
            <span className="flex items-center space-x-2">
              <span className="font-medium text-gray-600">
                Drop files to Attach, or
              </span>
              <span className="font-medium text-blue-600 underline">
                browse
              </span>
            </span>
            <input type="file" name="file_upload" className="hidden"></input>
          </label>
        </div>
        <button className="my-4 p-1 bg-blue text-white rounded col-span-4">
          Soumettre
        </button>
      </form>
    </div>
  );
}

export default InscriptionEmployeur;
