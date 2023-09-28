import React from "react";
import {useLocation} from "react-router-dom";
import Nav from "../components/common/Nav";

function ErrorPage() {
    const location = useLocation();
    const user = location.state;
    return (
        <div className="w-screen bg-darkwhite">
            <Nav/>
        </div>
    );
}

export default ErrorPage;