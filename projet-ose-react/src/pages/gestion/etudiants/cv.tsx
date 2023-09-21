import {useState} from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFileSignature } from '@fortawesome/free-solid-svg-icons'


function EvaluerCV() {
    const [displayCV, setDisplayCV] = useState(false);
    const [displayDisplayAssessmentPopup, setDisplayAssessmentPopup] = useState(false);
    return (
        <>
            <nav className="w-full relative flex flex-wrap items-center justify-center px-2 py-3 bg-gray-500 mb-3">
                <h1>Header Commun ici</h1>
            </nav>
            <h1 className="font-extrabold text-2xl">EvaluerCV</h1>
            <div onClick={() => { setDisplayCV(true) } }
                 className="mx-12 px-7 py-3 bg-slate-50 rounded-3xl">
                <div className="w-full relative flex flex-wrap items-center justify-between">
                    <p>Nom, Prenom</p>
                    <p>Formation</p>
                </div>
                <p>Matricule</p>
                <br />
                <div className="w-full relative flex flex-wrap items-center justify-between">
                    <p>Nom du Document</p>
                    <p>Date de Remise</p>
                </div>
                <br />
                <h1 className="text-lg font-bold">Commentaire:</h1>
                <p>Commentaire</p>
            </div>

            {
                displayCV ?
                     (
                        <>
                            <div className="absolute top-0 bg-dark w-full h-8 z-10 flex justify-end items-center px-3 ">
                                <FontAwesomeIcon icon={faFileSignature} className="text-white" onClick={() => {
                                    setDisplayCV(false);
                                    setDisplayAssessmentPopup(true);
                                }} />
                            </div>
                            <div className="absolute bottom-0 bg-dark w-full h-full opacity-75 z-0"  onClick={() => { setDisplayCV(false) } }></div>
                            <div className="absolute bottom-0 bg-white h-[90%] aspect-[216/279] z-10 -translate-x-1/2 -translate-y-1/2 left-1/2 top-1/2">
                                Je suis un document.
                            </div>
                        </>
                ) : null
            }
            {
                displayDisplayAssessmentPopup ? (
                    <>
                        <div className="absolute bottom-0 bg-dark w-full h-full opacity-75 z-0"  onClick={() => { setDisplayAssessmentPopup(false) } }></div>
                        <div className="absolute bottom-0 bg-white h-3/5 w-1/3 z-10 -translate-x-1/2 -translate-y-1/2 left-1/2 top-1/2">
                            <h1 className="font-bold text-2xl relative px-5 py-3 w-100 text-center">Evaluer le CV</h1>
                            <div className="w-full flex-start flex">
                                <p>Nom, Prenom</p>
                                <p>Formation</p>
                                <input type="text" className="flex-1"/>
                                <hr/>
                            </div>
                        </div>
                    </>
                ) : null
            }
        </>
    );
}


export default EvaluerCV;