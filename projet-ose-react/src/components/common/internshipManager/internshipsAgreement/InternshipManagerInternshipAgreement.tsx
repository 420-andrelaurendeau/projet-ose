import React, {useEffect, useRef, useState} from "react";
import {useNavigate} from "react-router-dom";
import {ReactPainter} from "react-painter";
import {ReactComponent as Icon} from '../../../../assets/icons/back_icon.svg';
import {useToast} from "../../../../hooks/state/useToast";
// todo : change the sort field with correct name of the api

export default function InternshipManagerInternshipAgreement(intershipAggreement:any) {

    const navigate = useNavigate();
    const toast = useToast();

    const [numPage, setNumPage] = useState(null);
    const [signature, setSignature] = useState(null);

    const isLoading = useRef(false);

    useEffect(() => {
        console.log(intershipAggreement)
        function fetchReviewRequest() {
            isLoading.current = true;
            // todo : fetch the review request
            try {

            } catch (error) {
                toast.error("Une erreur est survenue lors du chargement de l'offre");
            }
            isLoading.current = false;
        }

        if (!isLoading.current)
            fetchReviewRequest();

    }, []);

    function blobToBase64(blob: Blob, callback: (arg0: string | ArrayBuffer | null) => void) {
        const reader = new FileReader();

        reader.onload = () => {
            const base64String = reader.result;
            callback(base64String);
        };

        reader.readAsDataURL(blob);
    }


    function sendsignature(blop: Blob) {
        blobToBase64(blop, function (base64String) {
            console.log(base64String);
        });

        // todo send the signature to the api
        // id: employé, id: étudiant, id: offre de stage, signature: base64String
    }


    return (
        <div className="h-max ">
            <button
                className="fixed z-10 top-20 left-4 p-2 bg-blue dark:bg-orange rounded-full shadow-lg text-offwhite hover:font-bold"
                onClick={() => navigate("/internshipmanager/home/offers")}
            >
                <Icon className="w-5 h-5 fill-current hover:font-bold"/>
            </button>

            <h1 className="text-center text-3xl font-bold"> Titre de l'offre de stage </h1>

            <div
                className="block sm:flex mt-5 sm:justify-between sm:items-start sm:w-3/4 sm:mx-auto dark:text-offwhite">

                <div className='block items-center min-h-50'>


                    <div className="flex pb-4">
                        <h2 className="font-bold text-2xl">Étudiant</h2>
                    </div>

                    <div className="ml-8 p-1">

                        <p className="p-1"> {intershipAggreement.etudiantDto.nom + intershipAggreement.etudiantDto.prenom}</p>


                        <p className="p-1">{intershipAggreement.etudiantDto.email}</p>

                        <p className="p-1"> {intershipAggreement.stateStudent} </p>
                    </div>

                </div>
                <div className="block sm:flex flex-col sm:justify-end sm:items-end h-full sm:text-end">
                    <div className="flex pb-4">
                        <h2 className="font-bold text-2xl">Employeur</h2>
                    </div>

                    <div className="sm:mr-8 ml-8 p-1">

                        <p className="p-1"> {intershipAggreement.stateEmployeur}</p>

                        {/* Company field */}
                        <p className="p-1"> {intershipAggreement.employeur.nom + " " + intershipAggreement.employeur.prenom}</p>

                        <p className="p-1"> {intershipAggreement.employeur.email}</p>

                        <p className="p-1"> {intershipAggreement.stateEmployeur} </p>
                    </div>
                </div>
            </div>


            <ReactPainter
                width={300}
                height={300}
                onSave={blob => sendsignature(blob)}
                lineCap={"round"}
                render={({triggerSave, canvas}) => (
                    <div className="text-center">
                        <div className="border border-1 border-black" style={{height: 300 + "px", width: 300 + "px"}}>
                            {canvas}
                        </div>
                        <button onClick={triggerSave}>Signer le contrat</button>
                    </div>
                )}
            />

        </div>
    );
}