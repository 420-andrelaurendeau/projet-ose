import React from "react";
import {useLocation} from "react-router-dom";

function ErrorPage() {
    const location = useLocation();
    const user = location.state;
    return (

        <div className="w-full h-screen flex flex-col items-center justify-center">
            <div className="flex flex-col items-center justify-center">
                <p className="text-5xl md:text-6xl lg:text-7xl font-bold tracking-wider text-gray-600 mt-8">500</p>
                <p className="text-2xl md:text-3xl lg:text-4xl font-bold text-gray-600 mt-2">Server Error</p>
                <p className="md:text-lg xl:text-xl text-gray-500 mt-4">Whoops,
                    sorry <b>{user.nom} {user.prenom}</b> something went wrong on our servers.</p>
            </div>
        </div>
    );
}

export default ErrorPage;