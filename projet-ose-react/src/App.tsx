import './App.css';
import {useState} from "react";
import EtudiantInscription from "./pages/etudiantInscription";

function App() {
    const [darkMode, setDarkMode] = useState(false);
    function toggleDarkMode() {
        setDarkMode(!darkMode)
    }

    return (
        <>
            <EtudiantInscription darkMode={darkMode} toggleDarkMode={toggleDarkMode} />
        </>
    );
}


export default App;