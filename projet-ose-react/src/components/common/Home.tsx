import React, {useEffect} from "react";
import Header from "../../Header";
import EmployeurHomePage from "../../pages/EmployeurHomePage";
import EtudiantStagePage from "../../pages/EtudiantStagePage";
import GSInternOfferPage from "../../pages/GSInternOfferPage";
import GSOffersPage from "../../pages/GSOffersPage";

function Home(props: any) {
    useEffect(() => {

        console.log(localStorage.getItem('theme'))
    }, [localStorage.getItem('theme')]);
    return (
        <div>
            <Header/>
            {
                props.user.id == 5 ? <GSOffersPage/> : props.user.matricule
                    ? <EtudiantStagePage/>
                    : <EmployeurHomePage/>
            }
        </div>
    );
}

export default Home;