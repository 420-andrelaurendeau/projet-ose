import React, {useEffect, useRef, useState} from "react";
import i18n from "i18next";
import {Interview} from "../../model/Interview";
import {useAuth} from "../../authentication/AuthContext";
import {getUser} from "../../api/UtilisateurAPI";
import {acceptStage, declineStage, fetchStagePending} from "../../api/StudentApi";
import {Stage} from "../../model/Stage";
import {useTranslation} from "react-i18next";


function StudentStagePage(){
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "StudentInterview");
    const {t} = useTranslation()
    const [stages, setStages] = React.useState<Stage[]>([])
    const [user, setUser] = useState<any>(null);
    const isLoading = useRef(false);
    const auth = useAuth();

    const handleOnAccept = (stage: any) => {
        acceptStage(stage).then((res) => {
            console.log(res);
            if (res === true) {
                setStages(stages.map((element) => {
                    if(element.id == stage.id){
                        element.stateStudent = "ACCEPTED";
                    }
                    return element;
                }));
            }
        });
    }

    const handleOnDecline = (stage: any) => {
        declineStage(stage).then((res) => {
            console.log(res);
            if (res === true) {
                setStages(stages.map((element) => {
                    if(element.id === stage.id){
                        element.stateStudent = "DECLINED";
                    }
                    return element;
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
         <div className="text-white dark:bg-softdark pt-24">
             <div className="flex flex-col justify-center max-md:pt-24 pb-14">
                 <div className="xs:-mx-1 lg:-mx-2">
                     <div className="max-md:pt-2 min-w-full xs:px-6 lg:px-8">
                         <div className="overflow-x-hidden hover:overflow-auto border border-gray dark:border-darkgray xxxs:rounded-lg">
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
                                             <td className="px-6 py-4 whitespace-nowrap text-sm font-medium dark:text-white">
                                                 {stage.offer.title}
                                             </td>
                                             <td className="px-6 py-4 whitespace-nowrap text-sm font-medium  dark:text-white">
                                                 {stage.offer.location}
                                             </td>
                                             <td className="px-6 py-4 whitespace-nowrap text-sm font-medium  dark:text-white">
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
                                                 {t("StudentInterview.table.noStage")}
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