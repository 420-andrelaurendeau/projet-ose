import React from "react";
import {useLocation} from "react-router-dom";
import Home from "../components/common/Home";

function HomePage() {
    const location = useLocation();
    const user = location.state;
    return (
        <Home user={user}/>
    );
}

export default HomePage;