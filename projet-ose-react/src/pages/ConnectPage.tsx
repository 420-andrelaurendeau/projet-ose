import React from "react";
import ConnectForm from "../components/common/ConnectForm";

function ConnectPage() {
    const [darkMode, setDarkMode] = React.useState(false)

    function toggleDarkMode() {
        setDarkMode(prevDarkMode => !prevDarkMode)
    }

    return (

        <div className={darkMode ? "h-screen w-full bg-dark" : "h-screen w-full bg-white"}>
            <div className="items-center">
                <ConnectForm
                    darkMode={darkMode}
                    toggleDarkMode={toggleDarkMode}
                />
            </div>
        </div>
    );
}

export default ConnectPage;