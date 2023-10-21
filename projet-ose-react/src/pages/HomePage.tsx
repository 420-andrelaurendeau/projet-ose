import React from "react";
import {Outlet, useLocation} from "react-router-dom";
import Home from "../components/common/Home";

function HomePage() {
    const location = useLocation();
    const user = location.state;
    return (
        <div >
            <Outlet/>
        </div>
    );
}

export default HomePage;