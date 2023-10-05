import React from "react";
import Header from "../../Header";
import {useTranslation} from "react-i18next";

function Home(props:any) {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.Home");

    return (
       <div>
           <Header user={props.user}/>
           <div className="w-full h-screen flex flex-col items-center justify-center">
               <div className="flex flex-col items-center justify-center">
                   <p className="md:text-lg xl:text-xl text-gray-500 mt-4">{fields.text}{props.user.prenom} {props.user.nom}</p>
               </div>
           </div>
       </div>
    );
}

export default Home;