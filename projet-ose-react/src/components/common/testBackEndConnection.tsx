import React, {useEffect} from "react";

const TestBackEndConnection = () => {

    const [utilisateur, setUtilisateur] = React.useState([])

    interface FormData {
        nom: string;
        prenom: string;
        email: string;
        telephone: string;
    }

    useEffect(() => {
        const getUtilisateurs = async () => {
            const utilisateursFromServer = await fetchUtilisateurs()
            setUtilisateur(utilisateursFromServer)

        }
        getUtilisateurs().then(r => console.log(r))
    }, [])

    const fetchUtilisateurs = async () => {
        const res = await fetch('http://localhost:8080/apiUtilisateur/utilisateurs')
        return await res.json()
    }
return (
    <div className="flex flex-col items-center">
        <div className="w-full md:w-1/2 flex flex-col items-center h-64">
            <div className="w-full px-4">
                <div className="flex flex-col items-center relative">
                    <div className="absolute shadow bg-white top-100 z-40 w-full lef-0 rounded max-h-select overflow-y-auto svelte-5uyqqj">
                        {utilisateur.map((utilisateur: FormData) => (
                            <div className="flex flex-col w-full">
                                <div className="cursor-pointer w-full border-gray-100 rounded-t border-b hover:bg-teal-100">
                                    <div className="w-6 flex flex-col items-center">
                                        <div className="flex relative w-5 h-5 bg-orange-500 justify-center items-center m-1 mr-2 w-4 h-4 mt-1 rounded-full "><img className="rounded-full" alt="A" src="https://randomuser.me/api/portraits/men/62.jpg"></img> </div>
                                    </div>
                                    <div className="w-full items-center flex">
                                        <div className="mx-2 -mt-1  ">{utilisateur.nom} {utilisateur.prenom}
                                            <div className="text-xs truncate w-full normal-case font-normal -mt-1 text-gray-500">{utilisateur.telephone} &amp; {utilisateur.email}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    </div>
    );
}

export default TestBackEndConnection;