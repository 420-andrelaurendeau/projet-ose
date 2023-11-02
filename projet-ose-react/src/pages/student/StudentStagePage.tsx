import React, {useEffect, useRef, useState} from "react";
import i18n from "i18next";
import {Interview} from "../../model/Interview";
import {useAuth} from "../../authentication/AuthContext";
import {getUser} from "../../api/UtilisateurAPI";
import {acceptStage, declineStage, fetchStagePending} from "../../api/StudentApi";
import {Stage} from "../../model/Stage";


function StudentStagePage(){
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "StudentInterview");
    const [stages, setStages] = React.useState<Stage[]>([])
    const [user, setUser] = useState<any>(null);
    const isLoading = useRef(false);
    const auth = useAuth();

    const handleOnAccept = (stage: any) => {
        acceptStage(stage).then((res) => {
            console.log(res);
            if (res === true) {
                setStages(stages.map((stage) => {
                    stage.stateStudent = "ACCEPTED";
                    return stage;
                }));
                console.log(stages);
            }
        });
    }

    const handleOnDecline = (stage: any) => {
        declineStage(stage).then((res) => {
            console.log(res);
            if (res === true) {
                setStages(stages.map((stage) => {
                    stage.stateStudent = "DECLINED";
                    return stage;
                }));
            }
        });
    }

    useEffect(() => {
        const fetchUser = async () => {
            isLoading.current = true;

            getUser(auth.userEmail!)
                .then((resUser) => {
                    setUser(resUser);
                    console.log(resUser);
                    fetchStagePending(resUser.id).then((res) => {
                        setStages(res);
                    });
                })
                .catch((err) => {
                    console.log(err);
                })
                .finally(() => (isLoading.current = false));
        };
        if (!isLoading.current) {
            fetchUser();
        }
    }, []);

    return(
     <>
         <div className="text-white dark:bg-black">
             <div className="flex flex-col items-center">
                 <div className=" lg:-mx-8 mt-28 w-11/12 ">
                     <div
                         className=" md:z-50 md:top-0 md:left-0 justify-center md:w-full md:h-full md:flex md:p-3 max-md:w-full ">
                         <div className=" w-full">
                             <table className=" w-full divide-y divide-gray dark:divide-darkgray">
                                 <thead className="bg-blue dark:bg-orange ">
                                 <tr>
                                     <th
                                         scope="col"
                                         className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                                     >
                                         {fields.table.title}
                                     </th>
                                     <th
                                         scope="col"
                                         className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                                     >
                                         {fields.table.location}
                                     </th>
                                     <th
                                         scope="col"
                                         className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                                     >

                                         {fields.table.company}
                                     </th>
                                     <th
                                         scope="col"
                                         className="px-6 py-3 text-left text-xs font-medium text-white uppercase tracking-wider"
                                     >
                                         {fields.table.action.text}
                                     </th>
                                 </tr>
                                 </thead>
                                 <tbody className="bg-white text-black divide-y divide-gray dark:bg-darkgray dark:divide-darkgray">
                                 {stages && stages.length > 0 ? (
                                     stages.map((stage) => (
                                         <tr key={stage.id}>
                                             <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
                                                 {stage.offer.title}
                                             </td>
                                             <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
                                                 {stage.offer.location}
                                             </td>
                                             <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
                                                 {stage.offer.employeurEntreprise}
                                             </td>
                                             <td className="px-6 py-4 whitespace-nowrap">
                                                 <div className="flex justify-start space-x-2">
                                                     <button
                                                         disabled={stage.stateStudent !== "PENDING"}
                                                         onClick={() => handleOnAccept(stage)}
                                                         className="disabled:bg-gray text-white bg-green hover:bg-green-700 px-3 py-2 rounded-md text-sm font-medium"
                                                     >
                                                         {fields.table.action.button.accept}
                                                     </button>
                                                     <button
                                                         disabled={stage.stateStudent !== "PENDING"}
                                                         onClick={() => handleOnDecline(stage)}
                                                         className="disabled:bg-gray text-white bg-red hover:bg-red-700 px-3 py-2 rounded-md text-sm font-medium"
                                                     >
                                                         {fields.table.action.button.decline}
                                                     </button>
                                                     {stage.stateStudent === "ACCEPTED" && <p className="text-white bg-blue hover:bg-red-700 px-3 py-2 rounded-md text-sm font-bold">{fields.table.action.status.accepted}</p>}
                                                     {stage.stateStudent === "DECLINED" && <p className="text-white bg-red hover:bg-red-700 px-3 py-2 rounded-md text-sm font-bold">{fields.table.action.status.declined}</p>}
                                                 </div>
                                             </td>
                                         </tr>
                                     ))
                                 ) : (
                                     <tr>
                                         <td colSpan={5}>
                                             <div className="w-full text-center bg-red text-white">
                                                 Aucune entreprise ne vous a encore accepte ...
                                             </div>
                                         </td>
                                     </tr>
                                 )}
                                 </tbody>
                             </table>
                         </div>
                     </div>
                 </div>
             </div>
         </div>
     </>
    );
}

export default StudentStagePage;