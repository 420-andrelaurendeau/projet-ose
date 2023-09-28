import {useState} from "react";
import EtudiantInscription from "../components/common/etudiantInscription";

function EtudiantInscriptionPage() {
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
            <EtudiantInscription
                darkMode={darkMode}
                toggleDarkMode={toggleDarkMode}
            />
        </div>
        </body>
        </html>
    )
}

export default EtudiantInscriptionPage;