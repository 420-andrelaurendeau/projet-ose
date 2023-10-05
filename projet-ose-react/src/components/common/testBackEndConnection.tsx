import React, {useEffect} from "react";
import {useNavigate} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faUser} from '@fortawesome/free-solid-svg-icons'


const TestBackEndConnection = () => {

    const [utilisateurs, setUtilisateurs] = React.useState([])
    const navigate = useNavigate()

    interface FormData {
        nom: string;
        prenom: string;
        email: string;
        telephone: string;
        entreprise: string;
        programme: string;
    }

    useEffect(() => {
        const getUtilisateurs = async () => {
            const utilisateursFromServer = await fetchUtilisateurs()
            setUtilisateurs(utilisateursFromServer)

        }
        getUtilisateurs().then(r => console.log(r))
    }, [])

    const fetchUtilisateurs = async () => {
        const res = await fetch('http://localhost:8080/api/utilisateur/utilisateurs')
        return await res.json()
    }

    const handleSubmit = (user: FormData) => {
        navigate('/home', {
            state: user,

        });
    }

    return (
        <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
            <div className=" sm:mx-auto sm:w-full sm:max-w-md">
                <ul role="list" className="divide-y divide-gray-100">
                    {utilisateurs.map((utilisateur: FormData) => (
                        <li key={utilisateur.email} className="flex justify-between gap-x-6 py-5">
                            <div className="flex min-w-0 gap-x-4">
                                <FontAwesomeIcon className="h-12 w-12 flex-none rounded-full bg-gray-50" icon={faUser}/>
                                <div className="min-w-0 flex-auto">
                                    <p className="text-sm font-semibold leading-6 text-gray-900">{utilisateur.prenom + ' ' + utilisateur.nom}</p>
                                    <p className="mt-1 truncate text-xs leading-5 text-gray-500">
                                        {utilisateur.email}
                                        {' - '}
                                        {utilisateur.programme ? <i>{utilisateur.programme}</i> :
                                            <i>{utilisateur.entreprise}</i>}
                                    </p>
                                </div>
                            </div>
                            <div className="shrink-0 sm:flex sm:flex-col sm:items-end">
                                <button
                                    type="button"
                                    onClick={() => handleSubmit(utilisateur)}
                                    className="bg-blue hover:bg-cyan-900 text-white font-bold py-2 px-4 border border-blue rounded">
                                    Sign in
                                </button>
                            </div>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}

export default TestBackEndConnection;