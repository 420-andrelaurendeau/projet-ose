import React from "react";
import ConnectForm from "../components/common/ConnectForm";

function ConnectPage() {
    const [darkMode, setDarkMode] = React.useState(false)
    function toggleDarkMode() {
        setDarkMode(prevDarkMode => !prevDarkMode)
    }
    return (
        <html lang="en">
        <head>
            <meta charSet="UTF-8"/>
            <title>Page de connection</title>
        </head>
        <body className={darkMode ? "h-screen bg-dark" : "h-screen bg-white"}>
        <div className="items-center">
            <ConnectForm
                darkMode={darkMode}
                toggleDarkMode={toggleDarkMode}
            />
        </div>
        </body>
        </html>
    );
}
export default ConnectPage;