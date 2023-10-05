import React, {useEffect} from "react";
import Header from "../../Header";
import EmployeurHomePage from "../../pages/EmployeurHomePage";
import EtudiantStage from "./EtudiantStage";
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
                    <EtudiantStage/>
                    : <EmployeurHomePage/>
           }
       </div>
    );
}

export default Home;