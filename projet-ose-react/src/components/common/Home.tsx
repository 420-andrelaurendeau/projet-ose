import React, {useEffect} from "react";
import Header from "../../Header";
import EmployeurHomePage from "../../pages/EmployeurHomePage";
import EtudiantStage from "./EtudiantStage";
import EtudiantStagePage from "../../pages/EtudiantStagePage";
function Home(props:any) {
    console.log(props.user)
    useEffect(() => {

        console.log(localStorage.getItem('theme'))
    }, [localStorage.getItem('theme')]);
    return (
       <div>
           <Header
           />
           {
                props.user.matricule ?
                    <EtudiantStagePage

                    />
                    : <EmployeurHomePage/>
           }
       </div>
    );
}

export default Home;