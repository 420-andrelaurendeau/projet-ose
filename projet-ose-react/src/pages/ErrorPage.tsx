import ErrorComponent from "../components/common/ErrorComponent";
import {useState} from "react";

export default function ErrorPage() {
    const [darkMode, setDarkMode] = useState(false);
    function toggleDarkMode() {
        setDarkMode(!darkMode)
    }
    return (
        <ErrorComponent
            darkMode={darkMode}
            toggleDarkMode={toggleDarkMode}
        />
    );
}