import React from "react";
import {useLocation} from "react-router-dom";
import Home from "../components/common/Home";

function HomePage() {
    const location = useLocation();
    const user = location.state;
    return (
        <div className="min-h-screen h-full bg-darkwhite dark:bg-softdark">
            <Home user={user}/>
        </div>
    );
}

export default HomePage;