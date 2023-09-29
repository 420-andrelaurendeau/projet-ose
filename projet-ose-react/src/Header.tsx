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
                <h1 className={"w-full mx-10"}>Bonjour, {props.user.prenom}</h1>
                <div className="container px-6 py-3 mx-auto md:flex md:justify-end md:items-center w-full">
                    <Link to={'/home'} state={props.user} className={"text-red-600"}>Home</Link>
                    <Link to={'/etudiantStage'} state={props.user} className={"text-red-600"}>Stage</Link>
                </div>
            </div>
        </nav>
    )
}

export default Header;