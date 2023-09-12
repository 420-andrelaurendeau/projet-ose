import React from "react";
import Button from "../components/Button";
import {useNavigate} from "react-router-dom";
import ResponsiveAppBar from "../components/AppBar";
import NavBar from "../components/AppBar";

function ConnectPage() {
    const navigate = useNavigate()

    return (
        <div className={"container-fluid h-100"}>
            <NavBar/>

        </div>
    );
}
export default ConnectPage;