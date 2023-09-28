import {useState} from "react";
import EtudiantStage from "../components/common/etudiantStage";

function EtudiantStagePage() {
    const [darkMode, setDarkMode] = useState(false);
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
                <EtudiantStage
                    darkMode={darkMode}
                    toggleDarkMode={toggleDarkMode}
                />
            </div>
            </body>
        </html>
    );
}
export default EtudiantStagePage;