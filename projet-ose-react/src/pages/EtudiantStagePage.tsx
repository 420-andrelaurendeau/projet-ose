import React, {useState} from "react";
import EtudiantStage from "../components/common/EtudiantStage";
import SidebarOptionSwitcher from "./SidebarOptionSwitcher";
import {useLocation} from "react-router-dom";

function EtudiantStagePage() {
    const [darkMode, setDarkMode] = useState(false);
    const location = useLocation();
    const user = location.state;
    function toggleDarkMode() {
        setDarkMode(!darkMode)
    }

    return (
        <html lang="en">
            <head>
                <title>Etudiant Inscription</title>
            </head>
            <body className={darkMode ? "h-screen bg-dark" : "h-screen bg-white"}>
            <div className="items-center">
                <div className="w-full">
                    <SidebarOptionSwitcher
                        user={user}
                    />
                </div>
            </div>
            </body>
        </html>
    );
}
export default EtudiantStagePage;