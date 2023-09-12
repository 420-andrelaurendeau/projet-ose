import '../css/Main.css';
import React from "react";
import "../css/index.css";
import Button from "../components/Button";
import {useNavigate} from "react-router-dom";

function ConnectPage() {
    const navigate = useNavigate()

    return (
        <div className={"container-fluid h-100"}>

            <div className={"row h-25"}/>
            <div className={"row"}>
                <p className={'text-center'} style={{fontSize: '5rem'}}> Welcome to the library </p>
                <div className={"col-4"}/>
                <div className={"col-2 text-center"}>
                    <Button text="Sign In" onClick={() => {navigate("/home")}}/>
                </div>
                <div className={"col-2 text-center"}>
                    <Button text="Management" onClick={() => {navigate("/management")}}/>
                </div>
            </div>
            <div className={"row h-25"}/>
        </div>
    );
}
export default ConnectPage;