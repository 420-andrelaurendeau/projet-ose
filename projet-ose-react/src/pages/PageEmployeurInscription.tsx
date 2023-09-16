import React from "react";
import InscriptionEmployeur from "../components/common/inscriptionEmployeur";

function EmployeurInscription() {
  const [darkMode, setDarkMode] = React.useState(false);
  function toggleDarkMode() {
    setDarkMode((prevDarkMode) => !prevDarkMode);
  }
  return (
    <div className={darkMode ? "h-screen bg-dark" : "h-screen bg-white"}>
      <InscriptionEmployeur
        darkMode={darkMode}
        toggleDarkMode={toggleDarkMode}
      />
    </div>
  );
}
export default EmployeurInscription;
