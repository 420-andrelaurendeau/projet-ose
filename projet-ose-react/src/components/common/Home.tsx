import React from "react";
import Header from "../../Header";
function Home(props:any) {
    return (
       <div>
           <Header user={props.user}/>
           <div className="w-full h-screen flex flex-col items-center justify-center">
               <div className="flex flex-col items-center justify-center">
                   <p className="md:text-lg xl:text-xl text-gray-500 mt-4">Whoops, sorry <b>{props.user.nom} {props.user.prenom}</b> something went wrong on our servers.</p>
               </div>
           </div>
       </div>
    );
}

export default Home;