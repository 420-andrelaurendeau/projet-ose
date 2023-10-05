import React, {useEffect} from "react";
import Header from "../../Header";
import EmployeurHomePage from "../../pages/EmployeurHomePage";
function Home(props:any) {
    console.log(props.user)
    useEffect(() => {

        console.log(localStorage.getItem('theme'))
    }, [localStorage.getItem('theme')]);
    return (
       <div>
           <Header
               user={props.user}
           />
           {
                props.user.matricule ?
                    <div className="w-full h-screen flex flex-col items-center justify-center">
                        <div className="flex flex-col items-center justify-center">
                            <p className="md:text-lg xl:text-xl text-gray-500 mt-4">Whoops, sorry <b>{props.user.nom} {props.user.prenom}</b> something went wrong on our servers.</p>
                        </div>
                    </div>
                    : <EmployeurHomePage/>
           }
       </div>
    );
}

export default Home;