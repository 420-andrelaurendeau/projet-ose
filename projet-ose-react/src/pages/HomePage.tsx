import React from "react";
import {Outlet, useLocation} from "react-router-dom";
import Layout from "../components/layout/Layout";

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