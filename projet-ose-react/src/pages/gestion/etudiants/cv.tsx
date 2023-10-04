import {useState} from "react";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faFileSignature} from '@fortawesome/free-solid-svg-icons'


function EvaluerCV() {
    const [displayCV, setDisplayCV] = useState(false);
    const [displayAssessmentPopup, setDisplayAssessmentPopup] = useState(false);
    return (
        <>
            <div className="md:p-16 md:m-16 p-4 m-4">
                <h1 className="font-extrabold text-2xl">CVs &aacute; &Eacute;valuer</h1>
                <div onClick={() => {
                    setDisplayCV(true)
                }}
                     className="mx-12 my-16 px-7 py-3 bg-slate-50 rounded-3xl flex flex-wrap">
                    <p className="basis-full flex-grow pb-2 md:pb-0">Nom, Prenom</p>
                    <p className="basis-full md:basis-1/2">Formation</p>
                    <p className="basis-full md:basis-1/2 md:text-end pb-2 md:pb-0">Matricule</p>
                    <p className="basis-full md:basis-1/2 flex-grow">Nom du Document</p>
                    <p className="basis-full md:basis-1/2 flex-grow md:text-end">Date de Remise</p>
                    <h1 className="text-lg font-bold basis-full flex-grow mt-4">Commentaire:</h1>
                    <p className="basis-full flex-grow">Commentaire</p>
                </div>
            </div>

            {
                displayCV ?
                    (
                        <>
                            <div className="absolute top-0 bg-dark w-full h-8 z-20 flex justify-end items-center px-3 ">
                                <FontAwesomeIcon icon={faFileSignature} className="text-white" onClick={() => {
                                    setDisplayCV(false);
                                    setDisplayAssessmentPopup(true);
                                }}/>
                            </div>
                            <div className="fixed bottom-0 bg-dark w-full h-full opacity-75 z-0" onClick={() => {
                                setDisplayCV(false)
                            }}></div>
                            <div className="absolute bottom-0 overflow-scroll scroll-auto z-10 w-screen h-[calc(100vh-2rem)]">
                                <div className="flex flex-col justify-end items-center px-3">
                                    <div
                                        className="my-3 bg-white w-[90vw] sm:w-auto sm:h-[80vh] md:h-[90vh] aspect-[216/279]">
                                        Je suis un document. Page 1
                                    </div>
                                    <div
                                        className="bg-white w-[90vw] sm:w-auto sm:h-[80vh] md:h-[90vh] aspect-[216/279]">
                                        Je suis un document. Page 2
                                    </div>
                                </div>
                            </div>
                        </>
                    ) : null
            }
            {
                displayAssessmentPopup ? (
                    <>
                        <div className="absolute bottom-0 bg-dark w-full h-full opacity-75 z-0" onClick={() => {
                            setDisplayAssessmentPopup(false)
                        }}></div>
                        <div
                            className="absolute bottom-0 bg-white h-3/5 w-1/3 z-10 -translate-x-1/2 -translate-y-1/2 left-1/2 top-1/2">
                            <h1 className="font-bold text-2xl relative px-5 py-3 w-100 text-center">Evaluer le CV: NOM_DU_CV</h1>
                            <div className="w-full h-full flex-start flex px-5 py-3">
                                <div className="w-full max-h-full relative flex flex-col flex-wrap">
                                    <p className="px-2 w-full">Nom, Prenom</p>
                                    <p className="px-2 w-full">Formation</p>
                                    <p className="px-2 w-full">Matricule</p>
                                    <div className="flex-grow">a</div>
                                    <input type="text" className="w-full outline-1"/>
                                </div>
                                <br/>
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