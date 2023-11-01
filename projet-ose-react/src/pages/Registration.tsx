import React, {useEffect, useState} from "react";
import {render} from "@testing-library/react";
import InscriptionEmployeur from "../components/common/inscriptionEmployeur";
import EtudiantInscription from "../components/common/student/form/StudentInscriptionForm";
import StudentInscriptionForm from "../components/common/student/form/StudentInscriptionForm";


function Registration() {
    const [isButtonClicked, setIsButtonClicked] = useState(false);

    const handleButtonClick = (value:boolean) => {
        setIsButtonClicked(value);
        console.log(isButtonClicked)
    };

    return(
        <>
            <div>
                <div className="flex justify-center items-start ">
                    <button className={
                        !isButtonClicked ?'m-4 p-2' : 'border-b-4 m-4 p-2 cursor-not-allowed'
                    }
                            onClick={() => handleButtonClick(true)}
                            disabled={isButtonClicked}
                    >
                        Inscription Employeur
                    </button>
                    <button className={
                        isButtonClicked ? 'm-4 p-2' : 'border-b-4 m-4 p-2 cursor-not-allowed'
                    }
                            onClick={() => handleButtonClick(false)}
                            disabled={!isButtonClicked}
                    >
                        Inscription Etudiant
                    </button>
                </div>
                <div className="flex justify-center items-start">
                    {isButtonClicked ? (
                        <InscriptionEmployeur/>
                    ) : (
                        <StudentInscriptionForm/>
                    )}
                </div>
            </div>

        </>
    );
}

export default Registration;