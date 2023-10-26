import React from "react";
import Header from "../common/shared/header/Header";
import {Outlet} from "react-router-dom";
import { t } from "i18next";

function Layout(props: any) {

    //TODO Temporaire
    const userr = {
        user: {
            matricule: "123456789",
        }
    }

    return (
        <div className="bg-darkwhite dark:bg-softdark min-h-screen">

            <div className="">
                <Header/>
            </div>
            <div className="">
                <Outlet/>
            </div>
        </div>
    );
}

export default Layout;