import {useState} from "react";
import StudentInscriptionForm from "../../components/common/student/form/StudentInscriptionForm";

function StudentInscriptionPage() {
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
                    <StudentInscriptionForm
                        darkMode={darkMode}
                        toggleDarkMode={toggleDarkMode}
                    />
                </div>
            </body>
        </html>
    )
}

export default StudentInscriptionPage;