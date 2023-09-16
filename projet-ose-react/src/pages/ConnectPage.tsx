import React from "react";
import ConnectForm from "../components/common/ConnectForm";
import TestBackEndConnection from "../components/common/testBackEndConnection";

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

            <TestBackEndConnection/>
        </div>
        </body>
        </html>
    );
}
export default ConnectPage;