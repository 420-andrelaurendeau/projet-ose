import img from "./assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png";
import imgDark from "./assets/images/Cegep-Andre-Laurendeau.png";
import {Link} from "react-router-dom";
import React from "react";

function Header(props: any) {

    return (
        <nav className="bg-white shadow dark:bg-gray-800">
            <div className="container mx-1 md:flex md:justify-between md:items-center">
                <img
                    className={props.darkMode ? "mx h-10 w-min" : "mx h-10 w-min"}
                    src={props.darkMode ? imgDark : img}
                    alt="Cegep Andre Laurendeau"
                />
                <div className="container px-6 py-3 mx-auto md:flex md:justify-between md:items-center w-full">
                    <h1>Bonjour, {props.user.prenom}</h1>
                    <Link to={'/etudiantStage'} state={props.user} className={"text-red-600"}>Stage</Link>
                </div>
            </div>
        </nav>
    )
}

export default Header;