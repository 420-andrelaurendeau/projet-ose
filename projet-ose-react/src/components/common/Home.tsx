import React from "react";
import Header from "../../Header";
import {Outlet} from "react-router-dom";

function Home(props: any) {

    //TODO Temporaire
    const userr = {
        user: {
            matricule: "123456789",
        }
    }

    return (
        <div className="dark:bg-dark min-h-screen">

            <div className="pb-4">
                <Header user={userr}/>
            </div>
            <div className="h-20"></div>
            <div className="px-4">
                <Outlet/>
            </div>
        </div>
    );
}

export default Home;