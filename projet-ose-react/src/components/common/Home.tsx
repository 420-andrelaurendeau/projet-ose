import React from "react";
import Header from "../../Header";
import {Outlet} from "react-router-dom";

function Home(props: any) {

    const userr = {
        user: {
            matricule: "123456789",
        }
    }
    /**
     <div className="border-red border-4 flex h-screen">
     {/* <Header user={userr}/>}

<div className="border-4 border-black py-6 h-full w-full">
</div>

</div>
     */

    return (
        <div className="">

            <div className="border-4 border-blue">
                <Header user={userr}/>
            </div>
            <div className="h-20"></div>
            <div className="border-4 border-red">
                <Outlet/>
            </div>
        </div>
    );
}

export default Home;